package com.example.demo.bootstrap;

import com.example.demo.dao.CustomerRepository;
import com.example.demo.dao.DivisionRepository;
import com.example.demo.dao.CountryRepository;
import com.example.demo.entity.Country;
import com.example.demo.entity.Customer;
import com.example.demo.entity.Division;
import jakarta.transaction.Transactional;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class BootStrapData implements CommandLineRunner {
    private final CustomerRepository customerRepository;
    private final DivisionRepository divisionRepository;
    private final CountryRepository countryRepository;

    public BootStrapData(CustomerRepository customerRepository, DivisionRepository divisionRepository, CountryRepository countryRepository) {
        this.customerRepository = customerRepository;
        this.divisionRepository = divisionRepository;
        this.countryRepository = countryRepository;
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        Division nJ = divisionRepository.findById(29L).orElse(null);

        if (nJ == null) {
          nJ = new Division();
          Country country = countryRepository.findById(1L).orElse(null);
          if (country == null) {
              country = new Country();
              country.setId(1L);
              countryRepository.save(country);
          }
            nJ.setCountry(country);
        }

        Customer jackie = new Customer("Jackie", "Smith", "1000 Parkway Ave", "12345", "609-445-7775", nJ);
        Customer gavin = new Customer("Gavin", "Wheatley", "1001 Parkway Ave", "12345", "609-328-7774", nJ);
        Customer katlin = new Customer("Katlin", "Wheatley", "1001 Parkway Ave", "12345", "609-108-7773", nJ);
        Customer steve = new Customer("Steve", "Richtmyer", "1002 Parkway Ave", "12345", "609-658-7772", nJ);
        Customer josh = new Customer("Josh", "Johnston", "1002 Parkway Ave", "12345", "609-111-7771", nJ);

        List<Customer> customerSet = Arrays.asList(jackie, gavin, katlin, josh, steve);

        for (Customer customer: customerSet) {
            Optional<Customer> existingCustomer = customerRepository.findByFirstNameAndLastName(customer.getFirstName(), customer.getLastName());

            if (existingCustomer.isPresent()) {
                continue;
            }

            else {
                customer.setDivision(nJ);
                nJ.getCustomers().add(customer);
                customerRepository.save(customer);
            }
        }

        divisionRepository.save(nJ);

        List<Customer> customers = customerRepository.findAll();
        for (Customer customer : customers) {
            System.out.println(customer.getFirstName() + " " + customer.getLastName());
        }
    }
}

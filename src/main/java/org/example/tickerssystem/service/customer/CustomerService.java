package org.example.tickerssystem.service.customer;

import org.example.tickerssystem.entity.Customer;

import java.util.List;

public interface CustomerService {
    void save(Customer customer);
    void saveAll(List<CustomerDTO> customer);
    List<Customer> findAll();
    void deleteAll();
}

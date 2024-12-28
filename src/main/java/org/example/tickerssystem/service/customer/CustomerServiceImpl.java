package org.example.tickerssystem.service.customer;

import org.example.tickerssystem.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.example.tickerssystem.repository.customer.CustomerRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public void save(Customer customer) {
        customerRepository.save(customer);
    }

    @Override
    public void saveAll(List<CustomerDTO> customers) {
        List<Customer> customerList = new ArrayList<Customer>();
        for (CustomerDTO customer : customers) {
            Customer entity = new Customer();
            entity.setName(customer.getName());
            entity.setEmail(customer.getEmail());
            entity.setPhone(customer.getPhone());
            customerList.add(entity);
        }
        customerRepository.saveAll(customerList);
    }

    @Override
    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    @Override
    public void deleteAll() {
        customerRepository.deleteAll();
    }
}

package com.lopezino.springmvc.service;

import com.lopezino.springmvc.entity.Customer;
import com.lopezino.springmvc.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService{

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public List<Customer> findAllCustomers() {

        return customerRepository.findAllByOrderByLastNameDesc();
    }

    public void saveCustomer(Customer customer) {

        customerRepository.save(customer);
    }

    @Override
    public Customer findCustomerById(long id) {
        Optional<Customer> optional = customerRepository.findById(id);
        Customer customer = null;
        if (optional.isPresent()) {
            customer = optional.get();
        } else {
            throw new RuntimeException(" Customer not found for id :: " + id);
        }
        return customer;
    }

    @Override
    public void deleteCustomerById(long id) {
        this.customerRepository.deleteById(id);
    }
}

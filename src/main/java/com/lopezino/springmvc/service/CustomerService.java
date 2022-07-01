package com.lopezino.springmvc.service;

import com.lopezino.springmvc.entity.Customer;

import java.util.List;

public interface CustomerService {

    List<Customer> findAllCustomers();
    void saveCustomer(Customer customer);
    Customer findCustomerById(long id);
    void deleteCustomerById(long id);
}

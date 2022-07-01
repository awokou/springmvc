package com.lopezino.springmvc.controller;

import com.lopezino.springmvc.entity.Customer;
import com.lopezino.springmvc.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    //Display list of customer
    @GetMapping("/")
    public String getAllCustomers(Model model) {
        List<Customer> theCustomers = customerService.findAllCustomers();
        model.addAttribute("listCustomers", theCustomers);
        return "index";
    }

    @GetMapping("/showNewCustomerForm")
    public String showCustomerForm(Model model) {
        // create model attribute to bind form data
        Customer customer = new Customer();
        model.addAttribute("customer", customer);
        return "new_customer";
    }

    @PostMapping("/saveCustomer")
    public String saveCustomer(@ModelAttribute("customer") Customer customer) {
        // save customer to database
        customerService.saveCustomer(customer);
        return "redirect:/";
    }

    @GetMapping("/showFormForUpdate/{id}")
    public String showFormForUpdate(@PathVariable(value = "id") long id, Model model) {
        // get customer from the service
        Customer customer = customerService.findCustomerById(id);
        // set customer as a model attribute to pre-populate the form
        model.addAttribute("customer", customer);
        return "update_customer";
    }

    @GetMapping("/deleteCustomer/{id}")
    public String deleteCustomer(@PathVariable(value = "id") long id) {
        // call delete customer method
        this.customerService.deleteCustomerById(id);
        return "redirect:/";
    }
}

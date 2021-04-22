package com.example.amazonpart2mongodb.services;


import com.example.amazonpart2mongodb.models.Customer;
import com.example.amazonpart2mongodb.models.CustomerRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerService implements UserDetailsService {

    @Autowired
    private CustomerRepository respository;



    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public List<Customer> getCustomers()
    {
        //business logic
        return respository.findAll();

    }

    public Customer insertIntoCustomers(Customer customer) throws Exception {
        //add validation where username exists
        Optional<Customer> existingcustomer = Optional.ofNullable(respository.findUserByUsername(customer.getUsername()));

        if(existingcustomer.isPresent())
        {
            throw new Exception("Customer already exists");
        }


      //  respository.insert(customer);
        String encodedPassword = bCryptPasswordEncoder.encode(customer.getPassword());
        customer.setPassword(encodedPassword);
        String encodedConfirmPassword = bCryptPasswordEncoder.encode(customer.getConfirmPassword());
        customer.setConfirmPassword(encodedConfirmPassword);
        Customer insertedCustomer = respository.insert(customer);

        return insertedCustomer;
    }



    public Optional<Customer> getACustomer(String id) throws Exception {

        Optional<Customer> customer =  respository.findById(id);
        if(!customer.isPresent())
        {
            throw new Exception("Customer with "+id+" is not found");
        }
        return customer;
    }



    public void deleteACustomer(String id) throws Exception {
        Optional<Customer> customer =  respository.findById(id);
        if(!customer.isPresent())
        {
            throw new Exception("Customer with "+id+" is not found");
        }
        respository.deleteById(id);
    }


    public Customer editCustomer (String id, Customer newCustomerData) throws Exception {
        Optional<Customer> customer =  respository.findById(id);

        if(!customer.isPresent())
        {
            throw new Exception("Customer with ID:"+id+" is not found");
        }
        customer.get().setName(newCustomerData.getName());
        customer.get().setEmail(newCustomerData.getEmail());
        customer.get().setUsername(newCustomerData.getUsername());
        customer.get().setPassword(newCustomerData.getPassword());
        customer.get().setConfirmPassword(newCustomerData.getConfirmPassword());

        Customer updatedCustomer = respository.save(customer.get());
        return updatedCustomer;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Customer foundUser = respository.findUserByUsername(username);
        String userN = foundUser.getUsername();
        String password = foundUser.getPassword();
        return new User(userN,password,new ArrayList<>());
    }

    public Customer getACustomerByUsername(String username) throws Exception {

        Customer customer =  respository.findUserByUsername(username);

        return customer;
    }
}

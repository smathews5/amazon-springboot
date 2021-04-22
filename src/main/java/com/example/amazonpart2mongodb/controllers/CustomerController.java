package com.example.amazonpart2mongodb.controllers;


import com.example.amazonpart2mongodb.CustomizedResponse;

import com.example.amazonpart2mongodb.models.Customer;
import com.example.amazonpart2mongodb.models.Product;
import com.example.amazonpart2mongodb.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@CrossOrigin(origins="http://localhost:4000")
@RestController
public class CustomerController {

    @Autowired
    private CustomerService service;


    @GetMapping("/customers")
    public ResponseEntity getCustomers()
    {
        var customizedResponse = new CustomizedResponse("A list of customers",service.getCustomers());
        return new ResponseEntity(customizedResponse, HttpStatus.OK);
    }

    @GetMapping("/customers/{id}")
    public ResponseEntity getACustomer(@PathVariable("id") String id) throws Exception {

        CustomizedResponse customizedResponse = null;
        try {
            List customers = Collections.singletonList(service.getACustomer(id));
            customizedResponse = new CustomizedResponse("Customer with id :" + id, customers);

        }
        catch(Exception e){
            customizedResponse = new CustomizedResponse(e.getMessage(), null);
            return new ResponseEntity(customizedResponse, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(customizedResponse, HttpStatus.OK);
    }



    @DeleteMapping("/customers/{id}")
    public ResponseEntity deleteACustomer(@PathVariable("id") String id) throws Exception {
        CustomizedResponse customizedResponse = null;
        try{
            service.deleteACustomer(id);
            customizedResponse = new CustomizedResponse("Customer with ID: "+id +" is deleted successfully", null);
        }
        catch(Exception e)
        {
            customizedResponse = new CustomizedResponse(e.getMessage(), null);
            return new ResponseEntity(customizedResponse, HttpStatus.NOT_FOUND);
        }
        //   return new ResponseEntity(HttpStatus.OK);
        return new ResponseEntity(customizedResponse, HttpStatus.OK);
    }



    @PostMapping(value = "/customers",consumes = {
            MediaType.APPLICATION_JSON_VALUE
    })
    public ResponseEntity addCustomer(@RequestBody Customer customer) throws Exception
    {

        CustomizedResponse response = null;
        try {

            if (customer.getName() == null || customer.getName().equals("")) {
                var customizedResponse = new CustomizedResponse("Name cannot be empty",
                        null);

                //return new ResponseEntity( product, HttpStatus.OK);
                return new ResponseEntity(customizedResponse, HttpStatus.BAD_REQUEST);
            }
            if (customer.getUsername() == null || customer.getUsername().equals("")) {
                var customizedResponse = new CustomizedResponse("Username cannot be empty",
                        null);

                //return new ResponseEntity( product, HttpStatus.OK);
                return new ResponseEntity(customizedResponse, HttpStatus.BAD_REQUEST);
            }
            if (customer.getPassword() == null || customer.getPassword().equals("")) {
                var customizedResponse = new CustomizedResponse("Password cannot be empty",
                        null);

                //return new ResponseEntity( product, HttpStatus.OK);
                return new ResponseEntity(customizedResponse, HttpStatus.BAD_REQUEST);
            }
            if (customer.getConfirmPassword() == null || customer.getConfirmPassword().equals("")) {
                var customizedResponse = new CustomizedResponse("Confirm Password cannot be empty",
                        null);

                //return new ResponseEntity( product, HttpStatus.OK);
                return new ResponseEntity(customizedResponse, HttpStatus.BAD_REQUEST);
            }
            if (customer.getEmail() == null || customer.getEmail().equals("")) {
                var customizedResponse = new CustomizedResponse("Email cannot be empty",
                        null);
                return new ResponseEntity(customizedResponse, HttpStatus.BAD_REQUEST);
            }
             //both passwords be same
            if (!customer.getPassword().equals(customer.getConfirmPassword())) {
                var customizedResponse = new CustomizedResponse("Both passwords should match",
                        null);
                return new ResponseEntity(customizedResponse, HttpStatus.BAD_REQUEST);
            }

            //password length
            if (customer.getPassword().length() < 6 || customer.getPassword().length() > 12) {

                 var customizedResponse = new CustomizedResponse("Password should between 6 to 12 alphanumeric characters",
                            null);
               return new ResponseEntity(customizedResponse, HttpStatus.BAD_REQUEST);
            }


             //alpha-numeric
            if (!customer.getPassword().matches("^(?=.*[0-9])(?=.*[a-zA-Z])[a-zA-Z0-9]+$"))
            {
                var customizedResponse = new CustomizedResponse("Password should be alphanumeric",
                        null);
                return new ResponseEntity(customizedResponse, HttpStatus.BAD_REQUEST);
            }



            // service.insertIntoCustomers(customer);
            response = new CustomizedResponse("User created successfully", Collections.singletonList(service.insertIntoCustomers(customer)));
            return new ResponseEntity(response, HttpStatus.OK);
        }
        catch(Exception e)
        {
             response = new CustomizedResponse("Customer already exists", null);
            return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
        }
       // return new ResponseEntity(response, HttpStatus.OK);
    }

    @PutMapping(value = "/customers/{id}", consumes = {
            MediaType.APPLICATION_JSON_VALUE
    })
    public ResponseEntity editCustomers(@PathVariable("id") String id, @RequestBody Customer newCustomer)  throws Exception{

        CustomizedResponse customizedResponse = null;
        try {
            customizedResponse = new CustomizedResponse("Customer with ID: "+id +" is updated successfully", Collections.singletonList(service.editCustomer(id, newCustomer)));
        }
        catch(Exception e){
            customizedResponse = new CustomizedResponse(e.getMessage(), null);
            return new ResponseEntity(customizedResponse, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(customizedResponse, HttpStatus.OK);
    }

    @GetMapping("/customerbyusername/{username}")
    public ResponseEntity getACustomerByUserName(@PathVariable("username") String username) throws Exception {

        CustomizedResponse customizedResponse = null;
        try {
            List customer = Collections.singletonList(service.getACustomerByUsername(username));
            customizedResponse = new CustomizedResponse("Customer with username :" + username, customer);

        }
        catch(Exception e){
            customizedResponse = new CustomizedResponse(e.getMessage(), null);
            return new ResponseEntity(customizedResponse, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(customizedResponse, HttpStatus.OK);
    }


}

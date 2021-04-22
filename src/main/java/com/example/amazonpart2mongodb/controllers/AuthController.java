package com.example.amazonpart2mongodb.controllers;

import com.example.amazonpart2mongodb.CustomizedResponse;
import com.example.amazonpart2mongodb.models.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collections;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping(value = "/auth",consumes = {
            MediaType.APPLICATION_JSON_VALUE
    })
    public ResponseEntity login(@RequestBody Customer customer)
    {
        try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(customer.getUsername(),customer.getPassword()));
            var response = new CustomizedResponse("You logged in successfully",null);
            return  new ResponseEntity(response, HttpStatus.OK);

        }
        catch(BadCredentialsException e)
        {
            var response = new CustomizedResponse("Your username/password is incorrect",null);
            return  new ResponseEntity(response, HttpStatus.UNAUTHORIZED);
        }


    }

  /* New mathod****/
/*
    @RequestMapping(value="/logout", method = RequestMethod.GET)
    public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
        System.out.println("logout ");
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        System.out.println("AuthController"+ auth);
        if (auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
       return "";
    }

*/
}

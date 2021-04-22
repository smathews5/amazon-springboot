package com.example.amazonpart2mongodb.config;

import com.example.amazonpart2mongodb.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {



    @Autowired
    private CustomerService customerService;

    //This allows us to configure our Authentication
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
      auth.userDetailsService(customerService);
    }

    //This allows us to configure our authorization
    @Override
    protected void configure(HttpSecurity http) throws Exception {
      http.csrf().disable().authorizeRequests()
              .antMatchers("/customers/**").permitAll()
              .antMatchers("/auth/**").permitAll()
              .antMatchers("/categories/**").permitAll()
              .antMatchers("/products/**").permitAll()
              .antMatchers("/bestsellers/**").permitAll()
              .antMatchers("/productsbycategory/**").permitAll()
              .antMatchers("/searchproducts/**").permitAll()
              .antMatchers("/customerbyusername/**").permitAll()
              .antMatchers("/dashboard/**").permitAll()
              .anyRequest().authenticated();
    }

    //Bcrypt
    @Bean
    public BCryptPasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder();
    }

    @Override
    @Bean
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }
}

package com.example.amazonpart2mongodb.models;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends MongoRepository<Customer,String> {
  Customer findUserByUsername(String username);
 }

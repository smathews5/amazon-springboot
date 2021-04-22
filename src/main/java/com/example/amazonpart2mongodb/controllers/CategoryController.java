package com.example.amazonpart2mongodb.controllers;

import com.example.amazonpart2mongodb.CustomizedResponse;
import com.example.amazonpart2mongodb.models.Category;
import com.example.amazonpart2mongodb.models.Customer;
import com.example.amazonpart2mongodb.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@CrossOrigin(origins="http://localhost:4000")
@RestController
public class CategoryController {

    @Autowired
    private CategoryService service;

    @GetMapping("/categories")
    public ResponseEntity getCategories()
    {

        var customizedResponse = new CustomizedResponse("A list of categories",service.getCategories());

        return new ResponseEntity(customizedResponse, HttpStatus.OK);
    }

    @GetMapping("/categories/{id}")
    public ResponseEntity getACategory(@PathVariable("id") String id)
    {

        var customizedResponse = new CustomizedResponse("Category with id :"+id, Collections.singletonList(service.getACategory(id)));

        return new ResponseEntity(customizedResponse,HttpStatus.OK);
    }


    @DeleteMapping("/categories/{id}")
    public ResponseEntity deleteACategory(@PathVariable("id") String id) throws Exception
    {
        CustomizedResponse customizedResponse = null;
        try{
        service.deleteACategory(id);
            customizedResponse = new CustomizedResponse("Category with ID: "+id +" is deleted successfully", null);
        }
        catch(Exception e)
        {
            customizedResponse = new CustomizedResponse(e.getMessage(), null);
            return new ResponseEntity(customizedResponse, HttpStatus.NOT_FOUND);
        }
        //return new ResponseEntity(HttpStatus.OK);
        return new ResponseEntity(customizedResponse, HttpStatus.OK);
    }

    @PostMapping(value = "/categories",consumes = {
            MediaType.APPLICATION_JSON_VALUE
    })
    public ResponseEntity addCategory(@RequestBody Category category)
    {

        var customizedResponse    = new CustomizedResponse("Category created successfully", Collections.singletonList(service.insertIntoCategories(category)));
       // return new ResponseEntity(response, HttpStatus.OK);
        return new ResponseEntity( customizedResponse, HttpStatus.OK);
    }

    @PutMapping(value = "/categories/{id}", consumes = {
            MediaType.APPLICATION_JSON_VALUE
    })
    public ResponseEntity editCategory(@PathVariable("id") String id, @RequestBody Category newCategory)  throws Exception{

        CustomizedResponse customizedResponse = null;
        try {
            customizedResponse = new CustomizedResponse("Category with ID: "+id +" is updated successfully", Collections.singletonList(service.editCategory(id, newCategory)));
        }
        catch(Exception e){
            customizedResponse = new CustomizedResponse(e.getMessage(), null);
            return new ResponseEntity(customizedResponse, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(customizedResponse, HttpStatus.OK);
    }


}

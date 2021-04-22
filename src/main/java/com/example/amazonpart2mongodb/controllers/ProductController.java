package com.example.amazonpart2mongodb.controllers;

import com.example.amazonpart2mongodb.CustomizedResponse;
import com.example.amazonpart2mongodb.models.Product;
import com.example.amazonpart2mongodb.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4000")
@RestController
public class ProductController {

    @Autowired
    private ProductService service;


    @GetMapping("/products")
    public ResponseEntity getAllProducts() {

        var customizedResponse = new CustomizedResponse("A list of products", service.getAllProducts());

        return new ResponseEntity(customizedResponse, HttpStatus.OK);
    }

    @GetMapping("/products/{id}")
    public ResponseEntity getAProduct(@PathVariable("id") String id) throws Exception {

        CustomizedResponse customizedResponse = null;
   try {
    List products = Collections.singletonList(service.getAProduct(id));
    customizedResponse = new CustomizedResponse("Product with id :" + id, products);
   /* if (products.get(0) == Optional.empty()) {
        var customizedResponse = new CustomizedResponse("No product found for Id:" + id, null);
        return new ResponseEntity(customizedResponse, HttpStatus.NOT_FOUND);
    } else {
        var customizedResponse = new CustomizedResponse("Product with id :" + id, products);
        return new ResponseEntity(customizedResponse, HttpStatus.OK);
    }
    */

    }
     catch(Exception e){
         customizedResponse = new CustomizedResponse(e.getMessage(), null);
         return new ResponseEntity(customizedResponse, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(customizedResponse, HttpStatus.OK);
    }


    @DeleteMapping("/products/{id}")
    public ResponseEntity deleteAProduct(@PathVariable("id") String id) throws Exception {
        CustomizedResponse customizedResponse = null;
        try{
            service.deleteAProduct(id);
            customizedResponse = new CustomizedResponse("Product with ID: "+id +" is deleted successfully", null);
         }
        catch(Exception e)
        {
            customizedResponse = new CustomizedResponse(e.getMessage(), null);
            return new ResponseEntity(customizedResponse, HttpStatus.NOT_FOUND);
        }
           //   return new ResponseEntity(HttpStatus.OK);
        return new ResponseEntity(customizedResponse, HttpStatus.OK);
    }

    @PostMapping(value = "/products", consumes = {
            MediaType.APPLICATION_JSON_VALUE
    })
    public ResponseEntity addProducts(@RequestBody Product product) {

        if (product.getCategoryId()==null || product.getCategoryId().equals("")) {
            var customizedResponse = new CustomizedResponse("CategoryID cannot be null",
                    null);

            //return new ResponseEntity( product, HttpStatus.OK);
            return new ResponseEntity( customizedResponse, HttpStatus.BAD_REQUEST);
        }

        var customizedResponse    = new CustomizedResponse("Product created successfully", Collections.singletonList(service.insertIntoProducts(product)));
        return new ResponseEntity( customizedResponse, HttpStatus.OK);
    }
  /*  @PutMapping(value = "/products", consumes = {
            MediaType.APPLICATION_JSON_VALUE
    })
    public void updateProducts(@Valid @RequestBody Product product) {
        service.updateProducts(product);
    }
*/

    //    @GetMapping("/products?categoryId={categoryId}")
    @GetMapping("/productsbycategory/{categoryId}")
    public ResponseEntity getProductsByCategoryId(@PathVariable("categoryId") String categoryId) {

        var customizedResponse = new CustomizedResponse("Product with id :" + categoryId, Collections.singletonList(service.getProductsByCategoryId(categoryId)));

        return new ResponseEntity(customizedResponse, HttpStatus.OK);
    }

    @GetMapping("/bestsellers")
    public ResponseEntity getBestSellerProducts() {

        var customizedResponse = new CustomizedResponse("Best Seller Products:",
                Collections.singletonList(service.getBestSellerProducts()));

        return new ResponseEntity(customizedResponse, HttpStatus.OK);
    }

    @PutMapping(value = "/products/{id}", consumes = {
            MediaType.APPLICATION_JSON_VALUE
    })
    public ResponseEntity editProducts(@PathVariable("id") String id, @RequestBody Product newProduct)  throws Exception{
        //service.editProduct(id,newProduct);
        CustomizedResponse customizedResponse = null;
        try {
       customizedResponse = new CustomizedResponse("Product with ID: "+id +" was updated successfully", Collections.singletonList(service.editProduct(id, newProduct)));
        }
        catch(Exception e){
            customizedResponse = new CustomizedResponse(e.getMessage(), null);
            return new ResponseEntity(customizedResponse, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(customizedResponse, HttpStatus.OK);
    }



    //search method
    @GetMapping("/searchproducts/{searchText}")
    public ResponseEntity searchProductsByInputText(@PathVariable("searchText") String searchText) {

        var customizedResponse = new CustomizedResponse("Products related to your search :" + searchText, Collections.singletonList(service.searchProductsByInputText(searchText)));

        return new ResponseEntity(customizedResponse, HttpStatus.OK);
    }
}

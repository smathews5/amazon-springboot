package com.example.amazonpart2mongodb.services;



import com.example.amazonpart2mongodb.models.Product;
import com.example.amazonpart2mongodb.models.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Service
@Validated
public class ProductService {

    @Autowired
    private ProductRepository respository;


    public List<Product> getAllProducts() {
        //business logic
        return respository.findAll();

    }

    public Product insertIntoProducts(Product product) {
        return respository.insert(product);
    }

    public Optional<Product> getAProduct(String id) throws Exception {

        Optional<Product> product = respository.findById(id);
        if (!product.isPresent()) {
            throw new Exception("Product with " + id + " is not found");
        }
        return product;
    }

    public void deleteAProduct(String id) throws Exception {
        Optional<Product> product = respository.findById(id);
        if (!product.isPresent()) {
            throw new Exception("Product with " + id + " is not found");
        }
        respository.deleteById(id);
    }

    public void updateProducts(@Valid Product product) {

        respository.save(product);
    }

    public List<Product> getProductsByCategoryId(String categoryId) {
        return respository.findByCategoryId(categoryId);
    }

    public List<Product> getBestSellerProducts() {
        return respository.findByIsBestSeller(true);
    }


    public Product editProduct(String id, Product newProductData) throws Exception {
        Optional<Product> product = respository.findById(id);

        if (!product.isPresent()) {
            throw new Exception("Product with " + id + " is not found");
        }
        product.get().setCategoryId(newProductData.getCategoryId());
        product.get().setProductName(newProductData.getProductName());
        product.get().setProductDesc1(newProductData.getProductDesc1());
        product.get().setProductDesc2(newProductData.getProductDesc2());
        product.get().setProductDesc3(newProductData.getProductDesc3());
        product.get().setIsBestSeller(newProductData.getIsBestSeller());
        product.get().setRating(newProductData.getRating());
        product.get().setStock(newProductData.getStock());
        product.get().setProductImageSmall(newProductData.getProductImageSmall());
        product.get().setProductImageBig(newProductData.getProductImageBig());
        product.get().setProductPrice(newProductData.getProductPrice());
        product.get().setQuantity(newProductData.getQuantity());

        Product updatedProduct = respository.save(product.get());
        return updatedProduct;
    }

    public List<Product> searchProductsByInputText(String searchText) {
        return respository.findByProductNameContaining(searchText);
    }

}




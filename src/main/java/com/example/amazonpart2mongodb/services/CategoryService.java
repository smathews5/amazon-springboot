package com.example.amazonpart2mongodb.services;

import com.example.amazonpart2mongodb.models.Category;
import com.example.amazonpart2mongodb.models.CategoryRepository;
import com.example.amazonpart2mongodb.models.Customer;
import com.example.amazonpart2mongodb.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository respository;

    public List<Category> getCategories()
    {
        //business logic
        return respository.findAll();

    }

    public Category insertIntoCategories(Category category)
    {

       return respository.insert(category);
    }

    public Optional<Category> getACategory(String id)
    {
        return respository.findById(id);
    }

    public void deleteACategory(String id) throws Exception
    {
        Optional<Category> category = respository.findById(id);
        if (!category.isPresent()) {
            throw new Exception("Category with " + id + " is not found");
        }
       respository.deleteById(id);
    }

    public Category editCategory (String id, Category newCategoryData) throws Exception {
        Optional<Category> category =  respository.findById(id);

        if(!category.isPresent())
        {
            throw new Exception("Category with "+id+" is not found");
        }
        category.get().setCategoryName(newCategoryData.getCategoryName());
        category.get().setCategoryImage(newCategoryData.getCategoryImage());
        category.get().setisNavbarItem(newCategoryData.getIsNavbarItem());

        Category updatedCategory = respository.save(category.get());
        return updatedCategory;
    }
}

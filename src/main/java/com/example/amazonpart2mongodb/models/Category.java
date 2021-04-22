package com.example.amazonpart2mongodb.models;

import org.springframework.data.annotation.Id;

public class Category {


    @Id
    private String id;
    private String categoryName;
    private String categoryImage;
    private boolean isNavbarItem;

    public Category() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryImage() {
        return categoryImage;
    }

    public void setCategoryImage(String categoryImage) {
        this.categoryImage = categoryImage;
    }

  /*  public boolean isNavbarItem() {
        return isNavbarItem;
    }
*/
    public boolean getIsNavbarItem() {
        return isNavbarItem;
    }


    public void setisNavbarItem(boolean navbarItem) {
        isNavbarItem = navbarItem;
    }

    public Category(String id, String categoryName, String categoryImage, boolean isNavbarItem) {
        this.id = id;
        this.categoryName = categoryName;
        this.categoryImage = categoryImage;
        this.isNavbarItem = isNavbarItem;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id='" + id + '\'' +
                ", categoryName='" + categoryName + '\'' +
                ", categoryImage='" + categoryImage + '\'' +
                ", isNavbarItem=" + isNavbarItem +
                '}';
    }
}

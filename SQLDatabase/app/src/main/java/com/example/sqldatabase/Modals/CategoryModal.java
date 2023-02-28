package com.example.sqldatabase.Modals;

public class CategoryModal {
    String CategoryName;
    byte[] CategoryImage;

    public CategoryModal() {
    }

    public CategoryModal(String categoryName, byte[] categoryImage) {
        CategoryName = categoryName;
        CategoryImage = categoryImage;
    }

    public String getCategoryName() {
        return CategoryName;
    }

    public void setCategoryName(String categoryName) {
        CategoryName = categoryName;
    }

    public byte[] getCategoryImage() {
        return CategoryImage;
    }

    public void setCategoryImage(byte[] categoryImage) {
        CategoryImage = categoryImage;
    }
}

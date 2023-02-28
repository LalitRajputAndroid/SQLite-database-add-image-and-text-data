package com.example.sqldatabase.Modals;

public class ProductModal {

    String ProductName , ProductPrice ,ProductDiscount ,ProductCategory ,ProductDiscription,ProductDis_Price ;
    byte[] ProductImage;
    public ProductModal() {
    }

    public ProductModal(String productName, String productPrice, String productDiscount, String productCategory, String productDiscription, String productDis_Price, byte[] productImage) {
        ProductName = productName;
        ProductPrice = productPrice;
        ProductDiscount = productDiscount;
        ProductCategory = productCategory;
        ProductDiscription = productDiscription;
        ProductDis_Price = productDis_Price;
        ProductImage = productImage;
    }

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String productName) {
        ProductName = productName;
    }

    public String getProductPrice() {
        return ProductPrice;
    }

    public void setProductPrice(String productPrice) {
        ProductPrice = productPrice;
    }

    public String getProductDiscount() {
        return ProductDiscount;
    }

    public void setProductDiscount(String productDiscount) {
        ProductDiscount = productDiscount;
    }

    public String getProductCategory() {
        return ProductCategory;
    }

    public void setProductCategory(String productCategory) {
        ProductCategory = productCategory;
    }

    public String getProductDiscription() {
        return ProductDiscription;
    }

    public void setProductDiscription(String productDiscription) {
        ProductDiscription = productDiscription;
    }

    public String getProductDis_Price() {
        return ProductDis_Price;
    }

    public void setProductDis_Price(String productDis_Price) {
        ProductDis_Price = productDis_Price;
    }

    public byte[] getProductImage() {
        return ProductImage;
    }

    public void setProductImage(byte[] productImage) {
        ProductImage = productImage;
    }
}

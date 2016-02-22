package com.example.dhruva.fashonation;

/**
 * Created by Dhruva on 18-02-2016.
 */
public class ModelProducts
{
    private String productName;
    private String productDescription;
    private int productPrice;

    public ModelProducts(String productName,String productDescription, int productPrice)
    {
        this.productName = productName;
        this.productDescription = productDescription;
        this.productPrice = productPrice;
    }

    public String getProductName()
    {
        return productName;
    }

    public String getProductDescription()
    {
        return productDescription;
    }

    public int getProductPrice()
    {
        return productPrice;
    }
}

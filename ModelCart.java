package com.example.dhruva.fashonation;

import java.util.ArrayList;

/**
 * Created by Dhruva on 18-02-2016.
 */
public class ModelCart
{
    private ArrayList<ModelProducts> cartProducts = new ArrayList<ModelProducts>();

    public ModelProducts getProducts(int position)
    {
        return cartProducts.get(position);

    }

    public void setProducts(ModelProducts Products)
    {
        cartProducts.add(Products);

    }

    public int getCartSize()
    {
        return cartProducts.size();
    }

    public boolean CheckProductInCart(ModelProducts aproducts)
    {
        return cartProducts.contains(aproducts);
    }
}

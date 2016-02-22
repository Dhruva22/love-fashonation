package com.example.dhruva.fashonation;

import android.app.Application;

import java.util.ArrayList;

/**
 * Created by Dhruva on 18-02-2016.
 */
public class Controller extends Application
{
    private ArrayList<ModelProducts> myProducts = new ArrayList<ModelProducts>();

    private ModelCart myCart = new ModelCart();

    public ModelProducts getProducts(int pPosition)
    {
        return myProducts.get(pPosition);

    }

    public void setProducts(ModelProducts products)
    {
        myProducts.add(products);

    }

    public ModelCart getCart()
    {
        return myCart;
    }

    public int getProductsArraylistSize()
    {
        return myProducts.size();
    }

}

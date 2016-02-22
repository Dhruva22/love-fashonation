package com.example.dhruva.fashonation;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Dhruva on 18-02-2016.
 */
public class Screen2 extends Activity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.screen2);

        TextView showCartContent = (TextView)findViewById(R.id.showcart);

        final Button btn3 = (Button)findViewById(R.id.third);
        final Controller ct = (Controller)getApplicationContext();
        final int cartSize = ct.getCart().getCartSize() ;

        String show = "";

        for(int i=0;i<cartSize;i++)
        {
            String pname =  ct.getCart().getProducts(i).getProductName();
            int pprice =  ct.getCart().getProducts(i).getProductPrice();
            String pdesc =  ct.getCart().getProducts(i).getProductDescription();

            show += ("Product Name:" + pname + " Product Description:" + pdesc + " Product Price:" + pprice+" ");
        }

        showCartContent.setText(show);
    }
}

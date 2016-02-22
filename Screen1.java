package com.example.dhruva.fashonation;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Dhruva on 18-02-2016.
 */
public class Screen1 extends Activity
{
    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        setContentView(R.layout.screen1);
        super.onCreate(savedInstanceState);

        TextView showCartContent = (TextView)findViewById(R.id.showcart);

        final Button btn3 = (Button)findViewById(R.id.third);
        final Controller ct = (Controller)getApplicationContext();
        final int cartSize = ct.getCart().getCartSize() ;

        String show = "";

        if(cartSize > 0)
        {
            for(int i=0;i<cartSize;i++)
            {
                String pname =  ct.getCart().getProducts(i).getProductName();
                String pdesc =  ct.getCart().getProducts(i).getProductDescription();
                int pprice =  ct.getCart().getProducts(i).getProductPrice();

                show += ("Product Name:" + pname + " Product Description:" + pdesc + " Product Price:" + pprice+"");
            }
        }
        else
        {
            show = "There is no item in The cart.";
            showCartContent.setText(show);

            btn3.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    if(cartSize>0)
                    {
                        Intent i = new Intent(getBaseContext(),Screen2.class);
                        startActivity(i);
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(),"Shopping cart is empty",Toast.LENGTH_LONG).show();
                    }
                }
            });

        }



    }
}

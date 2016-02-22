package com.example.dhruva.fashonation;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dhruva.fashonation.Controller;

public class CartActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        final LinearLayout layout = (LinearLayout)findViewById(R.id.linear_main);
        final Button btn = (Button)findViewById(R.id.second);
        final Controller ct = (Controller)getApplicationContext();

        ModelProducts products = null;

        for(int i=1 ; i<7 ; i++)
        {
            int price = 15+i;
            products = new ModelProducts("Product Item"+i,"Description"+i,price);
            ct.setProducts(products);
        }

        int productssize = ct.getProductsArraylistSize();
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(AbsListView.LayoutParams.WRAP_CONTENT, AbsListView.LayoutParams.WRAP_CONTENT);

        for(int j=0;j<productssize;j++)
        {
            String pname = ct.getProducts(j).getProductName();
            int price = ct.getProducts(j).getProductPrice();

            LinearLayout la = new LinearLayout(this);

            la.setOrientation(LinearLayout.HORIZONTAL);

            TextView tv = new TextView(this);
            tv.setText(" " + pname + " ");
            la.addView(tv);

            TextView tv1 = new TextView(this);
            tv.setText("$" + price + " ");
            la.addView(tv1);

            final Button btn1 = new Button(this);
            btn1.setId(j + 1);
            btn1.setText("Add to Cart");

            btn1.setLayoutParams(params);

            final int index = j;

            btn1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.i("Tag", "Index:" + index);

                    ModelProducts productsObject = ct.getProducts(index);

                    if(!ct.getCart().CheckProductInCart(productsObject))
                    {
                        btn1.setText("Item Added");

                        ct.getCart().setProducts(productsObject);

                        Toast.makeText(getApplicationContext(), "New CartSize:" + ct.getCart().getCartSize(), Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(),"Products"+(index+1)+"Already added",Toast.LENGTH_LONG).show();
                    }
                }
            });

            la.addView(btn1);
            layout.addView(la);

            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent in = new Intent(getBaseContext(),Screen2.class);
                    startActivity(in);
                }
            });
        }

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

}

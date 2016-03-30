package com.example.dhruva.fashonation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.RatingBar;
import android.widget.TextView;

public class Fashonation_Product_Details_Page1 extends AppCompatActivity
{
    Toolbar toolbar;
    private RatingBar ratingBar;
    private String txtRatingValue;
    TextView product_name, price, offer, description;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fashonation__product__details__page1);

        addListenerOnRatingBar();
        //addListenerOnButton();

        product_name = (TextView) findViewById(R.id.product_name);
        price = (TextView) findViewById(R.id.price);
        offer = (TextView) findViewById(R.id.offer_available);
        description = (TextView) findViewById(R.id.description);
    }

    private void addListenerOnRatingBar() {
        ratingBar=(RatingBar)findViewById(R.id.ratingBar);


        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                txtRatingValue =(String.valueOf(rating));
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement


        return super.onOptionsItemSelected(item);
    }
}

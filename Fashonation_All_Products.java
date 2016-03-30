package com.example.dhruva.fashonation;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Fashonation_All_Products extends AppCompatActivity
{

    private static RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private static RecyclerView recyclerView;
    private static ArrayList<Fashonation_DataModel> data;
    static View.OnClickListener myOnClickListener;
    private static ArrayList<Integer> removedItems;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fashonation__all__products);

        myOnClickListener = new MyOnClickListener(this);

        recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        recyclerView.setHasFixedSize(true);

        layoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        data = new ArrayList<Fashonation_DataModel>();
        for (int i = 0; i < Fashonation_MyData.nameArray.length; i++)
        {
            data.add(new Fashonation_DataModel(
                    Fashonation_MyData.nameArray[i],
                    Fashonation_MyData.versionArray[i],
                    Fashonation_MyData.id_[i],
                    Fashonation_MyData.drawableArray[i]
            ));
        }

        removedItems = new ArrayList<Integer>();

        adapter = new Fashonation_CustomAdapter(data);
        recyclerView.setAdapter(adapter);
    }


    private static class MyOnClickListener implements View.OnClickListener
    {

        private final Context context;

        private MyOnClickListener(Context context)
        {
            this.context = context;
        }

        @Override
        public void onClick(View v)
        {
            removeItem(v);
        }

        private void removeItem(View v)
        {
            int selectedItemPosition = recyclerView.getChildPosition(v);
            RecyclerView.ViewHolder viewHolder
                    = recyclerView.findViewHolderForPosition(selectedItemPosition);
            TextView textViewName
                    = (TextView) viewHolder.itemView.findViewById(R.id.textViewName);
            String selectedName = (String) textViewName.getText();
            int selectedItemId = -1;
            for (int i = 0; i < Fashonation_MyData.nameArray.length; i++)
            {
                if (selectedName.equals(Fashonation_MyData.nameArray[i]))
                {
                    selectedItemId = Fashonation_MyData.id_[i];
                }
            }
            removedItems.add(selectedItemId);
            data.remove(selectedItemPosition);
            adapter.notifyItemRemoved(selectedItemPosition);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        super.onOptionsItemSelected(item);
        //if (item.getItemId() == R.id.add_item)
        {
            //check if any items to add
            if (removedItems.size() != 0)
            {
                addRemovedItemToList();
            }
            else
            {
                Toast.makeText(this, "Nothing to add", Toast.LENGTH_SHORT).show();
            }
        }
        return true;
    }



    private void addRemovedItemToList() {
        int addItemAtListPosition = 3;
        data.add(addItemAtListPosition, new Fashonation_DataModel(
                Fashonation_MyData.nameArray[removedItems.get(0)],
                Fashonation_MyData.versionArray[removedItems.get(0)],
                Fashonation_MyData.id_[removedItems.get(0)],
                Fashonation_MyData.drawableArray[removedItems.get(0)]
        ));
        adapter.notifyItemInserted(addItemAtListPosition);
        removedItems.remove(0);
    }

}

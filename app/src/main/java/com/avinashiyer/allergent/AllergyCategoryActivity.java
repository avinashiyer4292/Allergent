package com.avinashiyer.allergent;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.avinashiyer.allergent.adapters.AllergyRecyclerViewAdapter;
import com.avinashiyer.allergent.adapters.AllergyViewHolder;
import com.avinashiyer.allergent.utils.AllergyObject;
import com.avinashiyer.allergent.utils.Constants;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AllergyCategoryActivity extends AppCompatActivity implements AllergyRecyclerViewAdapter.RecyclerItemClickListener{
private GridLayoutManager layoutManager;

    SharedPreferences prefs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_allergy_category);
        layoutManager = new GridLayoutManager(AllergyCategoryActivity.this, 2);
        RecyclerView rView = (RecyclerView)findViewById(R.id.recyclerView);
        rView.setHasFixedSize(true);
        rView.setLayoutManager(layoutManager);

        List<AllergyObject> allergies = getAllItemsList();
        AllergyRecyclerViewAdapter rcAdapter = new AllergyRecyclerViewAdapter(AllergyCategoryActivity.this, allergies,this   );
        rView.setAdapter(rcAdapter);

    }

    @Override
    public void onRecyclerItemClick(final AllergyObject item) {
        new AlertDialog.Builder(AllergyCategoryActivity.this)
                .setTitle("Add allergy")
                .setMessage("Add allergy to list?")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // continue with delete
                        prefs = getSharedPreferences(Constants.SHARED_PREFS, Context.MODE_PRIVATE);
                        Set<String> set = prefs.getStringSet("key", new HashSet<String>());

                        Log.d("on clicking yes dialog",item.getName());
                        set.add(item.getName());
                        SharedPreferences.Editor editor = prefs.edit();
                        editor.putStringSet("key",set);
                        editor.commit();

                    }
                })
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // do nothing
                    }
                })

                .show();
    }

    private List<AllergyObject> getAllItemsList(){
        List<AllergyObject> list = new ArrayList<>();
        list.add(new AllergyObject("Dairy Products",R.drawable.dairy));
        list.add(new AllergyObject("Meat Products",R.drawable.meat));
        list.add(new AllergyObject("Beverages",R.drawable.beverages));
        list.add(new AllergyObject("Condiments",R.drawable.spices));
        list.add(new AllergyObject("Egg Products",R.drawable.eggs));
        list.add(new AllergyObject("Nuts",R.drawable.nuts));
        list.add(new AllergyObject("Gluten",R.drawable.gluten));
        list.add(new AllergyObject("Wheat",R.drawable.wheat));
        list.add(new AllergyObject("Fish",R.drawable.fish));
        list.add(new AllergyObject("Alcohol",R.drawable.alcohol));
        list.add(new AllergyObject("Soy",R.drawable.soy));

        return list;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.profile:
                startActivity(new Intent(this, ProfileActivity.class));
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}

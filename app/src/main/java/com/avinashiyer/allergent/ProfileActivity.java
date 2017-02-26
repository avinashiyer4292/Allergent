package com.avinashiyer.allergent;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.avinashiyer.allergent.adapters.PagerAdapter;
import com.avinashiyer.allergent.utils.Constants;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileActivity extends AppCompatActivity{
    SharedPreferences prefs;
    CircleImageView imgView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        imgView = (CircleImageView)findViewById(R.id.profile_image);
        prefs = getSharedPreferences(Constants.SHARED_PREFS, Context.MODE_PRIVATE);
        String id = prefs.getString("id","Avinash");
        Log.d("id",id);
        String url = "https://graph.facebook.com/" + id + "/picture?type=large";
        Log.d("url",url);
            //URL imageURL = new URL("https://graph.facebook.com/" + id + "/picture?type=large");
            new RetrieveFeedTask().execute(url);


        final TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText("My Allergies"));
        tabLayout.addTab(tabLayout.newTab().setText("Record picture"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        final PagerAdapter adapter = new PagerAdapter
                (getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
                //tabLayout.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                //tabLayout.setTe
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }

    class RetrieveFeedTask extends AsyncTask<String, Void, Bitmap> {

        Bitmap bitmap=null;
        protected Bitmap doInBackground(String... urls) {
            Log.d("in background",urls[0]);
            try {
                URL imageURL = new URL(urls[0]);
                bitmap = BitmapFactory.decodeStream(imageURL.openConnection().getInputStream());


            } catch (Exception e) {

            }
            return bitmap;
        }

        protected void onPostExecute(Bitmap bitmap) {
            // TODO: check this.exception
            // TODO: do something with the feed
            imgView.setImageBitmap(bitmap);


        }
    }




}

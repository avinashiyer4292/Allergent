package com.avinashiyer.allergent;

import android.app.Application;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

/**
 * Created by avinashiyer on 2/25/17.
 */

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/gotham-book.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );
    }


}

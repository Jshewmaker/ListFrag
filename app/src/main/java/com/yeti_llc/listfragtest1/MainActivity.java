package com.yeti_llc.listfragtest1;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void saveStats(View view){
        SharedPreferences sharedPref = getSharedPreferences("classStats", Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedPref.edit();



    }
}

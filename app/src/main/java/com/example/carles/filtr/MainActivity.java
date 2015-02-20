package com.example.carles.filtr;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends ActionBarActivity {
    public static final String EXTRA_MESSAGE = "com.example.carles.filtr.MESSAGE";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        return false;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return false;
    }

    public void swapcamera(View view) {
        Intent intent = new Intent(MainActivity.this, activity_camera.class);
        startActivity(intent);
    }

    public void swapfile(View view) {
        Intent intent = new Intent(MainActivity.this, activity_arxiu.class);
        startActivity(intent);
    }
}

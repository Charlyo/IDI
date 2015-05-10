package com.example.carles.filtr;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;


public class activity_validate extends ActionBarActivity {
    String path;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_validate);
        Bundle bundle = getIntent().getExtras();
        path = bundle.getString("imatge_guardada");
        Bitmap thumbnail = (BitmapFactory.decodeFile(path));
        ImageView imageView = (ImageView) findViewById(R.id.viewImage);
        imageView.setImageBitmap(thumbnail);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_activity_validate, menu);
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

    public void swapfilter(View view) {
        Intent intent = new Intent(activity_validate.this, activity_filtres.class);
        intent.putExtra("path", path);
        startActivity(intent);
    }

    public void back_to_main(View view) {
        finish();
    }
}

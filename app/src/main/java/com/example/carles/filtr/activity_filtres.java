package com.example.carles.filtr;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.renderscript.Allocation;
import android.renderscript.Allocation.MipmapControl;
import android.renderscript.Element;
import android.renderscript.Matrix4f;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicBlur;
import android.renderscript.ScriptIntrinsicColorMatrix;
import android.renderscript.ScriptIntrinsicConvolve3x3;
import android.renderscript.ScriptIntrinsicConvolve5x5;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.io.ByteArrayOutputStream;
import java.util.Random;

public class activity_filtres extends ActionBarActivity {

    Allocation allin;
    Allocation allout;
    Bitmap anterior, pre_filtre, actual;
    Boolean butons_actius;
    Boolean butons_aplicar_actius;
    Boolean enrere_actiu;
    Boolean primer_filtre;
    float[] filtre;
    float[] filtre5;
    ImageView imageView;
    LinearLayout mlay2;
    Matrix4f kernelcolor;
    Random rand;
    RenderScript rs;
    ScriptIntrinsicConvolve3x3 filtre3;
    ScriptIntrinsicConvolve5x5 filtre5x5;
    ScriptIntrinsicColorMatrix filtrecolor;
    ScriptIntrinsicBlur filtreblur;
    String path;

    public void invert(View view) {
        rs = RenderScript.create(this);
        kernelcolor.set(0, 0, -1);
        kernelcolor.set(0, 1, 0);
        kernelcolor.set(0, 2, 0);
        kernelcolor.set(0, 3, 0);
        kernelcolor.set(1, 0, 0);
        kernelcolor.set(1, 1, -1);
        kernelcolor.set(1, 2, 0);
        kernelcolor.set(1, 3, 0);
        kernelcolor.set(2, 0, 0);
        kernelcolor.set(2, 1, 0);
        kernelcolor.set(2, 2, -1);
        kernelcolor.set(2, 3, 0);
        kernelcolor.set(3, 0, 1);
        kernelcolor.set(3, 1, 1);
        kernelcolor.set(3, 2, 1);
        kernelcolor.set(3, 3, 0);
        allin = Allocation.createFromBitmap(rs, pre_filtre,
                MipmapControl.MIPMAP_NONE,
                Allocation.USAGE_SCRIPT);
        allout = Allocation.createTyped(rs, allin.getType());
        filtrecolor = ScriptIntrinsicColorMatrix.create(rs, Element.U8_4(rs));
        filtrecolor.setColorMatrix(kernelcolor);
        filtrecolor.forEach(allin, allout);
        allout.copyTo(actual);
        imageView.setImageBitmap(actual);
        rs.destroy();
        afegir_botons_aplicar_filtre(view);
    }

    public void greyscale(View view) {
        rs = RenderScript.create(this);
        kernelcolor.set(0, 0, 0.333f);
        kernelcolor.set(0, 1, 0.333f);
        kernelcolor.set(0, 2, 0.333f);
        kernelcolor.set(1, 0, 0.333f);
        kernelcolor.set(1, 1, 0.333f);
        kernelcolor.set(1, 2, 0.333f);
        kernelcolor.set(2, 0, 0.333f);
        kernelcolor.set(2, 1, 0.333f);
        kernelcolor.set(2, 2, 0.333f);
        kernelcolor.set(3, 0, 0);
        kernelcolor.set(3, 1, 0);
        kernelcolor.set(3, 2, 0);
        kernelcolor.set(3, 3, 1);
        allin = Allocation.createFromBitmap(rs, pre_filtre,
                MipmapControl.MIPMAP_NONE,
                Allocation.USAGE_SCRIPT);
        allout = Allocation.createTyped(rs, allin.getType());
        filtrecolor = ScriptIntrinsicColorMatrix.create(rs, Element.U8_4(rs));
        filtrecolor.setColorMatrix(kernelcolor);
        filtrecolor.forEach(allin, allout);
        allout.copyTo(actual);
        imageView.setImageBitmap(actual);
        rs.destroy();
        afegir_botons_aplicar_filtre(view);
    }

    public void polarid(View view) {
        rs = RenderScript.create(this);
        kernelcolor.set(0, 0, 1.438f);
        kernelcolor.set(0, 1, -0.062f);
        kernelcolor.set(0, 2, -0.062f);
        kernelcolor.set(1, 0, -0.122f);
        kernelcolor.set(1, 1, 1.378f);
        kernelcolor.set(1, 2, -0.122f);
        kernelcolor.set(2, 0, -0.016f);
        kernelcolor.set(2, 1, -0.016f);
        kernelcolor.set(2, 2, 1.483f);
        kernelcolor.set(3, 0, -0.03f);
        kernelcolor.set(3, 1, 0.05f);
        kernelcolor.set(3, 2, -0.02f);
        kernelcolor.set(3, 3, 0);
        allin = Allocation.createFromBitmap(rs, pre_filtre,
                MipmapControl.MIPMAP_NONE,
                Allocation.USAGE_SCRIPT);
        allout = Allocation.createTyped(rs, allin.getType());
        filtrecolor = ScriptIntrinsicColorMatrix.create(rs, Element.U8_4(rs));
        filtrecolor.setColorMatrix(kernelcolor);
        filtrecolor.forEach(allin, allout);
        allout.copyTo(actual);
        imageView.setImageBitmap(actual);
        rs.destroy();
        afegir_botons_aplicar_filtre(view);
    }

    public void blackandwhite(View view) {
        rs = RenderScript.create(this);
        kernelcolor.set(0, 0, 1.5f);
        kernelcolor.set(0, 1, 1.5f);
        kernelcolor.set(0, 2, 1.5f);
        kernelcolor.set(1, 0, 1.5f);
        kernelcolor.set(1, 1, 1.5f);
        kernelcolor.set(1, 2, 1.5f);
        kernelcolor.set(2, 0, 1.5f);
        kernelcolor.set(2, 1, 1.5f);
        kernelcolor.set(2, 2, 1.5f);
        kernelcolor.set(3, 0, -1);
        kernelcolor.set(3, 1, -1);
        kernelcolor.set(3, 2, -1);
        kernelcolor.set(3, 3, 0);
        allin = Allocation.createFromBitmap(rs, pre_filtre,
                MipmapControl.MIPMAP_NONE,
                Allocation.USAGE_SCRIPT);
        allout = Allocation.createTyped(rs, allin.getType());
        filtrecolor = ScriptIntrinsicColorMatrix.create(rs, Element.U8_4(rs));
        filtrecolor.setColorMatrix(kernelcolor);
        filtrecolor.forEach(allin, allout);
        allout.copyTo(actual);
        imageView.setImageBitmap(actual);
        rs.destroy();
        afegir_botons_aplicar_filtre(view);
    }

    public void original(View view) {
        actual = BitmapFactory.decodeFile(path);
        imageView.setImageBitmap(actual);
        afegir_botons_aplicar_filtre(view);
    }

    public void sepia(View view) {
        rs = RenderScript.create(this);
        kernelcolor.set(0, 0, 0.393f);
        kernelcolor.set(0, 1, 0.349f);
        kernelcolor.set(0, 2, 0.272f);
        kernelcolor.set(1, 0, 0.769f);
        kernelcolor.set(1, 1, 0.686f);
        kernelcolor.set(1, 2, 0.534f);
        kernelcolor.set(2, 0, 0.189f);
        kernelcolor.set(2, 1, 0.168f);
        kernelcolor.set(2, 2, 0.131f);
        kernelcolor.set(3, 0, 0);
        kernelcolor.set(3, 1, 0);
        kernelcolor.set(3, 2, 0);
        kernelcolor.set(3, 3, 1);
        allin = Allocation.createFromBitmap(rs, pre_filtre,
                MipmapControl.MIPMAP_NONE,
                Allocation.USAGE_SCRIPT);
        allout = Allocation.createTyped(rs, allin.getType());
        filtrecolor = ScriptIntrinsicColorMatrix.create(rs, Element.U8_4(rs));
        filtrecolor.setColorMatrix(kernelcolor);
        filtrecolor.forEach(allin, allout);
        allout.copyTo(actual);
        imageView.setImageBitmap(actual);
        rs.destroy();
        afegir_botons_aplicar_filtre(view);
    }

    public void desapareixbutons(View view) {
        LinearLayout mlay = (LinearLayout) findViewById(R.id.linearbutons);
        if (butons_actius) {
            mlay.setVisibility(View.GONE);
            butons_actius = false;
            mlay.setClickable(false);
            if (butons_aplicar_actius) {
                treure_botons_aplicar_filtre(view);
                butons_aplicar_actius = true;
            }
        } else {
            mlay.setVisibility(View.VISIBLE);
            butons_actius = true;
            mlay.setClickable(true);
            if (butons_aplicar_actius) afegir_botons_aplicar_filtre(view);
        }
    }

    public void afegir_botons_aplicar_filtre(View view) {
        mlay2.setVisibility(View.VISIBLE);
        butons_aplicar_actius = true;
        mlay2.setClickable(true);
        enrere_actiu = false;
    }

    public void treure_botons_aplicar_filtre(View view) {
        mlay2.setVisibility(View.GONE);
        butons_aplicar_actius = false;
        mlay2.setClickable(false);
        if (!primer_filtre) {
            anterior = pre_filtre.copy(pre_filtre.getConfig(), true);
        }
        pre_filtre = actual.copy(actual.getConfig(), true);
        enrere_actiu = true;
        primer_filtre = false;
    }

    public void desfes_filtre(View view) {
        actual = pre_filtre.copy(pre_filtre.getConfig(), true);
        imageView.setImageBitmap(actual);
        mlay2.setVisibility(View.GONE);
        butons_aplicar_actius = false;
        mlay2.setClickable(false);
        enrere_actiu = true;
    }

    public void enrere_filtre() {
        if (enrere_actiu) {
            mlay2.setVisibility(View.GONE);
            butons_aplicar_actius = false;
            mlay2.setClickable(false);
            actual = anterior.copy(anterior.getConfig(), true);
            imageView.setImageBitmap(actual);
            pre_filtre = anterior.copy(anterior.getConfig(), true);
        }
    }

    public void guardar_bitmap() {
        ByteArrayOutputStream bitmap_guardar = new ByteArrayOutputStream();
        actual.compress(Bitmap.CompressFormat.JPEG, 85, bitmap_guardar);
        byte[] arraybyte = bitmap_guardar.toByteArray();
        Intent guardar = new Intent(activity_filtres.this, activity_guardar.class);
        guardar.putExtra("imatge", arraybyte);
        startActivity(guardar);
    }

    public void Blur(View view) {
        rs = RenderScript.create(this);
        allin = Allocation.createFromBitmap(rs, pre_filtre,
                MipmapControl.MIPMAP_NONE,
                Allocation.USAGE_SCRIPT);
        allout = Allocation.createTyped(rs, allin.getType());
        filtreblur = ScriptIntrinsicBlur.create(rs, Element.U8_4(rs));
        filtreblur.setInput(allin);
        filtreblur.setRadius(11);
        filtreblur.forEach(allout);
        allout.copyTo(actual);
        imageView.setImageBitmap(actual);
        afegir_botons_aplicar_filtre(view);
    }

    public void BGR(View view) {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                kernelcolor.set(i, j, 0);
            }
        }
        kernelcolor.set(0, 2, 1);
        kernelcolor.set(1, 1, 1);
        kernelcolor.set(2, 0, 1);
        rs = RenderScript.create(this);
        allin = Allocation.createFromBitmap(rs, pre_filtre,
                MipmapControl.MIPMAP_NONE,
                Allocation.USAGE_SCRIPT);
        allout = Allocation.createTyped(rs, allin.getType());
        filtrecolor = ScriptIntrinsicColorMatrix.create(rs, Element.U8_4(rs));
        filtrecolor.setColorMatrix(kernelcolor);
        filtrecolor.forEach(allin, allout);
        allout.copyTo(actual);
        imageView.setImageBitmap(actual);
        rs.destroy();
        afegir_botons_aplicar_filtre(view);
    }

    public void emboss(View view) {
        filtre[0] = 2;
        filtre[1] = 0;
        filtre[2] = 0;
        filtre[3] = 0;
        filtre[4] = -1;
        filtre[5] = 0;
        filtre[6] = 0;
        filtre[7] = 0;
        filtre[8] = -1;
        filtres3x3(view);
        afegir_botons_aplicar_filtre(view);
    }

    public void sharpen(View view) {
        filtre[0] = 1;
        filtre[1] = 1;
        filtre[2] = 1;
        filtre[3] = 1;
        filtre[4] = -7;
        filtre[5] = 1;
        filtre[6] = 1;
        filtre[7] = 1;
        filtre[8] = 1;
        filtres3x3(view);
        afegir_botons_aplicar_filtre(view);
    }

    public void meanfilter(View view) {
        for (int i = 0; i < 25; i++) {
            filtre5[i] = 1 / 25f;
        }
        filtres5x5(view);
        afegir_botons_aplicar_filtre(view);
    }

    public void edgedetection(View view) {
        for (int i = 0; i < 25; i++) {
            filtre5[i] = 0;
        }
        filtre5[10] = filtre5[11] = -1;
        filtre5[12] = 2;
        filtres5x5(view);
        afegir_botons_aplicar_filtre(view);
    }

    public void random(View view) {
        for (int i = 0; i < 24; i++) {
            filtre5[i] = rand.nextFloat() * 4 - 2;
        }
        filtres5x5(view);
        afegir_botons_aplicar_filtre(view);
    }

    public void filtres3x3(View view) {
        rs = RenderScript.create(getApplicationContext());
        filtre3 = ScriptIntrinsicConvolve3x3.create(rs, Element.U8_4(rs));
        filtre3.setCoefficients(filtre);
        allin = Allocation.createFromBitmap(rs, pre_filtre,
                MipmapControl.MIPMAP_NONE,
                Allocation.USAGE_SCRIPT);
        allout = Allocation.createTyped(rs, allin.getType());
        filtre3.setInput(allin);
        filtre3.forEach(allout);
        allout.copyTo(actual);
        imageView.setImageBitmap(actual);
        rs.destroy();
    }

    public void filtres5x5(View view) {
        rs = RenderScript.create(getApplicationContext());
        filtre5x5 = ScriptIntrinsicConvolve5x5.create(rs, Element.U8_4(rs));
        filtre5x5.setCoefficients(filtre5);
        allin = Allocation.createFromBitmap(rs, pre_filtre,
                MipmapControl.MIPMAP_NONE,
                Allocation.USAGE_SCRIPT);
        allout = Allocation.createTyped(rs, allin.getType());
        filtre5x5.setInput(allin);
        filtre5x5.forEach(allout);
        allout.copyTo(actual);
        imageView.setImageBitmap(actual);
        rs.destroy();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filtres);
        if (savedInstanceState != null) {
            //Bundle bundle = getIntent().getExtras();
            //path = bundle.getString("path");
            anterior = savedInstanceState.getParcelable("anterior");
            pre_filtre = savedInstanceState.getParcelable("pre_filtre");
            actual = savedInstanceState.getParcelable("actual");
            filtre = new float[9];
            filtre5 = new float[25];
            rand = new Random();
            filtre = savedInstanceState.getFloatArray("filtre");
            filtre5 = savedInstanceState.getFloatArray("filtre5");
            enrere_actiu = savedInstanceState.getBoolean("enrere_actiu");
            primer_filtre = savedInstanceState.getBoolean("primer_filtre");
            butons_actius = savedInstanceState.getBoolean("butons_actius");
            butons_aplicar_actius = savedInstanceState.getBoolean("butons_aplica_actius");
            float[] coloraux = savedInstanceState.getFloatArray("kernelcolor");
            kernelcolor = new Matrix4f();
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    kernelcolor.set(j, i, coloraux[4 * i + j]);
                }
            }
            imageView.setImageBitmap(actual);

        } else {
            Bundle bundle = getIntent().getExtras();
            butons_actius = true;
            butons_aplicar_actius = false;
            filtre = new float[9];
            filtre5 = new float[25];
            mlay2 = (LinearLayout) findViewById(R.id.linearLayoutaplicarfiltre);
            kernelcolor = new Matrix4f();
            rand = new Random();
            path = bundle.getString("path");
            pre_filtre = (BitmapFactory.decodeFile(path));
            anterior = (BitmapFactory.decodeFile(path));
            actual = (BitmapFactory.decodeFile(path));
            imageView = (ImageView) findViewById(R.id.photo_back);
            imageView.setImageBitmap(pre_filtre);
            mlay2.setVisibility(View.GONE);
            mlay2.setClickable(false);
            enrere_actiu = true;
            primer_filtre = true;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_activity_filtres, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        switch (id) {
            case R.id.desfer:
                enrere_filtre();
                return true;
            case R.id.boto_guardar:
                guardar_bitmap();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putFloatArray("kernelcolor", kernelcolor.getArray());
        outState.putFloatArray("filtre", filtre);
        outState.putFloatArray("filtre5", filtre5);
        outState.putBoolean("enrere_actiu", enrere_actiu);
        outState.putBoolean("primer_filtre", primer_filtre);
        outState.putBoolean("butons_actius", butons_actius);
        outState.putBoolean("butons_aplicar_actius", butons_aplicar_actius);
        outState.putParcelable("actual", actual);
        outState.putParcelable("pre_filtre", pre_filtre);
        outState.putParcelable("anterior", anterior);
    }

}

package com.example.carles.filtr;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.renderscript.Allocation;
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

import java.util.Random;

import static android.renderscript.Allocation.createFromBitmap;


public class activity_filtres extends ActionBarActivity {

    Allocation allin;
    Allocation allout;
    Bitmap anterior, copia, out;
    Boolean butons_actius = true;
    Boolean butons_aplicar_actius = false;
    float[] filtre = new float[9];
    float[] filtre5 = new float[25];
    ImageView imageView;
    LinearLayout mlay2 = (LinearLayout) findViewById(R.id.linearLayoutaplicarfiltre);
    Matrix4f kernelcolor = new Matrix4f();
    Random rand = new Random();
    RenderScript rs;
    ScriptIntrinsicConvolve3x3 filtre3;
    ScriptIntrinsicConvolve5x5 filtre5x5;
    ScriptIntrinsicColorMatrix filtrecolor;
    ScriptIntrinsicBlur filtreblur;
    String path;

    public void invert(View view) {
        rs = RenderScript.create(this);
        out = copia.copy(copia.getConfig(), true);
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
        allin = Allocation.createFromBitmap(rs, copia,
                Allocation.MipmapControl.MIPMAP_NONE,
                Allocation.USAGE_SCRIPT);
        allout = Allocation.createTyped(rs, allin.getType());
        filtrecolor = ScriptIntrinsicColorMatrix.create(rs, Element.U8_4(rs));
        filtrecolor.setColorMatrix(kernelcolor);
        filtrecolor.forEach(allin, allout);
        allout.copyTo(out);
        imageView.setImageBitmap(out);
        rs.destroy();
        afegir_botons_aplicar_filtre(view);
    }

    public void greyscale(View view) {

        rs = RenderScript.create(this);
        out = copia.copy(copia.getConfig(), true);
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
        allin = Allocation.createFromBitmap(rs, copia,
                Allocation.MipmapControl.MIPMAP_NONE,
                Allocation.USAGE_SCRIPT);
        allout = Allocation.createTyped(rs, allin.getType());
        filtrecolor = ScriptIntrinsicColorMatrix.create(rs, Element.U8_4(rs));
        filtrecolor.setColorMatrix(kernelcolor);
        filtrecolor.forEach(allin, allout);
        allout.copyTo(out);
        imageView.setImageBitmap(out);
        rs.destroy();
        afegir_botons_aplicar_filtre(view);
    }

    public void polarid(View view) {
        rs = RenderScript.create(this);
        out = copia.copy(copia.getConfig(), true);
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
        allin = Allocation.createFromBitmap(rs, copia,
                Allocation.MipmapControl.MIPMAP_NONE,
                Allocation.USAGE_SCRIPT);
        allout = Allocation.createTyped(rs, allin.getType());
        filtrecolor = ScriptIntrinsicColorMatrix.create(rs, Element.U8_4(rs));
        filtrecolor.setColorMatrix(kernelcolor);
        filtrecolor.forEach(allin, allout);
        allout.copyTo(out);
        imageView.setImageBitmap(out);
        rs.destroy();
        afegir_botons_aplicar_filtre(view);
    }

    public void blackandwhite(View view) {
        rs = RenderScript.create(this);
        out = copia.copy(copia.getConfig(), true);
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
        allin = Allocation.createFromBitmap(rs, copia,
                Allocation.MipmapControl.MIPMAP_NONE,
                Allocation.USAGE_SCRIPT);
        allout = Allocation.createTyped(rs, allin.getType());
        filtrecolor = ScriptIntrinsicColorMatrix.create(rs, Element.U8_4(rs));
        filtrecolor.setColorMatrix(kernelcolor);
        filtrecolor.forEach(allin, allout);
        allout.copyTo(out);
        imageView.setImageBitmap(out);
        rs.destroy();
        afegir_botons_aplicar_filtre(view);
    }

    public void original(View view) {
        out = (BitmapFactory.decodeFile(path));
        imageView.setImageBitmap(out);
        afegir_botons_aplicar_filtre(view);
    }

    public void sepia(View view) {
        rs = RenderScript.create(this);
        out = copia.copy(copia.getConfig(), true);
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
        allin = Allocation.createFromBitmap(rs, copia,
                Allocation.MipmapControl.MIPMAP_NONE,
                Allocation.USAGE_SCRIPT);
        allout = Allocation.createTyped(rs, allin.getType());
        filtrecolor = ScriptIntrinsicColorMatrix.create(rs, Element.U8_4(rs));
        filtrecolor.setColorMatrix(kernelcolor);
        filtrecolor.forEach(allin, allout);
        allout.copyTo(out);
        imageView.setImageBitmap(out);
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
    }

    public void treure_botons_aplicar_filtre(View view) {
        mlay2.setVisibility(View.GONE);
        butons_aplicar_actius = false;
        mlay2.setClickable(false);
        anterior = copia.copy(copia.getConfig(), true);
        copia = out.copy(out.getConfig(), true);
    }

    public void desfes_filtre(View view) {
        out = copia.copy(copia.getConfig(), true);
        imageView.setImageBitmap(out);
        treure_botons_aplicar_filtre(view);
    }

    public void enrere_filtre() {
        mlay2.setVisibility(View.GONE);
        butons_aplicar_actius = false;
        mlay2.setClickable(false);
        out = anterior.copy(anterior.getConfig(), true);
        imageView.setImageBitmap(out);
    }

    public void Blur(View view) {
        rs = RenderScript.create(this);
        out = copia.copy(copia.getConfig(), true);
        allin = Allocation.createFromBitmap(rs, copia,
                Allocation.MipmapControl.MIPMAP_NONE,
                Allocation.USAGE_SCRIPT);
        allout = Allocation.createTyped(rs, allin.getType());
        filtreblur = ScriptIntrinsicBlur.create(rs, Element.U8_4(rs));
        filtreblur.setInput(allin);
        filtreblur.setRadius(11);
        filtreblur.forEach(allout);
        allout.copyTo(out);
        imageView.setImageBitmap(out);
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
        out = copia.copy(copia.getConfig(), true);
        rs = RenderScript.create(getApplicationContext());
        filtre3 = ScriptIntrinsicConvolve3x3.create(rs, Element.U8_4(rs));
        filtre3.setCoefficients(filtre);
        allin = createFromBitmap(rs, copia);
        allout = createFromBitmap(rs, out);
        filtre3.setInput(allin);
        filtre3.forEach(allout);
        allout.copyTo(out);
        imageView.setImageBitmap(out);
        rs.destroy();
    }

    public void filtres5x5(View view) {
        out = copia.copy(copia.getConfig(), true);
        rs = RenderScript.create(getApplicationContext());
        filtre5x5 = ScriptIntrinsicConvolve5x5.create(rs, Element.U8_4(rs));
        filtre5x5.setCoefficients(filtre5);
        allin = createFromBitmap(rs, copia);
        allout = createFromBitmap(rs, out);
        filtre5x5.setInput(allin);
        filtre5x5.forEach(allout);
        allout.copyTo(out);
        imageView.setImageBitmap(out);
        rs.destroy();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filtres);
        Bundle bundle = getIntent().getExtras();
        path = bundle.getString("path");
        anterior = (BitmapFactory.decodeFile(path));
        copia = anterior.copy(anterior.getConfig(), true);
        imageView = (ImageView) findViewById(R.id.photo_back);
        imageView.setImageBitmap(copia);
        mlay2.setVisibility(View.GONE);
        mlay2.setClickable(false);
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
        }
        return super.onOptionsItemSelected(item);
    }
}

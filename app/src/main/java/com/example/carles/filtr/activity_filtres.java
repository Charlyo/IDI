package com.example.carles.filtr;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.graphics.Color;
import android.widget.ImageView;
import android.view.*;


public class activity_filtres extends ActionBarActivity {
    String path;
    Bitmap copia;
    Bitmap original;
    ImageView imageView;
    int r, g, b;
    int outr, outg, outb;
    double[][] kernel = new double[3][3];

    public void agafa_rgb(int i, int j) {

        r = copia.getPixel(i,j) & 0xff0000 >> 16;
        g = copia.getPixel(i,j) & 0x00ff00 >> 8;
        b = copia.getPixel(i,j) & 0x0000ff;
    }

    public void invert(View view) {
        for (int i = 0; i < copia.getWidth(); ++i) {
            for (int j = 0; j < copia.getHeight(); ++j) {
                agafa_rgb(i, j);
                copia.setPixel(i, j, Color.rgb(255-r, 255-g, 255-b));
            }
        }
        imageView.setImageBitmap(copia);
    }
    public void blackandwhite(View view) {
        for (int i = 0; i < copia.getWidth(); ++i) {
            for (int j = 0; j < copia.getHeight(); ++j) {
                agafa_rgb(i, j);
                copia.setPixel(i, j, Color.rgb((r+g+b)/3, (r+g+b)/3, (r+g+b)/3));
            }
        }
        imageView.setImageBitmap(copia);
    }

    public void original(View view) {
        copia = original.copy(original.getConfig(), true);
        imageView.setImageBitmap(copia);
    }

    public void sepia(View view) {
        for (int i = 0; i < copia.getWidth(); ++i) {
            for (int j = 0; j < copia.getHeight(); ++j) {
                agafa_rgb(i, j);
                outr = r*4/10+g*8/10+b*2/10;
                outg = r*4/10+g*7/10+b*2/10;
                outb = r*3/10+g*5/10+b/10;
                if (outr > 255) outr = 255;
                if (outg > 255) outg = 255;
                if (outb > 255) outb = 255;
                copia.setPixel(i, j, Color.rgb(outr, outg ,outb));
            }
        }
        imageView.setImageBitmap(copia);
    }

    public void mitjana5() {
        /*for (int i = -1; i < 2; ++i) {
            for (int j = -1; j < 2; ++j) {
                agafa_rgb(x+i, y+j);
                outr += r;
                outb += b;
                outg += g;
            }
        }

        outr /=25;
        outg /=25;
        outb /=25;*/
        int SIZE = 3;
        int width = copia.getWidth();
        int height = copia.getHeight();
        Bitmap result = Bitmap.createBitmap(width, height, copia.getConfig());

        int A, R, G, B;
        int sumR, sumG, sumB;
        int[][] pixels = new int[3][3];

        for (int y = 0; y < height - 2; ++y) {
            for (int x = 0; x < width - 2; ++x) {

                // get pixel matrix
                for (int i = 0; i < SIZE; ++i) {
                    for (int j = 0; j < SIZE; ++j) {
                        pixels[i][j] = copia.getPixel(x + i, y + j);
                    }
                }

                // get alpha of center pixel
                A = Color.alpha(pixels[1][1]);

                // init color sum
                sumR = sumG = sumB = 0;

                // get sum of RGB on matrix
                for (int i = 0; i < SIZE; ++i) {
                    for (int j = 0; j < SIZE; ++j) {
                        sumR += (Color.red(pixels[i][j]) * kernel[i][j]);
                        sumG += (Color.green(pixels[i][j]) *kernel[i][j]);
                        sumB += (Color.blue(pixels[i][j]) * kernel[i][j]);
                    }
                }

                // get final Red
                R = (sumR / 2);
                if (R < 0) {
                    R = 0;
                } else if (R > 255) {
                    R = 255;
                }

                // get final Green
                G = (sumG / 2);
                if (G < 0) {
                    G = 0;
                } else if (G > 255) {
                    G = 255;
                }

                // get final Blue
                B = (sumB / 2);
                if (B < 0) {
                    B = 0;
                } else if (B > 255) {
                    B = 255;
                }

                // apply new pixel
                result.setPixel(x + 1, y + 1, Color.argb(A, R, G, B));
            }
        }
        copia = result;
        imageView.setImageBitmap(copia);
    }

    public void mitjana(View view) {
        /*for (int i = 0; i < 3; ++i) {
            for (int j = 0; j< 3; ++j) {
                kernel[i][j] = -1;
            }
        }

        kernel[1][1]= 8;*/
        kernel[0][0] = 1;
        kernel[0][1] = 0;
        kernel[0][2] = 0;
        kernel[1][0] = 0;
        kernel[1][1] = 1;
        kernel[1][2] = 0;
        kernel[2][0] = 0;
        kernel[2][1] = 0;
        kernel[2][2] = 1;

        mitjana5();
       /* for (int i = 1; i < copia.getWidth()-1; ++i) {
            for (int j = 1; j < copia.getHeight()-1; ++j) {

                copia.setPixel(i, j, Color.rgb(outr, outg, outb));
            }
        }*/
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filtres);
        Bundle bundle = getIntent().getExtras();
        path = bundle.getString("path");
        original = (BitmapFactory.decodeFile(path));
        copia = original.copy(original.getConfig(), true);
        imageView = (ImageView) findViewById(R.id.photo_back);
        imageView.setImageBitmap(copia);
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_activity_filtres, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

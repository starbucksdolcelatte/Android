package com.example.seoyoon.midterm;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TableLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView rgbinfo;
    ImageButton ru,gu,bu, rd,gd,bd;
    int r=255, g=255, b=255, avg=255;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("중간고사 1) 컬러 팔레트");

        rgbinfo = (TextView) findViewById(R.id.textView1);
        rgbinfo.setTextColor(Color.rgb(0,0,0));

        ru = (ImageButton) findViewById(R.id.imageButton_ru);
        gu = (ImageButton) findViewById(R.id.imageButton_gu);
        bu = (ImageButton) findViewById(R.id.imageButton_bu);
        rd = (ImageButton) findViewById(R.id.imageButton_rd);
        gd = (ImageButton) findViewById(R.id.imageButton_gd);
        bd = (ImageButton) findViewById(R.id.imageButton_bd);

    }


    public void onClick(View view) {

        if (view == ru) {
            if (r >= 255){
                return;
            }
            r = r + 5;
        }
        else if (view == gu) {
            if (g >= 255){
                return;
            }
            g = g + 5;
        }
        else if (view == bu) {
            if (b >= 255){
                return;
            }
            b = b + 5;
        }
        else if (view == rd) {
            if (r <= 0){
                return;
            }
            r = r - 5;
        }
        else if (view == gd) {
            if (g <= 0){
                return;
            }
            g = g - 5;
        }
        else if (view == bd) {
            if (b <= 0){
                return;
            }
            b = b - 5;
        }
        avg = (r+g+b)/3;

        rgbinfo.setText("RED "+Integer.toString(r)+" GREEN "+Integer.toString(g)+" BLUE "+Integer.toString(b));
        rgbinfo.setBackgroundColor(Color.rgb(r,g,b));

        if(avg > 128){
            rgbinfo.setTextColor(Color.rgb(0,0,0));
        }
        else{
            rgbinfo.setTextColor(Color.rgb(255,255,255));
        }
        return;
    }
}

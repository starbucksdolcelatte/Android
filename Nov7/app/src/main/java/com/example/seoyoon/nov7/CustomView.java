package com.example.seoyoon.nov7;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.view.View;

public class CustomView extends View {


    public CustomView(Context context) {
        super (context);
        init(context);
    }

    public CustomView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }


    private void init(Context context) {

    }


    @Override
    protected void onDraw(Canvas canvas){
        Paint paint = new Paint();

        //화면 해상도 크기로 비트맵 a 만들기
        Bitmap a = BitmapFactory.decodeResource(
                getResources(), R.drawable.cat400x300);

        //(0.0) 위치에 비트맵 a 그리기
        canvas.drawBitmap(a,0,0,null);

        //원본 이미지 크기로 비트맵 b 만들기
        BitmapFactory.Options opts = new BitmapFactory.Options();
        opts.inScaled = false;
        Bitmap b = BitmapFactory.decodeResource(
                getResources(), R.drawable.cat400x300, opts);

        //(0.1000) 위치에 비트맵 b 그리기
        canvas.drawBitmap(b, 0, 1000, null);
    }
}

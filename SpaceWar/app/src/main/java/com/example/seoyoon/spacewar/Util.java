package com.example.seoyoon.spacewar;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

// 유틸리티 클래스
class Util {
    static int view_width;      // view의 가로 크기
    static int view_height;     // view의 세로 크기

    static Bitmap bmp_ufo;
    static Bitmap bmp_myship;
    static Bitmap bmp_missle;

    static float crush_dist;    // ufo와 myship의 충돌 거리
    static float hit_dist;      // ufo와 missle의 충돌 거리

    // 이미지 로딩
    static void loadImages(Resources res) {
        // 원본 이미지 크기로 비트맵 만들기
        BitmapFactory.Options opts = new BitmapFactory.Options();
        opts.inScaled = false;

        bmp_ufo = BitmapFactory.decodeResource(res, R.drawable.ufo, opts);
        bmp_myship = BitmapFactory.decodeResource(res, R.drawable.myship, opts);
        bmp_missle = BitmapFactory.decodeResource(res, R.drawable.missile, opts);

        // 충돌 거리 계산
        crush_dist = (bmp_ufo.getWidth() + bmp_myship.getWidth())/2;
        hit_dist = (bmp_ufo.getWidth() + bmp_missle.getWidth())/2;
    }

    static void drawUfo(Canvas c, float x, float y) {
        c.drawBitmap(bmp_ufo, x - bmp_ufo.getWidth()/2,
                y - bmp_ufo.getHeight()/2, null);
    }

    static void drawMyShip(Canvas c, float x, float y) {
        c.drawBitmap(bmp_myship, x - bmp_myship.getWidth()/2,
                y - bmp_myship.getHeight()/2, null);
    }

    static void drawMissle(Canvas c, float x, float y) {
        c.drawBitmap(bmp_missle, x - bmp_missle.getWidth()/2,
                y - bmp_missle.getHeight()/2, null);
    }

    // 폭발하는 장면 그리기 (풍선을 랜덤하게 그려서...)
    static void drawBlast(Canvas c, float x, float y, int blast_count) {
        final float blast_size = 50;
        Paint p = new Paint();
        // blast_count 개수 만큼 연기 그리기
        for (int i = 1; i < blast_count; i++) {
            p.setColor(randColor());
            float x0 = Util.rand(-blast_size, blast_size);
            float y0 = Util.rand(-blast_size, blast_size);
            float r0 = Util.rand(blast_size/5, blast_size);
            c.drawCircle((x - x0 - r0/2), (y - y0 - r0/2), r0, p);
        }
    }

    // return 0.0 <= n < max
    static float rand(int max) {
        return (float)Math.random()*max;
    }

    // return min <= n < max
    static float rand(float min, float max) {
        return min + ((float)Math.random()*(max - min));
    }

    // return r% 확률
    static boolean prob100(float r) {
        return (Math.random()*100) <= r;
    }

    // 임의의 컬러 리턴
    static int randColor() {
        return randColor(0, 255);
    }

    // rgb 범위 min~max 범위의 임의의 컬러 리턴
    static int randColor(int min, int max) {
        int r = (int)rand(min, max);
        int g = (int)rand(min, max);
        int b = (int)rand(min, max);
        return Color.rgb(r, g, b);
    }

    // (x1, y1)-(x2, y2) 거리 계산
    static float get_dist(float x1, float y1, float x2, float y2) {
        float dx = x2 - x1;
        float dy = y2 - y1;
        return (float)Math.sqrt(dx*dx + dy*dy);
    }
}
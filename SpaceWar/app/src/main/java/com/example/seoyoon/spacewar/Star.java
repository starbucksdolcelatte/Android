package com.example.seoyoon.spacewar;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

class Star {

    static final int MAX_SIZE = 10;
    static final int STEP_DIV = 2;

    float x, y;     // star의 위치
    float size;     // star의 반지름 크기

    Star() {
        x = Util.rand(0, Util.view_width);
        y = Util.rand(0, Util.view_height);
        size = Util.rand(1, MAX_SIZE);
    }

    // star의 움직임 처리
    void move() {
        // 크기에 비례하여 이동 속도 결정
        y += size/STEP_DIV;
        // 아래쪽으로 사라지면 위쪽에 다시 생성
        if (y > Util.view_height + MAX_SIZE) {
            x = Util.rand(0, Util.view_width);
            y = Util.rand(0, -Util.view_height/10);
            size = Util.rand(1, MAX_SIZE);
        }
    }

    // star 그리기
    void draw(Canvas c) {
        Paint p = new Paint();
        p.setColor(Color.WHITE);
        c.drawCircle(x, y, size, p);
    }
}

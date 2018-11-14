package com.example.seoyoon.spacewar;

import android.graphics.Canvas;

class MyShip {
    static final int ST_DEATH = 0;
    static final int ST_ALIVE = 1;
    static final int ST_BLAST = 2;

    int state;			// myship의 상태 = ST_DEATH, ST_ALIVE, ST_BLAST

    static final int STEP = 20;
    static final int BLAST_TIME = 25;

    float x, y;		    // myship 위치
    float tx, ty;       // myship 이동 위치
    float dx, dy;       // myship 단위 이동 거리
    int width, height;	// myship 크기
    int blast_count;	// 폭발 카운트

    MyShip() {
        state = ST_ALIVE;
        x = tx = Util.view_width/2;
        y = ty = Util.view_height - 150;
        width = Util.bmp_myship.getWidth();
        height = Util.bmp_myship.getHeight();
    }

    int getState()	{ return state; }	// 상태 확인
    float getX()	{ return x;     }	// 가로 위치 확인
    float getY()	{ return y;     }	// 세로 위치 확인

    // 이동 위치 지정
    void moveTo(float x, float y) {
        tx = x;
        ty = y;
    }

    // 폭발 상태로 변경
    void blast() {
        state = ST_BLAST;
        blast_count = BLAST_TIME;
    }

    // 타이머에 의한 우주선 동작 처리
    void move() {
        // ALIVE 상태
        if (state == ST_ALIVE) {
            // (x, y)에서 (tx, ty)로 이동
            if (x < tx)
                x = (x < tx - STEP) ? x + STEP : tx;
            if (x > tx)
                x = (x > tx + STEP) ? x - STEP : tx;
            if (y < ty)
                y = (y < ty - STEP) ? y + STEP : ty;
            if (y > ty)
                y = (y > ty + STEP) ? y - STEP : ty;
        }
        // BLAST 상태에서는 BLAST_TIME 시간 후 ALIVE 상태로 설정
        if (state == ST_BLAST) {
            blast_count--;
            if (blast_count == 0) state = ST_ALIVE;
        }
    }

    // myship 그리기
    void draw(Canvas c) {
        if (state == ST_ALIVE)
            Util.drawMyShip(c, x, y);
        else if (state == ST_BLAST) {
            if (blast_count%2 == 0) Util.drawMyShip(c, x, y);
            Util.drawBlast(c, x, y, blast_count);
        }
    }
}
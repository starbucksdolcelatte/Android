package com.example.seoyoon.spacewar;

import android.graphics.Canvas;

class Missile {
    static final int ST_DEATH = 0;
    static final int ST_ALIVE = 1;

    int state;			// missle의 상태 = ST_DEATH, ST_ALIVE

    static final int STEP = 30;

    float x, y;		    // missle 위치
    int width, height;	// missle 크기

    Missile() {
        state = ST_DEATH;
        width = Util.bmp_missle.getWidth();
        height = Util.bmp_missle.getHeight();
    }

    int getState()	{ return state; }	// 상태 확인
    float getX()	{ return x;     }	// 가로 위치 확인
    float getY()	{ return y;     }	// 세로 위치 확인

    // 미사일 발사
    // 매개변수로 넘어오는 myship의 위치에서 출발
    void shot(float x, float y) {
        state = ST_ALIVE;
        this.x = x;
        this.y = y;
    }

    // 미사일 명중
    void hit() {
        state = ST_DEATH;
    }

    // 반복 실행되는 미사일 움직임 처리
    void move() {
        if (state == ST_ALIVE) {
            y -= STEP;
            if (y < -Util.bmp_missle.getHeight())
                state = ST_DEATH;
        }
    }

    // missle 그리기
    void draw(Canvas c) {
        if (state == ST_ALIVE)
            Util.drawMissle(c, x, y);
    }
}
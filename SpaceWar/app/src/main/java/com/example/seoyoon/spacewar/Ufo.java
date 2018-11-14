package com.example.seoyoon.spacewar;

import android.graphics.Canvas;

class Ufo {
    static final int ST_DEATH = 0;
    static final int ST_ALIVE = 1;
    static final int ST_BLAST = 2;

    int state;			// ufo의 상태 = ST_DEATH, ST_ALIVE, ST_BLAST

    static final int STEP = 10;
    static final int BLAST_TIME = 15;

    float x, y;		    // ufo 위치
    float dx, dy;       // ufo 단위 이동 거리
    int width, height;	// ufo 크기
    int blast_count;	// 폭발 카운트

    Ufo() {
        state = ST_DEATH;
        width = Util.bmp_ufo.getWidth();
        height = Util.bmp_ufo.getHeight();
    }

    int getState()	{ return state; }	// 상태 확인
    float getX()	{ return x;     }	// 가로 위치 확인
    float getY()	{ return y;     }	// 세로 위치 확인

    // ufo 등장
    void birth() {
        state = ST_ALIVE;

        // 초기 위치 설정
        x = Util.rand(Util.view_width/3, Util.view_width*2/3);
        y = -height;

        // 단위 이동 거리 dx, dy 설정
        if (Util.prob100(50))
            dx = Util.rand(STEP/4, STEP);
        else
            dx = Util.rand(-STEP, -STEP/4);
        dy = Util.rand(STEP/4, STEP/2);
    }

    // 폭발 상태 설정
    void blast() {
        state = ST_BLAST;
        blast_count = BLAST_TIME;
    }

    // ufo의 움직임 처리
    void move() {
        // ALIVE 상태에서는
        if (state == ST_ALIVE) {
            x += dx;
            y += dy;

            // 좌우 벽에 닿으면 반사
            if (x < width/2 || x > Util.view_width - width/2)
                dx = -dx;
            // 아래 벽에 닿으면 반사
            if (y > Util.view_height - height/2)
                dy = -dy;

            // 위쪽으로 넘어가면 DEATH 상태가 됨
            if (y < -height)
                state = ST_DEATH;
        }
        // BLAST 상태에서는 BLAST_TIME 시간 후 DEATH 상태가 됨
        else if (state == ST_BLAST) {
            blast_count--;
            if (blast_count == 0)
                state = ST_DEATH;
        }
    }

    // ufo 그리기
    void draw(Canvas c) {
        if (state == ST_ALIVE)
            Util.drawUfo(c, x, y);
        else if (state == ST_BLAST)
            Util.drawBlast(c, x, y, blast_count);
    }
}

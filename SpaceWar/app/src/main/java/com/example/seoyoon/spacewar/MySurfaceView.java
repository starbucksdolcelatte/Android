package com.example.seoyoon.spacewar;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

// 서피스 뷰 정의
public class MySurfaceView
        extends SurfaceView
        implements SurfaceHolder.Callback {

    private MyThread thread; // 스레드 참조 변수

    //===========================
    //      게임 속성 선언
    //---------------------------

    final int MAX_STAR = 100;
    final int MAX_UFO = 5;

    Star star[] = new Star[MAX_STAR];   // star 객체 목록
    Ufo ufo[] = new Ufo[MAX_UFO];       // ufo 객체 목록
    MyShip myship;                      // myship 객체
    Missile missile;                    // missile 객체

    //===========================

    public MySurfaceView(Context context) {
        super(context);

        SurfaceHolder holder = getHolder();
        holder.addCallback(this);
        thread = new MyThread(holder);
    }

    public MyThread getThread() {
        return thread;
    }

    public void surfaceCreated(SurfaceHolder holder) {

        //============================
        //      게임 속성 초기화
        //----------------------------

        // view 크기 저장
        Util.view_width = getWidth();
        Util.view_height = getHeight();

		// 비트맵 로딩
        Util.loadImages(getResources());

        // star 객체 생성
        for (int i = 0; i < MAX_STAR; i++)
            star[i] = new Star();

        // ufo 객체 생성
        for (int i = 0; i < MAX_UFO; i++)
            ufo[i] = new Ufo();

        // myship 객체 생성
        myship = new MyShip();

        // missile 객체 생성
        missile = new Missile();

        //============================

        thread.setRunning(true);    // 스레드를 시작한다.
        thread.start();
    }

    public void surfaceDestroyed(SurfaceHolder holder) {
        boolean retry = true;

        thread.setRunning(false);   // 스레드를 중지시킨다.
        while (retry) {
            try {
                thread.join();      // 메인 스레드와 합친다.
                retry = false;
            } catch (InterruptedException e) {
            }
        }
    }

    public void surfaceChanged(SurfaceHolder holder, int format, int width,
                               int height) {
        // Ball.WIDTH = width;
        // Ball.HEIGHT = height;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();

        //============================
        //      터치 이벤트 처리
        //----------------------------

        // myship을 터치하면 missile 발사
        float dist = Util.get_dist(x, y, myship.getX(), myship.getY());
        if (dist < myship.width) {
            if (missile.getState() == Missile.ST_DEATH)
                missile.shot(myship.getX(), myship.getY());
        }
        // 아니면 myship을 터치 위치로 이동
        else
            myship.moveTo(x, y);

        //============================

        return true;
    }

    // 스레드를 내부 클래스로 정의한다.
    public class MyThread extends Thread {
        private boolean mRun = false;
        private SurfaceHolder mSurfaceHolder;

        public MyThread(SurfaceHolder surfaceHolder) {
            mSurfaceHolder = surfaceHolder;
        }

        public void setRunning(boolean b) {
            mRun = b;
        }

        @Override
        public void run() {
            while (mRun) {

                //==========================
                //      게임 동작 처리
                //--------------------------

                // star 움직임 처리
                for (int i = 0; i < MAX_STAR; i++) {
                    Star s = star[i];
                    s.move();
                }

                // 1% 확률로 ufo 생성
                if (Util.prob100(1)) {
                    for (int i = 0; i < MAX_UFO; i++) {
                        Ufo u = ufo[i];
                        if (u.getState() == Ufo.ST_DEATH) {
                            u.birth();
                            break;
                        }
                    }

                }

                // ufo 움직임 처리
                for (int i = 0; i < MAX_UFO; i++) {
                    Ufo u = ufo[i];
                    u.move();
                }

                // missile 움직임 처리
                if (missile.getState() == Missile.ST_ALIVE) {
                    missile.move();

                    // missile과 ufo 충돌 확인
                    for (int i = 0; i < MAX_UFO; i++) {
                        Ufo u = ufo[i];
                        if (u.getState() == Ufo.ST_ALIVE) {
                            float dist = Util.get_dist(u.getX(), u.getY(),
                                    missile.getX(), missile.getY());
                            if (dist < Util.hit_dist) {
                                u.blast();
                                missile.hit();
                                break;
                            }
                        }
                    }
                }

                // myship 움직임 처리 및 그리기
                myship.move();

                // myship과 ufo의 충돌 확인
                if (myship.getState() == MyShip.ST_ALIVE) {
                    for (int i = 0; i < MAX_UFO; i++) {
                        Ufo u = ufo[i];
                        if (u.getState() == Ufo.ST_ALIVE) {
                            float dist = Util.get_dist(u.getX(), u.getY(),
                                    myship.getX(), myship.getY());
                            if (dist < Util.crush_dist) {
                                u.blast();
                                myship.blast();
                                break;
                            }
                        }
                    }
                }

                //==========================

                Canvas c = null;
                try {
                    c = mSurfaceHolder.lockCanvas(null);
                    c.drawColor(Color.BLACK);
                    synchronized (mSurfaceHolder) {

                        //========================
                        //      게임 그리기
                        //------------------------

                        // star 그리기
                        for (int i = 0; i < MAX_STAR; i++) {
                            Star s = star[i];
                            s.draw(c);
                        }

                        // missile 그리기
                        if (missile.getState() == Missile.ST_ALIVE)
                            missile.draw(c);

                        // ufo 그리기
                        for (int i = 0; i < MAX_UFO; i++) {
                            Ufo u = ufo[i];
                            u.draw(c);
                        }

                        // myship 그리기
                        myship.draw(c);

                        //========================
                    }
                } finally {
                    if (c != null) {
                        mSurfaceHolder.unlockCanvasAndPost(c);
                    }
                }
                //try { Thread.sleep(100); } 
				//catch (InterruptedException e) { }
            }
        }
    }
}
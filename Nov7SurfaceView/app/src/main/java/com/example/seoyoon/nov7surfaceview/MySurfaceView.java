package com.example.seoyoon.nov7surfaceview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class MySurfaceView extends SurfaceView implements SurfaceHolder.Callback {
    public Ball basket[] = new Ball[10]; // Ball의 배열 선언
    private MyThread thread; // thread 참조 변수

    public MySurfaceView(Context context) //생성자
    {
        super(context);

        SurfaceHolder holder = getHolder(); // surface view의 홀더를 얻는다
        holder.addCallback(this); // 콜백메소드 처리

        thread = new MyThread(holder); //스레드를 생성

        // Ball 객체를 생성하여 배열에 넣는다.
        for (int i = 0 ; i < 10 ; i++ )
            basket[i] = new Ball(100);
    }

    public MyThread getThread() {
        return thread;
    }

    public void surfaceCreated(SurfaceHolder holder){
        //스레드를 시작
        thread.setRunning(true);
        thread.start();
    }

    public void surfaceDestroyed(SurfaceHolder holder){
        boolean retry = true;

        //스레드를 중지시킴
        thread.setRunning(false);
        while(retry) {
            try {
                thread.join(); //메인 스레드와 합친다.
                retry = false;
            }
            catch (InterruptedException e) { }
        }

        public void surfaceChanged(SurfaceHolder holder, int format, int width, int height){
            // Ball.WIDTH = width;
            // Ball.HEIGHT = height;
        }


    }
}


public class MyThread extends Thread {
    private boolean mRun = false;
    private SurfaceHolder mSurfaceHolder;

    public MyThread(SurfaceHolder surfaceHolder){
        mSurfaceHolder = surfaceHolder;
    }

    @Override
    public void run(){
        while (mRun) {
            Canvas c = null;
            try{
                c = mSurfaceHolder.lockCanvas(null); //캔버스를 얻는다.
                c.drawColor(Color.BLACK); //캔버스의 배경을 지운다.
                synchronized (mSurfaceHolder){
                    for(Ball b : basket){
                        //basket의 모든 원소를 그린다.
                        b.paint(c);
                    }
                }
            }
            finally {
                if (c!=null){
                    //캔버스의 lock을 푼다.
                    mSurfaceHolder.unlockCanvasAndPost(c);
                }
            }
            try { Thread.sleep(100); }
            catch (InterruptedException e ) {}
        }
    }
    public void setRunning(boolean b) {
        mRun = b;
    }
}
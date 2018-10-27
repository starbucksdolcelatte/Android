package com.example.seoyoon.practiceformidterm2;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RatingBar;


//나만의 커스텀 뷰를 만들어줘!
class MyView extends View {
    //int key;
    //String str;
    int x, y;

    public MyView(Context context) {
        super(context);
        setBackgroundColor(Color.rgb(247,202,201)); //rose quartz
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        x = (int)event.getX(0);
        y = (int)event.getY(0);
        invalidate();
        return super.onTouchEvent(event);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Paint paint = new Paint();
        paint.setTextSize(50);
        paint.setColor(Color.rgb(146,168,209));
        canvas.drawCircle(x,y,20, paint);
        canvas.drawText("("+x+", "+y+") 에서 터치 이벤트가 발생하였음", x,y+60, paint);
    }
}
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Volume Control 뷰
        setContentView(R.layout.activity_main);
        final RatingBar ratingbar = (RatingBar) findViewById(R.id.ratingBar);

        VolumeControlView view = (VolumeControlView) findViewById(R.id.volume);
        view.setKnobListener(new VolumeControlView.KnobListener() {
            @Override
            public void onChanged(double angle) {
                float rating = ratingbar.getRating();
                if (angle > 0 && rating < 7.0)
                    //오른쪽으로 회전
                    ratingbar.setRating(rating+1.0f);
                else if (rating > 0.0)
                    ratingbar.setRating(rating-1.0f);
            }
        });


        // 캔바스에 점찍는 뷰
        /*
        //커스텀 뷰 객체 생성
        MyView w = new MyView(this);
        //커스텀 뷰 객체를 액티비티의 화면으로 설정한다.
        setContentView(w);
        */

    }
}

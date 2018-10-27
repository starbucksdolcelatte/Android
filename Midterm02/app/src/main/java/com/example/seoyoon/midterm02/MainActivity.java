package com.example.seoyoon.midterm02;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button b1,b2,b3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("중간고사 2) 캠퍼스 생활전화");
        b1 = (Button) findViewById(R.id.button1);
        b2 = (Button) findViewById(R.id.button2);
        b3 = (Button) findViewById(R.id.button3);
    }

    public void onClick(View view) {

        if (view == b1) {
            PopupMenu popup =  new PopupMenu(this,view);
            popup.getMenuInflater().inflate(R.menu.popupmenu, popup.getMenu());

            popup.setOnMenuItemClickListener(
                    new PopupMenu.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem item) {
                            Toast.makeText(getApplicationContext(), item.getTitle()+" 로 연결하시겠습니까?"
                            , Toast.LENGTH_SHORT).show();
                            return true;
                        }
                    }
            );
            popup.show();
        }
        else if (view == b2) {

        }
        else if (view == b3) {

        }
        return;
    }
    public void open(View view, String str){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage(str+" 로 전화 연결할까요?");
        alertDialogBuilder.setPositiveButton("네",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
        alertDialogBuilder.setNegativeButton("아니요",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }


}

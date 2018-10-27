package com.example.seoyoon.oct17;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    TextView text;
    Button btn1, btn2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        text=(TextView)findViewById(R.id.TextView01);
        registerForContextMenu(text);

        //btn1=(Button)findViewById(R.id.button1);
        //btn2=(Button)findViewById(R.id.button2);
    }
/*
    public void onClick(View button){
        if(button == btn1){
            PopupMenu popup = new PopupMenu(content this, button);
            popup.getMenuInflater().inflate(R.menu.popup1, popup.getMenu());

            popup.setOnMenuItemClickIistner(
                    new PopupMenu.OnMenuItemClickListner()
            )
        }
    }
*/
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.mymenu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.apple:
                Toast.makeText(this, "사과사과", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.grape:
                Toast.makeText(this, "포도포도", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.banana:
                Toast.makeText(this, "빠냐냐", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo){
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.setHeaderTitle("컨텍스트 메뉴ㅎㅎㅎ");
        menu.add(0,1,0, "배경색은 red");
        menu.add(0,1,0, "배경색은 green");
        menu.add(0,1,0, "배경색은 blue");
    }

    public boolean onContextItemSelected(MenuItem item){
        switch (item.getItemId()){
            case 1:
                text.setBackgroundColor(Color.RED);
            case 2:
                text.setBackgroundColor(Color.GREEN);
            case 3:
                text.setBackgroundColor(Color.BLUE);
            default:
                return super.onContextItemSelected(item);
        }
    }
}

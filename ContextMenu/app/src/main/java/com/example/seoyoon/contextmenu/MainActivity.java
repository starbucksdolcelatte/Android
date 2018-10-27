package com.example.seoyoon.contextmenu;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        text = (TextView) findViewById(R.id.TextView01);
        registerForContextMenu(text);

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.setHeaderTitle("컨텍스트 메뉴");
        menu.add(0, 1, 0, "배경색: RED");
        menu.add(0, 2, 0, "배경색: GREEN");
        menu.add(0, 3, 0, "배경색: BLUE");
    }

    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case 1:
                text.setBackgroundColor(Color.RED);
                return true;
            case 2:
                text.setBackgroundColor(Color.GREEN);
                return true;
            case 3:
                text.setBackgroundColor(Color.BLUE);
                return true;
            default:
                return super.onContextItemSelected(item); }
    }


        @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //메뉴 mymenu.xml을 팽창하여 메뉴 객체로 만든다.
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.mymenu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //각 메뉴의 항목이 눌러지면 토스트메세지를 표시한다.
        switch(item.getItemId()){
            case R.id.apple:
                Toast.makeText(this, "apple >_<", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.banana:
                Toast.makeText(this, "banana o_o", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}

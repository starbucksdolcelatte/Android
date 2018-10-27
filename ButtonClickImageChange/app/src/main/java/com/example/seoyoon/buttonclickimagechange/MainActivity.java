package com.example.seoyoon.buttonclickimagechange;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    RadioGroup group;
    RadioButton button1, button2;
    Button button;
    ImageView image;
    TextView text;

    View.OnClickListener radio_listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (group.getCheckedRadioButtonId()) {
                case R.id.radioButton_chicken:
                    image.setImageResource(R.drawable.chicken);
                    break;
                case R.id.radioButton_pizza:
                    image.setImageResource(R.drawable.pizza);
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("배달의민족");

        group = (RadioGroup) findViewById(R.id.radiogroup);
        button1 = (RadioButton) findViewById(R.id.radioButton_chicken);
        button2 = (RadioButton) findViewById(R.id.radioButton_pizza);

        //button1.setOnClickListener(radio_listener);
        //button2.setOnClickListener(radio_listener);

        button = (Button) findViewById(R.id.button);
        image = (ImageView) findViewById(R.id.imageView);
    }

    public void onClicked(View view) {
        switch (view.getId()){
            case R.id.button:
                button1 = (RadioButton) findViewById(R.id.radioButton_chicken);
                button2 = (RadioButton) findViewById(R.id.radioButton_pizza);
                text = (TextView) findViewById(R.id.textView4);
                image = (ImageView) findViewById(R.id.imageView);

                if (button1.isChecked()) {
                    image.setImageResource(R.drawable.chicken);
                    text.setText("18000");
                }
                if (button2.isChecked()) {
                    image.setImageResource(R.drawable.pizza);
                    text.setText("21000");
                }


        }
    }
}

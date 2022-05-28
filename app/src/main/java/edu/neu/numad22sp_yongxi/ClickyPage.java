package edu.neu.numad22sp_yongxi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ClickyPage extends AppCompatActivity {

    Button btn_a;
    Button btn_b;
    Button btn_c;
    Button btn_d;
    Button btn_e;
    Button btn_f;
    TextView press_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clicky_page);

        btn_a = findViewById(R.id.clicky_a);
        btn_b = findViewById(R.id.clicky_b);
        btn_c = findViewById(R.id.clicky_c);
        btn_d = findViewById(R.id.clicky_d);
        btn_e = findViewById(R.id.clicky_e);
        btn_f = findViewById(R.id.clicky_f);
        press_text = findViewById((R.id.press_text));

        btn_a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                press_text.setText("Pressed: A");
            }
        });

        btn_b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                press_text.setText("Pressed: B");
            }
        });

        btn_c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                press_text.setText("Pressed: C");
            }
        });

        btn_d.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                press_text.setText("Pressed: D");
            }
        });

        btn_e.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                press_text.setText("Pressed: E");
            }
        });

        btn_f.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                press_text.setText("Pressed: F");
            }
        });
    }
}
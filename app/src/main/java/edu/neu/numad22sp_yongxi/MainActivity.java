package edu.neu.numad22sp_yongxi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button btn_about;
    Button btn_clicky;
    Button btn_link;
    Button btn_prime;
    Button btn_locator;
    Button btn_webService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_about = findViewById(R.id.btn_hello);
        btn_about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AboutPage.class);
                startActivity(intent);
            }
        });

        btn_clicky = findViewById(R.id.btn_clicky);
        btn_clicky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ClickyPage.class);
                startActivity(intent);
            }
        });

        btn_link = findViewById(R.id.btn_linkController);
        btn_link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LinkControllerPage.class);
                startActivity(intent);
            }
        });

        btn_prime = findViewById(R.id.btn_prime);
        btn_prime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, PrimePage.class);
                startActivity(intent);
            }
        });

        btn_locator = findViewById(R.id.btn_locator);
        btn_locator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LocatorPage.class);
                startActivity(intent);
            }
        });

        btn_webService = findViewById(R.id.btn_service);
        btn_webService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, WebService.class);
                startActivity(intent);
            }
        });
    }
}
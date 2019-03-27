package com.example.yomaestacionamiento;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;



public class MainActivity extends AppCompatActivity {

    TextView ini, invi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ini=(TextView) findViewById(R.id.ini);
        invi=(TextView) findViewById(R.id.invi);

        ini.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ve= new Intent(MainActivity.this,InicioSesion.class);

                startActivity(ve);
            }
        });



        invi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ve= new Intent(MainActivity.this,Crono.class);

                startActivity(ve);

            }
        });
    }


}

package com.example.yomaestacionamiento;

import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Chronometer;
import android.widget.ImageButton;

public class Crono extends AppCompatActivity {

    ImageButton Inicio,pausa;
    Chronometer crono;
    Boolean correr=false;
     long detenr;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crono);

        Inicio=(ImageButton) findViewById(R.id.btini);
        pausa=(ImageButton) findViewById(R.id.btpau);

        crono=(Chronometer) findViewById(R.id.cronometro);

        Inicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                starcronometro();
            }
        });
        pausa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               stopcronometro();
            }
        });


    }

    private void reiniciarcrono() {
        crono.setBase(SystemClock.elapsedRealtime());
        detenr=0;

    }

    private void starcronometro() {
        if(!correr){
            crono.setBase(SystemClock.elapsedRealtime());
            crono.start();
            correr=true;
        }
    }
     private void stopcronometro() {

        if(correr){
            crono.stop();
            detenr=SystemClock.elapsedRealtime()-crono.getBase()-detenr;
            correr=false;

        }

     }
}

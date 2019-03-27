package com.example.yomaestacionamiento;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class MenuAdm extends AppCompatActivity {

    ImageButton emp,cli,qr,rep;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_adm);


        emp=(ImageButton) findViewById(R.id.btge);
        cli=(ImageButton) findViewById(R.id.btgc);
        qr=(ImageButton) findViewById(R.id.btq);
        rep=(ImageButton) findViewById(R.id.btr);

        emp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ve= new Intent(MenuAdm.this,GesEmp.class);

                startActivity(ve);
            }
        });

        cli.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ve= new Intent(MenuAdm.this,GesCli.class);

                startActivity(ve);
            }
        });
        qr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ve= new Intent(MenuAdm.this,Qr.class);

                startActivity(ve);
            }
        });
        rep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ve= new Intent(MenuAdm.this,Reporte.class);

                startActivity(ve);
            }
        });


    }
}

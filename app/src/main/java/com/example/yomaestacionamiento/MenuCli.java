package com.example.yomaestacionamiento;

import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MenuCli extends AppCompatActivity {

    ImageView imagen ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_cli);

        imagen = (ImageView) findViewById(R.id.imagen);
    }

    public void onClick(View view){
        cargarImagen();
    }

    private void cargarImagen(){

        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/");
        startActivityForResult(intent.createChooser(intent,"Seleccione la aplicacion"),10);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode== RESULT_OK){

            Uri paht = data.getData();
            imagen.setImageURI(paht);

        }
    }
}

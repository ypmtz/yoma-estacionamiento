package com.example.yomaestacionamiento;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class InicioSesion extends AppCompatActivity {
EditText user,pass;
   // ProgressBar culo;
    ConexionSQL conexion;
    Button boton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio_sesion);


        user = (EditText) findViewById(R.id.txtUsu);
        pass = (EditText) findViewById(R.id.txtpass);
        boton = (Button) findViewById(R.id.btn_ingresar);
       // culo = (ProgressBar) findViewById(R.id.pb);
       // culo.setVisibility(View.GONE);
        conexion = new ConexionSQL();


        getSupportActionBar().hide();

        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login ingreso=new  login();
                ingreso.execute("");
            }
        });

    }

    public void mostrarActionBar(View v) {
        getSupportActionBar().show();
    }

    public class login extends AsyncTask<String, String, String> {
        String msj;
        boolean exito;
        String usu = user.getText().toString();
        String contraseña = pass.getText().toString();
        String tipo;


        @Override
        //metodo que se ejecuta antes y durante del metodo doinBackgroud
        protected void onPreExecute() {
           // culo.setVisibility(View.VISIBLE);

        }

        protected void onPostExecute(String r) {
           // culo.setVisibility(View.GONE);
            if (exito) {
                if(tipo.equals("ADMINISTRADOR")){ Toast.makeText(getApplicationContext(), r, Toast.LENGTH_LONG).show();
                    Intent v= new Intent(InicioSesion.this,MenuAdm.class);

                    startActivity(v);
                }else if(tipo.equals("CLIENTE")){Toast.makeText(getApplicationContext(), r, Toast.LENGTH_LONG).show();
                    Intent v= new Intent(InicioSesion.this,MenuCli.class);

                    startActivity(v);
                }else { Intent v= new Intent(InicioSesion.this,MenuEmp.class);

                    startActivity(v);}



            }
            else {
                Toast.makeText(getApplicationContext(), r, Toast.LENGTH_LONG).show();
            }

        }

        protected String doInBackground(String... strings) {
            if (usu.trim().equals("")||  contraseña.trim().equals("")) {
                msj = "No se admiten campos vacios";
            } else {
                Connection conn = conexion.CONN();
                System.out.println("conex: "+conn);
                String query = "select * from Usuarios where Correo='" + usu + "'and Password='" + contraseña + "'";
                try {
                    Statement stmt = conn.createStatement();
                    ResultSet rs = stmt.executeQuery(query);
                    if (rs.next()) {
                        msj = "Bienvenido al sistema";
                        tipo=rs.getString("Tipo");
                        exito = true;
                    } else {
                        msj = "Usuario o  contraseña incorrecta";
                        exito = false;
                    }

                } catch (SQLException e) {
                    e.printStackTrace();
                }
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            }
            return msj;

        }
    }

}

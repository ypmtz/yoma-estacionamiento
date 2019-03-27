package com.example.yomaestacionamiento;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Listado extends AppCompatActivity {

    ConexionSQL CONN;
    ListView listView;
    ArrayList<String> listado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado);
        listView=(ListView)findViewById(R.id.listView);
        CONN = new ConexionSQL();
        CargarListado();
    }


    private void CargarListado(){
        listado=ListaEmpleados();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,listado);
        listView.setAdapter(adapter);
    }

    private ArrayList<String>ListaEmpleados(){
        ArrayList<String> datos= new ArrayList<>();
        Connection conn = CONN.CONN();
        String query = "Select Id_Empleado,Nombre, Ap_p,Ap_m,Correo from Empleados";
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            rs.next();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            CONN.CONN().close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return datos;
    }

}

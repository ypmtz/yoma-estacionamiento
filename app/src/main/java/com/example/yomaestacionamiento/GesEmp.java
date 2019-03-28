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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLOutput;
import java.sql.Statement;


public class GesEmp extends AppCompatActivity {

    ConexionSQL CONN;
    EditText editTextNUMEMPLEADO, editTextNOMEMPLEADO, editTextAP_P, editTextAP_M, editTextTEL_EMP, editTextDOMEMP, editTextCORR_EMP, editTextTIPO_EMP, editTextEST_EMP;
    Button bt_guardar, bt_mostrar, btn_modifcarEmpleado;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ges_emp);

        CONN = new ConexionSQL();
        editTextNUMEMPLEADO = (EditText) findViewById(R.id.editTextNUMEMPLEADO);
        editTextNOMEMPLEADO = (EditText) findViewById(R.id.editTextNOMEMPLEADO);
        editTextAP_P = (EditText) findViewById(R.id.editTextAP_P);
        editTextAP_M = (EditText) findViewById(R.id.editTextAP_M);
        editTextTEL_EMP = (EditText) findViewById(R.id.editTextTEL_EMP);
        editTextDOMEMP = (EditText) findViewById(R.id.editTextDOMEMP);
        editTextCORR_EMP = (EditText) findViewById(R.id.editTextCORR_EMP);
        editTextTIPO_EMP = (EditText) findViewById(R.id.editTextTIPO_EMP);
        editTextEST_EMP = (EditText) findViewById(R.id.editTextEST_EMP);
        bt_guardar = (Button) findViewById(R.id.bt_guardar);
        bt_mostrar = (Button) findViewById(R.id.bt_mostrar);
        btn_modifcarEmpleado = (Button) findViewById(R.id.btn_modificar);

        editTextNUMEMPLEADO.setEnabled(false);
        editTextTIPO_EMP.setEnabled(false);
        editTextEST_EMP.setEnabled(false);


        bt_guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guardarEmpleado();
            }
        });

        bt_mostrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                consultarEmpleado();
              // Intent ve = new Intent(GesEmp.this, Listado.class);
               // startActivity(ve);
            }
        });

        btn_modifcarEmpleado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                modificar();
            }
        });
    }

    private void guardarEmpleado() {

        try {
            PreparedStatement pst = CONN.CONN().prepareStatement("insert into Empleados (Nombre,Ap_p,Ap_m,Telefono,Domicilio,Correo,Tipo,Estatus) values(?,?,?,?,?,?,?,?)");
            pst.setString(1, editTextNOMEMPLEADO.getText().toString());
            pst.setString(2, editTextAP_P.getText().toString());
            pst.setString(3, editTextAP_M.getText().toString());
            pst.setString(4, editTextTEL_EMP.getText().toString());
            pst.setString(5, editTextDOMEMP.getText().toString());
            pst.setString(6, editTextCORR_EMP.getText().toString());
            pst.setString(7, editTextTIPO_EMP.getText().toString());
            pst.setString(8, editTextEST_EMP.getText().toString());


            if (editTextNOMEMPLEADO.length() == 0) {
                editTextNOMEMPLEADO.setError("INGRESE EL NOMBRE");
            } else if (editTextAP_P.length() == 0) {
                editTextAP_P.setError("INGRESE APELLIDO PATERNO");
            } else if (editTextAP_M.length() == 0) {
                editTextAP_M.setError("INGRESE APELLIDO MATERNO");
            } else if (editTextTEL_EMP.length() == 0) {
                editTextTEL_EMP.setError("INGRESE TELÉFONO");
            } else if (editTextDOMEMP.length() == 0) {
                editTextDOMEMP.setError("INGRESE DOMICILIO");
            } else if (editTextCORR_EMP.length() == 0) {
                editTextCORR_EMP.setError("INGRESE CORREO");
            } else {
                pst.executeUpdate();
                editTextNOMEMPLEADO.setText("");
                editTextAP_P.setText("");
                editTextAP_M.setText("");
                editTextTEL_EMP.setText("");
                editTextDOMEMP.setText("");
                editTextCORR_EMP.setText("");

                Toast.makeText(getApplicationContext(), "EMPLEADO REGISTRADO", Toast.LENGTH_SHORT).show();
            }

        } catch (Exception e) {

        }

    }

    public void consultarEmpleado(){
        String nombre= editTextNOMEMPLEADO.getText().toString();


        String id = null;
        String app= null;
        String appm= null;
        String telefono= null;
        String direcion= null;
        String correo= null;
        String tipo= null;
        String estatus = null;

        if (nombre.trim().equals("")) {
           //toast
        } else {
            Connection conn = CONN.CONN();
            System.out.println("conex: "+conn);
            String query = "select * from Empleados where Nombre='" +nombre+"'";

            try {
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(query);
                if (rs.next()) {
                    id=rs.getString("id_empleado");
                    app=rs.getString("Ap_p");
                    appm=rs.getString("Ap_m");
                    telefono=rs.getString("Telefono");
                    direcion=rs.getString("Domicilio");
                    correo=rs.getString("Correo");
                    tipo=rs.getString("Tipo");
                    estatus=rs.getString("Estatus");

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
        editTextNUMEMPLEADO.setText(id);
        editTextAP_M.setText(appm);
        editTextAP_P.setText(app);
        editTextTEL_EMP.setText(telefono);
        editTextDOMEMP.setText(direcion);
        editTextCORR_EMP.setText(correo);
        editTextTIPO_EMP.setText(tipo);
        editTextEST_EMP.setText(estatus);



    }
    public void modificar(){
        //consultarEmpleado();
    String id =editTextNUMEMPLEADO.getText().toString();
    String nombre =editTextNOMEMPLEADO.getText().toString();

        String app= editTextAP_P.getText().toString();
        String appm= editTextAP_M.getText().toString();
        String telefono= editTextTEL_EMP.getText().toString();
        String direcion= editTextDOMEMP.getText().toString();
        String correo= editTextCORR_EMP.getText().toString();
        String tipo= editTextTIPO_EMP.getText().toString();
        String estatus = editTextEST_EMP.getText().toString();



        Connection conn = CONN.CONN();
        System.out.println("conex: "+conn);
        String query =" update Empleados set Nombre='"+nombre+"',Ap_p='"+app+"',Ap_m='"+appm+"',Telefono='"+telefono+"',Domicilio='"+direcion+"',Correo='"+correo+"',Tipo='"+tipo+"',Estatus='"+estatus+"' where Id_empleado='"+id+"'";

        try {
            int i=1;
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            if (rs.rowUpdated()) {

                Toast.makeText(getApplicationContext(), "Empleado modificado ", Toast.LENGTH_LONG).show();
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



    }




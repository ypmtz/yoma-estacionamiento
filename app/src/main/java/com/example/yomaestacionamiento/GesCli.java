package com.example.yomaestacionamiento;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.sql.PreparedStatement;

public class GesCli extends AppCompatActivity {


    ConexionSQL CONN;
    EditText editTextNUMEMPLEADO, editTextNOMEMPLEADO, editTextAP_P, editTextAP_M, editTextTEL_EMP, editTextDOMEMP, editTextCORR_EMP, editTextTIPO_EMP, editTextEST_EMP;
    Button bt_guardarCliente,bt_mostrarCliente, btn_modificarCliente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ges_cli);

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
        bt_guardarCliente=(Button)findViewById(R.id.bt_guardarCliente);
        bt_mostrarCliente=(Button)findViewById(R.id.bt_mostrarCliente);
       // btn_modificarCliente = (Button) findViewById(R.id.btn_modificarCliente);

        editTextNUMEMPLEADO.setEnabled(false);
        editTextTIPO_EMP.setEnabled(false);
        editTextEST_EMP.setEnabled(false);

        bt_guardarCliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guardarCliente();
            }
        });

    }

    private void guardarCliente(){

        try{
            PreparedStatement pst=CONN.CONN().prepareStatement("insert into Clientes (Nombre,Ap_p,Ap_m,Telefono,Domicilio,Correo,Tipo,Estatus) values(?,?,?,?,?,?,?,?)");
            pst.setString(1,editTextNOMEMPLEADO.getText().toString());
            pst.setString(2,editTextAP_P.getText().toString());
            pst.setString(3,editTextAP_M.getText().toString());
            pst.setString(4,editTextTEL_EMP.getText().toString());
            pst.setString(5,editTextDOMEMP.getText().toString());
            pst.setString(6,editTextCORR_EMP.getText().toString());
            pst.setString(7,editTextTIPO_EMP.getText().toString());
            pst.setString(8,editTextEST_EMP.getText().toString());

            if(editTextNOMEMPLEADO.length()==0){
                editTextNOMEMPLEADO.setError("INGRESE EL NOMBRE");
            }else if(editTextAP_P.length()==0){
                editTextAP_P.setError("INGRESE APELLIDO PATERNO");
            }
            else if(editTextAP_M.length()==0){
                editTextAP_M.setError("INGRESE APELLIDO MATERNO");
            }
            else if(editTextTEL_EMP.length()==0){
                editTextTEL_EMP.setError("INGRESE TELÉFONO");
            }
            else if(editTextDOMEMP.length()==0){
                editTextDOMEMP.setError("INGRESE DOMICILIO");
            }
            else if(editTextCORR_EMP.length()==0){
                editTextCORR_EMP.setError("INGRESE CORREO");
            }
            else{
                pst.executeUpdate();
                editTextNOMEMPLEADO.setText("");
                editTextAP_P.setText("");
                editTextAP_M.setText("");
                editTextTEL_EMP.setText("");
                editTextDOMEMP.setText("");
                editTextCORR_EMP.setText("");

                Toast.makeText(getApplicationContext(),"CLIENTE REGISTRADO",Toast.LENGTH_SHORT).show();
            }
        }catch (Exception e){

        }
    }

}

package com.example.yomaestacionamiento;
import android.annotation.SuppressLint;
import android.os.StrictMode;
import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class ConexionSQL {

    String ip="192.168.0.27:1433";
    String classs="net.sourceforge.jtds.jdbc.Driver";
    String db="Estacionamiento";
    String us="sa";
    String password="1234";

    @SuppressLint("NewApi")
    public Connection CONN(){
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        Connection conn=null;
        String conURL=null;
        try{
            Class.forName(classs);
            conURL="jdbc:jtds:sqlserver://"+ip+";"+"databaseName="+db+";user="+us+";password="+password;
            conn=DriverManager.getConnection(conURL);
        }catch(SQLException se){
            Log.e("ERROR", se.getMessage());
        }catch (ClassNotFoundException e) {
            Log.e("ERROR", e.getMessage());
        }catch (Exception e){
            Log.e("ERROR", e.getMessage());
        }
        return conn;
    }



}

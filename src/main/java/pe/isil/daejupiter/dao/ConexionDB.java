/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.isil.daejupiter.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionDB {
    private Connection conexion;
    private static ConexionDB instancia;

    //el constructor es la primera función que se ejecuta cuando creamos un objeto de la clase
    private ConexionDB(){
        try {
            //Paso 1: Definir los parametros de la funcion
            String username="root";
            String password="root";
            String host = "localhost";
            String database = "daejupiter";
            String puerto = "3306";
            
            //paso 2; Cargar el Driver o controlador o dependencia de MYSQL
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            //paso 3: Conectarnos a la DB
            String url = "jdbc:mysql://" + host +":"+puerto + "/"+database+
                    "?useSSL=false&serverTimezone=America/Lima";
            
            this.conexion = DriverManager.getConnection(url,username,password);
            System.out.println("Conexion a DB exitosa!");
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    public Connection getConnection(){
        return conexion;
    }
    
    public static ConexionDB getInstancia(){
        if(ConexionDB.instancia != null){
            return ConexionDB.instancia;
        }
        ConexionDB.instancia = new ConexionDB();
        return ConexionDB.instancia;
    }
    
    public void close(Connection conexion){
        try {
            conexion.close();
            instancia = null;
            if(conexion.isClosed()){
                System.out.println("Se cerró la conexion ");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}

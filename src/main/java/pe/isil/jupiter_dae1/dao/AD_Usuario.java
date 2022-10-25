/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.isil.jupiter_dae1.dao;

import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import pe.isil.jupiter_dae1.model.Usuario;

/**
 *
 * @author BSJF
 */
public class AD_Usuario {
    
    private PreparedStatement pst = null;
    private ResultSet rst; // obtiene los datos de la db, select
    
    public boolean insertar (Usuario usuario){
        boolean resultado = false;
        Connection Conexion = null;
        try {
               Conexion = ConexionDB.getInstancia().getConnection();
           if(Conexion != null){
               String SQL = "INSERT INTO usuario (email, password, nombre, dni,rol) VALUES(?,?,?,?,?)";
               pst = Conexion.prepareStatement(SQL);
               pst.setString(1, usuario.getEmail());
               pst.setString(2, usuario.getPassword());
               pst.setString(3, usuario.getNombre());
               pst.setString(4, usuario.getDni());
               pst.setString(5, usuario.getRol());
               
               int res= 0;
               res = pst.executeUpdate();
               if(res > 0){
                   resultado = true;
               }else{
                   resultado = false ;
               }
               
           }else{
               System.out.println("Error en la conexión a la DB");
           }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            ConexionDB.getInstancia().close(Conexion);
        }
    return resultado;
    }
    
    //actualizar
     public boolean actualizar (Usuario usuario){
        boolean resultado = false;
        Connection Conexion = null;
        try {
               Conexion = ConexionDB.getInstancia().getConnection();
           if(Conexion != null){
               String SQL = "UPDATE usuario set email=?, password=?, nombre=?,dni=?,rol=? where id=?";
               pst = Conexion.prepareStatement(SQL);
               pst.setString(1, usuario.getEmail());
               pst.setString(2, usuario.getPassword());
               pst.setString(3, usuario.getNombre());
               pst.setString(4, usuario.getDni());
               pst.setString(5, usuario.getRol());
               pst.setInt(6, usuario.getId());
               int res= 0;
               res = pst.executeUpdate();
               if(res > 0){
                   resultado = true;
               }else{
                   resultado = false ;
               }
               
           }else{
               System.out.println("Error en la conexión a la DB");
           }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            ConexionDB.getInstancia().close(Conexion);
        }
    return resultado;
    }
    
    //eliminar
        public boolean eliminar (Usuario usuario){
        boolean resultado = false;
        Connection Conexion = null;
        try {
               Conexion = ConexionDB.getInstancia().getConnection();
           if(Conexion != null){
               String SQL = "Delete from usuario where id=?";
               pst = Conexion.prepareStatement(SQL);
               pst.setInt(1, usuario.getId());
               int res= 0;
               res = pst.executeUpdate();
               if(res > 0){
                   resultado = true;
               }else{
                   resultado = false ;
               }
               
           }else{
               System.out.println("Error en la conexión a la DB");
           }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            ConexionDB.getInstancia().close(Conexion);
        }
    return resultado;
    }
     
     //Listar
     public List<Usuario>  getAll(){
         List<Usuario> listado  = new ArrayList<Usuario>();
         Usuario usuario; 
         Connection Conexion = null;
            try {
               Conexion = ConexionDB.getInstancia().getConnection();
                if(Conexion != null){
                   String SQL = "select * from usuario";
                   pst = Conexion.prepareStatement(SQL);                   
                   rst = pst.executeQuery(); // se guarda el resutlado del select aqui                   
                    while (rst.next()) {
                        usuario = new Usuario();
                        usuario.setId(rst.getInt("id"));
                        usuario.setEmail(rst.getString("email"));
                        usuario.setPassword(rst.getString("password"));    
                        usuario.setNombre(rst.getString("nombre"));                        
                        usuario.setDni(rst.getString("dni"));    
                        usuario.setRol(rst.getString("rol"));
                        listado.add(usuario);
                    }
               }else{
                   System.out.println("Error en la conexión a la DB");
               }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            ConexionDB.getInstancia().close(Conexion);
        }
            return listado;
     }
     
     public Usuario get(Integer id){          
         Usuario usuario = new Usuario();
         Connection Conexion = null;
            try {
               Conexion = ConexionDB.getInstancia().getConnection();
                if(Conexion != null){
                   String SQL = "select * from usuario where id=?";
                   pst = Conexion.prepareStatement(SQL);
                   pst.setInt(1, id);
                   
                   rst = pst.executeQuery(); // se guarda el resutlado del select aqui
                   
                    while (rst.next()) {                        
                        usuario.setId(rst.getInt("id"));
                        usuario.setEmail(rst.getString("email"));
                        usuario.setPassword(rst.getString("password"));    
                        usuario.setNombre(rst.getString("nombre"));                        
                        usuario.setDni(rst.getString("dni"));    
                        usuario.setRol(rst.getString("rol"));
                    }
               }else{
                   System.out.println("Error en la conexión a la DB");
               }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            ConexionDB.getInstancia().close(Conexion);
        }
            return usuario;
    
    }
}

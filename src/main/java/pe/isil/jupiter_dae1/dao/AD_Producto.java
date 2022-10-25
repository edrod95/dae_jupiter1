
package pe.isil.jupiter_dae1.dao;

import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import pe.isil.jupiter_dae1.model.Producto;
/**
 *
 * @author BSJF
 */
public class AD_Producto {
    
    private PreparedStatement pst = null;
    private ResultSet rst; // obtiene los datos de la db, select
    
    public boolean insertar (Producto producto){
        boolean resultado = false;
        Connection Conexion = null;
        try {
               Conexion = ConexionDB.getInstancia().getConnection();
           if(Conexion != null){
               String SQL = "INSERT INTO producto (nombre, precio,stock,categoria) VALUES(?,?,?,?)";
               pst = Conexion.prepareStatement(SQL);
               pst.setString(1, producto.getNombre());                             
               pst.setDouble(2, producto.getPrecio());
               pst.setInt(3, producto.getStock());
               pst.setString(4, producto.getCategoria());
               
               int res= 0;
               res = pst.executeUpdate();
               if(res > 0){
                   resultado = true;
               }else{
                   resultado = false ;
               }
               
           }else{
               System.out.println("Error en la conexión a la DB - insertar producto");
           }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            ConexionDB.getInstancia().close(Conexion);
        }
    return resultado;
    }
    
    //actualizar
     public boolean actualizar (Producto producto){
        boolean resultado = false;
        Connection Conexion = null;
        try {
               Conexion = ConexionDB.getInstancia().getConnection();
           if(Conexion != null){
               String SQL = "UPDATE producto set nombre=?, precio=?,stock=?, categoria=? where id=?";
               pst = Conexion.prepareStatement(SQL);
               pst.setString(1, producto.getNombre());               
               pst.setDouble(2, producto.getPrecio());
               pst.setInt(3, producto.getStock());
               pst.setString(4, producto.getCategoria());               
               pst.setInt(5, producto.getId());
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
        public boolean eliminar (Integer id){
        boolean resultado = false;
        Connection Conexion = null;
        try {
               Conexion = ConexionDB.getInstancia().getConnection();
           if(Conexion != null){
               String SQL = "Delete from producto where id=?";
               pst = Conexion.prepareStatement(SQL);
               pst.setInt(1, id);
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
     public List<Producto>  getAll(){
         List<Producto> listado  = new ArrayList<Producto>();
         Producto producto; 
         Connection Conexion = null;
            try {
               Conexion = ConexionDB.getInstancia().getConnection();
                if(Conexion != null){
                   String SQL = "select * from producto";
                   pst = Conexion.prepareStatement(SQL);                   
                   rst = pst.executeQuery(); 
                    while (rst.next()) {
                        producto = new Producto();                        
                        producto.setId(rst.getInt("id"));                      
                        producto.setNombre(rst.getString("nombre"));
                        producto.setPrecio(rst.getDouble("precio"));
                        producto.setStock(rst.getInt("stock"));
                        producto.setCategoria(rst.getString("categoria"));
                        listado.add(producto);
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
     
     public Producto get(Integer id){          
         Producto producto = new Producto();
         Connection Conexion = null;
            try {
               Conexion = ConexionDB.getInstancia().getConnection();
                if(Conexion != null){
                   String SQL = "select * from producto where id=?";
                   pst = Conexion.prepareStatement(SQL);
                   pst.setInt(1, id);
                   
                   rst = pst.executeQuery(); 
                   
                    while (rst.next()) {
                        producto.setId(rst.getInt("id"));                        
                        producto.setNombre(rst.getString("nombre"));                        
                        producto.setPrecio(rst.getDouble("precio"));
                        producto.setStock(rst.getInt("stock"));
                        producto.setCategoria(rst.getString("categoria"));
                    }
               }else{
                   System.out.println("Error en la conexión a la DB");
               }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            ConexionDB.getInstancia().close(Conexion);
        }
            return producto;
    
    }
}

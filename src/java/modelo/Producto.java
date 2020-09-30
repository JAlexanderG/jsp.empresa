/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author JORGE
 */
public class Producto {
    private int idProducto;
    private String producto;
    private int idMarca;
    private String descripcion;
    private float precio_costo;
    private float precio_venta;
    private int existencia;
    Conexion cn;
    
    public Producto(){
    }

    public Producto(int idProducto, String producto, int idMarca, String descripcion, float precio_costo, float precio_venta, int existencia) {
        this.idProducto = idProducto;
        this.producto = producto;
        this.idMarca = idMarca;
        this.descripcion = descripcion;
        this.precio_costo = precio_costo;
        this.precio_venta = precio_venta;
        this.existencia = existencia;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    public int getIdMarca() {
        return idMarca;
    }

    public void setIdMarca(int idMarca) {
        this.idMarca = idMarca;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public float getPrecio_costo() {
        return precio_costo;
    }

    public void setPrecio_costo(float precio_costo) {
        this.precio_costo = precio_costo;
    }

    public float getPrecio_venta() {
        return precio_venta;
    }

    public void setPrecio_venta(float precio_venta) {
        this.precio_venta = precio_venta;
    }

    public int getExistencia() {
        return existencia;
    }

    public void setExistencia(int existencia) {
        this.existencia = existencia;
    }
   
     public int agregar(){
       int retorno = 0;
       try{
          PreparedStatement parametro;
          cn = new Conexion();
          String query = "insert into productos(producto,idMarca,descripcion,precio_costo,precio_venta,existencia) Values(?,?,?,?,?,?)";
          cn.abrir_conexion();
          parametro = (PreparedStatement)cn.conexionBD.prepareStatement(query);
          parametro.setString(1, getProducto());
          parametro.setInt(2, getIdMarca());
          parametro.setString(3, getDescripcion());
          parametro.setFloat(4, getPrecio_costo());
          parametro.setFloat(5, getPrecio_venta());
          parametro.setInt(6, getExistencia());
          retorno = parametro.executeUpdate(); // si aqui se ejecuta el query exitosamente aqui va enviar uno, sino un cero
       }catch(SQLException ex){
           System.out.println(ex.getMessage());
        }
        return retorno;
  }
     
     
     public DefaultTableModel leer(){
    DefaultTableModel tabla = new DefaultTableModel();
    
    try{
        cn = new Conexion();
        String query = "select p.idProducto as id, p.producto, m.marca, m.idMarca, p.descripcion, p.precio_costo, p.precio_venta, p.existencia from productos p, marcas m where p.idMarca = m.idMarca order by p.idProducto ASC";
        cn.abrir_conexion();
        ResultSet consulta = cn.conexionBD.createStatement().executeQuery(query);
        String encabezado[]={"id","producto","marca","idMarca","descripcion","precio_costo","precio_venta","existencia"};
        tabla.setColumnIdentifiers(encabezado);
        //obtine los  elemento de la consulta query
        String datos[] = new String[8];
        // consulta tiene los resultados de la variable query
        while(consulta.next()){
            datos[0] = consulta.getString("id");
            datos[1] = consulta.getString("producto");
            datos[2] = consulta.getString("marca");
            datos[3] = consulta.getString("idMarca");
            datos[4] = consulta.getString("descripcion");
            datos[5] = consulta.getString("precio_costo");
            datos[6] = consulta.getString("precio_venta");
            datos[7]= consulta.getString("existencia");
            
            tabla.addRow(datos); //agrega los registros a la tabal            
        }
        cn.cerrar_conexion();
    }catch(SQLException ex){
        System.out.println(ex.getMessage());
    }
    
    return tabla;
    }
    
     
    public int modificar(){
         int retorno = 0;
     
     try{
         PreparedStatement parametro;
         cn = new Conexion();
         String query = "update productos set producto=?, idMarca=?, descripcion=?, precio_costo=?, precio_venta=?, existencia=? where idProducto = ?;";
         cn.abrir_conexion();
         parametro = (PreparedStatement)cn.conexionBD.prepareStatement(query);
         parametro.setString(1, getProducto());
         parametro.setInt(2, getIdMarca());         
         parametro.setString(3, getDescripcion());
         parametro.setFloat(4, getPrecio_costo());
         parametro.setFloat(5, getPrecio_venta());
         parametro.setInt(6, getExistencia());
         parametro.setInt(7, this.getIdProducto());         
         retorno = parametro.executeUpdate();
         cn.cerrar_conexion();
     }catch(SQLException ex){
           System.out.println(ex.getMessage());
     }
     
    return retorno; 
    }
     
   
    public int eliminar(){
     int retorno = 0;
     
     try{
         PreparedStatement parametro;
         cn = new Conexion();
         String query = "delete from productos where idProducto = ?;";
         cn.abrir_conexion();
         parametro = (PreparedStatement)cn.conexionBD.prepareStatement(query);
         parametro.setInt(1, this.getIdProducto());
         retorno = parametro.executeUpdate();
         cn.cerrar_conexion();
     }catch(SQLException ex){
           System.out.println(ex.getMessage());
     }
     
    return retorno; 
    }
     
}

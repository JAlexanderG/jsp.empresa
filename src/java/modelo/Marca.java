/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;
import java.sql.ResultSet;
import java.sql.SQLException; 
import java.util.HashMap;
/**
 *
 * @author JORGE
 */
public class Marca {
    private int idMarca;
    private String marca;
    Conexion cn;
    
    public Marca() {
    }

    public Marca(int idMarca, String marca) {
        this.idMarca = idMarca;
        this.marca = marca;
    }

    public int getIdMarca() {
        return idMarca;
    }

    public void setIdMarca(int idMarca) {
        this.idMarca = idMarca;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }
    
    public HashMap drop_marca(){
         HashMap <String,String> drop = new HashMap();
         try{
          String query = "Select idMarca as id, marca from marcas;";
          cn = new Conexion();
          cn.abrir_conexion();
          ResultSet consulta = cn.conexionBD.createStatement().executeQuery(query);
          while (consulta.next()){
             drop.put(consulta.getString("id"), consulta.getString("marca"));
          }
          cn.cerrar_conexion();
         }catch(SQLException ex){
             System.out.println(ex.getMessage());
         }
         
         return drop;
    }
    
}

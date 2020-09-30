<%-- 
    Document   : index
    Created on : 23-sep-2020, 14:51:09
    Author     : JORGE
--%>
<%@page import="java.util.HashMap" %>
<%@page import="modelo.Marca" %>
<%@page import="modelo.Producto" %>
<%@page import="javax.swing.table.DefaultTableModel" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Productos</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">    
    </head>
    <body>
        <font face="Helv Children" size="5" color="#A50021">
        <h6 align="center">Desarrollo Web, Parcial 2</h6>
        <h1 align="center">PRODUCTOS</h1>
        </font>
        
        <br/>
        <div class="container">
        <font face="Courgette" size="6">    
        <button type="button" class="btn btn-dark btn-lg" data-toggle="modal" data-target="#modal_empleados" onclick="limpiar()">Nuevo</button>
        </font>
        <br/> <br/>
         <div class="modal" id="modal_empleados">
          <div class="modal-dialog">
           <div class="modal-content">
            
        <!-- Modal Header -->
        <div class="modal-header">
          <h4 class="modal-title">Nuevo producto</h4>
          <button type="button" class="close" data-dismiss="modal">&times;</button>
        </div>
               
        <!-- Modal body -->
        <div class="modal-body">
        <font face="Arial" size="3" color="#D07C00">
          <form action="servlet_producto" method="post" class="form-group">
                <label for="lbl_id"><b>ID</b></label>
                <input type="text" name="txt_id" id="txt_id" class="form-control" value="0" readonly>
                <label for="lbl_producto"><b>Producto:</b></label>
                <input type="text" name="txt_producto" id="txt_producto" class="form-control" placeholder="Ej. Producto" required>
                
                <label for="lbl_marca"><b>Marca:</b></label>
                <select name="drop_marca" id="drop_marca" class="form-control">
                <option value="0">--- Seleccione ---</option>    
                    <%
                     System.out.println("Hola mundo");
                  
                     Marca marca = new Marca();
                     HashMap<String,String> drop = marca.drop_marca();
                     for (String i:drop.keySet()){
                         out.println("<option value='" + i + "'>" + drop.get(i) + "</option>");
                     }
                     %>
                </select>

                <label for="lbl_descripcion"><b>Descripcion:</b></label>
                <input type="text" name="txt_descripcion" id="txt_descripcion" class="form-control" placeholder="Ej. Descripcion modelo, etc." required>
                <label for="lbl_precio_costo"><b>Precio costo:</b></label>
                <input type="text" name="txt_precio_costo" id="txt_precio_costo" class="form-control" placeholder="Ej. 100.00" required>
                <label for="lbl_precio_venta"><b>Precio venta:</b></label>
                <input type="text" name="txt_precio_venta" id="txt_precio_venta" class="form-control" placeholder="Ej. 100.00" required>
                <label for="lbl_existencia"><b>Existencia:</b></label>
                <input type="number" name="txt_existencia" id="txt_existencia" class="form-control" placeholder="Ej. 10" required>
                    
                <br>
                <button name="btn_agregar" id="btn_agregar" value="agregar" class="btn btn-success btn-lg">Agregar</button>
                <button name="btn_modificar" id="btn_modificar" value="modificar" class="btn btn-info btn-lg">Modificar</button>
                 
				<br>
            </form>        
           </font>
          </div> 
   </div>         
  </div>
 </div>
                
  <font face="Tw Cen MT" size="5" color="skyblue">            
   <table class="table table-bordered" bgcolor="#C1D3ED">
      <thead>
       <tr bgcolor="#05164B">
         <th><p class="text-white">Producto</p></th>
         <th><p class="text-white">Marca</p></th>
         <th><p class="text-white">Descripcion</p></th>
         <th><p class="text-white">Precio costo</p></th>
         <th><p class="text-white">Precio venta</p></th>
         <th><p class="text-white">Existencia</p></th>
        </tr>
      </thead> 
      
      <tbody id="tbl_productos">
     
        <%
      Producto producto = new Producto();
      DefaultTableModel tabla = new DefaultTableModel();
      tabla = producto.leer();
      for(int t=0; t<tabla.getRowCount(); t++){
        
        out.println("<tr data-id="+ tabla.getValueAt(t, 0) +" data-idp="+ tabla.getValueAt(t, 3)+"  >");
        out.println("<td>" + tabla.getValueAt(t, 1) + "</td>");
        out.println("<td>" + tabla.getValueAt(t, 2) + "</td>");
        out.println("<td>" + tabla.getValueAt(t, 4) + "</td>");
        out.println("<td>" + tabla.getValueAt(t, 5) + "</td>");
        out.println("<td>" + tabla.getValueAt(t, 6) + "</td>");
        out.println("<td>" + tabla.getValueAt(t, 7) + "</td>");
        out.println("</tr>");
      }
      %>
      
    </tbody>
      
    </table> 
      </font>
 </div>
        
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js" integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8shuf57BaghqFfPlYxofvL8/KUEfYiJOMMV+rV" crossorigin="anonymous"></script>
        <script type="text/javascript">
         function limpiar(){
                $("#txt_id").val(0);
                $("#txt_producto").val('');
                $("#drop_marca").val(1);
                $("#txt_descripcion").val('');
                $("#txt_precio_costo").val('');
                $("#txt_precio_venta").val('');
                $("#txt_existencia").val('');
            }  
    
         $('#tbl_productos').on('click','tr td',function(evt){
                
                var target,id,idp,producto,descripcion,precio_costo,precio_venta,existencia;
                target = $(event.target);
                id = target.parent().data('id');
                idp = target.parent().data('idp');
                producto = target.parent("tr").find("td").eq(0).html();
                descripcion = target.parent("tr").find("td").eq(2).html();
                precio_costo = target.parent("tr").find("td").eq(3).html();
                precio_venta = target.parent("tr").find("td").eq(4).html();
                existencia = target.parent("tr").find("td").eq(5).html();
            
                $("#txt_id").val(id);
                $("#txt_producto").val(producto);
                $("#drop_marca").val(idp);
                $("#txt_descripcion").val(descripcion);
                $("#txt_precio_costo").val(precio_costo);
                $("#txt_precio_venta").val(precio_venta);
                $("#txt_existencia").val(existencia);
                $("#modal_empleados").modal('show');            
            });
        </script>
    </body>
</html>

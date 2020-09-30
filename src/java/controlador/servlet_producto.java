/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.Producto;

/**
 *
 * @author JORGE
 */
public class servlet_producto extends HttpServlet {
    Producto producto;
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            
            producto = new Producto(Integer.valueOf(request.getParameter("txt_id")),request.getParameter("txt_producto"),Integer.valueOf(request.getParameter("drop_marca")),request.getParameter("txt_descripcion"),Float.valueOf(request.getParameter("txt_precio_costo")),Float.valueOf(request.getParameter("txt_precio_venta")),Integer.valueOf(request.getParameter("txt_existencia")));
            
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Producto</title>");            
            out.println("</head>");
            
            if("agregar".equals(request.getParameter("btn_agregar"))){
               if(producto.agregar()>0){
                 out.println("<h1>Ingreso Exitoso</h1>");
                 response.sendRedirect("index.jsp");
                   }else{
               out.println("<h1>Dato no ingresado</h1>");
                }
            }
            
            if("modificar".equals(request.getParameter("btn_modificar"))){
                if(producto.modificar()>0){
                    response.sendRedirect("index.jsp");
                }else{
                    out.println("<h1> DATO NO MODIFICADO </h1>");
                }
            }
            
            if("eliminar".equals(request.getParameter("btn_eliminar"))){
                if(producto.eliminar()>0){
                    response.sendRedirect("index.jsp");
                }else{
                    out.println("<h1> DATO NO ELIMINADO </h1>");
                }
            }
            
            out.println("<h1>Servlet servlet_producto at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}

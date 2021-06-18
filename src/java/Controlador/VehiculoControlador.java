/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import ModeloDAO.VehiculoDAO;
import Modelo.VehiculoVO;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author USER
 */
@WebServlet(name = "VehiculoControlador", urlPatterns = {"/Vehiculo1"})
public class VehiculoControlador extends HttpServlet {

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

        //recibir datos de las vistas
        int opcion = Integer.parseInt(request.getParameter("opcion"));
        String vehplaca = request.getParameter("textPlaca");
        String datId = request.getParameter("textDatos");
        String catId = request.getParameter("textCategoria");
        String vehmodelo = request.getParameter("textModelo");
        String vehMarca = request.getParameter("textMarca");
        String vehEstado = request.getParameter("textEstado");
        String vehPrecio = request.getParameter("textPrecio");

        VehiculoVO vehVO = new VehiculoVO(vehplaca, datId, catId, vehmodelo, vehMarca, vehEstado, vehPrecio);
        VehiculoDAO vehDAO = new VehiculoDAO(vehVO);

        switch (opcion) {
            case 1:
                if (vehDAO.agregarRegistro()){
                    request.setAttribute("mensajeExito", "El vehiculo se registro correctamente");

                } else {
                    request.setAttribute("mensajeError", "El vehiculo No se registró correctamente");
                }
                request.getRequestDispatcher("registrarVehiculo.jsp").forward(request, response);
                break;

            case 2: //Actualizar registro
                if (vehDAO.actualizarRegistro()) {
                    request.setAttribute("mensajeExito", "El vehiculo se registro exitosamente");

                } else {
                    request.setAttribute("mensajeError", "El vehiculo No se actualió correctamente");
                }
                request.getRequestDispatcher("actualizarVehiculo.jsp").forward(request, response);
                break;

            case 3:
                vehVO = vehDAO.consultarPlaca1(vehplaca);
                if (vehVO !=null) {
                    request.setAttribute("vehiculoConsultado", vehVO);
                    request.getRequestDispatcher("actualizarVehiculo.jsp").forward(request, response);

                } else {
                    request.setAttribute("mensajeError", "El vehiculo No existe");
                    request.getRequestDispatcher("consultarVehiculo.jsp").forward(request, response);
                }
                break;

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

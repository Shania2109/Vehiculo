/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.UsuarioVO;
import ModeloDAO.RolDAO;
import ModeloDAO.UsuarioDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author USER
 */
@WebServlet(name = "UsuarioControlador", urlPatterns = {"/Usuario"})
public class UsuarioControlador extends HttpServlet {

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

        //1.Recibir datos de las vistas
        int opcion = Integer.parseInt(request.getParameter("opcion"));
        String usuId = request.getParameter("textId");
        String usuLogin = request.getParameter("textUsuario");
        String usuPassword = request.getParameter("textClave");

        //2.Quien tiene los datos de forma segura 
        UsuarioVO usuVO = new UsuarioVO(usuId, usuLogin, usuPassword);
        //3.Quien hace las operaciones? DAO 
        UsuarioDAO usuDAO = new UsuarioDAO(usuVO);
        //4.Administrar operaciones 
        switch (opcion) {
            case 1: //Agregar Registro 
                if (usuDAO.agregarRegistro()) {

                    request.setAttribute("mensajeExito", "El usuario se registro correctamente");

                } else {

                    request.setAttribute("mensajeError", "El usuario NO se registro correctamente");

                }
                request.getRequestDispatcher("registrarUsuario.jsp").forward(request, response);
                break;

            case 2: //Actualizar Registro 
                if (usuDAO.actualizarRegistro()) {

                    request.setAttribute("mensajeExito", "El usuario se actualizo correctamente");

                } else {

                    request.setAttribute("mensajeError", "El usuario NO se actualizo correctamente");

                }
                request.getRequestDispatcher("actualizarUsuario.jsp").forward(request, response);
                break;

            case 3: //Iniciar Sesion 
                if (usuDAO.iniciarSesion(usuLogin, usuPassword)) {

                    HttpSession miSesion = request.getSession(true);
                    RolDAO rolDAO = new RolDAO();
                     usuVO = new UsuarioVO(usuId, usuLogin, usuPassword);
                    miSesion.setAttribute("datosUsuario", usuVO);
                    ArrayList<UsuarioVO> listaRoles = rolDAO.rol(usuLogin);

                    for (int i = 0; i < listaRoles.size(); i++) {
                        usuVO = listaRoles.get(i);
                    }

                    if (listaRoles.size() > 1) {
                        request.getRequestDispatcher("menu.jsp").forward(request, response);
                    } else if (usuVO.getRol().equals("Vendedor")) {
                        request.getRequestDispatcher("Vendedor.jsp").forward(request, response);
                    } else {
                        request.getRequestDispatcher("Comprador.jsp").forward(request, response);
                    }

                   
                    miSesion.setAttribute("rol", listaRoles);

                } else {

                    request.setAttribute("mensajeError", "El usuario y/o la contraseña son incorrectos");
                    request.getRequestDispatcher("index.jsp").forward(request, response);

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

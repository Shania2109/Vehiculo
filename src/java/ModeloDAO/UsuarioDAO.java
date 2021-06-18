/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModeloDAO;

import Modelo.UsuarioVO;
import Util.Conexion;
import Util.Crud;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author USER
 */
public class UsuarioDAO extends Conexion implements Crud {

    //1.Declarar Variables u objetos 
    private Connection conexion;
    private PreparedStatement puente;
    private ResultSet mensajero;

    private boolean operacion = false;
    private String sql;

    private String usuId = "", usuLogin = "", usuPassword = "";

    //2.metodo principal para recibir lols datos del VO 
    public UsuarioDAO(UsuarioVO usuVO) {
        super();

        try {
            //3.Conectarse    
            conexion = this.obtenerConexion();
            //4.Traer al DAO los datos del VO para hacer la operacion
            usuId = usuVO.getUsuId();
            usuLogin = usuVO.getUsuLogin();
            usuPassword = usuVO.getUsuPassword();

        } catch (Exception e) {

            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, e);
        }

    }

    @Override
    public boolean agregarRegistro() {
        try {
            sql = "Insert into usuario(USULOGIN, USUPASSWORD) Values (?,?)";
            puente = conexion.prepareStatement(sql);
            puente.setString(1, usuLogin);
            puente.setString(2, usuPassword);
            puente.executeUpdate();
            operacion = true;

        } catch (SQLException e) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            try {
                this.cerrarConexion();
            } catch (SQLException e) {
                Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, e);
            }
        }

        return operacion;
    }

    @Override
    public boolean actualizarRegistro() {
        try {
            sql = "Update usuario set USULOGIN=? , USUPASSWORD=? where USUID=?";
            puente = conexion.prepareStatement(sql);
            puente.setString(1, usuLogin);
            puente.setString(2, usuPassword);
            puente.setString(3, usuId);
            puente.executeUpdate();
            operacion = true;

        } catch (SQLException e) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            try {
                this.cerrarConexion();
            } catch (SQLException e) {
                Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, e);
            }
        }

        return operacion;
    }

    @Override
    public boolean eliminarRegistro() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public boolean iniciarSesion(String usuario, String clave) {
        //conexion a la BD
        try {
            conexion = this.obtenerConexion();
            sql = "select * from usuario where USULOGIN=? and USUPASSWORD=?";
            puente = conexion.prepareStatement(sql);
            puente.setString(1, usuario);
            puente.setString(2, clave);
            mensajero = puente.executeQuery();
            if (mensajero.next()) {
                operacion = true;
            }

        } catch (SQLException e) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            try {
                this.cerrarConexion();
            } catch (Exception e) {
                Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, e);
            }
        }
        return operacion;
    }

    public ArrayList<UsuarioVO> rol(String usuario) {

        UsuarioVO usuVO = null;
        ArrayList<UsuarioVO> listarRoles = new ArrayList<>();

        try {
            conexion = this.obtenerConexion();
            sql = "Select usuario.USUID, rol.ROLTIPO\n"
                  +"From rol Inner join\n"
                  +"usuario_rol on rol.ROLID = usuario_rol.ROLID inner join\n"
                  +"usuario On usuario_rol.USUID = usuario.USUID\n"
                  +"Where usuario.USULOGIN = ?";
            puente = conexion.prepareStatement(sql); 
            puente.setString(1, usuario);
            mensajero= puente.executeQuery();
            
            while(mensajero.next()){
                
                usuVO= new UsuarioVO(mensajero.getString(1), mensajero.getString(2));
                listarRoles.add(usuVO);
            }

        } catch (Exception e) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, e);
            
        }
        return listarRoles;

    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModeloDAO;

import Modelo.UsuarioVO;
import Util.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author USER
 */
public class RolDAO extends Conexion{
     private Connection conexion;
    private PreparedStatement puente;
    private ResultSet mensajero;
    
    private String sql;
    
    public ArrayList<UsuarioVO> rol(String usuario) {

       
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
                
                UsuarioVO usuVO= new UsuarioVO(mensajero.getString(1), mensajero.getString(2));
                listarRoles.add(usuVO);
            }

        } catch (Exception e) {
            Logger.getLogger(RolDAO.class.getName()).log(Level.SEVERE, null, e);
            
        }
        return listarRoles;

    }
    
}

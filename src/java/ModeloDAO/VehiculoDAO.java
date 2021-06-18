/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModeloDAO;


import Modelo.VehiculoVO;
import java.sql.Connection;
import Util.Conexion;
import Util.Crud;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author USER
 */
public class VehiculoDAO extends Conexion implements Crud {
    
    private Connection conexion;
    private PreparedStatement puente;
    private ResultSet mensajero;
    
    private boolean operacion = false;
    private String sql;
    
    private String vehPlaca="", datId="", catId="", vehModelo="", vehMarca="", vehEstado="", vehPrecio="";

    public VehiculoDAO() {
    }
    
    public VehiculoDAO(VehiculoVO vehVO){
        super();
        
        try {
            conexion = this.obtenerConexion();
            
            vehPlaca = vehVO.getVehPlaca();
            datId = vehVO.getDatId();
            catId = vehVO.getCatId();
            vehModelo = vehVO.getVehModelo();
            vehMarca = vehVO.getVehMarca();
            vehEstado = vehVO.getVehEstado();
            vehPrecio = vehVO.getVehPrecio();
            
        } catch (Exception e) {
            
            Logger.getLogger(VehiculoDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        
    }
    
    

    @Override
    public boolean agregarRegistro() {
        try {
            sql = "Insert into vehiculo Values (?,?,?,?,?,?,?)";
            puente = conexion.prepareStatement(sql);
            puente.setString(1, vehPlaca);
            puente.setString(2, datId);
            puente.setString(3, catId);
            puente.setString(4, vehModelo);
            puente.setString(5, vehMarca);
            puente.setString(6, vehEstado);
            puente.setString(7, vehPrecio);
            puente.executeUpdate();
            operacion = true;

        } catch (SQLException e) {
            Logger.getLogger(VehiculoDAO.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            try {
                this.cerrarConexion();
            } catch (SQLException e) {
                Logger.getLogger(VehiculoDAO.class.getName()).log(Level.SEVERE, null, e);
            }
        }

        return operacion;
    }

   
@Override
    public boolean actualizarRegistro() {
        try {
            //para mas seguro mejor estructurado
            //crear sentencias
            sql = "update vehiculo set DATID=? ,CATID=?, VEHMODELO=?, VEHMARCA=?, VEHESTADO=?, VEHPRECIO=? Where VEHPLACA=?";
            //crear puente para conexion establecida
            puente = conexion.prepareStatement(sql);

            //preparando bien la sentencia para que no sea manipulable 
            puente.setString(1, datId);
            puente.setString(2, catId);
            puente.setString(3, vehModelo);
            puente.setString(4, vehMarca);
            puente.setString(5, vehEstado);
            puente.setString(6, vehPrecio);
            puente.setString(7, vehPlaca);
            puente.executeUpdate();
            operacion = true;

        } catch (SQLException e) {
            //si no se executeUp pasa a esto y pasa a false
            Logger.getLogger(VehiculoDAO.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            try {
                this.cerrarConexion();
            } catch (SQLException e) {

                Logger.getLogger(VehiculoDAO.class.getName()).log(Level.SEVERE, null, e);

            }
        }

        return operacion;
    }

    @Override
    public boolean eliminarRegistro() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public VehiculoVO consultarPlaca1(String placa) {

        VehiculoVO vehVO = null;

        try {
            conexion = this.obtenerConexion();
            sql = "Select * from vehiculo where VEHPLACA=?";
            puente = conexion.prepareStatement(sql);
            puente.setString(1, placa);
            mensajero = puente.executeQuery();
            while (mensajero.next()) {

                vehVO = new VehiculoVO(placa, mensajero.getString(2), mensajero.getString(3),
                        mensajero.getString(4), mensajero.getString(5), mensajero.getString(6),
                        mensajero.getString(7));

            }
        } catch (Exception e) {
            Logger.getLogger(VehiculoDAO.class.getName()).log(Level.SEVERE, null, e);

        } finally {
            try {
                //this.cerrarConexion();
            } catch (Exception e) {

                Logger.getLogger(VehiculoDAO.class.getName()).log(Level.SEVERE, null, e);

            }
        }

        return vehVO;
    }

    public ArrayList<VehiculoVO> listar() {
        ArrayList<VehiculoVO> listaVehiculos = new ArrayList<>();
        try {
            conexion = this.obtenerConexion();
            sql = "Select * From vehiculo";
            puente = conexion.prepareStatement(sql);
            mensajero = puente.executeQuery();
            
            while (mensajero.next()) {

                VehiculoVO vehVO = new VehiculoVO(mensajero.getString(1), mensajero.getString(2),
                        mensajero.getString(3), mensajero.getString(4), mensajero.getString(5),
                        mensajero.getString(6), mensajero.getString(7));
                
                listaVehiculos.add(vehVO);

            }

        } catch (Exception e) {
             Logger.getLogger(VehiculoDAO.class.getName()).log(Level.SEVERE, null, e);

        } finally {
            try {
                this.cerrarConexion();
            } catch (SQLException e) {

                Logger.getLogger(VehiculoDAO.class.getName()).log(Level.SEVERE, null, e);

            }
        }

        return listaVehiculos;
    }


}

  



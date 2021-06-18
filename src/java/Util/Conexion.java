/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author USER
 */
public class Conexion {

    //1declarar variables y objetos
    private String driver, user, password, nombreBd, urlBd;
    private Connection conexion;

    //2metodo consultor principal, primero asignar valores a los elementos
    public Conexion() {
        driver = "com.mysql.jdbc.Driver";
        user = "root";
        password = "";
        nombreBd = "ventavehiculo2242760";
        urlBd = "jdbc:mysql://localhost:3306/" + nombreBd;

        //try and catch 3.me conecto
        try {

            Class.forName(driver).newInstance();
            conexion = DriverManager.getConnection(urlBd, user, password);
            //para comprobar
            System.out.println("Conexion Ok");
        } catch (Exception e) {

            System.out.println("Error al conectarse " + e.toString());
        }
    }

    public Connection obtenerConexion() {

        return conexion;
    }

    public Connection cerrarConexion() throws SQLException {

        conexion.close();
        conexion = null;
        return conexion;
    }
    
    public static void main(String[] args) {
        new Conexion();
    }
}

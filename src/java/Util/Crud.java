/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Util;

/**
 *
 * @author USER
 */

//interface solo se declara mas no se hace logica
public interface Crud {
    
    public abstract boolean agregarRegistro();
    public abstract boolean actualizarRegistro();
    public abstract boolean eliminarRegistro();
    
}
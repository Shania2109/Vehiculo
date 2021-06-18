/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

/**
 *
 * @author USER
 */
public class CategoriaVO {
    
    private String CatId, CatTipo;

    public CategoriaVO(String CatId, String CatTipo) {
        this.CatId = CatId;
        this.CatTipo = CatTipo;
    }
    
    
    public String getCatId() {
        return CatId;
    }

    public void setCatId(String CatId) {
        this.CatId = CatId;
    }

    public String getCatTipo() {
        return CatTipo;
    }

    public void setCatTipo(String CatTipo) {
        this.CatTipo = CatTipo;
    }
    
   
    
}

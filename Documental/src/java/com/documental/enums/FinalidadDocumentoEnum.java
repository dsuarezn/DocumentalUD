/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.documental.enums;

/**
 *
 * @author Alexander
 */
public enum FinalidadDocumentoEnum {
    APROBACION("APROBAR",1), VISUALIZACION("VER",2);
    
    
    FinalidadDocumentoEnum(String nombre, Integer codigo){
        this.nombre=nombre;
        this.codigo=codigo;
    }
    
    private String nombre;
    private Integer codigo;
    
    public Integer getCodigo(){return this.codigo;}
    public String  getNombreEstado(){return this.nombre;}
    
    public static String getNombrePorCodigo(Integer codigo){        
        for (FinalidadDocumentoEnum item : FinalidadDocumentoEnum.values()) {
            if(item.codigo == codigo){
                return item.getNombreEstado();
            }
        }
        return null;
    }
    
    
}

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
public enum EstadosDocumentoEnum {
    RADICADO("Radicado",1), ENTRAMITE("En tramite",2), CERRADO("Cerrado",10);
    
    
    EstadosDocumentoEnum(String nombre, Integer codigo){
        this.nombreEstado=nombre;
        this.codigoEstado=codigo;
    }
    
    private String nombreEstado;
    private Integer codigoEstado;
    
    public Integer getCodigo(){return this.codigoEstado;}
    public String  getNombreEstado(){return this.nombreEstado;}
}

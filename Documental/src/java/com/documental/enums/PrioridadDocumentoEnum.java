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
public enum PrioridadDocumentoEnum {
    NORMAL("Normal",1), URGENTE("Urgente",2), CRITICO("Critico",3);
    
    
    PrioridadDocumentoEnum(String nombre, Integer codigo){
        this.nombrePrioridad=nombre;
        this.codigoPrioridad=codigo;
    }
    
    private String nombrePrioridad;
    private Integer codigoPrioridad;
    
    public Integer getCodigo(){return this.codigoPrioridad;}
    public String  getNombreEstado(){return this.nombrePrioridad;}
}

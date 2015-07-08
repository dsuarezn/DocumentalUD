/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.documental.servicios;


import com.documental.bo.Tipo;
import java.util.List;

/**
 *
 * @author Alexander
 */
public interface ServicioTipo {
    
    public int getMaxId();
    public String salvarTipo(Tipo tipo);
    public Tipo consultarTipoPorId(Integer Id);
    public List<Tipo> consultarTodosTipos();
    public void borrarTipo(Tipo Id);
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.documental.servicios;

import com.documental.bo.DependenciaEmpleado;
import java.util.List;

/**
 *
 * @author DiegoM
 */
public interface ServicioDependenciaEmpleado {
    public List<DependenciaEmpleado> obtenerDependenciaEmpleado();           
    public String salvarDependenciaEmpleado(DependenciaEmpleado dependenciaEmpleado);
    public void borrarDependenciaEmpleado(DependenciaEmpleado dependenciaEmpleado);        
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.documental.servicios;

import com.documental.bo.DependenciaEmpleado;
import com.documental.bo.DependenciaEmpleadoPK;
import java.util.List;

/**
 *
 * @author Estudiantes
 */
public interface ServicioEmpleado {
    
    public String salvarEmpleado(DependenciaEmpleado empleado);
    public DependenciaEmpleado consultarEmpleado(DependenciaEmpleadoPK empleado);
    public List<DependenciaEmpleado> consultarTodosEmpleados();
    public void borrarEmpleado(DependenciaEmpleado empleado);
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.documental.servicios.impl;

import com.documental.bo.DependenciaEmpleado;
import com.documental.bo.DependenciaEmpleadoPK;
import com.documental.dao.DAOAbstractFactory;
import com.documental.dao.DAOFactory;
import com.documental.dao.EmpleadoDAO;
import com.documental.servicios.ServicioEmpleado;
import java.util.List;

/**
 *
 * @author Estudiantes
 */
public class ServicioEmpleadoImpl implements ServicioEmpleado {

    private EmpleadoDAO empleadoDAO;

    public ServicioEmpleadoImpl() {
        DAOFactory factoria = DAOAbstractFactory.getInstance();
        empleadoDAO = factoria.getEmpleadoDAO();
    }

    @Override
    public String salvarEmpleado(DependenciaEmpleado empleado) {
        return empleadoDAO.salvar(empleado);
    }

    @Override
    public DependenciaEmpleado consultarEmpleado(DependenciaEmpleadoPK empleado) {
        return empleadoDAO.buscarPorClave(empleado);
    }

    @Override
    public List<DependenciaEmpleado> consultarTodosEmpleados() {
        return empleadoDAO.buscarTodos();
    }

    @Override
    public void borrarEmpleado(DependenciaEmpleado empleado) {
        empleadoDAO.borrar(empleado);
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.documental.servicios.impl;

import com.documental.bo.DependenciaEmpleado;
import com.documental.dao.DAOAbstractFactory;
import com.documental.dao.DAOFactory;
import com.documental.dao.DependenciaEmpleadoDAO;
import com.documental.servicios.ServicioDependenciaEmpleado;
import java.util.List;

/**
 *
 * @author DiegoM
 */
public class ServicioDependenciaEmpleadoImpl implements ServicioDependenciaEmpleado{
    
    private DependenciaEmpleadoDAO dao = null;

    public ServicioDependenciaEmpleadoImpl() {
        DAOFactory factoria = DAOAbstractFactory.getInstance();
        dao = factoria.getDependenciaEmpleadoDAO();
    }

    @Override
    public List<DependenciaEmpleado> obtenerDependenciaEmpleado() {
        return dao.buscarTodos();
    }

    @Override
    public String salvarDependenciaEmpleado(DependenciaEmpleado dependenciaEmpleado) {
        return dao.salvar(dependenciaEmpleado);
    }

    @Override
    public void borrarDependenciaEmpleado(DependenciaEmpleado dependenciaEmpleado) {
        dao.borrar(dependenciaEmpleado);
    } 
}

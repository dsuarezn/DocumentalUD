/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.documental.servicios.impl;

import com.documental.bo.DependenciaDirector;
import com.documental.bo.DependenciaDirectorPK;
import com.documental.dao.DAOAbstractFactory;
import com.documental.dao.DAOFactory;
import com.documental.dao.DependenciaDirectorDAO;
import com.documental.servicios.ServicioDependenciaDirector;
import java.util.List;

/**
 *
 * @author DiegoM
 */
public class ServicioDependenciaDirectorImpl implements ServicioDependenciaDirector {

    private DependenciaDirectorDAO dao = null;

    public ServicioDependenciaDirectorImpl() {
        DAOFactory factoria = DAOAbstractFactory.getInstance();
        dao = factoria.getDependenciaDirectorDAO();
    }

    @Override
    public List<DependenciaDirector> obtenerDependenciaDirector() {
        return dao.buscarTodos();
    }
    
    @Override
    public String salvarDependenciaDirector(DependenciaDirector dependenciaDirector) {
        return dao.salvar(dependenciaDirector);
    }

    @Override
    public void borrarDependenciaDirector(DependenciaDirector dependenciaDirector) {
        dao.borrar(dependenciaDirector);
    }

    @Override
    public DependenciaDirector obtenerDependenciaDirectorPorId(DependenciaDirectorPK dependenciaId) {
        return dao.buscarPorClave(dependenciaId);
    }

  

  
}

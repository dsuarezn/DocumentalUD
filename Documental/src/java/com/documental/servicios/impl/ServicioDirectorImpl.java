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
import com.documental.dao.DirectorDAO;

import com.documental.servicios.ServicioDirector;
import java.util.List;

/**
 *
 * @author enrique
 */
public class ServicioDirectorImpl implements ServicioDirector{
    
    private DirectorDAO directorDAO;

    public ServicioDirectorImpl() {
         DAOFactory factoria = DAOAbstractFactory.getInstance();
        directorDAO = factoria.getDirectorDAO();
    }

    
    @Override
    public String salvarDirector(DependenciaDirector director) {
        return directorDAO.salvar(director);
    }

    @Override
    public DependenciaDirector consultarDirector(DependenciaDirectorPK director) {
        return directorDAO.buscarPorClave(director);
    }

    @Override
    public List<DependenciaDirector> consultarTodosDirectores() {
        return directorDAO.buscarTodos();
    }

    @Override
    public void borrarDirector(DependenciaDirector d) {
       directorDAO.borrar(d);
    }

    
}

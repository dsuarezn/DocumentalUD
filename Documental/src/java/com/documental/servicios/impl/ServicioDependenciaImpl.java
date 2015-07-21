/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.documental.servicios.impl;

import com.documental.bo.Dependencia;
import com.documental.dao.DAOAbstractFactory;
import com.documental.dao.DAOFactory;
import com.documental.dao.DependenciaDAO;
import com.documental.servicios.ServicioDependencia;
import java.util.List;

/**
 *
 * @author enrique
 */
public class ServicioDependenciaImpl implements ServicioDependencia {
    
    private DependenciaDAO dependenciaDAO=null;

    public ServicioDependenciaImpl() {
        DAOFactory factoria = DAOAbstractFactory.getInstance();
        dependenciaDAO = factoria.getDependenciaDAO();
    }
    
    
    @Override
    public int getCount() {
       return dependenciaDAO.getCount();
    }

    @Override
    public int getMaxId() {
       return dependenciaDAO.getMaxId();
    }

    @Override
    public String salvarDependencia(Dependencia dependencia) {
        return dependenciaDAO.salvar(dependencia);
    }

    @Override
    public String borrarDependencia(Dependencia dependencia) {
        return dependenciaDAO.borrar(dependencia);
    }

    @Override
    public List<Dependencia> buscarTodosDependencia() {
        return dependenciaDAO.buscarTodos();
    }

    @Override
    public Dependencia buscarPorClave(Integer id) {
        return dependenciaDAO.buscarPorClave(id);
    }

    @Override
    public List<Dependencia> buscarTodosDependenciaConDirector() {
        return dependenciaDAO.buscarDependenciasConDirector();
    }
    
    @Override
    public List<Dependencia> buscarDependenciasActivas(){
    return dependenciaDAO.buscarDependenciasActivas();
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.documental.dao;

import com.documental.bo.Dependencia;
import com.documental.bo.DependenciaDirector;
import com.documental.bo.DependenciaEmpleado;
import java.util.List;

/**
 *
 * @author enrique
 */
public interface DependenciaDAO extends GenericDAO<Dependencia, Integer>{
    
    public List<Dependencia> buscarDependenciasConDirector();
    public List<Dependencia> buscarDependenciasActivas();
    public DependenciaDirector buscarDependenciaDirector(Integer login);
    public DependenciaEmpleado buscarDependenciaEmpleado(Integer login);
}

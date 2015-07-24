/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.documental.servicios;

import com.documental.bo.DependenciaDirector;
import com.documental.bo.DependenciaDirectorPK;
import java.util.List;

/**
 *
 * @author DiegoM
 */
public interface ServicioDependenciaDirector {
    public List<DependenciaDirector> obtenerDependenciaDirector();    
    public String salvarDependenciaDirector(DependenciaDirector dependenciaDirector);
    public void borrarDependenciaDirector(DependenciaDirector dependenciaDirector);    
    public DependenciaDirector obtenerDependenciaDirectorPorId(DependenciaDirectorPK dependenciaId);
}

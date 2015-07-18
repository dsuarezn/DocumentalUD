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
 * @author enrique
 */
public interface ServicioDirector {
        
    public String salvarDirector(DependenciaDirector director);
    public DependenciaDirector consultarDirector(DependenciaDirectorPK director);
    public List<DependenciaDirector> consultarTodosDirectores();
    public void borrarDirector(DependenciaDirector director);
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.documental.servicios;

import com.documental.bo.Dependencia;
import java.util.List;

/**
 *
 * @author enrique
 */
public interface ServicioDependencia {
    public int getCount();
    public int getMaxId();
    public String salvarDependencia(Dependencia dependencia);
    public String borrarDependencia(Dependencia dependencia);
    public List<Dependencia> buscarTodosDependencia();    
    public List<Dependencia> buscarTodosDependenciaConDirector();    
    public Dependencia buscarPorClave(Integer id); 
    public List<Dependencia> buscarDependenciasActivas();
}

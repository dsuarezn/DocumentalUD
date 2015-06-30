/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.documental.servicios;

import com.documental.bo.Tarea;
import java.util.List;

/**
 *
 * @author DiegoM
 */
public interface ServicioTarea {
    
    public int getCount();
    public int getMaxId();
    public Tarea buscarPorEntity(Integer id);
    public List<Tarea> buscarPorEntityList(Integer id);
    public String salvarTarea(Tarea tarea);
    public String insertarNative(Integer id1, Integer id2);
    public String borrarTarea(Tarea tarea);
    public String borrarNative(Integer id);
    public List<Tarea> buscarTodosTarea();    
    public Tarea buscarPorClave(Integer id); 
}

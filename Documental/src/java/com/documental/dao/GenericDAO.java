/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.documental.dao;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author DiegoM
 */
public interface GenericDAO<T, Id extends Serializable> {    
    int getCount();    
    int getMaxId();
    T buscarPorClave(Id id);
    T buscarPorEntity(Id id);
    List<T> buscarPorEntityList(Id id);
    List<T> buscarTodos();
    String salvar(T objeto);
    String borrar(T objeto);
    String borrarNative(Id id);
    String insertar(T objeto);
    String insertarNative(Id id1, Id id2);
}

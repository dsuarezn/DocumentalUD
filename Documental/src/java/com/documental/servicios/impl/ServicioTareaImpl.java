/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.documental.servicios.impl;

import com.documental.bo.Tarea;
import com.documental.dao.DAOAbstractFactory;
import com.documental.dao.DAOFactory;
import com.documental.dao.TareaDAO;
import com.documental.servicios.ServicioTarea;
import java.util.List;

/**
 *
 * @author DiegoM
 */
public class ServicioTareaImpl implements ServicioTarea{
    
    private TareaDAO accesoDAO = null;

    public ServicioTareaImpl() {
        DAOFactory factoria = DAOAbstractFactory.getInstance();
        accesoDAO = factoria.getTareaDAO();
    }

    @Override
    public int getCount() {
        return accesoDAO.getCount();
    }

    @Override
    public int getMaxId() {
        return accesoDAO.getMaxId();
    }

    @Override
    public String salvarTarea(Tarea tarea) {
        return accesoDAO.salvar(tarea);
    }

    @Override
    public String borrarTarea(Tarea tarea) {
        return accesoDAO.borrar(tarea);
    }

    @Override
    public List<Tarea> buscarTodosTarea() {
        return accesoDAO.buscarTodos();
    }

    @Override
    public Tarea buscarPorClave(Integer id) {
        return accesoDAO.buscarPorClave(id);
    }

    @Override
    public Tarea buscarPorEntity(Integer id) {
        return accesoDAO.buscarPorEntity(id);
    }

    @Override
    public List<Tarea> buscarPorEntityList(Integer id) {
        return accesoDAO.buscarPorEntityList(id);
    }

    @Override
    public String insertarNative(Integer id1, Integer id2) {
        return accesoDAO.insertarNative(id1, id2);
    }

    @Override
    public String borrarNative(Integer id) {
        return accesoDAO.borrarNative(id);
    }
    
}

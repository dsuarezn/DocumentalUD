/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.documental.servicios.impl;

import com.documental.bo.TipoUsuario;
import com.documental.dao.DAOAbstractFactory;
import com.documental.dao.DAOFactory;
import com.documental.dao.TipoUsuarioDAO;
import com.documental.servicios.ServicioTipoUsuario;
import java.util.List;

/**
 *
 * @author DiegoM
 */
public class ServicioTipoUsuarioImpl implements ServicioTipoUsuario {

    private TipoUsuarioDAO accesoDAO = null;

    public ServicioTipoUsuarioImpl() {
        DAOFactory factoria = DAOAbstractFactory.getInstance();
        accesoDAO = factoria.getTipoUsuarioDAO();
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
    public String salvarTipoUsuario(TipoUsuario tipo) {
        return accesoDAO.salvar(tipo);
    }

    @Override
    public String borrarTipoUsuario(TipoUsuario tipo) {
        return accesoDAO.borrar(tipo);
    }

    @Override
    public List<TipoUsuario> buscarTodosTipoUsuario() {
        return accesoDAO.buscarTodos();
    }

    @Override
    public TipoUsuario buscarPorClave(Integer id) {
        return accesoDAO.buscarPorClave(id);
    }

    @Override
    public TipoUsuario buscarPorEntity(Integer id) {
        return accesoDAO.buscarPorEntity(id);
    }

    @Override
    public List<TipoUsuario> buscarPorEntityList(Integer id) {
        return accesoDAO.buscarPorEntityList(id);
    }

}

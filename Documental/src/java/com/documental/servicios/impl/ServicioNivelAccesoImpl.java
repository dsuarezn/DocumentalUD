/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.documental.servicios.impl;

import com.documental.bo.NivelAcceso;
import com.documental.dao.DAOAbstractFactory;
import com.documental.dao.DAOFactory;
import com.documental.dao.NivelAccesoDAO;
import com.documental.servicios.ServicioNivelAcceso;
import java.util.List;

/**
 *
 * @author DiegoM
 */
public class ServicioNivelAccesoImpl implements ServicioNivelAcceso {

    private NivelAccesoDAO accesoDAO = null;

    public ServicioNivelAccesoImpl() {
        DAOFactory factoria = DAOAbstractFactory.getInstance();
        accesoDAO = factoria.getNivelAccesoDAO();
    }

    @Override
    public String salvarNivel(NivelAcceso nivel) {
        return accesoDAO.salvar(nivel);
    }

    @Override
    public String borrarNivel(NivelAcceso nivel) {
        return accesoDAO.borrar(nivel);
    }

    @Override
    public List<NivelAcceso> buscarTodosNiveles() {
        return accesoDAO.buscarTodos();
    }

    @Override
    public NivelAcceso buscarPorClave(Integer id) {
        return accesoDAO.buscarPorClave(id);
    }

    @Override
    public int getCount() {
        return accesoDAO.getCount();
    }

    @Override
    public int getMaxId() {
        return accesoDAO.getMaxId();
    }
}

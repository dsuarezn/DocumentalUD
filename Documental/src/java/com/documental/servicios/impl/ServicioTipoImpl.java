/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.documental.servicios.impl;

import com.documental.bo.Documento;
import com.documental.bo.Tipo;
import com.documental.dao.DAOAbstractFactory;
import com.documental.dao.DAOFactory;
import com.documental.dao.TipoDAO;
import com.documental.servicios.ServicioTipo;
import java.util.List;

/**
 *
 * @author Alexander
 */
public class ServicioTipoImpl implements ServicioTipo{

     private TipoDAO tipoDAO;

    public ServicioTipoImpl() {
        DAOFactory factoria= DAOAbstractFactory.getInstance();
        this.tipoDAO = factoria.getTipoDAO();
    }
    
    @Override
    public int getMaxId() {
        return tipoDAO.getMaxId();
    }

    @Override
    public String salvarTipo(Tipo tipo) {
        return tipoDAO.salvar(tipo);
    }

    @Override
    public Tipo consultarTipoPorId(Integer Id) {
        return tipoDAO.buscarPorClave(Id);
    }

    @Override
    public List<Tipo> consultarTodosTipos() {
        return tipoDAO.buscarTodos();
    }

    @Override
    public void borrarTipo(Tipo tipo) {
        tipoDAO.borrar(tipo);
    }
    
}

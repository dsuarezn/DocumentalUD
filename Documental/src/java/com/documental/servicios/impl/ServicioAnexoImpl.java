/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.documental.servicios.impl;

import com.documental.bo.Anexo;
import com.documental.dao.AnexoDAO;
import com.documental.dao.DAOAbstractFactory;
import com.documental.dao.DAOFactory;
import com.documental.dao.DocumentoDAO;
import com.documental.servicios.ServicioAnexo;
import java.util.List;

/**
 *
 * @author Alexander
 */
public class ServicioAnexoImpl implements ServicioAnexo{

     private AnexoDAO anexoDAO;

    public ServicioAnexoImpl() {
        DAOFactory factoria= DAOAbstractFactory.getInstance();
        this.anexoDAO = factoria.getAnexoDAO();
    }
    
    @Override
    public int getMaxId() {
        return anexoDAO.getMaxId();
    }

    @Override
    public String salvarAnexo(Anexo anexo) {
        return anexoDAO.salvar(anexo);
    }

    @Override
    public Anexo consultarAnexoPorId(Integer Id) {
        return anexoDAO.buscarPorClave(Id);
    }

    @Override
    public List<Anexo> consultarTodosAnexo() {
        return anexoDAO.buscarTodos();
    }

    @Override
    public void borrarAnexo(Anexo anexo) {
        anexoDAO.borrar(anexo);
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.documental.servicios.impl;

import com.documental.bo.Historico;
import com.documental.bo.HistoricoPK;
import com.documental.dao.DAOAbstractFactory;
import com.documental.dao.DAOFactory;
import com.documental.dao.HistoricoDAO;
import com.documental.servicios.ServicioHistorico;
import java.util.List;

/**
 *
 * @author Alexander
 */
public class ServicioHistoricoImpl implements ServicioHistorico {

    private HistoricoDAO historicoDAO;

    public ServicioHistoricoImpl() {
        DAOFactory factoria = DAOAbstractFactory.getInstance();
        historicoDAO = factoria.getHistoricoDAO();
    }

    @Override
    public String salvarHistorico(Historico historico) {
        return historicoDAO.salvar(historico);
    }

    @Override
    public Historico consultarHistoricoPorId(HistoricoPK Id) {
        return historicoDAO.buscarPorClave(Id);
    }

    @Override
    public List<Historico> consultarTodosHistoricos() {
        return historicoDAO.buscarTodos();
    }

    @Override
    public void borrarHistorico(Historico historico) {
        historicoDAO.borrar(historico);
    }

    @Override
    public List<Historico> buscarDestinatarioActivo(int id) {
        return historicoDAO.buscarDestinatarioActivo(id);
    }
 
    
    @Override
    public int getCountForDocument(Integer IdDocumento) {
        return historicoDAO.buscarHistoricoDocumento(IdDocumento).size();
    }

    @Override
    public List<Historico> consultarHistoricoPorDocumentId(Integer IdDocumento) {
        return historicoDAO.buscarHistoricoDocumento(IdDocumento);
    }
   

    @Override
    public List<Historico> consultarComentariosPorDocumento(Integer idDocumento) {
        return historicoDAO.buscarComentariosDocumento(idDocumento);
    }
    
    @Override
    public Integer countComentariosPorDocumento(Integer idDocumento) {
        return historicoDAO.countComentariosPorDocumento(idDocumento);
    }
    
    @Override
    public List<Historico> consultarHistoricosPorDocumento(Integer documentoId) {
        return historicoDAO.buscarComentariosDocumento(documentoId);
    }

    @Override
    public String insertarHistorico(Historico historico) {
        return historicoDAO.insertar(historico);
    }

}

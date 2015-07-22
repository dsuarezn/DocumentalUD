/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.documental.servicios.impl;

import com.documental.bo.Documento;
import com.documental.bo.Historico;
import com.documental.dao.DAOAbstractFactory;
import com.documental.dao.DAOFactory;
import com.documental.dao.DocumentoDAO;
import com.documental.servicios.ServicioDocumento;
import java.util.List;

/**
 *
 * @author Alexander
 */
public class ServicioDocumentoImpl implements ServicioDocumento{

    private DocumentoDAO documentoDAO;

    public ServicioDocumentoImpl() {
        DAOFactory factoria= DAOAbstractFactory.getInstance();
        this.documentoDAO = factoria.getDocumentoDAO();
    }
    
    @Override
    public int getMaxId() {
        return documentoDAO.getMaxId();
    }

    @Override
    public String salvarDocumento(Documento documento) {
        return documentoDAO.salvar(documento);
    }

    @Override
    public Documento consultarDocumentoPorId(Integer Id) {
        return documentoDAO.buscarPorClave(Id);
    }

    @Override
    public List<Documento> consultarTodosDocumentos() {
        return documentoDAO.buscarTodos();
    }

    @Override
    public void borrarDocumento(Documento documento) {
        documentoDAO.borrar(documento);
    }
    
    @Override
    public int getCount() {
        return documentoDAO.getCount();
    }

    @Override
    public List<Historico> buscarFiltro(Historico historico) {
        return documentoDAO.buscarFiltro(historico);
    }
    
}

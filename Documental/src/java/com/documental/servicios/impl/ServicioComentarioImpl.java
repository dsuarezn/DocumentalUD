/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.documental.servicios.impl;

import com.documental.bo.Comentario;
import com.documental.dao.ComentarioDAO;
import com.documental.dao.DAOAbstractFactory;
import com.documental.dao.DAOFactory;
import com.documental.servicios.ServicioComentario;
import java.util.List;

/**
 *
 * @author Alexander
 */
public class ServicioComentarioImpl implements ServicioComentario{

    private ComentarioDAO comentarioDAO;
    
    public ServicioComentarioImpl() {
        DAOFactory factoria = DAOAbstractFactory.getInstance();
        comentarioDAO = factoria.getComentarioDAO();
    }

    
    
    @Override
    public List<Comentario> obtenerComentariosDocumento(Integer documentoId) {
       return comentarioDAO.listarComentariosPorDocumento(documentoId);
    }

    @Override
    public Long contarComentariosDocumento(Integer documentoId) {
        return comentarioDAO.contarComentariosPorDocumento(documentoId);
    }

    @Override
    public String salvarComentario(Comentario comentario) {
        return comentarioDAO.salvar(comentario);
    }

    @Override
    public void borrarComentario(Comentario comentario) {
        comentarioDAO.borrar(comentario);
    }

    @Override
    public Comentario obtenerComentarioPorId(Integer comentarioId) {
        return comentarioDAO.buscarPorClave(comentarioId);
    }
    
}

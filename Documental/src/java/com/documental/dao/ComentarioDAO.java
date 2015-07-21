/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.documental.dao;

import com.documental.bo.Comentario;
import java.util.List;

/**
 *
 * @author Alexander
 */
public interface ComentarioDAO extends GenericDAO<Comentario, Integer>{
    
    public List<Comentario> listarComentariosPorDocumento(Integer documentoId);
    
    public Long contarComentariosPorDocumento(Integer documentoId);
}

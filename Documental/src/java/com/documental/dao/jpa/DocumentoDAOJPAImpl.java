/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.documental.dao.jpa;
import com.documental.bo.Documento;
import com.documental.dao.DocumentoDAO;
import java.util.List;

/**
 *
 * @author Alexander
 */
public class DocumentoDAOJPAImpl extends GenericDAOJPAImpl<Documento, Integer> implements DocumentoDAO{

    @Override
    public List<Documento> buscarPorClave(Documento login) {
       ///TODO: implementar metodo
        return null;
    }
    
}

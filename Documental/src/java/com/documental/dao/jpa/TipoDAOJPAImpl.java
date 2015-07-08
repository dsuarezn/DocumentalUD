/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.documental.dao.jpa;

import com.documental.bo.Tipo;
import com.documental.dao.DocumentoDAO;
import com.documental.dao.TipoDAO;
import java.util.List;

/**
 *
 * @author Alexander
 */
public class TipoDAOJPAImpl extends GenericDAOJPAImpl<Tipo, Integer> implements TipoDAO {

    @Override
    public List<Tipo> buscarPorClave(Tipo tipo) {
        //TODO:implementar metodo
        return null;
    }
    
}

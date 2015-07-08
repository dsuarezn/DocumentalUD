/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.documental.dao.jpa;

import com.documental.dao.AnexoDAO;
import com.documental.bo.Anexo;
import com.documental.bo.AnexoPK;
import java.util.List;

/**
 *
 * @author Alexander
 */
public class AnexoDAOJPAImpl extends GenericDAOJPAImpl<Anexo, Integer> implements AnexoDAO{

    @Override
    public List<Anexo> buscarPorClave(Anexo tipo) {
        //TODO:implementar
        return null;
    }
    
}

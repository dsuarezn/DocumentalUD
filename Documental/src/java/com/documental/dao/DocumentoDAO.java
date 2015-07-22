/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.documental.dao;

import com.documental.bo.Documento;
import com.documental.bo.Historico;
import java.util.List;

/**
 *
 * @author DiegoM
 */
public interface DocumentoDAO extends GenericDAO<Documento, Integer> {

    public List<Documento> buscarPorClave(Documento login);
    public List<Historico> buscarFiltro(Historico historico);

}

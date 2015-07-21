/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.documental.dao;

import com.documental.bo.Anexo;
import java.util.List;

/**
 *
 * @author DiegoM
 */
public interface AnexoDAO extends GenericDAO<Anexo, Integer>{
    public List<Anexo> buscarPorClave(Anexo tipo);
    public List<Anexo> buscarPorDocumentoAsociado(Integer documentoId);
    public Integer getCountPorDocumento(Integer documentoId);
    public Anexo buscarPorAnexoIdAsociado(Integer idAnexo);
}

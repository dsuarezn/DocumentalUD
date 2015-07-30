/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.documental.dao;



import com.documental.bo.Historico;
import com.documental.bo.HistoricoPK;
import java.util.List;
/**
 *
 * @author Alexander
 */
public interface HistoricoDAO extends GenericDAO<Historico, HistoricoPK>{
   
    public List<Historico> buscarDestinatarioActivo(int id);
    public List<Historico> buscarHistoricoDocumento(int id);      

    public Integer countHistoricosPorDocumento(Integer idDocumento);
    
    public List<Historico> buscarDocumentosSalida(int usuario);

    public List<Historico> buscarDocumentosCerrados(int usuario);
}

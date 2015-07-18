/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.documental.dao.jpa;
import com.documental.bo.Documento;
import com.documental.dao.DocumentoDAO;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

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
    
    @Override
    public int getMaxId(){
        EntityManagerFactory factoriaSession = JPAHelper.getJPAFactory();
        EntityManager manager = factoriaSession.createEntityManager();
        Integer idpk = 0;
        try {
            Query consulta = manager.createQuery("select max(d.idDocumento) from Documento d");
            if(consulta.getResultList().get(0) == null){
              idpk = 0;  
            }else{
              idpk = (Integer) consulta.getResultList().get(0);
            }            
            return idpk;
        } finally {
            manager.close();
        }
     }
    
}

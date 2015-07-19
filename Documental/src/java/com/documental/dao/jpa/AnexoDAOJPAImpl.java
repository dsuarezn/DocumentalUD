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
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

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
    
    public int getMaxId(){
        EntityManagerFactory factoriaSession = JPAHelper.getJPAFactory();
        EntityManager manager = factoriaSession.createEntityManager();
        Integer idpk = 0;
        try {
            Query consulta = manager.createQuery("select max(a.anexoPK.idAnexo) from Anexo a");
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

    @Override
    public List<Anexo> buscarPorDocumentoAsociado(Integer documentoId) {
        EntityManagerFactory factoriaSession = JPAHelper.getJPAFactory();
        EntityManager em = factoriaSession.createEntityManager();
        Query consulta = em.createNamedQuery("Anexo.findByDocumentoId");
        consulta.setParameter("documentoId", documentoId);
        List<Anexo> listaAnexos = null;
        try {
            listaAnexos = consulta.getResultList();
        } finally {
            em.close();
        }
        return listaAnexos;
    }

    
    @Override
    public Integer getCountPorDocumento(Integer documentoId) {
         EntityManagerFactory factoriaSession = JPAHelper.getJPAFactory();
        EntityManager em = factoriaSession.createEntityManager();
        Query consulta = em.createNamedQuery("Anexo.CountByDocumentoId");
        consulta.setParameter("documentoId", documentoId);
        Long count = null;
        try {
            count = (Long) consulta.getSingleResult();
        } finally {
            em.close();
        }
        return count.intValue();
    }
    
    
    
}

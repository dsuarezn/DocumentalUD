/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.documental.dao.jpa;

import com.documental.bo.NivelAcceso;
import com.documental.dao.NivelAccesoDAO;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
/**
 *
 * @author DiegoM
 */
public class NivelAccesoDAOJPAImpl extends GenericDAOJPAImpl<NivelAcceso, Integer> implements NivelAccesoDAO{
        
     public int getMaxId(){
        EntityManagerFactory factoriaSession = JPAHelper.getJPAFactory();
        EntityManager manager = factoriaSession.createEntityManager();
        Integer idpk = 0;
        try {
            Query consulta = manager.createQuery("select max(n.id) from NivelAcceso n");
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

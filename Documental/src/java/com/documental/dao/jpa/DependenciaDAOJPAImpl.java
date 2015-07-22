/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.documental.dao.jpa;

import com.documental.bo.Dependencia;
import com.documental.bo.DependenciaDirector;
import com.documental.bo.DependenciaEmpleado;
import com.documental.dao.DependenciaDAO;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author enrique
 */
public class DependenciaDAOJPAImpl extends GenericDAOJPAImpl<Dependencia, Integer> implements DependenciaDAO {
    
    public int getMaxId() {
        EntityManagerFactory factoriaSession = JPAHelper.getJPAFactory();
        EntityManager manager = factoriaSession.createEntityManager();
        Integer idpk = 0;
        try {
            Query consulta = manager.createQuery("select max(t.idDependencia) from Dependencia t");
            if (consulta.getResultList().get(0) == null) {
                idpk = 0;
            } else {
                idpk = (Integer) consulta.getResultList().get(0);
            }
            return idpk;
        } finally {
            manager.close();
        }
    }

    @Override
    public List<Dependencia> buscarDependenciasConDirector() {
        EntityManagerFactory factoriaSession = JPAHelper.getJPAFactory();
        EntityManager manager = factoriaSession.createEntityManager();        
        Query query = manager.createNamedQuery("Dependencia.findAllWithDirector",Dependencia.class);		        
	List<Dependencia> listadependencia =  query.getResultList();
	return listadependencia; 
    }

    @Override
    public List<Dependencia> buscarDependenciasActivas(){
        EntityManagerFactory factoriaSession = JPAHelper.getJPAFactory();
        EntityManager manager = factoriaSession.createEntityManager();        
        Query query = manager.createNamedQuery("Dependencia.findByEstado",Dependencia.class);
        query.setParameter("estado", "Activo");
	List<Dependencia> listadependencia =  query.getResultList();
	return listadependencia; 
    }

    @Override
    public DependenciaDirector buscarDependenciaDirector(Integer login) {
         EntityManagerFactory factoriaSession = JPAHelper.getJPAFactory();
        EntityManager manager = factoriaSession.createEntityManager();
        Integer idpk = 0;
        try {
            TypedQuery<DependenciaDirector> consulta = manager.createQuery(
                    "SELECT D FROM DependenciaDirector D JOIN FETCH D.login where D.login.idLogin = ?1", DependenciaDirector.class);
            consulta.setParameter(1, login);
            DependenciaDirector dependenciaDirector = null;
            dependenciaDirector = consulta.getSingleResult();
            return dependenciaDirector;
        } finally {
            manager.close();
        }
    }

    @Override
    public DependenciaEmpleado buscarDependenciaEmpleado(Integer login) {
        EntityManagerFactory factoriaSession = JPAHelper.getJPAFactory();
        EntityManager manager = factoriaSession.createEntityManager();
        Integer idpk = 0;
        try {
            TypedQuery<DependenciaEmpleado> consulta = manager.createQuery(
                    "SELECT D FROM DependenciaEmpleado D JOIN FETCH D.login where D.login.idLogin = ?1", DependenciaEmpleado.class);
            consulta.setParameter(1, login);
            DependenciaEmpleado dependenciaEmpleado = null;
            dependenciaEmpleado = consulta.getSingleResult();
            return dependenciaEmpleado;
        } finally {
            manager.close();
        }
    }
    
    
}

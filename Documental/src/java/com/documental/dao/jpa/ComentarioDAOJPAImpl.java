/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.documental.dao.jpa;
import com.documental.bo.Comentario;
import com.documental.dao.ComentarioDAO;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
/**
 *
 * @author Alexander
 */
public class ComentarioDAOJPAImpl extends GenericDAOJPAImpl<Comentario, Integer> implements ComentarioDAO {

    @Override
    public List<Comentario> listarComentariosPorDocumento(Integer documentoId) {
        EntityManagerFactory factoriaSession = JPAHelper.getJPAFactory();
        EntityManager manager = factoriaSession.createEntityManager();        
        Query query = manager.createNamedQuery("Comentario.findByDocumentoId",Comentario.class);		        
        query.setParameter("documentoId", documentoId);
	List<Comentario> listacomentarios =  query.getResultList();
	return listacomentarios; 
    }

    @Override
    public Long contarComentariosPorDocumento(Integer documentoId) {
        EntityManagerFactory factoriaSession = JPAHelper.getJPAFactory();
        EntityManager manager = factoriaSession.createEntityManager();        
        Query query = manager.createNamedQuery("Comentario.countByDocumentoId",Comentario.class);		        
        query.setParameter("documentoId", documentoId);
	Long count =  (Long) query.getSingleResult();
	return count; 
    }
    
}

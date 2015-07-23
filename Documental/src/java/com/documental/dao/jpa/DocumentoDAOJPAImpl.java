/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.documental.dao.jpa;

import com.documental.bo.Documento;
import com.documental.bo.Historico;
import com.documental.dao.DocumentoDAO;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author Alexander
 */
public class DocumentoDAOJPAImpl extends GenericDAOJPAImpl<Documento, Integer> implements DocumentoDAO {

    @Override
    public List<Documento> buscarPorClave(Documento login) {
        ///TODO: implementar metodo
        return null;
    }

    @Override
    public int getMaxId() {
        EntityManagerFactory factoriaSession = JPAHelper.getJPAFactory();
        EntityManager manager = factoriaSession.createEntityManager();
        Integer idpk = 0;
        try {
            Query consulta = manager.createQuery("select max(d.idDocumento) from Documento d");
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
    public List<Historico> buscarFiltro(Historico historico) {
        EntityManagerFactory factoriaSession = JPAHelper.getJPAFactory();
        EntityManager manager = factoriaSession.createEntityManager();
        String query = "SELECT H FROM Historico H "+
                 "JOIN FETCH H.documento D "
                + "WHERE 1=1 ";
        if (historico.getDocumento().getTipoId().getIdTipo() != null
                && historico.getDocumento().getTipoId().getIdTipo() != 0) {
            query = query + "and D.tipoId.idTipo = :pTipo ";
        }
        if (historico.getDocumento().getEstado() != null
                && !historico.getDocumento().getEstado().equals("")) {
            query = query + "and D.estado = :pEstado ";
        }
        if (historico.getDocumento().getFechaCreacion() != null) {
            query = query + "and D.fechaCreacion >= :sFechaD ";
        }
        if (historico.getDocumento().getFechaAuxiliar() != null) {
            query = query + "and D.fechaCreacion < :sFechaH ";
        }
        if (historico.getDocumento().getVisibilidad() != null
                && !historico.getDocumento().getVisibilidad().equals("")) {
            query = query + "and D.visibilidad = :pVisibilidad ";
        }
        if (historico.getDocumento().getAsunto() != null
                && !historico.getDocumento().getAsunto().equals("")) {
            query = query + "and D.asunto LIKE :pCriterio ";
            query = query + "or D.descripcion LIKE :pCriterio ";            
        }
        if (historico.getDocumento().getPalabrasClave() != null
                && !historico.getDocumento().getPalabrasClave().equals("")){
            query = query + "and D.palabrasClave LIKE :pPalabras_clave ";
        }
        if (historico.getLoginDestinatario().getIdLogin()!= 0){
            query = query + "and H.historicoPK.destinatarioId LIKE :pDestinatario ";
        }
        
        System.out.println("el query es: "+ query);

        TypedQuery<Historico> consulta = manager.createQuery(query,
                Historico.class);

        if (historico.getDocumento().getTipoId().getIdTipo() != null
                && historico.getDocumento().getTipoId().getIdTipo() != 0) {
            consulta.setParameter("pTipo",
                    historico.getDocumento().getTipoId().getIdTipo());
            System.out.println("el valor de idTipo es: "+historico.getDocumento().getTipoId().getIdTipo());
        }
        if (historico.getDocumento().getEstado() != null
                && !historico.getDocumento().getEstado().equals("")) {
            consulta.setParameter("pEstado",
                    historico.getDocumento().getEstado());
            System.out.println("el valor de estado es: "+historico.getDocumento().getEstado());
        }
 
        if (historico.getDocumento().getVisibilidad() != null
                && !historico.getDocumento().getVisibilidad().equals("")) {
            consulta.setParameter("pVisibilidad",
                    historico.getDocumento().getVisibilidad());
            System.out.println("el valor de visibilidad es: "+historico.getDocumento().getVisibilidad());
        }
 
        if (historico.getDocumento().getPalabrasClave()!= null
                && !historico.getDocumento().getPalabrasClave().equals("")) {
            consulta.setParameter("pPalabras_clave","%" + historico.getDocumento().getPalabrasClave() + "%");
            System.out.println("el valor de palabras_clave es: "+historico.getDocumento().getPalabrasClave());
        }
        if (historico.getLoginDestinatario().getIdLogin() != 0) {
            consulta.setParameter("pDestinatario", historico.getLoginDestinatario().getIdLogin());
        }
        if (historico.getDocumento().getFechaCreacion() != null) {
            consulta.setParameter("sFechaD", historico.getDocumento().getFechaCreacion());
        }
        if (historico.getDocumento().getFechaAuxiliar() != null) {
            consulta.setParameter("sFechaH", historico.getDocumento().getFechaAuxiliar());
        }
        if (historico.getDocumento().getAsunto() != null
                && !historico.getDocumento().getAsunto().equals("")) {
            consulta.setParameter("pCriterio", "%" + historico.getDocumento().getAsunto() + "%");
        }
        return consulta.getResultList();
    }

}

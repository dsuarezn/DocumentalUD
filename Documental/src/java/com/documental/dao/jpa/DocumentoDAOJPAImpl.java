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
    public List<Documento> buscarFiltro(Documento documento) {
        EntityManagerFactory factoriaSession = JPAHelper.getJPAFactory();
        EntityManager manager = factoriaSession.createEntityManager();
        String query = "SELECT D FROM Documento D "
                + "WHERE 1=1 ";
        if (documento.getTipoId().getIdTipo() != null
                && !documento.getTipoId().getIdTipo().equals("")) {
            query = query + "and D.tipoId.idTipo = :pTipo";
        }
        if (documento.getEstado() != null
                && !documento.getEstado().equals("")) {
            query = query + "and D.estado = :pEstado ";
        }
        if (documento.getFechaCreacion() != null) {
            query = query + "and D.fechaCreacion >= :sFechaD ";
        }
        if (documento.getFechaAuxiliar() != null) {
            query = query + "and D.fechaCreacion < :sFechaH ";
        }
        if (documento.getVisibilidad() != null) {
            query = query + "and D.visibilidad < :pVisibilidad ";
        }
        if (documento.getAsunto() != null
                && !documento.getAsunto().equals("")) {
            query = query + "and D.asunto LIKE :pCriterio ";
            query = query + "or D.descripcion LIKE :pCriterio ";
        }

        TypedQuery<Documento> consulta = manager.createQuery(query,
                Documento.class);

        if (documento.getTipoId().getIdTipo() != null
                && !documento.getTipoId().getIdTipo().equals("")) {
            consulta.setParameter("pTipo",
                    documento.getTipoId().getIdTipo() + "");
        }
        if (documento.getEstado() != null
                && !documento.getEstado().equals("")) {
            consulta.setParameter("pEstado",
                    documento.getEstado());
        }
 
        if (documento.getVisibilidad() != null
                && !documento.getVisibilidad().equals("")) {
            consulta.setParameter("pVisibilidad",
                    documento.getVisibilidad());
        }
        if (documento.getFechaCreacion() != null) {
            consulta.setParameter("sFechaD", documento.getFechaCreacion());
        }
        if (documento.getFechaAuxiliar() != null) {
            consulta.setParameter("sFechaH", documento.getFechaAuxiliar());
        }
        if (documento.getAsunto() != null
                && !documento.getAsunto().equals("")) {
            consulta.setParameter("pCriterio", "%" + documento.getAsunto() + "%");
        }
        return consulta.getResultList();
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.documental.dao.jpa;

import com.documental.dao.HistoricoDAO;
import com.documental.bo.Historico;
import com.documental.bo.HistoricoPK;
import com.documental.bo.Login;
import com.documental.bo.Tarea;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
/**
 *
 * @author Alexander
 */
public class HistoricoDAOJPAImpl extends GenericDAOJPAImpl<Historico, HistoricoPK> implements HistoricoDAO
{

    @Override
    public List<Historico> buscarDestinatarioActivo(int id) {
        EntityManagerFactory factoriaSession = JPAHelper.getJPAFactory();
        EntityManager manager = factoriaSession.createEntityManager();
        try {
            String sql = "select * from historico where destinatario_id = ? and activo = true";
            Query q = manager.createNativeQuery(sql, Historico.class);
            q.setParameter(1, id);
            return q.getResultList();
        } catch (Exception e) {
            System.out.println("el error es: " + e.toString());
            return null;
        }
    }

    @Override
    public List<Historico> buscarHistoricoDocumento(int id) {
        EntityManagerFactory factoriaSession = JPAHelper.getJPAFactory();
        EntityManager manager = factoriaSession.createEntityManager();
        try {
            String sql = "select * from historico where documento_id = ?";
            Query q = manager.createNativeQuery(sql, Historico.class);
            q.setParameter(1, id);
            return q.getResultList();
        } catch (Exception e) {
            System.out.println("el error es: " + e.toString());
            return null;
        }
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.documental.dao.jpa;

import com.documental.bo.TipoUsuario;
import com.documental.dao.TipoUsuarioDAO;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

/**
 *
 * @author DiegoM
 */
public class TipoUsuarioDAOJPAImpl extends GenericDAOJPAImpl<TipoUsuario, Integer> implements TipoUsuarioDAO {
    
    public int getMaxId() {
        EntityManagerFactory factoriaSession = JPAHelper.getJPAFactory();
        EntityManager manager = factoriaSession.createEntityManager();
        Integer idpk = 0;
        try {
            Query consulta = manager.createQuery("select max(t.idTipoUsuario) from TipoUsuario t");
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
    
    public TipoUsuario buscarPorEntity(Integer id) {
        EntityManagerFactory factoriaSession = JPAHelper.getJPAFactory();
        EntityManager manager = factoriaSession.createEntityManager();
        try {
            String sql = "SELECT tipo_usuario.id_tipo_usuario, "
                    + "tipo_usuario.tipo_usuario "
                    + "FROM tipo_usuario_tarea INNER JOIN tipo_usuario ON "
                    + "tipo_usuario_tarea.id_tipo_usuario = tipo_usuario.id_tipo_usuario "
                    + "WHERE tipo_usuario_tarea.id_tipo_usuario= ?";

            Query q = manager.createNativeQuery(sql, TipoUsuario.class);
            q.setParameter(1, id);
            return (TipoUsuario) q.getResultList().get(0);
        } catch (Exception e) {
            System.out.println("el error es: " + e.toString());
            return null;
        }
    }
}

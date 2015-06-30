/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.documental.dao.jpa;

import com.documental.bo.Tarea;
import com.documental.dao.TareaDAO;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

/**
 *
 * @author DiegoM
 */
public class TareaDAOJPAImpl extends GenericDAOJPAImpl<Tarea, Integer> implements TareaDAO {

    public int getMaxId() {
        EntityManagerFactory factoriaSession = JPAHelper.getJPAFactory();
        EntityManager manager = factoriaSession.createEntityManager();
        Integer idpk = 0;
        try {
            Query consulta = manager.createQuery("select max(t.idTarea) from Tarea t");
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

    public Tarea buscarPorEntity(Integer id) {
        EntityManagerFactory factoriaSession = JPAHelper.getJPAFactory();
        EntityManager manager = factoriaSession.createEntityManager();
        try {

            String sql = "SELECT tarea.id_tarea, "
                    + "tarea.nombre_tarea "
                    + "FROM tipo_usuario_tarea INNER JOIN tarea ON "
                    + "tipo_usuario_tarea.id_tarea = tarea.id_tarea "
                    + "WHERE tipo_usuario_tarea.id_tipo_usuario = ?";

            Query q = manager.createNativeQuery(sql, Tarea.class);
            q.setParameter(1, id);
            return (Tarea) q.getResultList().get(0);
        } catch (Exception e) {
            System.out.println("el error es: " + e.toString());
            return null;
        }
    }

    public List<Tarea> buscarPorEntityList(Integer id) {
        EntityManagerFactory factoriaSession = JPAHelper.getJPAFactory();
        EntityManager manager = factoriaSession.createEntityManager();
        try {

            String sql = "SELECT tarea.id_tarea, "
                    + "tarea.nombre_tarea "
                    + "FROM tipo_usuario_tarea INNER JOIN tarea ON "
                    + "tipo_usuario_tarea.id_tarea = tarea.id_tarea "
                    + "WHERE tipo_usuario_tarea.id_tipo_usuario = ?";

            Query q = manager.createNativeQuery(sql, Tarea.class);
            q.setParameter(1, id);
            return q.getResultList();
        } catch (Exception e) {
            System.out.println("el error es: " + e.toString());
            return null;
        }
    }

    public String borrarNative(Integer id) {
        EntityManagerFactory factoriaSession = JPAHelper.getJPAFactory();
        EntityManager manager = factoriaSession.createEntityManager();
        manager.getTransaction().begin();
        String msg;
        try {
            Query deleteQuery = manager.createNativeQuery("delete from tipo_usuario_tarea where id_tipo_usuario = ?");
            deleteQuery.setParameter(1, id);
            deleteQuery.executeUpdate();
            manager.getTransaction().commit();
            msg = "Operación Exitosa";
        } catch (Exception e) {
            msg = "Operación Erronea";
        }
        return msg;
    }

    @Override
    public String insertarNative(Integer tipoUsuario, Integer tarea) {
        EntityManagerFactory factoriaSession = JPAHelper.getJPAFactory();
        EntityManager manager = factoriaSession.createEntityManager();
        manager.getTransaction().begin();
        String msg;
        try {
            Query insertQuery = manager.createNativeQuery("insert into tipo_usuario_tarea(id_tipo_usuario, id_tarea) values (?, ?)");
            insertQuery.setParameter(1, tipoUsuario);
            insertQuery.setParameter(2, tarea);
            insertQuery.executeUpdate();
            manager.getTransaction().commit();
            msg = "Operación Exitosa";
        } catch (Exception e) {
            System.out.println("El error es: "+e.toString());
            msg = "Operación Erronea";
        }
        return msg;
    }
}

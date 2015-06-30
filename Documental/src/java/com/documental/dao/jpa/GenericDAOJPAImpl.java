/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.documental.dao.jpa;

import com.documental.dao.GenericDAO;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author DiegoM
 */
public class GenericDAOJPAImpl<T, Id extends Serializable> implements GenericDAO<T, Id> {

    private Class<T> claseDePersistencia;

    @SuppressWarnings("unchecked")
    public GenericDAOJPAImpl() {
        this.claseDePersistencia = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    @Override
    public T buscarPorClave(Id id) {
        EntityManagerFactory factoriaSession = JPAHelper.getJPAFactory();
        EntityManager manager = factoriaSession.createEntityManager();
        T objeto = null;
        try {
            objeto = (T) manager.find(claseDePersistencia, id);
            return objeto;
        } finally {
            manager.close();
        }
    }

    @Override
    public T buscarPorEntity(Id id) {
        EntityManagerFactory factoriaSession = JPAHelper.getJPAFactory();
        EntityManager manager = factoriaSession.createEntityManager();
        try {
            TypedQuery<T> consulta = (TypedQuery<T>) manager.createNativeQuery("select o from "
                    + claseDePersistencia.getSimpleName() + " o", claseDePersistencia);
            consulta.setHint("javax.persistence.cache.storeMode", "REFRESH");
            return (T) consulta.getResultList().get(0);
        } finally {
            manager.close();
        }
    }

    @Override
    public List<T> buscarPorEntityList(Id id) {
        EntityManagerFactory factoriaSession = JPAHelper.getJPAFactory();
        EntityManager manager = factoriaSession.createEntityManager();
        List<T> listaDeObjetos = null;
        try {
            TypedQuery<T> consulta = (TypedQuery<T>) manager.createNativeQuery("select o from "
                    + claseDePersistencia.getSimpleName() + " o", claseDePersistencia);
            consulta.setHint("javax.persistence.cache.storeMode", "REFRESH");
            listaDeObjetos = consulta.getResultList();
            return listaDeObjetos;
        } catch (Exception e) {
            System.out.println("El error es " + e.toString());
            return null;
        } finally {
            manager.close();
        }
    }

    @Override
    public List<T> buscarTodos() {
        EntityManagerFactory factoriaSession = JPAHelper.getJPAFactory();
        EntityManager manager = factoriaSession.createEntityManager();
        List<T> listaDeObjetos = null;
        try {
            TypedQuery<T> consulta = manager.createQuery("select o from "
                    + claseDePersistencia.getSimpleName() + " o", claseDePersistencia);
            consulta.setHint("javax.persistence.cache.storeMode", "REFRESH");
            listaDeObjetos = consulta.getResultList();
            return listaDeObjetos;
        } finally {
            manager.close();
        }
    }

    @Override
    public String salvar(T objeto) {
        EntityManagerFactory factoriaSession = JPAHelper.getJPAFactory();
        EntityManager manager = factoriaSession.createEntityManager();

        EntityTransaction tx = null;
        String msg = "";
        try {

            tx = manager.getTransaction();
            tx.begin();
            manager.merge(objeto);
            tx.commit();
            msg = "Operación Exitosa";

        } catch (PersistenceException e) {
            msg = "Operación Erronea";
            tx.rollback();
            throw e;
        } finally {

            manager.close();
        }
        return msg;
    }

    @Override
    public String borrar(T objeto) {
        EntityManagerFactory factoriaSession = JPAHelper.getJPAFactory();
        EntityManager manager = factoriaSession.createEntityManager();

        EntityTransaction tx = null;
        String msg;
        try {

            tx = manager.getTransaction();
            tx.begin();
            manager.remove(manager.merge(objeto));
            tx.commit();
            msg = "Operación Exitosa";
        } catch (PersistenceException e) {
            msg = "Operación Erronea";
            tx.rollback();
            throw e;
        } finally {

            manager.close();
        }
        return msg;
    }

    @Override
    public String insertar(T objeto) {
        EntityManagerFactory factoriaSession = JPAHelper.getJPAFactory();
        EntityManager manager = factoriaSession.createEntityManager();

        EntityTransaction tx = null;
        String msg = "";
        try {

            tx = manager.getTransaction();
            tx.begin();
            manager.persist(objeto);
            tx.commit();
            msg = "Operación Exitosa";

        } catch (PersistenceException e) {
            msg = "Operación Erronea";
            tx.rollback();
            throw e;
        } finally {

            manager.close();
        }
        return msg;
    }

    @Override
    public int getCount() {
        EntityManagerFactory factoriaSession = JPAHelper.getJPAFactory();
        EntityManager manager = factoriaSession.createEntityManager();
        CriteriaQuery cq = manager.getCriteriaBuilder().createQuery();
        Root<T> rt = cq.from(claseDePersistencia.getClass());
        cq.select(manager.getCriteriaBuilder().count(rt));
        Query q = manager.createQuery(cq);        
        return ((Long) q.getSingleResult()).intValue();
    }

    @Override
    public int getMaxId() {
        EntityManagerFactory factoriaSession = JPAHelper.getJPAFactory();
        EntityManager manager = factoriaSession.createEntityManager();
        Integer idpk = 0;
        try {
            TypedQuery<T> consulta = manager.createQuery("select o from "
                    + claseDePersistencia.getSimpleName() + " o", claseDePersistencia);
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
    public String borrarNative(Id id) {
        EntityManagerFactory factoriaSession = JPAHelper.getJPAFactory();
        EntityManager manager = factoriaSession.createEntityManager();
        String msg = "";
        try {
            TypedQuery<T> deleteQuery = (TypedQuery<T>) manager.createNativeQuery("delete from "
                    + claseDePersistencia.getSimpleName());
            deleteQuery.executeUpdate();
            manager.getTransaction().commit();
            msg = "Operación Exitosa";
        } catch (Exception e) {
            msg = "Operación Erronea";
        }
        return msg;
    }

    @Override
    public String insertarNative(Id id1, Id id2) {
        EntityManagerFactory factoriaSession = JPAHelper.getJPAFactory();
        EntityManager manager = factoriaSession.createEntityManager();
        String msg = "";
        try {
            TypedQuery<T> insertQuery = (TypedQuery<T>) manager.createNativeQuery("insert into "
                    + claseDePersistencia.getSimpleName());
            insertQuery.executeUpdate();
            manager.getTransaction().commit();
            msg = "Operación Exitosa";
        } catch (Exception e) {
            msg = "Operación Erronea";
        }
        return msg;
    }
}

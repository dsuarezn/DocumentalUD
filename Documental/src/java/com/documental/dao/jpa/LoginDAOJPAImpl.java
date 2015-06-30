/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.documental.dao.jpa;

import com.documental.bo.Login;
import com.documental.dao.LoginDAO;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

/**
 *
 * @author DiegoM
 */
public class LoginDAOJPAImpl extends GenericDAOJPAImpl<Login, String> implements LoginDAO {

    @Override
    public List<Login> buscarPorClave(Login login) {
        EntityManagerFactory factoriaSession = JPAHelper.getJPAFactory();
        EntityManager manager = factoriaSession.createEntityManager();
        TypedQuery<Login> consulta = manager.createQuery(
                "Select l from Login l where l.usuario=?1", Login.class);
        consulta.setParameter(1, login.getUsuario());
        List<Login> listaDeLogin = null;
        try {
            listaDeLogin = consulta.getResultList();
        } finally {
            manager.close();
        }
        return listaDeLogin;
    }

}

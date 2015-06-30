/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.documental.dao;

import com.documental.dao.jpa.DAOJPAFactory;

/**
 *
 * @author DiegoM
 */
public class DAOAbstractFactory {

    public static DAOFactory getInstance() {
        String tipo = "JPA";
        if (tipo.equals("Hibernate")) {
            //new DAOHibernateFactory();
            return null;
        } else {
            return new DAOJPAFactory();
        }
    }
}

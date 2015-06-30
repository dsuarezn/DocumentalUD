/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.documental.dao.jpa;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author DiegoM
 */
public class JPAHelper {

    private static final EntityManagerFactory emf = buildEntityManagerFactory();

    private static EntityManagerFactory buildEntityManagerFactory() {
        try {
            return Persistence.createEntityManagerFactory("DocumentalPU");
        } catch (Throwable ex) {
            throw new RuntimeException("Error al crear la factoria de JPA "+ex.toString() );
        }
    }

    public static EntityManagerFactory getJPAFactory() {
        return emf;
    }
}

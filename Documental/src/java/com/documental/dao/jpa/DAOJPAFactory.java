/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.documental.dao.jpa;

import com.documental.dao.DAOFactory;
import com.documental.dao.LoginDAO;
import com.documental.dao.NivelAccesoDAO;
import com.documental.dao.TareaDAO;
import com.documental.dao.TipoUsuarioDAO;

/**
 *
 * @author DiegoM
 */
public class DAOJPAFactory implements DAOFactory{
    
    @Override
    public LoginDAO getLoginDAO() {
        return new LoginDAOJPAImpl();
    }

    @Override
    public NivelAccesoDAO getNivelAccesoDAO() {
        return new NivelAccesoDAOJPAImpl();
    }

    @Override
    public TareaDAO getTareaDAO() {
        return new TareaDAOJPAImpl();
    }

    @Override
    public TipoUsuarioDAO getTipoUsuarioDAO() {
        return new TipoUsuarioDAOJPAImpl();
    }
    
}

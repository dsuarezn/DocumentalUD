/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.documental.servicios.impl;

import com.documental.bo.Login;
import com.documental.dao.DAOAbstractFactory;
import com.documental.dao.DAOFactory;
import com.documental.dao.LoginDAO;
import com.documental.servicios.ServicioLogin;
import java.util.List;

/**
 *
 * @author DiegoM
 */
public class ServicioLoginImpl implements ServicioLogin{
    
    private LoginDAO loginDAO=null;

    public ServicioLoginImpl() {
        DAOFactory factoria= DAOAbstractFactory.getInstance();
        loginDAO = factoria.getLoginDAO();        
    }
        
    @Override
    public void salvarLogin(Login login) {
        loginDAO.salvar(login);
    }

    @Override
    public void borrarLogin(Login libro) {
        loginDAO.borrar(libro);
    }

    @Override
    public List<Login> buscarTodosLogin() {
        return loginDAO.buscarTodos();
    }

    @Override
    public Login buscarPorClave(String usuario) {
        return loginDAO.buscarPorClave(usuario);
    }
    
}

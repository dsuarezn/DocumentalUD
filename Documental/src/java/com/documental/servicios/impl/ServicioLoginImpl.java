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
    public String salvarLogin(Login login) {
        return loginDAO.salvar(login);
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
    public Login buscarPorClave(Integer usuario) {
        return loginDAO.buscarPorClave(usuario);
    }

    @Override
    public int getMaxId() {
        return loginDAO.getMaxId();
    }

    @Override
    public int getCount() {
        return loginDAO.getCount();
    }
    
    public Login obtenerDirectorDependencia(Integer dependencia) {
        return loginDAO.obtenerDirectorDependencia(dependencia);
    }

    @Override
    public Login buscarPorUsuario(String login) {
        return loginDAO.buscarPorUsuario(login);
    }

    @Override
    public Login obtenerLogin(String usuario) {
        return loginDAO.obtenerLogin(usuario);
    }
}

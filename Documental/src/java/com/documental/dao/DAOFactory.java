/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.documental.dao;

/**
 *
 * @author DiegoM
 */
public interface DAOFactory {
    public LoginDAO getLoginDAO();    
    public NivelAccesoDAO getNivelAccesoDAO();
    public TareaDAO getTareaDAO();
    public TipoUsuarioDAO getTipoUsuarioDAO();
}

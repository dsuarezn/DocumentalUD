/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.documental.servicios;

import com.documental.bo.Login;
import java.util.List;

/**
 *
 * @author DiegoM
 */
public interface ServicioLogin {

    public void salvarLogin(Login login);
    public void borrarLogin(Login libro);
    public List<Login> buscarTodosLogin();    
    public Login buscarPorClave(String login);    
    
}

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

    public int getCount();
    public int getMaxId();
    public String salvarLogin(Login login);
    public void borrarLogin(Login login);
    public List<Login> buscarTodosLogin();    
    public Login buscarPorClave(Integer login);    
    
}

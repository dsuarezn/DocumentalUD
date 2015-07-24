/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.documental.dao;

import com.documental.bo.Login;
import java.util.List;

/**
 *
 * @author DiegoM
 */
public interface LoginDAO extends GenericDAO<Login, Integer>{
    public  List<Login> buscarPorClave(Login login);    
    public List<Login> obtenerEmpleadosDependencia(Integer idDependencia);
    public Login obtenerDirectorDependencia(Integer idDependencia);
    public Login buscarPorUsuario(String login);    
    public Login obtenerLogin(String usuario);
    
}

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
public interface LoginDAO extends GenericDAO<Login, String>{
    public  List<Login> buscarPorClave(Login login);
}

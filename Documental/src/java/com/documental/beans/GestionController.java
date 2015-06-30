/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.documental.beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author DiegoM
 */
@ManagedBean(name = "beanGestion")
@SessionScoped
public class GestionController {

    /**
     * Creates a new instance of GestionController
     */
    public GestionController() {
    }
    
    public String prepareList() {
        return "/GUI/Gestion/BandejaEntrada/GUIBandejaEntradaList";
    }
    
}

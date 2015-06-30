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
@ManagedBean(name = "beanGeneral")
@SessionScoped
public class GeneralController {

    /**
     * Creates a new instance of GeneralController
     */
    public GeneralController() {
    }
    
    public String prepareCerrarSesion() {
        return "/GUI/General/GUIGralCerrarSesion";
    }
}

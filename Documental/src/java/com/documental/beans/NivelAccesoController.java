/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.documental.beans;

import com.documental.bo.NivelAcceso;
import com.documental.servicios.ServicioNivelAcceso;
import com.documental.servicios.impl.ServicioNivelAccesoImpl;
import com.documental.util.JsfUtil;
import com.documental.util.PaginationHelper;
import java.util.List;
import java.util.ResourceBundle;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

/**
 *
 * @author DiegoM
 */
@ManagedBean(name = "beanNivelAcceso")
@SessionScoped
public class NivelAccesoController {

    private NivelAcceso current;
    private ServicioNivelAcceso servicio;
    private DataModel items = null;
    private PaginationHelper pagination;
    private List<NivelAcceso> listNivelAcceso = null;
    private Long tipoUsuario;

    public NivelAccesoController() {
    }

    public Long getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(Long tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public List<NivelAcceso> getListNivelAcceso() {
        return listNivelAcceso;
    }

    public void setListNivelAcceso(List<NivelAcceso> listNivelAcceso) {
        this.listNivelAcceso = listNivelAcceso;
    }

    public DataModel getItems() {
        if (items == null) {
            items = getPagination().createPageDataModel();
        }
        return items;
    }

    public NivelAcceso getSelected() {
        if (current == null) {
            current = new NivelAcceso();
        }
        return current;
    }

    private ServicioNivelAcceso getServicio() {
        if (servicio == null) {
            servicio = new ServicioNivelAccesoImpl();
        }
        return servicio;
    }

    public String prepareList() {
        return "/GUI/Administrador/NivelAcceso/GUINivelAccesoList";
    }

    public String prepareCreate() {
        return "/GUI/Administrador/NivelAcceso/GUINivelAccesoCrear";
    }

    public String prepareEdit(NivelAcceso nivelAcceso) {
        current = getServicio().buscarPorClave(nivelAcceso.getId());
        return "/GUI/Administrador/NivelAcceso/GUINivelAccesoEditar";
    }

    public void prepareDelete(NivelAcceso nivelAcceso) {
        current = nivelAcceso;
    }

    public String volver() {
        return "/GUI/Administrador/NivelAcceso/GUINivelAccesoList";
    }

    public PaginationHelper getPagination() {
        if (pagination == null) {
            pagination = new PaginationHelper((10)) {
                @Override
                public int getItemsCount() {
                    return getServicio().getCount();
                }

                @Override
                public DataModel createPageDataModel() {
                    setListNivelAcceso(getServicio().buscarTodosNiveles());
                    return new ListDataModel(getListNivelAcceso());
                }
            };
        }
        return pagination;
    }

    public void create() {
        String respuesta = "";
        try {
            Integer id = getServicio().getMaxId();
            id++;
            current.setId(id);
            respuesta = getServicio().salvarNivel(current);
            items = null;
            if (respuesta.equals("Operación Exitosa")) {
                JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("documental_GUINivelAcceso_Messages_pCreateNivelExitoso"));
            } else {
                JsfUtil.addErrorMessage(ResourceBundle.getBundle("/Bundle").getString("documental_GUINivelAcceso_Messages_pCreateNivelErroneo"));
            }
        } catch (Exception e) {
            JsfUtil.addErrorMessage(ResourceBundle.getBundle("/Bundle").getString("documental_GUINivelAcceso_Messages_pCreateNivelErroneo") + " " + e.toString());
        }
    }
    
    public void edit() {
        String respuesta = "";
        try {            
            respuesta = getServicio().salvarNivel(current);            
            items = null;
            if (respuesta.equals("Operación Exitosa")) {
                JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("documental_GUINivelAcceso_Messages_pEditNivelExitoso"));
            } else {
                JsfUtil.addErrorMessage(ResourceBundle.getBundle("/Bundle").getString("documental_GUINivelAcceso_Messages_pEditNivelErroneo"));
            }
        } catch (Exception e) {
            JsfUtil.addErrorMessage(ResourceBundle.getBundle("/Bundle").getString("documental_GUINivelAcceso_Messages_pEditNivelErroneo") + " " + e.toString());
        }
    }

    public void delete() {
        String rpta = null;
        try {
            rpta = getServicio().borrarNivel(current);            
            if (rpta.equals("Operación Exitosa")) {
                items = null;
                JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("documental_GUINivelAcceso_Messages_pEliminarNivelExitoso"));
            } else {
                JsfUtil.addErrorMessage(ResourceBundle.getBundle("/Bundle").getString("documental_GUINivelAcceso_Messages_pEliminarNivelErroneo"));
            }
        } catch (Exception e) {
            System.out.println("El error es nivel1: " + e.toString());
            JsfUtil.addErrorMessage(ResourceBundle.getBundle("/Bundle").getString("documental_GUINivelAcceso_Messages_pEliminarNivelErroneo") + " " + e.toString());
        }
    }

}

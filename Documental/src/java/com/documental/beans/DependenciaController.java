/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.documental.beans;

import com.documental.bo.Dependencia;
import com.documental.servicios.ServicioDependencia;
import com.documental.servicios.impl.ServicioDependenciaImpl;
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
 * @author enrique
 */
@ManagedBean(name = "beanDependencia")
@SessionScoped
public class DependenciaController {

    private Dependencia current;
    private ServicioDependencia servicio;
    private DataModel items = null;
    private PaginationHelper pagination;
    private List<Dependencia> listDependencia = null;
    private boolean activo;

    public DependenciaController() {
    }

    public List<Dependencia> getListDependencia() {
        return listDependencia;
    }

    public void setListDependencia(List<Dependencia> listDependencia) {
        this.listDependencia = listDependencia;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public Dependencia getSelected() {
        if (current == null) {
            current = new Dependencia();
        }
        return current;
    }

    public ServicioDependencia getServicio() {
        if (servicio == null) {
            servicio = new ServicioDependenciaImpl();
        }
        return servicio;
    }

    public DataModel getItems() {
        if (items == null) {
            items = getPagination().createPageDataModel();
        }
        return items;
    }

    public String prepareList() {
        return "/GUI/Administrador/Dependencia/GUIDependenciaList";
    }

    public String prepareCreate() {
        return "/GUI/Administrador/Dependencia/GUIDependenciaCrear";
    }

    public String prepareEdit(Dependencia dependencia) {
        current = getServicio().buscarPorClave(dependencia.getIdDependencia());
        return "/GUI/Administrador/Dependencia/GUIDependenciaEditar";
    }

    public void prepareDelete(Dependencia dependencia) {
        current = dependencia;
    }

    public String volver() {
        return "/GUI/Administrador/Dependencia/GUIDependenciaList";
    }

    public void create() {
        String respuesta = "";
        try {
            Integer id = getServicio().getMaxId();
            id++;
            current.setBooleanEstado(activo);
            current.setIdDependencia(id);
            respuesta = servicio.salvarDependencia(current);
            items = null;
            if (respuesta.equals("Operación Exitosa")) {
                JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("documental_GUIDependencia_Messages_pCreateDependenciaExitoso"));
            } else {
                JsfUtil.addErrorMessage(ResourceBundle.getBundle("/Bundle").getString("documental_GUIDependencia_Messages_pCreateDependenciaErroneo"));
            }
        } catch (Exception e) {
            JsfUtil.addErrorMessage(ResourceBundle.getBundle("/Bundle").getString("documental_GUIDependencia_Messages_pCreateDependenciaErroneo") + " " + e.toString());
        }
    }

    public void edit() {
        String respuesta = "";
        try {
            respuesta = getServicio().salvarDependencia(current);
            items = null;
            if (respuesta.equals("Operación Exitosa")) {
                JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("documental_GUIDependencia_Messages_pEditDependenciaExitoso"));
            } else {
                JsfUtil.addErrorMessage(ResourceBundle.getBundle("/Bundle").getString("documental_GUIDependencia_Messages_pEditDependenciaErroneo"));
            }
        } catch (Exception e) {
            JsfUtil.addErrorMessage(ResourceBundle.getBundle("/Bundle").getString("documental_GUIDependencia_Messages_pEditDependenciaErroneo") + " " + e.toString());
        }
    }

    public void delete() {
        String rpta;
        try {
            rpta = getServicio().borrarDependencia(current);
            if (rpta.equals("Operación Exitosa")) {
                items = null;
                JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("documental_GUIDependencia_Messages_pDeleteDependenciaExitoso"));
            } else {
                JsfUtil.addErrorMessage(ResourceBundle.getBundle("/Bundle").getString("documental_GUIDependencia_Messages_pDeleteDependenciaErroneo"));
            }
        } catch (Exception e) {
            System.out.println("El error es dependencia1: " + e.toString());
            JsfUtil.addErrorMessage(ResourceBundle.getBundle("/Bundle").getString("documental_GUIDependencia_Messages_pDeleteDependenciaErroneo") + " " + e.toString());
        }
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
                    setListDependencia(getServicio().buscarTodosDependencia());
                    return new ListDataModel(getListDependencia());
                }
            };
        }
        return pagination;
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.documental.beans;

import com.documental.bo.Tarea;
import com.documental.servicios.ServicioTarea;
import com.documental.servicios.impl.ServicioTareaImpl;
import com.documental.util.JsfUtil;
import com.documental.util.PaginationHelper;
import java.util.List;
import java.util.ResourceBundle;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

@ManagedBean(name = "beanTarea")
@SessionScoped
public class TareaController {

    private Tarea current;
    private ServicioTarea servicio;
    private DataModel items = null;
    private PaginationHelper pagination;
    private List<Tarea> listTarea = null;

    public TareaController() {
    }

    public List<Tarea> getListTarea() {
        return listTarea;
    }

    public void setListTarea(List<Tarea> listTarea) {
        this.listTarea = listTarea;
    }

    public Tarea getSelected() {
        if (current == null) {
            current = new Tarea();
        }
        return current;
    }

    public ServicioTarea getServicio() {
        if (servicio == null) {
            servicio = new ServicioTareaImpl();
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
        return "/GUI/Administrador/Tarea/GUITareaList";
    }

    public String prepareCreate() {
        return "/GUI/Administrador/Tarea/GUITareaCrear";
    }

    public String prepareEdit(Tarea tarea) {
        current = getServicio().buscarPorClave(tarea.getIdTarea());
        return "/GUI/Administrador/Tarea/GUITareaEditar";
    }

    public void prepareDelete(Tarea tarea) {
        current = tarea;
    }

    public String volver() {
        return "/GUI/Administrador/Tarea/GUITareaList";
    }

    public void create() {
        String respuesta = "";
        try {
            Integer id = getServicio().getMaxId();
            id++;
            current.setIdTarea(id);
            respuesta = servicio.salvarTarea(current);
            items = null;
            if (respuesta.equals("Operación Exitosa")) {
                JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("documental_GUITarea_Messages_pCreateTareaExitoso"));
            } else {
                JsfUtil.addErrorMessage(ResourceBundle.getBundle("/Bundle").getString("documental_GUITarea_Messages_pCreateTareaErroneo"));
            }
        } catch (Exception e) {
            JsfUtil.addErrorMessage(ResourceBundle.getBundle("/Bundle").getString("documental_GUITarea_Messages_pCreateTareaErroneo") + " " + e.toString());
        }
    }

    public void edit() {
        String respuesta = "";
        try {
            respuesta = getServicio().salvarTarea(current);
            items = null;
            if (respuesta.equals("Operación Exitosa")) {
                JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("documental_GUITarea_Messages_pEditTareaExitoso"));
            } else {
                JsfUtil.addErrorMessage(ResourceBundle.getBundle("/Bundle").getString("documental_GUITarea_Messages_pEditTareaErroneo"));
            }
        } catch (Exception e) {
            JsfUtil.addErrorMessage(ResourceBundle.getBundle("/Bundle").getString("documental_GUITarea_Messages_pEditTareaErroneo") + " " + e.toString());
        }
    }

    public void delete() {
        String rpta;
        try {
            rpta = getServicio().borrarTarea(current);
            if (rpta.equals("Operación Exitosa")) {
                items = null;
                JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("documental_GUITarea_Messages_pEliminarTareaExitoso"));
            } else {
                JsfUtil.addErrorMessage(ResourceBundle.getBundle("/Bundle").getString("documental_GUITarea_Messages_pEliminarTareaErroneo"));
            }
        } catch (Exception e) {
            System.out.println("El error es tarea1: " + e.toString());
            JsfUtil.addErrorMessage(ResourceBundle.getBundle("/Bundle").getString("documental_GUITarea_Messages_pEliminarTareaErroneo") + " " + e.toString());
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
                    setListTarea(getServicio().buscarTodosTarea());
                    return new ListDataModel(getListTarea());
                }
            };
        }
        return pagination;
    }
}

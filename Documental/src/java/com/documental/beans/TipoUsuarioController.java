/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.documental.beans;

import com.documental.bo.NivelAcceso;
import com.documental.bo.TipoUsuario;
import com.documental.servicios.ServicioNivelAcceso;
import com.documental.servicios.ServicioTipoUsuario;
import com.documental.servicios.impl.ServicioNivelAccesoImpl;
import com.documental.servicios.impl.ServicioTipoUsuarioImpl;
import com.documental.util.JsfUtil;
import com.documental.util.PaginationHelper;
import java.util.List;
import java.util.ResourceBundle;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

@ManagedBean(name = "beanTipoUsuario")
@SessionScoped
public class TipoUsuarioController {

    private TipoUsuario current;
    private NivelAcceso currentN;
    private ServicioTipoUsuario servicio;
    private ServicioNivelAcceso servicioN;
    private DataModel items = null;
    private PaginationHelper pagination;
    private List<TipoUsuario> listTipoUsuario = null;
    private Integer idNivelAcceso;

    public TipoUsuarioController() {
    }

    public List<TipoUsuario> getListTipoUsuario() {
        return listTipoUsuario;
    }

    public void setListTipoUsuario(List<TipoUsuario> listTipoUsuario) {
        this.listTipoUsuario = listTipoUsuario;
    }
    
    public List<NivelAcceso> getListNivelAcceso() {
        return getServicioN().buscarTodosNiveles();
    }

    public Integer getIdNivelAcceso() {
        return idNivelAcceso;
    }

    public void setIdNivelAcceso(Integer idNivelAcceso) {
        this.idNivelAcceso = idNivelAcceso;
    }
        
    public TipoUsuario getSelected() {
        if (current == null) {
            current = new TipoUsuario();
        }
        return current;
    }

    public NivelAcceso getSelectedN() {
        if (currentN == null) {
            currentN = new NivelAcceso();
        }
        return currentN;
    }

    public ServicioTipoUsuario getServicio() {
        if (servicio == null) {
            servicio = new ServicioTipoUsuarioImpl();
        }
        return servicio;
    }

    public ServicioNivelAcceso getServicioN() {
        if (servicioN == null) {
            servicioN = new ServicioNivelAccesoImpl();
        }
        return servicioN;
    }

    public DataModel getItems() {
        if (items == null) {
            items = getPagination().createPageDataModel();
        }
        return items;
    }

    public String prepareList() {
        return "/GUI/Administrador/TipoUsuario/GUITipoUsuarioList";
    }

    public String prepareCreate() {
        return "/GUI/Administrador/TipoUsuario/GUITipoUsuarioCrear";
    }

    public String prepareEdit(TipoUsuario tipoUsuario) {
        current = getServicio().buscarPorClave(tipoUsuario.getIdTipoUsuario());
        idNivelAcceso = current.getIdNivelAcceso().getId();
        return "/GUI/Administrador/TipoUsuario/GUITipoUsuarioEditar";
    }

    public void prepareDelete(TipoUsuario tipoUsuario) {
        current = tipoUsuario;
    }

    public String volver() {
        return "/GUI/Administrador/TipoUsuario/GUITipoUsuarioList";
    }

    public void create() {
        String respuesta = "";
        try {
            Integer id = getServicio().getMaxId();
            id++;
            current.setIdTipoUsuario(id);
            current.setIdNivelAcceso(new NivelAcceso(idNivelAcceso));
            respuesta = getServicio().salvarTipoUsuario(current);
            items = null;
            if (respuesta.equals("Operación Exitosa")) {
                JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("documental_GUITipoUsuario_Messages_pCreateTipoUsuarioExitoso"));
            } else {
                JsfUtil.addErrorMessage(ResourceBundle.getBundle("/Bundle").getString("documental_GUITipoUsuario_Messages_pCreateTipoUsuarioErroneo"));
            }
        } catch (Exception e) {
            JsfUtil.addErrorMessage(ResourceBundle.getBundle("/Bundle").getString("documental_GUITipoUsuario_Messages_pCreateTipoUsuarioErroneo") + " " + e.toString());
        }
    }

    public void edit() {
        String respuesta = "";
        try {
            current.setIdNivelAcceso(new NivelAcceso(idNivelAcceso));
            respuesta = getServicio().salvarTipoUsuario(current);
            items = null;
            if (respuesta.equals("Operación Exitosa")) {
                JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("documental_GUITipoUsuario_Messages_pEditTareaExitoso"));
            } else {
                JsfUtil.addErrorMessage(ResourceBundle.getBundle("/Bundle").getString("documental_GUITipoUsuario_Messages_pEditTareaErroneo"));
            }
        } catch (Exception e) {
            JsfUtil.addErrorMessage(ResourceBundle.getBundle("/Bundle").getString("documental_GUITipoUsuario_Messages_pEditTareaErroneo") + " " + e.toString());
        }
    }

    public void delete() {
        String rpta;
        try {
            rpta = getServicio().borrarTipoUsuario(current);
            if (rpta.equals("Operación Exitosa")) {
                items = null;
                JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("documental_GUITipoUsuario_Messages_pDeleteTipoUsuarioExitoso"));
            } else {
                JsfUtil.addErrorMessage(ResourceBundle.getBundle("/Bundle").getString("documental_GUITipoUsuario_Messages_pDeleteTipoUsuarioErroneo"));
            }
        } catch (Exception e) {
            System.out.println("El error es tipoUsuario1: " + e.toString());
            JsfUtil.addErrorMessage(ResourceBundle.getBundle("/Bundle").getString("documental_GUITipoUsuario_Messages_pDeleteTipoUsuarioErroneo") + " " + e.toString());
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
                    setListTipoUsuario(getServicio().buscarTodosTipoUsuario());
                    return new ListDataModel(getListTipoUsuario());
                }
            };
        }
        return pagination;
    }

}

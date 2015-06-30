/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.documental.beans;

import com.documental.bo.Tarea;
import com.documental.bo.TipoUsuario;
import com.documental.servicios.ServicioTarea;
import com.documental.servicios.ServicioTipoUsuario;
import com.documental.servicios.impl.ServicioTareaImpl;
import com.documental.servicios.impl.ServicioTipoUsuarioImpl;
import com.documental.util.JsfUtil;
import com.documental.util.PaginationHelper;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import org.primefaces.model.DualListModel;

@ManagedBean(name = "beanTusuarioTarea")
@SessionScoped
public class TipoUsuarioTareaController {
    
    private Tarea current;
    private TipoUsuario currentT;
    private ServicioTarea servicio;
    private ServicioTipoUsuario servicioT;
    private DataModel items = null;
    private PaginationHelper pagination;
    private List<TipoUsuario> listTipoUsuario = null;
    List<Tarea> tareaSource = new ArrayList<Tarea>();
    List<Tarea> tareaTarget = new ArrayList<Tarea>();
    List<Tarea> tipoUsuarioSource = new ArrayList<Tarea>();
    List<Tarea> tipoUsuarioTarget = new ArrayList<Tarea>();
    private DualListModel<Tarea> tarea;
    private Long idTuEtapa;
    private Long tipoUsuario;
    private String operacion;
    
    public TipoUsuarioTareaController() {
    }
    
    public ServicioTarea getServicio() {
        if (servicio == null) {
            servicio = new ServicioTareaImpl();
        }
        return servicio;
    }
    
    public ServicioTipoUsuario getServicioT() {
        if (servicioT == null) {
            servicioT = new ServicioTipoUsuarioImpl();
        }
        return servicioT;
    }
    
    public Tarea getSelected() {
        if (current == null) {
            current = new Tarea();
        }
        return current;
    }
    
    public TipoUsuario getSelectedT() {
        if (currentT == null) {
            currentT = new TipoUsuario();
        }
        return currentT;
    }
    
    public DataModel getItems() {
        if (items == null) {
            items = getPagination().createPageDataModel();
        }
        return items;
    }
    
    public DualListModel<Tarea> getTarea() {
        return tarea;
    }
    
    public void setTarea(DualListModel<Tarea> tarea) {
        this.tarea = tarea;
    }
    
    public List<TipoUsuario> getListTipoUsuario() {
        return listTipoUsuario;
    }
    
    public void setListTipoUsuario(List<TipoUsuario> listTipoUsuario) {
        this.listTipoUsuario = listTipoUsuario;
    }
    
    public Long getIdTuEtapa() {
        return idTuEtapa;
    }
    
    public void setIdTuEtapa(Long idTuEtapa) {
        this.idTuEtapa = idTuEtapa;
    }
    
    public Long getTipoUsuario() {
        return tipoUsuario;
    }
    
    public void setTipoUsuario(Long tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }
    
    public String getOperacion() {
        return operacion;
    }
    
    public void setOperacion(String operacion) {
        this.operacion = operacion;
    }
    
    public String prepareList() {
        return "/GUI/Administrador/TipoUsuarioTarea/GUITipoUsuarioTareaList";
    }
    
    public String volver() {
        return "/GUI/Administrador/TipoUsuarioTarea/GUITipoUsuarioTareaList";
    }
    
    public void loadDualList() {
        tareaSource = getServicio().buscarTodosTarea();
        tarea = new DualListModel<Tarea>(tareaSource, tareaTarget);
    }
    
    public void localEditData() {
        try {
            tareaSource = getServicio().buscarTodosTarea();
            tareaTarget = getServicio().buscarPorEntityList(currentT.getIdTipoUsuario());
            for (Tarea tareaTarget1 : tareaTarget) {
                for (int j = 0; j < tareaSource.size(); j++) {
                    if (tareaSource.get(j).getIdTarea() == tareaTarget1.getIdTarea()) {
                        tareaSource.remove(j);
                    }
                }
            }
            setTarea(new DualListModel<Tarea>(tareaSource, tareaTarget));
        } catch (Exception e) {
            
        }
    }
    
    public String prepareEdit(TipoUsuario tUsuario) {
        currentT = getServicioT().buscarPorEntity(tUsuario.getIdTipoUsuario());
        if (currentT == null) {
            currentT = tUsuario;
            loadDualList();
            operacion = "C";
            return "/GUI/Administrador/TipoUsuarioTarea/GUITipoUsuarioTareaCrear";
        } else {
            currentT = tUsuario;
            operacion = "E";
            localEditData();
            return "/GUI/Administrador/TipoUsuarioTarea/GUITipoUsuarioTareaEditar";
        }
    }
    
    public void setEntityTareasList() {
        String tarea_;
        Long id = null;
        if (operacion.equals("E")) {
            getServicio().borrarNative(currentT.getIdTipoUsuario());            
        }        
        try {
            for (int i = 0; i < tarea.getTarget().size(); i++) {
                tarea_ = tarea.getTarget().get(i) + "";
                getServicio().insertarNative(currentT.getIdTipoUsuario(), Integer.parseInt(tarea_));                
            }
        } catch (Exception e) {
            System.out.println("El error es tuEtapaTarea1: " + e.toString());
            JsfUtil.addErrorMessage(ResourceBundle.getBundle("/Bundle").getString("documental_GUITuEtapaCreate_Messages_pCreateTuEtapaErroneo") + " " + e.toString());
        } finally {
            items = null;
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("documental_GUITuEtapaCreate_Messages_pCreateTuEtapaExitoso"));
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
                    setListTipoUsuario(getServicioT().buscarTodosTipoUsuario());
                    return new ListDataModel(getListTipoUsuario());
                }
            };
        }
        return pagination;
    }
    
}

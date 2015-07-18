package com.documental.beans;

import com.documental.bo.Documento;
import com.documental.bo.Login;
import com.documental.bo.NivelAcceso;
import com.documental.bo.Tarea;
import com.documental.bo.Tipo;
import com.documental.bo.TipoUsuario;
import com.documental.enums.EstadosDocumentoEnum;
import com.documental.enums.VisibilidadDocumentoEnum;
import com.documental.servicios.ServicioDocumento;
import com.documental.servicios.ServicioLogin;
import com.documental.servicios.ServicioTipo;
import com.documental.servicios.ServicioTipoUsuario;
import com.documental.servicios.impl.ServicioDocumentoImpl;
import com.documental.servicios.impl.ServicioLoginImpl;
import com.documental.servicios.impl.ServicioTipoImpl;
import com.documental.servicios.impl.ServicioTipoUsuarioImpl;
import com.documental.util.EncripcionUtil;
import com.documental.util.JsfUtil;
import com.documental.util.PaginationHelper;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.text.SimpleDateFormat;
import java.util.Date;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
 
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author DiegoM
 */
@ManagedBean(name = "beanConsulta")
@SessionScoped
public class ConsultaController {

    private Documento current;
    private Documento docTrabajo;
    private DataModel items = null;
    private Date date5;
    private Date date6;
    private PaginationHelper pagination;
    private ServicioDocumento servicio;
    private ServicioTipo servicioTipo;
    private ServicioLogin servicioLogin;
    private ServicioTipoUsuario servicioT;
    private NivelAcceso nivelAcceso;
    private List<String> listaPermisosUsuario;
    private List<Login> listLogin = null;
    private List<Documento> listDocumento = null;
    private Login currentA;
    private static String usuario;
    private String contrasena;
    private Integer idTipo;
    private Integer tipoUsuario;
    //Variables de sesion
    static ExternalContext ectx;
    static HttpServletRequest request;
    static HttpSession session;

    public ConsultaController() {
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        ConsultaController.usuario = usuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public List<Documento> getListDocumento() {
        return listDocumento;
    }

    public void setListDocumento(List<Documento> listDocumento) {
        this.listDocumento = listDocumento;
    }

    public Date getDate5() {
        return date5;
    }
 
    public void setDate5(Date date5) {
        this.date5 = date5;
    }
    
    public Date getDate6() {
        return date6;
    }
 
    public void setDate6(Date date6) {
        this.date6 = date6;
    }
    
    public Documento getSelected() {
        if (current == null) {
            current = new Documento();
        }
        return current;
    }
    
    public Login getSelectedA() {
        if (currentA == null) {
            currentA = new Login();
        }
        return currentA;
    }
    
    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public List<TipoUsuario> getListTipoUsuarios() {
        return getServicioT().buscarTodosTipoUsuario();
    }
    
    public EstadosDocumentoEnum[] getEstadosDocumento(){
       return EstadosDocumentoEnum.values();
    }
    
    public VisibilidadDocumentoEnum[] getVisibilidadesDocumento(){
       return VisibilidadDocumentoEnum.values();
    }
    
    
    public Documento getDocTrabajo() {        
        if (docTrabajo == null) {
            docTrabajo = new Documento();
        }
        return docTrabajo;
    }
    
    private ServicioTipo getServicioTipo() {
        if (servicioTipo == null) {
            servicioTipo = new ServicioTipoImpl();
        }
        return servicioTipo;
    }
    public List<Tipo> getListaTipos(){
        return getServicioTipo().consultarTodosTipos();
    }

    public List<Login> getListLogin() {
        listLogin = getServicioLogin().buscarTodosLogin();
        return listLogin;
    }   

    public Integer getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(Integer tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }
    
    public Integer getIdTipo() {
        return idTipo;
    }

    public DataModel getItems() {
        if (items == null) {
            items = getPagination().createPageDataModel();
        }
        return items;
    }

    public ServicioDocumento getServicio() {
        if (servicio == null) {
            servicio = new ServicioDocumentoImpl();
        }
        return servicio;
    }

    public ServicioLogin getServicioLogin() {
        if (servicioLogin == null) {
            servicioLogin = new ServicioLoginImpl();
        }
        return servicioLogin;
    }

    public void setServicioLogin(ServicioLogin servicioLogin) {
        this.servicioLogin = servicioLogin;
    }
    
    

    public ServicioTipoUsuario getServicioT() {
        if (servicioT == null) {
            servicioT = new ServicioTipoUsuarioImpl();
        }
        return servicioT;
    }

    public String prepareList() {

        return "GUI/Gestion/Consultas/GUIConsultaList";
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
                    setListDocumento(getServicio().consultarTodosDocumentos());
                    return new ListDataModel(getListDocumento());
                }
            };
        }
        return pagination;
    }

}

package com.documental.beans;

import com.documental.bo.Login;
import com.documental.bo.NivelAcceso;
import com.documental.bo.Tarea;
import com.documental.bo.TipoUsuario;
import com.documental.servicios.ServicioLogin;
import com.documental.servicios.ServicioTipoUsuario;
import com.documental.servicios.impl.ServicioLoginImpl;
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

/**
 *
 * @author DiegoM
 */
@ManagedBean(name = "beanUsuario")
@SessionScoped
public class LoginController {

    private Login current;
    private Login currentC;
    private DataModel items = null;
    private PaginationHelper pagination;
    private ServicioLogin servicio;
    private ServicioTipoUsuario servicioT;
    private NivelAcceso nivelAcceso;
    private List<String> listaPermisosUsuario;
    private List<Login> listLogin = null;
    private static String usuario;
    private String contrasena;
    private Integer tipoUsuario;
    //Variables de sesion
    static ExternalContext ectx;
    static HttpServletRequest request;
    static HttpSession session;

    public LoginController() {
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        LoginController.usuario = usuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public List<TipoUsuario> getListTipoUsuarios() {
        return getServicioT().buscarTodosTipoUsuario();
    }

    public List<Login> getListLogin() {
        return listLogin;
    }

    public void setListLogin(List<Login> listLogin) {
        this.listLogin = listLogin;
    }

    public Integer getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(Integer tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public Login getSelected() {
        if (current == null) {
            current = new Login();
        }
        return current;
    }

    public Login getSelectedC() {
        if (currentC == null) {
            currentC = new Login();
        }
        return currentC;
    }

    public DataModel getItems() {
        if (items == null) {
            items = getPagination().createPageDataModel();
        }
        return items;
    }

    public ServicioLogin getServicio() {
        if (servicio == null) {
            servicio = new ServicioLoginImpl();
        }
        return servicio;
    }

    public ServicioTipoUsuario getServicioT() {
        if (servicioT == null) {
            servicioT = new ServicioTipoUsuarioImpl();
        }
        return servicioT;
    }

    private void cargarNivelAcceso() {
        try {
            nivelAcceso = current.getTipoUsuario().getIdNivelAcceso();
        } catch (Exception e) {
            e.printStackTrace();
            nivelAcceso = null;
        }
    }

    public String prepareCreate() {
        return "/GUI/Administrador/Usuarios/GUIUsuarioCrear";
    }

    public String prepareList() {

        return "/GUI/Administrador/Usuarios/GUIUsuarioList";
    }

    public String prepareListEdit() {

        return "/GUI/Administrador/Usuarios/GUIUsuarioListEdit";
    }

    public String prepareEdit(Login usuario) {
        current = usuario;
        return "/GUI/Administrador/Usuarios/GUIUsuarioEditar";
    }
    
    public String volver() {
        return "/GUI/Administrador/Usuarios/GUIUsuarioListEdit";
    }

    private boolean cargarPermisos() {
        try {
            if (current.getTipoUsuario().getTareaCollection().size() <= 0) {
                return false;
            }
            listaPermisosUsuario = new ArrayList<String>();
            for (Tarea tarea : current.getTipoUsuario().getTareaCollection()) {
                listaPermisosUsuario.add(tarea.getNombreTarea());
            }

            return true;

        } catch (Exception e) {
            return false;
        }
    }

    public boolean asPermission(String nombreTarea) {
        return listaPermisosUsuario.contains(nombreTarea);
    }

    public String autenticar() throws IOException {
        Login login = null;
        try {
            login = getServicio().obtenerLogin(usuario);
        } catch (javax.persistence.NoResultException ex) {
            JsfUtil.addErrorMessage(ResourceBundle.getBundle("/Bundle").getString("Ilogin_ErrorCredenciales"));
        }
        contrasena = EncripcionUtil.codif(contrasena);
        if (contrasena.equals(login.getContrasena())) {
            current = login;
            crearSession();
            if (!cargarPermisos()) {
                JsfUtil.addErrorMessage(ResourceBundle.getBundle("/Bundle").getString("Ilogin_ErrorNivelDeAcceso"));
                return null;
            }
            cargarNivelAcceso();
            FacesContext.getCurrentInstance().getExternalContext().redirect("/Documental/faces/menuDocumental.xhtml");
            return null;
        } else {
            JsfUtil.addErrorMessage(ResourceBundle.getBundle("/Bundle").getString("Ilogin_ErrorCredenciales"));
        }
        return "index_MenuPrincipal";
    }

    public void cerrarSesion() throws IOException {
        System.out.println(".......");
        FacesContext context = FacesContext.getCurrentInstance();
        ExternalContext externalContext = context.getExternalContext();
        Object session = externalContext.getSession(false);
        HttpSession httpSession = (HttpSession) session;
        httpSession.invalidate();
        FacesContext.getCurrentInstance().getExternalContext().redirect("http://" + ((HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest()).getServerName()
                + ":" + ((HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest()).getServerPort()
                + ((HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest()).getContextPath()
                + ((HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest()).getServletPath()
                + "/index.xhtml");
    }

    public static void crearSession() {
        //Aqui creo la session en caso de que el usuario exista!!
        ectx = FacesContext.getCurrentInstance().getExternalContext();
        request = (HttpServletRequest) ectx.getRequest();
        session = request.getSession();
        session.setAttribute("user", usuario);
    }

    public void create() {
        String respuesta = "";
        try {
            Integer id = getServicio().getMaxId();
            id++;
            currentC.setIdLogin(id);
            currentC.setTipoUsuario(new TipoUsuario(tipoUsuario));
            currentC.setContrasena(EncripcionUtil.Encriptar(currentC.getContrasena()));
            respuesta = getServicio().salvarLogin(currentC);
            if (respuesta.equals("Operación Exitosa")) {
                currentC = null;
                JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("documental_GUIUsuario_Messages_pCreateUsuarioExitoso"));
            } else {
                JsfUtil.addErrorMessage(ResourceBundle.getBundle("/Bundle").getString("documental_GUIUsuario_Messages_pCreateUsuarioErroneo"));
            }
        } catch (Exception e) {
            JsfUtil.addErrorMessage(ResourceBundle.getBundle("/Bundle").getString("documental_GUIUsuario_Messages_pCreateUsuarioExcepcion") + " " + e.toString());
        }
    }

    public void edit() {
        String respuesta = "";
        try {
            respuesta = getServicio().salvarLogin(current);
            items = null;
            if (respuesta.equals("Operación Exitosa")) {
                JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("documental_GUIUsuario_Messages_pEditUsuarioExitoso"));
            } else {
                JsfUtil.addErrorMessage(ResourceBundle.getBundle("/Bundle").getString("documental_GUIUsuario_Messages_pEditUsuarioErroneo"));
            }
        } catch (Exception e) {
            JsfUtil.addErrorMessage(ResourceBundle.getBundle("/Bundle").getString("documental_GUIUsuario_Messages_pEditUsuarioErroneo") + " " + e.toString());
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
                    setListLogin(getServicio().buscarTodosLogin());
                    return new ListDataModel(getListLogin());
                }
            };
        }
        return pagination;
    }

}

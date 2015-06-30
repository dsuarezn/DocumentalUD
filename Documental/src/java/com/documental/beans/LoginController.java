/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.documental.beans;

import com.documental.bo.Login;
import com.documental.bo.NivelAcceso;
import com.documental.bo.Tarea;
import com.documental.servicios.ServicioLogin;
import com.documental.servicios.impl.ServicioLoginImpl;
import com.documental.util.EncripcionUtil;
import com.documental.util.JsfUtil;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
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
    private ServicioLogin servicio;
    private NivelAcceso nivelAcceso;
    private List<String> listaPermisosUsuario;
    private static String usuario;
    private String contrasena;
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
        
    public Login getSelected() {
        if (current == null) {
            current = new Login();
        }
        return current;
    }

    public ServicioLogin getServicio() {
        if (servicio == null) {
            servicio = new ServicioLoginImpl();
        }
        return servicio;
    }

    private void cargarNivelAcceso() {
        try {
            nivelAcceso = current.getTipoUsuario().getIdNivelAcceso();
        } catch (Exception e) {
            e.printStackTrace();
            nivelAcceso = null;
        }
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
        System.out.println(listaPermisosUsuario.contains(nombreTarea));
        return listaPermisosUsuario.contains(nombreTarea);
    }

    public String autenticar() throws IOException {
        Login login = null;
        try {
            login = getServicio().buscarPorClave(usuario);
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

}

package com.documental.beans;

import com.documental.bo.Dependencia;
import com.documental.bo.DependenciaDirector;
import com.documental.bo.DependenciaEmpleado;
import com.documental.bo.Login;
import com.documental.bo.NivelAcceso;
import com.documental.bo.Tarea;
import com.documental.bo.TipoUsuario;
import com.documental.servicios.ServicioDependencia;
import com.documental.servicios.ServicioDependenciaDirector;
import com.documental.servicios.ServicioDependenciaEmpleado;
import com.documental.servicios.ServicioDirector;
import com.documental.servicios.ServicioEmpleado;
import com.documental.servicios.ServicioLogin;
import com.documental.servicios.ServicioTipoUsuario;
import com.documental.servicios.impl.ServicioDependenciaDirectorImpl;
import com.documental.servicios.impl.ServicioDependenciaEmpleadoImpl;
import com.documental.servicios.impl.ServicioDependenciaImpl;
import com.documental.servicios.impl.ServicioDirectorImpl;
import com.documental.servicios.impl.ServicioEmpleadoImpl;
import com.documental.servicios.impl.ServicioLoginImpl;
import com.documental.servicios.impl.ServicioTipoUsuarioImpl;
import com.documental.util.EncripcionUtil;
import com.documental.util.JsfUtil;
import com.documental.util.PaginationHelper;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
 * @author DiegoM mysqldump --user=root --password=root acme >
 * copia_seguridad.sql mysql --user=root --password=root < copia_seguridad.sql
 *
 */
@ManagedBean(name = "beanUsuario")
@SessionScoped
public class LoginController {

    private Login current;
    private Login currentC;
    private int currentDep;
    private DataModel items = null;
    private PaginationHelper pagination;
    private ServicioLogin servicio;
    private ServicioTipoUsuario servicioT;
    private ServicioDependencia servicioDependencia;
    private ServicioDirector servicioDirector;
    private ServicioEmpleado servicioEmpleado;
    private ServicioDependenciaDirector servicioDependenciaDirector;
    private ServicioDependenciaEmpleado servicioDependenciaEmpleado;
    private NivelAcceso nivelAcceso;
    private List<String> listaPermisosUsuario;
    private List<Login> listLogin = null;
    private List<Dependencia> listDependencia;
    private static String usuario;
    private String contrasena;
    private final static String estado = "A";
    private Integer tipoUsuario;
    private boolean desactivar = true;
    //Variables de sesion
    static ExternalContext ectx;
    static HttpServletRequest request;
    static HttpSession session;

    public LoginController() {
    }

    public int getCurrentDep() {
        return currentDep;
    }

    public void setCurrentDep(int currentDep) {
        this.currentDep = currentDep;
    }

    public ServicioEmpleado getServicioEmpleado() {
        if (servicioEmpleado == null) {
            servicioEmpleado = new ServicioEmpleadoImpl();
        }
        return servicioEmpleado;
    }

    public ServicioDependenciaDirector getServicioDependenciaDirector() {
        if (servicioDependenciaDirector == null) {
            servicioDependenciaDirector = new ServicioDependenciaDirectorImpl();
        }
        return servicioDependenciaDirector;
    }

    public ServicioDependenciaEmpleado getServicioDependenciaEmpleado() {
        if (servicioDependenciaEmpleado == null) {
            servicioDependenciaEmpleado = new ServicioDependenciaEmpleadoImpl();
        }
        return servicioDependenciaEmpleado;
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

    public boolean isDesactivar() {
        return desactivar;
    }

    public void setDesactivar(boolean desactivar) {
        this.desactivar = desactivar;
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

    public ServicioDependencia getServicioDependencia() {
        if (servicioDependencia == null) {
            servicioDependencia = new ServicioDependenciaImpl();
        }
        return servicioDependencia;
    }

    public List<Dependencia> getListDependencia() {
        return getServicioDependencia().buscarDependenciasActivas();
    }

    public ServicioDirector getServicioDirector() {
        if (servicioDirector == null) {
            return new ServicioDirectorImpl();
        }
        return servicioDirector;
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
        currentC = usuario;
        try {
            currentC.setContrasena(EncripcionUtil.Desencriptar(currentC.getContrasena()));
        } catch (Exception ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
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
            contrasena = EncripcionUtil.Encriptar(contrasena);
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
        } catch (Exception ex) {
            JsfUtil.addErrorMessage(ResourceBundle.getBundle("/Bundle").getString("Ilogin_ErrorCredenciales"));
        }
        //return "index_MenuPrincipal";
        return null;
    }

    public void onTipoUsuarioChange() {
        if (tipoUsuario == 3 || tipoUsuario == 4) {
            desactivar = false;
        } else {
            desactivar = true;
        }
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
            //salvar primero el usuario para evitar errores de consistencia
            respuesta = getServicio().salvarLogin(currentC);
            if (respuesta.equals("Operación Exitosa")) {
                setDependenciaCrear(id);
                currentC = null;
                JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("documental_GUIUsuario_Messages_pCreateUsuarioExitoso"));
            } else {
                JsfUtil.addErrorMessage(ResourceBundle.getBundle("/Bundle").getString("documental_GUIUsuario_Messages_pCreateUsuarioErroneo"));
            }
        } catch (Exception e) {
            JsfUtil.addErrorMessage(ResourceBundle.getBundle("/Bundle").getString("documental_GUIUsuario_Messages_pCreateUsuarioExcepcion") + " " + e.toString());
        }
    }

    public void setDependencia(int id) {
        // salvar en directores o empleados
        //director de Area                
        if (tipoUsuario == 3) {
            //Asginar Dependencia
            DependenciaDirector dep = getServicioDependencia().buscarDependenciaDirector(currentC.getIdLogin());
            if (dep != null) {
                dep.setEstado("I");
                getServicioDependenciaDirector().salvarDependenciaDirector(dep);
            }
            DependenciaDirector director = new DependenciaDirector(currentDep, id);
            director.setEstado(estado);
            director.setFecha(new Date());
            getServicioDirector().salvarDirector(director);
        }
        //Empleado    
        if (tipoUsuario == 4) {
            //Asignar Dependencia            
            DependenciaEmpleado dep = getServicioDependencia().buscarDependenciaEmpleado(currentC.getIdLogin());
            if (dep != null) {
                dep.setEstado("I");
                getServicioDependenciaEmpleado().salvarDependenciaEmpleado(dep);
            }
            DependenciaEmpleado empleado = new DependenciaEmpleado(currentDep, id);
            empleado.setEstado(estado);
            empleado.setFecha(new Date());
            getServicioEmpleado().salvarEmpleado(empleado);
        }
    }
    public void setDependenciaCrear(int id) {
        // salvar en directores o empleados
        //director de Area                
        if (tipoUsuario == 3) {
            //Asginar Dependencia            
            DependenciaDirector director = new DependenciaDirector(currentDep, id);
            director.setEstado(estado);
            director.setFecha(new Date());
            getServicioDirector().salvarDirector(director);
        }
        //Empleado    
        if (tipoUsuario == 4) {
            //Asignar Dependencia                        
            DependenciaEmpleado empleado = new DependenciaEmpleado(currentDep, id);
            empleado.setEstado(estado);
            empleado.setFecha(new Date());
            getServicioEmpleado().salvarEmpleado(empleado);
        }
    }

    public void edit() {
        String respuesta = "";
        try {
            currentC.setTipoUsuario(new TipoUsuario(tipoUsuario));
            currentC.setContrasena(EncripcionUtil.Encriptar(currentC.getContrasena()));
            respuesta = getServicio().salvarLogin(currentC);

            items = null;
            if (respuesta.equals("Operación Exitosa")) {
                setDependencia(currentC.getIdLogin());
                currentC = null;
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

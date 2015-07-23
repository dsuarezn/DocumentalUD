/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.documental.beans;

import com.documental.bo.Dependencia;
import com.documental.bo.Documento;
import com.documental.bo.Historico;
import com.documental.bo.HistoricoPK;
import com.documental.bo.Login;
import com.documental.servicios.ServicioDependencia;
import com.documental.servicios.ServicioDocumento;
import com.documental.servicios.ServicioHistorico;
import com.documental.servicios.ServicioLogin;
import com.documental.servicios.impl.ServicioDependenciaImpl;
import com.documental.servicios.impl.ServicioDocumentoImpl;
import com.documental.servicios.impl.ServicioHistoricoImpl;
import com.documental.servicios.impl.ServicioLoginImpl;
import com.documental.util.JsfUtil;
import com.documental.util.PaginationHelper;
import java.util.Date;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author enrique
 */
@ManagedBean(name = "beanBandeja")
@SessionScoped
public class BandejaController {

    private Historico current;
    private Documento docTrabajo;
    private DataModel items = null;
    private PaginationHelper pagination;
    private List<Historico> listHistorico = null;
    private List<Login> listaEmpleados = null;
    private List<Dependencia> listaDependencias = null;
    private List<Object[]> consolidado;
    private boolean ocultarDependencias = true;
    private Integer dependencia;
    private Integer empleado;
    private String comentario;
    private ServicioDocumento servicioDocumento;
    private ServicioHistorico servicioHistorico;
    private ServicioLogin servicioLogin;
    private ServicioDependencia servicioDependencia;
    private String usuarioActual = ((HttpServletRequest) FacesContext.getCurrentInstance().
            getExternalContext().getRequest()).getSession().getAttribute("user").toString();

    public String getUsuarioActual() {
        return usuarioActual;
    }

    private int usuario = 0;

    public boolean isOcultarDependencias() {
        return ocultarDependencias;
    }

    public void setOcultarDependencias(boolean ocultarDependencias) {
        this.ocultarDependencias = ocultarDependencias;
    }

    public Integer getDependencia() {
        return dependencia;
    }

    public void setDependencia(Integer dependencia) {
        this.dependencia = dependencia;
    }

    public Integer getEmpleado() {
        return empleado;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public void setEmpleado(Integer empleado) {
        this.empleado = empleado;
    }

    public int getUsuario() {
        return usuario;
    }

    public Historico getSelected() {
        if (current == null) {
            current = new Historico();
        }
        return current;
    }

    public List<Object[]> getConsolidado() {
        return consolidado;
    }

    public List<Historico> getListHistorico() {
        return listHistorico;
    }

    public void setListHistorico(List<Historico> listHistorico) {
        this.listHistorico = listHistorico;
    }

    public List<Login> getListaEmpleados() {
        return listaEmpleados;
    }

    public void setListaEmpleados(List<Login> listaEmpleados) {
        this.listaEmpleados = listaEmpleados;
    }

    public List<Dependencia> getListaDependencias() {
        return servicioDependencia.buscarTodosDependencia();
    }

    public void setListaDependencias(List<Dependencia> listaDependencias) {
        this.listaDependencias = listaDependencias;
    }

    public BandejaController() {
    }

    public Documento getDocTrabajo() {
        return docTrabajo;
    }

    public void setDocTrabajo(Documento docTrabajo) {
        this.docTrabajo = docTrabajo;
    }

    public ServicioDocumento getServicioDocumento() {
        if (servicioDocumento == null) {
            servicioDocumento = new ServicioDocumentoImpl();
        }
        return servicioDocumento;
    }

    public ServicioHistorico getServicioHistorico() {
        if (servicioHistorico == null) {
            servicioHistorico = new ServicioHistoricoImpl();
        }
        return servicioHistorico;
    }

    public ServicioLogin getServicioLogin() {
        if (servicioLogin == null) {
            servicioLogin = new ServicioLoginImpl();
        }
        return servicioLogin;
    }

    public ServicioDependencia getServicioDependencia() {
        if (servicioDependencia == null) {
            servicioDependencia = new ServicioDependenciaImpl();
        }
        return servicioDependencia;
    }

    public String prepareList() {
        usuario = getServicioLogin().obtenerLogin(usuarioActual).getIdLogin();
        listHistorico = getServicioHistorico().buscarDestinatarioActivo(usuario);
        /* cambio hecho por Diego Marín
         Se crea la llave foranea de la tabla historico a la tabla documento,
         mediante esta llave foranea es posible alcanzar todos los atributos del Documento
         consultando unicamente la tabla Historico.
         consolidado = new ArrayList<Object[]>();
         Documento tmp = null;
         for (Historico h : listHistorico) {
         tmp = getServicioDocumento().consultarDocumentoPorId(h.getHistoricoPK().getDocumentoId());
         Object[] con = {tmp, h.getLoginOrigen(), tmp.getTipoId().getNombre()};
         consolidado.add(con);
         }*/
        return "/GUI/Gestion/BandejaEntrada/GUIBandejaEntrada_";
    }
    
    public String volver(){
        return "/GUI/Gestion/BandejaEntrada/GUIBandejaEntrada_";
    }

    public String prepareRedirigir() {
        //current = historico;
        Integer dependencia;
        try {
            if (current.getLoginDestinatario().getTipoUsuario().getIdTipoUsuario() == 3) {
                dependencia = getServicioDependencia().buscarDependenciaDirector(current.getLoginDestinatario().getIdLogin()).
                        getDependencia().getIdDependencia();
                ocultarDependencias = false;
            } else {
                dependencia = getServicioDependencia().buscarDependenciaEmpleado(current.getLoginDestinatario().getIdLogin()).
                        getDependencia().getIdDependencia();
                ocultarDependencias = true;
            }

            setListaEmpleados(servicioLogin.obtenerEmpleadosDependencia(dependencia));
        } catch (Exception e) {
            System.out.println("El error es:......... " + e.toString());
        }
        return "/GUI/Gestion/BandejaEntrada/GUIDocumentoRedirigir";
    }

    public DataModel getItems() {
        if (items == null) {
            items = getPagination().createPageDataModel();
        }
        return items;
    }

    public PaginationHelper getPagination() {
        if (pagination == null) {
            pagination = new PaginationHelper((10)) {
                @Override
                public int getItemsCount() {
                    return getConsolidado().size();
                }

                @Override
                public DataModel createPageDataModel() {

                    return new ListDataModel(getConsolidado());
                }
            };
        }
        return pagination;
    }

    public String detalle(Historico historico) {
        current = historico;
        return "/GUI/Gestion/BandejaEntrada/GUIDocumentoDetalle";
    }

    public void redirigir() {
        Historico historicoAuxiliar = new Historico();
        historicoAuxiliar = current;
        getSelected().setLoginOrigen(current.getLoginDestinatario());
        if (validate()) {
            if (dependencia != null) {
                getSelected().setLoginDestinatario(servicioLogin.obtenerDirectorDependencia(dependencia));
            } else if (empleado != null) {
                getSelected().setLoginDestinatario(new Login(empleado));
            }
        }
        getSelected().setHistoricoPK(new HistoricoPK(current.getDocumento().getIdDocumento(),
                current.getLoginOrigen().getIdLogin(),
                current.getLoginDestinatario().getIdLogin()));
        getSelected().setFecha(new Date());
        getSelected().setComentario(comentario);
        String respuesta = servicioHistorico.insertarHistorico(current);
        if (respuesta.equals("Operación Exitosa")) {
            comentario = "";
            historicoAuxiliar.setActivo(false);
            getServicioHistorico().salvarHistorico(historicoAuxiliar);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("documental_GUIRedirigirDocumento_Messages_pCreacionHistoricoExitosa"));
        } else {
            JsfUtil.addErrorMessage(ResourceBundle.getBundle("/Bundle").getString("documental_GUIRedirigirDocumento_Messages_pCreacionHistoricoErroneo"));
        }
    }

    public boolean validate() {
        if ((dependencia == null || dependencia == 0) && (empleado == null || empleado == 0)) {
            JsfUtil.addErrorMessage(ResourceBundle.getBundle("/Bundle").getString("documental_GUIRedirigirDocumento_Messages_pDestinatarioVacio"));
            return false;
        } else if ((dependencia != null) && (empleado != null)) {
            JsfUtil.addErrorMessage(ResourceBundle.getBundle("/Bundle").getString("documental_GUIRedirigirDocumento_Messages_pDestinatarioMultiple"));
            return false;
        } else {
            return true;
        }
    }

}

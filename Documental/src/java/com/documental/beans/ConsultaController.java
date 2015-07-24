package com.documental.beans;

import com.documental.bo.Dependencia;
import com.documental.bo.Documento;
import com.documental.bo.Historico;
import com.documental.bo.Login;
import com.documental.bo.Tipo;
import com.documental.enums.EstadosDocumentoEnum;
import com.documental.enums.VisibilidadDocumentoEnum;
import com.documental.servicios.ServicioDependencia;
import com.documental.servicios.ServicioDocumento;
import com.documental.servicios.ServicioLogin;
import com.documental.servicios.ServicioTipo;
import com.documental.servicios.impl.ServicioDependenciaImpl;
import com.documental.servicios.impl.ServicioDocumentoImpl;
import com.documental.servicios.impl.ServicioLoginImpl;
import com.documental.servicios.impl.ServicioTipoImpl;
import com.documental.util.PaginationHelper;
import java.util.List;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author DiegoM
 */
@ManagedBean(name = "beanConsulta")
@RequestScoped
public class ConsultaController {

    private Documento current;
    private Historico currentH;
    private DataModel items = null;
    private PaginationHelper pagination;
    private ServicioDocumento servicio;
    private List<Documento> listDocumento = null;
    private List<Historico> listHistorico = null;
    private List<Dependencia> listDependencia = null;
    private ServicioTipo servicioTipo;
    private ServicioLogin servicioLogin;
    private ServicioDependencia servicioDependencia;
    private Integer idTipo;
    private Integer loginDestinatario;

    public Integer getLoginDestinatario() {
        return loginDestinatario;
    }

    public void setLoginDestinatario(Integer loginDestinatario) {
        this.loginDestinatario = loginDestinatario;
    }

    public ConsultaController() {
    }

    public Integer getIdTipo() {
        return idTipo;
    }

    public void setIdTipo(Integer idTipo) {
        this.idTipo = idTipo;
    }

    public List<Documento> getListDocumento() {
        return listDocumento;
    }

    public void setListDocumento(List<Documento> listDocumento) {
        this.listDocumento = listDocumento;
    }

    public List<Historico> getListHistorico() {
        return listHistorico;
    }

    public void setListHistorico(List<Historico> listHistorico) {
        this.listHistorico = listHistorico;
    }

    public List<Dependencia> getListDependencia() {
        return getServicioDependencia().buscarDependenciasActivas();
    }

    public void setListDependencia(List<Dependencia> listDependencia) {
        this.listDependencia = listDependencia;
    }
        
    public Documento getSelected() {
        if (current == null) {
            current = new Documento();
        }
        return current;
    }

    public Historico getSelectedH() {
        if (currentH == null) {
            currentH = new Historico();
        }
        return currentH;
    }

    private ServicioTipo getServicioTipo() {
        if (servicioTipo == null) {
            servicioTipo = new ServicioTipoImpl();
        }
        return servicioTipo;
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

    public List<Tipo> getListaTipos() {
        return getServicioTipo().consultarTodosTipos();
    }

    public List<Login> getListLogin() {
        return getServicioLogin().buscarTodosLogin();
    }

    public EstadosDocumentoEnum[] getEstadosDocumento() {
        return EstadosDocumentoEnum.values();
    }

    public VisibilidadDocumentoEnum[] getVisibilidadesDocumento() {
        return VisibilidadDocumentoEnum.values();
    }

    public DataModel getItems() {
        return items;
    }

    public void setItems(DataModel items) {
        this.items = items;
    }

    /*public DataModel getItems() {
     if (items == null) {
     items = getPagination().createPageDataModel();
     }
     return items;
     }*/
    public ServicioDocumento getServicio() {
        if (servicio == null) {
            servicio = new ServicioDocumentoImpl();
        }
        return servicio;
    }

    public String prepareList() {
        items = null;
        return "/GUI/Gestion/Consultas/GUIConsultaList";
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
                    setListHistorico(getServicio().buscarFiltro(currentH));
                    return new ListDataModel(getListHistorico());
                }
            };
        }
        return pagination;
    }

    public void buscar() {
        try {
            items = null;
            pagination = null;
            current.setTipoId(new Tipo(idTipo));
            getSelectedH().setLoginDestinatario(new Login(loginDestinatario));
            getSelectedH().setDocumento(current);
            items = getPagination().createPageDataModel();
        } catch (Exception e) {
            System.out.println("El error es: " + e.toString());
        }
    }

}

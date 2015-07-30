package com.documental.beans;

import com.documental.bo.Comentario;
import com.documental.bo.Dependencia;
import com.documental.bo.Documento;
import com.documental.bo.Historico;
import com.documental.bo.Login;
import com.documental.bo.Tipo;
import com.documental.enums.EstadosDocumentoEnum;
import com.documental.enums.VisibilidadDocumentoEnum;
import com.documental.servicios.ServicioComentario;
import com.documental.servicios.ServicioDependencia;
import com.documental.servicios.ServicioDocumento;
import com.documental.servicios.ServicioHistorico;
import com.documental.servicios.ServicioLogin;
import com.documental.servicios.ServicioTipo;
import com.documental.servicios.impl.ServicioComentarioImpl;
import com.documental.servicios.impl.ServicioDependenciaImpl;
import com.documental.servicios.impl.ServicioDocumentoImpl;
import com.documental.servicios.impl.ServicioHistoricoImpl;
import com.documental.servicios.impl.ServicioLoginImpl;
import com.documental.servicios.impl.ServicioTipoImpl;
import com.documental.util.PaginationHelper;
import java.util.List;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
/**
 *
 * @author DiegoM
 */
@ManagedBean(name = "beanConsulta")
@SessionScoped
public class ConsultaController {
     
    private BandejaController controladorBandeja;
    private boolean accionesDetalle = true;
    private Documento current;
    private Historico currentH;
    private DataModel items = null;
    private PaginationHelper pagination;
    private ServicioDocumento servicio;
    private List<Documento> listDocumento = null;
    private List<Historico> listHistorico = null;
    private List<Historico> listHistoricoDetalle = null;
    private List<Comentario> listComentario = null;
    private List<Dependencia> listDependencia = null;
    private ServicioTipo servicioTipo;
    private ServicioLogin servicioLogin;
    private ServicioHistorico servicioHistorico;
    private ServicioDependencia servicioDependencia;
    private ServicioComentario servicioComentario;
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

    public List<Historico> getListHistoricoDetalle() {
        return listHistoricoDetalle;
    }

    public void setListHistoricoDetalle(List<Historico> listHistoricoDetalle) {
        this.listHistoricoDetalle = listHistoricoDetalle;
    }

    public List<Comentario> getListComentario() {
        return listComentario;
    }

    public void setListComentario(List<Comentario> listComentario) {
        this.listComentario = listComentario;
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
    
    public ServicioHistorico getServicioHistorico() {
        if (servicioHistorico == null) {
            servicioHistorico = new ServicioHistoricoImpl();
        }
        return servicioHistorico;
    }
    
    public ServicioComentario getServicioComentario() {
        if (servicioComentario == null) {
            servicioComentario = new ServicioComentarioImpl();
        }
        return servicioComentario;
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
        pagination = null;
        currentH = null;
        return "/GUI/Gestion/Consultas/GUIConsultaList";
    }
    
    public String volver() {        
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
    
    public String detalle(Historico historico) {
        currentH = historico;
        accionesDetalle=true;
        setListHistoricoDetalle(getServicioHistorico().consultarHistoricoPorDocumentId(currentH.getDocumento().getIdDocumento()));
        setListComentario(getServicioComentario().obtenerComentariosDocumento(currentH.getDocumento().getIdDocumento()));
        getBandejaController().setCurrent(currentH);
        getBandejaController().setAccionesDetalle(true);        
        return "/GUI/Gestion/BandejaEntrada/GUIDocumentoDetalle";
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

    public boolean isAccionesDetalle() {
        return accionesDetalle;
    }

    public void setAccionesDetalle(boolean accionesDetalle) {
        this.accionesDetalle = accionesDetalle;
    }

    public Documento getCurrent() {
        return current;
    }

    public void setCurrent(Documento current) {
        this.current = current;
    }
    
    private BandejaController getBandejaController() {
        if (controladorBandeja == null) {
//            controladorAnexo  = (AnexoController) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("beanAnexo");        
//            controladorAnexo  = (AnexoController)FacesAccessor.getManagedBean("beanAnexo");
            FacesContext context = FacesContext.getCurrentInstance();
            controladorBandeja = (BandejaController) context.getApplication().evaluateExpressionGet(context, "#{beanBandeja}", BandejaController.class);
        }
        return controladorBandeja;
    }
    

}

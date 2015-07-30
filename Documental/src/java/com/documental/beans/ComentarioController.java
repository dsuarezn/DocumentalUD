/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.documental.beans;

import com.documental.bo.Anexo;
import com.documental.bo.Comentario;
import com.documental.bo.Documento;
import com.documental.bo.Historico;
import com.documental.bo.HistoricoPK;
import com.documental.bo.Login;
import com.documental.dto.ComentarioDTO;
import com.documental.servicios.ServicioAnexo;
import com.documental.servicios.ServicioComentario;
import com.documental.servicios.ServicioDocumento;
import com.documental.servicios.ServicioHistorico;
import com.documental.servicios.ServicioLogin;
import com.documental.servicios.impl.ServicioAnexoImpl;
import com.documental.servicios.impl.ServicioComentarioImpl;
import com.documental.servicios.impl.ServicioDocumentoImpl;
import com.documental.servicios.impl.ServicioHistoricoImpl;
import com.documental.servicios.impl.ServicioLoginImpl;
import com.documental.util.JsfUtil;
import com.documental.util.PaginationHelper;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Alexander
 */
@ManagedBean(name = "beanComentario")
@SessionScoped
public class ComentarioController {
    
    private Integer documentoId;
    private Documento documentoSelecciondo;
    private String comentario;    
    private List<ComentarioDTO> listaComentariosDTO;    
    private DataModel items = null;    
    private PaginationHelper pagination;
    private String adelante, volver; 
    private Integer tableCount;
    private List<Comentario> listaComentarios;
    private ListDataModel modelo;
    private ServicioDocumento servicioDocumento;   
    private ServicioComentario servicioComentario; 
    private ServicioHistorico servicioHistorico; 
    private ServicioAnexo servicioAnexo; 
    private ServicioLogin servicioLogin;
    private final String RUTA_VISTA="/GUI/Gestion/Comentarios/GUIComentariosList";

    private Login usuario;
    

    private String getLoggedUserName(){
        return ((HttpServletRequest) FacesContext.getCurrentInstance().
            getExternalContext().getRequest()).getSession().getAttribute("user").toString();
    }
    
    public Login getUsuario() {
        if (usuario == null) {
            usuario = getServicioLogin().buscarPorUsuario(getLoggedUserName());
        }
        return usuario;
    }
    
    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public String prepareCreate(Integer documentoId) {  
        System.out.println("Entrando a prepare create historico");
        establecerYConsultarDocumento(documentoId);     
        limpiarElementosTabla();
        return RUTA_VISTA;
    }
    
    public String prepareCreate(Integer documentoId, String adelante, String volver) {  
        System.out.println("Entrando a prepare create historico");
        establecerYConsultarDocumento(documentoId);
        this.volver=volver;
        this.adelante=adelante;
        limpiarElementosTabla();
        return RUTA_VISTA;
    }
    
     public String prepareCreate(Documento documento, String adelante, String volver) {  
        System.out.println("Entrando a prepare create historico por objeto");
        this.documentoSelecciondo=documento;
        this.documentoId=documento.getIdDocumento();        
        this.volver=volver;
        this.adelante=adelante;
        limpiarElementosTabla();        
        return RUTA_VISTA;
    }
     
     public PaginationHelper getPagination() {
        if (pagination == null) {
            pagination = new PaginationHelper((10)) {
                @Override
                public int getItemsCount() {
                    return tableCount;
                }

                @Override
                public DataModel createPageDataModel() {
                   return modelo;
                }
            };
        }
        return pagination;
    }
     
     
    private ServicioDocumento getServicioDocumento() {
        if (servicioDocumento == null) {
            servicioDocumento = new ServicioDocumentoImpl();
        }
        return servicioDocumento;
    }  
    
    private ServicioComentario getServicioComentario() {
        if (servicioComentario == null) {
            servicioComentario = new ServicioComentarioImpl();
        }
        return servicioComentario;
    } 
    
    
    private ServicioHistorico getServicioHistorico() {
        if (servicioHistorico == null) {
            servicioHistorico = new ServicioHistoricoImpl();
        }
        return servicioHistorico;
    } 
    
    private ServicioAnexo getServicioAnexo() {
        if (servicioAnexo == null) {
            servicioAnexo = new ServicioAnexoImpl();
        }
        return servicioAnexo;
    }
    
    public ServicioLogin getServicioLogin() {
        if (servicioLogin == null) {
            servicioLogin = new ServicioLoginImpl();
        }
        return servicioLogin;
    }
     
    private void establecerYConsultarDocumento(Integer documentoId){
        System.out.println("Estableciendo documento:"+documentoId);
        this.documentoId = documentoId;        
        try {
            this.documentoSelecciondo = getServicioDocumento().consultarDocumentoPorId(documentoId);                        
        } catch (Exception e) {
            e.printStackTrace();
            JsfUtil.addErrorMessage(ResourceBundle.getBundle("/Bundle").getString("documental_GUIComentariosList_Messages_error_consulta_documento") + " " + e.toString());
        }
    } 

    public DataModel getItems() {
        if (items == null) {
            items = getPagination().createPageDataModel();
        }
        return items;   
    } 
    
     private void calcularBasico(){
//        tableCount =  getServicioComentario().contarComentariosDocumento(documentoId).intValue();
        listaComentarios =  getServicioComentario().obtenerComentariosDocumento(documentoId);
    }
    
    private void calcularModelo(){
        listaComentariosDTO = obtenerListadoParaTrabajo(listaComentarios);
        List<ComentarioDTO> listaRedireccion = obtenerListadoComentariosRedireccion();
        List<ComentarioDTO> listaAnexo = obtenerListadoComentariosAnexos();
        listaComentariosDTO.addAll(listaRedireccion);
        listaComentariosDTO.addAll(listaAnexo);
        tableCount= listaComentariosDTO.size();
        modelo = new ListDataModel(listaComentariosDTO);
    }
    
    public void actualizarDataTable(){
        FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("formListaComentarios:tablaComentarios");
    }
    public void actualizarFormulario(){
        FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("frmAgregarComentarios:comentario");
    }
    
    private void limpiarElementosTabla(){
        this.pagination=null;
        this.items = null;
         calcularBasico();
         calcularModelo();
         actualizarDataTable();
     }
    
    public boolean esPermitido(Login usuarioComentario){
        return usuarioComentario.getIdLogin() == getUsuario().getIdLogin();
    }
    
    
      private List<ComentarioDTO> obtenerListadoParaTrabajo(List<Comentario> listaPersistida){
          List<ComentarioDTO> listaDTO=new ArrayList<ComentarioDTO>();                    
          for (Comentario comentario : listaPersistida) {
              listaDTO.add(new ComentarioDTO(comentario.getIdcomentario(),comentario.getDocumentoid().getIdDocumento(),comentario.getComentario(),comentario.getFecha(),comentario.getLoginid(),"Comentario", esPermitido(comentario.getLoginid())));
          }                  
          return listaDTO;
      }
      
      private List<ComentarioDTO> obtenerListadoComentariosRedireccion(){
          List<ComentarioDTO> listaDTO =  new ArrayList<ComentarioDTO>();
          List<Historico> listaHistoricos = getServicioHistorico().consultarHistoricoPorDocumentId(this.documentoId);          
          
          for (Historico historico : listaHistoricos) {
              listaDTO.add(new ComentarioDTO(null,historico.getHistoricoPK().getDocumentoId(),historico.getComentario(),historico.getFecha(),historico.getLoginOrigen(),"Redireccion", false));
          }                  
          
          return listaDTO;
      }
      
      private List<ComentarioDTO> obtenerListadoComentariosAnexos(){
          List<ComentarioDTO> listaDTO =  new ArrayList<ComentarioDTO>();
          List<Anexo> listaAnexos = getServicioAnexo().consultarAnexosPorDocumento(documentoId);
          for (Anexo anexo : listaAnexos) {
              listaDTO.add(new ComentarioDTO(null,anexo.getDocumento().getIdDocumento(),anexo.getDescripcion(),anexo.getFechaCreacion(),anexo.getLoginid(),"Anexo", false));
          }                  
          return listaDTO;
      }
      
    public Integer getDocumentoId() {
        return documentoId;
    }

    public void setDocumentoId(Integer documentoId) {
        this.documentoId = documentoId;
    }

    public Documento getDocumentoSelecciondo() {
        return documentoSelecciondo;
    }

    public void setDocumentoSelecciondo(Documento documentoSelecciondo) {
        this.documentoSelecciondo = documentoSelecciondo;
    }

   

    public String getAdelante() {
        return adelante;
    }

    public void setAdelante(String adelante) {
        this.adelante = adelante;
    }

    public String getVolver() {
        return volver;
    }

    public void setVolver(String volver) {
        this.volver = volver;
    }

    public Integer getTableCount() {
        return tableCount;
    }

    public void setTableCount(Integer tableCount) {
        this.tableCount = tableCount;
    }


    public ListDataModel getModelo() {
        return modelo;
    }

    public void setModelo(ListDataModel modelo) {
        this.modelo = modelo;
    }
    
     public String volver() {          
        return (JsfUtil.IsBlank(volver)?"/":volver);
    }
    public String adelante() {          
        return (JsfUtil.IsBlank(adelante)?"/":adelante);
    }
    
    
    
    public String agregarComentario(){
        try {
            Comentario comentarioActual = new Comentario();            
            comentarioActual.setComentario(getComentario());
            comentarioActual.setFecha(new Date());            
            comentarioActual.setDocumentoid(this.documentoSelecciondo);
            comentarioActual.setLoginid(getUsuario());
            getServicioComentario().salvarComentario(comentarioActual);
            limpiarElementosTabla();
            this.comentario = "";
            actualizarFormulario();            
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("documental_GUIComentariosList_Messages_pActualizacionAnexosExitosa"));
            return adelante();            
         } catch (Exception e) {
            JsfUtil.addErrorMessage(ResourceBundle.getBundle("/Bundle").getString("documental_GUIComentariosList_Messages_pActualizacionAnexosErroneo") + " " + e.toString());
        }
        return null;
    }
    
    
    public void deleteAction(ComentarioDTO comentario) {
        try {            
            Comentario obtenido = getServicioComentario().obtenerComentarioPorId(comentario.getComentarioID());
            getServicioComentario().borrarComentario(obtenido);
            limpiarElementosTabla();
            actualizarDataTable();
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("documental_GUIComentariosList_Messages_pActualizacionAnexosExitosa"));                       
        } catch (Exception e) {
            JsfUtil.addErrorMessage(ResourceBundle.getBundle("/Bundle").getString("documental_GUIComentariosList_Messages_pActualizacionAnexosErroneo") + " " + e.toString());
        }
    }
    
    
}

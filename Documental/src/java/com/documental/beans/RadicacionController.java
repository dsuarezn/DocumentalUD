/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.documental.beans;

import com.documental.bo.Tipo;
import com.documental.bo.Documento;
import com.documental.bo.Login;
import com.documental.dto.LoginDTO;
import com.documental.enums.EstadosDocumentoEnum;
import com.documental.enums.FinalidadDocumentoEnum;
import com.documental.enums.PrioridadDocumentoEnum;
import com.documental.enums.VisibilidadDocumentoEnum;
import com.documental.servicios.ServicioAnexo;
import com.documental.servicios.ServicioDocumento;
import com.documental.servicios.ServicioLogin;
import com.documental.servicios.ServicioTipo;
import com.documental.servicios.impl.ServicioAnexoImpl;
import com.documental.servicios.impl.ServicioDocumentoImpl;
import com.documental.servicios.impl.ServicioLoginImpl;
import com.documental.servicios.impl.ServicioTipoImpl;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author DiegoM
 */
@ManagedBean(name = "beanRadicacion")
@SessionScoped
public class RadicacionController {

    private List<UploadedFile> uploadAttachment;
    private String idUsuarioOrigen;
    private String idUsuarioDestino;
    
    private Integer tipoDocumento;
    private Integer estadoDocumento;
    private Integer prioridadDocumento;
    private Integer finalidadDocumento;
    private Integer visibilidadDocumento;
    
    private String palabrasClaves;
    private String finDocumento;
    private String firmaDocumento;
    private UploadedFile uploadFile;
    
    private String asunto;
    private String descripcion;
    
    private ServicioLogin servicioLogin;
    private ServicioDocumento servicioDocumento;
    private ServicioTipo servicioTipo;
    private ServicioAnexo servicioAnexo;
    
    /**
     * Creates a new instance of GestionController
     */
    public RadicacionController() {
        uploadAttachment = new LinkedList<UploadedFile>();        
    }
    
    public String getCurrentDate(){        
        SimpleDateFormat formato = new SimpleDateFormat("EEEEE, d 'de' MMMMM 'del' yyyy");
        return formato.format(new Date());
    }
    
    public String prepareCreate() {
        return "/GUI/Radicador/GUIRadicar";
    }
    
    public List<LoginDTO> getListaUsuarios(){
        return getLoginDTO(getServicioLogin().buscarTodosLogin());
    }
    
    public List<Tipo> getListaTipos(){
        return servicioTipo.consultarTodosTipos();
    }
    
   public ServicioLogin getServicioLogin() {
        if (servicioLogin == null) {
            servicioLogin = new ServicioLoginImpl();
        }
        return servicioLogin;
    }
   
    public ServicioDocumento getServicioDocumento() {
        if (servicioDocumento == null) {
            servicioDocumento = new ServicioDocumentoImpl();
        }
        return servicioDocumento;
    }
    
    public ServicioTipo getServicioTipo() {
        if (servicioTipo == null) {
            servicioTipo = new ServicioTipoImpl();
        }
        return servicioTipo;
    }
    
    public ServicioAnexo getServicioAnexo() {
        if (servicioAnexo == null) {
            servicioAnexo = new ServicioAnexoImpl();
        }
        return servicioAnexo;
    }
    
   private List<LoginDTO> getLoginDTO(List<Login> listaUsuarios){
       List<LoginDTO> listaDTO=new ArrayList<LoginDTO>();
       for (Login usuarioLogin : listaUsuarios) {
           listaDTO.add(new LoginDTO(usuarioLogin.getUsuario(), usuarioLogin.getCorreo(),usuarioLogin.getTipoUsuario()));
       }
       return listaDTO;
   }
   
   public EstadosDocumentoEnum[] getEstadosDocumento(){
       return EstadosDocumentoEnum.values();
   }
   
    public PrioridadDocumentoEnum[] getPrioridadesDocumento(){
       return PrioridadDocumentoEnum.values();
   }
   
    public FinalidadDocumentoEnum[] getFinalidadesDocumento(){
       return FinalidadDocumentoEnum.values();
   }
    
    public VisibilidadDocumentoEnum[] getVisibilidadesDocumento(){
       return VisibilidadDocumentoEnum.values();
   }

   public void addFileToAttachment(FileUploadEvent event) {
      uploadAttachment.add(event.getFile());
   }

   public List<UploadedFile> getUploadAttachment() {
      return uploadAttachment;
   }

   public void setUploadAttachment(List<UploadedFile> uploadAttachment) {
      this.uploadAttachment = uploadAttachment;
   }

    public String getIdUsuarioOrigen() {
        return idUsuarioOrigen;
    }

    public void setIdUsuarioOrigen(String idUsuarioOrigen) {
        this.idUsuarioOrigen = idUsuarioOrigen;
    }

    public String getIdUsuarioDestino() {
        return idUsuarioDestino;
    }

    public Integer getEstadoDocumento() {
        return estadoDocumento;
    }

    public Integer getPrioridadDocumento() {
        return prioridadDocumento;
    }

    public String getPalabrasClaves() {
        return palabrasClaves;
    }

    public void setPalabrasClaves(String palabrasClaves) {
        this.palabrasClaves = palabrasClaves;
    }

    public String getFinDocumento() {
        return finDocumento;
    }

    public void setFinDocumento(String finDocumento) {
        this.finDocumento = finDocumento;
    }

    public UploadedFile getUploadFile() {
        return uploadFile;
    }

    public void setUploadFile(UploadedFile uploadFile) {
        this.uploadFile = uploadFile;
    }

    public String getFirmaDocumento() {
        return firmaDocumento;
    }

    public void setFirmaDocumento(String firmaDocumento) {
        this.firmaDocumento = firmaDocumento;
    }
   
      public String volver() {
        return "/";
    }

    public String getAsunto() {
        return asunto;
    }

    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getFinalidadDocumento() {
        return finalidadDocumento;
    }

    public void setFinalidadDocumento(Integer finalidadDocumento) {
        this.finalidadDocumento = finalidadDocumento;
    }

    public Integer getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(Integer tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }
      
    public Tipo obtenerTipoDocumento(Integer id){
        return servicioTipo.consultarTipoPorId(id);
    }
    
//    public String guardarAnexos(Integer idDocumento, List<UploadedFile> listaArchivos){
//        for (UploadedFile listaArchivo : listaArchivos) {
//            
//        }
//    }

    public Integer getVisibilidadDocumento() {
        return visibilidadDocumento;
    }

    public void setVisibilidadDocumento(Integer visibilidadDocumento) {
        this.visibilidadDocumento = visibilidadDocumento;
    }
    
    
      
       public void create() {
        String respuesta = "";
        try {
            Integer id = getServicioDocumento().getMaxId();
            id++;
            Documento documento=new Documento();
            documento.setAsunto(this.getAsunto());
            documento.setDescripcion(this.getDescripcion());
            documento.setEstado(EstadosDocumentoEnum.getNombrePorCodigo(this.getEstadoDocumento()));
            documento.setPrioridad(PrioridadDocumentoEnum.getNombrePorCodigo(this.getPrioridadDocumento()));
            documento.setFinalidad(FinalidadDocumentoEnum.getNombrePorCodigo(this.getFinalidadDocumento()));
            documento.setFechaCreacion(new Date());
            documento.setIdDocumento(id);
            documento.setTipoId(obtenerTipoDocumento(this.getTipoDocumento()));
            documento.setVisibilidad(VisibilidadDocumentoEnum.getNombrePorCodigo(this.getVisibilidadDocumento()));
            respuesta = getServicioDocumento().salvarDocumento(documento);            
            if (respuesta.equals("Operación Exitosa")) {
                for (UploadedFile archivoCargado : uploadAttachment) {
                    
                }
                
                
//                JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("documental_GUITipoUsuario_Messages_pCreateTipoUsuarioExitoso"));
            } else {
//                JsfUtil.addErrorMessage(ResourceBundle.getBundle("/Bundle").getString("documental_GUITipoUsuario_Messages_pCreateTipoUsuarioErroneo"));
            }
        } catch (Exception e) {
//            JsfUtil.addErrorMessage(ResourceBundle.getBundle("/Bundle").getString("documental_GUITipoUsuario_Messages_pCreateTipoUsuarioErroneo") + " " + e.toString());
        }
    }
   
}

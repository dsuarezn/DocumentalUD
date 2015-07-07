/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.documental.beans;

import com.documental.bo.Login;
import com.documental.dto.LoginDTO;
import com.documental.enums.EstadosDocumentoEnum;
import com.documental.enums.PrioridadDocumentoEnum;
import com.documental.servicios.ServicioLogin;
import com.documental.servicios.impl.ServicioLoginImpl;
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
    private ServicioLogin servicio;
    private Integer estadoDocumento;
    private Integer prioridadDocumento;
    private String palabrasClaves;
    private String finDocumento;
    private String firmaDocumento;
    private UploadedFile uploadFile;
    
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
        return getLoginDTO(getServicio().buscarTodosLogin());
    }
   public ServicioLogin getServicio() {
        if (servicio == null) {
            servicio = new ServicioLoginImpl();
        }
        return servicio;
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
      
      
      
       public void create() {
//        String respuesta = "";
//        try {
//            Integer id = getServicio().getMaxId();
//            id++;
//            current.setIdTipoUsuario(id);
//            current.setIdNivelAcceso(new NivelAcceso(idNivelAcceso));
//            respuesta = getServicio().salvarTipoUsuario(current);
//            items = null;
//            if (respuesta.equals("Operación Exitosa")) {
////                JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("documental_GUITipoUsuario_Messages_pCreateTipoUsuarioExitoso"));
//            } else {
////                JsfUtil.addErrorMessage(ResourceBundle.getBundle("/Bundle").getString("documental_GUITipoUsuario_Messages_pCreateTipoUsuarioErroneo"));
//            }
//        } catch (Exception e) {
////            JsfUtil.addErrorMessage(ResourceBundle.getBundle("/Bundle").getString("documental_GUITipoUsuario_Messages_pCreateTipoUsuarioErroneo") + " " + e.toString());
//        }
    }
   
}

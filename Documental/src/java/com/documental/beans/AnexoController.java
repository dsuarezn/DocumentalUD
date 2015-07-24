/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.documental.beans;

import com.documental.bo.Anexo;
import com.documental.bo.AnexoPK;
import com.documental.bo.Documento;
import com.documental.bo.Login;
import com.documental.dto.AnexoDTO;
import com.documental.servicios.ServicioAnexo;
import com.documental.servicios.ServicioDocumento;
import com.documental.servicios.ServicioLogin;
import com.documental.servicios.impl.ServicioAnexoImpl;
import com.documental.servicios.impl.ServicioDocumentoImpl;
import com.documental.servicios.impl.ServicioLoginImpl;
import com.documental.util.JsfUtil;
import com.documental.util.PaginationHelper;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.RowEditEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

/**
 *
 * @author Alexander
 */
@ManagedBean(name = "beanAnexo")
@SessionScoped
public class AnexoController {
    
    private static final int BUFFER_SIZE = 6124;        
    private Integer documentoId;
    private Documento documentoSelecciondo;
    private DataModel items = null;
    private PaginationHelper pagination;
    private ServicioAnexo servicioAnexo;
    private ServicioLogin servicioLogin;
    private ServicioDocumento servicioDocumento;       
    private List<AnexoDTO> listaDTO=null;
    private String adelante, volver; 
    private String RUTA_VISTA="/GUI/Gestion/Anexo/GUIAnexo";

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
    
     public DataModel getItems() {
        if (items == null) {
            items = getPagination().createPageDataModel();
        }
        return items;   
    }  
     
    private void establecerYConsultarDocumento(Integer documentoId){
        System.out.println("Estableciendo documento:"+documentoId);
        this.documentoId = documentoId;        
        try {
            this.documentoSelecciondo = getServicioDocumento().consultarDocumentoPorId(documentoId);                        
        } catch (Exception e) {
            e.printStackTrace();
            JsfUtil.addErrorMessage(ResourceBundle.getBundle("/Bundle").getString("documental_GUIAnexo_Messages_error_consulta_documento") + " " + e.toString());
        }
    } 

    public String prepareCreate(Integer documentoId) {  
        System.out.println("Entrando a prepare create anexo");
        establecerYConsultarDocumento(documentoId);
        System.out.println("devolviendo vista anexo");      
        limpiarElementosTabla();
        return RUTA_VISTA;
    }
    
    public String prepareCreate(Integer documentoId, String adelante, String volver) {  
        System.out.println("Entrando a prepare create anexo");
        establecerYConsultarDocumento(documentoId);
        System.out.println("devolviendo vista anexo");
        this.volver=volver;
        this.adelante=adelante;
        limpiarElementosTabla();
        return RUTA_VISTA;
    }
    
     public String prepareCreate(Documento documento, String adelante, String volver) {  
        System.out.println("Entrando a prepare create anexo por objeto");
        this.documentoSelecciondo=documento;
        this.documentoId=documento.getIdDocumento();        
        this.volver=volver;
        this.adelante=adelante;
        limpiarElementosTabla();        
        return RUTA_VISTA;
    }
    
     private void limpiarElementosTabla(){
        this.pagination=null;
        this.items = null;
         calcularBasico();
         calcularListadoPersistido();
         calcularModelo();
     }
     
    private ServicioDocumento getServicioDocumento() {
        if (servicioDocumento == null) {
            servicioDocumento = new ServicioDocumentoImpl();
        }
        return servicioDocumento;
    }    
    
    public ServicioAnexo getServicioAnexo() {
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
     
    
    private void calcularBasico(){
        tableCount =  getServicioAnexo().getCount(documentoId);
        listaAnexo =  getServicioAnexo().consultarAnexosPorDocumento(documentoId);
    }
    
    private void calcularListadoPersistido(){
       listaDTO = obtenerListadoParaTrabajo(listaAnexo);
    }
    
    private void calcularModelo(){        
        modelo = new ListDataModel(listaDTO);
    }
    
    private Integer tableCount;
    private List<Anexo> listaAnexo;
    private ListDataModel modelo;
              
     
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

    public Documento getDocumentoSelecciondo() {
        return documentoSelecciondo;
    }

    public void setDocumentoSelecciondo(Documento documentoSelecciondo) {
        this.documentoSelecciondo = documentoSelecciondo;
    }

    
    public boolean esPermitido(Login usuarioComentario){
        return usuarioComentario.getIdLogin() == getUsuario().getIdLogin();
    }
    
    
      private List<AnexoDTO> obtenerListadoParaTrabajo(List<Anexo> listaPersistida){
          List<AnexoDTO> listaAnexoDTO=new ArrayList<AnexoDTO>();
          for (Anexo anexo : listaPersistida) {              
              listaAnexoDTO.add(new AnexoDTO(anexo.getAnexoPK().getIdAnexo(),anexo.getDescripcion(),anexo.getDireccion(), this.documentoId,true, esPermitido(anexo.getLoginid()),anexo.getLoginid()));
          }                  
          return listaAnexoDTO;
      }

   
      public AnexoDTO getNuevoAnexoDTO(){
          AnexoDTO dto = new AnexoDTO();
          dto.setDescripccion("");
          dto.setDireccionArchivo("");
          dto.setIdAnexo(null);
          dto.setIdDocumento(this.documentoId);
          dto.setNombreArchivo("");
          dto.setPermitido(true);
          dto.setPersistido(false);
          dto.setUsuarioComentario(getUsuario());
          return dto;
      }
    
    public void agregarAnexo(){
        System.out.println("ENTRO A AGREGAR ANEXO");
        //this.create();
        calcularListadoPersistido();
        this.listaDTO.add(getNuevoAnexoDTO());
//        this.listaAnexo.add(new Anexo());        
        this.pagination=null;
        this.items = null;
        calcularModelo();        
        actualizarDataTable();
    }
    
    public String volver() {          
        return (JsfUtil.IsBlank(volver)?"/":volver);
    }
    public String adelante() {          
        return (JsfUtil.IsBlank(adelante)?"/":adelante);
    }
      
    public void actualizarDataTable(){
        FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("frmListaAnexos:archivosAnexo");
    }
      
    public String create(){
        System.out.println("ENTRO A actualizar anexos");                
        try{
            for (AnexoDTO anexoDto : listaDTO) {
                if(!anexoDto.isPersistido()){
                    Anexo anexo = new Anexo();
                    AnexoPK anexopk = new AnexoPK();
                    anexopk.setDocumentoId(this.documentoId);            
                    anexopk.setIdAnexo(getServicioAnexo().getMaxId()+1);
                    anexo.setAnexoPK(anexopk);
                    anexo.setDescripcion(anexoDto.getDescripccion());
                    anexo.setDireccion(anexoDto.getDireccionArchivo());
                    anexo.setDocumento(this.documentoSelecciondo);
                    anexo.setFechaCreacion(new Date());
                    anexo.setFirmado(false);   
                    anexo.setLoginid(getUsuario());
                    if(!JsfUtil.IsBlank(anexo.getDireccion()) && !JsfUtil.IsBlank(anexo.getDescripcion())){
                        getServicioAnexo().salvarAnexo(anexo);
                    }  
                }
            }
            limpiarElementosTabla();
            actualizarDataTable();
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("documental_GUIAnexo_Messages_pActualizacionAnexosExitosa"));
            return adelante();
        } catch (Exception e) {
            e.printStackTrace();
            JsfUtil.addErrorMessage(ResourceBundle.getBundle("/Bundle").getString("documental_GUIAnexo_Messages_pActualizacionAnexosErroneo") + " " + e.toString());
        }
        return null;
    }
      
      
    public String manejarFileUpload(Integer id,FileUploadEvent event){
        String idDocumento = id.toString();
        ExternalContext extContext=FacesContext.getCurrentInstance().getExternalContext();         
//        String basepath=extContext.getRealPath("//resources//anexos"); 
        String basepath=extContext.getInitParameter("anexosfolder");
        Date now = new Date();        
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd-HHmmss");        
        if (!Files.exists(Paths.get(basepath+"//"+idDocumento+"//"))) {
            File dir = new File(basepath+"//"+idDocumento+"//");
            dir.mkdirs();
        }             
        String nuevoNombre = format.format(now)+"-"+idDocumento+".pdf";        
//        File result = new File(extContext.getRealPath("//resources//anexos//"+idDocumento+"//" + nuevoNombre));                
        File result = new File(extContext.getInitParameter("anexosfolder")+File.separator+idDocumento+ File.separator + nuevoNombre);                
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(result);
            byte[] buffer = new byte[BUFFER_SIZE];
            int bulk;
            InputStream inputStream = event.getFile().getInputstream();
            while (true) {
                   bulk = inputStream.read(buffer);
                   if (bulk < 0) {
                       break;
                    }
                   fileOutputStream.write(buffer, 0, bulk);
                   fileOutputStream.flush();
             }
                fileOutputStream.close();
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
         }  
        
        actualizarDataTable();
        return  result.getAbsolutePath();
    }  
    
    
    public void deleteAction(AnexoDTO anexo) {
        try {            
            Anexo obtenido = getServicioAnexo().consultarAnexoPorId(anexo.getIdAnexo());
            getServicioAnexo().borrarAnexo(obtenido);
            limpiarElementosTabla();
            actualizarDataTable();
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("documental_GUIComentariosList_Messages_pActualizacionAnexosExitosa"));                       
        } catch (Exception e) {
            e.printStackTrace();
            JsfUtil.addErrorMessage(ResourceBundle.getBundle("/Bundle").getString("documental_GUIComentariosList_Messages_pActualizacionAnexosErroneo") + " " + e.toString());
        }
    }
    
    
    public void descargarArchivo(String absolutePath){        
        System.out.println("Ruta del archivo a consultar:"+absolutePath);
        
        Path p = Paths.get(absolutePath);
        String archivo = p.getFileName().toString();
        try {
                // Get the FacesContext
            FacesContext facesContext = FacesContext.getCurrentInstance();
            // Get HTTP response
            HttpServletResponse response = (HttpServletResponse) facesContext.getExternalContext().getResponse();
            // Set response headers
            response.reset();   // Reset the response in the first place
            response.setHeader("Content-Type", "application/pdf");  // Set only the content type
            response.setHeader( "Content-Disposition", "attachment;filename="+ archivo );
            // Open response output stream
            OutputStream responseOutputStream = response.getOutputStream();
            // Read PDF contents            
            InputStream pdfInputStream = new FileInputStream(absolutePath);                    
            // Read PDF contents and write them to the output
            byte[] bytesBuffer = new byte[2048];
            int bytesRead;
            while ((bytesRead = pdfInputStream.read(bytesBuffer)) > 0) {
                responseOutputStream.write(bytesBuffer, 0, bytesRead);
            }
            // Make sure that everything is out
            responseOutputStream.flush();
            // Close both streams
            pdfInputStream.close();
            responseOutputStream.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

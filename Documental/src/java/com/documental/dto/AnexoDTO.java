/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.documental.dto;

import com.documental.beans.AnexoController;
import com.documental.util.JsfUtil;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author Alexander
 */
public class AnexoDTO {
    
    private String idDocumento;
    private String descripccion;
    private String direccionArchivo;  
    private String nombreArchivo;  
    
    private boolean paraEdicion;
    
    
    private AnexoController controlador;

    public String getIdDocumento() {
        return idDocumento;
    }

    public void setIdDocumento(String idDocumento) {
        this.idDocumento = idDocumento;
    }
    
    

    public AnexoDTO(String descripccion, String direccionArchivo, boolean paraEdicion, String idDocumento) {
         System.out.println("ENTRO A MANEJAR FILE DOWNLOAD");
        this.descripccion = descripccion;
        this.direccionArchivo = direccionArchivo;
        this.paraEdicion = paraEdicion;
        this.idDocumento = idDocumento;
        if(!JsfUtil.IsBlank(this.direccionArchivo)){
            setNombreArchivoByPath(this.direccionArchivo);
        }
    }

    public String getNombreArchivo() {
        return nombreArchivo;
    }

    public void setNombreArchivo(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }
    
    public String getDescripccion() {
        return descripccion;
    }

    public void setDescripccion(String descripccion) {
        this.descripccion = descripccion;
    }

    public String getDireccionArchivo() {
        return direccionArchivo;
    }

    public void setDireccionArchivo(String direccionArchivo) {
        this.direccionArchivo = direccionArchivo;
    }

    public boolean getParaEdicion() {
        return paraEdicion;
    }

    public void setParaEdicion(boolean paraEdicion) {
        this.paraEdicion = paraEdicion;
    }

    private void setNombreArchivoByPath(String direccion){
            Path p = Paths.get(direccion);
            this.nombreArchivo = p.getFileName().toString();        
    }
    
    public void manejarFileUpload(FileUploadEvent event) throws FileNotFoundException, IOException {
        System.out.println("ENTRO A MANEJAR FILE UPLOAD");        
            controlador=getController();
            this.direccionArchivo = controlador.manejarFileUpload(idDocumento, event);   
            setNombreArchivoByPath(this.direccionArchivo);
    }

    public AnexoController getControlador() {
        return controlador;
    }

    public void setControlador(AnexoController controlador) {
        this.controlador = controlador;
    }
    
    public void downloadFile(){        
        System.out.println("ENTRO A MANEJAR FILE DOWNLOAD");
        controlador=getController();
        if(this.direccionArchivo!=null && !this.direccionArchivo.trim().equals("")){
            controlador.descargarArchivo(this.direccionArchivo);
        }
    }
    
    private AnexoController getController(){
        if(controlador==null){
            controlador  = (AnexoController) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("beanAnexo");        
        }        
        return controlador;
    }
    
}

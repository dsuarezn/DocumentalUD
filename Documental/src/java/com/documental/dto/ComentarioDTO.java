/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.documental.dto;

import com.documental.bo.Login;
import java.util.Date;

/**
 *
 * @author Alexander
 */
public class ComentarioDTO {
    
    private Integer comentarioID;
    private Integer documentoID;
    private String comentario;
    private Date fechaComentario;
    private Login usuarioComentario; 
    private String tipoComentario;
    private boolean esPermitido;

    public ComentarioDTO(Integer comentarioID,Integer documentoID, String comentario, Date fechaComentario, Login usuarioComentario, String tipoComentario, boolean permitido) {
        this.comentarioID=comentarioID;
        this.documentoID = documentoID;
        this.comentario = comentario;
        this.fechaComentario = fechaComentario;
        this.usuarioComentario = usuarioComentario;
        this.tipoComentario = tipoComentario;
        this.esPermitido = permitido;
    }

    
    
    
    public Integer getDocumentoID() {
        return documentoID;
    }

    public void setDocumentoID(Integer documentoID) {
        this.documentoID = documentoID;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public Date getFechaComentario() {
        return fechaComentario;
    }

    public void setFechaComentario(Date fechaComentario) {
        this.fechaComentario = fechaComentario;
    }

    public Login getUsuarioComentario() {
        return usuarioComentario;
    }

    public void setUsuarioComentario(Login usuarioComentario) {
        this.usuarioComentario = usuarioComentario;
    }
    
    public String getNombreyApellido(){
        return this.usuarioComentario.getNombre()+" "+this.usuarioComentario.getApellido();
    }

    public Integer getComentarioID() {
        return comentarioID;
    }

    public void setComentarioID(Integer comentarioID) {
        this.comentarioID = comentarioID;
    }

    public String getTipoComentario() {
        return tipoComentario;
    }

    public void setTipoComentario(String tipoComentario) {
        this.tipoComentario = tipoComentario;
    }
    
    public boolean isEsComentario(){
        return ("Comentario".equalsIgnoreCase(this.tipoComentario)? true : false);
    }

    public boolean isEsPermitido() {
        return esPermitido;
    }

    public void setEsPermitido(boolean esPermitido) {
        this.esPermitido = esPermitido;
    }
    
 
    
    
}

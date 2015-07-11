/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.documental.dto;

import com.documental.bo.*;
import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author DiegoM
 */

public class LoginDTO implements Serializable {
    private static final long serialVersionUID = 1L;
   
    private Integer Id;
    
    private String nombre;
 
    private String apellido;
    
    private String usuario;
 
    private String correo;

    private TipoUsuario tipoUsuario;

    public LoginDTO() {
    }

    public LoginDTO(Integer id,String nombre, String apellido, String usuario, String correo, TipoUsuario tipoUsuario) {
        this.Id=id;
        this.usuario = usuario;
        this.correo = correo;
        this.tipoUsuario = tipoUsuario;
        this.nombre=nombre;
        this.apellido = apellido;
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer Id) {
        this.Id = Id;
    }

  

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public TipoUsuario getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(TipoUsuario tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }
    
    public String getNombreCompleto(){
        return this.nombre+" "+this.apellido;
    }
    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (usuario != null ? usuario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LoginDTO)) {
            return false;
        }
        LoginDTO other = (LoginDTO) object;
        if ((this.usuario == null && other.usuario != null) || (this.usuario != null && !this.usuario.equals(other.usuario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.documental.bo.Login[ usuario=" + usuario + " ]";
    }
    
}

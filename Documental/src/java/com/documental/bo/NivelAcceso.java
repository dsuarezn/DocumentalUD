/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.documental.bo;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author DiegoM
 */
@Entity
@Table(name = "nivel_acceso")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "NivelAcceso.findAll", query = "SELECT n FROM NivelAcceso n"),
    @NamedQuery(name = "NivelAcceso.findById", query = "SELECT n FROM NivelAcceso n WHERE n.id = :id"),
    @NamedQuery(name = "NivelAcceso.findByDescripcion", query = "SELECT n FROM NivelAcceso n WHERE n.descripcion = :descripcion")})
public class NivelAcceso implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "descripcion")
    private String descripcion;
    @OneToMany(mappedBy = "idNivelAcceso")
    private Collection<TipoUsuario> tipoUsuarioCollection;

    public NivelAcceso() {
    }

    public NivelAcceso(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @XmlTransient
    public Collection<TipoUsuario> getTipoUsuarioCollection() {
        return tipoUsuarioCollection;
    }

    public void setTipoUsuarioCollection(Collection<TipoUsuario> tipoUsuarioCollection) {
        this.tipoUsuarioCollection = tipoUsuarioCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof NivelAcceso)) {
            return false;
        }
        NivelAcceso other = (NivelAcceso) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.documental.bo.NivelAcceso[ id=" + id + " ]";
    }
    
}

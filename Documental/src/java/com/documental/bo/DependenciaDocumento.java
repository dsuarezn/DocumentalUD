/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.documental.bo;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Diego
 */
@Entity
@Table(name = "dependencia_documento")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DependenciaDocumento.findAll", query = "SELECT d FROM DependenciaDocumento d"),
    @NamedQuery(name = "DependenciaDocumento.findByDependenciaId", query = "SELECT d FROM DependenciaDocumento d WHERE d.dependenciaDocumentoPK.dependenciaId = :dependenciaId"),
    @NamedQuery(name = "DependenciaDocumento.findByDocumentoId", query = "SELECT d FROM DependenciaDocumento d WHERE d.dependenciaDocumentoPK.documentoId = :documentoId"),
    @NamedQuery(name = "DependenciaDocumento.findByFecha", query = "SELECT d FROM DependenciaDocumento d WHERE d.fecha = :fecha")})
public class DependenciaDocumento implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected DependenciaDocumentoPK dependenciaDocumentoPK;
    @Basic(optional = false)
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @JoinColumn(name = "dependencia_id", referencedColumnName = "id_dependencia", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Dependencia dependencia;
    @JoinColumn(name = "documento_id", referencedColumnName = "id_documento", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Documento documento;

    public DependenciaDocumento() {
    }

    public DependenciaDocumento(DependenciaDocumentoPK dependenciaDocumentoPK) {
        this.dependenciaDocumentoPK = dependenciaDocumentoPK;
    }

    public DependenciaDocumento(DependenciaDocumentoPK dependenciaDocumentoPK, Date fecha) {
        this.dependenciaDocumentoPK = dependenciaDocumentoPK;
        this.fecha = fecha;
    }

    public DependenciaDocumento(int dependenciaId, int documentoId) {
        this.dependenciaDocumentoPK = new DependenciaDocumentoPK(dependenciaId, documentoId);
    }

    public DependenciaDocumentoPK getDependenciaDocumentoPK() {
        return dependenciaDocumentoPK;
    }

    public void setDependenciaDocumentoPK(DependenciaDocumentoPK dependenciaDocumentoPK) {
        this.dependenciaDocumentoPK = dependenciaDocumentoPK;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Dependencia getDependencia() {
        return dependencia;
    }

    public void setDependencia(Dependencia dependencia) {
        this.dependencia = dependencia;
    }

    public Documento getDocumento() {
        return documento;
    }

    public void setDocumento(Documento documento) {
        this.documento = documento;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (dependenciaDocumentoPK != null ? dependenciaDocumentoPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DependenciaDocumento)) {
            return false;
        }
        DependenciaDocumento other = (DependenciaDocumento) object;
        if ((this.dependenciaDocumentoPK == null && other.dependenciaDocumentoPK != null) || (this.dependenciaDocumentoPK != null && !this.dependenciaDocumentoPK.equals(other.dependenciaDocumentoPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.documental.bo.DependenciaDocumento[ dependenciaDocumentoPK=" + dependenciaDocumentoPK + " ]";
    }
    
}

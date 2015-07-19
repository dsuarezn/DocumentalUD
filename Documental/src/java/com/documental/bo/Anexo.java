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
@Table(name = "anexo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Anexo.findAll", query = "SELECT a FROM Anexo a"),
    @NamedQuery(name = "Anexo.findByIdAnexo", query = "SELECT a FROM Anexo a WHERE a.anexoPK.idAnexo = :idAnexo"),
    @NamedQuery(name = "Anexo.findByFirmado", query = "SELECT a FROM Anexo a WHERE a.firmado = :firmado"),
    @NamedQuery(name = "Anexo.findByFechaCreacion", query = "SELECT a FROM Anexo a WHERE a.fechaCreacion = :fechaCreacion"),
    @NamedQuery(name = "Anexo.findByDescripcion", query = "SELECT a FROM Anexo a WHERE a.descripcion = :descripcion"),
    @NamedQuery(name = "Anexo.findByDireccion", query = "SELECT a FROM Anexo a WHERE a.direccion = :direccion"),
    @NamedQuery(name = "Anexo.findByDocumentoId", query = "SELECT a FROM Anexo a WHERE a.anexoPK.documentoId = :documentoId"),
    @NamedQuery(name = "Anexo.CountByDocumentoId", query = "SELECT count(a.anexoPK.documentoId) FROM Anexo a WHERE a.anexoPK.documentoId = :documentoId")
})
public class Anexo implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected AnexoPK anexoPK;
    @Basic(optional = false)
    @Column(name = "firmado")
    private boolean firmado;
    @Basic(optional = false)
    @Column(name = "fecha_creacion")
    @Temporal(TemporalType.DATE)
    private Date fechaCreacion;
    @Basic(optional = false)
    @Column(name = "descripcion")
    private String descripcion;
    @Basic(optional = false)
    @Column(name = "direccion")
    private String direccion;
    @JoinColumn(name = "documento_id", referencedColumnName = "id_documento", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Documento documento;

    public Anexo() {
    }

    public Anexo(AnexoPK anexoPK) {
        this.anexoPK = anexoPK;
    }

    public Anexo(AnexoPK anexoPK, boolean firmado, Date fechaCreacion, String descripcion, String direccion) {
        this.anexoPK = anexoPK;
        this.firmado = firmado;
        this.fechaCreacion = fechaCreacion;
        this.descripcion = descripcion;
        this.direccion = direccion;
    }

    public Anexo(int idAnexo, int documentoId) {
        this.anexoPK = new AnexoPK(idAnexo, documentoId);
    }

    public AnexoPK getAnexoPK() {
        return anexoPK;
    }

    public void setAnexoPK(AnexoPK anexoPK) {
        this.anexoPK = anexoPK;
    }

    public boolean getFirmado() {
        return firmado;
    }

    public void setFirmado(boolean firmado) {
        this.firmado = firmado;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
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
        hash += (anexoPK != null ? anexoPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Anexo)) {
            return false;
        }
        Anexo other = (Anexo) object;
        if ((this.anexoPK == null && other.anexoPK != null) || (this.anexoPK != null && !this.anexoPK.equals(other.anexoPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.documental.bo.Anexo[ anexoPK=" + anexoPK + " ]";
    }
    
}

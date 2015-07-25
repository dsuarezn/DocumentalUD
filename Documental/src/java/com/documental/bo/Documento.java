/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.documental.bo;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Diego
 */
@Entity
@Table(name = "documento")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Documento.findAll", query = "SELECT d FROM Documento d"),
    @NamedQuery(name = "Documento.findByIdDocumento", query = "SELECT d FROM Documento d WHERE d.idDocumento = :idDocumento"),
    @NamedQuery(name = "Documento.findByPrioridad", query = "SELECT d FROM Documento d WHERE d.prioridad = :prioridad"),
    @NamedQuery(name = "Documento.findByEstado", query = "SELECT d FROM Documento d WHERE d.estado = :estado"),
    @NamedQuery(name = "Documento.findByVisibilidad", query = "SELECT d FROM Documento d WHERE d.visibilidad = :visibilidad"),
    @NamedQuery(name = "Documento.findByDescripcion", query = "SELECT d FROM Documento d WHERE d.descripcion = :descripcion"),
    @NamedQuery(name = "Documento.findByFechaCreacion", query = "SELECT d FROM Documento d WHERE d.fechaCreacion = :fechaCreacion"),
    @NamedQuery(name = "Documento.findByAsunto", query = "SELECT d FROM Documento d WHERE d.asunto = :asunto"),
    @NamedQuery(name = "Documento.findByFinalidad", query = "SELECT d FROM Documento d WHERE d.finalidad = :finalidad")})
public class Documento implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id_documento")
    private Integer idDocumento;
    @Basic(optional = false)
    @Column(name = "prioridad")
    private String prioridad;
    @Basic(optional = false)
    @Column(name = "estado")
    private String estado;
    @Basic(optional = false)
    @Column(name = "visibilidad")
    private String visibilidad;
    @Basic(optional = false)
    @Column(name = "descripcion")
    private String descripcion;
    @Basic(optional = false)
    @Column(name = "palabras_clave")
    private String palabrasClave;
    @Basic(optional = false)
    @Column(name = "fecha_creacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCreacion;
    @Basic(optional = false)
    @Column(name = "asunto")
    private String asunto;
    @Basic(optional = false)
    @Column(name = "finalidad")
    private String finalidad;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "documento")
    private Collection<Anexo> anexoCollection;    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "documento")
    private Collection<Historico> historicoCollection;
    @JoinColumn(name = "tipo_id", referencedColumnName = "id_tipo")
    @ManyToOne(optional = false)
    private Tipo tipoId;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "documento")
    private Collection<DependenciaDocumento> dependenciaDocumentoCollection;
    
    @Temporal(TemporalType.DATE)
    private transient Date fechaAuxiliar;

    public Documento() {
    }

    public Documento(Integer idDocumento) {
        this.idDocumento = idDocumento;
    }

    public Documento(Integer idDocumento, String prioridad, String estado, String visibilidad, String descripcion, Date fechaCreacion, String asunto, String finalidad, String palabrasClave) {
        this.idDocumento = idDocumento;
        this.prioridad = prioridad;
        this.estado = estado;
        this.visibilidad = visibilidad;
        this.descripcion = descripcion;
        this.fechaCreacion = fechaCreacion;
        this.asunto = asunto;
        this.finalidad = finalidad;
        this.palabrasClave = palabrasClave;
    }

    public Integer getIdDocumento() {
        return idDocumento;
    }

    public void setIdDocumento(Integer idDocumento) {
        this.idDocumento = idDocumento;
    }

    public String getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(String prioridad) {
        this.prioridad = prioridad;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getVisibilidad() {
        return visibilidad;
    }

    public void setVisibilidad(String visibilidad) {
        this.visibilidad = visibilidad;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getAsunto() {
        return asunto;
    }

    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    public String getFinalidad() {
        return finalidad;
    }

    public void setFinalidad(String finalidad) {
        this.finalidad = finalidad;
    }

    public String getPalabrasClave() {
        return palabrasClave;
    }

    public void setPalabrasClave(String palabrasClave) {
        this.palabrasClave = palabrasClave;
    }

    public Date getFechaAuxiliar() {
        return fechaAuxiliar;
    }

    public void setFechaAuxiliar(Date fechaAuxiliar) {
        this.fechaAuxiliar = fechaAuxiliar;
    }

    @XmlTransient
    public Collection<Anexo> getAnexoCollection() {
        return anexoCollection;
    }

    public void setAnexoCollection(Collection<Anexo> anexoCollection) {
        this.anexoCollection = anexoCollection;
    }

    public Tipo getTipoId() {
        return tipoId;
    }
    
    @XmlTransient
    public Collection<Historico> getHistoricoCollection() {
        return historicoCollection;
    }

    public void setHistoricoCollection(Collection<Historico> historicoCollection) {
        this.historicoCollection = historicoCollection;
    }

    public void setTipoId(Tipo tipoId) {
        this.tipoId = tipoId;
    }

    @XmlTransient
    public Collection<DependenciaDocumento> getDependenciaDocumentoCollection() {
        return dependenciaDocumentoCollection;
    }

    public void setDependenciaDocumentoCollection(Collection<DependenciaDocumento> dependenciaDocumentoCollection) {
        this.dependenciaDocumentoCollection = dependenciaDocumentoCollection;
    }

    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idDocumento != null ? idDocumento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Documento)) {
            return false;
        }
        Documento other = (Documento) object;
        if ((this.idDocumento == null && other.idDocumento != null) || (this.idDocumento != null && !this.idDocumento.equals(other.idDocumento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.documental.bo.Documento[ idDocumento=" + idDocumento + " ]";
    }

}

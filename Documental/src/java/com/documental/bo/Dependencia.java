/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.documental.bo;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
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
 * @author Diego
 */
@Entity
@Table(name = "dependencia")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Dependencia.findAll", query = "SELECT d FROM Dependencia d"),
    @NamedQuery(name = "Dependencia.findByIdDependencia", query = "SELECT d FROM Dependencia d WHERE d.idDependencia = :idDependencia"),
    @NamedQuery(name = "Dependencia.findByNombre", query = "SELECT d FROM Dependencia d WHERE d.nombre = :nombre"),
    @NamedQuery(name = "Dependencia.findByEstado", query = "SELECT d FROM Dependencia d WHERE d.estado = :estado"),
    @NamedQuery(name = "Dependencia.findAllWithDirector", query = "SELECT d FROM Dependencia d, DependenciaDirector dd WHERE d.idDependencia = dd.dependencia.idDependencia and dd.estado = 'A'")
})
public class Dependencia implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id_dependencia")
    private Integer idDependencia;
    @Basic(optional = false)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @Column(name = "estado")
    private String estado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "dependencia")
    private Collection<DependenciaEmpleado> dependenciaEmpleadoCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "dependencia")
    private Collection<DependenciaDocumento> dependenciaDocumentoCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "dependencia")
    private Collection<DependenciaDirector> dependenciaDirectorCollection;

    public Dependencia() {
    }

    public Dependencia(Integer idDependencia) {
        this.idDependencia = idDependencia;
    }

    public Dependencia(Integer idDependencia, String nombre, String estado) {
        this.idDependencia = idDependencia;
        this.nombre = nombre;
        this.estado = estado;
    }

    public Integer getIdDependencia() {
        return idDependencia;
    }

    public void setIdDependencia(Integer idDependencia) {
        this.idDependencia = idDependencia;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @XmlTransient
    public Collection<DependenciaEmpleado> getDependenciaEmpleadoCollection() {
        return dependenciaEmpleadoCollection;
    }

    public void setDependenciaEmpleadoCollection(Collection<DependenciaEmpleado> dependenciaEmpleadoCollection) {
        this.dependenciaEmpleadoCollection = dependenciaEmpleadoCollection;
    }

    @XmlTransient
    public Collection<DependenciaDocumento> getDependenciaDocumentoCollection() {
        return dependenciaDocumentoCollection;
    }

    public void setDependenciaDocumentoCollection(Collection<DependenciaDocumento> dependenciaDocumentoCollection) {
        this.dependenciaDocumentoCollection = dependenciaDocumentoCollection;
    }

    @XmlTransient
    public Collection<DependenciaDirector> getDependenciaDirectorCollection() {
        return dependenciaDirectorCollection;
    }

    public void setDependenciaDirectorCollection(Collection<DependenciaDirector> dependenciaDirectorCollection) {
        this.dependenciaDirectorCollection = dependenciaDirectorCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idDependencia != null ? idDependencia.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Dependencia)) {
            return false;
        }
        Dependencia other = (Dependencia) object;
        if ((this.idDependencia == null && other.idDependencia != null) || (this.idDependencia != null && !this.idDependencia.equals(other.idDependencia))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.documental.bo.Dependencia[ idDependencia=" + idDependencia + " ]";
    }
    /**
     * Establecer el estado a partir de un atributo booleano
     * @param activo 
     */
    public void setBooleanEstado(boolean activo) {
        if(activo){
        this.estado = "Activo";
        }else{
        this.estado = "Inactivo";
        }
    }
    /**
     * Obtener el estado en un retorno de tipo booleano
     * @return estado de la dependencia
     */
    public boolean getBooleanEstado(){
    return "Activo".equals(this.estado);
    }
    
}

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
@Table(name = "dependencia_empleado")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DependenciaEmpleado.findAll", query = "SELECT d FROM DependenciaEmpleado d"),
    @NamedQuery(name = "DependenciaEmpleado.findByDependenciaId", query = "SELECT d FROM DependenciaEmpleado d WHERE d.dependenciaEmpleadoPK.dependenciaId = :dependenciaId"),
    @NamedQuery(name = "DependenciaEmpleado.findByLoginId", query = "SELECT d FROM DependenciaEmpleado d WHERE d.dependenciaEmpleadoPK.loginId = :loginId"),
    @NamedQuery(name = "DependenciaEmpleado.findByFecha", query = "SELECT d FROM DependenciaEmpleado d WHERE d.fecha = :fecha")})
public class DependenciaEmpleado implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected DependenciaEmpleadoPK dependenciaEmpleadoPK;
    @Basic(optional = false)
    @Column(name = "fecha")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    @JoinColumn(name = "dependencia_id", referencedColumnName = "id_dependencia", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Dependencia dependencia;
    @JoinColumn(name = "login_id", referencedColumnName = "id_login", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Login login;
    @Column(name = "estado")
    private String estado;

    public DependenciaEmpleado() {
    }

    public DependenciaEmpleado(DependenciaEmpleadoPK dependenciaEmpleadoPK) {
        this.dependenciaEmpleadoPK = dependenciaEmpleadoPK;
    }

    public DependenciaEmpleado(DependenciaEmpleadoPK dependenciaEmpleadoPK, Date fecha) {
        this.dependenciaEmpleadoPK = dependenciaEmpleadoPK;
        this.fecha = fecha;
    }

    public DependenciaEmpleado(int dependenciaId, int loginId) {
        this.dependenciaEmpleadoPK = new DependenciaEmpleadoPK(dependenciaId, loginId);
    }

    public DependenciaEmpleadoPK getDependenciaEmpleadoPK() {
        return dependenciaEmpleadoPK;
    }

    public void setDependenciaEmpleadoPK(DependenciaEmpleadoPK dependenciaEmpleadoPK) {
        this.dependenciaEmpleadoPK = dependenciaEmpleadoPK;
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

    public Login getLogin() {
        return login;
    }

    public void setLogin(Login login) {
        this.login = login;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (dependenciaEmpleadoPK != null ? dependenciaEmpleadoPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DependenciaEmpleado)) {
            return false;
        }
        DependenciaEmpleado other = (DependenciaEmpleado) object;
        if ((this.dependenciaEmpleadoPK == null && other.dependenciaEmpleadoPK != null) || (this.dependenciaEmpleadoPK != null && !this.dependenciaEmpleadoPK.equals(other.dependenciaEmpleadoPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.documental.bo.DependenciaEmpleado[ dependenciaEmpleadoPK=" + dependenciaEmpleadoPK + " ]";
    }

}

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
@Table(name = "dependencia_director")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DependenciaDirector.findAll", query = "SELECT d FROM DependenciaDirector d"),
    @NamedQuery(name = "DependenciaDirector.findByDependenciaId", query = "SELECT d FROM DependenciaDirector d WHERE d.dependenciaDirectorPK.dependenciaId = :dependenciaId"),
    @NamedQuery(name = "DependenciaDirector.findByLoginId", query = "SELECT d FROM DependenciaDirector d WHERE d.dependenciaDirectorPK.loginId = :loginId"),
    @NamedQuery(name = "DependenciaDirector.findByFecha", query = "SELECT d FROM DependenciaDirector d WHERE d.fecha = :fecha")})
public class DependenciaDirector implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected DependenciaDirectorPK dependenciaDirectorPK;
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

    public DependenciaDirector() {
    }

    public DependenciaDirector(DependenciaDirectorPK dependenciaDirectorPK) {
        this.dependenciaDirectorPK = dependenciaDirectorPK;
    }

    public DependenciaDirector(DependenciaDirectorPK dependenciaDirectorPK, Date fecha) {
        this.dependenciaDirectorPK = dependenciaDirectorPK;
        this.fecha = fecha;
    }

    public DependenciaDirector(int dependenciaId, int loginId) {
        this.dependenciaDirectorPK = new DependenciaDirectorPK(dependenciaId, loginId);
    }

    public DependenciaDirectorPK getDependenciaDirectorPK() {
        return dependenciaDirectorPK;
    }

    public void setDependenciaDirectorPK(DependenciaDirectorPK dependenciaDirectorPK) {
        this.dependenciaDirectorPK = dependenciaDirectorPK;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (dependenciaDirectorPK != null ? dependenciaDirectorPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DependenciaDirector)) {
            return false;
        }
        DependenciaDirector other = (DependenciaDirector) object;
        if ((this.dependenciaDirectorPK == null && other.dependenciaDirectorPK != null) || (this.dependenciaDirectorPK != null && !this.dependenciaDirectorPK.equals(other.dependenciaDirectorPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.documental.bo.DependenciaDirector[ dependenciaDirectorPK=" + dependenciaDirectorPK + " ]";
    }
    
}

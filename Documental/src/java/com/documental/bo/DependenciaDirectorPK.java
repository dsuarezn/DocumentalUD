/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.documental.bo;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author Diego
 */
@Embeddable
public class DependenciaDirectorPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "dependencia_id")
    private int dependenciaId;
    @Basic(optional = false)
    @Column(name = "login_id")
    private int loginId;

    public DependenciaDirectorPK() {
    }

    public DependenciaDirectorPK(int dependenciaId, int loginId) {
        this.dependenciaId = dependenciaId;
        this.loginId = loginId;
    }

    public int getDependenciaId() {
        return dependenciaId;
    }

    public void setDependenciaId(int dependenciaId) {
        this.dependenciaId = dependenciaId;
    }

    public int getLoginId() {
        return loginId;
    }

    public void setLoginId(int loginId) {
        this.loginId = loginId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) dependenciaId;
        hash += (int) loginId;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DependenciaDirectorPK)) {
            return false;
        }
        DependenciaDirectorPK other = (DependenciaDirectorPK) object;
        if (this.dependenciaId != other.dependenciaId) {
            return false;
        }
        if (this.loginId != other.loginId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.documental.bo.DependenciaDirectorPK[ dependenciaId=" + dependenciaId + ", loginId=" + loginId + " ]";
    }
    
}

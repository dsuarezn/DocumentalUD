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
public class DependenciaDocumentoPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "dependencia_id")
    private int dependenciaId;
    @Basic(optional = false)
    @Column(name = "documento_id")
    private int documentoId;

    public DependenciaDocumentoPK() {
    }

    public DependenciaDocumentoPK(int dependenciaId, int documentoId) {
        this.dependenciaId = dependenciaId;
        this.documentoId = documentoId;
    }

    public int getDependenciaId() {
        return dependenciaId;
    }

    public void setDependenciaId(int dependenciaId) {
        this.dependenciaId = dependenciaId;
    }

    public int getDocumentoId() {
        return documentoId;
    }

    public void setDocumentoId(int documentoId) {
        this.documentoId = documentoId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) dependenciaId;
        hash += (int) documentoId;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DependenciaDocumentoPK)) {
            return false;
        }
        DependenciaDocumentoPK other = (DependenciaDocumentoPK) object;
        if (this.dependenciaId != other.dependenciaId) {
            return false;
        }
        if (this.documentoId != other.documentoId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.documental.bo.DependenciaDocumentoPK[ dependenciaId=" + dependenciaId + ", documentoId=" + documentoId + " ]";
    }
    
}

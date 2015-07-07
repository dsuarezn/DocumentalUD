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
public class AnexoPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "id_anexo")
    private int idAnexo;
    @Basic(optional = false)
    @Column(name = "documento_id")
    private int documentoId;

    public AnexoPK() {
    }

    public AnexoPK(int idAnexo, int documentoId) {
        this.idAnexo = idAnexo;
        this.documentoId = documentoId;
    }

    public int getIdAnexo() {
        return idAnexo;
    }

    public void setIdAnexo(int idAnexo) {
        this.idAnexo = idAnexo;
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
        hash += (int) idAnexo;
        hash += (int) documentoId;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AnexoPK)) {
            return false;
        }
        AnexoPK other = (AnexoPK) object;
        if (this.idAnexo != other.idAnexo) {
            return false;
        }
        if (this.documentoId != other.documentoId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.documental.bo.AnexoPK[ idAnexo=" + idAnexo + ", documentoId=" + documentoId + " ]";
    }
    
}

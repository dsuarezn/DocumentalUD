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
public class HistoricoPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "documento_id")
    private int documentoId;
    @Basic(optional = false)
    @Column(name = "origen_id")
    private int origenId;
    @Basic(optional = false)
    @Column(name = "destinatario_id")
    private int destinatarioId;

    public HistoricoPK() {
    }

    public HistoricoPK(int documentoId, int origenId, int destinatarioId) {
        this.documentoId = documentoId;
        this.origenId = origenId;
        this.destinatarioId = destinatarioId;
    }

    public int getDocumentoId() {
        return documentoId;
    }

    public void setDocumentoId(int documentoId) {
        this.documentoId = documentoId;
    }

    public int getOrigenId() {
        return origenId;
    }

    public void setOrigenId(int origenId) {
        this.origenId = origenId;
    }

    public int getDestinatarioId() {
        return destinatarioId;
    }

    public void setDestinatarioId(int destinatarioId) {
        this.destinatarioId = destinatarioId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) documentoId;
        hash += (int) origenId;
        hash += (int) destinatarioId;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HistoricoPK)) {
            return false;
        }
        HistoricoPK other = (HistoricoPK) object;
        if (this.documentoId != other.documentoId) {
            return false;
        }
        if (this.origenId != other.origenId) {
            return false;
        }
        if (this.destinatarioId != other.destinatarioId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.documental.bo.HistoricoPK[ documentoId=" + documentoId + ", origenId=" + origenId + ", destinatarioId=" + destinatarioId + " ]";
    }
    
}

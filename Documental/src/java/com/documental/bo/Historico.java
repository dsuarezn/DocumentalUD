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
@Table(name = "historico")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Historico.findAll", query = "SELECT h FROM Historico h"),
    @NamedQuery(name = "Historico.findByComentario", query = "SELECT h FROM Historico h WHERE h.comentario = :comentario"),
    @NamedQuery(name = "Historico.findByDocumentoId", query = "SELECT h FROM Historico h WHERE h.historicoPK.documentoId = :documentoId"),
    @NamedQuery(name = "Historico.findByOrigenId", query = "SELECT h FROM Historico h WHERE h.historicoPK.origenId = :origenId"),
    @NamedQuery(name = "Historico.findByDestinatarioId", query = "SELECT h FROM Historico h WHERE h.historicoPK.destinatarioId = :destinatarioId"),
    @NamedQuery(name = "Historico.findByFecha", query = "SELECT h FROM Historico h WHERE h.fecha = :fecha"),
    @NamedQuery(name = "Historico.findByActivo", query = "SELECT h FROM Historico h WHERE h.activo = :activo")})
public class Historico implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected HistoricoPK historicoPK;
    @Basic(optional = false)
    @Column(name = "comentario")
    private String comentario;
    @Basic(optional = false)
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Basic(optional = false)
    @Column(name = "activo")
    private boolean activo;
    @JoinColumn(name = "destinatario_id", referencedColumnName = "id_login", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Login loginDestinatario;
    @JoinColumn(name = "origen_id", referencedColumnName = "id_login", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Login loginOrigen;

    public Historico() {
    }

    public Historico(HistoricoPK historicoPK) {
        this.historicoPK = historicoPK;
    }

    public Historico(HistoricoPK historicoPK, String comentario, Date fecha, boolean activo) {
        this.historicoPK = historicoPK;
        this.comentario = comentario;
        this.fecha = fecha;
        this.activo = activo;
    }

    public Historico(int documentoId, int origenId, int destinatarioId) {
        this.historicoPK = new HistoricoPK(documentoId, origenId, destinatarioId);
    }

    public HistoricoPK getHistoricoPK() {
        return historicoPK;
    }

    public void setHistoricoPK(HistoricoPK historicoPK) {
        this.historicoPK = historicoPK;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public boolean getActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public Login getLoginDestinatario() {
        return loginDestinatario;
    }

    public void setLoginDestinatario(Login login) {
        this.loginDestinatario = login;
    }

    public Login getLoginOrigen() {
        return loginOrigen;
    }

    public void setLoginOrigen(Login login1) {
        this.loginOrigen = login1;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (historicoPK != null ? historicoPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Historico)) {
            return false;
        }
        Historico other = (Historico) object;
        if ((this.historicoPK == null && other.historicoPK != null) || (this.historicoPK != null && !this.historicoPK.equals(other.historicoPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.documental.bo.Historico[ historicoPK=" + historicoPK + " ]";
    }
    
}

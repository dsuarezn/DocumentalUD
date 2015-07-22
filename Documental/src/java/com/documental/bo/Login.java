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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "login")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Login.findAll", query = "SELECT l FROM Login l"),
    @NamedQuery(name = "Login.findByIdLogin", query = "SELECT l FROM Login l WHERE l.idLogin = :idLogin"),
    @NamedQuery(name = "Login.findByUsuario", query = "SELECT l FROM Login l WHERE l.usuario = :usuario"),
    @NamedQuery(name = "Login.findByContrasena", query = "SELECT l FROM Login l WHERE l.contrasena = :contrasena"),
    @NamedQuery(name = "Login.findByNombre", query = "SELECT l FROM Login l WHERE l.nombre = :nombre"),
    @NamedQuery(name = "Login.findByApellido", query = "SELECT l FROM Login l WHERE l.apellido = :apellido"),
    @NamedQuery(name = "Login.findByEstado", query = "SELECT l FROM Login l WHERE l.estado = :estado"),
    @NamedQuery(name = "Login.findByCorreo", query = "SELECT l FROM Login l WHERE l.correo = :correo"),
    @NamedQuery(name = "Login.findDirectorByDependencia", query = "SELECT l FROM Login l, DependenciaDirector dd WHERE l.idLogin = dd.login.idLogin and dd.dependencia.idDependencia = :idDependencia"),
    @NamedQuery(name = "Login.findEmpleadosByDependencia", query = "SELECT l FROM Login l, DependenciaEmpleado de WHERE l.idLogin = de.login.idLogin and de.dependencia.idDependencia = :idDependencia")
})
public class Login implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id_login")
    private Integer idLogin;
    @Basic(optional = false)
    @Column(name = "usuario")
    private String usuario;
    @Basic(optional = false)
    @Column(name = "contrasena")
    private String contrasena;
    @Basic(optional = false)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @Column(name = "apellido")
    private String apellido;
    @Column(name = "estado")
    private String estado;
    @Column(name = "correo")
    private String correo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "loginDestinatario")
    private Collection<Historico> historicoCollection;    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "login")
    private Collection<DependenciaEmpleado> dependenciaEmpleadoCollection;
    @JoinColumn(name = "tipo_usuario", referencedColumnName = "id_tipo_usuario")
    @ManyToOne(optional = false)
    private TipoUsuario tipoUsuario;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "login")
    private Collection<DependenciaDirector> dependenciaDirectorCollection;

    public Login() {
    }

    public Login(Integer idLogin) {
        this.idLogin = idLogin;
    }

    public Login(Integer idLogin, String usuario, String contrasena, String nombre, String apellido) {
        this.idLogin = idLogin;
        this.usuario = usuario;
        this.contrasena = contrasena;
        this.nombre = nombre;
        this.apellido = apellido;
    }

    public Integer getIdLogin() {
        return idLogin;
    }

    public void setIdLogin(Integer idLogin) {
        this.idLogin = idLogin;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    @XmlTransient
    public Collection<Historico> getHistoricoCollection() {
        return historicoCollection;
    }

    public void setHistoricoCollection(Collection<Historico> historicoCollection) {
        this.historicoCollection = historicoCollection;
    }  

    @XmlTransient
    public Collection<DependenciaEmpleado> getDependenciaEmpleadoCollection() {
        return dependenciaEmpleadoCollection;
    }

    public void setDependenciaEmpleadoCollection(Collection<DependenciaEmpleado> dependenciaEmpleadoCollection) {
        this.dependenciaEmpleadoCollection = dependenciaEmpleadoCollection;
    }

    public TipoUsuario getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(TipoUsuario tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
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
        hash += (idLogin != null ? idLogin.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Login)) {
            return false;
        }
        Login other = (Login) object;
        if ((this.idLogin == null && other.idLogin != null) || (this.idLogin != null && !this.idLogin.equals(other.idLogin))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.documental.bo.Login[ idLogin=" + idLogin + " ]";
    }
    
}

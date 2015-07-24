/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.documental.dao.jpa;

import com.documental.dao.AnexoDAO;
import com.documental.dao.ComentarioDAO;
import com.documental.dao.DAOFactory;
import com.documental.dao.DocumentoDAO;
import com.documental.dao.DependenciaDAO;
import com.documental.dao.DependenciaDirectorDAO;
import com.documental.dao.DependenciaEmpleadoDAO;
import com.documental.dao.DirectorDAO;
import com.documental.dao.EmpleadoDAO;
import com.documental.dao.HistoricoDAO;
import com.documental.dao.LoginDAO;
import com.documental.dao.NivelAccesoDAO;
import com.documental.dao.TareaDAO;
import com.documental.dao.TipoDAO;
import com.documental.dao.TipoUsuarioDAO;

/**
 *
 * @author DiegoM
 */
public class DAOJPAFactory implements DAOFactory{
    
    @Override
    public LoginDAO getLoginDAO() {
        return new LoginDAOJPAImpl();
    }

    @Override
    public NivelAccesoDAO getNivelAccesoDAO() {
        return new NivelAccesoDAOJPAImpl();
    }

    @Override
    public TareaDAO getTareaDAO() {
        return new TareaDAOJPAImpl();
    }

    @Override
    public TipoUsuarioDAO getTipoUsuarioDAO() {
        return new TipoUsuarioDAOJPAImpl();
    }

    @Override
    public DocumentoDAO getDocumentoDAO() {
        return new DocumentoDAOJPAImpl();
    }

    @Override
    public TipoDAO getTipoDAO() {
        return new TipoDAOJPAImpl();
    }

    @Override
    public AnexoDAO getAnexoDAO() {
        return new AnexoDAOJPAImpl();
    }
    
    public DependenciaDAO getDependenciaDAO() {
       return new DependenciaDAOJPAImpl();
    }

    @Override
    public HistoricoDAO getHistoricoDAO() {
        return new HistoricoDAOJPAImpl();
    }
    
    @Override
    public DirectorDAO getDirectorDAO() {
        return new DirectorDAOJPAImpl();
    }    

    @Override
    public EmpleadoDAO getEmpleadoDAO() {
        return new EmpleadoDAOJPAImpl();
    }

    @Override
    public ComentarioDAO getComentarioDAO() {
        return new ComentarioDAOJPAImpl();
    }

    @Override
    public DependenciaDirectorDAO getDependenciaDirectorDAO() {
        return new DependenciaDirectorDAOJPAImpl();
    }

    @Override
    public DependenciaEmpleadoDAO getDependenciaEmpleadoDAO() {
        return new DependenciaEmpleadoDAOJPAImpl();
    }
    
    
}

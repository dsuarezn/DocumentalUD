/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.documental.servicios;

import com.documental.bo.TipoUsuario;
import java.util.List;

/**
 *
 * @author DiegoM
 */
public interface ServicioTipoUsuario {
    public int getCount();
    public int getMaxId();
    public TipoUsuario buscarPorEntity(Integer id);
    public List<TipoUsuario> buscarPorEntityList(Integer id);
    public String salvarTipoUsuario(TipoUsuario tipo);
    public String borrarTipoUsuario(TipoUsuario tipo);
    public List<TipoUsuario> buscarTodosTipoUsuario();    
    public TipoUsuario buscarPorClave(Integer id); 
}

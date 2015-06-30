/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.documental.servicios;

import com.documental.bo.NivelAcceso;
import java.util.List;

/**
 *
 * @author DiegoM
 */
public interface ServicioNivelAcceso {    
    public int getCount();
    public int getMaxId();
    public String salvarNivel(NivelAcceso nivel);
    public String borrarNivel(NivelAcceso nivel);
    public List<NivelAcceso> buscarTodosNiveles();    
    public NivelAcceso buscarPorClave(Integer id);    
}

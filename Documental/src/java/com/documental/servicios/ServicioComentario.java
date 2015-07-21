/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.documental.servicios;

import java.util.List;
import com.documental.bo.Comentario;

/**
 *
 * @author Alexander
 */
public interface ServicioComentario {
        
    public List<Comentario> obtenerComentariosDocumento(Integer documentoId);    
    public Long contarComentariosDocumento(Integer documentoId);    
    public String salvarComentario(Comentario comentario);
    public void borrarComentario(Comentario comentario);    
    public Comentario obtenerComentarioPorId(Integer comentarioId);
    
}

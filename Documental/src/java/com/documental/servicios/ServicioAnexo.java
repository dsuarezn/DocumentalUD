/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.documental.servicios;

import com.documental.bo.Anexo;
import java.util.List;

/**
 *
 * @author Alexander
 */
public interface ServicioAnexo {
    
    public Integer getCount(Integer documentoId);
    public int getCount();
    public int getMaxId();
    public String salvarAnexo(Anexo anexo);
    public Anexo consultarAnexoPorId(Integer Id);
    public List<Anexo> consultarTodosAnexo();
    public List<Anexo> consultarAnexosPorDocumento(Integer documentoId);
    public void borrarAnexo(Anexo anexo);
    
}

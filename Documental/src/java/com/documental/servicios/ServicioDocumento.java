/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.documental.servicios;

import com.documental.bo.Documento;
import java.util.List;

/**
 *
 * @author Alexander
 */
public interface ServicioDocumento {
    
    public int getMaxId();
    public String salvarDocumento(Documento documento);
    public Documento consultarDocumentoPorId(Integer Id);
    public List<Documento> consultarTodosDocumentos();
    public void borrarDocumento(Documento documento);
}
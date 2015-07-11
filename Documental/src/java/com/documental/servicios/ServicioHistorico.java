/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.documental.servicios;

import com.documental.bo.Historico;
import com.documental.bo.HistoricoPK;
import java.util.List;

/**
 *
 * @author Alexander
 */
public interface ServicioHistorico {
    
    public String salvarHistorico(Historico historico);
    public Historico consultarHistoricoPorId(HistoricoPK Id);
    public List<Historico> consultarTodosHistoricos();
    public void borrarHistorico(Historico historico);
}

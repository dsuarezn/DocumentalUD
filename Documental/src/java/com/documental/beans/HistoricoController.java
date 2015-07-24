/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.documental.beans;

import com.documental.bo.*;
import com.documental.servicios.ServicioHistorico;
import com.documental.servicios.impl.ServicioHistoricoImpl;
import com.documental.util.PaginationHelper;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

/**
 *
 * @author apple
 */

@ManagedBean(name = "beanHistorico")
@SessionScoped
public class HistoricoController {
    
    private DataModel items = null;
    private PaginationHelper pagination = null;
    private ServicioHistorico servicio;
    private Integer documentId;
    private List<Historico> listHistorico;
    
    public DataModel getItems() {
        if (items == null) {
            items = getPagination().createPageDataModel();
        }
        return items;
    }
    
    public ServicioHistorico getServicio() {
        if (servicio == null) {
            servicio = new ServicioHistoricoImpl();
        }
        return servicio;
    }
    
    public PaginationHelper getPagination() {
        if (pagination == null) {
            pagination = new PaginationHelper((10)) {
                @Override
                public int getItemsCount() {
                    return getServicio().getCountForDocument(getDocumentId());
                }

                @Override
                public DataModel createPageDataModel() {
                    
                    List<Historico> tempList = getServicio().consultarHistoricoPorDocumentId(getDocumentId());
                    Collections.sort(tempList, new Comparator<Historico>() {
                        public int compare(Historico h1, Historico h2) {
                            return h1.getFecha().compareTo(h2.getFecha());
                        }
                    });
                    
                    setListHistorico(tempList);
                    return new ListDataModel(getListHistorico());
                }

            };
        }
        return pagination;
    }
    
    public void setDocumentId(Integer documentId) {
        this.documentId = documentId;
    }

    public Integer getDocumentId() {
        return documentId;
    }

    public void setListHistorico(List<Historico> listHistorico) {
        this.listHistorico = listHistorico;
    }

    public List<Historico> getListHistorico() {
        return listHistorico;
    }

    public String prepareList(Integer documentId){
        this.documentId = documentId;        
        pagination = null;
        items = null;      
        listHistorico = null;
        return "/GUI/Gestion/BandejaEntrada/GUIHistorico";
    }
    
    public String volver() {          
        return "/GUI/Gestion/BandejaEntrada/GUIDocumentoDetalle";
    }
}

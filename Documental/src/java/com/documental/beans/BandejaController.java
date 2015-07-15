/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.documental.beans;

import com.documental.bo.Documento;
import com.documental.bo.Historico;
import com.documental.bo.Login;
import com.documental.servicios.ServicioDocumento;
import com.documental.servicios.ServicioHistorico;
import com.documental.servicios.ServicioLogin;
import com.documental.servicios.impl.ServicioDocumentoImpl;
import com.documental.servicios.impl.ServicioHistoricoImpl;
import com.documental.servicios.impl.ServicioLoginImpl;
import com.documental.util.PaginationHelper;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.DataModel;

/**
 *
 * @author enrique
 */
@ManagedBean(name = "beanBandeja")
@SessionScoped
public class BandejaController {

    private Documento current;
    private DataModel items = null;
    private PaginationHelper pagination;
    private List<Documento> listDocumento = null;
    private List<Historico> listHistorico = null;
    private List<Login> listOrigen = null;
    private boolean activo;
    private ServicioDocumento servicioDocumento;
    private ServicioHistorico servicioHistorico;
    private ServicioLogin servicioLogin;

    public BandejaController() {
    }

    public Documento getCurrent() {
        return current;
    }

    public void setCurrent(Documento current) {
        this.current = current;
    }

    public List<Documento> getListDocumento() {
        if (listDocumento == null) {
            prepareDocuments();
        }
        return listDocumento;
    }

    public List<Login> getListOrigen() {
        if (listOrigen == null) {
            listOrigen = new ArrayList<Login>();
        }
        return listOrigen;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public ServicioDocumento getServicioDocumento() {
        if (servicioDocumento == null) {
            servicioDocumento = new ServicioDocumentoImpl();
        }
        return servicioDocumento;
    }

    public ServicioHistorico getServicioHistorico() {
        if (servicioHistorico == null) {
            servicioHistorico = new ServicioHistoricoImpl();
        }
        return servicioHistorico;
    }

    public ServicioLogin getServicioLogin() {
        if (servicioLogin == null) {
            servicioLogin = new ServicioLoginImpl();
        }
        return servicioLogin;
    }

    /**
     * Lista todos los documentos activos asociados a un usuario como destino
     *
     */
    public void prepareDocuments() {
        Login login = getServicioLogin().buscarPorClave(3);
        listHistorico = getServicioHistorico().consultarTodosHistoricos();
        listDocumento = new ArrayList<Documento>();
        listOrigen = new ArrayList<Login>();
        Documento tmp = null;
        for (Historico h : listHistorico) {
            //verificar el destinatario y la bandera activo del historico
            if (h.getLoginDestinatario().equals(login) && h.getActivo()) {
                tmp = getServicioDocumento().consultarDocumentoPorId(h.getHistoricoPK().getDocumentoId());
                    listDocumento.add(tmp);
                    // obtener el ultimo usuario
                    listOrigen.add(h.getLoginOrigen());
            }
        }
    }

//    public DataModel getItems() {
//        if (items == null) {
//            items = getPagination().createPageDataModel();
//        }
//        return items;
//    }
//    public PaginationHelper getPagination() {
//        if (pagination == null) {
//            pagination = new PaginationHelper((10)) {
//                @Override
//                public int getItemsCount() {
//                    return getServicioDocumento().getCount();
//                }
//
//                @Override
//                public DataModel createPageDataModel() {
//                    setListDependencia(getServicio().buscarTodosDependencia());
//                    return new ListDataModel(getListDependencia());
//                }
//            };
//        }
//        return pagination;
//    }
}

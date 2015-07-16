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
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.servlet.http.HttpServletRequest;

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
    private List<Historico> listHistorico = null;
    private List<Object[]> consolidado;

    private ServicioDocumento servicioDocumento;
    private ServicioHistorico servicioHistorico;
    private ServicioLogin servicioLogin;
    private String usuarioActual = ((HttpServletRequest) FacesContext.getCurrentInstance().
            getExternalContext().getRequest()).getSession().getAttribute("user").toString();

    private int usuario = 0;

    public int getUsuario() {
        if (usuario == 0) {
            usuario = getServicioLogin().buscarPorUsuario(usuarioActual).getIdLogin();
        }
        return usuario;
    }

    public List<Object[]> getConsolidado() {
        return consolidado;
    }

    public BandejaController() {
    }

    public Documento getCurrent() {
        return current;
    }

    public void setCurrent(Documento current) {
        this.current = current;
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

    public String prepareList() {
        listHistorico = getServicioHistorico().buscarDestinatarioActivo(getUsuario());
        consolidado = new ArrayList<Object[]>();
        Documento tmp = null;
        for (Historico h : listHistorico) {
            tmp = getServicioDocumento().consultarDocumentoPorId(h.getHistoricoPK().getDocumentoId());
            Object[] con = {tmp, h.getLoginOrigen(), tmp.getTipoId().getNombre()};
            consolidado.add(con);
        }
        return "/GUI/Gestion/BandejaEntrada/GUIBandejaEntrada";
    }

    public DataModel getItems() {
        if (items == null) {
            items = getPagination().createPageDataModel();
        }
        return items;
    }

    public PaginationHelper getPagination() {
        if (pagination == null) {
            pagination = new PaginationHelper((10)) {
                @Override
                public int getItemsCount() {
                    return getConsolidado().size();
                }

                @Override
                public DataModel createPageDataModel() {

                    return new ListDataModel(getConsolidado());
                }
            };
        }
        return pagination;
    }

    public void detalle(Documento d) {
        //por implementar
    }

}

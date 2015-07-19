/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.documental.beans;

import com.documental.bo.Tipo;
import com.documental.bo.Documento;
import com.documental.bo.Dependencia;
import com.documental.bo.Historico;
import com.documental.bo.HistoricoPK;
import com.documental.bo.Login;
import com.documental.dto.LoginDTO;
import com.documental.enums.EstadosDocumentoEnum;
import com.documental.enums.FinalidadDocumentoEnum;
import com.documental.enums.PrioridadDocumentoEnum;
import com.documental.enums.VisibilidadDocumentoEnum;
import com.documental.servicios.ServicioDependencia;
import com.documental.servicios.ServicioDocumento;
import com.documental.servicios.ServicioHistorico;
import com.documental.servicios.ServicioLogin;
import com.documental.servicios.ServicioTipo;
import com.documental.servicios.impl.ServicioDependenciaImpl;
import com.documental.servicios.impl.ServicioDocumentoImpl;
import com.documental.servicios.impl.ServicioHistoricoImpl;
import com.documental.servicios.impl.ServicioLoginImpl;
import com.documental.servicios.impl.ServicioTipoImpl;
import com.documental.util.JsfUtil;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import java.util.List;
import java.util.ResourceBundle;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;


/**
 *
 * @author DiegoM
 */
@ManagedBean(name = "beanRadicacion")
@RequestScoped
public class RadicacionController {

    //Variables usadas en el controlador
    private Documento docTrabajo;
    
  
    
    //Destinados para historico
    private Integer idDependenciaDestino;
    private Integer idUsuarioOrigen;    
    private Integer idTipo;
    
    //Variables usadas en el controlador
    private ServicioLogin servicioLogin;
    private ServicioDocumento servicioDocumento;
    private ServicioTipo servicioTipo;
    private ServicioHistorico servicioHistorico;
    private ServicioDependencia servicioDependencia;
    private AnexoController controladorAnexo;
    
    
    /**
     * Creates a new instance of GestionController
     */
    public RadicacionController() {
        
    }
    
    public String getCurrentDate(){        
        SimpleDateFormat formato = new SimpleDateFormat("EEEEE, d 'de' MMMMM 'del' yyyy");
        return formato.format(new Date());
    }
    
    public String prepareCreate() {
        return "/GUI/Radicador/GUIRadicar";
    }

    private ServicioLogin getServicioLogin() {
        if (servicioLogin == null) {
            servicioLogin = new ServicioLoginImpl();
        }
        return servicioLogin;
    }   
    private ServicioDocumento getServicioDocumento() {
        if (servicioDocumento == null) {
            servicioDocumento = new ServicioDocumentoImpl();
        }
        return servicioDocumento;
    }    
    private ServicioTipo getServicioTipo() {
        if (servicioTipo == null) {
            servicioTipo = new ServicioTipoImpl();
        }
        return servicioTipo;
    }
    private ServicioDependencia getServicioDependencia(){
        if (servicioDependencia == null) {
            servicioDependencia = new ServicioDependenciaImpl();
        }
        return servicioDependencia;
    }
    private ServicioHistorico getServicioHistorico(){
        if (servicioHistorico == null) {
            servicioHistorico = new ServicioHistoricoImpl();
        }
        return servicioHistorico;
    }

    
   private List<LoginDTO> getLoginDTO(List<Login> listaUsuarios){
       List<LoginDTO> listaDTO=new ArrayList<LoginDTO>();
       for (Login usuarioLogin : listaUsuarios) {
           LoginDTO dto=new LoginDTO(usuarioLogin.getIdLogin(),usuarioLogin.getNombre(),usuarioLogin.getApellido(),usuarioLogin.getUsuario(), usuarioLogin.getCorreo(),usuarioLogin.getTipoUsuario());           
           listaDTO.add(dto);
       }
       return listaDTO;
   }
   
    public List<Dependencia> getListaDependencias(){
        return getServicioDependencia().buscarTodosDependenciaConDirector();
    }   
    public List<LoginDTO> getListaUsuarios(){
        return getLoginDTO(getServicioLogin().buscarTodosLogin());
    }
    public List<Tipo> getListaTipos(){
        return getServicioTipo().consultarTodosTipos();
    }    
    public EstadosDocumentoEnum[] getEstadosDocumento(){
       return EstadosDocumentoEnum.values();
    }
    public PrioridadDocumentoEnum[] getPrioridadesDocumento(){
       return PrioridadDocumentoEnum.values();
    }
    public FinalidadDocumentoEnum[] getFinalidadesDocumento(){
       return FinalidadDocumentoEnum.values();
    }    
    public VisibilidadDocumentoEnum[] getVisibilidadesDocumento(){
       return VisibilidadDocumentoEnum.values();
    }

    public Integer getIdUsuarioOrigen() {
        return idUsuarioOrigen;
    }

    public void setIdUsuarioOrigen(Integer idUsuarioOrigen) {
        this.idUsuarioOrigen = idUsuarioOrigen;
    }

    public Integer getIdTipo() {
        return idTipo;
    }

    public void setIdTipo(Integer idTipo) {
        this.idTipo = idTipo;
    }

    
    
      public String volver() {
        return "/";
    }
      
    public Tipo obtenerTipoDocumento(Integer id){
        return getServicioTipo().consultarTipoPorId(id);
    }

    public Integer getIdDependenciaDestino() {
        return idDependenciaDestino;
    }

    public void setIdDependenciaDestino(Integer idDependenciaDestino) {
        this.idDependenciaDestino = idDependenciaDestino;
    }

    public Documento getDocTrabajo() {        
        if (docTrabajo == null) {
            docTrabajo = new Documento();
        }
        return docTrabajo;
    }

    public void setDocTrabajo(Documento docTrabajo) {
        this.docTrabajo = docTrabajo;
    }
    
    
    
    


    private Login obtenerDirectorDependencia(){
        return getServicioLogin().obtenerDirectorDependencia(idDependenciaDestino);
    }
    
    private Login obtenerUsuarioPorCodigo(Integer id){
        return getServicioLogin().buscarPorClave(id);
    }
    
    private String crearRegistroHistoricoInicial(Documento documento){
        if(documento!=null){
            try {
                Login origen =  obtenerUsuarioPorCodigo(idUsuarioOrigen); 
                Login directorDest = obtenerDirectorDependencia();
                HistoricoPK historicoPK=new HistoricoPK();
                historicoPK.setDocumentoId(documento.getIdDocumento());
                historicoPK.setOrigenId(idUsuarioOrigen);
                historicoPK.setDestinatarioId(directorDest.getIdLogin());

                Historico historico=new Historico();
                historico.setActivo(true);
                historico.setComentario("Registro inicial");
                historico.setFecha(new Date());
                historico.setLoginOrigen(origen);
                historico.setLoginDestinatario(directorDest);
                historico.setHistoricoPK(historicoPK);
                String respuesta =  getServicioHistorico().salvarHistorico(historico);
                if (respuesta.equals("Operación Exitosa")) {
                    JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("documental_GUIRadicar_Messages_pCreacionHistoricoExitosa"));
                } else {
                    JsfUtil.addErrorMessage(ResourceBundle.getBundle("/Bundle").getString("documental_GUIRadicar_Messages_pCreacionHistoricoErroneo"));
                }
            } catch (Exception e) {
                JsfUtil.addErrorMessage(ResourceBundle.getBundle("/Bundle").getString("documental_GUIRadicar_Messages_pCreacionHistoricoErroneo") + " " + e.toString());
            }
        } 
        return null;
    }
    
    
    private Documento crearRegistroDocumento(){
        String respuesta = "";
        try {
            Integer id = getServicioDocumento().getMaxId();
            id++;            
            docTrabajo.setFechaCreacion(new Date());
            docTrabajo.setIdDocumento(id);
            docTrabajo.setTipoId(getServicioTipo().consultarTipoPorId(this.idTipo));
            respuesta = getServicioDocumento().salvarDocumento(docTrabajo);            
            if (respuesta.equals("Operación Exitosa")) {
                JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("documental_GUIRadicar_Messages_pCreacionRadicacionExitosa"));
            } else {
                JsfUtil.addErrorMessage(ResourceBundle.getBundle("/Bundle").getString("documental_GUIRadicar_Messages_pCreacionRadicacionErroneo"));
            }
            return getServicioDocumento().consultarDocumentoPorId(id);
            
        } catch (Exception e) {
            JsfUtil.addErrorMessage(ResourceBundle.getBundle("/Bundle").getString("documental_GUIRadicar_Messages_pCreacionRadicacionErroneo") + " " + e.toString());
        }
        return null;
    }
      
    public String create() {
        Documento documento=this.crearRegistroDocumento();
        crearRegistroHistoricoInicial(documento);
        cleanSessionVars();
        return getAnexoController().prepareCreate(documento, "/menuDocumental.xhtml", null);        
    }
    
    public void cleanSessionVars(){
        this.docTrabajo=null;
        idDependenciaDestino=null;
        idTipo=null;
        idUsuarioOrigen=null;
    }
    
    private AnexoController getAnexoController(){
        if(controladorAnexo==null){
//            controladorAnexo  = (AnexoController) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("beanAnexo");        
//            controladorAnexo  = (AnexoController)FacesAccessor.getManagedBean("beanAnexo");
            FacesContext context = FacesContext.getCurrentInstance();
            controladorAnexo = (AnexoController)context.getApplication().evaluateExpressionGet(context, "#{beanAnexo}", AnexoController.class);

        }        
        return controladorAnexo;
    }
   
}

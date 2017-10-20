/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.uesocc.ingenieria.prn335_2017.web.controladores;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;
import sv.edu.uesocc.ingenieria.prn335_2017.datos.definiciones.TipoSeccion;
import sv.edu.uesocc.ingenieria.prn335_2017.datos.acceso.BeansFacadeLocal;
import sv.edu.uesocc.ingenieria.prn335_2017.datos.acceso.TipoSeccionFacadeLocal;

@Named (value="tipoSeccionBean")
@ViewScoped
public class tipoSeccionBean  extends ManagedBeanGenerico<TipoSeccion> implements Serializable{
    
    public tipoSeccionBean() {
    }   
    boolean visible=true;
    @EJB
     TipoSeccionFacadeLocal tipoSeccion;
     TipoSeccion nuevo = new TipoSeccion();
     

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }
    
    public TipoSeccionFacadeLocal getTipoSeccion() {
        return tipoSeccion;
    }

    public TipoSeccion getNuevo() {
        return nuevo;
    }

    public void setNuevo(TipoSeccion nuevo) {
        this.nuevo = nuevo;
    }

    public void setTipoSeccion(TipoSeccionFacadeLocal tipoSeccion) {
        this.tipoSeccion = tipoSeccion;
    }

    
    
//    public List<TipoSeccion> obtener(){
//        List<TipoSeccion> resultado = new ArrayList<>();
//        try{
//            if (tipoSeccion!=null ) {
//                resultado=tipoSeccion.findAll();
//                
//            }
//        }catch (Exception e){
//                    Logger.getLogger(getClass().getName()).log(Level.SEVERE,e.getMessage(), e);
//                
//                    }
//        return resultado;
//    }
    public void showMessage(String Mensaje) {
        
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(Mensaje));
    }

   
    
    @PostConstruct
    public void rellenar(){
        llenar();
    }
    public void limpiar(){
        nuevo.setActivo(false);
        nuevo.setDescripcion(null);
        nuevo.setNombre(null);
    }
    
    
    
    
    public void cancelar(){
        nuevo=new TipoSeccion();
        visible=true;
    }
      public void onRowSelect(SelectEvent event) {
          tipoSeccion.edit(nuevo);
          visible=false;
    }
      public void onRowUnselect(UnselectEvent event) {
          
        visible=true;
          limpiar();
    }

    @Override
    public BeansFacadeLocal<TipoSeccion> getfacadelocal() {
        return  tipoSeccion;
    }

    @Override
    public TipoSeccion getEntity() {
        return this.nuevo;
    }
 
    
    
}

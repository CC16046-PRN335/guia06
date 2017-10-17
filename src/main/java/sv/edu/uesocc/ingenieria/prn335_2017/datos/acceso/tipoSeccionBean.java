/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.uesocc.ingenieria.prn335_2017.datos.acceso;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.primefaces.event.SelectEvent;
import sv.edu.uesocc.ingenieria.prn335_2017.datos.definiciones.TipoSeccion;

import sv.edu.uesocc.ingenieria.prn335_2017.web.controladores.TipoSeccionFacadeLocal;

@Named (value="tipoSeccionBean")
@ViewScoped
public class tipoSeccionBean implements Serializable{
    
    public tipoSeccionBean() {
    }   
    
    @EJB
     TipoSeccionFacadeLocal tipoSeccion;
     List<TipoSeccion> lista = new ArrayList<>();
     TipoSeccion nuevo = new TipoSeccion();
     List<TipoSeccion> Seleccion;
     TipoSeccion seleccionado;
     boolean visible=true;

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public List<TipoSeccion> getSeleccion() {
        return Seleccion;
    }

    public void setSeleccion(List<TipoSeccion> Seleccion) {
        this.Seleccion = Seleccion;
    }

    public TipoSeccion getSeleccionado() {
        return seleccionado;
    }

    public void setSeleccionado(TipoSeccion seleccionado) {
        this.seleccionado = seleccionado;
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

    public List<TipoSeccion> getLista() {
        return lista;
    }

    public void setLista(List<TipoSeccion> lista) {
        this.lista = lista;
    }
    
    public List<TipoSeccion> obtener(){
        List<TipoSeccion> resultado = new ArrayList<>();
        try{
            if (tipoSeccion!=null ) {
                resultado=tipoSeccion.findAll();
                
            }
        }catch (Exception e){
                    Logger.getLogger(getClass().getName()).log(Level.SEVERE,e.getMessage(), e);
                
                    }
        return resultado;
    }
    public void showMessage(String Mensaje) {
        
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(Mensaje));
    }

   
    
    @PostConstruct
    public void llenar(){
        if(lista != null){
            this.lista=tipoSeccion.findAll();
        }else {
            this.lista=Collections.EMPTY_LIST;
        }
    }
    
    public void crear(){
        try{
            llenar();
            tipoSeccion.create(this.nuevo);
            llenar();
            showMessage("Registro realizado correctamente");
            nuevo= new TipoSeccion();
        }catch(Exception e){
             System.out.println("Error: " + e);
                showMessage("Error a la hora de ingresar los datos.");
        }
         }     
    public void editar(){
            try{
            
            tipoSeccion.edit(nuevo);
            llenar();
            showMessage("Editado con exito");
            nuevo= new TipoSeccion();
        }catch(Exception e){
             System.out.println("Error: " + e);
                showMessage("Error a la hora de editar los datos.");
        }
    }
    public void eliminar(){
            try{
            
            tipoSeccion.remove(nuevo);
            llenar();
            showMessage("Elimacion satisfactoria");
            nuevo= new TipoSeccion();
        }catch(Exception e){
             System.out.println("Error: " + e);
                showMessage("Error a la hora de eliminar los datos.");
        }
    }
    public void cancelar(){
        
    }
    
}

package sv.edu.uesocc.ingenieria.prn335_2017.web.controladores;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import sv.edu.uesocc.ingenieria.prn335_2017.datos.acceso.BeansFacadeLocal;

/**
 * 
 * @author j3cs
 * @param <T> 
 */
public abstract class ManagedBeanGenerico<T> implements Serializable {

    T e = getEntity();
    List<T> lista = new ArrayList<>();

    
    public ManagedBeanGenerico() {
    }
    public List<T> getLista() {
        return lista;
    }

    public void setLista(List<T> lista) {
        this.lista = lista;
    }

    public T getE() {
        return e;
    }

    public void setE(T e) {
        this.e = null;
    }

    public void crear() {
        if (getfacadelocal() != null) {
            try {
               getfacadelocal().create(getEntity());
                System.out.println("Registro realizado correctamente");
                llenar();
                setE(getEntity());
            } catch (Exception ex) {
                System.out.println("Error: " + ex);
            }
        }
    }
    public void llenar(){
        if(getfacadelocal().findAll() != null){
            this.lista=getfacadelocal().findAll();
        }else {
            this.lista=Collections.EMPTY_LIST;
        }
    }
public void editar(){
            try{
            getfacadelocal().edit(getEntity());
            System.out.println("Editado con exito");
            llenar();
            setE(getEntity());
        }catch(Exception ex){
             System.out.println("Error: " + ex);
        }
    }

    public void eliminar(){
            try{
            
            getfacadelocal().remove(getEntity());
            llenar();
            System.out.println("Elimacion satisfactoria");
            setE(getEntity());
        }catch(Exception e){
             System.out.println("Error: " + e);
                System.out.println("Error a la hora de eliminar los datos.");
        }
    }
    protected abstract BeansFacadeLocal<T> getfacadelocal();

    public abstract T getEntity();
}

package practica3;

/**
 * Created by sheko on 16/03/15.
 */

import java.util.Hashtable;

public class Vehiculo {
    private String nombre;
    private Hashtable<String, String> tabla
            = new Hashtable<String, String>();
    public Vehiculo(String Nombre){
        this.nombre=Nombre;
    }
    public void setValue(String key, String value){
        this.tabla.put(key, value);
    }
    public boolean containsKey(String key){
        return this.containsKey(key);
    }
    public String getNombre(){
        return nombre;
    }
}
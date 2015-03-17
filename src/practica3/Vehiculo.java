package practica3;

/**
 * Created by sheko on 16/03/15.
 */

import java.util.*;

public class Vehiculo {
    private String nombre;

    public String getSoy() {
        return soy;
    }

    public void setSoy(String soy) {
        this.soy = soy;
    }

    private String soy;
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
    public HashMap isPosible(HashMap properties){
        boolean result=false;
        HashMap map= new HashMap();
        List<String> found =new ArrayList();
        List<String> notFound =new ArrayList();

        for (Object name: properties.keySet()) {

            String key = name.toString();
            if (this.tabla.containsKey(key)) {
                String value = properties.get(name).toString();
                System.out.println(key + " " + value);
                //funcion evaluar
                found.add(key);


            }else{
                notFound.add(key);

            }
        }
        if (result){
            map.put("found",found);
            map.put("notFound",notFound);
        }
        return map;

    }
}
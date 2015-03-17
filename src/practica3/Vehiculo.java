package practica3;

/**
 * Created by sheko on 16/03/15.
 */

import java.text.ParseException;
import java.util.*;

public class Vehiculo {
    private String nombre;
    private String soy;
    private String valor;
    private Hashtable<String, String> tabla
            = new Hashtable<String, String>();

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }



    public String getSoy() {
        return soy;
    }

    public void setSoy(String soy) {
        this.soy = soy;
    }


    public Vehiculo(String Nombre){
        this.nombre=Nombre;
    }
    public void setValue(String key, String value){
        this.tabla.put(key, value);
    }

    public boolean containsKey(String key){
        return this.tabla.containsKey(key);
    }
    public String getValue(String key){
        return this.tabla.get(key);
    }
    public String getNombre(){
        return nombre;
    }
    public HashMap isPosible(HashMap properties){
        boolean result=true;
        HashMap map= new HashMap();
        List<String> found =new ArrayList<String>();
        List<String> notFound =new ArrayList<String>();

        for (Object name: properties.keySet()) {

            String key = name.toString();
            if (this.tabla.containsKey(key)) {
                String value = properties.get(name).toString();

                boolean rest = evaluar(key, value);
                if(rest){
                    System.out.println("posibility found");
                }
                else{
                    result=false;
                }
                //funcion evaluar
                found.add(key);


            }else{
                notFound.add(key);

            }
        }
        if (result==true){
            System.out.println("returning:"+this.nombre);
            map.put("found",found);
            map.put("notFound",notFound);
        }
        return map;

    }
    boolean evaluar(String key,String inputVal){
        boolean val=false;
        String sentence =this.tabla.get(key);
        String operador;
        operador = sentence.substring(0,1);
        sentence =sentence.substring(1);
        System.out.println(this.nombre + "comparando: " + sentence + ":" + inputVal);
        if(operador.equals("="))
            if(inputVal.trim().toUpperCase().equals(sentence.trim().toUpperCase()))
                val= true;
        if(operador.equals("<"))
            try{
                if(Integer.parseInt(inputVal)<Integer.parseInt(sentence))
                    val =true;
            }catch(NumberFormatException nfe){}
        if(operador.equals(">"))
            try{
                if(Integer.parseInt(inputVal)<Integer.parseInt(sentence))
                    val =true;
            }catch(NumberFormatException nfe){}

        return val;
    }
}
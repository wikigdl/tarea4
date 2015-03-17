/**
 * Created by sheko on 16/03/15.
 */

package practica3;

        import java.awt.GridBagConstraints;
        import java.awt.GridBagLayout;
        import java.io.BufferedReader;
        import java.io.FileNotFoundException;
        import java.io.FileReader;
        import java.io.IOException;
        import java.util.ArrayList;
        import java.util.HashMap;
        import java.util.Hashtable;
        import java.util.List;
        import javax.swing.JLabel;
        import javax.swing.JOptionPane;
        import javax.swing.JPanel;
        import javax.swing.JTextField;

public class main {
    static List<String> llavesPrimarias = new ArrayList();
    static List<Vehiculo> objetos = new ArrayList();
    public static void main(String[] args) {
        List<Vehiculo> posibles= new ArrayList();
        List<HashMap>pMap=new ArrayList();
        try {
            separarAtributos("entrada");
        } catch (IOException e) {
            e.printStackTrace();
        }
/*
        String[] llavesPrimarias = { "vihicleType", "Numberweels", "motor" };
        String[] valores = { "1", "2", "3" };
        String[] vehiculos = { "moto", "lamelarene", "lamelacheco" };
        for (int i = 0; i < vehiculos.length; i++) {
            Vehiculo vehiculo = new Vehiculo(vehiculos[i]);
            vehiculo.setValue(llavesPrimarias[i], valores[i]);
            objetos.add(vehiculo);
        }*/
        JPanel myPanel = new JPanel();
        myPanel.setLayout(new GridBagLayout());
        JTextField field[]=new JTextField[llavesPrimarias.size()];
        for (int j = 0; j < llavesPrimarias.size(); j++) {
            field[j] = new JTextField(20);
            JLabel label = new JLabel(llavesPrimarias.get(j));
            GridBagConstraints constraints = new GridBagConstraints();
            constraints.gridx = 0; // El �rea de texto empieza en la columna cero.
            constraints.gridy = j; // El �rea de texto empieza en la fila cero
            constraints.gridwidth = 1; // El �rea de texto ocupa dos columnas.
            constraints.gridheight = 1;
            myPanel.add(label, constraints);
            constraints.gridx = 1;
            myPanel.add(field[j], constraints);

        }

        int result = JOptionPane.showConfirmDialog(null, myPanel,
                "Please Enter X and Y Values", JOptionPane.OK_CANCEL_OPTION);
        HashMap pBusqueda = new HashMap();
        if (result == JOptionPane.OK_OPTION) {
            //System.out.println("x value: " + xField.getText());
            //System.out.println("y value: " + yField.getText());
            for (int j =0;j<llavesPrimarias.size(); j++){
                if (!field[j].getText().isEmpty()){
                    String temp = field[j].getText();
                    pBusqueda.put(llavesPrimarias.get(j),temp);
                }
            }

        }
        for (Object name: pBusqueda.keySet()){

            String key =name.toString();
            String value = pBusqueda.get(name).toString();
            System.out.println(key + " " + value);


        }
        System.out.println(objetos.size());
        //buscamos propiedades dentro de todos los objetos creados
        for (Vehiculo actual : objetos) {
            HashMap rBuesqueda = actual.isPosible(pBusqueda);
            System.out.println(rBuesqueda.size());
                if (!rBuesqueda.isEmpty()){
                    posibles.add(actual);
                    pMap.add(rBuesqueda);
                }

        }
        if (posibles.size()>0){
            if(posibles.size()==1){
                Vehiculo vFinal = posibles.get(0);
                JOptionPane.showMessageDialog(null,"El programa tiene una respuesta a esas caracteristicas, es un:"+ vFinal.getNombre());
            } else{
                JOptionPane.showMessageDialog(null,"El programa no tiene una respuesta a esas caracteristicas muchas posibilidades encontradas");
                /*

                funcion para reducir el numero de posibilidades buscando un padre del cual heredar posibilidades

                for(int j=0;j<posibles.size();j++){
                    Vehiculo actual = posibles.get(j);
                    HashMap aProp= pMap.get(j);
                    List <String>notFound = (List) aProp.get("notFound");
                    for(int i=0;j<posibles.size();i++){
                        if(i!=j){
                            Vehiculo father = posibles.get(i);
                            for(String prop:notFound){
                                if(father.getSoy().equals(prop))
                                    if(father.getValor().equals())
                            }
                        }

                    }


                }*/
            }

        }else
            JOptionPane.showMessageDialog(null,"El programa no tiene una respuesta a esas caracteristicas");



    }
    private static void separarAtributos(String file) throws IOException {
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            try {
                while (true) {
                    String line = br.readLine();
                    if (line == null) {
                        break;
                    }
                    while (line.equals("")) {
                        line = br.readLine();
                    }
                    String name = line.substring(0, line.indexOf(":"));
                    line = line.replaceFirst(name + ":", "");
                    line = line.replaceAll("IF", "");
                    line = line.replaceAll("AND", "");
                    line = line.replaceAll("THEN", "");
                    line = line.replaceAll("< ", "= <");
                    String[] propiedades = line.split("[= ]+");
                    Vehiculo vehiculo = new Vehiculo(name);
                    for (int i = 1; i < propiedades.length; i = i + 2) {
                        if (!llavesPrimarias.contains(propiedades[i])) {
                            llavesPrimarias.add(propiedades[i]);
                        }
                        if(propiedades[i + 1].contains("<")){
                            vehiculo.setValue(propiedades[i], propiedades[i + 1]);
                        }else{
                            vehiculo.setValue(propiedades[i], "="+propiedades[i + 1]);
                        }
                    }
                    objetos.add(vehiculo);
                }

            } finally {
                br.close();
            }
        } catch (FileNotFoundException e) {
            System.out.println("Archivo no encontrado");
        }

    }


}

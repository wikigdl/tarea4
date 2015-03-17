/**
 * Created by sheko on 16/03/15.
 */

package practica3;

        import java.awt.GridBagConstraints;
        import java.awt.GridBagLayout;
        import java.util.ArrayList;
        import java.util.HashMap;
        import java.util.Hashtable;
        import java.util.List;
        import javax.swing.JLabel;
        import javax.swing.JOptionPane;
        import javax.swing.JPanel;
        import javax.swing.JTextField;

public class main {
    public static void main(String[] args) {
        Hashtable<String, String> tabla = new Hashtable<String, String>();
        List<Vehiculo> objetos = new ArrayList();
        List<Vehiculo> posibles= new ArrayList();
        List<HashMap>pMap=new ArrayList();
        String[] llavesPrimarias = { "vihicleType", "Numberweels", "motor" };
        String[] valores = { "1", "2", "3" };
        String[] vehiculos = { "moto", "lamelarene", "lamelacheco" };
        for (int i = 0; i < vehiculos.length; i++) {
            Vehiculo vehiculo = new Vehiculo(vehiculos[i]);
            vehiculo.setValue(llavesPrimarias[i], valores[i]);
            objetos.add(vehiculo);
        }
        JPanel myPanel = new JPanel();
        myPanel.setLayout(new GridBagLayout());
        JTextField field[]=new JTextField[llavesPrimarias.length];
        for (int j = 0; j < llavesPrimarias.length; j++) {
            field[j] = new JTextField(20);
            JLabel label = new JLabel(llavesPrimarias[j]);
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
            for (int j =0;j<llavesPrimarias.length; j++){
                if (!field[j].getText().isEmpty()){
                    String temp = field[j].getText();
                    pBusqueda.put(llavesPrimarias[j],temp);
                }
            }

        }
        for (Object name: pBusqueda.keySet()){

            String key =name.toString();
            String value = pBusqueda.get(name).toString();
            System.out.println(key + " " + value);


        }
        //buscamos propiedades dentro de todos los objetos creados
        for (Vehiculo actual : objetos) {
            HashMap rBuesqueda = actual.isPosible(pBusqueda);
                if (!rBuesqueda.isEmpty()){
                    posibles.add(actual);
                    pMap.add(rBuesqueda);
                }

        }
        if (posibles.size()<0){
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

}

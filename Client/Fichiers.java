package file;
import relation.Relation;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.util.Vector;
import oracle.net.aso.i;

public class Fichiers extends File {
    //creer un fichier s'il n'existe pas
    public Fichiers(String nomFichier)throws Exception{
            super(nomFichier);
             if (! this.exists()) {
            this.createNewFile();
        }    
    }

    public void ecriture(Relation relation)throws Exception{
        FileWriter cahier = new FileWriter(this);
        BufferedWriter stylo=new BufferedWriter(cahier);
        stylo.write(relation.getNom());

        Vector<String> attribut=relation.getAttribut();
        //descend a la ligne
        stylo.newLine();

        //attributs
        for (int i = 0; i < attribut.size(); i++) {
            stylo.write(attribut.get(i));
            stylo.write(";;");

        }

        //les valeurs
        stylo.newLine();
        Object[][] valeur=relation.getVal();
        for (int i = 0; i < valeur.length; i++) {
            for (int j = 0; j < valeur[0].length; j++) {
                stylo.write(String.valueOf(valeur));
                stylo.write("//");

            }
        }
        stylo.write(";;");

        //fermeture
        stylo.close();
        cahier.close();
    }

    //lecture
    public Vector<String> lecture()throws Exception{
        String s="a";

        BufferedReader lire=new BufferedReader(new InputStreamReader(new FileInputStream(this)));

        Vector<String> vector=new Vector<>();
        while (s!=null) {
            s=lire.readLine();
            if (s!=null) {
              vector.add(s);  
            }
        }
        return vector;
    }

    
    //recuperer les attributs dans le fichier
    public String[] getattributs()throws Exception{
        Vector<String> vect=this.lecture();
        String[] attribut=vect.get(1).split(";;");
        return attribut;
    }

    //recuperer les valeurs dans le fichier
    public String[][] getValeurs()throws Exception{
        Vector<String> vect=lecture();
        String[] tous=vect.get(2).split(";;");
        String[] valeur=tous[0].split("//");
        String[][] tableau=new String[tous.length][valeur.length];
        for (int i = 0; i < tableau.length; i++) {
            valeur=tous[i].split(";;");
            for (int j = 0; j < tableau.length; j++) {
                tableau[i][j]=valeur[j];
            }
        }
        return tableau;

    }

    
}



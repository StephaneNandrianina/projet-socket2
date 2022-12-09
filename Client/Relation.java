package relation;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;

import fonction.*;
public class Relation implements Serializable{

    String nom;
    String[] colonne;
    Object[][] tabl;
    //constructeur
    public Relation() {
    }
    public Relation(String nom, String[] colonne, Object[][] tabl) {
        this.nom = nom;
        this.colonne = colonne;
        this.tabl = tabl;
    }
    
    //constructeur

    //get et set
    public String getNom() {
        return nom;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }
    public String[] getColonne() {
        return colonne;
    }
    public void setColonne(String[] colonne) {
        this.colonne = colonne;
    }
    public Object[][] getTabl() {
        return tabl;
    }
    public void setTabl(Object[][] tabl) {
        this.tabl = tabl;
    }
    
    //get et set
    public void ecrire(){
        File fichier= new File(this.nom+".txt") ;
        Fonction function = new Fonction();
        if(fichier.exists()==false){
            try(BufferedWriter stylo=new BufferedWriter(new FileWriter(fichier,true))) 
            {
                stylo.write(nom);
                stylo.newLine() ;
                stylo.write(function.somme_string(this.colonne));
                stylo.newLine();
                stylo.write(function.table_to_string(this.tabl));
            } catch(IOException huhu){
                huhu.printStackTrace();
            }
        }
    }
    
}
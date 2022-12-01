package main;
import file.*;

import java.io.FileWriter;
import java.util.Scanner;
import java.util.Vector;
import relation.Fonction;
import relation.Relation;

public class Main {
    public static void main(String[] args) throws Exception{
        //tableau1
        String nom1="relation1";

        Vector<String> colonne1=new Vector<String>();
        colonne1.add("produits");
         colonne1.add("prix");

        Object[][] objet1=new Object[3][colonne1.size()];
        objet1[0][0]="porc";
        objet1[0][1]=30.2 ;

         objet1[1][0]="brochette";
         objet1[1][1]=23.1;

        objet1[2][0]="banane";
        objet1[2][1]=25.6;

        Relation r1=new Relation(nom1,colonne1,objet1);
                
        Fichiers f1=new Fichiers(nom1);
         f1.ecriture(r1);

         //tableau2
         String nom2="relation2";
         
         Vector<String> colonne2=new Vector<String>();
        colonne2.add("travail");
        colonne2.add("personne");

        Object[][] objet2=new Object[2][colonne2.size()];
        objet2[0][0]="ingenieur";
        objet2[0][1]="secretaire";

        Relation r2 = new Relation(nom2, colonne2, objet2);
        Fichiers f2 = new Fichiers(nom2);
        f2.ecriture(r2);

        //tableau3
        String nom3="relation3";

        Vector<String> colonne3=new Vector<>();
        colonne3.add("travail");
        colonne3.add("personne");

        Object[][] objet3=new Object[5][colonne3.size()];
        objet3[0][0]="Mickael";
        objet3[0][1]="Ingenieur";

        objet3[1][0]="Felicia";
        objet3[1][1]="secretaire";

        objet3[2][0]="Ravaka";
        objet3[2][1]="secretaire";

        objet3[3][0]="Fitia";
        objet3[3][1]="Ingenieur";

        objet3[4][0]="Jean ";
        objet3[4][1]="Ingenieur";

        Relation r3=new Relation(nom3, colonne3, objet3);
        Fichiers f3 = new Fichiers(nom3);
        f2.ecriture(r3);
        
       //tableau fichier 
        Vector<Fichiers> tableauFichier=new Vector<Fichiers>();
        tableauFichier.add(f1);
        tableauFichier.add(f2);
        tableauFichier.add(f3);

        Fonction fonction = new Fonction(tableauFichier);
        Scanner sc = new Scanner(System.in); /* recuperation des donnees */
        String s= sc.nextLine();
        fonction.Requette(s);


    }
}
    
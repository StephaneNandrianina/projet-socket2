package relation;
import relation.Relation;
import java.util.Vector;

import file.Fichiers;

import java.lang.reflect.*;



public class Fonction {
    Vector<Fichiers> arrayFile;

    public Fonction(Vector<Fichiers> arrayFile) {
        this.arrayFile = arrayFile;
    }

    //get set
    public Vector<Fichiers> getarrayFile() {
        return arrayFile;
    }
    public void setarrayFile(Vector<Fichiers> arrayFile) {
        this.arrayFile = arrayFile;
    }
    //get set

    //union 
        //avoir le fichier d'une realtion qu'on a besoin
        Fichiers vraiFichier(String nom)throws Exception{
             Fichiers fichier=this.arrayFile.get(0);
             for (int i = 0; i < arrayFile.size(); i++) {
                if (this.arrayFile.get(i).lecture().get(0).equals(nom)) {
                    fichier=this.arrayFile.get(i);
                }
             }
             return fichier;   
        }
        //avoir le fichier d'une realtion qu'on a besoin    
        
        public Vector<String> getVectorAttribut(Fichiers f1)throws Exception{
            Vector<String> vect = new Vector<>();
            String[] tabf= f1.getattributs();
            for (int i = 0; i < tabf.length; i++) {
                vect.add(tabf[i]);
            }
            return vect;
        }

        public Relation Union(String r1,String r2)throws Exception{
                Fichiers fichier1=this.vraiFichier(r1);
                Fichiers fichier2=this.vraiFichier(r2);
                String union=r1+"U"+r2 ;

                Vector<String> vect=this.getVectorAttribut(fichier1);

                String[][] valf1=fichier1.getValeurs();
                String[][] valf2=fichier2.getValeurs();
                Object[][] obj =new Object[(valf1.length+valf2.length)][vect.size()] ;
                String[][] tabl=valf1;
                
                int k=0;
                for (int i = 0; i < valf1.length; i++) {
                    for (int j = 0; j < valf1[0].length; j++) {
                        obj[i][j] = valf1[i][j];
                    }
                    k=i;
                }

                for (int i = 0; i < valf2.length; i++) {
                    k++;
                    for (int j = 0; j < valf2[0].length; j++) {
                        obj[k][i]=valf2[i][j];
                    }
                }
                Relation relation =new Relation(union, vect, valf2);
                return relation;
         }
    //union 
    

    //projection
         public int getIdAttribut(String colonne,Vector<String> vect) {
            int indice=0;
            for (int i = 0; i < vect.size(); i++) {
                if (vect.get(i).equals(colonne)) {
                    indice = i;
                }
            }
            return indice;
         }

        public Relation projection(String colonne,String table)throws Exception{
            Fichiers f = this.vraiFichier(table);
            Vector<String> vect=this.getVectorAttribut(f);

            if (colonne == "*") {
                colonne=" ";
                for (int i = 0; i < vect.size(); i++) {
                    colonne=colonne+vect.get(i)+",";
                }
            }

            String[] text =colonne.split(",");
            String nom ="project" +table;

            Vector<String>attribut = new Vector<String>();
            int indice=0;
            for (int i = 0; i < text.length; i++) {
                indice = this.getIdAttribut(text[i], vect);
                attribut.add(vect.get(indice));
            } 

            String[][] valeur=f.getValeurs();
            Object[][] tableau = new Object[valeur.length][text.length];

            indice=0;
            for (int i = 0; i < text.length; i++) {
                indice=getIdAttribut(text[i], vect);
                for (int j = 0; j < valeur.length; j++) {
                    tableau[i][j]=valeur[j][indice];
                }
            }
            Relation project =new Relation(nom, attribut, tableau);
            return project;
        } 
//projection

    //intersection
            //comparaison de 2 tables
                public int compareTableObj(String[] tabl1, String[] tabl2){
                    int compt=0;
                    int val=0;
                    for (int i = 0; i < tabl1.length; i++) {
                        for (int j = 0; j < tabl2.length; j++) {
                            if (tabl1[i]==tabl2[j]) {
                                compt++ ;
                            }
                        }
                    }
                    if (compt==tabl1.length) {
                        val=1;
                    }
                    return val;
                }
            //comparaison de 2 tables

                //vector à tableau d' objet
                    public Object[][] tablObjet(Vector<String[]> table , int taille){
                        Object[][] tablObjects=new Object[table.size()][taille];
                        for (int i = 0; i < table.size(); i++) {
                            for (int j = 0; j < table.get(0).length; j++) {
                                tablObjects[i][j] = table.get(i)[j];
                            }
                        }
                        return tablObjects;
                    }
                //vector à tableau d' objet
                    
                public Relation intersection(String r1,String r2)throws Exception{
                    Fichiers f1=this.vraiFichier(r1);
                    Fichiers f2=this.vraiFichier(r2);

                    String nom=r1+"intersection"+r2;
                    Vector<String> vecto=this.getVectorAttribut(f1);
                    String[][] valeurf1=f1.getValeurs();
                    String[][] valeurf2=f2.getValeurs();

                    int k=0;
                    Vector<String[]> tableau=new Vector<String[]>();
                    for (int i = 0; i < valeurf1.length; i++) {
                        for (int j = 0; j < valeurf2.length; j++) {
                            if (compareTableObj(valeurf1[i], valeurf2[j]) == 1) {
                                tableau.add(valeurf1[i]);
                            }
                        }
                    }
                    
                    Object[][] tableObjet=this.tablObjet(tableau, vecto.size());
                    Relation relation =new Relation(nom, vecto, tableObjet);
                    return relation;
                }
//intersection

    
    //difference
      //comparaison des objets
       public int compareObjet(Object[] objet1,Object[]objet2){
            int vale=0;
            for (int i = 0; i < objet1.length; i++) {
                if ((String.valueOf(objet1[i])) == (String.valueOf(objet2[i]))) {
                    vale=1;
                }
            }
            return vale;
       }
     //comparaison des objets
     
     public Relation difference(String r1 , String r2)throws Exception{
            Fichiers f1=this.vraiFichier(r1);
            Fichiers f2=this.vraiFichier(r2);
            String nom = r1+"difference"+r2 ;

            Vector<String> vector = getVectorAttribut(f1);
            String[][] valeur_f1 = f1.getValeurs();
            String[][] valeur_f2 = f2.getValeurs();

            Vector<Object[]> table = new Vector<Object[]>() ;
            Relation union = Union(r1, r2);
            Relation intersct = intersection(r1, r2);

            Object[][] tableUnion = union.getVal();
            Object[][] tableIntersct = intersct.getVal();

            int compt=0;
            for (int i = 0; i < tableUnion.length; i++) {
                for (int j = 0; j < tableIntersct.length; j++) {
                    if (this.compareObjet(tableUnion[i], tableIntersct[j])== 1) {
                        compt++ ;
                    }
                } 
                if (compt== tableIntersct.length) {
                    table.add(tableUnion[i]);
                }
                
                compt=0 ;
            }
                Object[][] tab = new Object[table.size()][vector.size()];
                for (int j = 0; j < tab.length; j++) {
                    for (int k = 0; k < table.get(0).length; k++) {
                        tab[j][k] = table.get(j)[k]; 
            }

            }
            Relation rel =new Relation(nom, vector, tab);
            return rel ;
     }
   //difference  

     //invention des tirets verticales  pour remplacer le tableau
     public void CreateTable(Relation table){
        Object[][] valeur = table.getVal();
        Vector<String> attribut= table.getAttribut();
        for (int i = 0; i < attribut.size(); i++) {
            System.out.println(attribut.get(i)+" |");
        }
        System.out.println("\n"); 
        for (int i = 0; i < valeur.length; i++) {
            for (int j = 0; j < valeur[0].length; j++) {
                System.out.println(valeur[i][j]+" |");
            }
            System.out.println("\n");
        }
     }

     //les requettes
     public void Requette(String requette)throws Exception{
        String[] texte = requette.split(" ");
        
        if (texte[0].equals("Union")) {
            Relation rel = Union(texte[1], texte[3]) ;
            CreateTable(rel);
        }
        
        if (texte[0].equals("projection")) {
            Relation rel = projection(texte[1], texte[3]);
            CreateTable(rel);
        }

        if (texte[0].equals("intersection")) {
            Relation rel = intersection(texte[1], texte[3]);
            CreateTable(rel);
        }

        if (texte[0].equals("difference")) {
            Relation rel = difference(texte[1], texte[3]);
            CreateTable(rel);
        }
     }
   }


    
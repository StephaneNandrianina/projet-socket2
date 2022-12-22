package fonction;
import relation.Relation;
import java.io.*;
public class Fonction {
    public String somme_string(Object[] tableau){
        String somme="";
        for(int i=0 ; i<tableau.length ; i++){
            if(i<tableau.length-1)
            {
                somme=somme+tableau[i]+",";
            }else{
                somme=somme+tableau[i];   
            }
        }
        return somme;
    }   
    public String table_to_string(Object[][] tableau){
        String somme="";
        for(int i=0; i<tableau.length ; i++){
            if(i<tableau.length-1)
            {
                somme= somme+somme_string(tableau[i])+";";
            } else{
                 somme= somme+somme_string(tableau[i]);
            }
        }
        return somme;
    }
    public Object[][] string_to_table(String ray){
        String[] line= ray.split(";");
        Object[][] resultat= new Object[line.length][];
        for(int i=0 ; i<line.length ; i++){
            resultat[i]= line[i].split(",");
        }
        return resultat;
    }

    public Object[] lire(String nom) throws Exception
    {
        File fichier= new File(nom+".txt");
        Object[] tableau= new Object[3];
        if(fichier.exists()){
            BufferedReader br = br = new BufferedReader(new FileReader(fichier));
            String ligne;
            int i = 0 ; 
            while ((ligne = br.readLine()) != null)
            {
                tableau[i]=ligne;
                i++;
            }
            br.close();
        } else{
            throw new Exception("le table "+nom+" n'existe pas ");
        }
        return tableau;
        
    }

     public String[] somme_tableau_string(Relation table1 , Relation table2) throws Exception{
        String[] header_table1 =table1.getColonne();
        String[] header_table2 =table2.getColonne();
        String[] somme= new String[header_table1.length + header_table2.length]  ;
        int j=0; 
        for(int i=0; i<header_table1.length ; i++){
            somme[j]=header_table1[i];
            j++;
        }
        for(int i=0; i<header_table2.length ; i++){
            somme[j]=header_table2[i];
            j++;
        }

        return somme;
    }

///-------------------------------------------------------------------------------------------------------------

    public boolean test_existence_colonne(Relation table1 , String nom_colonne) throws Exception{
        String[] colonne = table1.getColonne();
        boolean result=false;
        int indice=0;
        for(int i=0 ; i<colonne.length ; i++){
            if(colonne[i].equals(nom_colonne)){
                indice=i;
                result=true;
            }
        }
        return result;

    }

    public boolean verification_colonne(Relation table1 , Relation table2 )  throws Exception{      // on verifie si les 2 relations ont un colonne commun 
        boolean resultat=false;
       for(int i=0; i<table1.getColonne().length ; i++){
            if(test_existence_colonne(table2  , table1.getColonne()[i])){
                return true;
            }
       }
        return resultat;
    }
    public String colonne_commun(Relation table1 , Relation table2) throws Exception {
        String resultat = new String();
        if( verification_colonne(table1 ,table2 )){
            for(int i=0; i<table1.getColonne().length ; i++){
                if(test_existence_colonne(table2  , table1.getColonne()[i])){
                    resultat=table1.getColonne()[i];
                    break;
                }
            } 
        } else{ 
            throw new Exception("il n'y a pas de colonne commun entre les deux relations");
       }
        return resultat;

    }


    public boolean test_existence(Object[] ligne , Relation table2) throws Exception{
        int header_table2=table2.getColonne().length;               // colonne de la relation table2
        Object[][] valeur_table2 = table2.getTabl();             // tableau d'objet de table2
        int compte=0;
        if(ligne.length == header_table2 ){
            for( int j=0; j<valeur_table2.length ; j++){
                for(int i=0 ; i<header_table2 ; i++){
                    if(ligne[i].equals(valeur_table2[j][i])){
                        compte++;
                    }
                } 
                if(compte == header_table2){
                    return true;
                }
                compte=0;
            }
        } else{
            throw new  Exception(" les deux tables n'ont pas le mem nombre de colonne");
        }
        return false;
    }
    
    public int[] indice_des_colonnes(String[] les_colonnes , Relation table1) throws Exception{
        int[] resultat = new int[les_colonnes.length]; 
        for(int i=0; i<resultat.length ; i++)
        {
            resultat[i]=numero_colonne(table1 , les_colonnes[i]);
        }
        return resultat;
    }

     


//----------------utiliser dans union( String a , String b)
    public Object[][] tableau_union(Relation table1 , Relation table2)throws Exception{  
        Object[][] resultat;    
        int ligne_table1=table1.getTabl().length;
        int ligne_table2=table1.getTabl().length;
        int colonne_table1=table1.getColonne().length;
        int ligne=ligne_table1+ligne_table2;
        if(table1.getColonne().length== table2.getColonne().length)
        {
             resultat= new Object[ligne ][ colonne_table1];
             int j=0;
            for(int i=0 ; i<ligne_table1 ; i++){
                resultat[j]= table1.getTabl()[i];
                j++;
            }
            for(int i=0 ; i<ligne_table2 ; i++){
                resultat[j]= table2.getTabl()[i];
                j++;
            }

        } else{
            throw new Exception("les deux tables n'ont pas le meme nombre de colonne");
        }
        return resultat;
    }
    public int nombre_resultat_diff(Relation table1, Relation table2) throws Exception
    {
        int compte=0;
        Object[][] valeur_table1= table1.getTabl();
        for(int i=0; i<valeur_table1.length ; i++ )
        {
            if(test_existence(valeur_table1[i], table2)==false){
                System.out.println("sdsdsdsd");

                compte++;
            }
        }
        return compte;
    }


    public int nombre_resultat_intersec(Relation table1, Relation table2) throws Exception
    {
        int compte=0;
        Object[][] valeur_table1= table1.getTabl();
        for(int i=0; i<valeur_table1.length ; i++ )
        {
            if(test_existence(valeur_table1[i], table2)==true){
                compte++;
            }
        }
        return compte;
    }

     public int numero_colonne(Relation table1,  String nom_colonne) throws Exception{
        String[] colonne = table1.getColonne();
        int indice=0;
         if(test_existence_colonne(table1 , nom_colonne)==false){
            throw new Exception("le colonne '"+nom_colonne+"' n'existe pas dans '"+table1.getNom()+"'");
        }
        for(int i=0 ; i<colonne.length ; i++){
            if(colonne[i].equals(nom_colonne)){
                indice=i;
            }
        }
       
        return indice;
    }


    public int nombre_resultat_condition(Relation table1 , String condition) throws Exception{
        int compte =0; 
        Object[][] valeur_table1= table1.getTabl();
        String[] separer= condition.split("\\=");
        int indice_colonne= numero_colonne(table1 , separer[0]);
        for(int i=0; i<valeur_table1.length ; i++){
            if( valeur_table1[i][indice_colonne].equals(separer[1])  ){
                System.out.println(indice_colonne);
                compte++;
            }
        }
        return compte;
    }



    public Object[][] tableau_difference(Relation table1 , Relation table2)throws Exception{
        int compte=nombre_resultat_diff(table1, table2);
        Object[][] resultat= new Object[compte][table1.getColonne().length];
        Object[][] valeur_table1= table1.getTabl();
        int j=0;
        for(int i=0; i<valeur_table1.length ; i++ )
        {
            if(test_existence(valeur_table1[i], table2)==false){
                System.out.println("1");
                resultat[j] = valeur_table1[i];
                j++;
            }
        }
        return resultat;
    }
    public Object[][] tableau_intersection(Relation table1 , Relation table2) throws Exception{
        int compte=nombre_resultat_intersec(table1, table2);
        Object[][] resultat= new Object[compte][table1.getColonne().length];
        Object[][] valeur_table1= table1.getTabl();
        int j=0;
        for(int i=0; i<compte ; i++ )
        {
            if(test_existence(valeur_table1[i], table2)){
                resultat[j] = valeur_table1[i];
                j++;
            }
        }
        return resultat;
    }
    public Object[][] tableau_condition(Relation table1 , String condition) throws Exception{
        int ligne=nombre_resultat_condition(table1 , condition);
        String[] header=table1.getColonne(); 
        Object[][] resultat= new Object[ligne][header.length];
        Object[][] valeur_table1= table1.getTabl();
        String[] separer= condition.split("\\=");
        int indice_colonne= numero_colonne(table1 , separer[0]);
        int j=0;
        for(int i=0; i<valeur_table1.length ; i++){
            if( valeur_table1[i][indice_colonne].equals(String.valueOf(separer[1])) ){
                resultat[j] = valeur_table1[i];
                j++;
            }
        }
        return resultat;
    }
    public Object[][] tableau_pc( Relation table1 , Relation table2) throws Exception{   
        Object[][] valeur_table1 = table1.getTabl();
        Object[][] valeur_table2 = table2.getTabl();     
        int ligne_table1=table1.getTabl().length;
        int ligne_table2=table2.getTabl().length;
        int colonne = table1.getColonne().length + table2.getColonne().length;
        int ligne = ligne_table1 * ligne_table2;
        Object[][] resultat= new Object[ligne][colonne];
        int j=0; 
        for(int i=0; i< valeur_table1.length ; i++){
            for(int l=0 ; l<valeur_table2.length ; l++)
            {
                int m=0;
                for( int k=0; k<table1.getColonne().length ; k++ ){
                    resultat[j][m]=valeur_table1[i][k];
                    m++;
                }
                for( int k=0; k<table2.getColonne().length ; k++ ){
                    resultat[j][m]=valeur_table2[l][k];
                    m++;
                }
                j++;
            }
        }
        return resultat;
    }
    public int nombre_resultat_jointure(String a , String b , String condition) throws Exception{
        int compte=0;
        Relation table1 = selection(a);
        Relation table2 = selection(b);
        Relation produit_cart = produit_cartesienne(table1,table2);
        int numero1= numero_colonne(produit_cart,condition.split("\\=")[0]);
        int numero2= numero_colonne(produit_cart,condition.split("\\=")[1]); 
        // System.out.println("           "+numero2+"             "+numero1);
        Object[][] tab=produit_cart.getTabl();
        for(int i=0; i<tab.length ; i++){
            if(tab[i][numero1].equals(tab[i][numero2])){
                compte++;
            }
        }
        // System.out.println("========"+compte);
        return compte;
    }

    public Object[][] tableau_jointure(String a , String b , String condition) throws Exception{
        Relation table1 = selection(a);
        Relation table2 = selection(b);
        Relation produit_cart = produit_cartesienne(table1,table2);
        int numero1= numero_colonne(produit_cart,condition.split("\\=")[0]);
        int numero2= numero_colonne(produit_cart,condition.split("\\=")[1]);   
        int ligne=nombre_resultat_jointure(a,b,condition);
        Object[][] resultat = new Object[ligne][produit_cart.getColonne().length];
        Object[][] tab=produit_cart.getTabl();
        int j=0; 
        for(int i=0; i<tab.length ; i++){
            if(tab[i][numero1].equals(tab[i][numero2])){
                resultat[j]=tab[i];
                j++;
            }
        }
        return resultat;
    }

    


// ------------------------------------------------------------------------------------------------------------------

    public Relation selection(String nom )throws Exception{
        Object[] fichier= lire(nom);

        String table= nom;
        String[] colonne= String.valueOf(fichier[1]).split(",");
        Object[][] valeur= string_to_table(String.valueOf(fichier[2]));
        Relation relation = new Relation(table , colonne , valeur);
        return relation;
    }


    public Relation union(Relation table1 , Relation table2) throws Exception{
        Relation resultat=new Relation("union", table1.getColonne() , tableau_union(table1, table2));
        return resultat;
    }

    public Relation difference(Relation table1 , Relation table2  ) throws Exception{
        Relation resultat=new Relation("difference", table1.getColonne() , tableau_difference(table1, table2));
        return resultat;
    }

    public Relation intersection(Relation table1 , Relation table2  ) throws Exception{
        Relation resultat=new Relation("intersection", table1.getColonne() , tableau_intersection(table1, table2));
        return resultat;
    }

    public Relation condition(Relation table1, String condition ) throws Exception{
        Relation resultat=new Relation("intersection", table1.getColonne() , tableau_condition(table1, condition));
        return resultat;
    }

    public Relation projection(Relation table1 , String colonne) throws Exception
    {
        String[] header= colonne.split(",");
        Object[][] valeur_table1 = table1.getTabl();
        Object[][] resultat =new Object[valeur_table1.length][header.length];
        int[] indice = indice_des_colonnes(header , table1);
        int j=0; 
        for(int i=0; i<valeur_table1.length ;i++){
            for( j=0; j<indice.length ; j++){
                resultat[i][j]=valeur_table1[i][indice[j]];
            }
        }
        Relation result= new Relation("project",header , resultat );
        return result;
    }

    public Relation produit_cartesienne( Relation table1 , Relation table2) throws Exception{
        String[] header=somme_tableau_string(table1, table2);
        Relation resultat= new Relation("produit cartesienne" , header , tableau_pc(table1 , table2));
        return resultat;
    }

    public Relation jointure(Relation table1 , Relation table2 , String c) throws Exception{
        String[] header=somme_tableau_string(table1,table2 );
        Relation resultat= new Relation("produit cartesienne" , header , tableau_jointure(table1.getNom() , table2.getNom() ,c));
        return resultat;
    }
    
    public Relation division(Relation a , Relation b) throws Exception{   // table1: diviseur // table2: quotient  // table3: dividende
        Relation table1= selection(a.getNom());
        Relation table2= selection(b.getNom());   
        Relation table3= selection("table3");
        Relation projection_table1;            
        Relation projection_table2;
        if(test_existence_colonne( table1, table3.getColonne()[0])){
            projection_table1=projection(table1 , table3.getColonne()[0]);
            projection_table2=projection(table2 , table3.getColonne()[1]);
        }else{
            projection_table2=projection(table1 , table3.getColonne()[0]);
            projection_table1=projection(table2 , table3.getColonne()[1]);
        }
        Relation produit_cart= produit_cartesienne(projection_table1 , projection_table2); 
        Relation reste= difference(produit_cart , table3);
        // Affichage affichage= new Affichage(produit_cart);
        // Affichage affichage1= new Affichage(table3);
        // Affichage affichage2= new Affichage(reste);


//------------------ recupere la colonne commun entre les deux relations  reste et quotient
        String colonne_comm1= colonne_commun(reste , table2);
        Relation projection_reste= projection(reste , colonne_comm1);
        Relation projection_quotient= projection(table2 , colonne_comm1);
        Relation resultat= difference(projection_quotient , projection_reste);
        return resultat;
    }

    public String verification_table(String table) throws Exception {
        File fichier = new File(table+".txt");
        String resultat =  new String();
        if( fichier.exists()){
            resultat= table;
        }else{
            throw new Exception("ce table n'existe pas");
        }
        return resultat;
    }




    public Relation requette_select( String req )throws Exception{
        String[] resultat = req.split(" ");
        Relation Select=new Relation();
       
        if( resultat.length==4)
        {       if( resultat[1].equals("*") &&  resultat[2].equals("from")){
                    Select= selection(verification_table(resultat[3]));
                }
                else if( resultat[1].equals("*")==false &&  resultat[2].equals("from") ){
                    Relation table1= selection(verification_table(resultat[3]));
                    Select= projection(table1 , resultat[1]);
                }else{
                    throw new Exception("verifier bien votre requette!");
                }
        }
        if( resultat.length > 4 ){
            if(resultat[4].equals("where") ){
                Relation table1= selection(verification_table(resultat[3]));
                Select=condition( table1, resultat[5]);
            }else{
                throw new Exception("verifier bien votre requette!");
            }
        }
       return Select;

    }

    public Relation requette_union(String requeste) throws Exception{
        String[] separer = requeste.split(" ");
        Relation Union=new Relation();
        
        if(separer.length !=5){
            throw new Exception("la requette doit etre de la forme 'union de table1 a table2'");   
        }else{
            Relation table1= selection( verification_table(separer[2]));
            Relation table2= selection( verification_table(separer[4]));
            Union= union(table1, table2) ;
        }
        return Union;
    } 


    public Relation requette_jointure(String requeste) throws Exception{
        String[] separer = requeste.split(" ");
        Relation Jointure = new Relation();
        if(separer.length!=7)
        {
            throw new Exception("la requette doit etre de la forme 'jointure de table1 a table2 quand colonne1=colonne2'");   
        }
        else{
            Relation table1= selection( verification_table(separer[2]));
            Relation table2= selection( verification_table(separer[4]));
            Jointure=jointure(table1, table2 , separer[6]) ;
        }
        return Jointure;
    }


    public Relation requette_difference(String requeste) throws Exception {
     String[] separer = requeste.split(" ");
        Relation Differrence=new Relation();
        if(separer.length!=7)
        {
            throw new Exception("la requette doit etre de la forme 'diference entre table1 et table2 '");   
        }
        else{
            Relation table1= selection( verification_table(separer[2]));
            Relation table2= selection( verification_table(separer[4]));
            Differrence= difference(table1, table2 ) ;
        }   
        return Differrence;
    }

    public Relation create_table( String requette) throws Exception{
        String[] separer = requette.split(" ");
        Relation Table=new Relation();
        if(separer.length!=6)
        {
            throw new Exception("la requette doit etre de la forme 'creer table nomtable avec colonne: les_noms_colonnes'");   
        }
        else{
            File fichier= new File(separer[2]+".txt");
            if(fichier.exists()==false){
            try(BufferedWriter stylo=new BufferedWriter(new FileWriter(fichier,true))) 
            {
                stylo.write(separer[2]);
                stylo.newLine() ;
                stylo.write(separer[5]);
                Table=new Relation(separer[2],separer[5].split(",") , new Object[0][0]);
                stylo.newLine();
            } catch(IOException huhu){
                huhu.printStackTrace();
            }
            }else{
                throw new Exception("ce table existe deja");
            }
           
        }   
        return Table;
    }


    public Relation requette(String req) throws Exception{
        String[] resultat = req.split(" ");
        Relation fin=new Relation();
        String a=resultat[1];
        if(resultat[0].equals("select")){
            fin=requette_select(req);
        }
        else if( resultat[0].equals("union") )
        {
            fin=requette_union(req);
        }
        else if(resultat[0].equals("jointure")){
            fin=requette_jointure(req);
        }
        else if(resultat[0].equals("difference")){
            fin=requette_difference(req);
        } else if(resultat[0].equals("creer")){
            fin=create_table(req);
        }
        return fin;
    }

}
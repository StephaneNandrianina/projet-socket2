package client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

import relation.Relation;
 
public class Client { 
    //evoie et ecriture
    static final String serverName = "localhost";
    static final int serverPort = 9999;
     public static void main(String[] args) throws Exception, IOException {
        Socket socket = new Socket(serverName, serverPort);
        System.out.println("La connection est etablie");
        Scanner scann = new Scanner(System.in);

        System.out.println("-requette pour faire une SELECTION : select * from nom_table");
      
        System.out.println("-requette pour creer une TABLE est de la forme: creer table nom_table avec colonne: nom_colonne1;nom_coloneN");
      
        System.out.println("-requette pour faire une UNION est de la forme: union de nom_table1 a nom_table2 ");
      
        System.out.println("-requette pour faire une JOINTURE est de la forme: jointure de nom_table1 a nom_table2 quand nom_colonne1=nom_colonne2 ");

        System.out.println("-requette pour faire une DIFFERENCE est de la forme: diference entre nom_table1 et nom_table2 ");
        
        PrintWriter output = new PrintWriter(socket.getOutputStream());
        System.out.println("\n");
        System.out.println("Ecrivez une requette :");
        while (true) {
            
            String message = scann.nextLine();
             ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
             out.writeObject(message);
           

            ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
            Relation messParServer =(Relation) inputStream.readObject();  
            
            System.out.println("Nom de la table :"+messParServer.getNom());
            
             for (int i = 0; i < messParServer.getColonne().length; i++) {
               System.out.println("Nom de la colonne :"+messParServer.getColonne()[i]);  
             }
             for (int i = 0; i < messParServer.getTabl().length; i++) {
               for (int j = 0; j <messParServer.getTabl()[i].length; j++) {
                 System.out.println( messParServer.getTabl()[i][j]);    
               }
              
             } 

            

            // System.out.println("Ecrivez une requette");
            // String message;
            // message=scann.nextLine();
            // output.flush();
            // output.println(message);
            
            // ObjectInputStream imput = new ObjectInputStream(socket.getInputStream());
            // Relation relation = (Relation)imput.readObject(); 
            // System.out.println(relation.getColonne()[0]);

        }
      // socket.close();

    }
  }

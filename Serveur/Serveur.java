package serveur;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;

import javax.management.relation.RelationException;

import fonction.Fonction;
import relation.Relation;
 
public class Serveur {
    static final int port = 9999;
 
    public static void main(String[] args) throws Exception ,IOException{ 
        //reception et lecture 
      
        ServerSocket s = new ServerSocket(port);
        System.out.println("Attente du client");
        Socket socketClient = s.accept();
        System.out.println("Le client a reussi a se connecter ");
        

         BufferedReader input = new BufferedReader(new InputStreamReader(socketClient.getInputStream()));
         Fonction fonction = new Fonction();
            while (true) {
                ObjectInputStream inp = new ObjectInputStream(socketClient.getInputStream());
                String requette = (String) inp.readObject();
                Relation relation = fonction.requette(requette);
                
                ObjectOutputStream out = new ObjectOutputStream(socketClient.getOutputStream());
                out.writeObject(relation);

                // System.out.println(relation.getNom());
                // Relation messageVenantClient = (Relation) inp.readObject();
                // Relation nouveau_mess = messageVenantClient;
                // String requette = input.readLine();
                // Relation relation = fonction.requette(requette);
                // System.out.println(requette);
                // System.out.println(relation.getNom());
                // ObjectOutputStream out = new ObjectOutputStream(socketClient.getOutputStream());    
                // out.writeObject(relation);

        //         }       
        // //socketClient.close();
       // s.close();
    }
}

}

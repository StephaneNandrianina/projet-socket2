import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class SocketClient {
    public static void main(String[] args) throws UnknownHostException,IOException {
        //demande de service
    Socket client = new Socket("127.0.0.1" , 1026);
    System.out.println("Nom client");
    //recuperer le nom du client
    Scanner sc = new Scanner(System.in);
    String nomClient =sc.next();
    
    //message
    System.out.println("ecrire un message");
    Scanner scan = new Scanner(System.in);
    String message = scan.next();

    //envoie du donee au serveur
    DataOutputStream out = new DataOutputStream(client.getOutputStream());
    out.writeUTF(nomClient);
    //recuperer les donnees envoyer par le serveur
    DataInputStream in = new DataInputStream(client.getInputStream());
    String s1 = in.readUTF();
    System.out.println(s1); 

    //envoie du message
    DataOutputStream output = new DataOutputStream(client.getOutputStream());
    output.writeUTF(message);
    

     

    }
    


}
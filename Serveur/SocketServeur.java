import java.io.DataInputStream;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class SocketServeur {
    public static void main(String[] args) throws IOException{
        ServerSocket ss = new ServerSocket(1026);
        System.out.println("en attente de connexion du client");
        Socket s = ss.accept();
        System.out.println("connexion etablie");
        //on recupere la donnee envovoyee par le client
        DataInputStream in = new DataInputStream(s.getInputStream());
        String anaranaClient = in.readUTF();
        //on effectue un traitement
        String s1 ="Bienvennue ,"+anaranaClient+", vous etes bien connecte" ;
        //on envoie la donnee envoyer au client vers le client
        DataOutputStream out =new DataOutputStream(s.getOutputStream());
        out.writeUTF(s1);

        //reception du message du cote navigateur
        DataInputStream input = new DataInputStream(s.getInputStream());
        String afatra = input.readUTF();
        System.out.println(afatra);

    }
    

}
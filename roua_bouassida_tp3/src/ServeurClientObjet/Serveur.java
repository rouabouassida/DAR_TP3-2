package ServeurClientObjet;

//Importation de la classe Operation du package ServeurClientObjet
import ServeurClientObjet.Operation; 
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Serveur {
 public class Server extends Thread {
     public static void main(String[] args) {
//Crée une instance de la classe Serveur et appelle sa méthode start
         new Serveur().start(); 
     }
     @Override
     public void run() {
//Affiche un message indiquant que le serveur est actif
         System.out.println("Je suis un serveur"); 
         try {
//Crée un socket serveur écoutant sur le port 1235
             ServerSocket ss = new ServerSocket(1235); 
//Affiche un message indiquant que le serveur attend un client
             System.out.println("J'attends un client"); 
//Initialise un compteur pour le numéro du client
             int index = 1; 
//Boucle infinie pour accepter les connexions entrantes
             while (true) {
//Accepte la connexion d'un client
                 Socket clientSocket = ss.accept();
//Crée un thread pour gérer le client
                 new ClientProcess(clientSocket, index).start();
//Affiche un message indiquant que le client est connecté
                 System.out.println("Le client est connecté");
//Incrémente le numéro du client 
index++; 
             }
         } catch (IOException e) {
             throw new RuntimeException(e);
         }
     }
 }

public void start() {}
	// TODO Auto-generated method stub
	
}

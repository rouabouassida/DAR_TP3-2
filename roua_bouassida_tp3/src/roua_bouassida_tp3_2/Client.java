package roua_bouassida_tp3_2;

//Importation de la classe Operation du package ServeurClientObjet
import ServeurClientObjet.Operation;  
import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {
	public static void main(String[] args) {
		try {
			//Établit une connexion avec le serveur sur le port 1235
			Socket socket = new Socket("localhost", 1235);
			//Crée un lecteur pour les données du serveur
			BufferedReader br = new BufferedReader(new    InputStreamReader(socket.getInputStream())); 
			//Crée un écrivain pour envoyer des données au serveur
			PrintWriter pw = new PrintWriter(socket.getOutputStream(), true);
			//Crée un flux de sortie pour envoyer des données au serveur
			OutputStream os = socket.getOutputStream();
			//Crée un flux d'entrée pour recevoir des données du serveur
			InputStream is = socket.getInputStream(); 
			//Envoie un message au serveur
			pw.println("Bonjour, je suis un client dans ce serveur"); 
			//Lit la réponse du serveur
			String serverResponse = br.readLine(); 
			//Affiche la réponse du serveur
			System.out.println(serverResponse); 
			int nb1;
			int nb2;
			String op;
			double res = 0;
			//Crée un scanner pour lire les entrées de l'utilisateur
			Scanner scanner = new Scanner(System.in); 
			System.out.print("Donnez un nombre1 : ");
			//Lit le premier nombre à partir de l'entrée de l'utilisateur
			nb1 = scanner.nextInt(); 
			System.out.print("Donnez un nombre2 : ");
			//Lit le deuxième nombre à partir de l'entrée de l'utilisateur
			nb2 = scanner.nextInt();
			//Nettoie la ligne vide restante
			scanner.nextLine(); 
			System.out.print("Donnez un opérateur (+, -, *, /) : ");
			//Lit l'opérateur à partir de l'entrée de l'utilisateur
			op = scanner.nextLine(); 

			//Crée un objet Operation avec les valeurs lues
			ServeurClientObjet.Operation op1 = new Operation(nb1, nb2, op); 
			//Crée un flux de sortie pour les objets
			ObjectOutputStream oos = new ObjectOutputStream(os);
			//Crée un flux d'entrée pour les objets
			ObjectInputStream ois = new ObjectInputStream(is);
			//Envoie l'objet Operation au serveur
			oos.writeObject(op1); 
			//Lit la réponse du serveur sous forme d'objet Operation
			Operation op2 = (Operation) ois.readObject(); 
			//Affiche le résultat renvoyé par le serveur
			System.out.println("Résultat : " + op2.getRes());
			//Ferme le socket client
			socket.close(); 
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
	}
}

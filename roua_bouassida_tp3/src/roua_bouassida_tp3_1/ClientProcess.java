package roua_bouassida_tp3_1;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientProcess extends Thread {
// Le socket client pour cette instance
        private Socket socket; 
// Le numéro du client
        private int index; 
        public ClientProcess(Socket socket, int index) {
            this.socket = socket;
            this.index = index;
        }
        @Override
        public void run() {
            try {
                BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter pw = new PrintWriter(socket.getOutputStream(), true);
// Lit le message du client
           String clientMessage = br.readLine(); 
              
// Affiche le message du client
  System.out.println("Message du client " + index + ": " + clientMessage); 
                String response = "Bienvenue, vous êtes le client numéro " + index + ". Votre @IP: " + socket.getRemoteSocketAddress();
// Envoie une réponse au client avec son numéro et son adresse IP
                pw.println(response); 
                OutputStream os = socket.getOutputStream();
                InputStream is = socket.getInputStream();
// Crée un flux de sortie pour les objets
                ObjectOutputStream oos = new ObjectOutputStream(os); 
// Crée un flux d'entrée pour les objets
                ObjectInputStream ois = new ObjectInputStream(is); 
// Lit l'objet Operation du client
                ServeurClientObjet.Operation op1 = (Operation) ois.readObject(); 
// Récupère le premier nombre de l'opération
                int nb1 = op1.getNb1(); 
// Récupère le deuxième nombre de l'opération
                int nb2 = op1.getNb2();
// Récupère l'opérateur de l'opération
                String op = op1.getOp(); 
// Initialise le résultat de l'opération
                double res = 0; 
                if (op.equals("+")) {
// Effectue une addition
                    res = nb1 + nb2; 
                } else if (op.equals("-")) {
// Effectue une soustraction
                    res = nb1 - nb2; 
                } else if (op.equals("*")) {
// Effectue une multiplication
                    res = nb1 * nb2; 
                } else if (op.equals("/")) {
                    if (nb2 != 0) {
// Effectue une division (vérifie si le dénominateur n'est pas nul)
                        res = (double) nb1 / nb2; 
                    } else {
                        System.out.println("Division par zéro impossible.");
                    }
                }
// Définit le résultat de l'opération
                op1.setRes(res); 
// Envoie l'objet Operation mis à jour au client
                oos.writeObject(op1); 
// Ferme le socket client
                socket.close(); 
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException ex) {
                throw new RuntimeException(ex);
            }
        }
    }
    public void start() {
        // TODO Auto-generated method stub
    }
}

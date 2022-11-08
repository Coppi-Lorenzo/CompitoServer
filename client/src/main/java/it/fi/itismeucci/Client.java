package it.fi.itismeucci;

import java.io.*;
import java.net.*;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Client {
    
    String nomeServer = "localhost";
    int portaServer = 6789;
    Socket mioSocket;
    BufferedReader tastiera;
    Messaggio richiesta;
    String presenza;
    Messaggio MessaggioDalServer;
    DataOutputStream outVersoServer;
    BufferedReader inDalServer;
    Messaggio acquisti;
    String StringaRichiesta;
    ObjectMapper objectMapper = new ObjectMapper();


    public Socket connetti(){
        System.out.println("2 CLIENT partito in esecuzione...");
         
        try{
            tastiera = new BufferedReader(new InputStreamReader(System.in));    //Inizializzazione input scrittura  
            mioSocket = new Socket(nomeServer, portaServer);    //Inizializzazione del socket del client
            outVersoServer = new DataOutputStream(mioSocket.getOutputStream()); //Inizializzazione della periferica di output 
            inDalServer = new BufferedReader(new InputStreamReader(mioSocket.getInputStream()));    //Inizializzazione input messaggi dal server
        }
        catch(UnknownHostException e){
            System.err.println("Host sconosciuto"); //Errore inizializzazione generale
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            System.out.println("Errore durante la comunicazione!");
            System.exit(1);
        }
    return mioSocket;
    }

    public void comunica(){     
        try{
            System.out.println("Richiesta della lista dei biglietti" +'\n');      //Preparazione all'invio della richiesta per la lista
            presenza = tastiera.readLine();        //Lettura della richiesta da tastiera 

            System.out.println("Invio richiesta...");     //Messaggio per anetecedente all'invio della richiesta effettiva 
            outVersoServer.writeBytes(presenza +'\n');     //Invio della richiesta la server

            MessaggioDalServer = objectMapper.readValue(MessaggioDalServer, Messaggio.class);       //Lettura del messaggio ricevuto dal server da deserializzare
            System.out.println("Lista: " +'\n' +MessaggioDalServer);      //Stampa a schermo del messaggio ricevuto 

            System.out.println("Preparazione lista dei biglietti da acquistare" +'\n');     //Preparazione della lista dei biglietti da acquistare da parte del client 
            outVersoServer.writeBytes(StringaRichiesta = objectMapper.writeValueAsString(richiesta));      //Invio del messaggio contenente i biglietti da acquistare serializzato

            System.out.println("Lista degli acquisti" +'\n');        //Messaggio per l'arrivo della lista finale sugli acquisti avvenuti
            acquisti = objectMapper.readValue(acquisti, Messaggio.class);      //Arrivo della lista con i biglietti acquistati da deserializzare

            System.out.println("CLIENT: termine transazione");      //Termine della comunicazione
            mioSocket.close();  //chiusura del socket del client e comunicazione 
        }
        catch(Exception e){     //Errore
            System.out.println();
            System.out.println("Errore durante la comunicazione!");
            System.exit(1);
        }
    }
}
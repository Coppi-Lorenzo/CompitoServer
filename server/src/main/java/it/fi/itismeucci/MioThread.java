package it.fi.itismeucci;

import java.io.*;
import java.net.*;
import java.util.*;
import com.fasterxml.jackson.databind.ObjectMapper;

public class MioThread 
{
    ServerSocket server = null;
    Socket client;
    String messaggioDaClient;
    BufferedReader inDalCliente;
    DataOutputStream outVersoClient;
    Messaggio listaRichieste;
    Messaggio listaBiglietti;
    Messaggio venduti;
    boolean chiusura = true;
    ObjectMapper objectMapper = new ObjectMapper();

    public void run(){      //Viene affidata la comunicazione al Thread 

        try{

            inDalCliente = new BufferedReader(new InputStreamReader (client.getInputStream()));     //Inizializzazione periferica per lettura messaggi dal client 
            outVersoClient = new DataOutputStream(client.getOutputStream());        //Inizializzazione periferica per inviare al client 

            

            while(chiusura = true){
                System.out.println("Benvenuto cliente");      //Stampa messaggiod di base per il client 
                messaggioDaClient = inDalCliente.readLine();       //Lettura messaggio di introduzione del client 
                System.out.println("Ricevuta la stringa dal cliente:" + messaggioDaClient);    //Annuncio sulla stringa ricevuta dal client

                if(messaggioDaClient.equals("Termina")){
                    chiusura = false;
                }

                System.out.println("Invio lista biglietti totali");       //Messaggio antecedente l'invio della lista da parte del client 
                outVersoClient.writeBytes(objectMapper.writeValue(new File("listaBiglietti.json"), listaBiglietti));     //Invio della lista dei biglietti sottoforma di stringa serializzata

                System.out.println("Attesa richieste del cliente");     //Attesa della lista dei biglietti che il cliente vuole acquistare 
                listaRichieste = objectMapper.readValue(Messaggio.class);        //Ricevuta la lista dei biglietti da acquistare da deserializzare
                System.out.println("Ricevuta la richiesta dal cliente:" + listaRichieste.toString());        //Stampa a schermo della lista da acquistare

                System.out.println("Calcolo disponibilità...");     //Calcolo delle disponibilità dei biglietti

                for(int i = 0; i < listaBiglietti.getDimensione(); i++){        //Scorro la lista totale dei biglietti
                    for(int j = 0; j < listaRichieste.getDimensione(); j++){        //Scorro contemporaneamente quella della lista inviata dal client 
                        if(listaBiglietti.getBiglietto(i).equals(listaRichieste.getBiglietto(j))){        //Controllo se c'è un riscontro tra le due liste 
                            System.out.println("Biglietto numero " + listaBiglietti.getBiglietto(i).getNumero() + "con locazione " + listaBiglietti.getBiglietto(i).getLocazione() + "è stato venduto con successo");       //Stampo a schermo il biglietto che è stato riscontrato
                            venduti.Aggiungi(listaBiglietti.getBiglietto(i));       //Aggiungo alla lista delle vendite il biglietto che è stato venduto con successo
                            listaBiglietti.rimuovi(listaBiglietti.getBiglietto(i));     //Rimuovo dalla lista principale quello che è stato acquistato
                        }
                        else{
                            System.out.println("Il biglietto numero " + listaRichieste.getBiglietto(i).getNumero() + "con locazione " + listaRichieste.getBiglietto(i).getLocazione() + "non è disponibile per la vendita");    //Nel caso non ci fosse un riscontro stampo a schermo il biglietto che non è stato acquistato
                        }
                    }
                }

                System.out.println("Inoltro della lista degli acquisti");       //Messaggio per informare dell'inoltro della lista acquisti
                outVersoClient.writeBytes(objectMapper.writeValue(new File("listaVendite.json"), venduti));       //Invio al client della lista dei biglietti che è riuscito ad acquistare serializzato

                System.out.println("Grazie per aver usufruito del servizio");       //Saluto da parte del server e chiusura comunicazioni
            }

            if(chiusura = false){
                client.close();     //Chiusura del sockete e della comunicazione
            }
            
        }
        catch(Exception e){     //Errore
            System.out.println(e.getMessage());
            System.out.println("Errore durante l'istanza del server");
            System.exit(1);
        }
    }
}

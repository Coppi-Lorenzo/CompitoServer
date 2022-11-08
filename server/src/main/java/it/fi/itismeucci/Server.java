package it.fi.itismeucci;

import java.io.*;
import java.net.*;
import java.util.*;

public class Server
{
    ServerSocket server = null;
    Socket client;
    String stringaRicevuta = null;
    String stringaModificata = null;
    BufferedReader inDalCliente;
    DataOutputStream outVersoClient;

    public Socket attendi(){
        try{
            System.out.println("1 SERVER partito in esecuzione...");        //Si comunica l'avvio del server
            server = new ServerSocket(6789);        //Si inizializza il server con la sua porta 
            client = server.accept();       //Si accetta la richiesta del client 
            //server.close();     //Si chiude il socket 
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            System.out.println("Errore durante l'istanza del server");
            System.exit(1);
        }
        return client;
    }
}

package it.fi.itismeucci;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        Client cliente = new Client();      //Creazione del client
        cliente.connetti();     //Connessione del client 
        cliente.comunica();     //Avvio comunicazione del client 
    }
}

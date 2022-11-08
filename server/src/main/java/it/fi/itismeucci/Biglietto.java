package it.fi.itismeucci;

import com.fasterxml.jackson.databind.ObjectMapper;

public class Biglietto {
    
    int ID =0; 
    int numero; 
    String locazione = "tribuna"; 
    ObjectMapper objectMapper = new ObjectMapper();

    

    //GETTER


    public Biglietto() {
    }

    public int getNumero() {
        return numero;
    }

    public String getLocazione() {
        return locazione;
    }

    //SETTER

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public void setLocazione(String locazione) {
        this.locazione = locazione;
    }

    //COSTRUTTORE

    public Biglietto(String l){
        locazione = l;
        ID++;
        this.numero = ID;
    }

}

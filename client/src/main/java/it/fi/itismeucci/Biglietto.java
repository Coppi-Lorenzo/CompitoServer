package it.fi.itismeucci;

import com.fasterxml.jackson.databind.ObjectMapper;

public class Biglietto {
    
    int ID =0; 
    int numero; 
    String locazione; 
    ObjectMapper objectMapper = new ObjectMapper();

    //GETTER

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

    public Biglietto(){
        ID++;
        this.numero = ID;
    }


}

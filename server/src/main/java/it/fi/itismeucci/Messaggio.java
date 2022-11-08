package it.fi.itismeucci;

import java.util.ArrayList;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Messaggio 
{
    
    ArrayList <Biglietto> lista = new ArrayList<>();
    ObjectMapper objectMapper = new ObjectMapper();


    
    //GETTER


    public ArrayList<Biglietto> getLista() {
        return lista;
    }

    public int getDimensione(){
        return lista.size();
    }

    public Biglietto getBiglietto(int i){
        return lista.get(i);
    }

    //SETTER

    public void setLista(ArrayList<Biglietto> lista) {
        this.lista = lista;
    }

    //COSTRUTTORE

    public Messaggio(){}

    //METODI

    //Aggiungi alla lista
    public void Aggiungi(Biglietto b){
        lista.add(b);
    }

    //Rimuovi dalla lista 
    public void rimuovi(Biglietto b){
        lista.remove(b);
    }

    public String toString(){
        for(int i = 0; i < lista.size(); i++){
            System.out.println(lista.get(i).getNumero() + lista.get(i).getLocazione());
        }
        return null;
    }

}

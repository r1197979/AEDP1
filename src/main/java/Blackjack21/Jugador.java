package Blackjack21;

import DeckOfCards.CartaInglesa;
import DeckOfCards.Mazo;

import java.util.ArrayList;

public class Jugador {
    private String nombre;
    private ArrayList<CartaInglesa> mano;
    private int puntaje;
    private boolean plantado;
    private boolean superaLimite;
    private int Ases=0;

    public Jugador(){}
    public Jugador(String n){
        nombre= n;
        mano= new ArrayList<>();
    }

    public int convertirCarta(CartaInglesa carta){
        int valorNuevo= carta.getValor();
        if(valorNuevo==11 || valorNuevo==12 || valorNuevo==13) { //Q,J,K
            valorNuevo = 10;
        }else if (valorNuevo==14){ //As
            valorNuevo=11;
        }
        return valorNuevo;
    }

    //recorrer cartas de jugador y sumar total
    public void sumarMano(){
        puntaje= 0;
        int valor;

        for(int i=0; i<mano.size(); i++){
            CartaInglesa cartaActual= mano.get(i);
            valor= convertirCarta(cartaActual);

            if(valor==11){
                Ases++;
                puntaje+=valor;
            }else{
                puntaje+=valor;
            }
        }

        switch(Ases){
            case 1: if(puntaje>21) puntaje-=10; break;
            case 2: if(puntaje>21) puntaje-=20; break;
        }
    }

    //mete carta a la mano
    public void agregarCarta(CartaInglesa carta) {
        mano.add(carta);
        sumarMano();
        if(puntaje>21) superaLimite=true;
        carta.makeFaceUp();
    }

    public ArrayList<CartaInglesa> getMano(){return mano;}
    public int getPuntaje(){ return puntaje;}
    public boolean getPlantado(){ return plantado;}
    public void plantarse(){plantado=true;}
    public boolean getSuperaLimite(){ return superaLimite;}
    public String getNombre(){return nombre;}

}

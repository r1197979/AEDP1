package Blackjack21;

import DeckOfCards.Carta;
import DeckOfCards.CartaInglesa;
import DeckOfCards.Mazo;

import java.util.ArrayList;

public class Logica {

    public Logica(){}
    boolean esPrimera= false;

    //2 cartas por jugador
    public void iniciarReparto(ArrayList<Jugador> jugadores, Mazo mazo){
        for(int ronda=0; ronda<=1; ronda++){
            for(int i=0; i<jugadores.size(); i++){
                tomarCarta(jugadores.get(i), mazo);
            }
        }
    }

    //
    public void tomarCarta(Jugador j, Mazo m){
        if(!j.getSuperaLimite() && !j.getPlantado()){
            CartaInglesa carta= m.obtenerUnaCarta();
            j.agregarCarta(carta);
        }
    }

    public void plantarse(Jugador j){
        j.plantarse();
    }

    public boolean haPerdido(Jugador j){
        if(j.getSuperaLimite()) return true;
        else return false;
    }

    public ArrayList<Jugador> buscarGanador(ArrayList<Jugador> jugadores){
        ArrayList<Jugador> ganadores= new ArrayList<>();
        int puntajeGanador=0;

        for(int i=0; i<jugadores.size(); i++){
            Jugador j= jugadores.get(i);

            if(!j.getSuperaLimite()){
                if(j.getPuntaje()>puntajeGanador){
                    ganadores.clear(); //se reemplazatodo por solo el mayor
                    ganadores.add(j);
                    puntajeGanador= j.getPuntaje();
                }else if(j.getPuntaje() == puntajeGanador){
                    ganadores.add(j); //solo anexar otro ganador
                }
            }
        }
        return ganadores;
    }

    public void jugarTurnoDealer(Jugador dealer, Mazo mazo){
        while(dealer.getPuntaje() <=16){
            dealer.agregarCarta(mazo.obtenerUnaCarta());
        }
        dealer.plantarse();
    }



}

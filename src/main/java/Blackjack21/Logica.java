package Blackjack21;

import DeckOfCards.Carta;
import DeckOfCards.CartaInglesa;
import DeckOfCards.Mazo;

import java.util.ArrayList;

public class Logica {
    public Pila<Undo> getMovimientos() {
        return movimientos;
    }

    private Pila<Undo> movimientos;

    public Logica(){
        movimientos= new Pila<>();
    }
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
            Undo u= new Undo(j, carta, true);
            movimientos.push(u);
        }
    }

    public void plantarse(Jugador j){
        j.plantarse();
        Undo u= new Undo(j, null, false);
        movimientos.push(u);
    }

    public Jugador deshacerMovimiento(Mazo m){
        if(!movimientos.pilaVacia()){
            Undo tope = movimientos.pop(); // ultima accion
            Jugador j = tope.getJugador(); // quien juega
            // validar que no sean las dos primeras cartas del jugador
            if(tope.getAccion() && j.getMano().size()<=2){
                movimientos.push(tope);
                return null;
            }

            /*
            true = tomarCarta; false = plantado
            */
            if(tope.getAccion()){
                CartaInglesa ci = tope.getCarta();
                // quitar carta y recalcular puntaje
                j.getMano().remove(ci);
                j.sumarMano();
                if (j.getPuntaje() <= 21) {
                    j.setSuperaLimite(false);
                }

                m.getCartas().add(ci);
                m.mezclar();
            } else { // jugador eligió plantarse en ese movimiento
                j.deshacerPlantado();
            }
            return j;
        }
        return null;
    }


    public boolean haPerdido(Jugador j){
        if(j.getSuperaLimite()) return true;
        else return false;
    }

    /*
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
     */


    public Pila<Jugador> buscarGanador(ArrayList<Jugador> jugadores){
        Pila<Jugador> ganadores = new Pila<>();
        int puntajeGanador = 0;

        for(int i = 0; i < jugadores.size(); i++){
            Jugador j = jugadores.get(i);
            if(!j.getSuperaLimite()){
                if(j.getPuntaje() > puntajeGanador){
                    while(!ganadores.pilaVacia()){ ganadores.pop(); } // vaciar manualmente
                    ganadores.push(j);
                    puntajeGanador = j.getPuntaje();
                }else if(j.getPuntaje() == puntajeGanador){
                    ganadores.push(j);
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

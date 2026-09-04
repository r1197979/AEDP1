package Blackjack21;

import DeckOfCards.Carta;
import DeckOfCards.Mazo;

import java.util.ArrayList;
import java.util.Scanner;

public class Juego {
    private Logica logica;
    private ArrayList<Jugador> jugadores;
    private Mazo mazo;
    private int turnoActual;
    private int numeroJugadores;
    private ArrayList<Jugador> ganadores;
    private Jugador dealer;

    public Juego(ArrayList<String> nombres){
        mazo= new Mazo();
        logica= new Logica();
        numeroJugadores= nombres.size();
        jugadores= new ArrayList<Jugador>();
        dealer= new Jugador("Dealer");
        ganadores= new ArrayList<>();

        iniciarJuego(nombres);
    }

    public void iniciarJuego(ArrayList<String> nombres){

        for(int i=0; i<numeroJugadores; i++){
            Jugador j = new Jugador(nombres.get(i));
            jugadores.add(j);
        }

        logica.iniciarReparto(jugadores, mazo);
        logica.tomarCarta(dealer, mazo);
        logica.tomarCarta(dealer, mazo);
    }

    //llamar despues de cada turno. indica si termina la ronda.
    //evaluar si sigue un jugador, si no buscar ganador
    public boolean jugadorTurno(){
        turnoActual++; //pasar del jugador que acaba de jugar

        //mientras que el jugador esté plantado o supere límite, y que no sobrepase el numero de jugadores, salta su turno
        while(turnoActual<numeroJugadores && (jugadores.get(turnoActual).getPlantado() || jugadores.get(turnoActual).getSuperaLimite())){
            turnoActual++; //en caso de que no pueda jugar el actual sig
        }

        if(turnoActual>=numeroJugadores){//si ya jugaron todos
            logica.jugarTurnoDealer(dealer,mazo); //turno dealer
            ganadores= logica.buscarGanador(jugadores);

            if(!dealer.getSuperaLimite()){
                if(ganadores.isEmpty()){
                    ganadores.add(dealer); //si todos pierden gana dealer
                }else if(dealer.getPuntaje() == ganadores.get(0).getPuntaje()){
                    ganadores.add(dealer);
                }else if(dealer.getPuntaje() > ganadores.get(0).getPuntaje()){
                    ganadores.clear();
                    ganadores.add(dealer);
                }
            }
            return true; //ronda finalizada, se encontró ganador
        }
        return false; //no han pasado todos los jugadores
    }

    public ArrayList<Jugador> getJugadores() {return jugadores;}

    public Jugador getJugadorActual(){
        if(turnoActual>=jugadores.size()){return null;} //para no exceder limite
        return jugadores.get(turnoActual);
    }

    public Mazo getMazo(){ return mazo;}
    public Logica getLogica(){return logica;}
    public Jugador getDealer(){return dealer;}
    public ArrayList<Jugador> getGanadores(){return ganadores;}

    public String toString(){
        String cartasDealer= "";
        for(Carta c: dealer.getMano()){c.makeFaceUp();}
        String stringDealer= "-> "+dealer.getNombre()+" / Cartas: "+dealer.getMano()+" / "+dealer.getPuntaje()+" puntos";

        String stringJugadores= "";
        String carta= "";
        for(int i=0; i<jugadores.size(); i++){
            Jugador j= jugadores.get(i);

            for(Carta c:j.getMano()){c.makeFaceUp();}

            String turno= "";
            if(i==turnoActual){
                turno= "*TURNO DEL JUGADOR";
            }
            stringJugadores+= "-> "+j.getNombre()+" / Cartas: "+j.getMano()+" / "+j.getPuntaje()+" puntos "+turno+ "\n";
        }

        String textoGanadores= "";
        if(!ganadores.isEmpty()){
            textoGanadores+= "GANÓ ";
            for(int i=0; i<ganadores.size(); i++){
                textoGanadores+= ganadores.get(i).getNombre();
                textoGanadores+= ", ";
            }
            textoGanadores+= " con "+ganadores.get(0).getPuntaje()+ " puntos";
        }
        return stringDealer+ "\n" +stringJugadores+ "\n"+ textoGanadores;
    }



}

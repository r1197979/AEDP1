import Blackjack21.Juego;
import DeckOfCards.CartaInglesa;
import DeckOfCards.Mazo;

import java.util.ArrayList;
import java.util.Scanner;

public class JuegoConsola {
    Juego juego;

    public JuegoConsola(){ //interfaz texto
        Scanner scEnteros = new Scanner(System.in);
        System.out.println("Número de jugadores: ");
        int cantidad= scEnteros.nextInt();
        ArrayList<String> nombres= new ArrayList<>(cantidad);

        Scanner scNombres = new Scanner(System.in);
        for(int i=0; i<cantidad; i++){
            System.out.println("Ingresa nombre de jugador "+ (i+1) + ":");
            String n= scNombres.nextLine();
            nombres.add(n);
        }

        juego= new Juego(nombres);
    }

    public void playGame() {
        Scanner sc = new Scanner(System.in);
        System.out.println(juego);

        int op;
        boolean terminado = false;
        while (!terminado) {
            menu();
            System.out.println("¿Que operación quieres hacer?");
            op = sc.nextInt();

            switch (op) {
                case 1:
                    juego.getLogica().plantarse(juego.getJugadorActual());
                    terminado = juego.jugadorTurno(); //terminar turno
                    break;

                case 2:
                    juego.getLogica().tomarCarta(juego.getJugadorActual(), juego.getMazo());
                    if(juego.getJugadorActual().getSuperaLimite()){
                        terminado = juego.jugadorTurno();
                    }
                    break;

                default:
                    System.out.println("Ingresa operación válida");
            }
            System.out.println(juego);
        }
        if(terminado){
            System.out.println("JUEGO TERMINADO");
        }
    }

    private void menu(){
        System.out.println("*** MOVIMIENTOS ***");
        System.out.println("[1] Plantarse");
        System.out.println("[2] Tomar carta");
    }
}

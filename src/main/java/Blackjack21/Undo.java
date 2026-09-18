package Blackjack21;
import DeckOfCards.CartaInglesa;

public class Undo {
    private Jugador j;
    private CartaInglesa ci;
    private boolean accion; //0 plantado, 1 tomar carta

    public Undo(Jugador j, CartaInglesa carta, boolean accion) {
        this.j=j;
        ci=carta;
        this.accion=accion;
    }

    public Jugador getJugador() {return j;}
    public CartaInglesa getCarta() {return ci;}
    public boolean getAccion() {return accion;}
}


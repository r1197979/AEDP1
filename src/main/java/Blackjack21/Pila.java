package Blackjack21;

import java.util.Arrays;

public class Pila<T> {

    private T[] pila;
    private int tope;

    public Pila() {
        pila = (T[]) new Object[100];
        tope = -1;
    }

    public Pila(int cantidad) {
        pila = (T[]) new Object[cantidad];
        tope = -1;
    }

    public void push(T dato) {
        if (pilaLlena()) {
            pila = Arrays.copyOf(pila, pila.length*2);
        } else {
            tope++;
            pila[tope] = dato;
        }
    }

    public T pop() {
        T dato = null;
        if (pilaVacia()) {
            System.out.println("Subdesbordamiento");
        } else {
            dato = pila[tope];
            tope--;
        }
        return dato;
    }

    public boolean pilaLlena() {
        return tope == pila.length - 1;
    }

    public boolean pilaVacia() {
        return tope == -1;
    }

}

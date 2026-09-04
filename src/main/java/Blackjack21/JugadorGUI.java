package Blackjack21;

import DeckOfCards.CartaInglesa;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

public class JugadorGUI {


    /*
    CREAR LA VISTA DE UN JUGADOR
    */
    public VBox crearJugador(Jugador j, Jugador jugadorEnTurno){
        VBox jugador= new VBox(10); //un contenedor de jugador (nombre+cartas)
        jugador.setAlignment(Pos.CENTER);
        Label nombre= new Label(j.getNombre());
        nombre.setStyle("-fx-font-size: 18px; -fx-text-fill: white; -fx-font-weight: bold;");
        HBox cartasHbox= new HBox(5); //para meter cartas
        cartasHbox.setAlignment(Pos.CENTER);

        if(j ==jugadorEnTurno){
            nombre.setStyle("-fx-font-size: 18px; -fx-text-fill: gold; -fx-font-weight: bold;");
        }else{
            nombre.setStyle("-fx-font-size: 18px; -fx-text-fill: white; -fx-font-weight: bold;");
        }

        //recorrer mano de jugador, obtener carta y convertir a texto para Label
        for(int i=0; i<j.getMano().size(); i++){
            CartaInglesa ci= j.getMano().get(i);
            Label cartaLabel= new Label(ci.toString());
            cartaLabel.setStyle("-fx-font-size: 20px; -fx-background-color: white; -fx-padding: 60px 40px; -fx-background-radius: 10px;");
            cartasHbox.getChildren().add(cartaLabel); //juntar cartas
        }

        jugador.getChildren().addAll(nombre, cartasHbox); //label+cartas
        return jugador;
    }



    /*
    DETERMINA LAS POSICIONES DE LOS JUGADORES SEGUN CUANTOS SEAN
     */
    public void acomodarJugadores(ArrayList<Jugador> jugadores, BorderPane border, Jugador jugadorEnTurno){

        if(jugadores.size()==1){
            VBox uno= crearJugador(jugadores.get(0),jugadorEnTurno); //posicion 0=primer jugador
            border.setBottom(uno);
            BorderPane.setAlignment(uno, Pos.CENTER);

        }else if(jugadores.size()==2){
            VBox primero= crearJugador(jugadores.get(0),jugadorEnTurno);
            VBox segundo= crearJugador(jugadores.get(1),jugadorEnTurno);
            border.setLeft(primero);
            border.setRight(segundo);
            BorderPane.setAlignment(primero, Pos.CENTER);
            BorderPane.setAlignment(segundo, Pos.CENTER);

        }else if(jugadores.size()==3){
            VBox primero= crearJugador(jugadores.get(0),jugadorEnTurno);
            VBox segundo= crearJugador(jugadores.get(1),jugadorEnTurno);
            VBox tercero= crearJugador(jugadores.get(2),jugadorEnTurno);
            border.setLeft(primero);
            border.setRight(segundo);
            border.setBottom(tercero);
            BorderPane.setAlignment(primero, Pos.CENTER);
            BorderPane.setAlignment(segundo, Pos.CENTER);
            BorderPane.setAlignment(tercero, Pos.CENTER);

        }else if(jugadores.size()==4){ //jugador 3 y 4 abajo
            VBox primero= crearJugador(jugadores.get(0),jugadorEnTurno);
            VBox segundo= crearJugador(jugadores.get(1),jugadorEnTurno);
            VBox tercero= crearJugador(jugadores.get(2),jugadorEnTurno);
            VBox cuarto= crearJugador(jugadores.get(3),jugadorEnTurno);
            border.setLeft(primero);
            border.setRight(segundo);
            BorderPane.setAlignment(primero, Pos.CENTER);
            BorderPane.setAlignment(segundo, Pos.CENTER);

            HBox contenedorJugadores= new HBox(15);
            contenedorJugadores.getChildren().addAll(tercero, cuarto); //dos jugadores juntos
            contenedorJugadores.setAlignment(Pos.CENTER); //al centro del bottom
            border.setBottom(contenedorJugadores);
            BorderPane.setAlignment(contenedorJugadores, Pos.CENTER); //centrar bloque bottom

        }
    }
}

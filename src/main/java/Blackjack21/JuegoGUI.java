package Blackjack21;

import DeckOfCards.CartaInglesa;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import java.util.ArrayList;
import javafx.scene.control.Alert;

public class JuegoGUI extends Application{

    int numJugadores;
    ArrayList<String> nombres= new ArrayList<>(); //registrar nombres
    Juego juego; //recibe los nombres
    int contadorJugadores=0;
    JugadorGUI jugadorVista= new JugadorGUI();



    public void start(Stage stagePrincipal) {
        /*
        ESCENA PRINCIPAL MENU
         */
        StackPane stack = new StackPane();
        stack.setStyle("-fx-background-image: url('/imagenBlackjack.png'); -fx-background-size: 100% 100%;");

        Button botonJugar = new Button("Iniciar Partida");
        Button botonSalir = new Button("Salir");
        botonJugar.setStyle("-fx-font-size: 20px; -fx-padding: 15px 35px; -fx-background-color: #0b6623; -fx-text-fill: white;");
        botonSalir.setStyle("-fx-font-size: 20px; -fx-padding: 15px 35px; -fx-background-color: #0b6623; -fx-text-fill: white;");

        botonSalir.setOnAction(e -> {
            stagePrincipal.close();
        });

        Label titulo = new Label("Blackjack 21");
        titulo.setStyle("-fx-font-size: 48px; -fx-text-fill: #c9a227; -fx-font-weight: bold;-fx-padding: 0 0 100 0;-fx-effect: dropshadow(gaussian, black, 5, 0.5, 2, 2);");

        VBox cajaBotones = new VBox(15);
        cajaBotones.setAlignment(Pos.CENTER);
        cajaBotones.getChildren().addAll(titulo, botonJugar, botonSalir);
        stack.getChildren().add(cajaBotones);

        Scene escena = new Scene(stack, 1450, 900);
        stagePrincipal.setTitle("Juego Blackjack21");
        stagePrincipal.setScene(escena);
        stagePrincipal.show();

        /*
        ESCENA PARA SELECCIONAR NUMERO DE JUGADORES
         */
        botonJugar.setOnAction(e -> {
            StackPane pantallaNumJugadores = new StackPane();
            pantallaNumJugadores.setStyle("-fx-background-image: url('/imagenBlackjack.png'); -fx-background-size: cover;");

            Label labelJugadores = new Label("Elige el número de jugadores");
            labelJugadores.setStyle("-fx-font-size: 32px; -fx-text-fill: white; -fx-font-weight: bold;");

            Button boton1= new Button("1");
            Button boton2= new Button("2");
            Button boton3= new Button("3");
            Button boton4= new Button("4");
            String estiloBoton = "-fx-font-size: 20px; -fx-padding: 10px 20px; -fx-background-color: #8B0000; -fx-text-fill: #c9a227;";
            boton1.setStyle(estiloBoton);
            boton2.setStyle(estiloBoton);
            boton3.setStyle(estiloBoton);
            boton4.setStyle(estiloBoton);

            HBox filaBotones = new HBox(20);
            filaBotones.setAlignment(Pos.CENTER);
            filaBotones.getChildren().addAll(boton1, boton2, boton3, boton4);

            VBox contenedor = new VBox(30);
            contenedor.setAlignment(Pos.CENTER);
            contenedor.getChildren().addAll(labelJugadores, filaBotones);
            pantallaNumJugadores.getChildren().add(contenedor);

            Scene escenaNumJugadores = new Scene(pantallaNumJugadores, 1450, 900);
            stagePrincipal.setScene(escenaNumJugadores);

            boton1.setOnAction(evento -> { numJugadores=1; mostrarPantallaNombres(stagePrincipal);});
            boton2.setOnAction(evento -> { numJugadores=2; mostrarPantallaNombres(stagePrincipal);});
            boton3.setOnAction(evento -> { numJugadores=3; mostrarPantallaNombres(stagePrincipal);});
            boton4.setOnAction(evento -> { numJugadores=4; mostrarPantallaNombres(stagePrincipal);});
        });
    }


    /*
    METODO PARA PEDIR Y REGISTRAR NOMBRES DE JUGADORES
     */
    public void mostrarPantallaNombres(Stage stagePrincipal){
        StackPane pantallaNombres = new StackPane();
        pantallaNombres.setStyle("-fx-background-image: url('/imagenBlackjack.png'); -fx-background-size: cover;");
        Label instruccion = new Label("Nombre del jugador " + (contadorJugadores + 1));
        instruccion.setStyle("-fx-font-size: 28px; -fx-text-fill: white; -fx-font-weight: bold;");

        TextField textNombre = new TextField();
        textNombre.setPromptText("->");
        textNombre.setStyle("-fx-font-size: 18px;");
        textNombre.setMaxWidth(250);

        Button botonRegistrar = new Button("Registrar");
        botonRegistrar.setStyle("-fx-font-size: 18px; -fx-padding: 10px 20px; -fx-background-color: #8B0000; -fx-text-fill: #c9a227;");
        botonRegistrar.setOnAction(ev -> {
            String nombreEscrito = textNombre.getText();
            nombres.add(nombreEscrito);
            contadorJugadores++;

            if (contadorJugadores >= numJugadores) {
                //LLAMADA A LA PANTALLA PARA JUGAR
                iniciarPantallaJuego(stagePrincipal);
            } else {
                mostrarPantallaNombres(stagePrincipal);
            }
        });

        VBox contenedor = new VBox(20);
        contenedor.setAlignment(Pos.CENTER);
        contenedor.getChildren().addAll(instruccion, textNombre, botonRegistrar);
        pantallaNombres.getChildren().add(contenedor);

        Scene escenaNombre = new Scene(pantallaNombres, 1450, 900);
        stagePrincipal.setScene(escenaNombre);
    }



    /*
    METODO GRAFICO PARA JUGAR (PRIMERA VEZ)
     */
    //crear Juego (asigna nombres, crea dealer, reparte cartas)
    public void iniciarPantallaJuego(Stage stagePrincipal){
        juego= new Juego(nombres);
        dibujarPantalla(stagePrincipal);
    }

    /*
    REDIBUJAR ELEMENTOS DE PANTALLA, mismo juego
     */
    public void dibujarPantalla(Stage stagePrincipal){

        BorderPane border = new BorderPane();
        border.setStyle("-fx-background-image: url('/imagenBlackjack.png'); -fx-background-size: cover;");
        border.setPadding(new Insets(20));

        /*
        mazo central
         */
        Image imagenMazo = new Image(getClass().getResourceAsStream("/mazo.png"));
        ImageView vistaMazo = new ImageView(imagenMazo);
        VBox vistaDealer = jugadorVista.crearJugador(juego.getDealer(), juego.getJugadorActual());

        HBox hboxTop = new HBox(400);
        hboxTop.setAlignment(Pos.CENTER);
        hboxTop.getChildren().addAll(vistaMazo, vistaDealer);

        border.setTop(hboxTop);
        BorderPane.setAlignment(hboxTop, Pos.CENTER);


        jugadorVista.acomodarJugadores(juego.getJugadores(), border, juego.getJugadorActual());

        Button botonPlantarse= new Button("Plantarse");
        Button botonTomar= new Button("Tomar carta");
        botonTomar.setPrefWidth(120);
        botonTomar.setPrefHeight(60);
        botonPlantarse.setPrefWidth(120);
        botonPlantarse.setPrefHeight(60);
        botonTomar.setStyle("-fx-font-size: 15px; -fx-padding: 6px 10px; -fx-background-color: darkgreen; -fx-text-fill: white;");
        botonPlantarse.setStyle("-fx-font-size: 15px; -fx-padding: 6px 10px; -fx-background-color: darkgreen; -fx-text-fill: white;");

        botonPlantarse.setOnAction(e ->{
            if(juego.getJugadorActual()==null){return;}//no hay nadie en turno
            juego.getLogica().plantarse(juego.getJugadorActual());
            boolean rondaTerminada= juego.jugadorTurno(); //pasar turno
            dibujarPantalla(stagePrincipal);

            if(rondaTerminada){mostrarGanador(stagePrincipal);}
        });

        botonTomar.setOnAction(e ->{
            if(juego.getJugadorActual()==null){return;} //no hay nadie en turno
            juego.getLogica().tomarCarta(juego.getJugadorActual(), juego.getMazo()); //jugador toma carta

            boolean rondaTerminada=false;
            if(juego.getJugadorActual().getSuperaLimite()){//si ya supera limite no puede tomar, siguiente jugador
                rondaTerminada= juego.jugadorTurno();
            }
            dibujarPantalla(stagePrincipal);

            if(rondaTerminada){mostrarGanador(stagePrincipal);}
        });

        HBox cajaBotones= new HBox(20);
        cajaBotones.setAlignment(Pos.CENTER);
        cajaBotones.getChildren().addAll(botonPlantarse, botonTomar);
        border.setCenter(cajaBotones);

        Scene escenaJuego = new Scene(border, 1450, 900);
        stagePrincipal.setScene(escenaJuego);

    }

/*
    public void mostrarGanador(Stage stagePrincipal) {
        Pila<Jugador> ganador = juego.getGanadores();
        String mensaje;
        if(ganador.isEmpty()){
            mensaje="No hay ganador";
        }else if(ganador.size()==1){
            mensaje ="Jugador "+ganador.get(0).getNombre()+" ha ganado con "+ganador.get(0).getPuntaje()+ " puntos";
        }else{
            mensaje= "Han ganado ";
            for(int i=0; i<ganador.size();i++){
                mensaje+= ganador.get(i).getNombre()+", ";
            }
            mensaje+=" con "+ ganador.get(0).getPuntaje()+ " puntos";
        }

        Alert alerta= new Alert(Alert.AlertType.INFORMATION);
        alerta.setTitle("FIN DEL JUEGO");
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
        stagePrincipal.close();
    }
 */

    public void mostrarGanador(Stage stagePrincipal) {
        Pila<Jugador> ganador = juego.getGanadores();
        String mensaje;
        if (ganador.pilaVacia()) {
            mensaje = "No hay ganador";
        } else {
            Pila<Jugador> aux = new Pila<>();
            int contador = 0;
            int puntajeGanador = 0;
            String nombres = "";

            // Desapilamos temporalmente para contar y extraer la información
            while (!ganador.pilaVacia()) {
                Jugador g = ganador.pop();
                puntajeGanador = g.getPuntaje();
                nombres += g.getNombre() + ", ";
                contador++;
                aux.push(g);
            }

            while (!aux.pilaVacia()) {
                ganador.push(aux.pop());
            }

            if (contador == 1) {
                mensaje ="Jugador " + nombres + " ha ganado con " + puntajeGanador + " puntos";
            } else {
                mensaje = "Han ganado " + nombres + " con " + puntajeGanador + " puntos";
            }
        }

        Alert alerta= new Alert(Alert.AlertType.INFORMATION);
        alerta.setTitle("FIN DEL JUEGO");
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
        stagePrincipal.close();
    }


    public static void main(String[] args) {
        launch(args);
    }
}

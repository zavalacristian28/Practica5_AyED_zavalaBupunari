package com.example.practica5;

import Logica.Coleccion;
import javafx.collections.FXCollections;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Polygon;

public class Botones {

    private Coleccion coleccion;
    private Fondo fondo;

    private RadioButton rbMenorAMayor;
    private RadioButton rbMayorAMenor;
    private ComboBox<Integer> comboNivel;
    private ToggleGroup grupoOrden;
    private ComboBox<String> comboAtributos;

    //constructor
    public Botones(Coleccion coleccion, Fondo fondo) {
        this.coleccion = coleccion;
        this.fondo = fondo;
        crearControlesFiltro(fondo.getPanel());
        botonTodasLasCartas(fondo.getPanel());
        botonCartasPorNivel(fondo.getPanel());
        botonCartasPorAtributo(fondo.getPanel());
        botonPorAtaque(fondo.getPanel());
        botonPorDefensa(fondo.getPanel());
        botonDerecha(fondo.getPanel());
        botonIzquierda(fondo.getPanel());
        //Renderizado inicial de las cartas al correr el programa
        if (coleccion.getNodoActual() != null) {
            CartasG.renderizarGaleria(
                    coleccion.getNodoActual(),
                    fondo.getPanelCartas()
            );
        }
    }// fin del constructor

    private void crearControlesFiltro(Pane panel) {
        grupoOrden = new ToggleGroup();
        rbMenorAMayor = new RadioButton("Menor a Mayor");
        rbMayorAMenor = new RadioButton("Mayor a Menor");
        rbMenorAMayor.setToggleGroup(grupoOrden);
        rbMayorAMenor.setToggleGroup(grupoOrden);
        rbMenorAMayor.setSelected(true);
        String estiloRB = "-fx-font-size: 13px; " + "-fx-font-weight: bold; " + "-fx-text-fill: #173B57;";
        rbMenorAMayor.setStyle(estiloRB);
        rbMayorAMenor.setStyle(estiloRB);
        rbMenorAMayor.setLayoutX(0);
        rbMenorAMayor.setLayoutY(0);
        rbMayorAMenor.setLayoutX(140);
        rbMayorAMenor.setLayoutY(0);

        Integer[] niveles = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};
        comboNivel = new ComboBox<>(FXCollections.observableArrayList(niveles));
        comboNivel.setPromptText("Nivel");
        comboNivel.setPrefWidth(120);
        comboNivel.setStyle("-fx-font-size: 13px; -fx-font-weight: bold;");
        comboNivel.setLayoutX(300);
        comboNivel.setLayoutY(0);

        String[] atributos = {"Oscuridad", "Luz", "Fuego", "Tierra", "Viento", "Agua"};
        comboAtributos = new ComboBox<>(FXCollections.observableArrayList(atributos));
        comboAtributos.setPromptText("Atributos");
        comboAtributos.setPrefWidth(120);
        comboAtributos.setStyle("-fx-font-size: 13px; -fx-font-weight: bold;");
        comboAtributos.setLayoutX(440);
        comboAtributos.setLayoutY(0);

        // Esto es para deseleccionar el combo de niveles para impedir que se hagan 2 busquedas al mismo
        //tiempo del mismo tipo
        rbMenorAMayor.setOnAction(e ->
                comboNivel.getSelectionModel().clearSelection()
        );

        rbMayorAMenor.setOnAction(e ->
                comboNivel.getSelectionModel().clearSelection()
        );
        //esta deselecciona los radio button
        comboNivel.setOnAction(e -> {
            if (comboNivel.getValue() != null) {
                grupoOrden.selectToggle(null);
            }
        });


        Pane contenedorControles = new Pane();
        contenedorControles.setLayoutX(35);
        contenedorControles.setLayoutY(25);
        contenedorControles.setPrefSize(600, 35);
        contenedorControles.getChildren().addAll(rbMenorAMayor, rbMayorAMenor, comboNivel, comboAtributos);
        panel.getChildren().add(contenedorControles);
    }

    private boolean esAscendente() {
        return rbMenorAMayor.isSelected();
    }

    public Button crearBoton(String cad) {
        Button boton = new Button(cad);
        boton.setPrefWidth(300);
        boton.setPrefHeight(60);

        boton.setStyle("-fx-font-size: 18px; " + "-fx-font-weight: bold; " + "-fx-text-fill: #173B57; " + "-fx-background-color: #D6E8F5; " + "-fx-alignment: CENTER;");
        boton.setOnMouseEntered(e ->
                boton.setStyle("-fx-font-size: 18px; " + "-fx-font-weight: bold; " + "-fx-text-fill: #FFFFFF; " + "-fx-background-color: #245A81; " + "-fx-alignment: CENTER;")
        );

        boton.setOnMouseExited(e ->
                boton.setStyle("-fx-font-size: 18px; " + "-fx-font-weight: bold; " + "-fx-text-fill: #173B57; " + "-fx-background-color: #D6E8F5; " + "-fx-alignment: CENTER;")
        );

        return boton;
    }

    public Button crearBotonTriangular(boolean derecha) {

        Button boton = new Button();

        boton.setPrefSize(60, 60);
        boton.setMinSize(60, 60);
        boton.setMaxSize(60, 60);

        Polygon triangulo = derecha ? new Polygon(0.0, 0.0, 0.0, 60.0, 60.0, 30.0) : new Polygon(60.0, 0.0, 60.0, 60.0, 0.0, 30.0);

        boton.setShape(triangulo);
        boton.setStyle("-fx-background-color: #B8D5E9;");
        boton.setOnMouseEntered(e ->
                boton.setStyle("-fx-background-color: #245A81;")
        );
        boton.setOnMouseExited(e ->
                boton.setStyle("-fx-background-color: #B8D5E9;")
        );
        return boton;
    }

    public void botonTodasLasCartas(Pane panel) {

        Button botonTLC = crearBoton("Mostrar todas las cartas");
        botonTLC.setLayoutX(35);
        botonTLC.setLayoutY(80);
        panel.getChildren().add(botonTLC);

        botonTLC.setOnAction(event -> {
            coleccion.mostrarTodasLasCartas();
            CartasG.renderizarGaleria(coleccion.getNodoActual(), fondo.getPanelCartas());
        });
    }

    public void botonCartasPorNivel(Pane panel) {
        Button botonCPN = crearBoton("Mostrar por Nivel");
        botonCPN.setLayoutX(35);
        botonCPN.setLayoutY(170);
        panel.getChildren().add(botonCPN);

        botonCPN.setOnAction(event -> {
            Integer nivelSeleccionado = comboNivel.getValue();
            if (nivelSeleccionado != null) {
                coleccion.mostrarPorNivel(nivelSeleccionado);
            } else if (grupoOrden.getSelectedToggle() != null) {
                coleccion.ordenarPorNivel(esAscendente());
            }
            CartasG.renderizarGaleria(coleccion.getNodoActual(), fondo.getPanelCartas());
        });
    }

    public void botonCartasPorAtributo(Pane panel) {
        Button botonCPA = crearBoton("Mostrar cartas por atributo");
        botonCPA.setLayoutX(35);
        botonCPA.setLayoutY(260);
        panel.getChildren().add(botonCPA);

        botonCPA.setOnAction(event -> {
            String atributo = comboAtributos.getValue();
            if (atributo != null) {
                coleccion.mostrarPorAtributo(atributo);
                CartasG.renderizarGaleria(coleccion.getNodoActual(), fondo.getPanelCartas());
            }
        });
    }

    public void botonPorAtaque(Pane panel) {
        Button botonPuntosAtaque = crearBoton("Mostrar por puntos de ataque");
        botonPuntosAtaque.setLayoutX(35);
        botonPuntosAtaque.setLayoutY(350);
        panel.getChildren().add(botonPuntosAtaque);

        botonPuntosAtaque.setOnAction(event -> {
            coleccion.ordenarPorAtaque(esAscendente());
            CartasG.renderizarGaleria(coleccion.getNodoActual(), fondo.getPanelCartas());
        });
    }

    public void botonPorDefensa(Pane panel) {
        Button botonPuntosDefensa = crearBoton("Mostrar por puntos de defensa");
        botonPuntosDefensa.setLayoutX(35);
        botonPuntosDefensa.setLayoutY(440);
        panel.getChildren().add(botonPuntosDefensa);

        botonPuntosDefensa.setOnAction(event -> {
            coleccion.ordenarPorDefensa(esAscendente());
            CartasG.renderizarGaleria(coleccion.getNodoActual(), fondo.getPanelCartas());
        });
    }

    public void botonIzquierda(Pane panel) {
        Button botonIzq = crearBotonTriangular(false);
        botonIzq.setLayoutX(660);
        botonIzq.setLayoutY(750);
        panel.getChildren().add(botonIzq);

        botonIzq.setOnAction(event -> {
            coleccion.moverCartas(0);
            CartasG.renderizarGaleria(coleccion.getNodoActual(), fondo.getPanelCartas());
        });
    }

    public void botonDerecha(Pane panel) {
        Button botonDer = crearBotonTriangular(true);
        botonDer.setLayoutX(760);
        botonDer.setLayoutY(750);
        panel.getChildren().add(botonDer);

        botonDer.setOnAction(event -> {
            coleccion.moverCartas(1);
            CartasG.renderizarGaleria(coleccion.getNodoActual(), fondo.getPanelCartas());
        });
    }
}
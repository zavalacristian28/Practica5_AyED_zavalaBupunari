package com.example.practica5;

import javafx.scene.layout.Pane;

public class Fondo {
    private Pane panel;
    private Pane panelCartas;

    public Fondo(){
        panel=new Pane();
        panelCartas=new Pane();
        panelCartas.setLayoutX(550);
        panelCartas.setLayoutY(0);
        panelCartas.setPrefHeight(700);
        panel.getChildren().add(panelCartas);
    }
    public Pane getPanel(){
        return panel;
    }
    public Pane getPanelCartas(){
        return panelCartas;
    }
}

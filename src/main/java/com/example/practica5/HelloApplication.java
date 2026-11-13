package com.example.practica5;

import Logica.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Fondo fondo=new Fondo();
        Scene scene = new Scene(fondo.getPanel(), 1220, 840);
        stage.setTitle("Coleccion de cartas");
        Coleccion coleccion=new Coleccion();
        Botones boton=new Botones(coleccion,fondo);
        //coleccion.ordenarNivelAsc();
        //coleccion.ordenarNivelDesc();
        //coleccion.mostrarPorNivel(10);
        //coleccion.ordenarPorAtaqueAsc();
        //coleccion.ordenarPorAtaqueDesc();
        //coleccion.ordenarPorDefensaAsc();
       // coleccion.ordenarPorDefensaDesc();
        //coleccion.mostrarPorAtributo("Tierra");
        stage.setScene(scene);
        stage.show();
    }

}


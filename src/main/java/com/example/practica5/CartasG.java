
package com.example.practica5;

import Logica.Carta;
import Logica.Nodo;
import javafx.scene.layout.Pane;

public class CartasG {

    public static void renderizarGaleria(Nodo<Carta> nodoActual, Pane contenedor) {
        contenedor.getChildren().clear();
        if (nodoActual == null) {
            return;
        }

        Nodo<Carta> nodoIzquierda = nodoActual.getAnt();
        Nodo<Carta> nodoCentral = nodoActual;
        Nodo<Carta> nodoDerecha = nodoActual.getSig();

        CartaGrafica cartaIzquierda = new CartaGrafica(nodoIzquierda.getInfo(), nodoIzquierda.getInfo().getId());
        CartaGrafica cartaCentral = new CartaGrafica(nodoCentral.getInfo(), nodoCentral.getInfo().getId());
        CartaGrafica cartaDerecha = new CartaGrafica(nodoDerecha.getInfo(), nodoDerecha.getInfo().getId());

        cartaIzquierda.setScaleX(1.0);
        cartaIzquierda.setScaleY(1.0);
        cartaIzquierda.setOpacity(0.55);
        cartaIzquierda.getPanelInfo().setVisible(false);

        cartaCentral.setScaleX(1.5);
        cartaCentral.setScaleY(1.5);
        cartaCentral.setOpacity(1.0);
        cartaCentral.setViewOrder(-1.0);
        cartaCentral.getPanelInfo().setVisible(true);

        cartaDerecha.setScaleX(1.0);
        cartaDerecha.setScaleY(1.0);
        cartaDerecha.setOpacity(0.55);
        cartaDerecha.getPanelInfo().setVisible(false);

        double ancho = contenedor.getPrefWidth();
        double alto = contenedor.getPrefHeight();

        if (ancho <= 0) {
            ancho = 800;
        }

        if (alto <= 0) {
            alto = 400;
        }

        double anchoCarta = 300;
        double altoCarta = 290;

        double centroX = ancho / 2;
        double centroY = alto / 2;
        double separacion = 250;

        cartaIzquierda.setLayoutX(centroX - separacion - anchoCarta / 2);

        cartaIzquierda.setLayoutY(centroY - altoCarta / 2);

        cartaCentral.setLayoutX(centroX - anchoCarta / 2);

        cartaCentral.setLayoutY(centroY - altoCarta / 2);

        cartaDerecha.setLayoutX(centroX + separacion - anchoCarta / 2);

        cartaDerecha.setLayoutY(centroY - altoCarta / 2);

        contenedor.getChildren().addAll(cartaIzquierda, cartaDerecha, cartaCentral);

        cartaCentral.toFront();
    }
}

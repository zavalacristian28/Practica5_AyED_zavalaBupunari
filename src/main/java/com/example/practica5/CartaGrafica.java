
package com.example.practica5;

import Logica.Carta;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class CartaGrafica extends Pane {

    private Carta cartaLogica;
    private ImageView imageView;
    private Pane panelInfo;

    public CartaGrafica(Carta cartaLogica, int idImagen) {
        this.cartaLogica = cartaLogica;
        this.imageView = new ImageView();

        try {
            String ruta = "/com/example/practica5/CARTAS YuGiOh Puppillo/CARTAS YuGiOh/" + idImagen + ".png";
            java.io.InputStream stream = getClass().getResourceAsStream(ruta);
            if (stream != null) {
                Image img = new Image(stream);
                imageView.setImage(img);
            } else {
                System.out.println("No se encontro la imagen en la ruta: " + ruta);
            }

        } catch (Exception e) {
            System.out.println("Error al cargar la imagen: " + idImagen + ".png");
            e.printStackTrace();
        }

        imageView.setFitWidth(150);
        imageView.setFitHeight(220);
        imageView.setPreserveRatio(true);
        imageView.setSmooth(true);

        imageView.setLayoutX(0);
        imageView.setLayoutY(0);

        panelInfo = new Pane();
        panelInfo.setStyle("-fx-background-color: rgba(23, 59, 87, 0.85);" + "-fx-padding: 5px;" + "-fx-background-radius: 5px;");

        panelInfo.setPrefWidth(250);
        panelInfo.setPrefHeight(65);
        panelInfo.setLayoutX(0);
        panelInfo.setLayoutY(225);

        Label lblNombre = crearLabel(cartaLogica.getNombre(), "11px", "#FFFFFF", true);
        lblNombre.setLayoutX(5);
        lblNombre.setLayoutY(3);

        Label lblAtaDef;
        if (cartaLogica.getPuntosDefensa() == -1 && cartaLogica.getPuntosAtaque() == -1) {
            lblAtaDef = crearLabel("ATK: ? / DEF: ?", "10px", "#FFD700", true);

        } else if (cartaLogica.getPuntosDefensa() == -1) {
            lblAtaDef = crearLabel("ATK: " + cartaLogica.getPuntosAtaque() + " / DEF: ?", "10px", "#FFD700", true);

        } else if (cartaLogica.getPuntosAtaque() == -1) {
            lblAtaDef = crearLabel("ATK: ? / DEF: " + cartaLogica.getPuntosDefensa(), "10px", "#FFD700", true);
        } else {
            lblAtaDef = crearLabel("ATK: " + cartaLogica.getPuntosAtaque() + " / DEF: " + cartaLogica.getPuntosDefensa(), "10px", "#FFD700", true);
        }
        lblAtaDef.setLayoutX(5);
        lblAtaDef.setLayoutY(23);

        Label lblDetalles = crearLabel("Nivel: " + cartaLogica.getNivel() + " | " + cartaLogica.getAtributo() + " | " + cartaLogica.getTipo(), "9px", "#E0E0E0", false);
        lblDetalles.setLayoutX(5);
        lblDetalles.setLayoutY(43);
        panelInfo.getChildren().addAll(lblNombre, lblAtaDef, lblDetalles);
        this.getChildren().addAll(imageView, panelInfo);
        this.setPrefSize(300, 290);
    }

    private Label crearLabel(String texto, String tamañoFuente, String color, boolean negrita) {
        Label label = new Label(texto);
        String peso = negrita ? "bold" : "normal";
        label.setStyle("-fx-font-size: " + tamañoFuente + "; -fx-font-weight: " + peso + "; -fx-text-fill: " + color + ";");
        return label;
    }

    public Carta getCartaLogica() {
        return cartaLogica;
    }

    public Pane getPanelInfo() {
        return panelInfo;
    }
}

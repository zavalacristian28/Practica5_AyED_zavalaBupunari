package Logica;

public class Carta {
    private String nombre;
    private int puntosAtaque;
    private int puntosDefensa;
    private String atributo;//la bola de arriba ej oscuridad
    private int nivel; //estrellas
    private String tipo;

    public Carta(String nombre,int puntosAtaque,int puntosDefensa,String atributo,int nivel,String tipo){
        this.nombre=nombre;
        this.puntosAtaque=puntosAtaque;
        this.puntosDefensa=puntosDefensa;
        this.atributo=atributo;
        this.nivel=nivel;
        this.tipo=tipo;
    }

    public String getNombre(){return nombre;}
    public int getPuntosAtaque(){return puntosAtaque;}
    public int getPuntosDefensa(){return puntosDefensa;}
    public String getAtributo(){return atributo;}
    public String getTipo(){return tipo;}
    public int getNivel(){return nivel;}

    public String toString(){
        return "nombre: "+nombre +
                "puntos de ataque: "+puntosAtaque+
                "puntos de defensa: "+puntosDefensa+
                "atributo: "+atributo+
                "nivel: "+nivel+
                "tipo: "+tipo;

    }

}

package Logica;

public class Carta {
    private String nombre;
    private int puntosAtaque;
    private int puntosDefensa;
    private String atributo;//la bola de arriba ej oscuridad
    private int nivel; //estrellas
    private String tipo;
    private int id;

    public Carta(String nombre,int puntosAtaque,int puntosDefensa,String atributo,int nivel,String tipo,int id){
        this.nombre=nombre;
        this.puntosAtaque=puntosAtaque;
        this.puntosDefensa=puntosDefensa;
        this.atributo=atributo;
        this.nivel=nivel;
        this.tipo=tipo;
        this.id=id;
    }

    public String getNombre(){return nombre;}
    public int getPuntosAtaque(){return puntosAtaque;}
    public int getPuntosDefensa(){return puntosDefensa;}
    public String getAtributo(){return atributo;}
    public String getTipo(){return tipo;}
    public int getNivel(){return nivel;}
    public int getId(){
        return id;
    }

    public String toString(){
        return "nombre: "+nombre +
                "puntos de ataque: "+puntosAtaque+
                "puntos de defensa: "+puntosDefensa+
                "atributo: "+atributo+
                "nivel: "+nivel+
                "tipo: "+tipo;

    }

}

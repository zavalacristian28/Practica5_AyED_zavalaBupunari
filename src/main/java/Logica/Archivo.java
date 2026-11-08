package Logica;

import java.util.*;
import java.io.*;

public class Archivo {
    private ListaDobleCircular<Carta> LDC;
    private String rutaArchivo ="C:/Users/Zaval/IdeaProjects/Practica5_AyED/Practica5/src/main/resources/com/example/practica5/Datos_CArtas.csv";

    public Archivo(){
        LDC=new ListaDobleCircular<>();
    }
    public void cargarArchivo(){
        try(BufferedReader bf=new BufferedReader(new FileReader(rutaArchivo))){
            String linea;
            bf.readLine();
            while((linea=bf.readLine())!=null){

                if(linea.trim().isEmpty()){
                    continue;
                }

                String datosArchivo[]=linea.split(",", -1);

                if(datosArchivo.length != 6){
                    System.out.println("Error en la línea: " + linea);
                    continue;
                }

                Carta carta=new Carta(
                        datosArchivo[0].trim(),
                        Integer.parseInt(datosArchivo[1].trim()),
                        Integer.parseInt(datosArchivo[2].trim()),
                        datosArchivo[3].trim(),
                        Integer.parseInt(datosArchivo[4].trim()),
                        datosArchivo[5].trim()
                );

                LDC.insertaInicio(carta);
            }

        }catch(IOException e){
            System.out.println("Error al cargar el archivo");

        }
    }

    public void mostrar(){
        if(LDC.getInicio() == null){
            System.out.println("La lista está vacía");
            return;
        }

        Nodo<Carta> r = LDC.getInicio();
        int cont = 1;

        do {
            System.out.println("Carta: " + cont + " " + r.getInfo());
            cont++;
            r = r.getSig();

        } while(r != LDC.getInicio());

    }



}

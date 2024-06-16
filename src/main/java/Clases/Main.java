/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Clases;

import java.util.ArrayList;

/**
 *
 * @author Nataly
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //DEBUG, solo para pruebas
        Enfermedad enf = new Enfermedad();

        //1,1,1,1,1,1,0,0,0,0,0,0,0,0
        ArrayList<Integer> lista = new ArrayList<>();
        lista.add(1);
        lista.add(1);
        lista.add(1);
        lista.add(1);
        lista.add(1);
        lista.add(1);
        lista.add(0);
        lista.add(0);
        lista.add(0);
        lista.add(0);
        lista.add(0);
        lista.add(0);
        lista.add(0);
        lista.add(0);
        
        enf.inferencia(lista);
    }

}

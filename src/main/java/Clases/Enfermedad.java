/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 *
 * @author Nataly
 */
public class Enfermedad {

    private HashMap<Integer, String> listaEnfermedades;
    private HashMap<String, ArrayList<Integer>> listaSintomas;

    public Enfermedad() {
        this.listaEnfermedades = new HashMap<Integer, String>();
        this.listaSintomas = new HashMap<String, ArrayList<Integer>>();
        cargarEnfermedades();
        cargarSintomas();

    }

    private void cargarEnfermedades() {
        try {
            File archivo = new File("Enfermedades.txt");
            Scanner lector = new Scanner(archivo);
            while (lector.hasNextLine()) {
                String datos = lector.nextLine();
                String[] enfermedades = datos.split(",");
                int indice = 0;
                while (enfermedades.length > indice) {
                    this.listaEnfermedades.put(indice + 1, enfermedades[indice]);
                    indice++;
                }
            }
            lector.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    private void cargarSintomas() {
        try {
            File archivo = new File("Sintomas.txt");
            Scanner lector = new Scanner(archivo);
            int indiceE = 1;
            while (lector.hasNextLine()) {
                String datos = lector.nextLine();
                String[] sintomas = datos.split(",");
                int indiceS = 1;
                ArrayList<Integer> valoresSintomas = new ArrayList<>();
                for (int i = 0; i < indiceE; i++) {
                    valoresSintomas.add(Integer.valueOf(sintomas[indiceS]));
                    indiceS++;
                }
                /* while (sintomas.length > indiceS) {
                    valoresSintomas.add(Integer.valueOf(sintomas[indiceS]));
                    indiceS++;
                }*/
                this.listaSintomas.put(this.listaEnfermedades.get(indiceE), valoresSintomas);
                indiceE++;
            }
            lector.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public void print() {

        this.listaEnfermedades.forEach((key, value) -> System.out.println(key + " " + value));

    }

    public void print1() {

        for (String sint : this.listaSintomas.keySet()) {
            System.out.print(sint + ",");
            for (ArrayList<Integer> enf : this.listaSintomas.values()) {
                for (int s : enf) {
                    System.out.print(s + ",");
                }
                System.out.println("////");
            }
        }

    }
}

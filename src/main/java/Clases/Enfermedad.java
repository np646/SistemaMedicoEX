/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;

/**
 *
 * @author Nataly
 */
public class Enfermedad {

    private Map<Integer, String> listaEnfermedades;
    private Map<String, ArrayList<Integer>> listaSintomas;
    private int cantidadSintomas;

    public Enfermedad() {
        this.listaEnfermedades = new LinkedHashMap<>();
        this.listaSintomas = new LinkedHashMap<>();
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
                    this.listaEnfermedades.put(indice, enfermedades[indice]);
                    indice++;
                }
            }
            lector.close();
        } catch (FileNotFoundException e) {
            System.out.println("Ha ocurrido un error.");
            e.printStackTrace();
        }
    }

    private void cargarSintomas() {
        try {
            File archivo = new File("Sintomas.txt");
            Scanner lector = new Scanner(archivo);
            int indiceE = 0;
            while (lector.hasNextLine()) {
                String datos = lector.nextLine();
                String[] sintomas = datos.split(",");
                ArrayList<Integer> valoresSintomas = new ArrayList<>();
                this.cantidadSintomas = sintomas.length; // para saber cuántos sintomas hay
                for (int i = 0; i < sintomas.length; i++) {
                    valoresSintomas.add(Integer.valueOf(sintomas[i]));
                }
                this.listaSintomas.put(this.listaEnfermedades.get(indiceE), valoresSintomas);
                indiceE++;
            }
            lector.close();
        } catch (FileNotFoundException e) {
            System.out.println("Ha ocurrido un error.");
            e.printStackTrace();
        }
    }

    public void imprimirSintomas() {
        this.listaSintomas.forEach((key, value) -> System.out.println(key + " " + value));
    }

    public void imprimirEnfermedades() {
        this.listaEnfermedades.forEach((key, value) -> System.out.println(key + " " + value));
    }

    public String inferencia(ArrayList<Integer> sintomasUsuario) {
        Map<String, String> resultados = new LinkedHashMap<>();
        for (String key : this.listaSintomas.keySet()) {
            ArrayList<Integer> sintomasBDD = this.listaSintomas.get(key);
            int resp = contarAciertos(sintomasBDD, sintomasUsuario);
            String porcentaje = aPorcentaje(resp);
            resultados.put(key, porcentaje);
        }

        Map<String, String> resultadoOrdenado = ordenarPorValor(resultados);
        return imprimirResultados(resultadoOrdenado);
    }

    private int contarAciertos(ArrayList<Integer> sintomasBDD, ArrayList<Integer> sintomasUsuario) {
        int resultado = 0;
        int contador = 0;
        while (contador < sintomasBDD.size()) {
            if (Objects.equals(sintomasBDD.get(contador), sintomasUsuario.get(contador))) {
                resultado++;
            }
            contador++;
        }
        return resultado;
    }

    private Map<String, String> ordenarPorValor(Map<String, String> resultados) {
        List<Map.Entry<String, String>> lista = new ArrayList<>(resultados.entrySet());

        lista.sort((entry1, entry2) -> {
            double value1 = Double.parseDouble(entry1.getValue().replace("%", ""));
            double value2 = Double.parseDouble(entry2.getValue().replace("%", ""));
            return Double.compare(value2, value1); // Orden descendente
        });

        LinkedHashMap<String, String> listaOrdenada = new LinkedHashMap<>();
        for (Map.Entry<String, String> entry : lista) {
            listaOrdenada.put(entry.getKey(), entry.getValue());
        }
        return listaOrdenada;
    }

    private <K, V> String imprimirResultados(Map<K, V> map) {
        String resultado = "Resultados : \n";
        for (Map.Entry<K, V> entry : map.entrySet()) {
            //System.out.println(entry.getKey() + ": " + entry.getValue());
            resultado += entry.getKey() + ": " + entry.getValue() + "\n";
        }
        return resultado;
    }

    private String aPorcentaje(int valor) {
        int total = this.cantidadSintomas;
        double respuesta = ((double) valor * 100) / total;
        return String.format("%.2f", respuesta) + "%";
    }

}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fase.pkg1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class Fila {
    private static Map<String, Queue<String>> filas = new HashMap<>();

    public static void adicionaFila(String id, String[] participantes) {
        Queue<String> fila = new LinkedList<>(Arrays.asList(participantes));
        filas.put(id, fila);
    }

    public static void atendeFila(String id) {
        if (filas.containsKey(id)) {
            filas.get(id).poll();
        }
    }

    public static void chegou(String[] nomes) {
        for (String nome : nomes) {
            for (Queue<String> fila : filas.values()) {
                if (!fila.contains(nome)) {
                    fila.add(nome);
                    break;
                }
            }
        }
    }

    public static void desiste(String[] nomes) {
        for (String nome : nomes) {
            for (Queue<String> fila : filas.values()) {
                fila.remove(nome);
            }
        }
    }

    public static void imprime() {
        for (Map.Entry<String, Queue<String>> entry : filas.entrySet()) {
            System.out.print("#" + entry.getKey() + " [ ");
            for (String nome : entry.getValue()) {
                System.out.print(nome + " ");
            }
            System.out.println("]");
        }
    }

    public static void main(String[] args) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("entrada2.txt"));
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] tokens = linha.split(" ");
                switch (tokens[0]) {
                    case "criaFila:" ->                         {
                            String id = tokens[1];
                            String[] participantes = Arrays.copyOfRange(tokens, 2, tokens.length);
                            adicionaFila(id, participantes);
                        }
                    case "atendeFila:" ->                         {
                            String id = tokens[1];
                            atendeFila(id);
                        }
                    case "chegou:" ->                         {
                            String[] nomes = Arrays.copyOfRange(tokens, 1, tokens.length);
                            chegou(nomes);
                        }
                    case "desiste:" ->                         {
                            String[] nomes = Arrays.copyOfRange(tokens, 1, tokens.length);
                            desiste(nomes);
                        }
                    case "imprime:" -> imprime();
                    default -> {
                    }
                }
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
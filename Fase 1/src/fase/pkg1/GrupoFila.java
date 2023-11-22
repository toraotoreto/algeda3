/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fase.pkg1;
import static fase.pkg1.Fila.desiste;
import static fase.pkg1.Fila.imprime;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class GrupoFila {
    private static Map<String, Queue<String>> filas = new HashMap<>();
    private static Map<String, Map<String, Boolean>> grupos = new HashMap<>();

    public static void adicionaFila(String id, String[] participantes) {
        Queue<String> fila = new LinkedList<>(Arrays.asList(participantes));
        filas.put(id, fila);
    }

    public static void atendeFila(String id) {
        if (filas.containsKey(id)) {
            filas.get(id).poll();
        }
    }

    public static void adicionaGrupo(String id, String[] participantes) {
        Map<String, Boolean> grupo = new HashMap<>();
        for (String participante : participantes) {
            grupo.put(participante, true);
        }
        grupos.put(id, grupo);
    }

    public static boolean verificaExiste(String nome) {
        for (Map<String, Boolean> grupo : grupos.values()) {
            if (grupo.containsKey(nome)) {
                return true;
            }
        }
        return false;
    }

    public static boolean verificaConhece(String nome1, String nome2) {
        for (Map<String, Boolean> grupo : grupos.values()) {
            if (grupo.containsKey(nome1) && grupo.containsKey(nome2)) {
                return true;
            }
        }
        return false;
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

    public static void (String[] args) {
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
                    case "criaGrupo:" ->                         {
                            String id = tokens[1];
                            String[] participantes = Arrays.copyOfRange(tokens, 2, tokens.length);
                            adicionaGrupo(id, participantes);
                        }
                    case "existe:" ->                         {
                        String nome = tokens[1];
                        System.out.println(verificaExiste(nome) ? "[" + nome + "] existe" : "[" + nome + "] nao existe");
                    }
                    case "conhece:" -> {
                        String nome1 = tokens[1];
                        String nome2 = tokens[2];
                        System.out.println(verificaConhece(nome1, nome2) ? "[" + nome1 + "] conhece [" + nome2 + "]" : "[" + nome1 + "] nao conhece [" + nome2 + "]");
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
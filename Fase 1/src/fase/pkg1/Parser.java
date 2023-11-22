/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fase.pkg1;

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Parser {
    private Scanner leitor;

    public Parser(File file) {
        try {
            leitor = new Scanner(file);
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
    }

    public Parser(String input) {
        leitor = new Scanner(input);
    }

    public boolean hasNext() {
        return leitor.hasNext();
    }

    public String nextLine() {
        return leitor.nextLine();
    }

}
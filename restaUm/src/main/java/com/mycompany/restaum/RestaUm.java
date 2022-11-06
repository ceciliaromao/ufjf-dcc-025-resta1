/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package com.mycompany.restaum;
import java.util.Scanner;

/**
 *
 * @author mariacecilia
 */
public class RestaUm {
    
    public static char[][] initTab(){
        return new char[][] {
            {' ','0','1','2','3','4','5','6'},
            {'0',' ',' ','1','1','1',' ',' '},
            {'1',' ',' ','1','1','1',' ',' '},
            {'2','1','1','1','1','1','1','1'},
            {'3','1','1','1','0','1','1','1'},
            {'4','1','1','1','1','1','1','1'},
            {'5',' ',' ','1','1','1',' ',' '},
            {'6',' ',' ','1','1','1',' ',' '}            
        };
    }
    
    public static void displayTab(char[][] tab) {
        for(int i = 0; i < tab[0].length; i++){
            for (int j = 0; j < tab[0].length; j++){
                System.out.print(tab[i][j] + " ");
            }
            System.out.println(" ");
        }
    }
    
    public static boolean validInput(String input) {
        return (input.length() == 9 && 
                input.charAt(1) >= '0' && input.charAt(1) <= '6' &&
                input.charAt(3) >= '0' && input.charAt(3) <= '6' &&
                input.charAt(5) >= '0' && input.charAt(5) <= '6' &&
                input.charAt(7) >= '0' && input.charAt(7) <= '6');
    }
    
    public static boolean validateMove(char[][] tab, int pLine, int pColumn, int mLine, int mColumn) {
        return (tab[pLine][pColumn] == '1' && tab[mLine][mColumn] == '0' &&
                ((pLine == mLine        && mColumn == pColumn + 2   && tab[pLine][pColumn+1] == '1') || 
                 (pLine == mLine        && mColumn == pColumn - 2   && tab[pLine][pColumn-1] == '1') ||
                 (pColumn == mColumn    && mLine == pLine + 2       && tab[pLine+1][pColumn] == '1') || 
                 (pColumn == mColumn    && mColumn == pLine - 2     && tab[pLine-1][pColumn] == '1')));
    }
    
    public static boolean win(char[][] tab) {
        int count = 0;
        for(int i = 0; i < tab[0].length; i++){
            for (int j = 0; j < tab[0].length; j++){
                if(tab[i][j] == '1') count++;
            }
        }
        
        return count == 3;
    }
    
    public static void main(String[] args) {
        char[][] tab = initTab();
        Scanner key = new Scanner(System.in);
        String input;
        
        do {
            displayTab(tab);
            System.out.println("Informe sua jogada:");
            input = key.next();
            
            if(input.equals("reiniciar")) {
                tab = initTab();
            } else if (validInput(input)) {
                int pLine = Integer.parseInt(input.charAt(1)+"")+1;
                int pColumn = Integer.parseInt(input.charAt(3)+"")+1;
                int mLine = Integer.parseInt(input.charAt(5)+"")+1;
                int mColumn = Integer.parseInt(input.charAt(7)+"")+1;
                
                if(validateMove(tab, pLine, pColumn, mLine, mColumn)){
                    tab[pLine][pColumn] = '0';
                    tab[mLine][mColumn] = '1';
                    
                    if(pLine == mLine && mColumn == pColumn + 2) tab[pLine][pColumn+1] = '0';
                    if(pLine == mLine && mColumn == pColumn - 2) tab[pLine][pColumn-1] = '0';
                    if(pColumn == mColumn && mLine == pLine + 2 ) tab[pLine+1][pColumn] = '0';
                    if(pColumn == mColumn && mLine == pLine - 2 ) tab[pLine-1][pColumn] = '0';
                    
                    if (win(tab)) {
                        displayTab(tab);
                        System.out.println("Parabéns, vc ganhou!");
                    }
                } else {
                    System.out.println("Jogada Inválida! Informe nova jogada!");
                }
            } else if (!input.equals("sair")){
                System.out.println("Entrada Inválida. Informe sua jogada no formato '([linha da peça], [coluna da peça], [linha do espaço em branco], [coluna do espaço em branco])' ou 'reiniciar' ou 'sair', caso deseje.");
            } 
        } while (!input.equals("sair") && !win(tab));
            
    }
}

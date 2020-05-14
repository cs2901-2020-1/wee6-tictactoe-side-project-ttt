package com.company;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    static Scanner in;
    static Integer[] board;
    static Integer turn;

    public static void main(String[] args) {
        in = new Scanner(System.in);
        board = new Integer[9];
        turn = 10;
        String winner = null;
        populateEmptyBoard();

        printBoard();
        System.out.println("Turno jugador X. Inserta el numero de casilla: ");

        while (winner == null) {
            int numInput;
            try {
                numInput = in.nextInt();
                if (!(numInput > 0 && numInput <= 9)) {
                    System.out.println("Error. Intenta nuevamente: ");
                    continue;
                }
            } catch (InputMismatchException e) {
                System.out.println("Error. Intenta nuevamente: ");
                continue;
            }
            if (board[numInput-1] == numInput) {
                board[numInput - 1] = turn;
                turn = -turn;
                printBoard();
                winner = checkWinner();
            } else {
                System.out.println("Casilla llena. Intenta nuevamente: ");
            }
        }
        if (winner.equalsIgnoreCase("draw")) {
            System.out.println("Tablero lleno. Empate.");
        } else {
            System.out.println("Ganador jugador " + winner + ". Fin del juego.");
        }
    }

    static String checkWinner() {
        for (int a = 0; a < 8; a++) {
            Integer line = null;
            switch (a) {
                case 0:
                    line = board[0] + board[1] + board[2];
                    break;
                case 1:
                    line = board[3] + board[4] + board[5];
                    break;
                case 2:
                    line = board[6] + board[7] + board[8];
                    break;
                case 3:
                    line = board[0] + board[3] + board[6];
                    break;
                case 4:
                    line = board[1] + board[4] + board[7];
                    break;
                case 5:
                    line = board[2] + board[5] + board[8];
                    break;
                case 6:
                    line = board[0] + board[4] + board[8];
                    break;
                case 7:
                    line = board[2] + board[4] + board[6];
                    break;
            }
            if (line == 30) {
                return "X";
            } else if (line == -30) {
                return "O";
            }
        }

        for (int a = 0; a < 9; a++) {
            if (board[a] != 0) {
                if (board[a] == 10) {
                    System.out.println("Turno jugador X. Inserta el numero de casilla:");
                } else {
                    System.out.println("Turno jugador O. Inserta el numero de casilla:");
                }
                return null;
            }
        }
        return "draw";
    }

    static void printBoard() {
        Integer value;
        for(int i = 0; i < 3; i++)  {
            System.out.print("| ");
            for(int j = 0; j < 3; j++) {
                value = board[3 * i + j];
                switch (value) {
                    case 10:
                        System.out.print("X");
                        break;
                    case -10:
                        System.out.print("O");
                        break;
                    default:
                        System.out.print(value);
                        break;
                }
                System.out.print(" | ");
            }
            System.out.println();
        }
    }

    static void populateEmptyBoard() {
        for (int a = 0; a < 9; a++) {
            board[a] = a + 1;
        }
    }
}

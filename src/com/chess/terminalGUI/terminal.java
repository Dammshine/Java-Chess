package com.chess.terminalGUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Collection;
import java.util.Scanner;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import javax.print.event.PrintEvent;
import javax.swing.Timer;
import javax.swing.event.AncestorListener;


import com.chess.engine.board.Board;
import com.chess.engine.board.Move;
import com.chess.engine.pieces.Piece;

public class terminal {
    public terminal() {
        throw new RuntimeException("Can not run this class");
    }

    public static void graphOutput(Board board) {
        // Magical code act as c++ cls
        System.out.print("\033[H\033[2J");

        System.out.print("  ");
        for (int i = 0; i < 8; i++) {
            System.out.print((char) ('A' + i));
            System.out.print("   ");
        }
        System.out.println();

        for (int i = 0; i < 8; i++) {
            System.out.print(8 - i);
            for (int j = 0; j < 8; j++) {
                Piece piece = board.getTile(i, j);
                if (piece == null) {
                    System.out.printf("    ");
                } else {
                    System.out.print(" " + piece + " ");
                }
            }
            System.out.println();
        }
    }

    public static void checkOutExistMoves(Board board) {
        graphOutput(board);
        Collection<Move> moves = board.getMoves();
        System.out.println(moves.size());
        for (Move move : moves) {
            System.out.println(move);
        }
    }

    public static void checkOutExistMoves(Board board, int grid, int tile) {
        graphOutput(board);
        Collection<Move> moves = board.getMoves(grid, tile);
        System.out.println(moves.size());
        for (Move move : moves) {
            System.out.println(move);
        }
    }

    public static Move commandLinePrompt(Board board) {
        Scanner myObj = new Scanner(System.in);
        
        while (true) {
            System.out.println("\nEnter the your operation:\n\t 1. move\n\t 2. hint\n\t 3. simulate\n\t 4. simulate game");
            String command = myObj.nextLine();
            if (command.equals("hint")) {
                System.out.println("Enter the options:\n\t 1. checkAll\n\t 2. checkCoordinate");
                String option = myObj.nextLine();
                if (option == "checkAll") checkOutExistMoves(board);
                else {
                    System.out.println("Enter the coordinate:");
                    option = myObj.nextLine();
                    int tile = option.charAt(0) - 'A';
                    int grid = 7 - (option.charAt(1) - '1');
                    checkOutExistMoves(board, grid, tile);
                }
                
            } else if (command.equals("move")) {
                break;
            } else if (command.equals("simulate")) {
                board.selfSimulate(false);
                graphOutput(board);
            } else if (command.equals("simulate game")) {
                Timer timer = new Timer(100, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        graphOutput(board);
                        board.selfSimulate(true);
                    }
                });

                timer.setRepeats(true); // Only execute once
                timer.start(); // Go go go!
            }
        }
        


        System.out.println("\nEnter the coordinate you want to move:");
        String piece = myObj.nextLine();
        int tile = piece.charAt(0) - 'A';
        int grid = 7 - (piece.charAt(1) - '1');

        Piece pieceMove = board.getTile(grid, tile);

        System.out.println("Enter the coordinate you want to move to:");
        piece = myObj.nextLine();
        tile = (piece.charAt(0) - 'A');
        grid = 7 - (piece.charAt(1) - '1');
        Move moveObj = new Move(pieceMove, new int[]{grid, tile});
        return moveObj;
    }


    public static void main(String[] args) {
        
        Board board = Board.initializeBoard();
        while (true) {
            graphOutput(board);
            Move nextMove = commandLinePrompt(board);
            board.simulate(nextMove);
        }
    }
}

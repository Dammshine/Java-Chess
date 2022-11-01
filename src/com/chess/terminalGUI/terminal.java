package com.chess.terminalGUI;

import java.util.Scanner;

import com.chess.engine.board.Board;
import com.chess.engine.board.Move;
import com.chess.engine.pieces.Piece;

public class terminal {
    public terminal() {
        throw new RuntimeException("Can not run this class");
    }

    public static void graphOutput(Board board) {
        System.out.flush();  
        System.out.print("  ");
        for (int i = 0; i < 8; i++) {
            System.out.print('H' - i);
            System.out.print("   ");
        }

        for (int i = 0; i < 8; i++) {
            System.out.print(i);
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

    public static Move commandLinePrompt(Board board) {
        Scanner myObj = new Scanner(System.in);
        System.out.println("\nEnter the coordinate you want to move:");
        String piece = myObj.nextLine();
        int tile = piece.charAt(0) - 'H';
        int grid =  piece.charAt(1);

        Piece pieceMove = board.getTile(grid, tile);

        System.out.println("Enter the coordinate you want to move to:");
        piece = myObj.nextLine();
        tile = piece.charAt(0) - 'H';
        grid = piece.charAt(1);
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

package com.chess.terminalGUI;

import java.io.IOException;
import java.util.Collection;
import java.util.Scanner;

import javax.print.event.PrintEvent;

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

    public static void checkOutExistMOves(Board board) {
        graphOutput(board);
        Collection<Move> moves = board.getMoves();
        System.out.println(moves.size());
        for (Move move : moves) {
            System.out.println(move.getMovePiece() + 
            " [" + (char) (move.getMovePiece().getPiecePosition()[1] + 'A') + "," 
            + (8 - move.getMovePiece().getPiecePosition()[0]) + "]" + "=>" +
            " [" + (char) (move.getMoveCol() + 'A') + "," 
            + (8 - move.getMoveRow()) + "]");
        }
    }

    public static Move commandLinePrompt(Board board) {
        Scanner myObj = new Scanner(System.in);
        
        while (true) {
            System.out.println("\nEnter the your operation:\n\t 1. move\n\t 2. hint");
            String command = myObj.nextLine();
            if (command.equals("hint")) {
                checkOutExistMOves(board);
            } else if (command.equals("move")) {
                break;
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

package com.chess.engine.board;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import com.chess.deprecate.Tile;
import com.chess.engine.pieces.Bishop;
import com.chess.engine.pieces.King;
import com.chess.engine.pieces.Knight;
import com.chess.engine.pieces.Pawn;
import com.chess.engine.pieces.Piece;
import com.chess.engine.pieces.Queen;
import com.chess.engine.pieces.Rock;
import com.chess.engine.pieces.Piece.AllianceType;

public class Board {
    private Collection<Piece> whitePieces;
    private Collection<Piece> blackPieces;
    private Piece[][] board;
    private AllianceType nextMove;
    
    public static Board initializeBoard() {
        return new Board();
    }
    
    /**
     * 
     */
    private Board() {
        // Piece that is null indicate it's an empty tile
        this.board = new Piece[8][8];
        
        // Black Layout
        this.board[0][0] = new Rock(0, 0,   AllianceType.BLACK);
        this.board[0][1] = new Knight(0, 1, AllianceType.BLACK);
        this.board[0][2] = new Bishop(0, 2, AllianceType.BLACK);
        this.board[0][3] = new Queen(0, 3,  AllianceType.BLACK);
        this.board[0][4] = new King(0, 4,   AllianceType.BLACK);
        this.board[0][5] = new Bishop(0, 5, AllianceType.BLACK);
        this.board[0][6] = new Knight(0, 6, AllianceType.BLACK);
        this.board[0][7] = new Rock(0, 7,   AllianceType.BLACK);

        this.board[1][0] = new Pawn(1, 0,  AllianceType.BLACK);
        this.board[1][1] = new Pawn(1, 1,  AllianceType.BLACK);
        this.board[1][2] = new Pawn(1, 2, AllianceType.BLACK);
        this.board[1][3] = new Pawn(1, 3, AllianceType.BLACK);
        this.board[1][4] = new Pawn(1, 4, AllianceType.BLACK);
        this.board[1][5] = new Pawn(1, 5, AllianceType.BLACK);
        this.board[1][6] = new Pawn(1, 6, AllianceType.BLACK);
        this.board[1][7] = new Pawn(1, 7, AllianceType.BLACK);

        // White Layout
        this.board[6][0] = new Pawn(6, 0, AllianceType.WHITE);
        this.board[6][1] = new Pawn(6, 1, AllianceType.WHITE);
        this.board[6][2] = new Pawn(6, 2, AllianceType.WHITE);
        this.board[6][3] = new Pawn(6, 3, AllianceType.WHITE);
        this.board[6][4] = new Pawn(6, 4, AllianceType.WHITE);
        this.board[6][5] = new Pawn(6, 5, AllianceType.WHITE);
        this.board[6][6] = new Pawn(6, 6, AllianceType.WHITE);
        this.board[6][7] = new Pawn(6, 7, AllianceType.WHITE);

        this.board[7][0] = new Rock(7, 0,   AllianceType.WHITE);
        this.board[7][1] = new Knight(7, 1, AllianceType.WHITE);
        this.board[7][2] = new Bishop(7, 2, AllianceType.WHITE);
        this.board[7][3] = new Queen(7, 3,  AllianceType.WHITE);
        this.board[7][4] = new King(7, 4,   AllianceType.WHITE);
        this.board[7][5] = new Bishop(7, 5, AllianceType.WHITE);
        this.board[7][6] = new Knight(7, 6, AllianceType.WHITE);
        this.board[7][7] = new Rock(7, 7,   AllianceType.WHITE);
    }

    /**
     * 
     * @param canidateCoordinate
     * @return
     */
    public void simulate(Move move) {
        /**
         * Assume move here is legel move
         */
        int row = move.getMoveRow();
        int col = move.getMoveCol();
        Piece piece = move.getMovePiece();
        int currRow = piece.getPiecePosition()[0];
        int currCol = piece.getPiecePosition()[1];

        // Normal major move
        if (board[row][col] == null) {
            board[row][col] = board[currRow][currCol];
            board[currRow][currCol] = null;
        } else {
            // Capture that pieces
            board[row][col] = null;
            board[row][col] = board[currRow][currCol];
            board[currRow][currCol] = null;
        }
    }

    /*
     * TODO
     * - Return the Tile class at that slot
     */
    public Piece getTile(int row, int col) {
        return board[row][col];
    }

    /*
     * TODO
     * - Check if the tile is occupied by ally
     * - Check if this move cuase checkmate
     */
    public boolean isLegelMove(Piece piece, int[] destination) {
        return false;
    }
}

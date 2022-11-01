package com.chess.engine.board;

import com.chess.engine.pieces.Piece;

public class Move {
    final Piece movePiece;
    final int moveRow;
    final int moveCol;
    public Move(Piece piece, int[] piecePosition) {
        this.movePiece = piece;
        this.moveRow = piecePosition[0];
        this.moveCol = piecePosition[1];
    }

    public int getMoveCol() {
        return moveCol;
    }
    public int getMoveRow() {
        return moveRow;
    }
    public Piece getMovePiece() {
        return movePiece;
    }
}

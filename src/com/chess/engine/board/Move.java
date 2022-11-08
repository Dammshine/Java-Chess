package com.chess.engine.board;

import com.chess.engine.pieces.Piece;
import com.chess.engine.pieces.Piece.AllianceType;

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
    public AllianceType getMoveMaker() {
        return movePiece.getAllianceType();
    }

    @Override
    public String toString() {
        return this.getMovePiece() + 
            " [" + (char) (this.getMovePiece().getPiecePosition()[1] + 'A') + "," 
            + (8 - this.getMovePiece().getPiecePosition()[0]) + "]" + "=>" +
            " [" + (char) (this.getMoveCol() + 'A') + "," 
            + (8 - this.getMoveRow()) + "]";
    }
}

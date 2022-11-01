package com.chess.engine.pieces;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.chess.engine.board.Board;
import com.chess.engine.board.BoardUltis;
import com.chess.engine.board.Move;

public abstract class Piece {
    public enum PieceType {
        /**
         * The default Pieces
         */
        PAWN(100, "P"),
        KNIGHT(300, "N"),
        BISHOP(330, "B"),
        ROCK(500, "R"),
        QUEEN(900, "Q"),
        KING(10000, "K");
        
        private final int value;
        private final String pieceName;
        PieceType(final int val, final String pieceName) {
            this.value = val;
            this.pieceName = pieceName;
        }

        public int getPieceValue() {
            return this.value;
        }
        
        @Override
        public String toString() {
            return this.pieceName;
        }
    }

    public enum AllianceType {
        BLACK,
        WHITE,
    }

    final PieceType pieceType;
    final AllianceType allianceType;
    final int pieceRow;
    final int pieceCol;
    private final boolean isFirstMove;
    Piece(final PieceType type,
          final int pieceRow,
          final int pieceCol,
          final boolean isFirstMove,
          final AllianceType allianceType) {
        this.pieceType = type;
        this.pieceRow = pieceRow;
        this.pieceCol = pieceCol;
        this.isFirstMove = isFirstMove;
        this.allianceType = allianceType;
    }

    public PieceType getPieceType() {
        return pieceType;
    }
    public int[] getPiecePosition() {
        return new int[]{pieceRow, pieceCol};
    }
    
    public boolean isFirstMove() {
        return isFirstMove;
    }

    public static Piece getPiece() {
        return null;
    }
    
    /**
     * A common interface for all pieces, passes own possible moves according to type,
     * Exclude pawn since it got some unique mechanism (attack and move differs, also can promote, etc)
     * return a list that filters is those possible move are possible 
     * @param board
     * @param canidateMoves
     * @return
     */
    static public Collection<Move> calcualteMovesHelper(final Piece piece, final Board board, final int[][] CANDIDATE_MOVE_COORDINATES) {
        final List<Move> legelMoves = new ArrayList<>();
        for (final int[] move : CANDIDATE_MOVE_COORDINATES) {
            int[] pos = piece.getPiecePosition();
            pos[0] += move[0];
            pos[1] += move[1];
            
            if (!BoardUltis.isValidCoor(pos)) continue;

            // Board will determine if it's a valid move
            if (!board.isLegelMove(piece, pos)) continue;
            legelMoves.add(new Move(piece, pos));
        }
        return legelMoves;
    }

    public abstract Collection<Move> calculateLegalMoves(final Board board);
}

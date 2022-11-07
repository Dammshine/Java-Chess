package com.chess.engine.pieces;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.chess.engine.board.Board;
import com.chess.engine.board.BoardUltis;
import com.chess.engine.board.Move;

public class Bishop extends Piece {
    public Bishop(int pieceRow, int pieceCol, AllianceType allianceType) {
        super(PieceType.BISHOP, pieceRow, pieceCol, true, allianceType);
    }

    @Override
    public Collection<Move> calculateLegalMoves(Board board) {
        final List<Move> legelMoves = new ArrayList<>();
        int row = super.getPiecePosition()[0];
        int col = super.getPiecePosition()[1];
        
        // Four possible direction, 
        // - can be by friendly piece, 
        // - or by capture one eneny piece
        for (int i = 0; i < BoardUltis.NUM_TILE; i++) {
            int[] checkPos = new int[]{row + i, col + i};
            if (!BoardUltis.isValidCoor(new int[]{row, col}) || !board.isLegelMove(this, new int[]{row, col})) break;
            else legelMoves.add(new Move(this, checkPos));

            if (board.getTile(checkPos[0], checkPos[1]) != null) break;
        }

        for (int i = 0; i < BoardUltis.NUM_TILE; i++) {
            int[] checkPos = new int[]{row + i, col - i};
            if (!BoardUltis.isValidCoor(new int[]{row, col}) || !board.isLegelMove(this, new int[]{row, col})) break;
            else legelMoves.add(new Move(this, checkPos));

            if (board.getTile(checkPos[0], checkPos[1]) != null) break;
        }

        for (int i = 0; i < BoardUltis.NUM_TILE; i++) {
            int[] checkPos = new int[]{row - i, col - i};
            if (!BoardUltis.isValidCoor(new int[]{row, col}) || !board.isLegelMove(this, new int[]{row, col})) break;
            else legelMoves.add(new Move(this, checkPos));

            if (board.getTile(checkPos[0], checkPos[1]) != null) break;
        }

        for (int i = 0; i < BoardUltis.NUM_TILE; i++) {
            int[] checkPos = new int[]{row - i, col + i};
            if (!BoardUltis.isValidCoor(new int[]{row, col}) || !board.isLegelMove(this, new int[]{row, col})) break;
            else legelMoves.add(new Move(this, checkPos));

            if (board.getTile(checkPos[0], checkPos[1]) != null) break;
        }
        return legelMoves;
    }
}

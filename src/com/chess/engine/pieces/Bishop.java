package com.chess.engine.pieces;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.chess.engine.board.Board;
import com.chess.engine.board.BoardUltis;
import com.chess.engine.board.Move;

public class Bishop extends Piece {
    Bishop(int pieceRow, int pieceCol, AllianceType allianceType) {
        super(PieceType.BISHOP, pieceRow, pieceCol, true, allianceType);
    }

    static final int[][] CANDIDATE_MOVE_COORDINATES = {
        { 1, 1}, { 2, 2}, { 3, 3}, { 4, 4}, { 5, 5}, { 6, 6}, { 7, 7},
        { 1,-1}, { 2,-2}, { 3,-3}, { 4,-4}, { 5,-5}, { 6,-6}, { 7,-7},
        {-1, 1}, {-2, 2}, {-3, 3}, {-4, 4}, {-5, 5}, {-6, 6}, {-7, 7},
        {-1,-1}, {-2,-2}, {-3,-3}, {-4,-4}, {-5,-5}, {-6,-6}, {-7,-7}
    };

    @Override
    public Collection<Move> calculateLegalMoves(Board board) {
        return Piece.calcualteMovesHelper(this, board, CANDIDATE_MOVE_COORDINATES);
    }
}

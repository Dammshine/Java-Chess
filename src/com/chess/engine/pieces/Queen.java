package com.chess.engine.pieces;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.chess.engine.board.Board;
import com.chess.engine.board.BoardUltis;
import com.chess.engine.board.Move;

public class Queen extends Piece {
    Queen(int pieceRow, int pieceCol, AllianceType allianceType) {
        super(PieceType.QUEEN, pieceRow, pieceCol, true, allianceType);
    }
    
    static final int[][] CANDIDATE_MOVE_COORDINATES = {
        { 0, 1}, { 0, 2}, { 0, 3}, { 0, 4}, { 0, 5}, { 0, 6}, { 0, 7},
        { 0,-1}, { 0,-2}, { 0,-3}, { 0,-4}, { 0,-5}, { 0,-6}, { 0,-7},
        { 1, 0}, { 2, 0}, { 3, 0}, { 4, 0}, { 5, 0}, { 6, 0}, { 7, 0},
        {-1, 0}, {-2, 0}, {-3, 0}, {-4, 0}, {-5, 0}, {-6, 0}, {-7, 0},
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

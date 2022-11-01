package com.chess.engine.pieces;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.chess.engine.board.Board;
import com.chess.engine.board.BoardUltis;
import com.chess.engine.board.Move;

public class Rock extends Piece {
    Rock(int pieceRow, int pieceCol, AllianceType allianceType) {
        super(PieceType.ROCK, pieceRow, pieceCol, true, allianceType);
    }

    static final int[][] CANDIDATE_MOVE_COORDINATES = {
        { 0, 1}, { 0, 2}, { 0, 3}, { 0, 4}, { 0, 5}, { 0, 6}, { 0, 7},
        { 0,-1}, { 0,-2}, { 0,-3}, { 0,-4}, { 0,-5}, { 0,-6}, { 0,-7},
        { 1, 0}, { 2, 0}, { 3, 0}, { 4, 0}, { 5, 0}, { 6, 0}, { 7, 0},
        {-1, 0}, {-2, 0}, {-3, 0}, {-4, 0}, {-5, 0}, {-6, 0}, {-7, 0}
    };

    @Override
    public Collection<Move> calculateLegalMoves(Board board) {
        return Piece.calcualteMovesHelper(this, board, CANDIDATE_MOVE_COORDINATES);
    }
}

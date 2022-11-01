package com.chess.engine.pieces;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.chess.engine.board.Board;
import com.chess.engine.board.BoardUltis;
import com.chess.engine.board.Move;
import com.chess.engine.board.Tile;

public class Knight extends Piece{
    static final int[][] CANDIDATE_MOVE_COORDINATES = {{1,2}, {1,-2}, {2,1}, {2,-1}, {-1,2}, {-1,-2}, {-2,1}, {-2,-1}};


    Knight(final int pieceRow, final int pieceCol, final AllianceType allianceType) {
        super(PieceType.KNIGHT, pieceRow, pieceCol, true, allianceType);
    }

    @Override
    public Collection<Move> calculateLegalMoves(final Board board) {
        return Piece.calcualteMovesHelper(this, board, CANDIDATE_MOVE_COORDINATES);
    }
}

package com.chess.engine.pieces;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.chess.engine.board.Board;
import com.chess.engine.board.Move;

public class King extends Piece {
    King(int pieceRow, int pieceCol, AllianceType allianceType) {
        super(PieceType.KING, pieceRow, pieceCol, true, allianceType);
    }

    static final int[][] CANDIDATE_MOVE_COORDINATES = {
        {1, 0}, {0, 1 }, {-1, 0}, {0, -1 },
        {1, 1}, {1, -1}, {-1, 1}, {-1, -1}
    };

    @Override
    public Collection<Move> calculateLegalMoves(Board board) {
        return Piece.calcualteMovesHelper(this, board, CANDIDATE_MOVE_COORDINATES);
    }
}

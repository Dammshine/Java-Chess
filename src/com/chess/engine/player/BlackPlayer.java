package com.chess.engine.player;

import java.util.Collection;

import com.chess.engine.board.Board;
import com.chess.engine.board.Move;
import com.chess.engine.pieces.Piece;
import com.chess.engine.pieces.Piece.AllianceType;

public class BlackPlayer extends Player {
    
    public BlackPlayer(Board board, Collection<Move> ownMoves, Collection<Move> opponentMoves) {
        super(board, ownMoves, opponentMoves);
    }

    @Override
    Collection<Piece> getActivePieces() {
        return board.getBlackPieces();
    }

    @Override
    AllianceType getAllianceType() {
        return AllianceType.BLACK;
    }
}

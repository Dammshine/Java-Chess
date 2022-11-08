package com.chess.engine.player;

import java.util.Collection;

import com.chess.engine.board.Board;
import com.chess.engine.board.Move;
import com.chess.engine.pieces.Piece;
import com.chess.engine.pieces.Piece.AllianceType;

public class WhitePlayer extends Player {

    public WhitePlayer(Board board, Collection<Move> ownMoves, Collection<Move> opponentMoves) {
        super(board, ownMoves, opponentMoves);
    }
    
    @Override
    Collection<Piece> getActivePieces() {
        return board.getWhitePieces();
    }

    @Override
    AllianceType getAllianceType() {
        return AllianceType.WHITE;
    }
}

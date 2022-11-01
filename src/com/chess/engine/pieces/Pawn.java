package com.chess.engine.pieces;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.chess.engine.board.Board;
import com.chess.engine.board.BoardUltis;
import com.chess.engine.board.Move;

public class Pawn extends Piece {
    final int allianceFactor;
    Pawn(int pieceRow, int pieceCol, AllianceType allianceType) {
        super(PieceType.PAWN, pieceRow, pieceCol, true, allianceType);
        if (allianceType == AllianceType.WHITE) allianceFactor = 1;
        else allianceFactor = -1;
    }

    static final int[][] CANDIDATE_MOVE_COORDINATES = {
        {1, 0}, {1, 1}, {1, -1}
    };
    
    @Override
    public Collection<Move> calculateLegalMoves(Board board) {
        // TODO
        // - First move can be a jump or a move, can only move forward in one direction
        // - Attack and move are different, 
        // - Can promote [Ignore in this iteration!!!]
        // - en passant  [Ignore in this iteration!!!]

        final List<Move> legelMoves = new ArrayList<>();
        if (super.isFirstMove()) {
            int[] pos = this.getPiecePosition();
            pos[0] += 2 * allianceFactor;
            
            if (!BoardUltis.isValidCoor(pos)) ;
            // Board will determine if it's a valid move
            else if (!board.isLegelMove(this, pos)) ;
            else legelMoves.add(new Move(this, pos));
        }
        
        for (final int[] move : CANDIDATE_MOVE_COORDINATES) {
            int[] pos = this.getPiecePosition();
            pos[0] += (move[0] * allianceFactor);
            pos[1] += move[1];
            
            if (!BoardUltis.isValidCoor(pos)) continue;

            // Board will determine if it's a valid move
            if (!board.isLegelMove(this, pos)) continue;
            legelMoves.add(new Move(this, pos));
        }
        return legelMoves;
    }
}

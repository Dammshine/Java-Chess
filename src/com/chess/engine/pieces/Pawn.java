package com.chess.engine.pieces;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.chess.engine.board.Board;
import com.chess.engine.board.BoardUltis;
import com.chess.engine.board.Move;

public class Pawn extends Piece {
    final int allianceFactor;
    public Pawn(int pieceRow, int pieceCol, AllianceType allianceType) {
        super(PieceType.PAWN, pieceRow, pieceCol, true, allianceType);
        allianceFactor = BoardUltis.getAllianceFactor(this);
    }

    static final int[][] CANDIDATE_MOVE_COORDINATES = {
        {1, 1}, {1, -1}
    };
    
    @Override
    public Collection<Move> calculateLegalMoves(Board board) {
        final List<Move> legelMoves = new ArrayList<>();
        
        // Use flag to determine if pawn can jump
        boolean flag = true;
        int[] pos = this.getPiecePosition();
        pos[0] += allianceFactor;
        if (!BoardUltis.isValidCoor(pos)) flag = false;
        // Board will determine if it's a valid move
        else if (!board.isLegelMove(this, pos)) flag = false;
        else legelMoves.add(new Move(this, pos));
        
        // Get jump
        if (super.isFirstMove() && flag) {
            pos = this.getPiecePosition();
            pos[0] += 2 * allianceFactor;

            if (!BoardUltis.isValidCoor(pos)) ;
            // Board will determine if it's a valid move
            else if (!board.isLegelMove(this, pos)) ;
            else legelMoves.add(new Move(this, pos));
        }

        for (final int[] move : CANDIDATE_MOVE_COORDINATES) {
            pos = this.getPiecePosition();
            pos[0] += (move[0] * allianceFactor);
            pos[1] += move[1];
            
            if (!BoardUltis.isValidCoor(pos)) continue;

            // Board will determine if it's a valid move
            if (!board.isLegelAttack(this, pos)) continue;
            legelMoves.add(new Move(this, pos));
        }
        
        return legelMoves;
    }
}

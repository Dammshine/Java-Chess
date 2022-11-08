package com.chess.engine.player;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.chess.engine.board.Board;
import com.chess.engine.board.Move;
import com.chess.engine.pieces.Piece;
import com.chess.engine.pieces.Piece.AllianceType;
import com.chess.engine.pieces.Piece.PieceType;

public class MoveAnalyzer {
    /**
     * Return the status of board, by next corresponding move
     * @param board
     * @param nextMove
     * @return
     */
    public static MoveStatus analysisMove(Board board, Move nextMove) {
        // Create an imaginary board to simulate this situation
        Board duplicate = board.duplicate();
        duplicate.simulate(nextMove);
        
        int[] kingPos = getKingPosition(board);
        // Pieces
        Collection<Move> attackMoves = duplicate.getMoves();
        for (Move move : attackMoves) {
            if (move.getMoveRow() == kingPos[0] &&
            move.getMoveCol() == kingPos[1]) return MoveStatus.CHECK;
        }

        return MoveStatus.DONE;
    }

    /**
     * Return moves that attak piece position
     * @param piecePosition
     * @param opponentMove
     * @return
     */
    public static Collection<Move> calculateAttackOnTile(Board board, int[] piecePosition, Collection<Move> opponentMove) {
        List<Move> attackingMoves = new ArrayList<Move>();
        for (Move move : opponentMove) {
            if (move.getMoveRow() == piecePosition[0] && 
            move.getMoveCol() == piecePosition[1]) {
                attackingMoves.add(move);
            }
        }
        return attackingMoves;
    }

    public static int[] getKingPosition(Board board) {
        Collection<Piece> pieces = null;
        if (board.getNextMove() == AllianceType.WHITE) {
            pieces = board.getBlackPieces();
        } else {
            pieces = board.getWhitePieces();
        }


        for (Piece piece : pieces) {
            if (piece.getPieceType() == PieceType.KING) return piece.getPiecePosition();
        }
        return null;
    }
}

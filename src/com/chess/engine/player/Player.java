package com.chess.engine.player;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.chess.engine.board.Board;
import com.chess.engine.board.Move;
import com.chess.engine.pieces.King;
import com.chess.engine.pieces.Piece;
import com.chess.engine.pieces.Piece.AllianceType;
import com.chess.engine.pieces.Piece.PieceType;

abstract public class Player {
    protected final Board board;
    protected final King playerKing;
    protected final Collection<Move> legalMove;
    protected final Collection<Move> opponentMove;
    private final boolean isInCheck;

    // A strategy pattern here
    protected PlayerBody body;

    Player(final Board board,
        final Collection<Move> legalMove,
        final Collection<Move> opponentMove) {
        this.board = board;
        this.playerKing = establishKing();
        this.legalMove = legalMove;
        this.opponentMove = opponentMove;
        isInCheck = MoveAnalyzer.calculateAttackOnTile(board, playerKing.getPiecePosition(), opponentMove).size() != 0;
    }

    /**
     * Return moves that attak piece position
     * @param piecePosition
     * @param opponentMove
     * @return
     */
    protected Collection<Move> calculateEscapeMove() {
        Collection<Move> escapeMoves = new ArrayList<Move>();
        for (Move move : legalMove) {
            if (MoveAnalyzer.analysisMove(board, move) == MoveStatus.DONE) escapeMoves.add(move);
        }
        return escapeMoves;
    }

    /**
     * Ensure there is a king on the board
     */
    private King establishKing() throws illegalGameException {
        for (Piece piece : getActivePieces()) {
            if (piece.getPieceType() == PieceType.KING) {
                return (King) piece;
            }
        }
        throw new illegalGameException("Illegal game setup: with no King!!!");
    }


    /**
     * Get active pieces for corresponding player
     * @return
     */
    abstract Collection<Piece> getActivePieces();

    /**
     * Get AllianceType for player
     */
    abstract AllianceType getAllianceType();

    /**
     * Check if a move is legel
     */
    public boolean isMoveLegel(Move move) {
        return legalMove.contains(move);
    }

    /**
     * Return if player in check
     * @return
     */
    public boolean isInCheck() {
        return isInCheck;
    }

    /**
     * Return if player in checkmate
     * @return
     */
    public boolean isInCheckMate() {
        return isInCheck() && calculateEscapeMove().size() == 0;
    }

    /**
     * Return if player in stalemate
     * @return
     */
    public boolean isStaleMate() {
        return !isInCheck() && calculateEscapeMove().size() == 0;
    }

    /**
     * TODO:
     * Return if player is Castled
     * @return
     */
    public boolean isCastled() {
        return false;
    }

    public Board getBoard() {
        return board;
    }
}

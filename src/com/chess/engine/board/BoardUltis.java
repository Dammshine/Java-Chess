package com.chess.engine.board;

import com.chess.engine.pieces.Piece;
import com.chess.engine.pieces.Piece.AllianceType;

public class BoardUltis {
    public static final int NUM_TILE = 8;
    /**
     * Make it impossible to instantiated such class
     */
    private BoardUltis() {
        throw new RuntimeException("This class can't be instantiated");
    }

    /**
     * Return true if the coordinate is on the chess board
     * @param canidateCoordinate
     * @return
     */
    static public boolean isValidCoor(int[] canidateCoordinate) {
        return canidateCoordinate[0] >= 0 && canidateCoordinate[0] < NUM_TILE
        && canidateCoordinate[1] >= 0 && canidateCoordinate[1] < NUM_TILE;
    }

    /**
     * Return the direction for the piece on the chess board
     * @param piece
     * @return
     */
    static public int getAllianceFactor(Piece piece) {
        if (piece.getAllianceType() == AllianceType.WHITE) return -1;
        return 1;
    }
}


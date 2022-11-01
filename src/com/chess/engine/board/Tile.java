package com.chess.engine.board;

import java.util.HashMap;
import java.util.Map;

import com.chess.engine.pieces.Piece;
import com.google.common.collect.ImmutableMap;

/**
 * Chess tile,
 */

// com.chess.engine.board
// com.chess.engine.pieces
public abstract class Tile {
    protected final int tileCoordinate;
    private static final Map<Integer, EmptyTile> EMPTY_TILES_CACHE = createAllPossibleEmptyTiles();

    /**
     * Private Constructor
     * @param tileCoordinate
     */
    private Tile(final int tileCoordinate) {
        this.tileCoordinate = tileCoordinate;
    }

    /**
     * Only method for creating tile, either occupied tile or empty tile
     * @param tileCoordinate
     * @param piece
     * @return
     */
    public static Tile creatTile(final int tileCoordinate, final Piece piece) {
        return piece != null ? new OccupiedTile(tileCoordinate, piece) : EMPTY_TILES_CACHE.get(tileCoordinate);
    }

    /**
     * Create constant caches to avoid create new memory
     * @return
     */
    private static Map<Integer, EmptyTile> createAllPossibleEmptyTiles() {
        final Map<Integer, EmptyTile> emptyTileMap = new HashMap<>();
        for (int i = 0; i < 64; i++) {
            emptyTileMap.put(i, new EmptyTile(i));
        }
        return ImmutableMap.copyOf(emptyTileMap);
    }

    /**
     * Tell if the tile is occupied
     * @return
     */
    public abstract boolean isTileOccupied();

    /**
     * Get piece off the given tile
     * @return
     */
    public abstract Piece getPiece();

    /**
     * Empty Tile
     */
    public static final class EmptyTile extends Tile{
        EmptyTile(int coordinate) {
            super(coordinate);
        }

        @Override
        public boolean isTileOccupied() {
            return false;
        }

        @Override
        public Piece getPiece() {
            return null;
        }
    }

    /**
     * Occupied Tile
     */
    public static final class OccupiedTile extends Tile {
        private final Piece pieceOnTile;
        
        OccupiedTile(int coordinate, Piece piece) {
            super(coordinate);
            this.pieceOnTile = piece;
        }

        @Override
        public boolean isTileOccupied() {
            return true;
        }

        @Override
        public Piece getPiece() {
            return pieceOnTile;
        }
    }
}

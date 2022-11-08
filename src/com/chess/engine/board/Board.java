package com.chess.engine.board;

import java.text.CollationElementIterator;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.chess.engine.pieces.Bishop;
import com.chess.engine.pieces.King;
import com.chess.engine.pieces.Knight;
import com.chess.engine.pieces.Pawn;
import com.chess.engine.pieces.Piece;
import com.chess.engine.pieces.Queen;
import com.chess.engine.pieces.Rock;
import com.chess.engine.pieces.Piece.AllianceType;
import com.chess.engine.pieces.Piece.PieceType;
import com.chess.engine.player.BlackPlayer;
import com.chess.engine.player.WhitePlayer;

public class Board {
    private Piece[][] board;
    private AllianceType nextMove;

    /**
     * Player Class
     * @return
     */
    private WhitePlayer whitePlayer;
    private BlackPlayer blackPlayer;

    
    public static Board initializeBoard() {
        return new Board();
    }
    
    /**
     * Private constructor for the board
     */
    private Board() {
        // Piece that is null indicate it's an empty tile
        this.board = new Piece[8][8];
        
        // Black Layout
        this.board[0][0] = new Rock(0, 0,   AllianceType.BLACK);
        this.board[0][1] = new Knight(0, 1, AllianceType.BLACK);
        this.board[0][2] = new Bishop(0, 2, AllianceType.BLACK);
        this.board[0][3] = new Queen(0, 3,  AllianceType.BLACK);
        this.board[0][4] = new King(0, 4,   AllianceType.BLACK);
        this.board[0][5] = new Bishop(0, 5, AllianceType.BLACK);
        this.board[0][6] = new Knight(0, 6, AllianceType.BLACK);
        this.board[0][7] = new Rock(0, 7,   AllianceType.BLACK);

        this.board[1][0] = new Pawn(1, 0,  AllianceType.BLACK);
        this.board[1][1] = new Pawn(1, 1,  AllianceType.BLACK);
        this.board[1][2] = new Pawn(1, 2, AllianceType.BLACK);
        this.board[1][3] = new Pawn(1, 3, AllianceType.BLACK);
        this.board[1][4] = new Pawn(1, 4, AllianceType.BLACK);
        this.board[1][5] = new Pawn(1, 5, AllianceType.BLACK);
        this.board[1][6] = new Pawn(1, 6, AllianceType.BLACK);
        this.board[1][7] = new Pawn(1, 7, AllianceType.BLACK);

        // White Layout
        this.board[6][0] = new Pawn(6, 0, AllianceType.WHITE);
        this.board[6][1] = new Pawn(6, 1, AllianceType.WHITE);
        this.board[6][2] = new Pawn(6, 2, AllianceType.WHITE);
        this.board[6][3] = new Pawn(6, 3, AllianceType.WHITE);
        this.board[6][4] = new Pawn(6, 4, AllianceType.WHITE);
        this.board[6][5] = new Pawn(6, 5, AllianceType.WHITE);
        this.board[6][6] = new Pawn(6, 6, AllianceType.WHITE);
        this.board[6][7] = new Pawn(6, 7, AllianceType.WHITE);

        this.board[7][0] = new Rock(7, 0,   AllianceType.WHITE);
        this.board[7][1] = new Knight(7, 1, AllianceType.WHITE);
        this.board[7][2] = new Bishop(7, 2, AllianceType.WHITE);
        this.board[7][3] = new Queen(7, 3,  AllianceType.WHITE);
        this.board[7][4] = new King(7, 4,   AllianceType.WHITE);
        this.board[7][5] = new Bishop(7, 5, AllianceType.WHITE);
        this.board[7][6] = new Knight(7, 6, AllianceType.WHITE);
        this.board[7][7] = new Rock(7, 7,   AllianceType.WHITE);

        // Initialize first move
        nextMove = AllianceType.WHITE;

        // Initialize Player
        this.whitePlayer = new WhitePlayer(this, getMoves(AllianceType.WHITE), getMoves(AllianceType.BLACK));
        this.blackPlayer = new BlackPlayer(this, getMoves(AllianceType.BLACK), getMoves(AllianceType.WHITE));
    }

    /**
     * Private constructor for the board
     */
    private Board(Piece[][] board, AllianceType nextMove) {
        // Piece that is null indicate it's an empty tile
        this.board = new Piece[8][8];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == null) this.board[i][j] = null;
                else this.board[i][j] = this.board[i][j].duplicate();
                
            }
        }

        // Initialize first move
        this.nextMove = nextMove;

        // Initialize Player
        this.whitePlayer = new WhitePlayer(this, getMoves(AllianceType.WHITE), getMoves(AllianceType.BLACK));
        this.blackPlayer = new BlackPlayer(this, getMoves(AllianceType.BLACK), getMoves(AllianceType.WHITE));
    }

    /**
     * 
     * @param canidateCoordinate
     * @return
     */
    public void simulate(Move move) {
        // Swap turns
        if (nextMove == AllianceType.WHITE) nextMove = AllianceType.BLACK;
        else nextMove = AllianceType.WHITE;
        
        /**
         * Assume move here is legel move
         */
        int row = move.getMoveRow();
        int col = move.getMoveCol();
        Piece piece = move.getMovePiece();
        int currRow = piece.getPiecePosition()[0];
        int currCol = piece.getPiecePosition()[1];

        // Normal major move
        if (board[row][col] == null) {
            board[row][col] = board[currRow][currCol];
            board[currRow][currCol] = null;
        } else {
            // Capture that pieces
            /* System.out.println(board[row][col]);
            System.exit(0); */
            board[row][col] = null;
            board[row][col] = board[currRow][currCol]; 
            board[currRow][currCol] = null;
        }
        
        board[row][col].setPiecePosition(new int[]{row, col});
        board[row][col].setFirstMove();
    }

    /**
     * Get Moves
     * @return
     */
    public Collection<Move> getMoves() {
        return getMoves(nextMove);
    }

    /**
     * Get moves from one alliance side
     * @param side
     * @return
     */
    public Collection<Move> getMoves(AllianceType side) {
        List<Move> moves = new ArrayList<>();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] != null && board[i][j].getAllianceType() == side) {
                    Collection<Move> checkValidMove = board[i][j].calculateLegalMoves(this);
                    
                    moves = Stream.of(moves, checkValidMove).flatMap(Collection::stream)
                        .collect(Collectors.toList());
                }
            }
        }
        return moves;
    }

    public Collection<Move> getMoves(int row, int col) {
        return board[row][col].calculateLegalMoves(this);
    }

    /*
     * Return the Tile class at that slot
     */
    public Piece getTile(int row, int col) {
        return board[row][col];
    }

    /**
     * Return pieces for white side
     * @param piece
     * @param destination
     * @return
     */
    public Collection<Piece> getWhitePieces() {
        return getPieces(AllianceType.WHITE);
    }
    /**
     * Return pieces for black side
     * @param piece
     * @return
     */
    public Collection<Piece> getBlackPieces() {
        return getPieces(AllianceType.BLACK);
    }

    private Collection<Piece> getPieces(AllianceType side) {
        List<Piece> pieces = new ArrayList<>();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] != null && board[i][j].getAllianceType() == side) {
                    pieces.add(board[i][j]);
                }
            }
        }
        return pieces;
    }

    /*
     * - Check if the tile is occupied by ally
     * - Check if this move cuase checkmate
     */
    public boolean isLegelMove(Piece piece, int[] destination) {
        int row = destination[0];
        int col = destination[1];

        /**
         * - Empty grid
         * - Enemy Piece, can capture
         */
        if (board[row][col] == null || (board[row][col].getAllianceType() != piece.getAllianceType())) {
            if (causeCheckMate(piece, destination)) return false;
            return true;
        } else {
            return false;
        }
    }

    /*
     * - Check if the tile is occupied by an enermy
     */
    public boolean isLegelAttack(Piece piece, int[] destination) {
        int row = destination[0];
        int col = destination[1];

        /**
         * - Empty grid
         * - Enemy Piece, can capture
         */
        if (board[row][col] == null || (board[row][col].getAllianceType() == piece.getAllianceType())) {
            return false;
        } else {
            return true;
        }
    }

    /*
     * TODO:
     *  - Check if this move cause piece being checkmated
     */
    private boolean causeCheckMate(Piece piece, int[] destination) {
        return false;
    }

    /**
     * TODO:
     *  - Self simulate a move, use a random move basically
     */
    public void selfSimulate(boolean printAction) {
        // G2G code, get a random move
        Move randomMove = getRandomMove();
        
        if (printAction) System.out.println(randomMove);
        this.simulate(randomMove);
    }

    public Move getRandomMove() {
        List<Move> moves = (List<Move>) this.getMoves();
        if (moves.size() == 0) {
            System.out.println("Game ended");
            System.exit(0);
        }

        // G2G code, get a random move
        Move randomMove = moves.get(
            ThreadLocalRandom.current().nextInt(moves.size())
            % moves.size());
    }


    public Board duplicate() {
        return new Board(this.board, this.nextMove);
    }

    public AllianceType getNextMove() {
        return nextMove;
    }
}

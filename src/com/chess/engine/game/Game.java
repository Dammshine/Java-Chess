package com.chess.engine.game;

import java.util.ArrayList;
import java.util.List;

import com.chess.engine.board.Board;

/**
 * Game Class responsibility is to simulate the boards, from move to move
 * and actually simulate two player
 */
public class Game {
    List<Board> game;
    Board currGame;

    Game() {
        this.game = new ArrayList<Board>();
        this.currGame = Board.initializeBoard();
    }

    public List<Board> getHistroyGame() {
        return game;
    }
    
    public Board getCurrGame() {
        return currGame;
    }
}

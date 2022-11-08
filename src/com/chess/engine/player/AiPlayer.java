package com.chess.engine.player;

import com.chess.engine.board.Move;

public class AiPlayer implements PlayerBody {
    AiPlayer() {

    }

    @Override
    public Move execute(Player player) {
        // Select a random play from the board
        return player.getBoard().getRandomMove();
    }
    
}

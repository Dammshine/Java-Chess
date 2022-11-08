package com.chess.engine.player;

import com.chess.engine.board.Board;
import com.chess.engine.board.Move;

public enum MoveStatus {
    DONE {
        @Override
        public boolean isDone() {
            return true;
        }
    }, 
    CHECK {
        @Override
        public boolean isDone() {
            return false;
        }
    };
    public abstract boolean isDone();
}

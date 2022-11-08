package com.chess.engine.player;

public class illegalGameException extends IllegalArgumentException {
    illegalGameException(String message) {
        super(message);
    }
}

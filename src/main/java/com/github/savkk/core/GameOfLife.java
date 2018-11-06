package com.github.savkk.core;

import com.github.savkk.output.Displayable;

public final class GameOfLife {
    private long stepTimeInMillis;
    private Displayable display;
    private Board board;

    public GameOfLife(Displayable display, Board board, long stepTimeInMillis) {
        this.display = display;
        this.board = board;
        this.stepTimeInMillis = stepTimeInMillis;
    }

    public void run() {
        while (!board.isEmpty()) {
            try {
                Thread.sleep(stepTimeInMillis);
            } catch (InterruptedException e) {
                // ignore
            }
            display.show(board);
            board.step();
        }
        display.finish();
    }


    public void randomFirstGeneration() {
        for (int i = 0; i < board.getHeight(); i++) {
            for (int j = 0; j < board.getWidth(); j++) {
                if (Math.random() <= 0.3) {
                    board.setCell(i, j);
                }
            }
        }
    }
}

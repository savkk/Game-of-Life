package com.github.savkk.core;


import java.util.Arrays;

public class Board {
    private Cell[][] board;
    private Cell[][] newStateBoard;
    private int height;
    private int width;

    public Board(int height, int width) {
        this.height = height;
        this.width = width;
        board = new Cell[height][width];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                board[i][j] = new Cell();
            }
        }
        newStateBoard = Arrays.copyOf(board, board.length);
    }

    public Cell[][] setCell(int x, int y) {
        if (x > height || y > width) {
            return board;
        }
        board[x][y].swap();
        return board;
    }

    public Cell[][] setCellAndStep(int x, int y) {
        setCell(x, y);
        return step();
    }

    public Cell[][] step() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                int neighbours = getNeighboursCount(i, j);
                if (neighbours == 3) {
                    newStateBoard[i][j].setAlive(true);
                } else if (neighbours < 2 || neighbours > 3) {
                    newStateBoard[i][j].setAlive(false);
                }
            }
        }
        board = Arrays.copyOf(newStateBoard, newStateBoard.length);
        return board;
    }

    //     y y y
    // x  |1|2|3|
    // x  |4|*|6|
    // x  |7|8|9|
    private int getNeighboursCount(int x, int y) {
        int neighbours = 0;
        if (x != 0 && y != 0) {
            if (board[x - 1][y - 1].isAlive()) {
                neighbours++;
            }
        }
        if (x != 0) {
            if (board[x - 1][y].isAlive()) {
                neighbours++;
            }
        }
        if (x != 0 && y != width - 1) {
            if (board[x - 1][y + 1].isAlive()) {
                neighbours++;
            }
        }
        if (y != 0) {
            if (board[x][y - 1].isAlive()) {
                neighbours++;
            }
        }
        if (y != width - 1) {
            if (board[x][y + 1].isAlive()) {
                neighbours++;
            }
        }
        if (x != height - 1 && y != 0) {
            if (board[x + 1][y - 1].isAlive()) {
                neighbours++;
            }
        }
        if (x != height - 1) {
            if (board[x + 1][y].isAlive()) {
                neighbours++;
            }
        }
        if (x != height - 1 && y != width - 1) {
            if (board[x + 1][y + 1].isAlive()) {
                neighbours++;
            }
        }
        return neighbours;
    }

    private class Cell {
        private boolean isAlive = false;

        boolean isAlive() {
            return isAlive;
        }

        void setAlive(boolean alive) {
            isAlive = alive;
        }

        void swap() {
            isAlive = !isAlive;
        }
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }
}

package com.github.savkk.core;


import java.util.Arrays;

public class Board {
    private Cell[][] board;
    private Cell[][] newStateBoard;

    public Board(int x, int y) {
        board = new Cell[x][y];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                board[i][j] = new Cell();
            }
        }
        newStateBoard = Arrays.copyOf(board, board.length);
    }

    public void step() {
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
        if (x != 0 && y != board[x].length - 1) {
            if (board[x - 1][y + 1].isAlive()) {
                neighbours++;
            }
        }
        if (y != 0) {
            if (board[x][y - 1].isAlive()) {
                neighbours++;
            }
        }
        if (y != board[x].length - 1) {
            if (board[x][y + 1].isAlive()) {
                neighbours++;
            }
        }
        if (x != board.length - 1 && y != 0) {
            if (board[x + 1][y - 1].isAlive()) {
                neighbours++;
            }
        }
        if (x != board.length - 1) {
            if (board[x + 1][y].isAlive()) {
                neighbours++;
            }
        }
        if (x != board.length - 1 && y != board[x].length - 1) {
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
    }
}
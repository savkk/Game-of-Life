package com.github.savkk;

import com.github.savkk.core.Board;
import com.github.savkk.core.GameOfLife;
import com.github.savkk.output.Console;
import com.github.savkk.output.Displayable;

public class Main {
    public static void main(String[] args) {
        Board board = new Board(11, 100);
        Displayable display = new Console();
        GameOfLife gameOfLife = new GameOfLife(display, board, 300);
        gameOfLife.randomFirstGeneration();
        gameOfLife.run();
    }
}

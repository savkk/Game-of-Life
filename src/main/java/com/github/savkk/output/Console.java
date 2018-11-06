package com.github.savkk.output;

import com.github.savkk.core.Board;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;

public class Console implements Displayable {
    private Terminal terminal;
    private TextGraphics textGraphics;

    public Console() {
        DefaultTerminalFactory defaultTerminalFactory = new DefaultTerminalFactory();
        defaultTerminalFactory.setTerminalEmulatorTitle("Game of Life");
        try {
            terminal = defaultTerminalFactory.createTerminal();
            textGraphics = terminal.newTextGraphics();
            terminal.clearScreen();
            terminal.setCursorVisible(false);
            textGraphics.setForegroundColor(TextColor.ANSI.WHITE);
            textGraphics.setBackgroundColor(TextColor.ANSI.BLUE);
        } catch (IOException e) {
            throw new IllegalStateException("Не удалось создать терминал", e);
        }
    }

    @Override
    public void show(Board board) {
        for (int i = 0; i < board.getHeight(); i++) {
            for (int j = 0; j < board.getWidth(); j++) {
                if (board.isAlive(i, j)) {
                    textGraphics.putString(j, i, "■");
                } else {
                    textGraphics.putString(j, i, "□");
                }
            }
        }
        try {
            terminal.flush();
        } catch (IOException e) {
            throw new IllegalStateException("Не удалось вывести текст в терминал", e);
        }
    }

    @Override
    public void finish() {
        try {
            terminal.clearScreen();
        } catch (IOException e) {
            throw new IllegalStateException("Не удалось очистить окно терминала", e);
        }
        textGraphics.putString(0, 0, "Everybody died!");
    }
}

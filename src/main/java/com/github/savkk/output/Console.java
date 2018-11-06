package com.github.savkk.output;

import com.diogonunes.jcdp.color.ColoredPrinter;
import com.diogonunes.jcdp.color.api.Ansi;
import com.github.savkk.core.Board;

public class Console implements Displayable {
    private ColoredPrinter coloredPrinter = new ColoredPrinter.Builder(1, false)
            .foreground(Ansi.FColor.WHITE)
            .background(Ansi.BColor.BLUE).build();

    @Override
    public void show(Board board) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < board.getHeight(); i++) {
            for (int j = 0; j < board.getWidth(); j++) {
                if (board.isAlive(i, j)) {
                    stringBuilder.append("■");
                } else {
                    stringBuilder.append("□");
                }
            }
            stringBuilder.append(System.lineSeparator());
        }
        coloredPrinter.clear();
        coloredPrinter.println(stringBuilder.toString());
    }

    @Override
    public void finish() {
        coloredPrinter.println("Everybody died!");
    }
}

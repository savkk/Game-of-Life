package com.github.savkk;

import com.github.savkk.core.Board;
import com.github.savkk.core.GameOfLife;
import com.github.savkk.output.Displayable;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;

public class Main {
    @Option(name = "-s", aliases = "-step", usage = "step time in millis")
    private long stepTime = 500;

    @Option(name = "-o", aliases = "-output", usage = "user interface :)")
    private Output output = Output.CONSOLE;

    @Option(name = "-w", aliases = "-width", usage = "width of board")
    private int width = 80;

    @Option(name = "-h", aliases = "height", usage = "height of board")
    private int height = 30;

    public static void main(String[] args) {
        Main main = new Main();
        CmdLineParser cmdLineParser = new CmdLineParser(main);

        cmdLineParser.printUsage(System.err);

        Board board = new Board(main.height, main.width);
        GameOfLife gameOfLife = new GameOfLife(main.output.getDisplayable(), board, main.stepTime);
        gameOfLife.randomFirstGeneration();
        gameOfLife.run();
    }

    public enum Output {
        CONSOLE("com.github.savkk.output.Console");

        private String outputClass;

        Output(String outputClass) {
            this.outputClass = outputClass;
        }

        public Displayable getDisplayable() {
            Displayable o;
            try {
                o = (Displayable) Class.forName(this.outputClass).newInstance();
            } catch (Exception e) {
                throw new IllegalStateException("Что-то пошло не так", e);
            }
            return o;
        }
    }
}

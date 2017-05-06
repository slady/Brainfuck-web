package ps.java;

import ps.java.brainfuck.Brainfuck;

/**
 * This is a web app interpreter for the Brainfuck esoteric programming language.
 *
 * @see <a href="https://en.wikipedia.org/wiki/Brainfuck">Brainfuck from Wikipedia</a>
 * @author slady@slady.net
 */
public class BrainfuckApp {
    private void prepare() {
        Brainfuck.run("", "");
    }
}

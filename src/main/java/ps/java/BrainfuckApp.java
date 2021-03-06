package ps.java;

import com.github.wolfie.refresher.Refresher;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.Sizeable;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.*;
import ps.java.brainfuck.Brainfuck;
import ps.java.brainfuck.BrainfuckVisualDataStorage;
import ps.java.brainfuck.io.BrainfuckStringInputOutput;
import ps.java.brainfuck.parser.BrainfuckParser;

import static com.google.gwt.safehtml.shared.SafeHtmlUtils.htmlEscape;

/**
 * This is a web app interpreter for the Brainfuck esoteric programming language.
 *
 * @see <a href="https://en.wikipedia.org/wiki/Brainfuck">Brainfuck from Wikipedia</a>
 * @author slady@slady.net
 */
public class BrainfuckApp {

    private static final String STYLE_START = "<b><span style='background-color:black;color:white;'> ";

    private static final String STYLE_END = " <span></b>";

    private final Label stackLabel = new Label();

    private final Label codeLabel = new Label();

    private final Label outputLabel = new Label();

    private final TextArea textArea = new TextArea();

    private final Refresher refresher = new Refresher();

    private Brainfuck brainfuck;

    public TabSheet prepareDebugger() {
        final TabSheet tabSheet = new TabSheet();
        tabSheet.addTab(textArea, "Editor");
        textArea.setWidth(100, Sizeable.Unit.PERCENTAGE);

        final VerticalLayout verticalLayout = new VerticalLayout();
        final HorizontalLayout horizontalLayout = new HorizontalLayout();

        final Button buttonRun = new Button("Run", FontAwesome.PLAY);
        final Button buttonPause = new Button("Pause", FontAwesome.PAUSE);
        final Button buttonStep = new Button("Step", FontAwesome.STEP_FORWARD);
        final Button buttonReset = new Button("Reset", FontAwesome.REPEAT);
        horizontalLayout.addComponents(buttonRun, buttonPause, buttonStep, buttonReset);

        verticalLayout.addComponents(horizontalLayout,
                new Label("Stack:"), stackLabel,
                new Label("Code:"), codeLabel,
                new Label("Output:"), outputLabel);
        tabSheet.addTab(verticalLayout, "Debugger");

//        buttonRun.setEnabled(false);
//        buttonPause.setEnabled(false);
        buttonRun.addClickListener(e -> {
            refresher.setRefreshInterval(100);
            refresher.addListener(v -> makeStep());
        });

        buttonPause.addClickListener(e -> {
            refresher.setRefreshInterval(-1);
        });

        tabSheet.addSelectedTabChangeListener(e -> reset());

        buttonStep.addClickListener(e -> { makeStep(); } );

        buttonReset.addClickListener((Button.ClickListener) e -> { reset(); });

        return tabSheet;
    }

    private void reset() {
        brainfuck = prepareBrainfuck(textArea.getValue());
        repaint();
    }

    private void makeStep() {
        if (brainfuck.isRunning()) {
            brainfuck.step();
        }

        repaint();
    }

    private Brainfuck prepareBrainfuck(final String program) {
        String input = "";
        final BrainfuckParser parser = new BrainfuckParser(program);
        final BrainfuckVisualDataStorage dataStorage = new BrainfuckVisualDataStorage();
        final BrainfuckStringInputOutput inputOutput = new BrainfuckStringInputOutput(input);
        return new Brainfuck(parser, dataStorage, inputOutput);
    }

    private void repaint() {
        final StringBuilder stack = new StringBuilder();
        final BrainfuckVisualDataStorage dataStorage = (BrainfuckVisualDataStorage) brainfuck.getDataStorage();
        final int dataPointer = dataStorage.getPointer();
        for (int i = 0; i <= dataStorage.getMax(); i++) {
            stack.append(' ');

            if (i == dataPointer) {
                stack.append(STYLE_START);
            }

            stack.append(dataStorage.getValueAt(i));

            if (i == dataPointer) {
                stack.append(STYLE_END);
            }
        }
        stackLabel.setValue(stack.toString());
        stackLabel.setContentMode(ContentMode.HTML);

        final int pointer = brainfuck.getPointer();
        final String code = textArea.getValue();
        final String prefix = code.substring(0, pointer);
        final char ch;
        final String suffix;

        if (pointer < code.length()) {
            ch = code.charAt(pointer);
            suffix = code.substring(pointer + 1);
        } else {
            ch = ' ';
            suffix = "";
        }

        codeLabel.setValue(htmlEscape(prefix) + STYLE_START + htmlEscape(ch) + STYLE_END + htmlEscape(suffix));
        codeLabel.setContentMode(ContentMode.HTML);

        final BrainfuckStringInputOutput inputOutput = (BrainfuckStringInputOutput) brainfuck.getInputOutput();
        outputLabel.setValue(htmlEscape(inputOutput.getOutput()));
    }

    public Refresher getRefresher() {
        return refresher;
    }

}

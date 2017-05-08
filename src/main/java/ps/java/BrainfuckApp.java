package ps.java;

import com.vaadin.server.FontAwesome;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.*;

/**
 * This is a web app interpreter for the Brainfuck esoteric programming language.
 *
 * @see <a href="https://en.wikipedia.org/wiki/Brainfuck">Brainfuck from Wikipedia</a>
 * @author slady@slady.net
 */
public class BrainfuckApp {

    private final Label stackLabel = new Label();

    private final Label codeLabel = new Label("[ax<b>x</b>++x]", ContentMode.HTML);

    public static Layout prepareEditor() {
        final VerticalLayout verticalLayout = new VerticalLayout();
        new Label("");
        return verticalLayout;
    }

    public Layout prepareDebugger() {
        final VerticalLayout verticalLayout = new VerticalLayout();
        final HorizontalLayout horizontalLayout = new HorizontalLayout();

        final Button buttonRun = new Button("Run", FontAwesome.PLAY);
        final Button buttonStep = new Button("Step", FontAwesome.STEP_FORWARD);
        final Button buttonPause = new Button("Pause", FontAwesome.PAUSE);
        horizontalLayout.addComponents(buttonRun, buttonStep, buttonPause);

        verticalLayout.addComponents(horizontalLayout, stackLabel, codeLabel);

//        codeLabel.setContentMode(ContentMode.HTML);
        return verticalLayout;
    }

    public Label getCodeLabel() {
        return codeLabel;
    }

    public Label getStackLabel() {
        return stackLabel;
    }

}

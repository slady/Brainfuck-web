package ps.java;

import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Layout;
import com.vaadin.ui.VerticalLayout;
import ps.java.brainfuck.Brainfuck;

import java.awt.*;
import java.awt.event.FocusAdapter;

/**
 * This is a web app interpreter for the Brainfuck esoteric programming language.
 *
 * @see <a href="https://en.wikipedia.org/wiki/Brainfuck">Brainfuck from Wikipedia</a>
 * @author slady@slady.net
 */
public class BrainfuckApp {

    public static Layout prepareEditor() {
        final VerticalLayout verticalLayout = new VerticalLayout();
        new Label("");
        return verticalLayout;
    }

    public static Layout prepareDebugger() {
        final VerticalLayout verticalLayout = new VerticalLayout();
        final HorizontalLayout horizontalLayout = new HorizontalLayout();

        final Button buttonRun = new Button("Run", FontAwesome.PLAY);
        final Button buttonStep = new Button("Step", FontAwesome.STEP_FORWARD);
        final Button buttonPause = new Button("Pause", FontAwesome.PAUSE);
        horizontalLayout.addComponents(buttonRun, buttonStep, buttonPause);
        verticalLayout.addComponent(horizontalLayout);
//        Brainfuck.run("", "");
        return verticalLayout;
    }

}

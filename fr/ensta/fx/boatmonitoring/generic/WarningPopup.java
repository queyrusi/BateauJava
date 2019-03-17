package fr.ensta.fx.boatmonitoring.generic;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

/**
 * <p>
 *     A popup to warn the user about something.
 *     To use it, the following sample is enough (from an {@code Application} thread):
 *    <pre><code>
 new WarningPopup("Warning!", "Something went wrong!").popup();
 *    </code></pre>
 * </p>
 *
 * @see AbstractPopup#popup()
 * @see AbstractPopup#close()
 *
 * @author <a href="mailto:luka.le_roux@ensta-bretagne.fr">Luka Le Roux</a>
 */
public class WarningPopup extends AbstractPopup {

    private final Label label;

    public WarningPopup(String warningTitle, String warningMessage) {
        super(warningTitle);
        label = new Label(warningMessage);
    }

    @Override
    public Pane getPane() {
        final StackPane result = new StackPane();
        result.setAlignment(Pos.CENTER);
        result.getChildren().add(label);
        return result;
    }

}

package fr.ensta.fx.boatmonitoring.user;

import fr.ensta.fx.boatmonitoring.util.FXHelper;
import fr.ensta.fx.boatmonitoring.generic.AbstractPopup;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

/**
 * @see LoginPopup
 *
 * @author <a href="mailto:luka.le_roux@ensta-bretagne.fr">Luka Le Roux</a>
 */
public class ToggleMonitorPopup extends AbstractPopup {

    private final BoatMonitorTab boatMonitor;
    private PasswordField passwordField;

    public ToggleMonitorPopup(BoatMonitorTab boatMonitor) {
        super("Toggle Monitor for '" + boatMonitor.getBoatName() + "'");
        this.boatMonitor = boatMonitor;
    }

    @Override
    protected Pane getPane() {
        final Label passwordLabel = new Label("Password:");
        passwordField = new PasswordField();

        final Button submitButton = new Button("Toggle");
        submitButton.setOnAction(this::toggleMonitor);

        final GridPane result = new GridPane();
        FXHelper.setupAndFillGridPane(result, 5, 25, 2,
                passwordLabel, passwordField,
                submitButton
        );
        return result;
    }

    private void toggleMonitor(ActionEvent event) {
        this.close();
        boatMonitor.getClientView().toggleMonitor(boatMonitor, passwordField.getText());
    }
}

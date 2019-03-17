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
public class ChangePasswordPopup extends AbstractPopup {

    private final FXUserUI clientView;
    private PasswordField oldPasswordField;
    private PasswordField newPasswordField;

    public ChangePasswordPopup(FXUserUI clientView) {
        super("Change password for " + clientView.getAccountName());
        this.clientView = clientView;
    }

    @Override
    protected Pane getPane() {
        final Label accountNameLabel = new Label(clientView.accountNameProperty().getName() + ":");
        oldPasswordField = new PasswordField();
        final Label passwordLabel = new Label("Password:");
        newPasswordField = new PasswordField();

        final Button submitButton = new Button("Create");
        submitButton.setOnAction(this::createAccount);

        final GridPane result = new GridPane();
        FXHelper.setupAndFillGridPane(result, 5, 25, 2,
                accountNameLabel, oldPasswordField,
                passwordLabel, newPasswordField,
                submitButton
        );
        return result;
    }

    private void createAccount(ActionEvent event) {
        this.close();
        clientView.changePassword(oldPasswordField.getText(), newPasswordField.getText());
    }
}

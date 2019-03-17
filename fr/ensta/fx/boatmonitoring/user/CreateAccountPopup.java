package fr.ensta.fx.boatmonitoring.user;

import fr.ensta.fx.boatmonitoring.util.FXHelper;
import fr.ensta.fx.boatmonitoring.generic.AbstractPopup;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

/**
 * @see LoginPopup
 *
 * @author <a href="mailto:luka.le_roux@ensta-bretagne.fr">Luka Le Roux</a>
 */
public class CreateAccountPopup extends AbstractPopup {

    private final FXUserUI clientView;
    private TextField accountNameField;
    private PasswordField passwordField;

    public CreateAccountPopup(FXUserUI clientView) {
        super("Create Account");
        this.clientView = clientView;
    }

    @Override
    protected Pane getPane() {
        final Label accountNameLabel = new Label(clientView.accountNameProperty().getName() + ":");
        accountNameField = new TextField();
        final Label passwordLabel = new Label("Password:");
        passwordField = new PasswordField();

        final Button submitButton = new Button("Create");
        submitButton.setOnAction(this::createAccount);

        final GridPane result = new GridPane();
        FXHelper.setupAndFillGridPane(result, 5, 25, 2,
                accountNameLabel, accountNameField,
                passwordLabel, passwordField,
                submitButton
        );
        return result;
    }

    private void createAccount(ActionEvent event) {
        this.close();
        clientView.createAccount(accountNameField.getText(), passwordField.getText());
    }

}

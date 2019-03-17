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

/** <p>A login form as a popup.</p>
 *
 * <p>Calls {@link FXUserUI#login(String accountName, String password)}
 * when closed via the submit button.</p>
 *
 * @author <a href="mailto:luka.le_roux@ensta-bretagne.fr">Luka Le Roux</a>
 */
public class LoginPopup extends AbstractPopup {

    // The view who created this popup
    private final FXUserUI clientView;

    // Input fields to be displayed, then read for the actual login attempt
    private TextField accountNameField;
    private PasswordField passwordField;

    public LoginPopup(FXUserUI clientView) {
        super("Login");
        this.clientView = clientView;
    }

    @Override
    protected Pane getPane() {

        // Controls creation
        final Label accountNameLabel = new Label(clientView.accountNameProperty().getName() + ":");
        accountNameField = new TextField();
        final Label passwordLabel = new Label("Password:");
        passwordField = new PasswordField();

        final Button submitButton = new Button("Login");
        submitButton.setOnAction(this::login);

        // Pane creation and setting
        final GridPane result = new GridPane();
        FXHelper.setupAndFillGridPane(result, 5, 25, 2,
                accountNameLabel, accountNameField,
                passwordLabel, passwordField,
                submitButton
        );
        return result;
    }

    private void login(ActionEvent event) {
        this.close();
        clientView.login(accountNameField.getText(), passwordField.getText());
    }
}

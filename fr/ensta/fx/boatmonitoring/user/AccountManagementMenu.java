package fr.ensta.fx.boatmonitoring.user;

import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;

/**
 * @author <a href="mailto:luka.le_roux@ensta-bretagne.fr">Luka Le Roux</a>
 */
public class AccountManagementMenu extends Menu {

    private final FXUserUI clientView;

    private final MenuItem loginItem;
    private final MenuItem logoutItem;
    private final MenuItem createAccountItem;
    private final MenuItem changePasswordItem;

    public AccountManagementMenu(FXUserUI clientView) {
        super();

        this.clientView = clientView;

        loginItem = new MenuItem("Login");
        loginItem.setOnAction(this::login);
        this.getItems().add(loginItem);

        logoutItem = new MenuItem("Logout");
        logoutItem.setOnAction(this::logout);
        this.getItems().add(logoutItem);

        createAccountItem = new MenuItem("Create Account");
        createAccountItem.setOnAction(this::createAccount);
        this.getItems().add(createAccountItem);

        changePasswordItem = new MenuItem("Change Password");
        changePasswordItem.setOnAction(this::changePassword);
        this.getItems().add(changePasswordItem);

        this.clientView.loginStatusProperty().addListener(this::loginStatusListener);
        this.clientView.accountNameProperty().addListener(this::accountNameListener);

        Boolean loginStatus = clientView.getLoginStatus();
        update(loginStatus == null ? false : loginStatus);
    }

    private void login(ActionEvent event) {
        new LoginPopup(clientView).popup();
    }

    private void logout(ActionEvent event) {
        clientView.logout();
    }

    private void createAccount(ActionEvent event) {
        new CreateAccountPopup(clientView).popup();
    }

    private void changePassword(ActionEvent event) {
        new ChangePasswordPopup(clientView).popup();
    }

    private void loginStatusListener(ObservableValue<? extends Boolean> loginStatus, Boolean oldValue, Boolean newValue) {
        update(newValue == null ? false : newValue);
    }

    private void accountNameListener(ObservableValue<? extends String> accountName, String oldValue, String newValue) {
        final Boolean loginStatus = clientView.getLoginStatus();
        update(loginStatus == null ? false : loginStatus);
    }

    private void update(boolean loginStatus) {
        if (loginStatus) {
            this.setText("Logged as " + clientView.getAccountName());
            setLoggedIn(true);
        } else {
            this.setText("Not logged in");
            setLoggedIn(false);
        }
    }

    private void setLoggedIn(boolean loggedIn) {
        loginItem.setVisible(!loggedIn);
        logoutItem.setVisible(loggedIn);
        createAccountItem.setVisible(!loggedIn);
        changePasswordItem.setVisible(loggedIn);
    }

}

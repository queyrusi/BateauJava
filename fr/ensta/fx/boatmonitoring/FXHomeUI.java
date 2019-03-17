package fr.ensta.fx.boatmonitoring;

import fr.ensta.fx.boatmonitoring.generic.FXGenericUI;
import fr.ensta.fx.boatmonitoring.user.BoatMonitorTab;
import fr.ensta.fx.boatmonitoring.user.FXUserUI;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * @see fr.ensta.fx.boatmonitoring
 * @see FXUserUI
 * @see FXGenericUI
 *
 * @author <a href="mailto:luka.le_roux@ensta-bretagne.fr">Luka Le Roux</a>
 */
public class FXHomeUI extends FXUserUI {

    public BoatMonitorTab createAndAddMonitor() {
        final BoatMonitorTab result = new BoatMonitorTab(this);
        getTabs().add(result);
        return result;
    }

    public void removeMonitor(BoatMonitorTab monitorTab) {
        getTabs().remove(monitorTab);
    }

    public void removeAllMonitors() {
        getTabs().removeIf(tab -> tab instanceof BoatMonitorTab);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        super.start(primaryStage);

        final Scene scene = new Scene(getRootPane(), 400, 400);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Boat Monitoring: Home UI");
        primaryStage.show();
    }

    @Override
    public void login(String accountName, String password) {
        this.displayWarning("Login", "Unsupported operation");
    }

    @Override
    public void logout() {
        this.displayWarning("Logout", "Unsupported operation");
    }

    @Override
    public void createAccount(String accountName, String password) {
        this.displayWarning("Create Account", "Unsupported operation");
    }

    @Override
    public void changePassword(String oldPassword, String newPassword) {
        this.displayWarning("Change Password", "Unsupported operation");
    }

    @Override
    public void toggleMonitor(BoatMonitorTab boatMonitor, String password) {
        this.displayWarning("Boat Monitor", "Unsupported operation");
    }
    public static void main( String[] args ) {
    	
    	launch(args);
    	
    }
}

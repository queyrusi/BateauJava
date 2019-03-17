package fr.ensta.fx.boatmonitoring;

import fr.ensta.fx.boatmonitoring.controlcenter.CurrentSituationTab;
import fr.ensta.fx.boatmonitoring.controlcenter.DataBaseTab;
import fr.ensta.fx.boatmonitoring.generic.FXGenericUI;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * @see fr.ensta.fx.boatmonitoring
 * @see FXGenericUI
 *
 * @author <a href="mailto:luka.le_roux@ensta-bretagne.fr">Luka Le Roux</a>
 */
public class FXControlCenterUI extends FXGenericUI {

    // Data side of things

    private final ObservableList<String> accountList;
    private final ObservableList<String> boatList;
    private final ObservableList<String> connectedAccountList;
    private final ObservableList<String> monitoredBoatList;
    private final ObservableList<String> stolenBoatList;

    public FXControlCenterUI() {
        accountList = FXCollections.observableArrayList();
        boatList = FXCollections.observableArrayList();
        connectedAccountList = FXCollections.observableArrayList();
        monitoredBoatList = FXCollections.observableArrayList();
        stolenBoatList = FXCollections.observableArrayList();
    }

    public ObservableList<String> getConnectedAccountList() {
        return connectedAccountList;
    }

    public ObservableList<String> getMonitoredBoatList() {
        return monitoredBoatList;
    }

    public ObservableList<String> getStolenBoatList() {
        return stolenBoatList;
    }

    public ObservableList<String> getAccountList() {
        return accountList;
    }

    public ObservableList<String> getBoatList() {
        return boatList;
    }

    // HMI elements synchronized on those data

    /** <p>Note it calls {@code super.start(primaryStage)}.
     * This line calls {@link FXGenericUI#start(Stage)}. It is necessary to ensure
     * other graphical elements (like the main pane) are initialized first.</p>
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        super.start(primaryStage);

        getTabs().add(new CurrentSituationTab(this));
        getTabs().add(new DataBaseTab(this));

        final Scene scene = new Scene(getRootPane(), 400, 600);
        primaryStage.setTitle("Monitoring System: Control Center UI");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    public static void main( String[] args ) {
    	
    	launch(args);
    	
    }
}

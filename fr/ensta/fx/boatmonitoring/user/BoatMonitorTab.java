package fr.ensta.fx.boatmonitoring.user;

import fr.ensta.fx.boatmonitoring.FXBoatUI;
import fr.ensta.fx.boatmonitoring.FXHomeUI;
import fr.ensta.fx.boatmonitoring.generic.FXGenericUI;
import fr.ensta.fx.boatmonitoring.util.FXHelper;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.layout.GridPane;

/**
 * <p>
 *     A monitor displays the boat's name, its monitoring status and if it is stolen or not.
 *     Those data are held by {@link #boatName}, {@link #monitorStatus} and {@link #stolenStatus} properties.<br/>
 *     It is a {@code Tab} and can be added to any
 *     {@link fr.ensta.fx.boatmonitoring.generic.FXGenericUI FXGenericUI} via the
 *     {@link FXGenericUI#getTabs() FXGenericUI.getTabs()} method like so:
 *     <pre><code>
 myUI.getTabs().add(myMonitorTab);
 *     </code></pre>
 * </p>
 *
 * <p>
 *     Its {@link #BoatMonitorTab constructor} uses {@link FXHelper#bind} to synchronize booleans and the display.
 * </p>
 *
 * @see fr.ensta.fx.boatmonitoring.demo.TotoHomeDemo TotoHomeDemo
 *
 * @author <a href="mailto:luka.le_roux@ensta-bretagne.fr">Luka Le Roux</a>
 */
public class BoatMonitorTab extends Tab {

    private final static String UNKNOWN_BOAT = "Unknown";

    private final FXUserUI clientView;

    /**
     * <p>This property holds the name of the monitored boat.</p>
     *
     * <p>
     *     See {@link fr.ensta.fx.boatmonitoring.user.FXUserUI#loginStatus loginStatus}
     *     for general information about properties.
     * </p>
     */
    private final Property<String> boatName;

    /**
     * <p>This property holds a {@code Boolean} matching the status of the monitor (active/inactive).</p>
     *
     * <p>
     *     See {@link fr.ensta.fx.boatmonitoring.user.FXUserUI#loginStatus loginStatus}
     *     for general information about properties.
     * </p>
     */
    private final Property<Boolean> monitorStatus;

    /**
     * <p>This property holds a {@code Boolean} that is {@code true} if the boat was stolen.</p>
     *
     * <p>
     *     See {@link fr.ensta.fx.boatmonitoring.user.FXUserUI#loginStatus loginStatus}
     *     for general information about properties.
     * </p>
     */
    private final Property<Boolean> stolenStatus;

    /**
     * <p>
     *     The constructor for a boat monitor tab. It shouldn't be called directly,
     *     use {@link FXHomeUI#createAndAddMonitor() FXHomeUI.createAndAddMonitor()}
     *     or {@link FXBoatUI#getBoatMonitor() FXBoatUI.getBoatMonitor()} instead.<br/>
     * </p>
     *
     * <p>
     *     Its source code illustrates the binding functionality of properties
     *     via the use of {@link FXHelper#bind}.
     * </p>
     */
    public BoatMonitorTab(FXUserUI clientView) {

        this.clientView = clientView;

        boatName = new SimpleStringProperty(this, "Boat name", UNKNOWN_BOAT);
        FXHelper.bind(this.textProperty(), boatName,
                this::getTabTitle);

        monitorStatus = new SimpleBooleanProperty(this, "Monitor status", false);
        final Label monitorStatusLabel = new Label(monitorStatus.getName() + ":");
        final Label monitorStatusDisplay = new Label();
        FXHelper.bind(monitorStatusDisplay, monitorStatus,
                this::getMonitorStatusString);

        stolenStatus = new SimpleBooleanProperty(this, "Stolen status", false);
        final Label stolenStatusLabel = new Label(stolenStatus.getName() + ":");
        final Label stolenStatusDisplay = new Label();
        FXHelper.bind(stolenStatusDisplay, stolenStatus,
                this::getStolenStatusString);

        final Button toggleMonitorButton = new Button("Toggle Monitor");
        toggleMonitorButton.setOnAction(this::toggleMonitor);
        FXHelper.bind(toggleMonitorButton.disableProperty(), clientView.loginStatusProperty(),
                this::isToggleDisabled);

        final GridPane content = new GridPane();
        FXHelper.setupAndFillGridPane(content, 5, 10, 2,
                monitorStatusLabel, monitorStatusDisplay,
                stolenStatusLabel, stolenStatusDisplay,
                toggleMonitorButton
        );

        this.setContent(content);
        this.setClosable(false);
    }

    private String getTabTitle() {
        return getBoatName() + " monitor";
    }

    private String getMonitorStatusString() {
        return getMonitorStatus() ? "Active" : "Inactive";
    }

    private String getStolenStatusString() {
        return getStolenStatus() ? "STOLEN" : "ok";
    }

    private boolean isToggleDisabled() {
        return !clientView.getLoginStatus();
    }

    public FXUserUI getClientView() {
        return clientView;
    }

    /** <p>Resets the various fields to their default values.</p> */
    public void clear() {
        setBoatName(UNKNOWN_BOAT);
        setMonitorStatus(false);
        setStolenStatus(false);
    }

    /** <p>The action to be performed when pressing the "toggle monitor" button.
     * Here, create and display a {@link ToggleMonitorPopup}.</p> */
    private void toggleMonitor(ActionEvent event) {
        new ToggleMonitorPopup(this).popup();
    }

    // Boat name accessors

    public String getBoatName() {
        return boatName.getValue();
    }

    public void setBoatName(String boatName) {
        this.boatName.setValue(boatName);
    }

    public Property<String> boatNameProperty() {
        return boatName;
    }

    // Monitor status accessors

    public Boolean getMonitorStatus() {
        return monitorStatus.getValue();
    }

    public void setMonitorStatus(Boolean monitorStatus) {
        this.monitorStatus.setValue(monitorStatus);
    }

    public Property<Boolean> monitorStatusProperty() {
        return monitorStatus;
    }

    // Stolen status accessors

    public Boolean getStolenStatus() {
        return stolenStatus.getValue();
    }

    public void setStolenStatus(Boolean stolenStatus) {
        this.stolenStatus.setValue(stolenStatus);
    }

    public Property<Boolean> stolenStatusProperty() {
        return stolenStatus;
    }



}

package fr.ensta.fx.boatmonitoring.controlcenter;

import fr.ensta.fx.boatmonitoring.FXControlCenterUI;
import fr.ensta.fx.boatmonitoring.util.FXHelper;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.layout.GridPane;

/** <p>A tab bound to a {@link FXControlCenterUI}.</p>
 *
 * <p>It displays connected accounts, monitored and stolen boats.</p>
 *
 * <p>See its method {@link #initView} for an illustration of the elliptic syntax
 * allowed by {@link FXHelper#setupAndFillGridPane}.</p>
 *
 * @author <a href="mailto:luka.le_roux@ensta-bretagne.fr">Luka Le Roux</a>
 */
public class CurrentSituationTab extends Tab {

    private final FXControlCenterUI serverView;

    public CurrentSituationTab(FXControlCenterUI serverView) {
        super("Current Situation");
        this.serverView = serverView;
        initView();
    }

    /** <p>Creates the various {@code Label}s and {@code ListView}s displayed by this view.</p>
     *
     * <p>The {@code GridPane} is filled using {@link FXHelper#setupAndFillGridPane} as follows:
     *
     * <pre><code>
     // Layout
     final GridPane content = new GridPane();
     FXHelper.setupAndFillGridPane(content, 5, 10, 1,
         connectedAccountLabel,
         connectedAccountView,
         new Label(),
         monitoredBoatLabel,
         monitoredBoatView,
         new Label(),
         stolenBoatsLabel,
         stolenBoatView
     );
     * </code></pre>
     *
     * Since it has only 1 column, those {@code new Label()} act as empty rows.
     * </p>
     *
     * <p>Note how are passed the parameters of the methods. This type of syntax
     * is called "elliptic" and is allowed by the three dots in the {@link FXHelper#setupAndFillGridPane method}
     * signature. It's syntactic sugar to avoid having to explicitly create a {@code Control[]}.
     * </p>
     */
    private void initView() {

        // Labels
        final Label connectedAccountLabel = new Label("Connected Accounts");
        final Label monitoredBoatLabel = new Label("Monitored Boats");
        final Label stolenBoatsLabel = new Label("Stolen Boats");

        // ListViews
        final ListView<String> connectedAccountView = new ListView<>();
        connectedAccountView.setItems(serverView.getConnectedAccountList());
        connectedAccountView.setPrefWidth(1000);

        final ListView<String> monitoredBoatView = new ListView<>();
        monitoredBoatView.setItems(serverView.getMonitoredBoatList());
        monitoredBoatView.setPrefWidth(1000);

        final ListView<String> stolenBoatView = new ListView<>();
        stolenBoatView.setItems(serverView.getStolenBoatList());
        stolenBoatView.setPrefWidth(1000);

        // Layout
        final GridPane content = new GridPane();
        FXHelper.setupAndFillGridPane(content, 5, 10, 1,
                connectedAccountLabel,
                connectedAccountView,
                new Label(),
                monitoredBoatLabel,
                monitoredBoatView,
                new Label(),
                stolenBoatsLabel,
                stolenBoatView
        );

        // Settings
        this.setClosable(false);

        // Bind pane to the tab
        this.setContent(content);

    }

}
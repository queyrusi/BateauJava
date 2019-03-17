package fr.ensta.fx.boatmonitoring.controlcenter;

import fr.ensta.fx.boatmonitoring.FXControlCenterUI;
import fr.ensta.fx.boatmonitoring.util.FXHelper;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.layout.GridPane;

/**<p>A tab bounds to a {@link FXControlCenterUI}.</p>
 *
 * <p>It displays the existing accounts and boats.</p>
 *
 * @author <a href="mailto:luka.le_roux@ensta-bretagne.fr">Luka Le Roux</a>
 */
public class DataBaseTab extends Tab {

    private final FXControlCenterUI serverView;

    public DataBaseTab(FXControlCenterUI serverView) {
        super("Data Base");
        this.serverView = serverView;
        initView();
    }

    private void initView() {

        // Labels
        final Label accountsLabel = new Label("Accounts");
        final Label boatsLabel = new Label("Boats");

        // ListViews
        final ListView<String> accountsView = new ListView<>();
        accountsView.setItems(serverView.getAccountList());
        accountsView.prefWidth(1000);

        final ListView<String> boatsView = new ListView<>();
        boatsView.setItems(serverView.getBoatList());
        boatsView.prefWidth(1000);

        // Layout
        final GridPane content = new GridPane();
        FXHelper.setupAndFillGridPane(content, 0, 10, 1,
                accountsLabel,
                accountsView,
                new Label(),
                boatsLabel,
                boatsView
        );

        // Settings
        this.setClosable(false);

        // Bind pane to the tab
        this.setContent(content);

    }

}

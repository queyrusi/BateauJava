package fr.ensta.fx.boatmonitoring.generic;

import fr.ensta.fx.boatmonitoring.util.FXHelper;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.layout.GridPane;

/**
 * <p>A tab bound to a {@link FXGenericUI}.</p>
 *
 * <p>It displays the list of logs.</p>
 *
 * @author <a href="mailto:luka.le_roux@ensta-bretagne.fr">Luka Le Roux</a>
 */
public class LogTab extends Tab {

    private final FXGenericUI genericView;

    public LogTab(FXGenericUI genericView) {
        super("Logs");
        this.genericView = genericView;
        initView();
    }

    private void initView() {

        // ListView
        final ListView<String> logView = new ListView<>();
        logView.setItems(genericView.getLogList());
        logView.setPrefWidth(1000);

        // Layout
        final GridPane content = new GridPane();
        FXHelper.setupAndFillGridPane(content, 0, 10, 1, logView);

        // Settings
        this.setClosable(false);

        // Bind pane to the tab
        this.setContent(content);

    }

}

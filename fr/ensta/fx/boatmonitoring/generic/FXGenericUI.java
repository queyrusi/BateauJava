package fr.ensta.fx.boatmonitoring.generic;

import fr.ensta.fx.boatmonitoring.util.FXHelper;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.effect.BlendMode;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.util.List;

/** <p>Super class of all the provided UIs.</p>
 *
 * <p>
 *     <b><u>Supports:</u></b>
 *     <ul>
 *         <li>Pausing/resuming the application</li>
 *         <li>Displaying a warning</li>
 *         <li>Providing a list of logs with its view</li>
 *     </ul>
 * </p>
 *
 * <p>
 *     <b><u>Noteworthy accessors:</u></b>
 *     <ul>
 *         <li>{@link #getRootPane()} - The pane to use while creating the scene to display</li>
 *         <li>{@link #getMenus()} - Its list of menus, can be modified</li>
 *         <li>{@link #getTabs()} - Its list of tabs, can be modified</li>
 *         <li>{@link #getLogList()} - The observable list of logs ({@code String}), can be modified</li>
 *     </ul>
 * </p>
 *
 * <p>
 *     <b><u>Feature API:</u></b>
 *     <ul>
 *         <li>{@link #pause(String pauseMessage)}</li>
 *         <li>{@link #resume()}</li>
 *         <li>{@link #displayWarning(String warningTitle, String warningText)}</li>
 *     </ul>
 * </p>
 *
 * <p>
 *     <b><u>Implementation:</u></b><br/>
 *     To support the pause feature ({@link #pause} and {@link #resume} methods),
 *     this UI wraps the {@link #mainPane} into its {@link #rootPane}, adding an
 *     {@link #overlayPane} on top. The later is semi transparent and is only
 *     visible when the application is paused.<br/>
 *     The implementation of {@link #displayWarning} creates and opens a {@link WarningPopup},
 *     effectively locking the application until that popup is closed.<br/>
 *     The classes directly extending this one are:
 *     <ul>
 *         <li>{@link fr.ensta.fx.boatmonitoring.user.FXUserUI FXUserUI}</li>
 *         <li>{@link fr.ensta.fx.boatmonitoring.FXControlCenterUI FXControlCenterUI}</li>
 *     </ul>
 *     {@link fr.ensta.fx.boatmonitoring.FXBoatUI FXBoatUI} and
 *     {@link fr.ensta.fx.boatmonitoring.FXHomeUI FXHomeUI} extend this generic UI class
 *     indirectly by extending {@link fr.ensta.fx.boatmonitoring.user.FXUserUI FXUserUI} first.
 * </p>
 *
 * @author <a href="mailto:luka.le_roux@ensta-bretagne.fr">Luka Le Roux</a>
 */
public class FXGenericUI extends Application {

    /**
     * <p>Holds the {@link #mainPane} with the {@link #overlayPane} on top.</p>
     *
     * <p>
     *     Needs to be accessed to construct the {@code Scene} for the application. For example:
     *     <pre><code>
     final Scene myScene = new Scene(getRootPane(), 400, 400);
           </code></pre>
     * </p>
     *
     *  <p>Getter: {@link #getRootPane()}</p>
     */
    private StackPane rootPane;

    /**
     * <p>Holds the {@link #menuBar} and the {@link #tabPane}.</p>
     *
     * <p>It doesn't need to be accessed (but by the pause feature)
     * , as changes are applied to his children.</p>
     */
    private GridPane mainPane;

    /**
     * <p>The menu bar displayed at the top of the window (if not empty).</p>
     *
     * <p>Its list of menus needs be accessed for adding or removing menus.</p>
     *
     * <p>Getter: {@link #getMenus()}</p>
     */
    private MenuBar menuBar;

    /**
     * <p>
     *     The pane holding the various panes displayed
     *     depending on the specifics of the UI.
     * </p>
     *
     * <p>Its list of tabs needs be accessed for adding or removing tabs.</p>
     *
     * <p>Getter: {@link #getTabs()}</p>
     */
    private TabPane tabPane;

    /**
     * <p>The pane displayed on top of the {@link #mainPane}.<br/>
     * Only shows his {@link #pauseLabel} and progressIndicator when the application is paused</p>
     *
     * <p>Doesn't need to be accessed outside of the pause feature.</p>
     *
     * @see #pause(String)
     * @see #resume()
     */
    private GridPane overlayPane;

    /**
     * <p>The label holding the message to be displayed during a pause</p>
     *
     * <p>Doesn't need to be accessed outside of the pause feature.</p>
     *
     * @see #pause(String)
     */
    private Label pauseLabel;


    /**
     * <p>The list holding the logs to be displayed.</p>
     *
     * <p>Since it's an observable list, its {@code ListView} will update automatically.</p>
     *
     * <p>Getter: {@link #getLogList()}</p>
     */
    private final ObservableList<String> logList;

    /**
     * <p>The default constructor.</p>
     *
     * <p>Must only initialize data like lists and properties.</p>
     *
     * <p>Graphical element are to be created by the {@link #start(Stage)} method.</p>
     */
    public FXGenericUI() {
        logList = FXCollections.observableArrayList();
    }

    /** @see #rootPane */
    public StackPane getRootPane() {
        return rootPane;
    }

    /** @see #menuBar */
    public List<Menu> getMenus() {
        return menuBar.getMenus();
    }

    /** @see #tabPane */
    public List<Tab> getTabs() {
        return tabPane.getTabs();
    }

    /** @see #logList */
    public ObservableList<String> getLogList() {
        return logList;
    }

    /**
     * <p>Set the text of {@link #pauseLabel} to pauseMessage.</p>
     *
     * <p>Disable {@link #mainPane} and make {@link #overlayPane} visible.</p>
     */
    public void pause(String pauseMessage) {
        pauseLabel.setText(pauseMessage);
        mainPane.setDisable(true);
        overlayPane.setVisible(true);
    }

    /** <p>Enable {@link #mainPane} and hide {@link #overlayPane}.</p> */
    public void resume() {
        mainPane.setDisable(false);
        overlayPane.setVisible(false);
    }

    /** <p>Creates and shows a blocking warning window.</p> */
    public void displayWarning(String warningTitle, String warningMessage) {
        new WarningPopup(warningTitle, warningMessage).popup();
    }

    /**
     * <p>The actual UI construction.</p>
     *
     * <p>
     *     Note it doesn't create a scene nor does it actually start.
     *     This is the responsibility of the classes inheriting this one.
     * </p>
     */
    @Override
    public void start(Stage primaryStage) throws Exception {

        menuBar = new MenuBar();

        tabPane = new TabPane();
        getTabs().add(new LogTab(this));

        mainPane = new GridPane();
        mainPane.add(menuBar, 0, 0);
        mainPane.add(tabPane, 0 , 1);

        pauseLabel = new Label();
        ProgressIndicator pauseIndicator = new ProgressIndicator();

        overlayPane = new GridPane();
        FXHelper.setupAndFillGridPane(overlayPane, 2, pauseIndicator, pauseLabel);
        // Semi-transparency
        overlayPane.setBlendMode(BlendMode.DARKEN);
        // Not visible at initialization
        overlayPane.setVisible(false);

        rootPane = new StackPane();
        // Order matters. The overlayPane has to be at the front.
        rootPane.getChildren().add(mainPane);
        rootPane.getChildren().add(overlayPane);

    }


}

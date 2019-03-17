package fr.ensta.fx.boatmonitoring.demo;

import fr.ensta.fx.boatmonitoring.generic.FXGenericUI;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tab;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * <p>
 *     Shows how to create your own UI with {@link FXGenericUI} as a starting point.
 *     See {@link #start its start method javadoc} or the source code for more details.
 * </p>
 *
 * <p>
 *     As such it offers a menu bar, tabs (including one for logs), ability to display
 *     warnings and the pause/resume feature.
 * </p>
 */
public class CustomHelloWorld extends FXGenericUI {

    private Stage myStage;

    /**
     * <p>
     *     This overridden method is the {@code main} of any Java FX Application.
     *     It is responsible of
     *     <ul>
     *         <li>creating the various graphical elements</li>
     *         <li>creating the scene to be used by its {@code primaryStage} argument</li>
     *         <li>setting everything as needed</li>
     *         <li>calling {@code primaryStage.show();} at the end</li>
     *     </ul>
     * </p>
     *
     * <p>
     *     It is important to note that this method has to be called from a Java
     *     FX Application thread. Hence why you must directly or indirectly extend
     *     {@link javafx.application.Application javafx.application.Application} to
     *     work with this framework.
     * </p>
     *
     * @see fr.ensta.fx.boatmonitoring.demo
     *
     * @param primaryStage a Java FX object used as the container of the graphical view
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        // Calls its super implementation (FXGenericUI) to setup
        // the various handlers (menus, tabs, pause, warning, ...) correctly
        super.start(primaryStage);

        // Saving the stage for later use (to quit)
        myStage = primaryStage;

        // Button creation and setup
        // Note the syntax used to point the method to be called on click
        final Button myButton = new Button("Click me");
        myButton.setOnAction(this::sayHello);

        // Pane creation, adding the button to it
        final StackPane myPane = new StackPane();
        myPane.setAlignment(Pos.CENTER);
        myPane.getChildren().add(myButton);

        // Tab creation, setting the pane as its content
        final Tab myTab = new Tab("Clicker");
        myTab.setClosable(false);
        myTab.setContent(myPane);

        // Adding the tab to the view (method inherited from FXGenericUI)
        getTabs().add(myTab);

        // MenuItem creation
        final MenuItem myMenuItem = new MenuItem("Quit");
        myMenuItem.setOnAction(this::quit);

        // Menu creation, adding the menu item to it
        final Menu myMenu = new Menu("Menu");
        myMenu.getItems().add(myMenuItem);

        // Adding the menu to the view (method inherited from FXGenericUI)
        getMenus().add(myMenu);

        // Scene creation using the FXGenericUI root pane
        final Scene scene = new Scene(getRootPane(), 400, 400);

        // Last few steps, always the same for JavaFX applications
        primaryStage.setScene(scene);
        primaryStage.setTitle("Custom Hello World Demo");
        primaryStage.show();
    }

    private void sayHello(ActionEvent ignoredEvent) {
        getLogList().add("Hello World");
        System.out.println("Hello World");
    }

    private void quit(ActionEvent ignoredEvent) {
        myStage.close();
    }

}

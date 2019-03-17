package fr.ensta.fx.boatmonitoring.demo;

import fr.ensta.fx.boatmonitoring.generic.FXGenericUI;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * <p>A demo to illustrate the pause/resume feature of {@link FXGenericUI}.</p>
 *
 * <p>
 *     Its code source also shows how to:
 *     <ul>
 *         <li>Start a raw {@code FXGenericUI}</li>
 *         <li>Write a Java FX Application without the aforementioned UI helper</li>
 *     </ul>
 * </p>
 *
 * @see fr.ensta.fx.boatmonitoring.demo
 */
public class PauseDemo extends Application {

    private FXGenericUI genericUI;
    private ToggleButton pauseToggleButton;

    @Override
    public void start(Stage primaryStage) throws Exception {

        pauseToggleButton = new ToggleButton("Pause");
        pauseToggleButton.setOnAction(this::togglePause);

        final StackPane myPane = new StackPane();
        myPane.setAlignment(Pos.CENTER);
        myPane.getChildren().add(pauseToggleButton);

        startGenericUI();

        final Scene myScene = new Scene(myPane, 200, 100);
        primaryStage.setScene(myScene);
        primaryStage.setTitle("Pause Demo");
        primaryStage.show();
    }

    private void togglePause(ActionEvent event) {
        if (pauseToggleButton.isSelected()) {
            genericUI.pause("application is paused");
            pauseToggleButton.setText("Resume");
        } else {
            genericUI.resume();
            pauseToggleButton.setText("Pause");
        }
    }

    private void startGenericUI() throws Exception {
        genericUI = new FXGenericUI();

        final Stage genericUIStage = new Stage();
        genericUI.start(genericUIStage);

        final Scene genericUIScene = new Scene(genericUI.getRootPane(), 300, 400);
        genericUIStage.setScene(genericUIScene);
        genericUIStage.setTitle("Generic UI");
        genericUIStage.show();
    }

}

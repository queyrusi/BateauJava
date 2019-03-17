package fr.ensta.fx.boatmonitoring.generic;

import fr.ensta.fx.boatmonitoring.user.LoginPopup;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * <p>Abstract class to make coding popups easier.</p>
 *
 * <p>Only requires its subclasses to implements {@link #getPane()}.</p>
 *
 * <p>Provides the {@link #popup()} and {@link #close()} methods to show
 * a blocking window and close it respectively.</p>
 *
 * <p>For a simple example, see {@link WarningPopup}.</p>
 *
 * <p>For a more advanced example, see {@link LoginPopup}.</p>
 *
 * @see fr.ensta.fx.boatmonitoring.user.ChangePasswordPopup ChangePasswordPopup
 * @see fr.ensta.fx.boatmonitoring.user.CreateAccountPopup CreateAccountPopup
 * @see fr.ensta.fx.boatmonitoring.user.ToggleMonitorPopup ToggleMonitorPopup
 *
 * @author <a href="mailto:luka.le_roux@ensta-bretagne.fr">Luka Le Roux</a>
 */
public abstract class AbstractPopup extends Application {

    private final String title;

    private Stage stage;

    /**
     * <p>The only method that requires an implementation for a class extending {@link AbstractPopup}.</p>
     *
     * <p>See the class javadoc for links to various examples.</p>
     *
     * @return the pane to display
     */
    protected abstract Pane getPane();

    public AbstractPopup(String title) {
        this.title = title;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        stage = primaryStage;
        primaryStage.initModality(Modality.APPLICATION_MODAL);
        primaryStage.setTitle(title);
        final Scene scene = new Scene(getPane(), 300, 250);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /** <p>Display the popup. It locks the main application until closed.</p> */
    public void popup() {
        final Stage newStage = new Stage();
        try {
            start(newStage);
        } catch (Exception e) {
            final RuntimeException newException = new IllegalStateException("Failed to display popup");
            newException.addSuppressed(e);
            throw newException;
        }
    }

    /**
     * <p>Close the popup, hence lifting the lock on the main application.</p>
     *
     * <p>It's the exact same as closing it via the standard window controls.</p>
     */
    public void close() {
        if (stage != null) {
            stage.close();
        }
    }

}

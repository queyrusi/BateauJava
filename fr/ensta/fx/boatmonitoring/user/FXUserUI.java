package fr.ensta.fx.boatmonitoring.user;

import java.util.Observable;
import java.util.Observer;

import fr.ensta.fx.boatmonitoring.demo.TotoHomeDemo;
import fr.ensta.fx.boatmonitoring.generic.FXGenericUI;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.Tab;
import javafx.stage.Stage;

/**
 * <p>
 *     <b><u>Abstract methods:</u></b><br/>
 *     Common super class for both the boat and home user HMIs. It requires the
 *     implementation of some methods to handle various requests. They are called
 *     through the account management menu and boat monitor tabs.
 *     <ul>
 *         <li>{@link #login(String accountName, String password)}</li>
 *         <li>{@link #logout()}</li>
 *         <li>{@link #createAccount(String accountName, String password)}</li>
 *         <li>{@link #changePassword(String oldPassword, String newPassword)}</li>
 *         <li>{@link #toggleMonitor(BoatMonitorTab boatMonitor, String password)}</li>
 *    </ul>
 *    Those methods are responsible of:
 *    <ul>
 *        <li>Checking the correctness of the provided parameters</li>
 *        <li>Update the UI as needed</li>
 *        <li>Using {@link #pause}, {@link #resume} and {@link #displayWarning} when appropriate</li>
 *    </ul>
 *    See the {@link #login login method javadoc} for a more precise description and the
 *    {@link TotoHomeDemo} demo for an example of functional login and logout.
 * </p>
 *
 * <p>
 *     <b><u>Provided properties</u></b>
 *     <ul>
 *         <li>{@link #loginStatus}</li>
 *         <li>{@link #accountName}</li>
 *     </ul>
 * </p>
 *
 * @author <a href="mailto:luka.le_roux@ensta-bretagne.fr">Luka Le Roux</a>
 */
public abstract class FXUserUI extends FXGenericUI {

    /**
     * <p>This property holds a {@code Boolean} matching the login status.</p>
     *
     * <p>
     *     <b><u>General information about properties:</u></b><br/>
     *     A {@code Property<T>} holds a value of type {@code T}. It can be accessed via {@code getValue()}
     *     and modified via {@code setValue(T newValue)}.<br/>
     *     It can also be observed for changes via {@code addListener(listener)} or {@code bind(binding)}.
     *     See the {@link fr.ensta.fx.boatmonitoring.util.FXHelper#bind FXHelper.bind} method for more details.
     * </p>
     *
     * <p>
     *     By convention for a given {@code Property<T> myProperty} the following methods are provided by its {@code bean}
     *     (the object holding the property, here an instance of {@code FXUserUI}):
     *     <ul>
     *         <li>
     *             {@code Property<T> myPropertyProperty()} to retrieve the property
     *             (ex: {@link #loginStatusProperty()})
     *         </li>
     *         <li>
     *             {@code T getMyProperty()} to access its value directly
     *             (ex: {@link #getLoginStatus()})
     *         </li>
     *         <li>
     *             {@code void setMyProperty(T newValue)} to modify its value directly (still triggers observers)
     *             (ex: {@link #setLoginStatus(boolean)})
     *         </li>
     *     </ul>
     *
     * </p>
     *
     * @see #accountName FXUserUI.accountName
     * @see BoatMonitorTab#boatName BoatMonitorTab.boatName
     * @see BoatMonitorTab#monitorStatus BoatMonitorTab.monitorStatus
     * @see BoatMonitorTab#stolenStatus BoatMonitorTab.stolenStatus
     */
    private final Property<Boolean> loginStatus;

    /**
     * <p>This property holds the name of the active account.</p>
     *
     * <p>
     *     See {@link fr.ensta.fx.boatmonitoring.user.FXUserUI#loginStatus loginStatus}
     *     for general information about properties.
     * </p>
     */
    private final Property<String> accountName;

    public FXUserUI() {
        super();
        loginStatus = new SimpleBooleanProperty(this, "Login Status", false);
        accountName = new SimpleStringProperty(this, "Account Name", null);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        super.start(primaryStage);

        getMenus().add(new AccountManagementMenu(this));
    }

    // The various methods to implement so the UI can be functional

    /**
     * <p>
     *     This method is called upon a login request. Both
     *     {@link fr.ensta.fx.boatmonitoring.FXHomeUI FXHomeUI} and
     *     {@link fr.ensta.fx.boatmonitoring.FXBoatUI FXBoatUI} override
     *     it to show a warning that a correct implementation is sill missing:
     *     <pre><code>
    {@literal @}Override
     public void login(String accountName, String password) {
         this.displayWarning("Login", "Unsupported operation");
     }
     *     </code></pre>
     *     It has several responsibilities:
     *     <ul>
     *         <li>Checks that the {@code accountName} exists</li>
     *         <li>Checks that the {@code password} match</li>
     *         <li>Update {@link #loginStatus} and {@link #accountName} properties if correct</li>
     *         <li>Make use of {@link #displayWarning} if not</li>
     *         <li>{@link #pause Pauses} and {@link #resume resumes} the application if needed</li>
     *     </ul>
     *     See {@link fr.ensta.fx.boatmonitoring.demo.TotoHomeDemo TotoHomeDemo}
     *     for a simple example of an implementation.
     * </p>
     */
    public abstract void login(String accountName, String password);

    /** @see #login */
    public abstract void logout();

    /** @see #login */
    public abstract void createAccount(String accountName, String password);

    /** @see #login */
    public abstract void changePassword(String oldPassword, String newPassword);

    /** @see #login */
    public abstract void toggleMonitor(BoatMonitorTab boatMonitor, String password);

    // loginStatus accessors

    public Boolean getLoginStatus() {
        return loginStatus.getValue();
    }

    public void setLoginStatus(boolean loginStatus) {
        this.loginStatus.setValue(loginStatus);
    }

    public Property<Boolean> loginStatusProperty() {
        return loginStatus;
    }

    // accountName accessors

    public String getAccountName() {
        return accountName.getValue();
    }

    public void setAccountName(String accountName) {
        this.accountName.setValue(accountName);
    }

    public Property<String> accountNameProperty() {
        return accountName;
    }


}

package fr.ensta.fx.boatmonitoring.demo;


import fr.ensta.fx.boatmonitoring.FXHomeUI;
import fr.ensta.fx.boatmonitoring.generic.FXGenericUI;
import fr.ensta.fx.boatmonitoring.user.BoatMonitorTab;


/**
 * <p>A simple demo to get started.</p>
 *
 * <p>It's Toto's home UI. It supports login and logout only.</p>
 *
 * <p>
 *     The {@link DataModel DataModel} internal class is our data model. It is declared as a
 *     private field of our application and is initialized in the default constructor:
 *
 *     <pre><code>
 private DataModel data;

 public TotoDepartDemo() {
     // Do not forget to call the constructor from its super class
     super();

     // Data model initialization
     data = new DataModel();
 }
 *     </code></pre>
 * </p>
 *
 * <p>
 *     To support the login feature, one must override the {@link FXHomeUI#login} method.
 *     It should check that the provided account name exists and that the password match.
 * </p>
 *
 * <p>
 *     Make use of the inherited methods {@link FXGenericUI#displayWarning},
 *     {@link FXGenericUI#pause}  and {@link FXGenericUI#resume} when appropriate.
 * </p>
 *
 * <p>
 *     Don't forget to update the related properties of the UI. For example:
 *
 *     <pre><code>
{@literal @}Override
 public void login(String accountName, String password) {
     if (accountName.equals("Toto")) {
         if (password.equals(dataModel.password)) {
             this.setAccountName("Toto");
             this.setLoginStatus(true);

             // This is how to add a boat tab to the view, Toto owns only one
             final BoatMonitorTab boatMonitor = this.createAndAddMonitor();
             boatMonitor.setBoatName(dataModel.boatName);

         } else {
             this.displayWarning("Login", "Password incorrect, try '" + dataModel.password + "' instead.");
         }
     } else {
         this.displayWarning("Login", "Only Toto is able to use this UI.");
     }
 }
 *     </code></pre>
 * </p>
 *
 * <p>
 *     For a list of the method to override to implements the various feature see
 *     {@link fr.ensta.fx.boatmonitoring.user.FXUserUI FXUserUI}.<br/>
 *     {@link fr.ensta.fx.boatmonitoring.FXHomeUI FXHomeUI} and
 *     {@link fr.ensta.fx.boatmonitoring.FXBoatUI FXBoatUI}
 *     implement them like so:
 *     <code><pre>
{@literal @}Override
 public void login(String accountName, String password) {
     this.displayWarning("Login", "Unsupported operation");
 }
 *     </pre></code>
 * </p>
 *
 * @see fr.ensta.fx.boatmonitoring.demo
 *
 * @author <a href="mailto:luka.le_roux@ensta-bretagne.fr">Luka Le Roux</a>
 */
public class TotoDepartDemo extends FXHomeUI {

    /** We will need access to our dataModel later, so we need it as a field of this class */
//    private TotoHomeModel dataModel;

	public class DataModel {

	    private String accountName = "Toto";
	    private String password = "0000";
	    private String boatName = "Foobar";
	    
	}

	DataModel data;
	
    public TotoDepartDemo() {
        // Do not forget to call the constructor from its super class
        super();

        data = new DataModel();
        
    }

    /**
     * Overrides the login method and makes it functional,
     * testing the provided parameter correctness and updating the UI as necessary.
     *
     * @see fr.ensta.fx.boatmonitoring.user.FXUserUI#login FXUserUI.login
     */
    @Override
    public void login(String accountName, String password) {
        if (accountName.equals(data.accountName )) {
            if (password.equals(data.password )) {
                this.setAccountName(accountName);
                this.setLoginStatus(true);

                // This is how to add a boat tab to the view, Toto owns only one
                final BoatMonitorTab boatMonitor = this.createAndAddMonitor();
                boatMonitor.setBoatName(data.boatName );

            } else {
                this.displayWarning("Login", "Password incorrect, try '" + data.password  + "' instead.");
            }
        } else {
            this.displayWarning("Login", "Only " + data.accountName + " is able to use this UI.");
        }
    }

    /** Similar to {@link #login}. */
    @Override
    public void logout() {
        this.removeAllMonitors();
        this.setLoginStatus(false);
        this.setAccountName(null);
    }
    
    
    public static void main( String[] args ) {
    	
    	launch(args);
    	
    }

}

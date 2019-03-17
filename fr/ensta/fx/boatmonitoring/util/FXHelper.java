package fr.ensta.fx.boatmonitoring.util;

import fr.ensta.fx.boatmonitoring.controlcenter.CurrentSituationTab;
import fr.ensta.fx.boatmonitoring.user.BoatMonitorTab;
import javafx.beans.Observable;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.ObjectBinding;
import javafx.beans.property.Property;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Control;
import javafx.scene.control.Labeled;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;

import java.util.concurrent.Callable;

/** <p>Class with static utilities to ease the use of some Java FX features</p>
 *
 * @author <a href="mailto:luka.le_roux@ensta-bretagne.fr">Luka Le Roux</a>
 */
public class FXHelper {

    /**
     * <p>The provided GridPane is set with center alignment, gaps and padding.</p>
     *
     * <p>Then the controls are added from left to right, then top to bottom.</p>
     *
     * <p>On the last row, controls are forced right as much as possible.</p>
     *
     * <p>The method supports elliptic syntax, see the {@link CurrentSituationTab#initView}
     * method for a documented illustration. It is widely used by the classes in this package.</p>
     *
     * @param gridPane the {@code GridPane} to setup and fill
     * @param width an {@code int} representing the wanted number of columns
     * @param gap an {@code int} representing the number of pixels between the controls
     * @param padding an {@code int} representing the number of pixels around the grid
     * @param controlArray the {@code Control[]} holding the controls to fill the grid with
     */
    public static void setupAndFillGridPane(GridPane gridPane, int gap, int padding, int width, Control... controlArray) {

        // Center alignment, gaps and padding
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(gap);
        gridPane.setVgap(gap);
        gridPane.setPadding(new Insets(padding, padding, padding, padding));

        // Setting columns to be equally wide
        final ColumnConstraints columnConstraints = new ColumnConstraints();
        columnConstraints.setPercentWidth(100/width);
        for (int i = 0; i < width; i++) {
            gridPane.getColumnConstraints().add(columnConstraints);
        }

        // Filling the grid
        final int lastRow = controlArray.length / width;
        final int lastRowOffset = (lastRow * width) + width - controlArray.length;
        for (int i = 0; i < controlArray.length; i++) {
            final int rowIndex = i / width;

            // columnOffset may only be != 0 on the last row.
            // Effectively forcing the controls as much right as possible.
            final int columnOffset = rowIndex != lastRow ? 0 :  lastRowOffset;

            final int columnIndex = (i % width) + columnOffset;
            gridPane.add(controlArray[i], columnIndex, rowIndex);
        }
    }

    /**
     * @see #setupAndFillGridPane(GridPane, int, int, int, Control...)
     */
    public static void setupAndFillGridPane(GridPane gridPane, int width, Control... controlArray) {
        setupAndFillGridPane(gridPane, 10, 25, width, controlArray);
    }

    /** <p>Binds a property to an observable with a method to update the former's value.</p>
     *
     * <p>When the value of the observable changes, the target property value is set
     * to the result of the method call.</p>
     *
     * <p>The parametric typing {@code <T>} in its signature enforces that the {@code Property} and the
     * {@code Callable} have the same parametric type.</p>
     *
     * <p>Here's an example from {@link BoatMonitorTab#BoatMonitorTab BoatMonitorTab constructor}:
     *
     *     <pre><code>
     monitorStatus = new SimpleBooleanProperty(this, "Monitor status", false);
     final Label monitorStatusDisplay = new Label();
     FXHelper.bind(monitorStatusDisplay, monitorStatus, this::getMonitorStatusString);
     *     </code></pre>
     *
     *     The above synchronize the text displayed by the {@code Label monitorStatusDisplay} with
     *     the {@code Boolean} held by the {@code Property<> monitorStatus}.<br/>
     *     When the later changes {@link BoatMonitorTab#getMonitorStatusString getMonitorStatusString()}
     *     is called and the {@code String} it returns is the new text to display for
     *     {@code monitorStatusDisplay}.<br/>
     *     Hence, calling {@code this.setMonitorStatus(true)} will update the text to {@code "Active"}.
     *     More precisely the following will happen:<br/>
     *     <lu>
     *         <li>{@code monitorStatus.setValue(true)}</li>
     *         <li>{@code monitorStatus} broadcasts the fact it has changed</li>
     *         <li>When notified {@code monitorStatusDisplay} calls {@code this.getMonitorStatusString()}</li>
     *         <li>And sets its text to the result of this call: {@code "Active"}</li>
     *     </lu>
     * </p>
     *
     * @param target the {@code Property<T>} to keep up to date
     * @param observable the {@code Observable} to observe for changes
     * @param computeNewValue a {@code Callable<T>} that returns the new value (any {@code T method()})
     */
    public static <T> void bind(Property<T> target, Observable observable, Callable<T> computeNewValue) {
        final ObjectBinding<T> binding = Bindings.createObjectBinding(computeNewValue, observable);
        target.bind(binding);
    }

    /**
     * @see #bind(Property, Observable, Callable)
     */
    public static void bind(Labeled target, Observable observable, Callable<String> computeNewLabel) {
        bind(target.textProperty(), observable, computeNewLabel);
    }

}

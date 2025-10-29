/*
 * TCSS 305 - Assignment 5
 */

package view;

import javax.swing.Action;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JToggleButton;
import javax.swing.JToolBar;

/**
 * Creates the tool bar to use in the GUI.
 * 
 * @author Ryan Wilkerson
 * @version 3/1/2024
 */
public class PaintToolBar extends JToolBar {

    /** A generated serial version ID. */
    private static final long serialVersionUID = 4627859621931897062L;
    
    /**
     * The button group to hold our buttons.
     */
    private final ButtonGroup myButtonGroup;
    
    /**
     * Initializes the tool bar and our button group.
     */
    public PaintToolBar() {
        super();
        
        myButtonGroup = new ButtonGroup();
    }
    
    /**
     * Creates each button in the tool bar.
     * 
     * @param theAction that represents our tools.
     * @param theIcon that goes with each tool.
     */
    public void createToolBarButton(final Action theAction, final ImageIcon theIcon) {
        final JToggleButton toggleButton = new JToggleButton(theAction);
        
        toggleButton.setIcon(theIcon);
        
        myButtonGroup.add(toggleButton);
        
        add(toggleButton);
    }
    
}

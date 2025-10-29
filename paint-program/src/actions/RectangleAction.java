/*
 * TCSS 305 - Assignment 5
 */

package actions;

import java.awt.Point;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.Action;
import model.RectangleTool;
import model.Tool;
import view.DrawingPanel;

/**
 * The action when the rectangle tool is used.
 * 
 * @author Ryan Wilkerson
 * @version 3/1/2024
 */
public class RectangleAction extends AbstractAction {

    /** A generated serial version ID. */
    private static final long serialVersionUID = -3792435813783524196L;

    /**
     * The drawing panel where we draw rectangles.
     */
    private final DrawingPanel myDrawingPanel;
    
    /**
     * Represents the rectangle tool being used.
     */
    private final Tool myTool;
    
    /**
     * Initializes the rectangle action.
     * 
     * @param theDrawingPanel is the panel we draw rectangles on.
     */
    public RectangleAction(final DrawingPanel theDrawingPanel) {
        super("Rectangle");
        
        putValue(Action.SELECTED_KEY, true);
        
        myDrawingPanel = theDrawingPanel;
        myTool = new RectangleTool(new Point(-1, -1), new Point(-1, -1));
    }

    /**
     * The action that tracks when the rectangle is used.
     */
    @Override
    public void actionPerformed(final ActionEvent theEvent) {
        
        myDrawingPanel.setTool(myTool);
        myDrawingPanel.repaint();

    }

    /**
     * Returns the rectangle tool.
     * 
     * @return the rectangle tool being used.
     */
    public Tool getTool() {
        return myTool;
    }

}

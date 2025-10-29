/*
 * TCSS 305 - Assignment 5
 */

package actions;

import java.awt.event.ActionEvent;
import java.awt.geom.Point2D;
import javax.swing.AbstractAction;
import javax.swing.Action;
import model.EllipseTool;
import model.Tool;
import view.DrawingPanel;

/**
 * The action when the ellipse tool is used.
 * 
 * @author Ryan Wilkerson
 * @version 3/1/2024
 */
public class EllipseAction extends AbstractAction {

    /** A generated serial version ID. */
    private static final long serialVersionUID = -3236677585951200999L;

    /**
     * The starting point of the ellipse.
     */
    private static final Point2D.Double INITIAL_POINT = new Point2D.Double(0, 0);
    
    /**
     * The panel to draw an ellipse on.
     */
    private final DrawingPanel myDrawingPanel;
    
    /**
     * The tool that represents the ellipse.
     */
    private final Tool myTool;
    
    /**
     * Initializes the ellipse action.
     * 
     * @param theDrawingPanel is the panel we draw Ellipses on.
     */
    public EllipseAction(final DrawingPanel theDrawingPanel) {
        super("Ellipse");
        
        putValue(Action.SELECTED_KEY, true);
        
        myDrawingPanel = theDrawingPanel;
        
        myTool = new EllipseTool(INITIAL_POINT, INITIAL_POINT);    
    }
    
    /**
     * The action of drawing the ellipse.
     */
    @Override
    public void actionPerformed(final ActionEvent theEvent) {
        
        myDrawingPanel.setTool(myTool);
        myDrawingPanel.repaint();
    }

    /**
     * Returns the ellipse tool.
     * 
     * @return the ellipse tool being used.
     */
    public Tool getTool() {
        return myTool;
    }

}

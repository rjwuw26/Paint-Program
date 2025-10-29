/*
 * TCSS 305 - Assignment 5
 */

package actions;

import java.awt.event.ActionEvent;
import java.awt.geom.Point2D;
import javax.swing.AbstractAction;
import javax.swing.Action;
import model.LineTool;
import model.Tool;
import view.DrawingPanel;

/**
 * The action for when the line tool is used.
 * 
 * @author Ryan Wilkerson
 * @version 3/1/2024
 */
public class LineAction extends AbstractAction {

    /** A generated serial version ID. */
    private static final long serialVersionUID = -8217872118319916252L;

    /**
     * The initial point of our line.
     */
    private static final Point2D.Double INITIAL_POINT = new Point2D.Double(0, 0);
    
    /**
     * The panel where we draw our line on.
     */
    private final DrawingPanel myDrawingPanel;
    
    /**
     * The tool that represents the line.
     */
    private final Tool myTool;
    
    /**
     * Initializes the line tool.
     * 
     * @param theDrawingPanel is the panel where we draw our line on.
     */
    public LineAction(final DrawingPanel theDrawingPanel) {
        super("Line");
        
        putValue(Action.SELECTED_KEY, true);
        
        myDrawingPanel = theDrawingPanel;
        myTool = new LineTool(INITIAL_POINT, INITIAL_POINT);
    }
    
    /**
     * The action of drawing our line.
     */
    @Override
    public void actionPerformed(final ActionEvent theEvent) {
        
        myDrawingPanel.setTool(myTool);

        myDrawingPanel.repaint();  
    }

    /**
     * Returns our line tool.
     * 
     * @return the line tool being used.
     */
    public Tool getTool() {
        return myTool;
    }

}

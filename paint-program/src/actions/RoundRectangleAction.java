package actions;

import java.awt.Point;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.Action;
import model.RoundRectangleTool;
import model.Tool;
import view.DrawingPanel;

public class RoundRectangleAction extends AbstractAction {

    /** A generated serial version ID. */
    private static final long serialVersionUID = -3436159234390624773L;

    /**
     * The drawing panel where we draw rectangles.
     */
    private final DrawingPanel myDrawingPanel;
    
    /**
     * Represents the rectangle tool being used.
     */
    private final Tool myTool;
    
    /**
     * Initializes our fields.
     * 
     * @param theDrawingPanel
     */
    public RoundRectangleAction(final DrawingPanel theDrawingPanel) {
        super("Round Rectangle");
        
        putValue(Action.SELECTED_KEY, true);
        
        myDrawingPanel = theDrawingPanel;
        myTool = new RoundRectangleTool(new Point(-1, -1), new Point(-1, -1));
    }

    /**
     * The action of drawing our ellipse.
     */
    @Override
    public void actionPerformed(final ActionEvent theEvent) {
        myDrawingPanel.setTool(myTool);
        myDrawingPanel.repaint();
    }
    
    /**
     * Returns the tool being used.
     * 
     * @return the round rectangle tool.
     */
    public Tool getTool() {
        return myTool;
    }
    
}

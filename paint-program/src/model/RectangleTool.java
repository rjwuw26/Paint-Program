/*
 * TCSS 305 - Assignment 5
 */

package model;

import java.awt.Shape;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

/**
 * The tool to create a rectangle.
 * 
 * @author Ryan Wilkerson
 * @version 3/1/2024
 */
public class RectangleTool extends AbstractTool {
    
    /**
     * Initializes the rectangle tool.
     * 
     * @param theStartPoint of the rectangle drawn.
     * @param theEndPoint of the rectangle drawn.
     */
    public RectangleTool(final Point2D theStartPoint, final Point2D theEndPoint) {
        super(theStartPoint, theEndPoint);
      
    }

    @Override
    public Shape getShape() {
        final Rectangle2D.Double rect = new Rectangle2D.Double();
        rect.setFrameFromDiagonal(getStartPoint(), getEndPoint());
    
        return rect;
    }
}

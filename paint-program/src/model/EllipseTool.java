/*
 * TCSS 305 - Assignment 5
 */

package model;

import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;

/**
 * The tool to create an ellipse.
 * 
 * @author Ryan Wilkerson
 * @version 3/1/2024
 */
public class EllipseTool extends AbstractTool {

    /**
     * Initializes the ellipse tool.
     * 
     * @param theStartPoint of the ellipse drawn.
     * @param theEndPoint of the ellipse drawn.
     */
    public EllipseTool(final Point2D theStartPoint, final Point2D theEndPoint) {
        super(theStartPoint, theEndPoint);
    }
    
    @Override
    public Shape getShape() {
        final Ellipse2D.Double ellipse = new Ellipse2D.Double();
        ellipse.setFrameFromDiagonal(getStartPoint(), getEndPoint());
        
        return ellipse;
    }
    
}

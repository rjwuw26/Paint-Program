/*
 * TCSS 305 - Assignment 5
 */

package model;


import java.awt.Shape;
import java.awt.geom.Point2D;

/**
 * Creates the class that shares similar traits amongst all tools.
 * 
 * @author Ryan Wilkerson
 * @version 3/1/2024
 */
public abstract class AbstractTool implements Tool {


    /**
     * The start point of our drawn shape.
     */
    private Point2D myStartPoint;
    
    /**
     * The end point of our drawn shape.
     */
    private Point2D myEndPoint;

    /**
     * Initializes the start and end point of our drawn shape.
     */
    protected AbstractTool(final Point2D theStartPoint, final Point2D theEndPoint) {
        myStartPoint = theStartPoint;
        myEndPoint = theEndPoint;
    }
    
    
    @Override
    public abstract Shape getShape();

    @Override
    public void setStartPoint(final Point2D theStartPoint) {      
        myStartPoint = theStartPoint;
    }

    /**
     * Gets the start point of our drawn shape.
     */
    public Point2D getStartPoint() {
        return myStartPoint;
    }
    

    @Override
    public void setEndPoint(final Point2D theEndPoint) {      
        myEndPoint = theEndPoint;
    }

    /**
     * Gets the end point of our drawn shape.
     */
    public Point2D getEndPoint() {
        return myEndPoint;
    }
    

    
}

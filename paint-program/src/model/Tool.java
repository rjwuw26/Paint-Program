/*
 * TCSS 305 - Assignment 5
 */

package model;

import java.awt.Shape;
import java.awt.geom.Point2D;

/**
 * Creates an interface of a standard tool.
 * 
 * @author Ryan Wilkerson
 * @version 3/1/2024
 */
public interface Tool {


    /**
     * The given shape being drawn.
     * 
     * @return
     */
    Shape getShape();

    /**
     * Sets the start point of our shape.
     * 
     * @param the x and y coordinates of the start point.
     */
    void setStartPoint(Point2D theStartPoint);
    
    /**
     * Sets the end point of our shape.
     * 
     * @param theEndPoint the x and y coordinates of the end point.
     */
    void setEndPoint(Point2D theEndPoint);
    
    /**
     * Gets the starting point we drew.
     * 
     * @return the start point.
     */
    Point2D getStartPoint();
    
    /**
     * Gets the end point we drew.
     * 
     * @return the end point.
     */
    Point2D getEndPoint();
    
}

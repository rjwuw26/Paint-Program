/*
 * TCSS 305 - Assignment 5
 */

package model;

import java.awt.Color;
import java.awt.Shape;

/**
 * A class that houses tool properties.
 * 
 * @author Ryan Wilkerson
 * @version 3/1/2024
 */
public class ToolProp {

    /**
     * The shape we're drawing.
     */
    private final Shape myShape;
    
    /**
     * The thickness of the shape we're drawing.
     */
    private final int myThickness;
    
    /**
     * The color of the shape we're drawing.
     */
    private final Color myColor;
    
    /**
     * Initializes our tool properties.
     * 
     * @param theShape is the shape we are drawing.
     * @param theThickness is the thickness of our shape.
     * @param theColor is the color of our shape.
     */
    public ToolProp(final Shape theShape, final int theThickness, final Color theColor) {
        myShape = theShape;
        myThickness = theThickness;
        myColor = theColor;
    }
    
    /**
     * Returns the given shape.
     * 
     * @return the shape.
     */
    public Shape getShape() {
        return myShape;
    }
    
    /**
     * Returns the given thickness.
     * 
     * @return the thickness.
     */
    public int getThickness() {
        return myThickness;
    }
    
    /**
     * returns the given color.
     * 
     * @return the color.
     */
    public Color getColor() {
        return myColor;
    }
}

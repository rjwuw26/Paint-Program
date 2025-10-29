/*
 * TCSS 305 - Assignment 5
 */

package view;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Line2D;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Deque;
import java.util.LinkedList;
import javax.swing.JPanel;
import model.LineTool;
import model.Tool;
import model.ToolProp;

/**
 * Creates the drawing panel to draw on.
 * 
 * @author Ryan Wilkerson
 * @version 3/8/2024
 */
public class DrawingPanel extends JPanel {

    /** A generated serial version ID. */
    private static final long serialVersionUID = 2312708211118791716L;

    /**
     * The background color of the drawing panel.
     */
    private static final Color BACKGROUND_COLOR = Color.WHITE;
    
    /**
     * The initial thickness of our drawing tool.
     */
    private static final int THICKNESS_INITIAL = 2;
    
    /**
     * The initial point of the tool.
     */
    private static final Point INITIAL_POINT = new Point(0, 0);
    
    /**
     * The digital husky RGB value.
     */
    private static final Color DIGITAL_HUSKY_PURPLE = new Color(50, 0, 110);
    
    /**
     * The digital husky RGB value.
     */
    private static final Color DIGITAL_HUSKY_GOLD = new Color(232, 211, 162);
    
    /**
     * The property change support to use for property change listeners.
     */
    private final PropertyChangeSupport myPCS = new PropertyChangeSupport(this);
     
    /**
     * The tool being used to draw stuff.
     */
    private Tool myTool;
    
    /**
     * The value for whether the grid is on or off.
     */
    private boolean myGridSelection;
    
    /**
     * A list of all the ships we've drawn already.
     */
    private final Deque<ToolProp> myShapesList;
    
    /**
     * The starting position of where we drew.
     */
    private Point myStartPosition;
    
    /**
     * The ending position of where we stopped drawing.
     */
    private Point myEndPosition;
    
    /**
     * The color of our drawn shape.
     */
    private Color myColor;
    
    /**
     * The thickness of our drawn shape.
     */
    private int myThickness;
    
    /**
     * The designated size of our grid.
     */
    private int myGridSize;
    
    /**
     * The spacing of our grid.
     */
    private int mySpacing;
    
    /**
     * The determinant of whether we're dragging the mouse.
     */
    private boolean myDrag;
    
    /**
     * Initializes our fields.
     * 
     * @param theGridSize is the size of the grid.
     * @param theSpacing is the spacing of the grid.
     */
    public DrawingPanel(final int theGridSize, final int theSpacing) {
        
        super();
        
        myColor = DIGITAL_HUSKY_PURPLE;
        
        myDrag = false;
        
        myGridSelection = false;
        
        myTool = new LineTool(INITIAL_POINT, INITIAL_POINT);
        
        myShapesList = new LinkedList<ToolProp>();
        
        myThickness = THICKNESS_INITIAL;
        
        this.myGridSize = theGridSize;
        
        this.mySpacing = theSpacing;
             
        setUpMouseListeners();     
    }
    
    /**
     * Method that creates our mouse listeners.
     */
    private void setUpMouseListeners() {
        addMouseListener(new DrawListener());
        addMouseMotionListener(new DrawListener());
    }

    /**
     * Initializes the tool being used.
     * 
     * @param theTool is the tool we're using.
     */
    public void setTool(final Tool theTool) {
        myTool = theTool;
    }
    
    /**
     * Sets the color of our drawn shape.
     * 
     * @param theColor is the corresponding color we've selected.
     */
    public void setColor(final Color theColor) {
        myColor = theColor;
    }
    
    /**
     * Sets the desired thickness of the shape.
     * 
     * @param theThickness is the corresponding thickness we've selected.
     */
    public void setThickness(final int theThickness) {
        myThickness = theThickness;
    }
    
    /**
     * A method that adds a property change listener.
     */
    public void addPropertyChangeListener(final PropertyChangeListener theListener) {
        myPCS.addPropertyChangeListener(theListener);
    }
    
    /**
     * A method that removes a property change listener.
     */
    public void removePropertyChangeListener(final PropertyChangeListener theListener) {
        myPCS.removePropertyChangeListener(theListener);
    }
    
    /**
     * A method for determining if the grid should appear.
     * 
     * @param theSelection is the grid check box selection.
     */
    public void setGridBoolean(final boolean theSelection) {
        myGridSelection = theSelection;
        repaint();
    }
    
    /**
     * Method for determining the spacing of the grid.
     * 
     * @param theSpacing is the spacing of the grid boxes.
     */
    public void setGridSpacing(final int theSpacing) {
        mySpacing = theSpacing;
        repaint();
    }
    
    /**
     * A method that clears the entire panel of drawn shapes.
     */
    public void clearDrawingPanel() {

        myShapesList.clear();
            
        myTool.setStartPoint(new Point(-1, -1));
        myTool.setEndPoint(new Point(-1, -1));
            
        repaint();
    }
    
    /**
     * A method that undoes the last drawn shape.
     * 
     * @param theSelection determines if the undo button was pressed.
     */
    public void setUndo(final boolean theSelection) {
        
        if (theSelection) {

            myShapesList.removeLast();

            repaint();
        }
    }

    /**
     * A method that disables the undo button.
     * 
     * @return if the list of shapes is empty.
     */
    public boolean disableUndo() {
        
        return myShapesList.isEmpty();
 
    }
    
    /**
     * A method for setting the size of our grid.
     * 
     * @param theSize is the size of our grid.
     */
    public void setGridSize(final int theSize) {
        
        this.myGridSize = theSize;
        
    }
    
    /**
     * The component that draws general shapes on the panel.
     */
    @Override
    protected void paintComponent(final Graphics theGraphics) {
        super.paintComponent(theGraphics);
        final Graphics2D g2d = (Graphics2D) theGraphics;

        setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));

        setBackground(BACKGROUND_COLOR);
        
        if (myGridSelection) {
            
            g2d.setStroke(new BasicStroke(1));
            g2d.setColor(DIGITAL_HUSKY_GOLD);
            
            for (int x = 0; x <= myGridSize; x += mySpacing) {
                final Line2D.Double horizontalLine = new Line2D.Double(x, 0, x, getHeight());
                g2d.draw(horizontalLine);
            }
            
            for (int y = 0; y <= myGridSize; y += mySpacing) {
                final Line2D.Double verticalLine = new Line2D.Double(0, y, getWidth(), y);
                g2d.draw(verticalLine);
            }
            
        }
        
        g2d.setColor(myColor);
        
        g2d.setRenderingHint(RenderingHints.
                KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        firePropertyChange("shapes", null, !myShapesList.isEmpty());
        firePropertyChange("shapesUndo", null, !myShapesList.isEmpty());
        
        for (final ToolProp shape : myShapesList) {
            g2d.setStroke(new BasicStroke(shape.getThickness()));
            g2d.setColor(shape.getColor());
            g2d.draw(shape.getShape());
        }
        
        if (myDrag) {
            g2d.setStroke(new BasicStroke(myThickness));
            g2d.setColor(myColor);
            
            if (myThickness != 0) {
                g2d.draw(myTool.getShape());
            }
        }
       
        
    }   
    
    /**
     * A method that keeps track of the color.
     * 
     * @return the color of our shape being drawn.
     */
    public Color getColor() {
        return myColor;
    }
    
    /**
     * An inner class that creates mouse listeners.
     */
    class DrawListener extends MouseAdapter {
        
        /**
         * A listener for mouse pressed events.
         */
        @Override 
        public final void mousePressed(final MouseEvent theEvent) {
            
            myDrag = true;
            myStartPosition = theEvent.getPoint();
            myEndPosition = myStartPosition;
            myTool.setStartPoint(myStartPosition);
            myTool.setEndPoint(myEndPosition);
            repaint();        
        }
        
        /**
         * A listener for mouse released events.
         */
        @Override
        public final void mouseReleased(final MouseEvent theEvent) {
            
            myEndPosition = theEvent.getPoint();
            myTool.setEndPoint(myEndPosition);
            
            myPCS.firePropertyChange("clear", false, true);
            
            if (myDrag && myThickness != 0) {
                myDrag = false;
                myShapesList.addLast(new ToolProp(myTool.getShape(), myThickness, myColor));
            }
        }
        
        /**
         * A listener for mouse dragged events.
         */
        @Override
        public final void mouseDragged(final MouseEvent theEvent) {
            myDrag = true;
            myEndPosition = theEvent.getPoint();
            myTool.setEndPoint(myEndPosition);
            repaint();
        }
    }
}
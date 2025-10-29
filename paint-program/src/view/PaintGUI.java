/*
 * TCSS 305 - Assignment 5
 */

package view;

import actions.EllipseAction;
import actions.LineAction;
import actions.RectangleAction;
import actions.RoundRectangleAction;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.Action;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


/**
 * Presents the GUI for the PowerPaint application.
 * 
 * @author Ryan Wilkerson
 * @version 4.3
 */
public class PaintGUI  implements PropertyChangeListener {
    
    // constants (if any)    


    /** A ToolKit. */
    private static final Toolkit KIT = Toolkit.getDefaultToolkit();
    
    /** The Dimension of the screen. */
    private static final Dimension SCREEN_SIZE = KIT.getScreenSize();
    
    /** The width of the screen. */
    private static final int SCREEN_WIDTH = SCREEN_SIZE.width;

    /** The height of the screen. */
    private static final int SCREEN_HEIGHT = SCREEN_SIZE.height;
    
    /** A Factor for scaling the size of the GUI relative to the current screen size. */
    private static final int SCALE = 3;
    
    /**
     * The minimum thickness of the slider.
     */
    private static final int THICKNESS_MIN = 0;
    
    /**
     * The maximum thickness of the slider.
     */
    private static final int THICKNESS_MAX = 20;
    
    /**
     * The initial thickness of the slider.
     */
    private static final int THICKNESS_INITIAL = 2;
    
    /**
     * The major ticking of the thickness slider.
     */
    private static final int THICKNESS_MAJOR_TICK = 5;
    
    /**
     * The minor ticking of the thickness slider.
     */
    private static final int THICKNESS_MINOR_TICK = 1;
    
    /**
     * The minimum value of our grid spacing.
     */
    private static final int GRID_THICKNESS_MIN = 10;
    
    /**
     * The max value of our grid spacing.
     */
    private static final int GRID_THICKNESS_MAX = 50;
    
    /**
     * The major ticks for the grid slider.
     */
    private static final int GRID_MAJOR_TICK = 5;
    
    /**
     * The initial spacing of the grid squares.
     */
    private static final int GRID_SPACING_INITIAL = 30;
    
    /**
     * The grid size for our lines to draw over.
     */
    private static final int THEORETICAL_GRID_SIZE = 100000;
    
    // instance fields
    
    /**
     * The frame to create the paint GUI.
     */
    private final JFrame myFrame;
    
    /**
     * The UW 'W' icon at the top left corner of the GUI.
     */
    private ImageIcon myIcon = new ImageIcon("files/w_small.png");

    /**
     * The drawing panel to put into our GUI.
     */
    private DrawingPanel myDrawingPanel;
    
    /**
     * The menu that houses all of our tools.
     */
    private JMenu myToolsMenu;
    
    /**
     * The button that lets us clear all drawn shapes.
     */
    private JMenuItem myClearButton;
    
    /**
     * A button for undoing the last drawn shape.
     */
    private JButton myUndoButton;
    
    /**
     * The spacing of our grid.
     */
    private int myGridSpacing;
    
    /**
     * The group of menu buttons.
     */
    private final ButtonGroup myMenuGroup;
    
    /**
     * The icon for a line.
     */
    private final ImageIcon myLineIcon = new ImageIcon("files/line_bw.gif");
    
    /**
     * The icon for a rectangle.
     */
    private final ImageIcon myRectIcon = new ImageIcon("files/rectangle_bw.gif");
    
    /**
     * The icon for our round rectangle.
     */
    private final ImageIcon myRoundRectIcon = new ImageIcon("files/roundrectangle_bw.gif");
    
    /**
     * The icon for an ellipse.
     */
    private final ImageIcon myEllipseIcon = new ImageIcon("files/ellipse_bw.gif");
    
    /**
     * The list to hold the tool icons.
     */
    private List<ImageIcon> myImageIconList;

    /**
     * Initializes the GUI.
     */
    public PaintGUI() {
        super();
        
        myMenuGroup = new ButtonGroup();
        
        myToolsMenu = new JMenu("Tools");

        myFrame = new JFrame("TCSS 305 Paint");
        
        myImageIconList = new ArrayList<>();
        
        myClearButton = createClearButton();
       
        start();
        
        myDrawingPanel.addPropertyChangeListener(this);
        
    }

    /**
     * Performs all tasks necessary to display the UI.
     */
    protected void start() {

        myImageIconList.add(myLineIcon);
        myImageIconList.add(myRectIcon);
        myImageIconList.add(myRoundRectIcon);
        myImageIconList.add(myEllipseIcon);
        
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        myFrame.setIconImage(myIcon.getImage());
        
        myFrame.setResizable(true);
        
        final PaintToolBar toolBar = new PaintToolBar();
        
        final List<Action> toolActions = new ArrayList<Action>();
           
        myDrawingPanel = new DrawingPanel(THEORETICAL_GRID_SIZE, GRID_SPACING_INITIAL);
        
        toolActions.add(new LineAction(myDrawingPanel));
        
        toolActions.add(new RectangleAction(myDrawingPanel));
        
        toolActions.add(new RoundRectangleAction(myDrawingPanel));
        
        toolActions.add(new EllipseAction(myDrawingPanel));
        
        for (final Action action : toolActions) {
            createToolsMenuBarButton(action, 
                    myImageIconList.get(toolActions.indexOf(action)));
            toolBar.createToolBarButton(action, 
                    myImageIconList.get(toolActions.indexOf(action)));
        }
        
        myUndoButton = new JButton("Undo");
        
        myUndoButton.setEnabled(false);
        
        toolBar.add(myUndoButton);
        
        myUndoButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent theEvent) {

                if (myUndoButton.equals(theEvent.getSource())) {
                    
                    myDrawingPanel.setUndo(true);

                    if (myDrawingPanel.disableUndo()) {
                        myUndoButton.setEnabled(false);
                        myClearButton.setEnabled(false);
                    }
                }
            }
        });
        
        final JCheckBox gridCheckBox = new JCheckBox("Grid");
        
        toolBar.add(gridCheckBox);
        
        myDrawingPanel.setGridSpacing(GRID_SPACING_INITIAL);
        
        
        
        gridCheckBox.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(final ActionEvent theEvent) {
                
                if (gridCheckBox.equals(theEvent.getSource()) && gridCheckBox.isSelected()) {

                    myDrawingPanel.setGridSpacing(GRID_SPACING_INITIAL);
                    myDrawingPanel.setGridBoolean(true);
                    
                } else {
                    
                    myDrawingPanel.setGridBoolean(false);
                    
                }
            }
        });
 
        myFrame.setJMenuBar(setUpMenuBar());
        
        myFrame.add(toolBar, BorderLayout.SOUTH);
         
        myFrame.add(myDrawingPanel, BorderLayout.CENTER);
        
        myFrame.setSize(SCREEN_WIDTH / SCALE, SCREEN_HEIGHT / SCALE);
        
        myFrame.setLocationRelativeTo(null);
        
        myFrame.setVisible(true);   
        
    }
    
    /**
     * Method for setting up the menu bar.
     * 
     * @return the menu bar.
     */
    private JMenuBar setUpMenuBar() {
        
        final JMenuBar menuBar = new JMenuBar();
        
        final JMenu optionsMenu = setUpOptionsMenu();
        
        final JMenu helpMenu = setUpHelpMenu();
        
        menuBar.add(optionsMenu);
        
        menuBar.add(myToolsMenu);
        
        menuBar.add(helpMenu);
        
        return menuBar;
    }   
    
    /**
     * Method for setting up the options menu in the menu bar.
     * 
     * @return the options menu item.
     */
    private JMenu setUpOptionsMenu() {
        
        final JMenu optionsMenu = new JMenu("Options");

        final JMenu thicknessMenu = new JMenu("Thickness");
        
        final JMenu gridSpacingMenu = new JMenu("Grid spacing");
        
        optionsMenu.add(thicknessMenu);
        
        optionsMenu.add(gridSpacingMenu);
        
        optionsMenu.addSeparator();
        
        final JSlider thicknessButton = new JSlider(JSlider.HORIZONTAL, 
                THICKNESS_MIN, THICKNESS_MAX, THICKNESS_INITIAL);
        
        thicknessMenu.add(thicknessButton);
        thicknessButton.setMajorTickSpacing(THICKNESS_MAJOR_TICK);
        thicknessButton.setMinorTickSpacing(THICKNESS_MINOR_TICK);
        
        thicknessButton.setPaintLabels(true);
        thicknessButton.setPaintTicks(true);
        
        thicknessButton.addChangeListener(new ChangeListener() {
            
            @Override
            public void stateChanged(final ChangeEvent theEvent) {
                final int thickness = thicknessButton.getValue();
                
                if (thickness >= 0) {
                    myDrawingPanel.setThickness(thickness);
                }
            }
        });    
        
        final JSlider gridSpacingButton = new JSlider(JSlider.HORIZONTAL, 
                GRID_THICKNESS_MIN, GRID_THICKNESS_MAX, GRID_SPACING_INITIAL);
        gridSpacingMenu.add(gridSpacingButton);
        gridSpacingButton.setMajorTickSpacing(GRID_MAJOR_TICK);
        
        gridSpacingButton.setPaintLabels(true);
        gridSpacingButton.setPaintTicks(true);
        
        gridSpacingButton.addChangeListener(new ChangeListener() {
            
            @Override
            public void stateChanged(final ChangeEvent theEvent) {
                myGridSpacing = gridSpacingButton.getValue();
                
                myDrawingPanel.setGridSpacing(myGridSpacing);
                myDrawingPanel.repaint();
                
            }
        });
        
        final JMenuItem colorButton = new JMenuItem("Color...");

        optionsMenu.add(colorButton);
        
        colorButton.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(final ActionEvent theEvent) {
                final Color colorResult = JColorChooser.showDialog
                        (null, "Select a color", myDrawingPanel.getColor());
                
                if (colorResult != null) {
                    myDrawingPanel.setColor(colorResult);
                }
            }
        });
        
        optionsMenu.add(myClearButton);
        myClearButton.setEnabled(false);
        
        return optionsMenu;
    }
    
    /**
     * Method for creating the clear button.
     * 
     * @return the clear button.
     */
    private JMenuItem createClearButton() {
        final JMenuItem clearButton = new JMenuItem("Clear");

        clearButton.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(final ActionEvent theEvent) {
                
                myDrawingPanel.clearDrawingPanel();
                clearButton.setEnabled(false);
                myUndoButton.setEnabled(false);                
            }
            
        });
        
        return clearButton;
        
    }

    /**
     * Creates the help button in the menu.
     * 
     * @return the help menu button.
     */
    private JMenu setUpHelpMenu() {
        
        final JMenu helpMenu = new JMenu("Help");

        
        final JMenuItem aboutButton = new JMenuItem("About...");

        helpMenu.add(aboutButton);
        
        aboutButton.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(final ActionEvent theEvent) {
                if (theEvent.getSource().equals(aboutButton)) {
                    final Image image = myIcon.getImage();
                    final Image newImage = 
                            image.getScaledInstance(40,  30,  java.awt.Image.SCALE_SMOOTH);
                    myIcon = new ImageIcon(newImage);
                    
                    JOptionPane.showMessageDialog(null, "Ryan Wilkerson \nWinter 2024", 
                            "About", JOptionPane.INFORMATION_MESSAGE, myIcon);
                }
            }        
        });
        return helpMenu;
    }

    /**
     * Creates the menu for our tools in the menu.
     * 
     * @param theAction is the action the represents each tool.
     * @param theIcon is the image given to each tool respectively.
     */
    private void createToolsMenuBarButton(final Action theAction, final ImageIcon theIcon) {
        
        final JRadioButtonMenuItem createdButton = new JRadioButtonMenuItem(theAction);
        createdButton.setIcon(theIcon);
        
        myMenuGroup.add(createdButton);
        myToolsMenu.add(createdButton);
    }
    
    /**
     * Method that lets us clear all our drawn shapes and the most recently drawn shape.
     */
    @Override
    public void propertyChange(final PropertyChangeEvent theEvent) {
     
        if (theEvent.getPropertyName().equals("clear")) {
            
            myClearButton.setEnabled((Boolean) theEvent.getNewValue());
            myUndoButton.setEnabled((Boolean) theEvent.getNewValue());

        }    
    }
}
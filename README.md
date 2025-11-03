# Paint-Program

This project is a Java-based paint program, developed over a series of three milestones in a Programming Practicum course. The application allows users to draw and manipulate basic shapes (lines, rectangles, ellipses, and rounded rectangles) with an intuitive GUI and a selection of drawing tools.

This repository contains the final version of the program, incorporating all features from previous deliverables and milestone improvements.

### Features

 - Drawing Tools: Line, Rectangle, Rounded Rectangle, Ellipse, Pencil.

 - Customizable Tool Properties: Change color, thickness, and fill options.

 - Interactive GUI: Toolbar, drawing panel, and tool selection integrated into a clean interface.

 - File Management: Supports saving and loading drawings (via project files and support files).

 - Robust Design: Follows MVC pattern for clean separation of concerns between the model, view, and controller.

### Project Structure

 - bin/ – Compiled class files.

 - src/ – Source code, organized into packages:

    - actions/ – Defines actions for each drawing tool.

    - controller/ – Handles program flow and user interactions.

    - model/ – Defines tools and properties used in drawing.

    - view/ – GUI components and panels.

 - files/ – Image resources used in the GUI.

 - support_files/ – Style guides and checkstyle configuration for coding standards.

### Usage

1) Compile the program using javac or your preferred Java IDE.

2) Run the program via the main class or using the run button:

  java controller.PaintMain

3) Use the toolbar to select tools, draw shapes, and interact with the canvas.

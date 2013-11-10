// file: ShapeDialog.java
// CS 360 - Fall 2012 - Watts
// Project 2
// October 2012 
// Originally created by Dr. Watts
// http://watts.cs.sonoma.edu
/*
   The file contains a Dialog box class for selecting a shape
   */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class ShapeDialog extends JDialog implements ActionListener 
{
    private JPanel myPanel = null;
    private JButton OKButton = null, cancelButton = null;
    private ShapePanel shapePanel = null;
    private JPanel buttonPanel = null;    
    private Shape newShape = null;
    private boolean answer = false;
    public Shape getMyShape () {return newShape; }
    public boolean getAnswer() { return answer; }

    public ShapeDialog(JFrame frame, boolean modal, int x, int y)
        //public ShapeDialog(JPanel frame, boolean modal, int x, int y)
    {
        super(frame, modal);
        myPanel = new JPanel();
        getContentPane().add(myPanel);
        myPanel.setLayout (new FlowLayout());
        shapePanel = new ShapePanel ();
        myPanel.add (shapePanel);
        addTextAndButtons ();
        setTitle ("New Shape Dialog");
        setLocation (x, y);
        setSize (300,190);
        setVisible(true);
    }

    private void addTextAndButtons ()
    {
        buttonPanel = new JPanel();
        OKButton = new JButton("    OK    ");
        OKButton.addActionListener(this);
        buttonPanel.add(OKButton); 
        cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(this);
        buttonPanel.add(cancelButton); 
        myPanel.add(buttonPanel); 
    }

    public void actionPerformed(ActionEvent e) 
    {
        if(OKButton == e.getSource()) 
        {
            Shape.ShapeType currentShape = shapePanel.getMyShape ();
            switch (currentShape)
            {
                case CIRCLE:
                    newShape = new Circle (); break;
                case SQUARE:
                    newShape = new Square (); break;
                case RECTANGLE:
                    newShape = new Rectangle (); break;
                case EQUILATERAL:
                    newShape = new Equilateral (); break;
                case RIGHT:
                    newShape = new Right (); break;
                case SCALENE:
                    newShape = new Scalene (); break;
                case POLYGON:
                    newShape = new RegularPolygon (); break;
                default:
                    newShape = new Circle (); break;
            }
            answer = true;
            setVisible(false);
            getContentPane().remove(myPanel);
        }
        else if(cancelButton == e.getSource()) 
        {
            answer = false;
            setVisible(false);
        }
    }

    public class ShapePanel extends JPanel implements ActionListener 
    {
        private JCheckBox circleCBox = null, equilateralCBox = null, 
            squareCBox = null, scaleneCBox = null,
            rightCBox = null, rectangleCBox = null,
            polygonCBox = null, trapezoidCBox = null,
            starCBox = null;
        private JPanel shapePanel = null;
        private ButtonGroup shapeGroup = null;
        private Shape.ShapeType currentShape = Shape.ShapeType.CIRCLE;
        public Shape.ShapeType getMyShape () { return currentShape; }

        public ShapePanel ()
        {
            setLayout (new GridLayout (2,1));
            add(new JLabel("Select a shape:"));
            shapePanel = new JPanel();
            shapePanel.setLayout (new GridLayout (3,3));
            circleCBox = new JCheckBox ("Circle",true);
            circleCBox.addActionListener(this);
            shapePanel.add (circleCBox);
            squareCBox = new JCheckBox ("Square",false);
            squareCBox.addActionListener(this);
            shapePanel.add (squareCBox);
            rectangleCBox = new JCheckBox ("Rectangle",false);
            rectangleCBox.addActionListener(this);
            shapePanel.add (rectangleCBox);
            equilateralCBox = new JCheckBox ("Equilateral",false);
            equilateralCBox.addActionListener(this);
            shapePanel.add (equilateralCBox);
            rightCBox = new JCheckBox ("Right",false);
            rightCBox.addActionListener(this);
            shapePanel.add (rightCBox);
            scaleneCBox = new JCheckBox ("Scalene",false);
            scaleneCBox.addActionListener(this);
            shapePanel.add (scaleneCBox);
            polygonCBox = new JCheckBox ("Polygon", false);
            polygonCBox.addActionListener(this);
            shapePanel.add (polygonCBox);
            trapezoidCBox = new JCheckBox ("Trapezoid", false);
            trapezoidCBox.addActionListener(this);
            shapePanel.add (trapezoidCBox);
            starCBox = new JCheckBox ("Star", false);
            starCBox.addActionListener (this);
            shapePanel.add (starCBox);
            add (shapePanel);
            shapeGroup = new ButtonGroup ();
            shapeGroup.add (circleCBox);
            shapeGroup.add (squareCBox);
            shapeGroup.add (equilateralCBox);
            shapeGroup.add (rectangleCBox);
            shapeGroup.add (rightCBox);
            shapeGroup.add (scaleneCBox);
            shapeGroup.add (polygonCBox);
            shapeGroup.add (trapezoidCBox);
            shapeGroup.add (starCBox);
        }

        public void actionPerformed(ActionEvent e) 
        {
            if (circleCBox == e.getSource())
                currentShape = Shape.ShapeType.CIRCLE;
            else if (squareCBox == e.getSource())
                currentShape = Shape.ShapeType.SQUARE;
            else if (rectangleCBox == e.getSource())
                currentShape = Shape.ShapeType.RECTANGLE;
            else if (equilateralCBox == e.getSource())
                currentShape = Shape.ShapeType.EQUILATERAL;
            else if (equilateralCBox == e.getSource())
                currentShape = Shape.ShapeType.EQUILATERAL;
            else if (rightCBox == e.getSource())
                currentShape = Shape.ShapeType.RIGHT;
            else if (scaleneCBox == e.getSource())
                currentShape = Shape.ShapeType.SCALENE;
            else if (polygonCBox == e.getSource())
                currentShape = Shape.ShapeType.POLYGON;
            else if (trapezoidCBox == e.getSource())
                currentShape = Shape.ShapeType.TRAPEZOID;
            else if (starCBox == e.getSource())
                currentShape = Shape.ShapeType.STAR;
        }
    }
} 


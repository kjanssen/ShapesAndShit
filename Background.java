// File: Background.java
// CS 360 - Fall 2012 - Watts
// Project 2
// October 2012 
// Originally created by Dr. Watts
// http://watts.cs.sonoma.edu
/*
   This file contains the definition and implementation of 
   a background Panel class for a small GUI application that
   uses the Shape class hierarchy.
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.util.*;
import static java.lang.Math.*;

public class Background extends JPanel implements ActionListener, MouseMotionListener, MouseListener, KeyListener
{
    private JButton clearButton, saveButton, loadButton, colorButton;
    private JFrame outside;
    private boolean inFrame = true, altDown = false, shiftDown = false;
    private int currentX, currentY;
    private ArrayList <Shape> S = new ArrayList <Shape> ();
    private ArrayList <Shape> selected = new ArrayList<Shape>();

    public Background ()
    {
        repaint();
    }
    public Background (JFrame frame, String [] files)
    {
        outside = frame;
        clearButton = new JButton("Clear Shapes");
        add (clearButton);
        clearButton.addActionListener (this);
        saveButton = new JButton ("Save Shapes");
        add (saveButton);
        saveButton.addActionListener (this);
        loadButton = new JButton ("Load Shapes");
        add (loadButton);
        loadButton.addActionListener (this);
        colorButton = new JButton ("Background Color");
        add (colorButton);
        colorButton.addActionListener (this);
        setBackground (Color.BLACK);
        addMouseMotionListener(this);
        addMouseListener(this);
        clearButton.addKeyListener(this);
        saveButton.addKeyListener(this);
        loadButton.addKeyListener(this);
        colorButton.addKeyListener(this);
        addKeyListener(this);
        setFocusable(true);
        ShapeIO shapeIO = new ShapeIO ();
        for (int i = 0; i < files.length; i++)
        {
            shapeIO.readShapes (files[i], S);
        }
        repaint();
    }
    public void mouseDragged(MouseEvent e)
    {
        //System.out.println ("Mouse dragged to " + e.getX() + ", " + e.getY());
        if (inFrame && selected.size() != 0)
        {
            if (altDown)
            {
                double dt = atan2((e.getX() - selected.getCenterX()), (selected.getCenterY() - e.getY())) - 
                    atan2((currentX - selected.getCenterX()), (selected.getCenterY() - currentY));

                selected.rotateBy(toDegrees(-dt));
            }
            else if (shiftDown)
            {
                int dx1 = currentX - selected.getCenterX();
                int dy1 = currentY - selected.getCenterY();
                double d1 = sqrt(dx1 * dx1 + dy1 * dy1);

                int dx2 = e.getX() - selected.getCenterX();
                int dy2 = e.getY() - selected.getCenterY();
                double d2 = sqrt(dx2 * dx2 + dy2 * dy2);

                selected.scale(d2 / d1);
            }
            else
            {
                //System.out.println ("Moving " + selected);
                selected.move (e.getX() - currentX, e.getY() - currentY);
            }
            repaint();
        }
        currentX = e.getX();
        currentY = e.getY();
    }
    public void mouseMoved(MouseEvent e) {}
    public void paintComponent (Graphics g)
    {
        super.paintComponent (g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        for (int i = 0; i < S.size(); i++)
        {
            S.get(i).paintComponent (g2);
        }
    }
    public void actionPerformed (ActionEvent e)
    {
        if (e.getSource() == clearButton)
        {
            S.clear();
            repaint();
        }
        else if (e.getSource() == saveButton)
        {
            FileIODialog fileiodialog = new FileIODialog(outside, true, "Save");

            if (fileiodialog.getAnswer() == true)
            {
                ShapeIO shapeIO = new ShapeIO ();
                shapeIO.writeShapes (fileiodialog.getFilename(), S);
            }
        }
        else if (e.getSource() == loadButton)
        {
            FileIODialog fileiodialog = new FileIODialog(outside, true, "Load");

            if (fileiodialog.getAnswer() == true)
            {
                ShapeIO shapeIO = new ShapeIO ();
                shapeIO.readShapes (fileiodialog.getFilename(), S);
                repaint();
            }
        }
        else if (e.getSource() == colorButton)
        {
            BackgroundDialog backgrounddialog = new BackgroundDialog(outside, true, getBackground());

            if (backgrounddialog.getAnswer() == true)
                setBackground (backgrounddialog.getColor());
        }
    }
    public void mousePressed (MouseEvent e)
    {
        //System.out.println ("Mouse pressed at " + e.getX() + ", " + e.getY());
        inFrame = true;
        currentX = e.getX();
        currentY = e.getY();

        if (!(altDown || shiftDown)) selected = null;

        if (e.getButton() == e.BUTTON1) // Left mouse button
        {
            //System.out.println ("BUTTON1 pressed at " + e.getX() + ", " + e.getY());
            for (int i = S.size()-1; i >= 0; i--)
                if (S.get(i).isIn(currentX, currentY))
                {
                    selected.add(0, S.get(i));
                    repaint();
                    return;
                    //System.out.println ("Selected " + selected.getName() + "; " + selected);
                }

            repaint();
        }
        else if (e.getButton() == e.BUTTON3) // Right mouse button
        {
            int sel = -1;
            //System.out.println ("BUTTON3 pressed at " + e.getX() + ", " + e.getY());
            for (int i = selected.size()-1; i >= 0; i--) {
                if (selected.get(i) != null) {
                    if (selected.get(i).isIn(currentX, currentY))
                    {
                        selected.add(0, S.get(i));
                        break;
                        //System.out.println ("Selected " + selected.getName() + "; " + selected);
                    }
                }
                else {
                    selected.remove(i);
                    i--;
                }
            }

            repaint();

            if (sel != -1) {
                selected.get(sel).modifyShape (outside, e.getX(), e.getY());
                // replace with modifyAllShape method
            }
            else
            {        
                selected = new ArrayList<Shape> ();
                ShapeDialog shapedialog = new ShapeDialog(outside, true, e.getX(), e.getY());
                //ShapeDialog shapedialog = new ShapeDialog(this, true, e.getX(), e.getY());
                if (shapedialog.getAnswer() == true)
                {
                    Shape newshape = shapedialog.getMyShape();
                    S.add(newshape);
                    newshape.setCenterX(e.getX());
                    newshape.setCenterY(e.getY());
                    newshape.modifyShape (outside, e.getX(), e.getY());
                }
            }
            repaint();
        }
    }

    public void mouseReleased (MouseEvent e) {}

    public void mouseEntered (MouseEvent e) 
    {
        inFrame = true;
    }

    public void mouseExited (MouseEvent e)
    {
        inFrame = false;
        //selected = null;
    }

    public void mouseClicked (MouseEvent e) {}

    public void keyPressed(KeyEvent e)
    {
        System.out.println(e.getKeyCode());
        if (e.getKeyCode() == KeyEvent.VK_ALT || e.getKeyCode() == 65406) {
            altDown = true;
            System.out.println("Alt Down");
        }
        else if (e.getKeyCode() == KeyEvent.VK_SHIFT) {
            shiftDown = true;
            System.out.println("Shift Down");
        }
        else if (e.getKeyCode() == KeyEvent.VK_DELETE && selected!= null) {
            S.remove(selected);
            repaint();
        }
    }

    public void keyReleased(KeyEvent e)
    {
        if (e.getKeyCode() == KeyEvent.VK_ALT || e.getKeyCode () == 65406) {
            altDown = false;
            System.out.println("Alt Up");
        }
        else if(e.getKeyCode() == KeyEvent.VK_SHIFT) {
            shiftDown = false;
            System.out.println("Shift Up");
        }
    }

    public void keyTyped(KeyEvent e) {}
}

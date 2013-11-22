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

public class Background extends JPanel implements ActionListener, MouseMotionListener, MouseListener, KeyListener {
    private JButton clearButton, saveButton, loadButton, colorButton;
    private JFrame outside;
    private boolean inFrame = true;
    private int currentX, currentY;
    private ArrayList <Shape> S = new ArrayList <Shape> ();
    private ArrayList <Shape> selected = new ArrayList<Shape>();

    private void emptySelected() {
        for (int i = 0; i < selected.size(); i++) 
            selected.get(i).setSelected(false);
        selected = new ArrayList<Shape> ();
    }

    public Background () {
        repaint();
    }

    public Background (JFrame frame, String [] files) {
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
        addKeyListener(this);
        setFocusable(true);
        ShapeIO shapeIO = new ShapeIO ();
        for (int i = 0; i < files.length; i++) {
            shapeIO.readShapes (files[i], S);
        }
        repaint();
    }

    public void mouseDragged(MouseEvent e) {
        //System.out.println ("Mouse dragged to " + e.getX() + ", " + e.getY());
        if (inFrame && selected.size() != 0) {
            //System.out.println ("Moving " + selected);
            for (int i = 0; i < selected.size(); i++) {
                if (selected.get(i) != null)
                    selected.get(i).move (e.getX() - currentX, e.getY() - currentY);
                else {
                    selected.remove(i);
                    i--;
                }
            }
            repaint();
        }
        currentX = e.getX();
        currentY = e.getY();
    }

    public void mouseMoved(MouseEvent e) {}

    public void paintComponent (Graphics g) {
        super.paintComponent (g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        for (int i = 0; i < S.size(); i++) {
            S.get(i).paintComponent (g2);
        }
    }

    public void actionPerformed (ActionEvent e) {
        if (e.getSource() == clearButton) {
            S.clear();
            repaint();
        }
        else if (e.getSource() == saveButton) {
            FileIODialog fileiodialog = new FileIODialog(outside, true, "Save");

            if (fileiodialog.getAnswer() == true) {
                ShapeIO shapeIO = new ShapeIO ();
                shapeIO.writeShapes (fileiodialog.getFilename(), S);
            }
        }
        else if (e.getSource() == loadButton) {
            FileIODialog fileiodialog = new FileIODialog(outside, true, "Load");

            if (fileiodialog.getAnswer() == true) {
                ShapeIO shapeIO = new ShapeIO ();
                shapeIO.readShapes (fileiodialog.getFilename(), S);
                repaint();
            }
        }
        else if (e.getSource() == colorButton) {
            BackgroundDialog backgrounddialog = new BackgroundDialog(outside, true, getBackground());

            if (backgrounddialog.getAnswer() == true) setBackground (backgrounddialog.getColor());
        }
    }

    public void mousePressed (MouseEvent e) {
        //System.out.println ("Mouse pressed at " + e.getX() + ", " + e.getY());
        inFrame = true;
        currentX = e.getX();
        currentY = e.getY();
        if (e.getButton() == e.BUTTON1) { // Left mouse button
            //System.out.println ("BUTTON1 pressed at " + e.getX() + ", " + e.getY());
            for (int i = S.size()-1; i >= 0; i--) {
                if (S.get(i).isIn(currentX, currentY)) {
                    if (!selected.contains(S.get(i))) {
                        S.get(i).setSelected(true);
                        selected.add(0, S.get(i));
                        repaint();
                    }
                    return;
                    //System.out.println ("Selected " + selected.getName() + "; " + selected);
                }
            }

            emptySelected();
            repaint();
        }
        else if (e.getButton() == e.BUTTON3) { // Right mouse button
            int sel = -1;
            //System.out.println ("BUTTON3 pressed at " + e.getX() + ", " + e.getY());
            for (int i = selected.size()-1; i >= 0; i--) {
                if (selected.get(i) != null) {
                    if (selected.get(i).isIn(currentX, currentY)) {
                        sel = i;
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
            else {        
                emptySelected();

                ShapeSelectionDialog shapeselectiondialog = new ShapeSelectionDialog(outside, true, e.getX(), e.getY());
                //ShapeSelectionDialog shapeselectiondialog = new ShapeSelectionDialog(this, true, e.getX(), e.getY());
                if (shapeselectiondialog.getAnswer() == true) {
                    Shape newshape = shapeselectiondialog.getMyShape();
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

    public void mouseEntered (MouseEvent e) {
        inFrame = true;
    }

    public void mouseExited (MouseEvent e) {
        inFrame = false;
        //selected = null;
    }
    
    public void mouseClicked (MouseEvent e) {}
    public void keyPressed(KeyEvent e) {}
    public void keyReleased(KeyEvent e) {}

    public void keyTyped(KeyEvent e) {
        System.out.println(e.getKeyCode());
        System.out.println(e.getKeyChar());
        System.out.println(e.isActionKey());
        System.out.println();

        if (e.getKeyChar() == 'd' && selected!= null) {
            S.remove(selected);
            repaint();
        }
    }
}

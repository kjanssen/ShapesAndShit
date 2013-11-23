// File: Background.java // CS 360 - Fall 2012 - Watts // Project 2 // October 2012 
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
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import static java.lang.Math.*;

public class Background extends JPanel implements ActionListener, MouseMotionListener, MouseListener, KeyListener {
    private JButton clearButton, saveButton, loadButton, colorButton;
    private JFrame outside;
    private boolean inFrame = true, altDown = false, shiftDown = false;
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
        clearButton.addKeyListener(this);
        saveButton.addKeyListener(this);
        loadButton.addKeyListener(this);
        colorButton.addKeyListener(this);
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
        if (inFrame && selected.size() != 0)
        {
            if (altDown && selected.size() == 1)
            {
                double dt = atan2((e.getX() - selected.get(0).getCenterX()), (selected.get(0).getCenterY() - e.getY())) - 
                    atan2((currentX - selected.get(0).getCenterX()), (selected.get(0).getCenterY() - currentY));

                selected.get(0).rotateBy(toDegrees(-dt));
            }
            else if (shiftDown && selected.size() == 1)
            {
                int dx1 = currentX - selected.get(0).getCenterX();
                int dy1 = currentY - selected.get(0).getCenterY();
                double d1 = sqrt(dx1 * dx1 + dy1 * dy1);

                int dx2 = e.getX() - selected.get(0).getCenterX();
                int dy2 = e.getY() - selected.get(0).getCenterY();
                double d2 = sqrt(dx2 * dx2 + dy2 * dy2);

                selected.get(0).scale(d2 / d1);
            }
            else
            {
                //System.out.println ("Moving " + selected);
                for (int i = 0; i < selected.size(); i++) {
                    if (selected.get(i) != null)
                        selected.get(i).move (e.getX() - currentX, e.getY() - currentY);
                    else {
                        selected.remove(i);
                        i--;
                    }
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
            FileIODialog fileiodialog = new FileIODialog(outside, true, "Save Shapes", "Save", ".sio", "SavedShapes.sio");

            if (fileiodialog.getAnswer() == true) {
                ShapeIO shapeIO = new ShapeIO ();
                shapeIO.writeShapes (fileiodialog.getFilename(), S);
            }
        }
        else if (e.getSource() == loadButton) {
            FileIODialog fileiodialog = new FileIODialog(outside, true, "Load Shapes", "Load", ".sio", "SavedShapes.sio");

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

            if (!(altDown || shiftDown)) {
                emptySelected();
                repaint();
            }
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
                if (sel == 0 && selected.size() == 1)
                    selected.get(sel).modifyShape (outside, e.getX(), e.getY());
                else {
                    ShapeDialog shapedialog = new ShapeDialog(outside, true, e.getX(), e.getY(), selected.get(0).getAngle());
                    if (shapedialog.getAnswer() == true)
                    {
                        double scale = shapedialog.getScale();
                        double angle = shapedialog.getAngle();
                        for (int i = 0; i < selected.size(); i++) {
                            selected.get(i).rotateBy(angle);
                            selected.get(i).scale(scale);
                        }
                    }
                }

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
    }

    public void mouseClicked (MouseEvent e) {}

    public void keyPressed(KeyEvent e)
    {
        if (e.getKeyCode() == KeyEvent.VK_ALT || e.getKeyCode() == 65406
            || e.getKeyCode () == KeyEvent.VK_CONTROL) {
            altDown = true;
        }
        else if (e.getKeyCode() == KeyEvent.VK_SHIFT) {
            shiftDown = true;
        }
        else if (e.getKeyCode() == KeyEvent.VK_DELETE && selected!= null) {
            for (int i = 0; i < selected.size(); i++)
                S.remove(selected.get(i));

            emptySelected();
            repaint();
        }
        else if (e.getKeyCode() == KeyEvent.VK_P)
            printScreen();
    }

    public void keyReleased(KeyEvent e)
    {
        if (e.getKeyCode() == KeyEvent.VK_ALT || e.getKeyCode () == 65406
                                   || e.getKeyCode () == KeyEvent.VK_CONTROL) {
            altDown = false;
        }
        else if(e.getKeyCode() == KeyEvent.VK_SHIFT) {
            shiftDown = false;
        }
    }

    public void keyTyped(KeyEvent e) {}

    public void printScreen()
    {
        FileIODialog fileiodialog = new FileIODialog(outside, true, "Print Screen", "Save", ".png", "Screenshot.png");

        if (fileiodialog.getAnswer() == true) {
            clearButton.setVisible(false);
            saveButton.setVisible(false);
            loadButton.setVisible(false);
            colorButton.setVisible(false);

            BufferedImage bi = new BufferedImage(this.getSize().width, this.getSize().height, BufferedImage.TYPE_INT_ARGB); 
            Graphics g = bi.createGraphics();
            this.paint(g);  //this == JComponent
            g.dispose();
            try{ImageIO.write(bi,"png",new File(fileiodialog.getFilename()));}catch (Exception e) {}

            clearButton.setVisible(true);
            saveButton.setVisible(true);
            loadButton.setVisible(true);
            colorButton.setVisible(true);
        }
    }
}

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

public class Background extends JPanel implements ActionListener, MouseMotionListener, MouseListener
{
	private JButton saveButton;
	private JFrame outside;
	private boolean inFrame = true;
	private int currentX, currentY;
	private ArrayList <Shape> S = new ArrayList <Shape> ();
	private Shape selected = null;

	public Background ()
	{
		repaint();
	}
	public Background (JFrame frame, String [] files)
	{
		outside = frame;
		saveButton = new JButton ("Save Shapes");
		add (saveButton);
		saveButton.addActionListener (this);
		setBackground (Color.BLACK);
		addMouseMotionListener(this);
		addMouseListener(this);
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
		if (inFrame && selected != null)
		{
			//System.out.println ("Moving " + selected);
			selected.move (e.getX() - currentX, e.getY() - currentY);
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
		if (e.getSource() == saveButton)
		{
			ShapeIO shapeIO = new ShapeIO ();
			shapeIO.writeShapes ("SavedShapes.sio", S);
		}
	}
	public void mousePressed (MouseEvent e)
	{
		//System.out.println ("Mouse pressed at " + e.getX() + ", " + e.getY());
		inFrame = true;
		currentX = e.getX();
		currentY = e.getY();
		selected = null;
		if (e.getButton() == e.BUTTON1) // Left mouse button
		{
			//System.out.println ("BUTTON1 pressed at " + e.getX() + ", " + e.getY());
			for (int i = S.size()-1; selected == null && i >= 0; i--)
				if (S.get(i).isIn(currentX, currentY))
				{
					selected = S.get(i);
						//System.out.println ("Selected " + selected.getName() + "; " + selected);
				}
		}
		else if (e.getButton() == e.BUTTON3) // Right mouse button
		{
			//System.out.println ("BUTTON3 pressed at " + e.getX() + ", " + e.getY());
			for (int i = S.size()-1; selected == null && i >= 0; i--)
				if (S.get(i).isIn(currentX, currentY))
				{
					selected = S.get(i);
						//System.out.println ("Selected " + selected.getName() + "; " + selected);
				}
			if (selected != null)
					selected.modifyShape (outside, e.getX(), e.getY());
			else
			{	
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
		selected = null;
	}
	public void mouseClicked (MouseEvent e) {}
}

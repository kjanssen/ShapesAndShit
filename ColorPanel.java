// file: ColorPanel.java
// CS 360 - Fall 2012 - Watts
// Project 2
// October 2012 
// Originally created by Dr. Watts
// http://watts.cs.sonoma.edu
/*
This file contains a panel displaying radio buttons for
selecting a shape's color.
*/

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class ColorPanel extends JPanel implements ActionListener 
{
	private JRadioButton redRButton = null, orangeRButton = null,
			yellowRButton = null, greenRButton = null,
			blueRButton = null, purpleRButton = null,
			whiteRButton = null, grayRButton = null,
	                blackRButton = null, brickRButton = null,
	                steelRButton = null, pinkRButton = null;
	private ButtonGroup colorGroup = null;
	private Color currentColor;
	private static Color pink = new Color (255, 20, 147);
	private static Color orange = new Color (237, 155, 37);
        private static Color brick = new Color (207, 83, 0);
        private static Color steel = new Color (70, 130, 180);
	private static Color purple = new Color (82, 8, 125);
	private static Color gray = new Color (170, 170, 170);
	public Color getColor () { return currentColor; }

	public ColorPanel(Color C)
	{
		currentColor = C;
		setLayout (new GridLayout (0,4,0,0));
		pinkRButton = new JRadioButton("Pink", currentColor.equals(pink));
		pinkRButton.addActionListener(this);
		pinkRButton.setForeground (pink);
		add (pinkRButton);
		redRButton = new JRadioButton ("Red", currentColor.equals(Color.red));
		redRButton.addActionListener(this);
		redRButton.setForeground (Color.red);
		add (redRButton);
		brickRButton = new JRadioButton ("Brick", currentColor.equals(brick));
		brickRButton.addActionListener(this);
		brickRButton.setForeground (brick);
		add (brickRButton);
		orangeRButton = new JRadioButton ("Orange", currentColor.equals(orange));
		orangeRButton.addActionListener(this);
		orangeRButton.setForeground (orange);
		add (orangeRButton);
		yellowRButton = new JRadioButton ("Yellow", currentColor.equals(Color.yellow));
		yellowRButton.addActionListener(this);
		yellowRButton.setForeground (Color.yellow);
		add (yellowRButton);
		greenRButton = new JRadioButton ("Green", currentColor.equals(Color.green));
		greenRButton.addActionListener(this);
		greenRButton.setForeground (Color.green);
		add (greenRButton);
		blueRButton = new JRadioButton ("Blue", currentColor.equals(Color.blue));
		blueRButton.addActionListener(this);
		blueRButton.setForeground (Color.blue);
		add (blueRButton);
		steelRButton = new JRadioButton ("Steel", currentColor.equals(steel));
		steelRButton.addActionListener(this);
		steelRButton.setForeground (steel);
		add (steelRButton);
		purpleRButton = new JRadioButton ("Purple", currentColor.equals(purple));
		purpleRButton.addActionListener(this);
		purpleRButton.setForeground (purple);
		add (purpleRButton);
		whiteRButton = new JRadioButton ("White", currentColor.equals(Color.white));
		whiteRButton.addActionListener(this);
		whiteRButton.setForeground (Color.white);
		add (whiteRButton);
		grayRButton = new JRadioButton ("Gray", currentColor.equals(gray));
		grayRButton.addActionListener(this);
		grayRButton.setForeground (gray);
		add (grayRButton);
		blackRButton = new JRadioButton ("Black", currentColor.equals(Color.black));
		blackRButton.addActionListener(this);
		add (blackRButton);
		colorGroup = new ButtonGroup ();
		colorGroup.add (pinkRButton);
		colorGroup.add (redRButton);
		colorGroup.add (brickRButton);
		colorGroup.add (orangeRButton);
		colorGroup.add (yellowRButton);
		colorGroup.add (greenRButton);
		colorGroup.add (blueRButton);
		colorGroup.add (steelRButton);
		colorGroup.add (purpleRButton);
		colorGroup.add (whiteRButton);
		colorGroup.add (grayRButton);
		colorGroup.add (blackRButton);
	}

	public void actionPerformed(ActionEvent e) 
	{
	        if (pinkRButton == e.getSource())
		        currentColor = pink;
	        else if (redRButton == e.getSource())
			currentColor = Color.red;
		else if (brickRButton == e.getSource())
		        currentColor = brick;
		else if (orangeRButton == e.getSource())
			currentColor = orange;
	 	else if (yellowRButton == e.getSource())
			currentColor = Color.yellow;
	 	else if (greenRButton == e.getSource())
			currentColor = Color.green;
		else if (blueRButton == e.getSource())
			currentColor = Color.blue;
		else if (steelRButton == e.getSource())
		        currentColor = steel;
		else if (purpleRButton == e.getSource())
			currentColor = purple;
		else if (whiteRButton == e.getSource())
			currentColor = Color.white;
		else if (grayRButton == e.getSource())
			currentColor = gray;
		else if (blackRButton == e.getSource())
			currentColor = Color.black;
       }
} 


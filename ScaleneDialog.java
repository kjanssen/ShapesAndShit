// file: ScaleneDialog.java
// CS 360 - Fall 2012 - Watts
// Project 2
// October 2012 
// Originally created by Dr. Watts
// http://watts.cs.sonoma.edu
/*
Dialog box for entering color, side length, and rotation
of a Scalene.
*/

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class ScaleneDialog extends JDialog implements ActionListener 
{
 	private JPanel myPanel = null;
 	private JButton OKButton = null, cancelButton = null;
	private JTextField sideText;
	private JTextField side2Text;
	private JTextField side3Text;
	private JTextField angleText;
	private ColorPanel colorPanel = null;
	private JPanel buttonPanel = null;    
	private Color currentColor = Color.white;
	private int oldSide = 0;
	private int side = 0;
	private int oldSide2 = 0;
	private int side2 = 0;
	private int oldSide3 = 0;
	private int side3 = 0;
	private double oldAngle = 0;
	private double angle = 0;
 	private boolean answer = false;
 	public Color getColor() { return currentColor; }
 	public int getSide() { return side; }
 	public int getSide2() { return side2; }
 	public int getSide3() { return side3; }
 	public double getAngle() { return angle; }
 	public boolean getAnswer() { return answer; }

	public ScaleneDialog(JFrame frame, boolean modal, int x, int y, int S1, int S2, int S3, double A, Color C)
	{
 		super(frame, modal);
		oldSide = S1;
		side = oldSide;
		oldSide2 = S2;
		side2 = oldSide2;
		oldSide3 = S3;
		side3 = oldSide3;
		oldAngle = A;
		angle = oldAngle;
		currentColor = C;
 		myPanel = new JPanel();
		getContentPane().add(myPanel);
		myPanel.setLayout (new FlowLayout ());
	 	myPanel.add(new JLabel("Select the color:"));
		colorPanel = new ColorPanel (currentColor);
		myPanel.add (colorPanel);
		addTextAndButtons ();
		setTitle ("Modify Scalene Dialog");
		setLocation (x, y);
		setSize (300,375);
 		setVisible(true);
	}
	
	private void addTextAndButtons ()
	{
	 	myPanel.add(new JLabel("Enter the first side:"));
		sideText = new JTextField(((Integer) side).toString(), 20);
		sideText.addActionListener(this);
		myPanel.add (sideText);
	 	myPanel.add(new JLabel("Enter the second side:"));
		side2Text = new JTextField(((Integer) side2).toString(), 20);
		side2Text.addActionListener(this);
		myPanel.add (side2Text);
	 	myPanel.add(new JLabel("Enter the third side:"));
		side3Text = new JTextField(((Integer) side3).toString(), 20);
		side3Text.addActionListener(this);
		myPanel.add (side3Text);
	 	myPanel.add(new JLabel("Enter the angle:"));
		angleText = new JTextField(((Double) angle).toString(), 20);
		angleText.addActionListener(this);
		myPanel.add (angleText);
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
			currentColor = colorPanel.getColor();
			answer = true;
			setVisible(false);
			getContentPane().remove(myPanel);
			try
			{
				side = Integer.parseInt (sideText.getText());
			}
			catch (NumberFormatException ex)
			{
				side = oldSide;
			}
			try
			{
				side2 = Integer.parseInt (side2Text.getText());
			}
			catch (NumberFormatException ex)
			{
				side2 = oldSide2;
			}
			try
			{
				side3 = Integer.parseInt (side3Text.getText());
			}
			catch (NumberFormatException ex)
			{
				side3 = oldSide3;
			}
			try
			{
				angle = Double.parseDouble (angleText.getText());
			}
			catch (NumberFormatException ex)
			{
				angle = oldAngle;
			}
			answer = side == 0 || side2 == 0 ? false : true;
        	}
 		else if(cancelButton == e.getSource()) 
		{
 			answer = false;
			setVisible(false);
		}
       }
 
} 


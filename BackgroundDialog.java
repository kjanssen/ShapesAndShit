// file: BackgroundDialog.java
// CS 360 - Fall 2012 - Watts
// Project 2
// October 2012 
// Originally created by Dr. Watts
// http://watts.cs.sonoma.edu
/*
The file contains a Dialog box class for changing the background color.
*/

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class BackgroundDialog extends JDialog implements ActionListener 
{
 	private JPanel myPanel = null;
        private ColorPanel colorPanel = null;
        private JButton OKButton = null, cancelButton = null;
	private JPanel buttonPanel = null;
        private Color currentColor = Color.black;
 	private boolean answer = false;
	public Color getColor () {return currentColor; }
 	public boolean getAnswer() { return answer; }

        public BackgroundDialog(JFrame frame, boolean modal, Color C)
	{
 		super(frame, modal);
		currentColor = C;
 		myPanel = new JPanel();
		getContentPane().add(myPanel);
		myPanel.setLayout (new FlowLayout());
		addTextAndButtons ();
		setTitle ("Change Background Color");
		setLocation(300,300);
		setSize (350,200);
 		setVisible(true);
	}

	private void addTextAndButtons ()
	{
	        myPanel.add(new JLabel("Select the background color:"));
		colorPanel = new ColorPanel (currentColor);
		myPanel.add(colorPanel);
		buttonPanel = new JPanel();
		OKButton = new JButton("  OK  ");
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
        	}
 		else if(cancelButton == e.getSource()) 
		{
 			answer = false;
			setVisible(false);
		}
       }
} 


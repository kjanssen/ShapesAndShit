// file: FileIODialog.java
// CS 360 - Fall 2012 - Watts
// Project 2
// October 2012 
// Originally created by Dr. Watts
// http://watts.cs.sonoma.edu
/*
The file contains a Dialog box class for saving a shape IO file.
*/

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class FileIODialog extends JDialog implements ActionListener 
{
 	private JPanel myPanel = null;
        private JButton actionButton = null, cancelButton = null;
	private JPanel buttonPanel = null;
        private JTextField filenameText;
	private String filename = null;
 	private boolean answer = false;
	public String getFilename () {return filename; }
 	public boolean getAnswer() { return answer; }

        public FileIODialog(JFrame frame, boolean modal, String action)
	{
 		super(frame, modal);
 		myPanel = new JPanel();
		getContentPane().add(myPanel);
		myPanel.setLayout (new FlowLayout());
		addTextAndButtons (action);
		setTitle (action + " Shapes");
		setLocation(300,300);
		setSize (400,120);
 		setVisible(true);
	}

	private void addTextAndButtons (String action)
	{
	        myPanel.add(new JLabel("Enter Filename (.sio extension):"));
		filenameText = new JTextField("SavedShapes.sio", 30);
		filenameText.addActionListener(this);
		myPanel.add(filenameText);
		buttonPanel = new JPanel();
		actionButton = new JButton(action);
		actionButton.addActionListener(this);
		buttonPanel.add(actionButton);
		cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(this);
		buttonPanel.add(cancelButton); 
		myPanel.add(buttonPanel); 
	}

	public void actionPerformed(ActionEvent e) 
	{
		if(actionButton == e.getSource()) 
		{
		        filename = filenameText.getText();
		
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


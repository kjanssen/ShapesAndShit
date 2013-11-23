// file: CircleDialog.java
// CS 360 - Fall 2012 - Watts
// Project 2
// October 2012 
// Originally created by Dr. Watts
// http://watts.cs.sonoma.edu
/*
   This file contains a Dialog box class for entering the color and
   diameter of a circle
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class CircleDialog extends JDialog implements ActionListener 
{
    private JPanel myPanel = null;
    private JButton OKButton = null, cancelButton = null;
    private JTextField sideText;
    private ColorPanel colorPanel = null;
    private JPanel buttonPanel = null;    
    private Color currentColor = Color.white;
    private int oldSide = 0;
    private int side = 0;
    private boolean answer = false;
    public Color getColor() { return currentColor; }
    public int getSide() { return side / 2; }
    public boolean getAnswer() { return answer; }

    public CircleDialog(JFrame frame, boolean modal, int x, int y, int R, Color C)
    {
        super(frame, modal);
        oldSide = R;
        side = 2 * oldSide;
        currentColor = C;
        myPanel = new JPanel();
        getContentPane().add(myPanel);
        myPanel.setLayout (new FlowLayout());
        myPanel.add(new JLabel("Select the color:"));
        colorPanel = new ColorPanel (currentColor);
        myPanel.add (colorPanel);
        addTextAndButtons ();
        setTitle ("Modify Circle Dialog");
        setLocation (x, y);
        setSize (300,225);
        setVisible(true);
    }

    private void addTextAndButtons ()
    {
        myPanel.add(new JLabel("Enter the diameter:"));
        sideText = new JTextField(((Integer) side).toString(), 20);
        sideText.addActionListener(this);
        myPanel.add (sideText);
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
            setVisible(false);
            getContentPane().remove(myPanel);
            currentColor = colorPanel.getColor ();
            try
            {
                side = Integer.parseInt (sideText.getText());
            }
            catch (NumberFormatException ex)
            {
                side = 2 * oldSide;
            }
            answer = side == 0 ? false : true;
        }
        else if(cancelButton == e.getSource()) 
        {
            answer = false;
            setVisible(false);
        }
    }
} 


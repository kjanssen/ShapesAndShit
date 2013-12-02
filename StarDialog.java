// file: StarDialog.java
// CS 360 - Fall 2012 - Watts
// Project 2
// October 2012 
// Originally created by Dr. Watts
// http://watts.cs.sonoma.edu
/*
   Dialog box for entering color, side length, and rotation
   of a regular star.
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class StarDialog extends JDialog implements ActionListener 
{
    private JPanel myPanel = null;
    private JButton OKButton = null, cancelButton = null;
    private JTextField sideNumText;
    private JTextField sideText;
    private JTextField angleText;
    private ColorPanel colorPanel = null;
    private JPanel buttonPanel = null;    
    private Color currentColor = Color.white;
    private int oldNumSides = 0;
    private int numSides = 0;
    private int oldSide = 0;
    private int side = 0;
    private double oldAngle = 0;
    private double angle = 0;
    private boolean answer = false;
    public Color getColor() { return currentColor; }
    public int getNumSides() { return numSides; }
    public int getSide() { return side; }
    public double getAngle() { return angle; }
    public boolean getAnswer() { return answer; }

    public StarDialog(JFrame frame, boolean modal, int x, int y, int N, int S, double A, Color C)
    {
        super(frame, modal);
        oldNumSides = N;
        numSides = oldNumSides;
        oldSide = S;
        side = oldSide;
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
        setTitle ("Modify Star Dialog");
        setLocation (x, y);
        setSize (325, 325);
        setVisible(true);
    }

    private void addTextAndButtons ()
    {
        myPanel.add(new JLabel("Enter the number of sides:"));
        sideNumText = new JTextField(((Integer) numSides).toString(), 20);
        sideNumText.addActionListener(this);
        myPanel.add(sideNumText);
        myPanel.add(new JLabel("Enter the side:"));
        sideText = new JTextField(((Integer) side).toString(), 20);
        sideText.addActionListener(this);
        myPanel.add (sideText);
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
                numSides = Integer.parseInt (sideNumText.getText());
            }
            catch (NumberFormatException ex)
            {
                numSides = oldNumSides;
            }
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
                angle = Double.parseDouble (angleText.getText());
            }
            catch (NumberFormatException ex)
            {
                angle = oldAngle;
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


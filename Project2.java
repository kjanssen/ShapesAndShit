// File: Project2.java
// CS 360 - Fall 2012 - Watts
// Project 2
// October 2012 
// Originally created by Dr. Watts
// http://watts.cs.sonoma.edu
/*
This file contains the implementation of a small
GUI application that uses the Shape class hierarchy.
*/

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.util.*;

public class Project2
{
	public static void main (String[] args)
	{
		JFrame frame = new JFrame ("Project 2");
		//super (frame, modal);
		Background background = new Background (frame, args);
		frame.getContentPane().add (background);
		frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
		frame.setSize (600,600);
		frame.setVisible (true);
		frame.setResizable (false);
		frame.setLocation (200, 200);
	}
}

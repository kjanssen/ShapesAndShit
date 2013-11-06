// File: ShapeIO.java
// CS 360 - Fall 2012 - Watts
// Project 2
// October 2012 
// Originally created by Dr. Watts
// http://watts.cs.sonoma.edu
/*
This file contains a class called ShapeIO that can be used to read a file of
shape descriptions or write a file of shape descriptions.
*/

import java.io.*;
import java.util.*;
import java.lang.*;
import java.awt.*;

public class ShapeIO
{
	public ShapeIO ()
	{
	}

	public void readShapes (String fileName, ArrayList <Shape> shapeList)
	{
		try
		{
			BufferedReader inFile = new BufferedReader (new FileReader (fileName));
			String string = inFile.readLine();
			int count = Integer.parseInt (string);
			Shape shape = new Shape ();
			for (int i = 0; i < count; i++)
			{
				String name = inFile.readLine();
				string = inFile.readLine();
				if (name.equals("Circle"))
					shape = new Circle ();
				else if (name.equals("Square"))
					shape = new Square ();
				else if (name.equals("Rectangle"))
					shape = new Rectangle ();
				else if (name.equals("Equilateral"))
					shape = new Equilateral ();
				else if (name.equals("Right"))
					shape = new Right ();
				else if (name.equals("Scalene"))
					shape = new Scalene ();
				shape.fromString (string);
				shapeList.add (shape);
			}
			inFile.close();
		}
		catch (IOException e)
		{
			System.out.println ("Error reading file " + fileName);
		}
		catch (NumberFormatException e)
		{
			System.out.println ("Numeric input error in " + fileName);
		}
	}

	public void writeShapes (String fileName, ArrayList <Shape> shapeList)
	{
		try
		{
			BufferedWriter outFile = new BufferedWriter(new FileWriter(fileName));
			outFile.write (((Integer)shapeList.size()).toString());
			outFile.newLine ();
			for (int i = 0; i < shapeList.size(); i++)
			{
				outFile.write (shapeList.get(i).getName());
				outFile.newLine ();
				outFile.write (shapeList.get(i).toString());
				outFile.newLine ();
			}
			outFile.close();
		}
		catch (IOException e)
		{
			System.out.println ("Error writing file " + fileName);
		}
	}

	public static void main (String[] args)
	{
		ShapeIO shapeIO = new ShapeIO ();
		ArrayList <Shape> S = new ArrayList <Shape> ();
		shapeIO.readShapes (args[0], S);
		shapeIO.writeShapes (args[1], S);
	}
}

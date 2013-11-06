// File: Square.java
// CS 360 - Fall 2012 - Watts
// Project 2
// October 2012 
// Originally created by Dr. Watts
// http://watts.cs.sonoma.edu
/*
This file contains the description and implementation
of a class called Square. 
*/

import static java.lang.Math.*;
import java.awt.*;
import javax.swing.*;

public final class Square extends Quadrilateral
{
	public Square ()
	{
	}

	public Square (Square E)
	{
		side = E.side;
		centerX = E.centerX;
		centerY = E.centerY;
		color = E.color;
		angle = E.angle;
		for (int i = 0; i < 4; i++)
		{
			vertexX[i] = E.vertexX[i];
			vertexY[i] = E.vertexY[i];
		}
	}

	public Square (int S, int X, int Y, Color C, double A)
	{
		side = S;
		centerX = X;
		centerY = Y;
		color = C;
		angle = A;
		setVertices ();
	}

	public void setVertices ()
	{
		vertexX[0] = vertexY[0] = -side/2;
		vertexX[1] = -side/2; vertexY[1] = side/2;
		vertexX[2] = vertexY[2] = side/2;
		vertexX[3] = side/2; vertexY[3] = -side/2;
		for (int i = 0; i < 4; i++)
		{
			vertexX[i] += centerX;
			vertexY[i] += centerY;
		}
		rotate ();
		polygon = new Polygon (vertexX, vertexY, 4);
	}

	public void setSide (int S)
	{
		side = S;
	}

	public int getSide ()
	{
		return side;
	}

	public void fromString (String str)
	{
		String [] parts = str.split (" ");
		try
		{
			centerX = Integer.parseInt(parts[0]);
			centerY = Integer.parseInt(parts[1]);
			side = Integer.parseInt(parts[2]);
			color = new Color(Integer.parseInt(parts[3]));
			angle = Double.parseDouble (parts[4]);
			setVertices ();
		}
		catch (NumberFormatException e)
		{
			System.out.println ("Numeric input error");
		}
	}

	public String toString ()
	{
		String string = new String ();
		string += centerX + " ";
		string += centerY + " ";
		string += side + " ";
		string += color.getRGB() + " ";
		string += angle + " ";
		return string;
	}

	public void modifyShape (JFrame frame, int x, int y)
	{
		SquareDialog squaredialog = new SquareDialog (frame, true, x, y, side, angle, color); 
		if (squaredialog.getAnswer() == true)
		{
			side = squaredialog.getSide ();
			angle = squaredialog.getAngle ();
			color = squaredialog.getColor ();
			setVertices ();
		}
	}

	public double area ()
	{
		return side * side;
	}

	public double perimeter ()
	{
		return 4 * side;
	}

	public String getName ()
	{
		return "Square";
	}
}

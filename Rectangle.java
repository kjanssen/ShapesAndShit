// File: Rectangle.java
// CS 360 - Fall 2012 - Watts
// Project 2
// October 2012 
// Originally created by Dr. Watts
// http://watts.cs.sonoma.edu
/*
This file contains the description and implementation
of a class called Rectangle. 
*/

import static java.lang.Math.*;
import java.awt.*;
import javax.swing.*;

public final class Rectangle extends Quadrilateral
{
	private int side2;

	public Rectangle ()
	{
		side2 = 30;
	}

	public Rectangle (Rectangle R)
	{
		side = R.side;
		side2 = R.side2;
		centerX = R.centerX;
		centerY = R.centerY;
		color = R.color;
		angle = R.angle;
		for (int i = 0; i < 4; i++)
		{
			vertexX[i] = R.vertexX[i];
			vertexY[i] = R.vertexY[i];
		}
	}

	public Rectangle (int S1, int S2, int X, int Y, Color C, double A)
	{
		side = S1;
		side2 = S2;
		centerX = X;
		centerY = Y;
		color = C;
		angle = A;
		setVertices ();
	}

	public void setVertices ()
	{
		vertexX[0] = -side/2; vertexY[0] = -side2/2;
		vertexX[1] = -side/2; vertexY[1] = side2/2;
		vertexX[2] = side/2; vertexY[2] = side2/2;
		vertexX[3] = side/2; vertexY[3] = -side2/2;
		for (int i = 0; i < 4; i++)
		{
			vertexX[i] += centerX;
			vertexY[i] += centerY;
		}
		rotate ();
		polygon = new Polygon (vertexX, vertexY, 4);
	}

	public void setSide1 (int S1)
	{
		side = S1;
	}

	public int getSide1 ()
	{
		return side;
	}

	public void setSide2 (int S2)
	{
		side2 = S2;
	}

	public int getSide2 ()
	{
		return side2;
	}

	public double perimeter ()
	{
		return 2 * (side + side2);
	}

	public double area ()
	{
		return side * side2;
	}

	public void modifyShape (JFrame frame, int x, int y)
	{
		RectangleDialog rectangledialog = new RectangleDialog (frame, true, x, y, side, side2, angle, color); 
		if (rectangledialog.getAnswer() == true)
		{
			side = rectangledialog.getSide ();
			side2 = rectangledialog.getSide2 ();
			angle = rectangledialog.getAngle ();
			color = rectangledialog.getColor ();
			setVertices ();
		}
	}

	public String getName ()
	{
		return "Rectangle";
	}

	public void fromString (String str)
	{
		String [] parts = str.split (" ");
		try
		{
			centerX = Integer.parseInt(parts[0]);
			centerY = Integer.parseInt(parts[1]);
			side = Integer.parseInt(parts[2]);
			side2 = Integer.parseInt(parts[3]);
			color = new Color(Integer.parseInt(parts[4]));
			angle = Double.parseDouble (parts[5]);
			setVertices ();
		}
		catch (NumberFormatException e)
		{
			System.out.println ("File input error - invalid integer");;
		}
	}

	public String toString ()
	{
		String string = new String ();
		string += centerX + " ";
		string += centerY + " ";
		string += side + " ";
		string += side2 + " ";
		string += color.getRGB() + " ";
		string += angle + " ";
		return string;
	}
	
	public boolean isIn (int X, int Y)
	{
		int deltaX = Math.abs (X - centerX);
		int deltaY = Math.abs (Y - centerY);
		if (deltaX <= side/2 && deltaY <= side2/2)
			return true;
		return false;
	}
}

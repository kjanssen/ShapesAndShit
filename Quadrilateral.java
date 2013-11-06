// File: Quadrilateral.java
// CS 360 - Fall 2012 - Watts
// Project 2
// October 2012 
// Originally created by Dr. Watts
// http://watts.cs.sonoma.edu
/*
This file contains the description and implementation
of a parent class called Quadrilateral. 
*/

import static java.lang.Math.*;
import java.awt.*;

public class Quadrilateral extends Shape
{
	protected int [] vertexX = new int [4];
	protected int [] vertexY = new int [4];
	protected Polygon polygon = new Polygon (vertexX, vertexY, 4);
	protected double angle = 0.0;

	Quadrilateral ()
	{
	}

	public void setCenterX (int X)
	{
		centerX = X;
		setVertices ();
	}

	public void setCenterY (int Y)
	{
		centerY = Y;
		setVertices ();
	}

	public String getName ()
	{
		return "Quadrilateral";
	}

	public void paintComponent (Graphics2D g2)
	{
		g2.setPaint (color);
		g2.fillPolygon (vertexX, vertexY, 4);
		g2.drawPolygon (vertexX, vertexY, 4);
		g2.setPaint (Color.BLACK);
		g2.fillOval (centerX-1, centerY-1, 2, 2); // Draw the center point
	}

	protected void setVertices ()
	{
	}

	public boolean isIn (int X, int Y)
	{
		return polygon.contains (X, Y);
	}

	public void move (int deltaX, int deltaY)
	{
		centerX += deltaX;
		centerY += deltaY;
		setVertices ();
	}

	protected void rotate ()
	{
		double A = Math.toRadians (angle);
		for (int i = 0; i < 4; i++)
		{
			double dx = vertexX[i] - centerX;
			double dy = centerY - vertexY[i];
			double r = sqrt (dx*dx + dy*dy);
			double t = atan2 (dy, dx);
			t += A;
			vertexX[i] = centerX + (int) (r * cos(t));
			vertexY[i] = centerY - (int) (r * sin(t));
		}
	}
}

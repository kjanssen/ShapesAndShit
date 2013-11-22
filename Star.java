// File: Star.java
// CS 360 - Fall 2013 - Watts
// Project 2
// November 2013 
// Originally created by Amandeep Gill
/*
   This file contains the description and implementation
   of a class called Star. 
 */

import java.awt.*;
import javax.swing.*;

public final class Star extends Shape
{
    private int numSides;
    private double angle;

    private RegularPolygon outer;
    private RegularPolygon inner;
    private int[] vertexX;
    private int[] vertexY;
    private Polygon polygon;

    public Star()
    {
        outer = new RegularPolygon();
        inner = new RegularPolygon();
        side = outer.getSide();
        numSides = outer.getNumSides();
        angle = 0.0;

        setVertices();
    }

    public Star(int N, int S, int X, int Y, Color C, double A)
    {
        numSides = N;
        side = S;
        centerX = X;
        centerY = Y;
        color = C;
        angle = A;

        outer = new RegularPolygon(N, S, X, Y, C, A);
        inner = new RegularPolygon(N, S/2, X, Y, C, A + 360/(2*N));

        setVertices();
    }

    public void setVertices()
    {
	outer = new RegularPolygon(numSides, side, centerX, centerY, color, angle);
	inner = new RegularPolygon(numSides, side/2, centerX, centerY, color, angle + 360/(2*numSides));

        numSides = outer.getNumSides();
        vertexX = new int[2*numSides];
        vertexY = new int[2*numSides];

        int[] outerVx = outer.getVertexX();
        int[] outerVy = outer.getVertexY();
        int[] innerVx = inner.getVertexX();
        int[] innerVy = inner.getVertexY();
        for (int i = 0; i < 2 * numSides; i += 2)
        {
            vertexX[i] = innerVx[i/2];
            vertexY[i] = innerVy[i/2];
            vertexX[i+1] = outerVx[i/2];
            vertexY[i+1] = outerVy[i/2];
        }

        polygon = new Polygon(vertexX, vertexY, 2 * numSides);
    }

    public void setCenterX (int X)
    {
        outer.setCenterX(X);
        inner.setCenterX(X);
        setVertices ();
    }

    public void setCenterY (int Y)
    {
        outer.setCenterY(Y);
        inner.setCenterY(Y);
        setVertices ();
    }

    public void paintComponent (Graphics2D g2)
    {
        g2.setPaint (color);
        g2.fillPolygon (vertexX, vertexY, 2 * numSides);
        g2.drawPolygon (vertexX, vertexY, 2 * numSides);
        
        if (highlited) {
            g2.setPaint(highlite);
            g2.fillPolygon(vertexX, vertexY, 2 * numSides);
        }

        g2.setPaint (Color.BLACK);
        g2.fillOval (centerX-1, centerY-1, 2, 2); // Draw the center point
    }

    public boolean isIn (int X, int Y)
    {
        if (polygon.contains (X, Y))
            highlited = true;
        else
            highlited = false;
        return highlited;
    }

    public void move (int deltaX, int deltaY)
    {
        centerX += deltaX;
        centerY += deltaY;
        outer.move(deltaX, deltaY);
        inner.move(deltaX, deltaY);
        setVertices ();
    }

    public void fromString (String str)
    {
        String [] parts = str.split (" ");
        try
        {
            centerX = Integer.parseInt(parts[0]);
            centerY = Integer.parseInt(parts[1]);
            numSides = Integer.parseInt(parts[2]);
            side = Integer.parseInt(parts[3]);
            color = new Color(Integer.parseInt(parts[4]));
            angle = Double.parseDouble (parts[5]);
	    setVertices();
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
        string += numSides + " ";
        string += side + " ";
        string += color.getRGB() + " ";
        string += angle + " ";
        return string;
    }

    public void modifyShape (JFrame frame, int x, int y)
    {
        StarDialog stardialog = new StarDialog (frame, true, x, y, numSides, side, angle, color);
        if (stardialog.getAnswer() == true)
        {
            numSides = stardialog.getNumSides();
            side = stardialog.getSide ();
            angle = stardialog.getAngle ();
            color = stardialog.getColor ();
            vertexX = new int[numSides];
            vertexY = new int[numSides];
            centerX = x;
            centerY = y;
            outer = new RegularPolygon(numSides, side, x, y, color, angle);
            inner = new RegularPolygon(numSides, side/2, x, y, color, angle + 360/(2*numSides));
            setVertices();
        }
    }

    public double perimeter ()
    {
        return numSides * side;
    }

    public String getName ()
    {
        return "Star";
    }

    public void rotateBy (double A)
    {
	angle += A;
	setVertices();
    }

    public void scale (double R)
    {
	side = ((int)(side * R) == 0) ? side : (int)(side * R);
	setVertices();
    }
}

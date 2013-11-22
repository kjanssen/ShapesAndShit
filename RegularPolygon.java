// File: RegularPolygon.java
// CS 360 - Fall 2012 - Watts
// Project 2
// October 2012 
// Originally created by Dr. Watts
// http://watts.cs.sonoma.edu
/*
   This file contains the description and implementation
   of a parent class called Polygon. 
   */

import static java.lang.Math.*;
import java.awt.*;
import javax.swing.*;

public final class RegularPolygon extends Shape
{
    private int [] vertexX;
    private int [] vertexY;
    private int numSides;
    private Polygon polygon;
    private double angle = 0.0;

    public RegularPolygon ()
    {
        numSides = 5;
        side = 20;
        vertexX = new int[numSides];
        vertexY = new int[numSides];
    }

    public RegularPolygon (int N, int S, int X, int Y, Color C, double A)
    {
        numSides = N;
        side = S;
        centerX = X;
        centerY = Y;
        color = C;
        angle = A;

        vertexX = new int[numSides];
        vertexY = new int[numSides];
        setVertices();
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

    public int getNumSides()
    {
        return numSides;
    }

    public int[] getVertexX ()
    {
        return vertexX;
    }

    public int[] getVertexY ()
    {
        return vertexY;
    }

    public void paintComponent (Graphics2D g2)
    {
        g2.setPaint (color);
        g2.fillPolygon (vertexX, vertexY, numSides);
        g2.drawPolygon (vertexX, vertexY, numSides);
        
        if (highlited) {
            g2.setPaint(highlite);
            g2.fillPolygon(vertexX, vertexY, numSides);
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
        setVertices ();
    }

    public void setVertices ()
    {
        double t = 2 * Math.PI / numSides;
        double r = side / Math.sin(t / 2);
        double dt = ((numSides + 1)  % 2) * t / 2;

        for (int i = 0; i < numSides; i++)
        {
            vertexX[i] = (int)(r * Math.sin(i * t - dt)) + centerX;
            vertexY[i] = (int)(-r * Math.cos(i * t - dt)) + centerY;
        }

        rotate ();
        polygon = new Polygon (vertexX, vertexY, numSides);
    }

    private void rotate ()
    {
        double A = Math.toRadians (angle);
        for (int i = 0; i < numSides; i++)
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

    public void rotateBy (double A)
    {
	angle += A;
	setVertices();
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
            numSides = Integer.parseInt(parts[2]);
            side = Integer.parseInt(parts[3]);
            color = new Color(Integer.parseInt(parts[4]));
            angle = Double.parseDouble (parts[5]);
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
        string += numSides + " ";
        string += side + " ";
        string += color.getRGB() + " ";
        string += angle + " ";
        return string;
    }

    public void modifyShape (JFrame frame, int x, int y)
    {
        PolygonDialog polygondialog = new PolygonDialog (frame, true, x, y, numSides, side, angle, color);
        if (polygondialog.getAnswer() == true)
        {
            numSides = polygondialog.getNumSides();
            side = polygondialog.getSide ();
            angle = polygondialog.getAngle ();
            color = polygondialog.getColor ();
            vertexX = new int[numSides];
            vertexY = new int[numSides];
            centerX = x;
            centerY = y;
            setVertices();
        }
    }

    public double perimeter ()
    {
        return numSides * side;
    }

    public String getName ()
    {
        return "Regular Polygon";
    }

    public void scale (double R)
    {
        side *= R;
        setVertices();
    }
}

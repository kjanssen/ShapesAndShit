// File: Circle.java
// CS 360 - Fall 2012 - Watts
// Project 2
// October 2012 
// Originally created by Dr. Watts
// http://watts.cs.sonoma.edu
/*
   This file contains the description and implementation
   of a class called Circle. 
 */

import static java.lang.Math.*;
import java.awt.*;
import javax.swing.*;

public final class Circle extends Shape
{
    public Circle ()
    {
        side /= 2;
    }

    public Circle (int S, int X, int Y, Color C)
    {
        side = S;
        centerX = X;
        centerY = Y;
        color = C;
    }

    public Circle (Circle C)
    {
        side = C.side;
        centerX = C.centerX;
        centerY = C.centerY;
        color = C.color;
    }

    public void setRadius (int R)
    {
        side = R;
    }

    public int getRadius ()
    {
        return side;
    }

    public void modifyShape (JFrame frame, int x, int y)
    {
        CircleDialog circledialog = new CircleDialog (frame, true, x, y, side, color); 
        if (circledialog.getAnswer() == true)
        {
            side = circledialog.getRadius ();
            color = circledialog.getColor ();
        }
    }

    public double area ()
    {
        return Math.PI * side * side;
    }

    public double perimeter ()
    {
        return 2 * Math.PI * side;
    }

    public String getName ()
    {
        return "Circle";
    }

    public void paintComponent (Graphics2D g2)
    {
        g2.setPaint (color);
        g2.fillOval (centerX-side, centerY-side, 2*side, 2*side);
        g2.drawOval (centerX-side, centerY-side, 2*side, 2*side);

        if (highlited) {
            g2.setPaint(highlite);
            g2.fillOval (centerX-side, centerY-side, 2*side, 2*side);
        }

        g2.setPaint (Color.BLACK);
        g2.fillOval (centerX-1, centerY-1, 2, 2); // Draw the center point
    }

    public boolean isIn (int X, int Y)
    {
        int deltaX = X - centerX;
        int deltaY = Y - centerY;
        double dist = sqrt (deltaX * deltaX + deltaY * deltaY);
        //System.out.println ("dist = " + dist + " side = " + side);
        highlited = dist <= side;
        return highlited;

    }
}

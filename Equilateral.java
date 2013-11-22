// File: Equilateral.java
// CS 360 - Fall 2012 - Watts
// Project 2
// October 2012 
// Originally created by Dr. Watts
// http://watts.cs.sonoma.edu
/*
   This file contains the description and implementation
   of a class called Equilateral. 
 */

import static java.lang.Math.*;
import java.awt.*;
import javax.swing.*;

public final class Equilateral extends Triangle
{
    public Equilateral ()
    {
    }

    public Equilateral (Equilateral E)
    {
        side = E.side;
        centerX = E.centerX;
        centerY = E.centerY;
        color = E.color;
        angle = E.angle;
        for (int i = 0; i < 3; i++)
        {
            vertexX[i] = E.vertexX[i];
            vertexY[i] = E.vertexY[i];
        }
    }

    public Equilateral (int S, int X, int Y, Color C, double A)
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
        vertexX[0] = vertexY[0] = 0;
        vertexX[1] = side/2; vertexY[1] = (int) (-side * sqrt(3) / 2);
        vertexX[2] = side; vertexY[2] = 0;
        double perim = perimeter ();
        int inX = 0, inY = 0;
        if (perim > 0)
        {
            inX = (int) (side * (vertexX[0] + vertexX[1] + vertexX[2]) / perim);
            inY = (int) (side * (vertexY[0] + vertexY[1] + vertexY[2]) / perim);
        }
        for (int i = 0; i < 3; i++)
        {
            vertexX[i] += (centerX - inX);
            vertexY[i] += (centerY - inY);
        }
        rotate ();
        polygon = new Polygon (vertexX, vertexY, 3);
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
        EquilateralDialog equilateraldialog = new EquilateralDialog (frame, true, x, y, side, angle, color); 
        if (equilateraldialog.getAnswer() == true)
        {
            side = equilateraldialog.getSide ();
            angle = equilateraldialog.getAngle ();
            color = equilateraldialog.getColor ();
            setVertices ();
        }
    }

    public double area ()
    {
        return sqrt(3) * side * side / 4;
    }

    public double perimeter ()
    {
        return 3 * side;
    }

    public String getName ()
    {
        return "Equilateral";
    }

    public void scale (double R)
    {
        side = ((int)(side * R) == 0) ? side : (int)(side * R);
        setVertices();
    }
}

// File: Right.java
// CS 360 - Fall 2012 - Watts
// Project 2
// October 2012 
// Originally created by Dr. Watts
// http://watts.cs.sonoma.edu
/*
   This file contains the description and implementation
   of a class called Right. 
 */

import static java.lang.Math.*;
import java.awt.*;
import javax.swing.*;

public final class Right extends Triangle
{
    private int side2;

    public Right ()
    {
        side2 = 30;
    }

    public Right (Right R)
    {
        side = R.side;
        side2 = R.side2;
        centerX = R.centerX;
        centerY = R.centerY;
        color = R.color;
        angle = R.angle;
        for (int i = 0; i < 3; i++)
        {
            vertexX[i] = R.vertexX[i];
            vertexY[i] = R.vertexY[i];
        }
    }

    public Right (int S1, int S2, int X, int Y, Color C, double A)
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
        vertexX[0] = vertexY[0] = 0;
        vertexX[1] = 0; vertexY[1] = -side2;
        vertexX[2] = side; vertexY[2] = 0;
        double hyp = sqrt (side * side + side2 * side2);
        double perim = perimeter ();
        int inX = 0, inY = 0;
        if (perim > 0)
        {
            inX = (int) ((vertexX[0]* hyp + vertexX[1] * side + vertexX[2] * side2) / perim);
            inY = (int) ((vertexY[0]* hyp + vertexY[1] * side + vertexY[2] * side2) / perim);
        }
        for (int i = 0; i < 3; i++)
        {
            vertexX[i] += (centerX - inX);
            vertexY[i] += (centerY - inY);
        }
        rotate ();
        polygon = new Polygon (vertexX, vertexY, 3);
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
        double side3sq = side*side + side2*side2;
        return side + side2 + sqrt (side3sq);
    }

    public double area ()
    {
        return side * side2 / 2;
    }

    public void modifyShape (JFrame frame, int x, int y)
    {
        RightDialog rightdialog = new RightDialog (frame, true, x, y, side, side2, angle, color); 
        if (rightdialog.getAnswer() == true)
        {
            side = rightdialog.getSide ();
            side2 = rightdialog.getSide2 ();
            angle = rightdialog.getAngle ();
            color = rightdialog.getColor ();
            setVertices ();
        }
    }

    public String getName ()
    {
        return "Right";
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

    public void scale (double R)
    {
        side = ((int)(side * R) == 0) ? side : (int)(side * R);
	side2 = ((int)(side2 * R) == 0) ? side2 : (int)(side2 * R);
	setVertices();
    }
}

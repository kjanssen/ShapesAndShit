import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ShapeDialog extends JDialog implements ActionListener {
    protected JPanel myPanel = null;
    protected JButton OKButton = null, cancelButton = null;
    protected JTextField sideText;
    protected JTextField scaleText;
    protected JTextField angleText;
    protected ColorPanel colorPanel = null;
    protected JPanel buttonPanel = null;    
    protected Color currentColor = Color.white;
    protected int oldSide = 0;
    protected int side = 0;
    protected int oldSide2 = 0;
    protected int side2 = 0;
    protected int oldSide3 = 0;
    protected int side3 = 0;
    protected double oldAngle = 0;
    protected double angle = 0;
    protected double oldScale = 1.0;
    protected double scale = 1.0;
    protected boolean answer = false;
    public Color getColor() { return currentColor; }
    public int getSide() { return side; }
    public int getSide2() { return side2; }
    public int getSide3() { return side3; }
    public double getScale() { return scale; }
    public double getAngle() { return angle; }
    public boolean getAnswer() { return answer; }

    public ShapeDialog(JFrame frame, boolean modal) {
        super(frame, modal);
    }

    public ShapeDialog(JFrame frame, boolean modal, int x, int y, double A) {
        super(frame, modal);
        oldAngle = A;
        angle = oldAngle;
        myPanel = new JPanel();
        getContentPane().add(myPanel);
        myPanel.setLayout (new FlowLayout ());
        addTextAndButtons ();
        setTitle ("Modify Scalene Dialog");
        setLocation (x, y);
        setSize (300,375);
        setVisible(true);
    }

    private void addTextAndButtons ()
    {
        myPanel.add(new JLabel("Enter the amount to scale the shapes by:"));
        scaleText = new JTextField(((Double) scale).toString(), 20);
        scaleText.addActionListener(this);
        myPanel.add (scaleText);
        myPanel.add(new JLabel("Enter the angle:"));
        angleText = new JTextField(((Double) angle).toString(), 20);
        angleText.addActionListener(this);
        myPanel.add (angleText);
        buttonPanel = new JPanel();
        OKButton = new JButton("    OK    ");
        OKButton.addActionListener(this);
        buttonPanel.add(OKButton); 
        cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(this);
        buttonPanel.add(cancelButton); 
        myPanel.add(buttonPanel); 
    }
    
    public void actionPerformed(ActionEvent e) 
    {
        if(OKButton == e.getSource()) 
        {
            currentColor = colorPanel.getColor();
            answer = true;
            setVisible(false);
            getContentPane().remove(myPanel);
            try
            {
                scale = Integer.parseInt (scaleText.getText());
            }
            catch (NumberFormatException ex)
            {
                scale = oldSide;
            }
            try
            {
                angle = Double.parseDouble (angleText.getText());
            }
            catch (NumberFormatException ex)
            {
                angle = oldAngle;
            }
            answer = scale == 0 && angle == 0 ? false : true;
        }
        else if(cancelButton == e.getSource()) 
        {
            answer = false;
            setVisible(false);
        }
    }
}

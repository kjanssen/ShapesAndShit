import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ShapeDialog extends JDialog {
    protected JPanel myPanel = null;
    protected JButton OKButton = null, cancelButton = null;
    protected JTextField sideText;
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
}

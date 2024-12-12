import javax.swing.*;
import java.awt.*;

public class SimpleDrawingApp {
    public static void main(String[] args) {
        JFrame frame = new JFrame("зурлага");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);

        frame.add(new DrawingPanel());


        frame.setVisible(true);
    }
}

class DrawingPanel extends JPanel {
    
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        
        g.setColor(Color.BLUE); 
        g.fillRect(50, 50, 100, 100); 

        g.setColor(Color.RED); 
        g.fillOval(200, 50, 100, 100); 

        g.setColor(Color.GREEN); 
        g.drawLine(50, 200, 300, 300); 
    }
}

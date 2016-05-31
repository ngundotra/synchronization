import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
/**
 * Runs simulation of various sync processes. Specifically Fireflies whose voltage model is
 * assumed to be similar to the heart model.
 * 
 * @author Noah Gundotra, Mark Burrito
 * @version 5-31-16
 */
public class Sync
{
    public static final int WIDTH = 500; 
    public static final int HEIGHT = 500;
    public static final int NUM = 200;
    public static final int DELAY = 10; // in millis
    
    public Sync() {
        run();
    }

    static public void main(String[] args) {
        Sync a = new Sync();
    }
    
    public void run() {
        JFrame frame = new JFrame("Sync");
        frame.setSize(WIDTH, WIDTH);
        frame.setLayout(new BorderLayout());
        
        OPanel panel = new OPanel(NUM);
        frame.getContentPane().add(BorderLayout.CENTER, panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        
        JFrame circleFrame = new JFrame("Kuramoto");
        circleFrame.setSize(WIDTH, WIDTH);
        
        circleFrame.setLayout(new BorderLayout());
        CirclePanel circlePanel = new CirclePanel();
        circleFrame.getContentPane().add(BorderLayout.CENTER, circlePanel);
        circleFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        circleFrame.setVisible(true);
        
        long time = System.currentTimeMillis();
        while (true) {
            if (System.currentTimeMillis() - time > DELAY) {
                panel.repaint();
                circlePanel.repaint();
                time = System.currentTimeMillis();
            }
        }
    }
}

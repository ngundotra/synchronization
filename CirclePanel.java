import java.awt.*;
import javax.swing.*;
import java.util.*;
/**
 * Write a description of class CirclePanel here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CirclePanel extends JPanel
{
    private ArrayList<Firefly> flies = new ArrayList<Firefly>();
    public CirclePanel()
    {
        flies = Firefly.flies;  
    }
    
    public void paintComponent(Graphics g) {
        for (int i = 0; i < flies.size(); i++) {
            flies.get(i).drawKuramoto(g);
        }
    }
}

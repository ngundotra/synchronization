import java.awt.*;
import javax.swing.*;
import java.util.*;
/**
 * Takes number of Fireflies and displays them
 * 
 * @author Noah Gundotra
 * @version 5-23-16
 */
public class OPanel extends JPanel
{
    private final int NUM;
    
    /**
     * Constructor for objects of class OPanel
     */
    public OPanel(int num)
    {
        NUM = num;
        init();
    }
    
    public void init() {
        for (int i = 0; i < NUM; i++) {
            Firefly a = new Firefly(Firefly.getRandomCoord());
            System.out.println("Freq: " + a.getFreq() +  " Phase: " + a.getPhase());
        }
    }
    
    public void paintComponent(Graphics g) {
        for(Firefly fly: Firefly.flies) {
            fly.display(g);
        }
    }
}

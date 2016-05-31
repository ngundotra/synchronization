import java.awt.*;
import javax.swing.*;
import java.util.*;
/**
 * Model adjusts freq of fireflies to meet the freq of firefly who last flashed
 * 
 * @author Noah Gundotra
 * @version 5-31-16
 */
public class Firefly implements Oscillator
{
    static public double k = 0.1; // coupling constant
    static public ArrayList<Firefly> flies = new ArrayList<Firefly>();
    static public double freqDist = 0.1; 
    static public double phaseDist = 0.1;
    static public double error = 0.95;
    public int[] coords;
    public double t;
    public double phase;
    public double freq;

    public Firefly(int[] xy) {
        t = 0;
        coords = xy;
        phase = randomPhase();
        freq = randomFreq();
        //         phase = randomPhase();
        //         freq = Math.PI/6;
        //phase = Math.PI/2;
        //freq = randomFreq();
        flies.add(this);
    }

    public static int[] getRandomCoord() {
        int[] c = new int[2];
        c[0] = (int)(Math.random() * Sync.WIDTH);
        c[1] = (int)(Math.random() * Sync.HEIGHT);
        return c;
    }

    public void display(Graphics g) {
        updatePhase();
        // System.out.println("Phase: " + phase + " Freq: " + freq);
        g.drawOval(coords[0], coords[1], 5, 5);
        if (phase > error) {
            g.fillOval(coords[0], coords[1], 5, 5);
            // this is where snapshot takes place
        }
    }

    public void updatePhase() {
        t += freq;
        phase = Math.sin(t);
        if (phase > error) {
            // flash
            Firefly.adjustAll();
        }
    }

    public static void adjustAll()
    {
        //         System.out.println("Adjust all called!");
        for (Firefly fly: flies) {
            if (fly.phase < error)
                fly.adjust();
        }
    }

    public void adjust() {
        if (freq * Math.cos(t) < 0) {
            freq *= freq * Math.cos(t);
            // freq += (freq * Math.cos(t));
            if (freq > Math.PI * 2) {
                freq -= 0.2;
            }
        }
        else {
            freq /= freq * Math.cos(t);
            // freq -= (freq * Math.cos(t));
            if (freq < 0.01) {
                freq += 0.2;
            }
        }
        //System.out.println("cos of phase: " + Math.cos(t) + " freq: " + freq + " phase: " + phase);
        //System.out.println();
    }

    public double randomPhase() {
        // returns random phase from normal distribution
        Random randomGen = new Random();
        double rand = randomGen.nextGaussian() * phaseDist;
        if (rand > 1 || rand < -1)
            rand = randomGen.nextGaussian() * phaseDist;
        return rand;
    }

    public double randomFreq() {
        // returns random phase from normal distribution
        Random randomGen = new Random();

        double rand = randomGen.nextGaussian() * freqDist;
        if (0 > rand || rand > 1)
            rand = randomGen.nextGaussian() * freqDist;
        return rand;
    }

    public double getPhase() {
        return phase;
    }

    public double getFreq() {
        return freq;
    }

    public double getT() {
        return t;
    }

    public void drawKuramoto(Graphics g) {
        int r = 100;
        g.drawOval((int)(r * Math.cos(t)) + 250,  (int)(r * Math.sin(t)) + 250, 3, 3);
        if (phase > error)
            g.fillOval((int)(r * Math.cos(t)) + 250,  (int)(r * Math.sin(t)) + 250, 3, 3);
    }

    //     public static void increaseAllVoltage() {
    //         double totalFriendHelp = friendHelp;
    //         for (Firefly fly: children) {
    //             fly.voltage += totalFriendHelp;
    //             if (fly.voltage >= 1.0) {
    //                 fly.voltage = 0.0;
    //                 fly.dVdt = initDVdt;
    //                 if (numFlashed == 0)
    //                     Snapshot.createNew();
    //                 numFlashed++;
    //                 if (numFlashed > children.length) 
    //                     numFlashed = 0;
    //                 totalFriendHelp += 0.01;
    //             }
    //         }

}

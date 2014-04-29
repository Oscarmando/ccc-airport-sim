import java.awt.*;
import java.awt.image.*;
import javax.swing.*;
import java.io.*;
import javax.imageio.ImageIO;
import java.util.*;
/**
 * Attaches images to GUI
 * 
 * @author (Asa Swan) 
 * @version (4/25/2014)
 */
public class Images extends JComponent
{
    static String PLANE_PATH = "resources/Plane.png";
    static String AIRPORT_PATH = "resources/Airport.png";

    Toolkit tk = Toolkit.getDefaultToolkit();
    BufferedImage plane;
    BufferedImage airport;
    ArrayList<Flight> flights;
    City city;

    /**
     * Constructor for objects of class Images
     */
    public Images()
    {
        //setSize(5000,5000);
        flights = new ArrayList<Flight>();
        loadImages();
        setVisible(true);
    }

    /**
     * Loads Images onto screen
     */
    public void loadImages()
    {
        try{
            plane = ImageIO.read(new File(PLANE_PATH));
            airport = ImageIO.read(new File(AIRPORT_PATH));
        }catch(Exception e){
            System.out.println("ERROR");
        }
    }

    public void paint(Graphics g)
    {
        //super.paint(g);
        //Graphics2D g2d = (Graphics2D)g;
        //g.dispose();
        
        super.paint(g);
        Graphics2D g2d = (Graphics2D)g;
        g2d.drawImage(airport, 0, 0, 932, 732, null); //932,763
        g.dispose();
    }
}

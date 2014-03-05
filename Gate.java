/**
 * CCC-airport gate class constructor + act method
 * 
 */
import java.util.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.HashMap;
public class Gate 
{
    //private ArrayList<Flight> flight;
    //private HashMap<String, String> flightName;
    //private Random rand; //used for anything needed.
    private boolean gateAvailability; // if gate is available or not
    private int numberFlights; //tracks number of flights

    /**
     * Constructor for objects of class Gate
     */
    public Gate()//ArrayList<Flight> flights)
    {
        //flight = flights;
        //flights = new ArrayList<Flight>();
        //flightName = new HashMap<String, String>();
        //rand = new Random();
        gateAvailability = true;
    }

    public void flightCheck()
    {

    }

    public boolean getGateAvailability()
    {
        if(gateAvailability == true)
            return true;
        else
            return false;  
    }

    public void act()
    {

    }
}

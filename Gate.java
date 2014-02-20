/**
 * CCC-airport gate class constructor
 * Michael Focht
 * version 1
 */
import java.util.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.HashMap;
public class Gate
{
    private ArrayList<Flight> flight;
    private HashMap<String, String> flightName;
    private Random rand; //used for anything needed.
    private int maxGateCapacity; //for the maximum number flights at gate
    private boolean gateAvailability; // if gate is available or not
    
    /**
     * Constructor for objects of class Gate
     */
    public Gate(ArrayList<Flight> flights)
    {
        flights = new ArrayList<Flight>();
        flightName = new HashMap<String, String>();
        rand = new Random();
        maxGateCapacity = 4;
        gateAvailability = true;
    }
}

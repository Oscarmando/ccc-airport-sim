import java.util.*;
/**
 * Shuttles an arriving flight to a gate, and from a gate to takeoff.
 * Might replace some methods from gate class.
 * @author (Jeremiah) 
 * @version (.2)
 */
public class Runway
{
    private boolean runwayPhaseOne;//runway availability for taxiing to gate
    private boolean runwayPhaseTwo;//runway avaiability for taxiing to "sky"
    private boolean runwayAvailability;//If runway is open or not.
    private int runwayNumber;//number of runways
    private Flight flight;//importing flights
    private Gate gate;//importing gates
    private ArrayList<Flight> flights;//For all possible flights.
    private int MAX_CAPACITY = 1;//Max number of Flights to a runway.

    /**
     * Constructor for objects of class Runway
     */
    public Runway(int runwayNumber)
    {
        this.runwayNumber = runwayNumber;
        runwayPhaseOne = true;
        runwayAvailability = true;
        flights = new ArrayList<Flight>();
    }
    
    public void setPlane(Flight flight)
    {
        runwayAvailability = false;
        this.flight = flight;
        //flights.add(flight);
    }
    
    public void unsetPlane()
    {
        runwayAvailability = true;
        this.flight = null;
        //flights.remove(flight);
    }
    
    public void remove(Flight flight)
    {
        flights.remove(flight);
    }
    
    public boolean isClear()
    {
        return (flights.size() < MAX_CAPACITY);
    }
    
    public int getRunwayNumber()
    {
        return runwayNumber;
    }
    
    /**
     * Accessor method to return gate availability.
     */
    public boolean getRunwayAvailability()
    {
        return runwayAvailability;
    }

    /**
     * Accessor method for runways' availability for a landing. 
     */
    public boolean getRunAvailLand()
    {
        return runwayPhaseOne;
        
    }
    
     /**
     * Accessor method for runways' availability for taking off. 
     */
    public boolean getRunAvailTakeOff()
    {
        return runwayPhaseTwo;
        
    }
    
    /**
     * Gets flight.
     */
    public Flight getFlight()
    {
        return flight;
    }

    /**
     * Assigns runway landing availability to true or false for Operations.
     */
    public void setRunAvailabilityLand(boolean runwayPhaseOne)
    {
        this.runwayPhaseOne = runwayPhaseOne;
    }

    /**
     * Assigns runway taking off availability to true or false for Operations.
     */
    public void setRunAvailabilityAir(boolean runwayPhaseTwo)
    {
        this.runwayPhaseTwo = runwayPhaseTwo;
    }
}
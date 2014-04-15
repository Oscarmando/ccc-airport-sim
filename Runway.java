
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
    private int rNumb;//number of runways
    private Flight flight;//importing flights
    private Gate gate;//importing gates

    /**
     * Constructor for objects of class Runway
     */
    public Runway(int runway)
    {
        this.rNumb = runway;
        runwayPhaseOne = true;
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
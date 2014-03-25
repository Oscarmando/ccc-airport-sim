/**
 * CCC-airport gate class.
 * 
 */
public class Gate 
{
    private boolean gateAvailability; // if gate is available or not
    private int gateNumber;
    private Plane plane;

    /**
     * Constructor for objects of class Gate.
     */
    public Gate(int gateNumber)
    {
        this.gateNumber = gateNumber;
        gateAvailability = true;
    }
    
    /**
     * Mutator method to assign plane to gate.
     */
    public void setPlane(Plane plane)
    {
        this.plane = plane;
    }
    
    /**
     * Accessor method to get plane from gate.
     */
    public Plane getPlane()
    {
        return plane;
    }
    
    /**
     * Accessor method to return gate number.
     */
    public int getGateNumber()
    {
        return gateNumber;
    }

    /**
     * Accessor method to return gate availability.
     */
    public boolean getGateAvailability()
    {
        return gateAvailability;
    }
    
    /**
     * Mutator method to assign gate availabiliy to true or false.
     */
    public void setGateAvailability(boolean gateAvailability)
    {
      this.gateAvailability = gateAvailability;  
    }
}

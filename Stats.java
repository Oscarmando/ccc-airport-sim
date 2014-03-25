
/**
 * Write a description of class Stats here.
 * 
 * @author (Statistics group) 
 * @version (3/4/2014)
 */
public class Stats implements Actor
{
    private int avgFlightTime;
    private int totalFlightTimes;
    private boolean infoRecieved;
    Flight currentFlight;

    /**
     * Constructor for objects of class Stats
     */
    public Stats()
    {
        avgFlightTime = 0;
        totalFlightTimes = 0;
        infoRecieved = false;
        currentFlight = null;
    }

    public void newFlight(Flight currentFlight){
        this.currentFlight = currentFlight;
    }

    /**
     * Actor methods
     */
    public void act(int tick)
    {
        if(infoRecieved == true)
        {
            calcAvgFlightTime();
            printReport();
            infoRecieved = false;
        }
    }

    public void recieveInfo()
    {
        infoRecieved = true;
    }

    /**
     * Calculates average time in air
     */
    public void calcAvgFlightTime()
    {
        int flightTime = (currentFlight.getActualLandTime() - currentFlight.getTakeoffTime());
        totalFlightTimes += flightTime;
        avgFlightTime =  totalFlightTimes / Operations.getPlanes().size();
    }

    /**
     * Constructor for objects of class Stats
     */
    public void printReport()
    {
        System.out.println("Average flight time: " + avgFlightTime);
    }
}

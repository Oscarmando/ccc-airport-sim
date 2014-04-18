
/**
 * Tracks the flights and time it takes to prep, take off and land
 */
public class Stats implements Actor
{
    private int avgFlightTime;
    private int totalFlightTimes;
    private int totalFlightsLanded;
    private boolean infoRecieved;
    Flight currentFlight;
    Operations operations;

    /**
     * Constructor for objects of class Stats
     */
    public Stats(Operations operations)
    {
        this.operations = operations;
        avgFlightTime = 0;
        totalFlightTimes = 0;
        totalFlightsLanded = 0;
        infoRecieved = false;
        currentFlight = null;
    }

    public void newFlight(Flight currentFlight){
        this.currentFlight = currentFlight;
    }

    /**
     * If info is received tracks number of flights and calculates avg flight time
     */
    public void act(int tick)
    {
        if(infoRecieved == true)
        {
            totalFlightsLanded++;
            calcAvgFlightTime();
            printReport();
            infoRecieved = false;
        }
    }
    
    /**
     *  Sets boolean infoRecieved to true.
     */
    public void recieveInfo()
    {
        infoRecieved = true;
    }

    /**
     * Calculates average time in air
     */
    public void calcAvgFlightTime()
    {
        //int flightTime = (currentFlight.getActualLandTime() - currentFlight.getTakeoffTime());
        //totalFlightTimes += flightTime;
        avgFlightTime =  totalFlightTimes / totalFlightsLanded;
    }

    /**
     * prints report of all the stats
     */
    public void printReport()
    {
        System.out.println("Average flight time: " + avgFlightTime);
    }
}

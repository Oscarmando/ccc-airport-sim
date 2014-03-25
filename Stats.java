
/**
 * Write a description of class Stats here.
 * 
 * @author (Statistics group) 
 * @version (3/4/2014)
 */
public class Stats implements Actor
{
    private int avgFlightTime;
    private boolean infoRecieved;
    Flight f;

    /**
     * Constructor for objects of class Stats
     */
    public Stats()
    {
        f = null;
        avgFlightTime = 0;
        infoRecieved = false;
    }

    public void newFlight(Flight fl){
        this.f = fl;
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
        avgFlightTime = f.getActualLandTime() / f.getnumofFlights();
    }

    /**
     * Constructor for objects of class Stats
     */
    public void printReport()
    {
        System.out.println("Average flight time: " + avgFlightTime);
    }
}

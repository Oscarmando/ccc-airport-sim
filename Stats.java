import java.util.ArrayList;

/**
 * Tracks information regarding all flights, gates, and airports in general.
 * Calculates various statistical data and reports to GUI.
 * CS216 Class
 */
public class Stats
{
    private GUI gui;
    /**Fields for calculating Average Gate Time*/
    private int totalGateTimes = 0;
    private int totalGatePlanes = 0;
    private boolean gateTimesChanged;
    /**Fields for calculating... */
    private double planesAtGates = 0;
    private int incPlanes = 0;
    private int outPlanes = 0;

    /**
     * Constructor for objects of class Stats
     */
    public Stats(GUI gui)
    {
        this.gui = gui;
    }

    /**
     * @param the gate time for one flight
     * Method keeps a running number on the total gate times for
     * all flights and the total number of flights that have left
     * their gates.
     * Uses this info to calculate the average gate time and display
     * to GUI
     */
    public void calcAvgGateTime(int flightGateTime)
    {
        totalGateTimes += flightGateTime;
        totalGatePlanes++;
        gui.avgGateTimeUpdate(totalGateTimes / totalGatePlanes);
    }

    /**
     * @param total gates in airport, whether adding or subtracting plane
     * Method keeps adding if planes occupy a gate. If not, it 
     * subtracts it from the total.
     * The information is then passed to the GUI.
     */
    public void calcPercGateUsed(int totalGates, boolean adding)
    {  
        double totalGatesDouble=(double) totalGates;
        if(adding)
            planesAtGates++;
        else
            planesAtGates--;
        gui.gatePercUsed(planesAtGates / totalGates *100);
    }
    
    /**
     * Total number of incoming Flights airport has seen over during of simulation.
     */
    public void calcTotalInc()
    {
        incPlanes++;
        System.out.println("Inc total " + incPlanes);
    }
    
    /**
     * Adjust the total number of inc flights if removed.
     */
    public void removeFlightInc()
    {
        incPlanes--;
        System.out.println("Inc total " + incPlanes);
    }
    
    /**
     * Total number of outgoing Flights airport has seen oer during of simulation.
     */
    public void calcTotalOut()
    {
        outPlanes++;
        System.out.println("Out total " + outPlanes);
    }
}

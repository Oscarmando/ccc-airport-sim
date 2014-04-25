import java.util.ArrayList;

/**
 * Tracks information regarding all flights, gates, and airports in general.
 * Calculates various statistical data and reports to GUI.
 */
public class Stats
{
    private GUI gui;
    /**Fields for calculating Average Gate Time*/
    private int totalGateTimes;
    private int totalGatePlanes;
    private boolean gateTimesChanged;
    /**Fields for calculating... */

    /**
     * Constructor for objects of class Stats
     */
    public Stats(GUI gui)
    {
        this.gui = gui;
        totalGateTimes = 0;
        totalGatePlanes = 0;
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
        gui.statsUpdate(totalGateTimes / totalGatePlanes);
    }
}

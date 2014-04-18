
/**
 * Write a description of class FlightController here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class FlightController
{
    private Operations operations;
    private Simulator sim;

    /**
     * Constructor for objects of class FlightController
     */
    public FlightController(Operations operations, Simulator sim)
    {
     this.operations = operations;
     this.sim = sim;
    }
    
      /**
     * Reset function: Resets the timer
     */
    public void reset()
    {
        System.out.print("\u001b");
        System.out.flush();
        System.out.println("Reset");
    }

    /**
     * Quit function: quit the application.
     */
    public void quit()
    {
        System.exit(0);
    }
    
    public void pause()
    {
        sim.setPaused(true);
    }
    
    public void play()
    {
        sim.setPaused(false);
    }
}

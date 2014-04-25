
/**
 * Allows us to interact with GUI and change status of Simulation while
 * running.
 * 
 * @author CS216 Class 
 * @version (a version number or a date)
 */
public class FlightController
{
    private Operations operations;
    private TickLoop tickLoop;

    /**
     * Constructor for objects of class FlightController
     */
    public FlightController(Operations operations, TickLoop tickLoop)
    {
     this.operations = operations;
     this.tickLoop = tickLoop;
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
    
    /**
     * Calls the method to pause Simulation.
     */
    public void pause()
    {
        tickLoop.pause();
    }
    
    /**
     * Calls the method to resume the Simulation.
     */
    public void play()
    {
        tickLoop.play();
    }
}

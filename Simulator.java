import java.util.*;
/**
 * Run the simulation by iterating time after a certain wait interval.
 * Calls operations to collect actors to act.
 * 
 * @author CS216 Class
 * @version 2012.04.08
 */
public class Simulator
{
    //Speed at which each simulation tick will run in milliseconds
    final int tickSpeed = 500;
    //Limit at which the simulation resets itself
    static int tickRollover = 1440;
    //Simulation time
    public int time;

    City ct;

    /**
     * Start new simulation
     */
    public Simulator(){
        time = 0;
        ct = new City();
        tickLoop();
    }

    /**
     * Run the simulation for a fixed number of ticks.
     * Pause after each tick to give simulation real time.
     */
    public void tickLoop(){
        while(true){
            tick();
            wait(tickSpeed);

            for(int i=0; i < ct.getActors().size(); i++){
                ct.getActors().get(i).act(time);
            }
        }
    }

    private void tick(){
        time++;
    }

    /**
     * Wait for a specified number of milliseconds before finishing.
     * This provides an easy way to cause a small delay.
     * @param milliseconds The number of milliseconds to wait.
     */
    private void wait(int milliseconds)
    {
        try
        {
            Thread.sleep(milliseconds);
        } 
        catch (InterruptedException e)
        {
            // ignore the exception
        }
    }
}

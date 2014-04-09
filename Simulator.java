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
    final int tickSpeed = 300;
    //Limit at which the simulation resets itself
    static int ticklimit = 1000;
    //Simulation time
    public int time;
    
    Operations op;
    
    /**
     * Start new simulation
     */
    public Simulator(){
        time = 0;
        System.out.print('\u000C'); //Clears console
        op = new Operations();
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
            
            for(int i=0; i < op.getActors().size(); i++){
                op.getActors().get(i).act(time);
            }
            //for(Actor a: op.getActors()){
             //   a.act(time);
            //}
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

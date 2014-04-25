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
    //Used to pause Simultion
    public boolean paused = false;

    Thread myThread = new Thread();

    //Operations operations;
    Thread tickloop;
    /**
     * Start new simulation
     */
    public Simulator() throws InterruptedException
    {
        //operations = new Operations(this, tickloop);
        tickloop = new TickLoop(this);
        tickloop.start();
    }

    /*public void setPaused(boolean paused)
    {
        this.paused = paused;
        synchronized(tickloop) {
            if(this.paused == false)
                tickloop.notify();
            else
                this.paused = paused;
        }
    }

    public boolean paused()
    {
        return paused;
    }*/
}


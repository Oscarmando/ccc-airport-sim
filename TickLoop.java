
/**
 * Write a description of class TickLoop here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class TickLoop extends Thread
{
    //Speed at which each simulation tick will run in milliseconds
    final int tickSpeed = 500;
    //Limit at which the simulation resets itself
    static int tickRollover = 1440;
    //Simulation time
    public int time;
    //Used to pause Simultion
    public boolean paused = false; 

    Operations operations;
    Simulator sim;
    public TickLoop(Operations operations,Simulator sim)
    {
        this.operations = operations; 
        this.sim = sim;
    }

    public void run()
    {
        while(true){
            tick();
            synchronized(this){
            if(sim.paused == true){
                try
                {
                    wait();
                } 
                catch (InterruptedException e)
                {
                     //ignore the exception
                }
            }
        }
            hold(tickSpeed);

            for(int i=0; i < operations.getActors().size(); i++){
                operations.getActors().get(i).act(time);
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
    private void hold(int milliseconds)
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

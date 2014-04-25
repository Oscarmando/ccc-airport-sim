
/**
 * Write a description of class TickLoop here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class TickLoop extends Thread
{
    //Speed at which each simulation tick will run in milliseconds
    private int tickSpeed = 100;
    //Simulation time
    public int time;
    //Used to pause Simultion
    public boolean paused = false; 
    public boolean started = false;

    Operations operations;
    Simulator sim;
    public TickLoop(Simulator sim)
    {
        operations = new Operations(this);
        this.sim = sim;
    }

    public void run()
    {
        started = true;
        while(true){
            tick();
            synchronized(this){
                if(paused == true){
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

    public void play(){
        paused = false;

        if(started){
            synchronized(this){
                notify();
            }
        }else{
            start();
        }

    }

    public void pause()
    {
        paused = true;
    }

    public boolean paused()
    {
        return paused;
    }

    public void setSpeed(int sp)
    {
        tickSpeed = sp;
    }

    public int getSpeed(){
        return tickSpeed;
    }
}

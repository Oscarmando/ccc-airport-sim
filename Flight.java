public class Flight
{
    // instance variables - replace the example below with your own
    private boolean isDeparted;
    private boolean isReady;
    private boolean isWaiting;
    private int takeoffTime;
    private int estLandTime;
    private int actualLandTime;
    private boolean fuel;
    private int delayTime;
    private int duration;
    private int delay;

    /**
     * Constructor for objects of class Flight
     */
    public Flight()
    {
        isDeparted = true;
        isReady = true;
        isWaiting = false;
        takeoffTime = 0;
        estLandTime = 0;
        actualLandTime = 0;
        fuel = true;
        delayTime = 0;
        duration = 0;
        delay = 0;
    }
    
    public int getTakeoffTime()
    {
        return takeoffTime;
    }
    public int getEstLandTime()
    {
        return estLandTime;
    }
    public int getActualLandTime()
    {
        return actualLandTime;
    }
    public void setTakeoffTime(int takeOffTime)
    {
        takeoffTime = takeOffTime;
    }
    public void setEstimatedLandTime(int estimatedLandingTime)
    {
        estLandTime = estimatedLandingTime;
    }
    public void setActualLandTime(int actualLandingTime)
    {
        actualLandTime = actualLandingTime;
    }
    public void setDuration(int flightDuration)
    {
        duration = flightDuration;
    }
    public void land()
    {
        if(isDeparted == true)
        {
            System.out.println("The flight has landed at the gate.");
            isDeparted = false;
        }
        else
        {
            System.out.println("The flight is already at the gate.");
        }
    }
    public void takeoff()
    {
        if((isDeparted == false) &&  (isReady == true))
        {
            isDeparted = true;
            System.out.println("The flight has left the gate, and is in the air.");
        }
        else
        {
            System.out.println("The flight is already in the air.");
        }
    }
    public void getStatus()
    {
        System.out.println("Departed: "+isDeparted);
        System.out.println("Ready: "+isReady);
        System.out.println("Waiting: "+isWaiting);
    }
    public void changeStatus()
    {
        if(isWaiting = true)
        {
            isWaiting = false;
        }
        else
        {
         isWaiting = true;
    }
}
    public void act(int tick)
    {
        estLandTime = duration + tick;
        if(tick == estLandTime - 20)
        {
            System.out.println("Requesting Gate");
            //send to gate
        }
        if(tick == estLandTime)
        {
            System.out.println("Flight Landed");
            //isLanded = true;
            //send to stats
        }
        if(delay > 0)
        {
            actualLandTime = estLandTime + delay;
        }
        System.out.println(tick +". "+actualLandTime);
        tick = tick + 1;
    }
        
    

    
}

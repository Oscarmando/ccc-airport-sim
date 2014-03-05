public class Flight implements Actor
{
    // instance variables - replace the example below with your own
    private boolean isDeparted;
    private boolean isReady;
    private int takeoffTime;
    private int estLandTime;
    private int actualLandTime;
    private int duration;
    private Gate g;
    private Simulator sim;
    private Stats st;

    /**
     * Constructor for objects of class Flight
     */
    public Flight(Simulator sim, Stats st)
    {
        this.sim = sim;
        isDeparted = true;
        isReady = true;
        takeoffTime = 0;
        estLandTime = 0;
        actualLandTime = 0;
        duration = 30;
        this.st = st;
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
    }

    public void act(int tick)
    {
        actualLandTime = duration;
        if(tick == actualLandTime - 20)
        {
            System.out.println("Requesting Gate");
            //send to gate
            g = sim.needGate(this);
        }
        if(tick == actualLandTime)
        {
            isDeparted = false;
            System.out.println("Flight Landed");
            st.recieveInfo();
        }
    }
}

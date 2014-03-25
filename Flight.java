import java.util.Random;

public class Flight implements Actor
{
    // instance variables - replace the example below with your own
    private boolean isDeparted;
    private boolean isReady;
    private boolean isFlying;
    private boolean atGate;
    private int takeoffTime;
    private int estLandTime;
    private int actualLandTime;
    private int difference;
    private int prepTime;
    private Random random = new Random();
    private Gate g;
    private Plane plane;

    /**
     * Constructor for objects of class Flight
     */
    public Flight(Plane plane)
    {
        this.plane = plane;
        isDeparted = false;
        isReady = true;
        atGate = false;
        takeoffTime = random.nextInt(5)+5;
        actualLandTime = random.nextInt(20)+ 20;
        prepTime = 30;
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

    public void land()
    {
        if(isDeparted == true)
        {
            System.out.println("The flight is on the ground.");
            isDeparted = false;
            Operations.getStats().recieveInfo();
        }
        else
        {
            System.out.println("The flight is already on the ground.");
        }
    }

    public void takeoff()
    {
        if((isDeparted == false) &&  (isReady == true))
        {
            isDeparted = true;
            isReady = false;
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

    public void requestGate()
    {
        System.out.println("Requesting Gate");
        g = Operations.getOpenGate();
        if(g !=null)
            atGate = true;

    }

    public void attachPlane(Plane p){
        plane = p;
    }

    public void act(int tick)
    {
        difference = actualLandTime - 10;
        if(isDeparted == false && isReady == true){
            if(tick == difference) 
            {
                takeoff();
            }
        }
        else if(atGate == false && isDeparted == true){
            if(tick == difference + prepTime)
            {
                requestGate();
                if(g == null)
                    actualLandTime = actualLandTime +20;
                else{
                    land();
   
                }
            }    
        }

    }
}
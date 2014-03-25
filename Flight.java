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
    private int numofFlights;//Used for calculation puproses(temp)
    private Random random = new Random();
    private Gate g;
    private Simulator sim;
    private Stats st;
    private Plane plane;

    /**
     * Constructor for Flight.
     */
    public Flight(Simulator sim, Stats st, Plane plane)
    {
        this.sim = sim;
        this.plane = plane;
        this.st = st;
        isDeparted = true;
        isReady = true;
        atGate = false;
        takeoffTime = random.nextInt(5)+5;
        actualLandTime = random.nextInt(20)+ 20;
        prepTime = 30;
    }
    
    /**
     * Returns the variable numofFlights
     */
    public int getnumofFlights()
    {
        return numofFlights;
    }

    /**
     * Returns the variable takeoffTime.
     */
    public int getTakeoffTime()
    {
        return takeoffTime;
    }

    /**
     * Returns the variable estLandTime.
     */
    public int getEstLandTime()
    {
        return estLandTime;
    }

    /**
     * Returns the variable actualLandTime.
     */
    public int getActualLandTime()
    {
        return actualLandTime;
    }

    /**
     * Changes the value of takeoffTime.
     */
    public void setTakeoffTime(int takeOffTime)
    {
        takeoffTime = takeOffTime;
    }

    /**
     *Changes the value of estLandTime. 
     */
    public void setEstimatedLandTime(int estimatedLandingTime)
    {
        estLandTime = estimatedLandingTime;
    }

    /**
     * Changes the value of actualLandTime.
     */
    public void setActualLandTime(int actualLandingTime)
    {
        actualLandTime = actualLandingTime;
    }

    /**
     * "Lands" the flight and sets booleans to the needed values.
     * Also prints out to the screen.
     */
    public void land()
    {
        if(isDeparted == true)
        {
            System.out.println("The flight is on the ground.");
            isDeparted = false;
            isReady = true;
            numofFlights++;
        }
        else
        {
            System.out.println("The flight is already on the ground.");
        }
    }

    /**
     * The flight "Takesoff" and sets booleans to the needed values.
     * Also prints out to the screen.
     */
    public void takeoff()
    {
        if((isDeparted == false) &&  (isReady == true))
        {
            isDeparted = true;
            isReady = false;
            atGate = false;
            System.out.println("The flight has left the gate, and is in the air.");
        }
        else
        {
            System.out.println("The flight is already in the air.");
        }
    }

    /**
     * Prints out to the screen the planes status. 
     */
    public void getStatus()
    {
        System.out.println("Departed: "+isDeparted);
        System.out.println("Ready: "+isReady);
    }
 
    /**
     * Checks to see if a gate is open, if so sets needed boolean to true.
     */
    public void requestGate()
    {
      System.out.println("Requesting Gate");
      g = sim.needGate(this);
      if(g !=null)
       atGate = true;
        
        
        
        
        
        
    }

    /**
     * The main acting method that allows the flight to do commands,
     * under specific situations.
     */
    public void act(int tick)
    {
        difference = actualLandTime - 10;
        if(atGate == false && isDeparted == true){
        if(tick == difference)
        {
         requestGate();
         if(g == null)
            actualLandTime = actualLandTime +20;
            else{
            land();
            st.recieveInfo();
        }
        }    
     }
     else if(isDeparted == false && isReady == true)
     if(tick == difference + prepTime) 
     {
       takeoff();
       actualLandTime = tick + random.nextInt(20)+ 20;
     }
    }
}
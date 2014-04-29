import java.util.Random;
/**
 * Is the actual Flight(Plane), that takes off into air,lands,taxi's at Runway,
 * Rests at Gate.
 */
public class Flight implements Actor
{
    private Gate gate;//Instance of Gate.
    private Operations operations;//Instance of Operations.
    private City ct;//Instance of City.
    private GUI gui;//Instance of GUI.
    private Stats st;//Instance of Stats.
    private Runway runway;//Instance of Runway.
    private boolean isGrounded;//Tells program if Flight is in air or on ground.
    private boolean isAtRunway = false;//Tells program if Flight is at Runway.
    private boolean shouldLand = false;//Tells Flight if it should attempt to land.
    private boolean shouldDepart = false;//Tells Flight if it should attempt to takeoff.
    private boolean isTaxing = false;//Tell program if Flight is Taxing on Runway.
    private boolean isReady = false;//Tells program if Flight is ready to takeoff.
    private boolean takeOff = false;//Tells Flight to actually takeoff.
    private int initTick = -1;//Used to allow actor to intialize variables.
    private int currentTick;//Used to keep track of Thread's tick.
    private int gateTime;//Used to tell Flight how long to wait at gate.
    private int landTime;//Used to tell Flight how long until land attempt.
    private int runwayTime;//Used to tell Flight how long to stay on Runway.
    private int takeOffTime;//Used to tell Flight how long to leave
    private int flightNum;//Flight number
    private String city;//Used to hold a city name.

    /**
     * Constructor for objects of class Flight, Also checks at beginning of simulation
     * if it is an already grounded flight to assign it a Gate.
     */
    public Flight(Operations operations,City ct,GUI gui,Stats st,int flightNum,int landTime,int runwayTime,int gateTime,int takeOffTime, boolean isGrounded)
    {
        this.operations = operations;
        this.ct = ct;
        this.gui = gui;
        this.st = st;
        this.flightNum = flightNum;
        this.isGrounded = isGrounded;
        this.landTime = landTime;
        this.runwayTime = runwayTime;
        this.gateTime = gateTime;
        this.takeOffTime = takeOffTime;
        city = ct.genCity();
        if (isGrounded){
            gate = operations.getOpenGate();
            gate.setFlight(this);
        }
        else{
            gui.statusUpdate("Flight " + flightNum + " from " + city + " reqesting Runway for landing in " + landTime + " minutes.");
        }
    }

    public boolean isGrounded()
    {
        return isGrounded;
    }

    /**
     * Returns the Flight number.
     */
    public int getNum(){
        return flightNum;
    }

    /**
     * Used only in beginning of Simulation to establish all variables.
     */
    public void initVariables(){
        initTick = currentTick;
        landTime += currentTick;
        runwayTime += landTime;
        gateTime += runwayTime;
        takeOffTime += gateTime;
    }

    /**
     * Method is used to update all ticktimes for each flight phase based
     * on the delays the flight receives
     * 
     * Phase 1: Flight is delayed trying to land on runway
     * Phase 2: Flight is delayed trying to station at gate
     * Phase 3: Flight is delayed trying to takeoff on runway
     */
    public void updateDelayedFlightVariables(int flightPhase){
        switch(flightPhase) {
            case 1:
            landTime += 10;
            runwayTime += 10;
            gateTime += 10;
            takeOffTime += 10;
            break;

            case 2: 
            runwayTime += 10;
            gateTime += 10;
            takeOffTime += 10;
            break;

            case 3: 
            gateTime += 10;
            takeOffTime += 10;
            break;
        }
    }

    /**
     * Makes Flight "TakeOff" Sets status to not on ground and removes it from
     * operations ArrayList of Flights. Also sends info to Stats to calculate
     * total Gate time for Flight.
     */
    public void takeOff(){
        int thisGateTime = gateTime - runwayTime; //Calculates total gate time for flight
        st.calcAvgGateTime(thisGateTime); //
        isGrounded = false;
        runway.unsetFlight();
        operations.removeFlight(this);
        city = ct.genCity();
        gui.statusUpdate("Flight "+ flightNum +" has left runway " + runway.getRunwayNumber() + " for " + city + ".");    
    }

    /**
     * Makes Flight "Land" Sets status to on ground and switches it from in air
     * to grounded Flight ArrayList. Also sets the Flight to a Runway.
     */
    public void land(){
        runway.setFlight(this);
        isGrounded = true;
        isAtRunway = true;
        gui.statusUpdate("Flight "+ flightNum +" has landed on runway " + runway.getRunwayNumber() + ".");
        operations.switchList(this);
    }

    /**
     * Makes Flight "Taxi to an open Gate" Sets taxing and at Runway to false.
     * Removes Flight from the runway and adds it to the Gate.
     */
    public void taxiToGate()
    {
        isTaxing = false;
        isAtRunway = false;
        runway.unsetFlight();
        gate.setFlight(this);
        gui.statusUpdate("Flight "+ flightNum +" has moved to Gate " + gate.getGateNumber() + " from runway " + runway.getRunwayNumber() + ".");
        st.calcPercGateUsed(operations.getTotalGates(), true);
    }

    /**
     * Makes Flight "Taxi to an open Runway" Sets the Flight to ready.
     * Removes Flight from the Gate and add it to the Runway.
     */
    public void taxiToRunway()
    {
        isReady = true;
        gate.unsetFlight();
        runway.setFlight(this);
        gui.statusUpdate("Flight "+ flightNum +" has moved to Runway " + runway.getRunwayNumber() + " from Gate " +gate.getGateNumber() +" to take off.");
        st.calcPercGateUsed(operations.getTotalGates(), false);
    }

    /**
     * Used in the FlightActor of Flight every time it is called it checks the
     * times for the flight to land,go to gate, go to runway, and takeoff. 
     * Sets the status of Flight as needed to FlightActor to make Flight Act.
     */
    public void update(){
        if(currentTick >= runwayTime && isAtRunway ){//&& runway != null){
            isTaxing = true;
        } else if(currentTick >= takeOffTime && isTaxing == false && gate != null){
            shouldDepart = true;
            takeOff = true;
        }else if(currentTick >= landTime){          
            shouldLand = true;
        }
    }

    /**
     * Calls update to check status of Flight. Based on status Flight will "Act"
     * Allows Flight to takeoff,land,taxi to gate, taxi to runway, and if it needs
     * to re-request anything during this time.
     */
    public void flightAct(){
        update();
        //If the plane is landed and ready to takeoff
        if(isReady)
        {
            takeOff();
        }
        else if(isTaxing && isAtRunway){
            gui.statusUpdate("Flight " + flightNum + " from " + city + " reqesting Gate.");
            gate = operations.getOpenGate();
            if(gate == null){
                gui.statusUpdate("Flight " + flightNum + " Taxing is delayed: no open Gates.");
                updateDelayedFlightVariables(2);
            }
            else{
                taxiToGate();
            }
        }
        else if(isGrounded){
            if(shouldDepart){
                gui.statusUpdate("Flight " + flightNum + " from " +city+ " requesting Runway for takeoff.");
                runway = operations.getOpenRunway();
                if(runway == null)
                {
                    gui.statusUpdate("Flight " + flightNum + " Takeoff is delayed: no open Runways.");
                    updateDelayedFlightVariables(3);
                }

                else if(takeOff){
                    taxiToRunway();
                }
            }
        }
        else{
            if(shouldLand){
                runway = operations.getOpenRunway();
                if(runway == null){
                    gui.statusUpdate("Flight " + flightNum + " landing is delayed: no open Runway.");
                    updateDelayedFlightVariables(1);
                    shouldLand = false;
                }else{
                    land();
                }
            }
        }
    }

    /**
     * This method allows the flight to react to conditions in specific
     * amount of "time"(tick) and decides what to do.
     */
    public void act(int tick){
        currentTick = tick;
        if(initTick != -1){
            flightAct();
        }else{
            initVariables();
            flightAct();
        }
    }
}
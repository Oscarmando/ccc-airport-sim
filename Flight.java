import java.util.Random;

public class Flight implements Actor
{
    private Random random = new Random();
    private Gate gate;
    private Operations operations;
    private City ct;
    private GUI gui;
    private Stats st;
    private Runway runway;
    private boolean isGrounded;
    private boolean isAtRunway = false;
    private boolean shouldLand = false;
    private boolean shouldDepart = false;
    private boolean isTaxing = false;
    private boolean isReady = false;
    private boolean takeOff = false;
    private int initTick = -1;
    private int currentTick;
    private int gateTime;
    private int landTime;
    private int runwayTime;
    private int num;
    private String city;

    /**
     * Constructor for objects of class Flight
     */
    public Flight(Operations operations,City ct, GUI gui, Stats st, int num, int landticks,int gateTime,int runwayTime,boolean isGrounded)
    {
        this.operations = operations;
        this.ct = ct;
        this.gui = gui;
        this.num = num;
        this.landTime = landticks;
        this.isGrounded = isGrounded;
        this.gateTime = gateTime;
        this.runwayTime = runwayTime;
        this.st = st;
        if (isGrounded){
            gate = operations.getOpenGate();
            gate.setPlane(this);
        }
    }

    public int getNum(){
        return num;
    }

    public void initVariables(){
        initTick = currentTick;
        landTime += currentTick;
        runwayTime += landTime;
        gateTime += runwayTime;
    }

    public void takeOff(){
        int thisGateTime = gateTime - runwayTime; //Calculates total gate time for flight
        st.calcAvgGateTime(thisGateTime); //
        isGrounded = false;
        runway.unsetPlane();
        operations.removeFlight(this);
        gui.statusUpdate("Flight "+ num +" has left runway " + runway.getRunwayNumber() + " for " + city + ".");    
    }

    public void land(){
        runway.setPlane(this);
        isGrounded = true;
        isAtRunway = true;
        gui.statusUpdate("Flight "+ num +" has landed on runway " + runway.getRunwayNumber() + ".");
        operations.switchList(this);
    }

    public void taxiToGate()
    {
        isTaxing = false;
        isAtRunway = false;
        runway.unsetPlane();
        gate.setPlane(this);
        gui.statusUpdate("Flight "+ num +" has moved to Gate " + gate.getGateNumber() + " from runway " + runway.getRunwayNumber() + ".");
    }

    public void taxiToRunway()
    {
        isReady = true;
        gate.unsetPlane();
        runway.setPlane(this);
        gui.statusUpdate("Flight "+ num +" has moved to Runway " + runway.getRunwayNumber() + " from Gate " +gate.getGateNumber() +" to take off.");
    }

    public void update(){
        if(currentTick >= runwayTime && isAtRunway ){//&& runway != null){
            isTaxing = true;
        } else if(currentTick >= gateTime && isTaxing == false && gate != null){
            city = ct.genCity();
            shouldDepart = true;
            takeOff = true;
        }else if(currentTick >= landTime){
            city = ct.genCity();            
            shouldLand = true;
        }
    }

    public void flightAct(){
        update();
        //If the plane is landed and ready to takeoff
        if(isReady)
        {
            takeOff();
        }
        else if(isTaxing && isAtRunway){
            gui.statusUpdate("Flight " + num + " from " + city + " reqesting Gate.");
            gate = operations.getOpenGate();
            if(gate == null){
                gui.statusUpdate("Flight " + num + " Taxing is delayed: no open Gates.");
                landTime += 10;
                gateTime = landTime + 10;
            }
            else{
                taxiToGate();
            }
        }
        else if(isGrounded){
            if(shouldDepart){
                gui.statusUpdate("Flight " +num+ " from " +city+ " requesting Runway for takeoff.");
                runway = operations.getOpenRunway();
                if(runway == null)
                {
                    gui.statusUpdate("Flight " + num + " Takeoff is delayed: no open Runways.");
                    landTime += 10;
                    runwayTime = landTime + 10;
                }

                else if(takeOff){
                    taxiToRunway();
                }
            }
        }
        else{
            if(shouldLand){
                gui.statusUpdate("Flight " + num + " from " + city + " reqesting Runway for landing.");
                runway = operations.getOpenRunway();
                if(runway == null){
                    gui.statusUpdate("Flight " + num + " landing is delayed: no open Runway.");
                    landTime += 10;
                    runwayTime = landTime + 10;
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
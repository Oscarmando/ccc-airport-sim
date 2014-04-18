import java.util.Random;

public class Flight implements Actor
{
    private Random random = new Random();
    private Gate gate;
    private Operations operations;
    private City ct;
    private GUI gui;
    private boolean isGrounded;
    private boolean shouldLand = false;
    private boolean shouldDepart = false;
    private boolean isPrepping = false;
    private int initTick = -1;
    private int currentTick;
    private int gateTime;
    private int landTime;
    private int num;
    private String city;

    /**
     * Constructor for objects of class Flight
     */
    public Flight(Operations operations,City ct, GUI gui, int num, int landticks,int gateTime,boolean isGrounded)
    {
        this.operations = operations;
        this.ct = ct;
        this.gui = gui;
        this.num = num;
        this.landTime = landticks;
        this.isGrounded = isGrounded;
        this.gateTime = 1;
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
        gateTime += landTime;
    }

    public void takeOff(){
        isGrounded = false;
        gate.unsetPlane();
        operations.removeFlight(this);
        gui.statusUpdate("Flight "+ num +" has left gate " + gate.getGateNumber() + " for " + city + ".");    
    }

    public void land(){
        gate.setPlane(this);
        isGrounded = true;
        isPrepping = true;
        gui.statusUpdate("Flight "+ num +" has landed at gate " + gate.getGateNumber() + ".");
        operations.switchList(this);
    }

    public void update(){
        if(currentTick >= gateTime){
            city = ct.genCity();
            shouldDepart = true;
        }else if(currentTick >= landTime){
            city = ct.genCity();            
            shouldLand = true;
        }
    }

    public void flightAct(){
        update();
        //If the plane is landed and ready to takeoff
        if(isGrounded){
            if(shouldDepart){
                takeOff();
            }
            //If the plane is in the air    
        }else{
            if(shouldLand){
                gui.statusUpdate("Flight " + num + " from " + city + " reqesting gate.");
                gate = operations.getOpenGate();
                if(gate == null){
                    gui.statusUpdate("Flight " + num + " landing is delayed: no gate.");
                    landTime += 10;
                    gateTime = landTime + 10;
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
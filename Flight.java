import java.util.Random;

public class Flight implements Actor
{
    private Random random = new Random();
    private Gate g;
    private Operations op;
    private GUI gui;

    private boolean isGrounded;
    private boolean shouldLand = false;
    private boolean shouldDepart = false;
    private boolean isPrepping = false;
    private int initTick = -1;
    private int currentTick;
    private int departureTime;
    private int landTime;
    private int num;

    private Gate gate;

    /**
     * Constructor for objects of class Flight
     */
    public Flight(Operations op, GUI gui, int num, int landticks)
    {
        this.op = op;
        this.gui = gui;
        this.num = num;
        this.landTime = landticks;
    }

    public int getNum(){
        return num;
    }

    public void initVariables(){
        initTick = currentTick;
        landTime += currentTick;
        departureTime = landTime + 30;
    }

    public void takeOff(){
        isGrounded = false;
        gate.unsetPlane();
        op.removeFlight(this);
        gui.statusUpdate("Flight "+ num +" has left gate " + gate.getGateNumber() + ".");    
    }

    public void land(){
        gate.setPlane(this);
        isGrounded = true;
        isPrepping = true;
        gui.statusUpdate("Flight "+ num +" has landed at gate " + gate.getGateNumber() + ".");
        op.switchList(this);
    }

    public void update(){
        if(currentTick >= departureTime){
            shouldDepart = true;
        }else if(currentTick >= landTime){
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
                gui.statusUpdate("Flight " + num + " reqesting gate.");
                gate = op.getOpenGate();
                if(gate == null){
                    gui.statusUpdate("Flight " + num + " landing delayed: no gate.");
                    landTime += 10;
                    departureTime = landTime + 30;
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
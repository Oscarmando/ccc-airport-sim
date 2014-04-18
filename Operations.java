import java.util.*;
public class Operations implements Actor
{
    Random rgen;// = new random();
    private  ArrayList<Actor> actors;
    private  ArrayList<Gate> gates;
    private FlightController fc;
    private  Stats st;
    private GUI gui;
    private City ct;
    private Simulator sim;
    private ArrayList<Flight> inbound;  //These are the flights that are on their way to our airport
    private ArrayList<Flight> grounded;  //These are the flights that are landed at our airport
    
    private static final double IN_BOUND_FLIGHT_PROBABILITY = .2;//.2
    private static final double GROUNDED_FLIGHT_PROBABILITY = .6;//.6
    private static final int AMOUNT_INITIAL_GATES = 100;

    public Operations(Simulator sim) 
    {
        this.sim = sim;
        rgen = new Random();
        ct = new City();
        st = new Stats(this);
        fc = new FlightController(this,sim);
        gui = new GUI(fc);
        actors = new ArrayList<Actor>();
        gates = new ArrayList<Gate>();
        inbound = new ArrayList<Flight>();
        grounded = new ArrayList<Flight>();
        actors.add(st);
        actors.add(gui);
        actors.add(this);
        createGates(AMOUNT_INITIAL_GATES);
        groundedFlights();
    }

    private void addGate(Gate g){
        gates.add(g);
    }

    public ArrayList<Actor> getActors()
    {
        return actors;
    }

    /**
     *method to get the first open gate we can find.
     */
    public Gate getOpenGate(){
        for(Gate g: gates){
            if(g.getGateAvailability()){ return g; }
        }
        return null;
    }

    private void inBoundFlights(int num){
        for(int i=0;i<num;i++){
            makeInBoundFlight();
        }
    }

    private void groundedFlights(){
        for(int i=0; i < gates.size(); i++){
            if(rgen.nextDouble() <= (GROUNDED_FLIGHT_PROBABILITY))
                makeGroundedFlight();
        }
    }

    private void createGates(int num){
        for(int i=1;i<=num;i++){
            Gate g = new Gate(i);
            gates.add(g);
        }
    }

    private void makeInBoundFlight(){
        Flight f = new Flight(this,ct, gui, rgen.nextInt(9999), rgen.nextInt(15) + 1, rgen.nextInt(350) + 11, false);
        actors.add(f);
        inbound.add(f);
    }

    private void makeGroundedFlight(){
        Flight f = new Flight(this,ct, gui, rgen.nextInt(9999), rgen.nextInt(15) + 1, rgen.nextInt(360) + 1, true);
        actors.add(f);
        grounded.add(f);
    }

    /**
     *  This switches the flight from the arraylist of incoming flights, to the 
     *  arraylist of grounded flights.
     */
    public void switchList(Flight f) 
    {
        inbound.remove(f);
        grounded.add(f);
    }

    /**
     *  This removes fligths from the grounded list once they takeoff
     */
    public void removeFlight(Flight f)
    {
        actors.remove(f);
        grounded.remove(f);
    }


    public void act(int tick)
    {
        if (rgen.nextDouble() <= IN_BOUND_FLIGHT_PROBABILITY){
            inBoundFlights(rgen.nextInt(2) + 1);
        }
    }
}

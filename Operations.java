import java.util.*;
public class Operations implements Actor
{
    Random rgen;
    private  ArrayList<Actor> actors;
    private  ArrayList<Gate> gates;
    private FlightController fc;
    private  Stats st;
    private GUI gui;
    private ArrayList<Flight> inbound;  //These are the flights that are on their way to our airport
    private ArrayList<Flight> grounded;  //These are the flights that are landed at our airport
    private static final double IN_BOUND_FLIGHT_PROBABILITY = 1;
    private static final double GROUNDED_FLIGHT_PROBABILITY = .08;

    public Operations() 
    {
        rgen = new Random();
        int numOfPlanes = rgen.nextInt(2) + 3;
        st = new Stats(this);
        gui = new GUI(fc);
        fc = new FlightController(this);
        actors = new ArrayList<Actor>();
        gates = new ArrayList<Gate>();
        inbound = new ArrayList<Flight>();
        grounded = new ArrayList<Flight>();
        actors.add(st);
        actors.add(gui);
        actors.add(this);
        createGates(100);
        inBoundFlights(numOfPlanes); 
        groundedFlights(numOfPlanes);
    }

    public void sendStatInfo()
    {
        st.recieveInfo();
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
            Gate gate = getOpenGate();
        }
        listFlights(true);
    }

    private void groundedFlights(int num){
        for(int i=0;i<num;i++){
            makeGroundedFlight();
            Gate gate = getOpenGate();
        }
        listFlights(false);
    }

    private void createGates(int num){
        for(int i=1;i<=num;i++){
            Gate g = new Gate(i);
            gates.add(g);
        }
    }

    private void makeInBoundFlight(){
        Flight f = new Flight(this, gui, rgen.nextInt(9999), rgen.nextInt(15) + 1, rgen.nextInt(40) +20, false);
        actors.add(f);
        st.newFlight(f);
        inbound.add(f);
    }

    private void makeGroundedFlight(){
        Flight f = new Flight(this, gui, rgen.nextInt(9999), rgen.nextInt(15) + 1, rgen.nextInt(40) +20, true);
        actors.add(f);
        st.newFlight(f);
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

    public void listFlights(boolean f){
        String status = "Flights: ";
        if(f){
            for(Flight g: inbound){
                status += g.getNum() + " ";
            }
            gui.statusUpdate(status);
        }
        else {
            for(Flight g: grounded){
                status += g.getNum() + " ";
            }
            gui.statusUpdate(status);
        }
    }

    public void act(int tick)
    {
        if (rgen.nextDouble() <= IN_BOUND_FLIGHT_PROBABILITY){
            inBoundFlights(rgen.nextInt(5));
        }
    }
}

import java.util.*;
public class Operations
{
    Random rgen;
    private  ArrayList<Actor> actors;
    private  ArrayList<Gate> gates;
    private  Stats st;
    private GUI gui;

    private ArrayList<Flight> inbound;  //These are the flights that are on their way to our airport
    private ArrayList<Flight> grounded;  //These are the flights that are landed at our airport

    public Operations()
    {
        rgen = new Random();
        int numOfPlanes = rgen.nextInt(2) + 3;
        
        st = new Stats(this);
        gui = new GUI(this);
        actors = new ArrayList<Actor>();
        gates = new ArrayList<Gate>();
        inbound = new ArrayList<Flight>();
        grounded = new ArrayList<Flight>();
        actors.add(st);
        actors.add(gui);
        createGates(2);
        inBoundFlights(numOfPlanes); 
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
        listFlights();
    }

    private void createGates(int num){
        for(int i=1;i<=num;i++){
            Gate g = new Gate(i);
            gates.add(g);
        }
    }

    private void makeInBoundFlight(){
        Flight f = new Flight(this, rgen.nextInt(1000), rgen.nextInt(15) + 1);
        actors.add(f);
        st.newFlight(f);
        inbound.add(f);
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
        System.out.println("Removed:" + f.getNum());
        actors.remove(f);
        grounded.remove(f);
    }
    
    public void listFlights(){
        System.out.print("Flights: ");
        for(Flight g: inbound){
            System.out.print(g.getNum() + " ");
        }
        System.out.println();
    }
}

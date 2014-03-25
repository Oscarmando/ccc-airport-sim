import java.util.*;
public class Operations
{
    private static ArrayList<Actor> actors;
    private static ArrayList<Gate> gates;
    private static ArrayList<Plane> planes;
    private static Stats st;

    public Operations()
    {
        st = new Stats();
        actors = new ArrayList<Actor>();
        gates = new ArrayList<Gate>();
        planes = new ArrayList<Plane>();
        createGates(1);
        createIdlePlanes(1); 
        makeFlight();
    }

    static Stats getStats()
    {
        return st;
    }

    static void addGate(Gate g){
        gates.add(g);
    }

    static ArrayList<Actor> getActors()
    {
        return actors;
    }

    static ArrayList<Plane> getPlanes()
    {
        return planes;
    }

    /**
     * method for getting a plane that is prepped and ready.
     */
    static Plane getIdlePlane(){
        for(Plane p: planes){
            if(p.isIdle()){ return p; }
        }
        return null;
    }

    /**
     *method to get the first open gate we can find.
     */
    static Gate getOpenGate(){
        for(Gate g: gates){
            if(g.getGateAvailability()){ return g; }
        }
        return null;
    }

    private void createIdlePlanes(int num){
        for(int i=0;i<num;i++){
            Plane plane = new Plane("plane"+i, "ccc-airport");
            actors.add(plane);
            Gate gate = Operations.getOpenGate();
            //gate.park(plane);
            //st.PlaneGateAttached(gate);
        }
    }

    private void createGates(int num){
        for(int i=1;i<=num;i++){
            Gate g = new Gate(i);
            //st.gateCreated(g);
            gates.add(g);
        }
    }

    static void makeFlight(){
        Flight f = new Flight(getIdlePlane());
        actors.add(f);
        st.newFlight(f);
    }

    static void makeInBound(){
        //Flight f = new Flight();
    }
}

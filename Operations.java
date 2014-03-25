import java.util.*;
public class Operations
{
    private  ArrayList<Actor> actors;
    private  ArrayList<Gate> gates;
    private  ArrayList<Plane> planes;
    private  Stats st;

    public Operations()
    {
        st = new Stats(this);
        actors = new ArrayList<Actor>();
        gates = new ArrayList<Gate>();
        planes = new ArrayList<Plane>();
        actors.add(st);
        createGates(1);
        createIdlePlanes(1); 
        makeFlight();
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

    public ArrayList<Plane> getPlanes()
    {
        return planes;
    }

    /**
     * method for getting a plane that is prepped and ready.
     */
    private Plane getIdlePlane(){
        for(Plane p: planes){
            if(p.isIdle()){ return p; }
        }
        return null;
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

    private void createIdlePlanes(int num){
        for(int i=0;i<num;i++){
            Plane plane = new Plane("plane"+i, "ccc-airport");
            planes.add(plane);
            Gate gate = getOpenGate();
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

    private void makeFlight(){
        Flight f = new Flight(getIdlePlane(), this);
        actors.add(f);
        st.newFlight(f);
    }

    static void makeInBound(){
        //Flight f = new Flight();
    }
}

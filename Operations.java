import java.util.*;
public class Operations
{
    private  ArrayList<Actor> actors;
    private  ArrayList<Gate> gates;
    private  Stats st;

    public Operations()
    {
        st = new Stats(this);
        actors = new ArrayList<Actor>();
        gates = new ArrayList<Gate>();
        actors.add(st);
        createGates(2);
        outBoundFlights(2); 
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

    private void outBoundFlights(int num){
        for(int i=0;i<num;i++){
            makeOutBoundFlight();
            Gate gate = getOpenGate();
        }
    }

    private void createGates(int num){
        for(int i=1;i<=num;i++){
            Gate g = new Gate(i);
            gates.add(g);
        }
    }

    private void makeOutBoundFlight(){
        Flight f = new Flight(this);
        actors.add(f);
        st.newFlight(f);
    }

       /**
     * Will create planes going into the airport.
     */
    private void makeInBoundFlight(){
        
    }
}

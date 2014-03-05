import java.util.*;
public class Simulator
{
    final int tickSpeed = 300;//In milliseconds
    static int ticklimit = 1000;

    public int time;
    Random rand;

    //ArrayList<Flight> flights;
    ArrayList<Actor> actors;
    ArrayList<Gate> gates;

    Gate gate;
    Stats st;
    public Simulator(){
        System.out.print('\u000C'); //Clears console

        gates = new ArrayList<Gate>();
        actors = new ArrayList<Actor>();
        time = 0;
        rand = new Random();
        st = new Stats();

        gates.add(new Gate());

        actors.add(st);
        //actors.add(gate);

        makeFlight();
        tickLoop();
    }

    //test method
    public void makeFlight(){
        Flight f = new Flight(this, st);
        actors.add(f);
        st.newFlight(f);
    }

    public Gate needGate(Flight f){
        for(Gate gate: gates){
            if(gate.getGateAvailability()){
                return gate;
            }
            //keep flying ;)

        }
        return null;
    }

    public void tickLoop(){
        while(true){
            tick();
            try{
                Thread.sleep(tickSpeed);
            }catch(Exception e){
                System.out.println("Oops!");
            }

            for(Actor a: actors){
                a.act(time);
            }
        }
    }

    private void tick(){
        if(time > ticklimit){
            time = 1;
        }
        time++;
        System.out.println(time);
    }

    public boolean shouldCreate(){
        int t = rand.nextInt(10);
        switch(t){
            case 2: case 6: case 9:
            return true;

            default:
            return false;
        }
    }

}

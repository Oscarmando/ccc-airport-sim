import java.util.*;
public class Simulator
{
    final int tickSpeed = 300;//In milliseconds
    static int ticklimit = 1000;

    public int time;
    Random rand;

    //ArrayList<Flight> flights;
    //ArrayList<Actor> actors;
    //ArrayList<Gate> gates;
    Operations op;
    Gate gate;
    Stats st;
    Plane plane;
    public Simulator(){
        time = 0;
        System.out.print('\u000C'); //Clears console
        op = new Operations();
        //gates = new ArrayList<Gate>();
        //actors = new ArrayList<Actor>();
        rand = new Random();
      
        Operations.setStats(st);

        tickLoop();
    }


    public void tickLoop(){
        while(true){
            tick();
            try{
                Thread.sleep(tickSpeed);
            }catch(Exception e){
                System.out.println("Oops!");
            }
            
            //if(shouldCreate()){
            //    makeInBound();
            //}
            
            //if(shouldCreate()){
            //    makeOutBound();
            //}

            for(Actor a: op.getActors()){
                a.act(time);
            }
        }
    }
    
    private void makeInBound(){
        
    }
    
    private void makeOutBound(){
        //Flight f = new Flight();
        //Plane pl = Operations.getIdlePlane();
        //f.attachPlane(pl);
        //actors.add(f);
        //st.
    }

    private void tick(){
        if(time > ticklimit){
            time = 1;
        }
        time++;
        System.out.println(time);
    }

    /*public boolean shouldCreate(){
        int t = rand.nextInt(15);
        switch(t){
            case 2: case 9: case 14:
            return true;

            default:
            return false;
        }
    }
    */
}

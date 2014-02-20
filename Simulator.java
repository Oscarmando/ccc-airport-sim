import java.util.*;
public class Simulator
{
    final int tickSpeed = 1;
    static int ticklimit = 1000;
    
    public int time;
    Random rand;
    
    //ArrayList<Flight> flights;
    ArrayList<Actor> actors;
    
    Gate gate;
    Statistics st;
    public Simulator(){
        time = 1;
        rand = new Random();
        gate = new Gate();
        st = new Statistics();
        tickLoop();
    }
    
    
    public void tickLoop(){
        while(true){
            tick();
            try{
                Thread.sleep(tickSpeed*1000);
            }catch(Exception e){
                System.out.println("Oops!");
            }
        
            if(shouldCreate()){
                Flight f = new Flight();
                gate.flight(f);
            }
        
            for(Actor a: actors){
                a.act(time);
            }
        }
    }
    
    private void tick(){
        if(time++ > ticklimit){
            time = 1;
        }
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

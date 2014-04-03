import java.util.*;
public class Simulator
{
    final int tickSpeed = 300;//In milliseconds
    static int ticklimit = 1000;

    public int time;
    Random rand;
    Operations op;
    public Simulator(){
        time = 0;
        System.out.print('\u000C'); //Clears console
        op = new Operations();
        rand = new Random();
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
            for(Actor a: op.getActors()){
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
}

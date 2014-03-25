import java.util.Random;
/**
 * Write a description of class Plane here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Plane implements Actor
{
   private String planeNumber;
   private int numPassengers;
   private String airline;
   private int prepTime;
   private boolean idle;
   
   public Plane(String planeNumber, String airline)
   {
      this.planeNumber = planeNumber;
      this.airline = airline;
      prepTime = 10;
      idle = true;
   }
   
   public int getPrepTime()
   {
       return prepTime;
   }
   
   
   public void act(int tick)
   {
   }
   
   public boolean isIdle(){
       return idle;
   }
       
}

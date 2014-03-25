import java.util.Random;
/**
 * Intended to be the actual plane that is "carried" by the flight.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Plane
{
   private int planeNumber;
   private int numPassengers;
   private String airline;
   private int prepTime;
   
   public Plane(int planeNumber, String airline)
   {
      this.planeNumber = planeNumber;
      this.airline = airline;
      prepTime = 10;
   }
   
   public int getPrepTime()
   {
       return prepTime;
   }
   
   public void act(int tick)
   {
       
       
       
       
   }
       
}

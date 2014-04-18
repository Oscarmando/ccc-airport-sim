import java.util.*;
public class City
{
    Random rgen = new Random();
    private String cities[];
    

    public City() 
    {
        this.cities = new String[] {"Chicago", "Houston", "Philadelphia", "New York"};
    }

    public String genCity()
    {
        return cities[rgen.nextInt(cities.length)];
    }
    
    public void act(int tick)
    {
    }

}

Our idea goes like this so far.

![http://s1.postimg.org/ow62sp65r/image.png](http://s1.postimg.org/ow62sp65r/image.png)

Simulator will periodically call the tick method of classes that extend
Dispatch. That is when you need to process your events. Whenever there is a random event your message method will be called.

Here is an example of what you class might look like.
```

....
public void tick(){
System.out.println("<-----Events since last tick----->");
Iterator<Event> ev = events.iterator();
while(ev.hasNext()){
Event e = ev.next();
System.out.println("Test2 got a event from " + e.getAirportName());
System.out.println("\t"+e.getName() + " - " + e.getDec());
ev.remove();
}
}

public void message(Event e){
//Check to see if this event effects me.
//If so add to my "event to be processed"
//or if not just ignore it.
events.add(e);
}
....
```
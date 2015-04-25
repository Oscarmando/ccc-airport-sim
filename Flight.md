We want to have a boolean in this class to state whether its landed or not.
The boolean can be altered by a random number generator to choose if something like bad weather is effecting its final landing time or not.

We need an initial landing time and also an actual landing time. The actual will be altered if the random generator states that something like bad weather happened.

We'll need to determine the time of takeoff and where its going/coming from.

CRC: Feel free to add or fix anything for this!

Flight: () = Variable Name
-Carries passengers to a gate. (arrive)
-Arrives at a certain time. (arrivalTime)
-Can be delayed from arriving. (delay)
-Can leave a gate. (depart)
-Can have seat restrictions. (maxCapacity)
-Flight number. (flightNum)

Responsibilities:
-Land in a gate.
-Keep track of when to be at a gate.
-Respond to possible delays.
-Prepare for flights.
-Let out/board passengers.

Collaboration: Gate, and Statistics.
# Voting_Booth_Simulation

Project to simulate lines of a voting booth
This sort of project could be used by a company to discover bottlenecks in lines, assembly processes, etc.

Run the file VoterSimulation.java to run the simulation

Fixed inputs:
  2 registration desks:
    One for names A-M
    One for names N-Z
  4 types of voters:
    "Regular" voters
    "Limited time" voters (votes quickly, leave if delay is too long)
    "Special needs" voters (votes slowly)
    "Super special needs" voters (votes VERY slowly)
      (My professor coined the names for the voters, not me...)

Variable inputs:
  Total simulation time (simulation runs for this xxx seconds)
  Interval time for the simulation (simulation displays output every xxx seconds)
  Number of voting booths
  Average amount of time between voters' arrivals
  Average amount of time taken during check-in
  Average amount of time taken in voting booth
  Average amount of time a voter is willing to wait before leaving
  Time constant for rate of simulation display
  
Numerical outputs:
  Time elapsed in simulation
  Throughput (number of voters that made it through, and max that theoretically could have in that time)
  Current number of voters still in voting process
  Number of people who got tired of waiting and left
  Various other data
  
Visual outputs:
  GUI representation of voters progressing through the lines in the simulation
  
  

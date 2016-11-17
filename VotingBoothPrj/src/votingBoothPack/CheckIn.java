package votingBoothPack;

import java.util.ArrayList;
/**********************************************************************
 * Check In class is the front end of the program. Voter is added to
 * a Q depending on name. Voter is removed from Q, and after certain
 * checkIn time, is allowed to pass on, allowing next voter to check
 * In.
 * 
 * @author Tony Bober
 * @version 7/13/2016
 *********************************************************************/
public class CheckIn implements ClockListener {

	/** ArrayList of voters who are waiting to checkIn. */
	private ArrayList<Voter> checkInQ = new ArrayList<Voter>();
	
	/** Person that is added from Q to checkIn. */
	private Voter person;
	
	/** Allows user to be added to booth. */
	private Booth booth;
	
	/** Time when next event occurs. */
	private int timeOfNextEvent;

	/** Number of people who left the checkIn line. */
	private int numLeft = 0;
	
	/** Maximum size of CheckIn line. */
	private int maxCheckInQLength = 0;
	
	/******************************************************************
	 * Constructor: timeOfNext event goes to 0, instantiated booth
	 * is set to booth for previous class.
	 * 
	 * @param booth, booth from previous class, which will be added to
	 * booth class
	 *****************************************************************/
	public CheckIn(Booth booth){
		timeOfNextEvent = 0;
		this.booth = booth;
	}
	
	/******************************************************************
	 * add: Voter is added to to checkInQ.
	 * 
	 * @param person, the voter added to the Q
	 *****************************************************************/
	public void add(Voter person){
		checkInQ.add(person);
		
		// Records maximum length reached by the checkIn Q
				if (checkInQ.size() > maxCheckInQLength)
					maxCheckInQLength = checkInQ.size();
	}
	
	/******************************************************************
	 * event: Required function that runs a series of checkIn events
	 * every time of next event.
	 * 
	 * @param tick, the current tick
	 *****************************************************************/
	public void event(int tick){
		if (tick >= timeOfNextEvent) {
			
			// Looks at each voter in the Q - if any of them reach
			// their leave time, they're removed, numLeft is incrementd
			for(int j = 0; j < checkInQ.size(); j++)
			{
				if(tick - checkInQ.get(j).getTickTime() >= 
						checkInQ.get(j).getLeaveTime())
				{
					checkInQ.remove(j);
					numLeft++;
				}
			}
			
			if(person != null)
			{
				if(tick >= person.getCheckInTime() + 
						person.getTickTime())
				{
					person.setTickTime(tick);
					booth.add(person);
					person = null;
				}
			}
			
		    if (checkInQ.size() >= 1 && person == null) {
				person = checkInQ.remove(0);
				timeOfNextEvent = tick + (int) 
						(person.getCheckInTime() + 1);
				person.setTickTime(tick);
			}
		}
	}
	
	/******************************************************************
	 * getMaxCheckInQ: returns the highest checkIn length.
	 * 
	 * @return maxCheckInQLength
	 *****************************************************************/
	public int getMaxCheckInQ()
	{
		return maxCheckInQLength;
	}
	
	/******************************************************************
	 * getNumLeft: returns number of people that left the line.
	 * 
	 * @return getNumLeft
	 *****************************************************************/
	public int getNumLeft(){
		return numLeft;
	}
	
	/******************************************************************
	 * getCheckInQ: returns ArrayListof voters in the Q.
	 * 
	 * @return checkInQ
	 *****************************************************************/
	public ArrayList<Voter> getCheckInQ() {
		return checkInQ;
	}
	
	/******************************************************************
	 * getPersonAtCheckIn: The person who is at the checkIn counter.
	 * 
	 * @return person
	 *****************************************************************/
	public Voter getPersonAtCheckIn(){
		return person;
	}
	
	/******************************************************************
	 * getInProgressCount: Get the number of people currently in line 
	 * or at checkIn counter.
	 * 
	 * @return checkInQ size + person at counter
	 *****************************************************************/
	public int getInProgressCount(){
		if(person != null)
			return checkInQ.size() + 1;
		else
			return checkInQ.size();
	}
}


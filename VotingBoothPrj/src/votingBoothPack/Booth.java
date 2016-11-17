package votingBoothPack;

import java.util.ArrayList;
/**********************************************************************
 * Booth: Implements ClockListener and adds the required event
 * function. Receives each voter and places them in the Q, then
 * proceeds to determine if a booth is available, otherwise leaves
 * that voter in the Q. boothsOcc is the defacto booth array.
 * 
 * @author Tony Bober
 * @version 7/13/2016
 *********************************************************************/
public class Booth implements ClockListener {
	/** ArrayList of voters waiting for booth to open. */
	private ArrayList<Voter> Q = new ArrayList<Voter>();
	
	/** ArrayList of booths, occupied or unoccupied. */
	private ArrayList<Voter> boothsOcc = new ArrayList<Voter>();

	/** Maximum length of Q. */
	private int maxQlength = 0;
	
	/** Current person being pulled from Q. */
	private Voter person; 
	
	/** Number of people who finished the process. */
	private int completed = 0;
	
	/**Number of people who left the Q. */
	private int numLeft = 0;
	
	/** Number of booths as per the User */
	private int numBooths;
	
	/** Average time it takes for user to complete the process. */
	private int avgProcessTime = 0;

	/******************************************************************
	 * Constructor: Adds empty booths to boothsOcc array based on user 
	 * number of booths.
	 * 
	 * @param numBooths, number of user-inputted booths
	 *****************************************************************/
	public Booth(int numBooths)
	{
		this.numBooths = numBooths;
			for(int i = 0; i < numBooths; i++){
			boothsOcc.add(null);
		}
	}

	/******************************************************************
	 * add: Adds the voter from the previous station to the Q of the
	 * voting booths.
	 * 
	 * @param person, voter from previous station
	 *****************************************************************/
	public void add (Voter person)
	{
		Q.add(person);

		// Records maximum length reached by the booth Q
		if (Q.size() > maxQlength)
			maxQlength = Q.size();
	}

	/******************************************************************
	 * event: required method from ClockListener that runs a series
	 * of booth events each "second". Checks for open booth and either
	 * removes or adds voter to the boothsOcc arrayList.
	 * 
	 * @param tick, current time of simulation
	 *****************************************************************/
	public void event (int tick){
		
		// Looks at each voter in the Q - if any of them reach
		// their leave time, they're removed, numLeft is incremented.
		for(int j = 0; j < Q.size(); j++)
		{
			//determine if user wants to leave
		   if(tick - Q.get(j).getTickTime() >= Q.get(j).getLeaveTime())
			{
				Q.remove(j);
				numLeft++;
			}
		}
		
		// Looks at each booth - if it's beyond votingBoothTime for the
		// voter in that booth, that booth just became vacant.  
		// Increment completed, add a new person, and set the new time
		// that the booth will be vacant
		for (int i = 0; i < numBooths; i++){
			
			if(boothsOcc.get(i) != null)
			{
				//if user voting time is up, remove them
				if(tick >= boothsOcc.get(i).getVotingBoothTime() + 
						boothsOcc.get(i).getTickTime())
				{
					completed++;
					setAvgProcessTime(tick, boothsOcc.get(i));
					boothsOcc.set(i, null);
				}
			}
			
			//else add voter if booth is empty and Q is not
			if (Q.size() >= 1 && boothsOcc.get(i) == null) {
				person = Q.remove(0);
				boothsOcc.set(i, person);
				boothsOcc.get(i).setTickTime(tick);									
			}
		}
	}

	/******************************************************************
	 * getmaxLength: return biggest size of Q.
	 * 
	 * @return maximum Q length
	 *****************************************************************/
	public int getMaxQlength() {
		return maxQlength;
	}

	/******************************************************************
	 * getThroughPut: return number of completed voters.
	 * 
	 * @return completed, number of voters finished
	 *****************************************************************/
	public int getThroughPut() {
		return completed;
	}
	
	/******************************************************************
	 * getNumLeft: Get number of people who left line.
	 * 
	 * @return numLeft, number of people that left
	 *****************************************************************/
	public int getNumLeft(){
		return numLeft;
	}
	
	/******************************************************************
	 * getQ: Get the Q of waiting voters.
	 * 
	 * @return Q, voting queue
	 *****************************************************************/
	public ArrayList<Voter> getQ() {
		return Q;
	}
	
	/******************************************************************
	 * getPeopleAtBooths: return Voting Booths.
	 * 
	 * @return boothsOcc, all voting booths
	 *****************************************************************/
	public ArrayList<Voter> getPeopleAtBooths(){
		return boothsOcc;
	}
	
	/******************************************************************
	 * setBooths: If user changes number of booths, they will be added
	 * or removed accordingly. Cannot removed booths in use.
	 * 
	 * @param setBooths, new number of booths
	 * @return actual number of new booths
	 *****************************************************************/
	public int setBooths(int setBooths){
		if(this.numBooths <= setBooths){
			for(int i = numBooths; i < setBooths; i++)
			{
				boothsOcc.add(i, null);
				this.numBooths = setBooths;
			}
		}
		else
		{
			//if user wants less booths
			if(setBooths < this.numBooths){
				for(int i = numBooths - 1; i >= setBooths; i--)
				{
					if(setBooths < 0)
						break;
					//remove if null
					if(boothsOcc.get(i) == null)
						boothsOcc.remove(i);
					//try next booth
					else if(boothsOcc.get(i) != null)
						setBooths --;
				}
				this.numBooths = boothsOcc.size();
			}
		}
		return numBooths;
	}
	
	/******************************************************************
	 * setAvgProcessTime: set the variable value for processTime.
	 * 
	 * @param finalTick, last tick when user leaves
	 * @param pers, the person who is leaving
	 *****************************************************************/
	private void setAvgProcessTime(int finalTick, Voter pers){
		int pT = finalTick - pers.getEnterTick();
		avgProcessTime += pT;
	}
	
	/******************************************************************
	 * getProcessTime: returns the average time of the voting process.
	 * 
	 * @return avgProcesstime/completed
	 *****************************************************************/
	public int getProcessTime(){
		if(completed != 0)
			return (int)(avgProcessTime/completed);
		else
			return 0;
	}
	
	/******************************************************************
	 * getInProcessCount: Determine number of voters in progress for
	 * booth.
	 * 
	 * @return Q size + occupied booths
	 *****************************************************************/
	public int getInProcessCount(){
		int count = 0;
		for(int i = 0; i < numBooths; i++)
		{
			if(boothsOcc.get(i) != null)
				count ++;
		}
		
		return Q.size() + count;
	}
}

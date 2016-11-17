package votingBoothPack;


/**********************************************************************
 * Template class for all voters.
 * 
 * @author   Tony Bober
 * @version 7/13/2016
 *********************************************************************/
public abstract class Voter {
	
	/** curentTickTime of Voter. */
	private int tickTime;
	
	/** Tick when Voter arrives. */
	private int enterTick;
	
	/** Time Voter will spend in booth. */
	protected double votingBoothTime;
	
	/** Time when voter will leave Q */
	protected int leaveTime;
	
	/** Time it takes for Voter to check in. */
	protected double checkInTime;

	/** Abstract classes required for each sub Voter */
	public abstract void setCheckInTime(double checkInTime);
	public abstract void setLeaveTime(int leaveTime);
	public abstract void setVotingBoothTime(double votingBoothTime);
	public abstract int getVoterType();
	
	/******************************************************************
	 * Get the check in time of each voter.
	 * 
	 * @return checkInTime
	 *****************************************************************/
	public double getCheckInTime(){
		return checkInTime;
	}
	
	/******************************************************************
	 * Get the leave time of each voter.
	 * 
	 * @return leaveTime
	 *****************************************************************/
	public int getLeaveTime(){
		return leaveTime;
	}
	
	/******************************************************************
	 * Get voting time of each Voter
	 * 
	 * @return votingBoothTime
	 *****************************************************************/
	public double getVotingBoothTime(){
		return votingBoothTime;
	}
	
	/******************************************************************
	 * The tick time of the voter at any given time.
	 * 
	 * @return tickTime
	 *****************************************************************/
	public int getTickTime() {
		return tickTime;
	}

	/*****************************************************************
	 * Set any tick time for the voter.
	 * 
	 * @param tickTime
	 *****************************************************************/
	public void setTickTime(int tickTime) {
		this.tickTime = tickTime;
	}
	
	/******************************************************************
	 * Get the enter tick time for the voter.
	 * 
	 * @return
	 *****************************************************************/
	public int getEnterTick(){
		return enterTick;
	}
	
	/******************************************************************
	 * Set the enter tick time for any voter.
	 * @param tick
	 *****************************************************************/
	public void setEnterTick(int tick){
		this.enterTick = tick;
	}
}
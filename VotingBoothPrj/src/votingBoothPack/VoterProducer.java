
package votingBoothPack;

import java.util.Random;

/**********************************************************************
 * VoterProducer creates and sets the characteristics for each voter
 * based upon random statistics.
 * 
 * @author Tony Bober
 * @version 7/13/2016
 *********************************************************************/
public class VoterProducer implements ClockListener {

	/** Arrival of next person */
	private int nextPerson = 0;
	/** CheckIn Class A-L. */
	private CheckIn A_L;
	/** CheckIn class M_Z */
	private CheckIn M_Z;
	/** Tick until next person arrives. */
	private int numOfTicksNextPerson;
	
	/** Time for Voter to check In. */
	private int checkInTime;
	/** Time for Voter to leave Q. */
	private int leaveTime;
	/** Time for Voter to vote in  booth. */
	private int votingBoothTime;
	
	/** Number of SSN voters. */
	private int numSSN = 0;
	/** Number of SN voters. */
	private int numSN = 0;
	/** Number of LT voters. */
	private int numLT = 0;
	/** Number of R voters. */
	private int numR = 0;

	/** Random number for gaussian distribution. */
	private Random r = new Random();

	/******************************************************************
	 * Constructor: sets instance variables to those created in the
	 * simulation.
	 * 
	 * @param A_L, checkIn class for A_L
	 * @param M_Z, checkIn class for M_Z
	 * @param numOfTicksNextPerson, ticks until next voter
	 * @param checkInTime, time for checkIn
	 * @param leaveTime, time until leaving Q
	 * @param votingBoothTime, time for voting
	 *****************************************************************/
	public VoterProducer(CheckIn A_L, CheckIn M_Z, int 
			numOfTicksNextPerson, int checkInTime, int leaveTime, 
			int votingBoothTime) {

		this.A_L = A_L;
		this.M_Z = M_Z;
		this.numOfTicksNextPerson = numOfTicksNextPerson;
		this.checkInTime = checkInTime;
		this.leaveTime = leaveTime;
		this.votingBoothTime = votingBoothTime;
		// r.setSeed(13); // This will cause the same random numbers
	}

	/******************************************************************
	 * event: Creates a new voter based on Gaussian distributions.
	 * 
	 * @param tick, seconds
	 *****************************************************************/
	public void event(int tick) {
		
		// Sets next person to come at a semi-random time based on the
		// numOfTicksNextPerson parameter passed into the constructor
		if (nextPerson <= tick) {
			nextPerson = tick + (int)(numOfTicksNextPerson * 0.5 * 
					r.nextGaussian() + numOfTicksNextPerson + .5);

			SuperSpecialNeedsVoter ssnVoter = 
					new SuperSpecialNeedsVoter();
			SpecialNeedsVoter snVoter = new SpecialNeedsVoter();
			LimitedTimeVoter ltVoter = new LimitedTimeVoter();
			RegularVoter rVoter = new RegularVoter();

			// 3% are super special needs voters
			// 7% are special Needs voters
			// 20% are limited Time voters
			// 70% are regular voters

			// Random number from 0 to 99
			int rNumber = (int) (Math.random() * 100);
			int rNumber2 = (int) (Math.random() * 100);

			// 3% super special needs voters
			if(rNumber <= 3) {
				ssnVoter.setCheckInTime(checkInTime * 0.1 * 
						r.nextGaussian() + checkInTime);
				ssnVoter.setLeaveTime(leaveTime);
				ssnVoter.setVotingBoothTime(votingBoothTime * 0.1 * 
						r.nextGaussian() + votingBoothTime);
				ssnVoter.setTickTime(tick);
				ssnVoter.setEnterTick(tick);
				numSSN++;
				
				if(rNumber2 <= 50)
					A_L.add(ssnVoter);
				else
					M_Z.add(ssnVoter);
			}
			// 7% special needs voters
			else if (rNumber < 10 && rNumber > 3) {
				snVoter.setCheckInTime(checkInTime * 0.1 * 
						r.nextGaussian() + checkInTime);
				snVoter.setLeaveTime(leaveTime);
				snVoter.setVotingBoothTime(votingBoothTime * 0.1 * 
						r.nextGaussian() + votingBoothTime);
				snVoter.setTickTime(tick);
				snVoter.setEnterTick(tick);
				numSN++;
				
				if(rNumber2 <= 50)
					A_L.add(snVoter);
				else
					M_Z.add(snVoter);
			} 
			
			// 20% limited time voters
			else if (rNumber >= 10 && rNumber < 30) {
				ltVoter.setCheckInTime(checkInTime * 0.1 * 
						r.nextGaussian() + checkInTime);
				ltVoter.setLeaveTime(leaveTime);
				ltVoter.setVotingBoothTime(votingBoothTime * 0.1 * 
						r.nextGaussian() + votingBoothTime);
				ltVoter.setTickTime(tick);
				ltVoter.setEnterTick(tick);
				numLT++;
				
				if(rNumber2 <= 50)
					A_L.add(ltVoter);
				else
					M_Z.add(ltVoter);
			} 
			
			// 70% regular voters
			else {
				rVoter.setCheckInTime(checkInTime * 0.1 * 
						r.nextGaussian() + checkInTime);
				rVoter.setLeaveTime(leaveTime);
				rVoter.setVotingBoothTime(votingBoothTime * 0.1 * 
						r.nextGaussian() + votingBoothTime);
				rVoter.setTickTime(tick);
				rVoter.setEnterTick(tick);
				numR++;
				
				if(rNumber2 <= 50)
					A_L.add(rVoter);
				else
					M_Z.add(rVoter);
			}
			
			Sim.setTheoreticalThroughput(Sim.getTheoreticalThroughput() 
					+ 1);
		}
	}

	/******************************************************************
	 * getNextPerson: return nextPerson time
	 * 
	 * @return nextPerson
	 *****************************************************************/
	public int getNextPerson() {
		return nextPerson;
	}

	/******************************************************************
	 * getA_L: return the CheckIn instance of A_L.
	 * 
	 * @return A_L instance
	 *****************************************************************/
	public CheckIn getA_L() {
		return A_L;
	}

	/******************************************************************
	 * getM_Z: return the CheckIn instance of M_Z.
	 * 
	 * @return M_Z instance
	 *****************************************************************/
	public CheckIn getM_Z() {
		return M_Z;
	}

	/******************************************************************
	 * getNumOfTicksNextPerson: Number ofticks until next person comes.
	 * 
	 * @return numOfTicksNextPerson
	 *****************************************************************/
	public int getNumOfTicksNextPerson() {
		return numOfTicksNextPerson;
	}

	/******************************************************************
	 * getCheckInTime: voter check in time.
	 * 
	 * @return checkInTime
	 *****************************************************************/
	public int getCheckInTime() {
		return checkInTime;
	}
	
	/******************************************************************
	 * setCheckInTime: sets check in time of voter.
	 * 
	 * @param checkInTime
	 *****************************************************************/
	public void setCheckInTime(int checkInTime){
		this.checkInTime = checkInTime;
	}

	/******************************************************************
	 * getLeaveTime: returns voter leave time.
	 * 
	 * @return getLeaveTime
	 *****************************************************************/
	public int getLeaveTime() {
		return leaveTime;
	}
	
	/******************************************************************
	 * setLeaveTime: set voter leave time.
	 * 
	 * @param leaveTime
	 *****************************************************************/
	public void setLeaveTime(int leaveTime) {
		this.leaveTime = leaveTime;
	}

	/******************************************************************
	 * getVotingBoothTime: return the voters voting time.
	 * 
	 * @return votingBoothTime
	 *****************************************************************/
	public int getVotingBoothTime() {
		return votingBoothTime;
	}
	
	/******************************************************************
	 * setVotingBoothTime: edit voter voting time.
	 * 
	 * @param votingBoothTime
	 *****************************************************************/
	public void setVotingBoothTime(int votingBoothTime) {
		this.votingBoothTime = votingBoothTime;
	}
	
	/******************************************************************
	 * setTimeNext: set the time the nextPerson will come.
	 * 
	 * @param nextPerson
	 *****************************************************************/
	public void setTimeNext(int nextPerson) {
		this.numOfTicksNextPerson = nextPerson;
	}
	
	/******************************************************************
	 * getNumSSN: number of SSN voters arrived
	 * 
	 * @return numSSN
	 *****************************************************************/
	public int getNumSSN(){
		return numSSN;
	}
	
	/******************************************************************
	 * getNumSN: number of SN voters arrived
	 * 
	 * @return numSN
	 *****************************************************************/
	public int getNumSN(){
		return numSN;
	}
	
	/******************************************************************
	 * getNumLT: number of LT voters arrived
	 * 
	 * @return LT
	 *****************************************************************/
	public int getNumLT(){
		return numLT;
	}
	
	/******************************************************************
	 * getNumR: number of R voters arrived
	 * 
	 * @return R
	 *****************************************************************/
	public int getNumR(){
		return numR;
	}
}
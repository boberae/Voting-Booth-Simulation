package votingBoothPack;


public class SpecialNeedsVoter extends Voter {
	/*public class SpecialNeedsVoter extends Voter 
	checkInTime  * 1.5;
	leaveTime * 2;
	votingBoothTime * 3;*/
	public void setCheckInTime(double checkInTime)
	{
		this.checkInTime = 1.5*checkInTime;
	}
	
	public void setLeaveTime(int leaveTime)
	{
		this.leaveTime = 2*leaveTime;
	}
	
	public void setVotingBoothTime(double votingBoothTime)
	{
		this.votingBoothTime = 3*votingBoothTime;
	}
	
	public int getVoterType()
	{
		return 1;
	}

}


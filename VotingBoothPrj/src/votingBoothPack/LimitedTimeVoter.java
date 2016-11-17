package votingBoothPack;

public class LimitedTimeVoter extends Voter{
	/*public class LimtedTimeVoter extends Voter 
	checkInTime (no change);
	leaveTime * 0.5
	votingBoothTime * 0.5;*/
	public void setCheckInTime(double checkInTime)
	{
		this.checkInTime = checkInTime;
	}
	
	public void setLeaveTime(int leaveTime)
	{
		this.leaveTime = (int)(0.5*leaveTime);
	}
	
	public void setVotingBoothTime(double votingBoothTime)
	{
		this.votingBoothTime = (0.5*votingBoothTime);
	}
	
	public int getVoterType()
	{
		return 2;
	}

}


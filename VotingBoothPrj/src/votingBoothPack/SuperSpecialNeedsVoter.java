package votingBoothPack;

public class SuperSpecialNeedsVoter extends SpecialNeedsVoter {

	public void setCheckInTime(double checkInTime)
	{
		this.checkInTime = 3*checkInTime;
	}
	
	public void setLeaveTime(int leaveTime)
	{
		this.leaveTime = 4*leaveTime;
	}
	
	public void setVotingBoothTime(double votingBoothTime)
	{
		this.votingBoothTime = 6*votingBoothTime;
	}
	
	public int getVoterType()
	{
		return 0;
	}

}

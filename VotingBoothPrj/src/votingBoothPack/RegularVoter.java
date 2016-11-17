package votingBoothPack;

public class RegularVoter extends Voter{
	/*public class RegularVoter extends Voter 
	checkInTime  (no change);
	leaveTime (no change);
	votingBoothTime (no change);*/

	public void setCheckInTime(double checkInTime)
	{
		this.checkInTime = checkInTime;
	}
	
	public void setLeaveTime(int leaveTime)
	{
		this.leaveTime = leaveTime;
	}
	
	public void setVotingBoothTime(double votingBoothTime)
	{
		this.votingBoothTime = votingBoothTime;
	}
	
	public int getVoterType()
	{
		return 3;
	}
}
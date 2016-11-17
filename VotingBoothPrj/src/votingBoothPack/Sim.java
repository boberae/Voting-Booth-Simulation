package votingBoothPack;

import javax.swing.JOptionPane;

/**********************************************************************
 * Sim class sets up the voting booths and check in lines. Start sim
 * will instantiate all items and continue sim will get the necessary
 * information.
 * 
 * @author Tony Bober
 * @version 7/13/2016
 *********************************************************************/
public class Sim {

	// Everything in Sim is static, but no other classes use static 
	//variables or methods
	
	/** Time when next person arrives. */
	private static int timeNextPerson;
	
	/** Time for voter to check in. */
	private static int checkInTime;
	
	/** Average time before voters' get tired and leave the line. */
	private static int leaveTime;
	
	/** Average time spent at the voting booths. */
	private static int votingBoothTime;
	
	/** Number of voting booths. */
	private static int numBooths;
	
	/** Total time of simulation. */
	private static int totalTime;
	
	/** Increment size of simulation. */
	private static int stepSize;
	
	/** Maximum theoretical throughput at any point. */
	private static int theoreticalThroughput;
	
	/** Current time of the simulation. */
	private static int currentTime;

	
	/** VoterProducer clockListener. */
	private static VoterProducer producer;
	/** Booth clockListener. */
	private static Booth booth;
	/** A_L checkIn clockListener. */
	private static CheckIn A_L;
	/** M_Z checkIn clockListener. */
	private static CheckIn M_Z;
	/** Actual clock that runs simulation. */
	private static Clock clk;

	/******************************************************************
	 * startSim: Instantiates all the variables necessary to run the 
	 * simulation.
	 * 
	 * @param timeNextPerson1, next voter arrives
	 * @param checkInTime1, voter checkIn time
	 * @param totalTime1, time of simulation
	 * @param stepSize1, step size of sim
	 * @param votingBoothTime1, voting time
	 * @param leaveTime1, leave Q time
	 * @param numBooths1, num booths in sim
	 * @return if error
	 *****************************************************************/
	public static AllInfo startSim (String timeNextPerson1, 
			String checkInTime1, String totalTime1, String stepSize1, 
			String votingBoothTime1, String leaveTime1, 
			String numBooths1) {
		
		// Print an error message if inputs are invalid
		try {
			timeNextPerson = Integer.parseInt(timeNextPerson1);
			checkInTime = Integer.parseInt(checkInTime1);
			leaveTime = Integer.parseInt(leaveTime1);
			votingBoothTime = Integer.parseInt(votingBoothTime1);
			numBooths = Integer.parseInt(numBooths1);
			totalTime = Integer.parseInt(totalTime1);
			stepSize = Integer.parseInt(stepSize1);
		}
		catch (NumberFormatException e)
		{
			JOptionPane.showMessageDialog(null,"Improper input type. " +
					"All text fields" +
					" must contain positive integer values.");
			return null;
		}	
		
		if (timeNextPerson < 1 || checkInTime < 1 || leaveTime < 1 || 
				votingBoothTime < 1 || numBooths < 1 || totalTime < 1 
				|| stepSize < 1)
		{
			JOptionPane.showMessageDialog(null, 
					"Improper input type. All values must " +
					"be positive.");
			return null;
		}
		
		if (numBooths > 100)
		{
			JOptionPane.showMessageDialog(null, 
					"Number of booths must be less than 100.");
			return null;
		}
		
		theoreticalThroughput = 0;

		clk = new Clock();

		//Number of booths
		booth = new Booth(numBooths);
		A_L = new CheckIn(booth);
		M_Z = new CheckIn(booth);

		//checkIn A_L, checkIn M_Z, timeNextPerson, checkInTime, leaveTime, votingBoothTime
		producer = new VoterProducer(A_L, M_Z, 
				timeNextPerson, checkInTime, leaveTime, votingBoothTime);	

		clk.add(producer);
		clk.add(A_L);
		clk.add(M_Z);
		clk.add(booth);

		//Total time of simulation, step size, current time
		currentTime = clk.run(totalTime, stepSize, 0);
		

		//Package all information to send to GUI
		AllInfo data = new AllInfo(producer, A_L, M_Z, booth, theoreticalThroughput, currentTime);
		return data;
	}

	
	/******************************************************************
	 * continueSim: Runs setter methods for all variables of the voter
	 * and simulation times.
	 * 
	 * @param timeNextPerson1, next voter arrives
	 * @param checkInTime1, voter checkIn time
	 * @param totalTime1, time of simulation
	 * @param stepSize1, step size of sim
	 * @param votingBoothTime1, voting time
	 * @param leaveTime1, leave Q time
	 * @param numBooths1, num booths in sim
	 * @return if error
	 *****************************************************************/
	public static AllInfo continueSim (String timeNextPerson1, 
			String checkInTime1, String totalTime1, String stepSize1, 
			String votingBoothTime1, String leaveTime1, 
			String numBooths1) {
		//Total time of simulation, step size, current time

		// Print an error message if inputs are invalid
		try {
			timeNextPerson = Integer.parseInt(timeNextPerson1);
			checkInTime = Integer.parseInt(checkInTime1);
			leaveTime = Integer.parseInt(leaveTime1);
			votingBoothTime = Integer.parseInt(votingBoothTime1);
			numBooths = Integer.parseInt(numBooths1);
			totalTime = Integer.parseInt(totalTime1);
			stepSize = Integer.parseInt(stepSize1);
		}
		catch (NumberFormatException e)
		{
			JOptionPane.showMessageDialog(null,"Improper input type. " +
					"All text fields" +
					" must contain positive integer values.");
			return null;
		}	
				
		if (timeNextPerson < 1 || checkInTime < 1 || leaveTime < 1 || 
				votingBoothTime < 1 || numBooths < 1 || totalTime < 1 
				|| stepSize < 1)
		{
			JOptionPane.showMessageDialog(null, 
					"Improper input type. All values must " +
					"be positive.");
			return null;
		}
				
		if (numBooths > 100)
		{
			JOptionPane.showMessageDialog(null, 
					"Number of booths must be less than 100.");
			return null;
		}
		
		
		
		currentTime = clk.run(totalTime, stepSize, currentTime);

		//Package all information to send to GUI
		AllInfo data = new AllInfo(producer, A_L, M_Z, booth, theoreticalThroughput, currentTime);
		
		data.getProducer().setTimeNext(timeNextPerson);
		data.getProducer().setCheckInTime(checkInTime);
		data.getProducer().setLeaveTime(leaveTime);
		data.getProducer().setVotingBoothTime(votingBoothTime);
		numBooths = data.getBooth().setBooths(numBooths);
		
		return data;
	}
	
	/******************************************************************
	 * Get the theoretical number of voters who should make it thru 
	 * progress.
	 * 
	 * @return theoreticalThroughput
	 *****************************************************************/
	public static int getTheoreticalThroughput() {
		return theoreticalThroughput;
	}

	/******************************************************************
	 * Sets the theoretical throughput based on the number of voters
	 * that have entered the process.
	 * 
	 * @param theoreticalThroughput
	 *****************************************************************/
	public static void setTheoreticalThroughput(int 
			theoreticalThroughput) {
		Sim.theoreticalThroughput = theoreticalThroughput;
	}

	/******************************************************************
	 * Get the number of booths at the given time for the simulation
	 * 
	 * @return numBooths
	 *****************************************************************/
	public static int getNumBooths() {
		return numBooths;
	}
	
	
	// Main method
	public static void main (String[] args) {

	}
}

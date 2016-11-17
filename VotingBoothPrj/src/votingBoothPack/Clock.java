package votingBoothPack;
/**********************************************************************
 * Clock class manages the events for each tick of the program.
 * 
 * @author Tony Bober
 * @version 7/13/2016
 *********************************************************************/
public class Clock {

	/** Array of Booths and CheckIns and producers. */
	private ClockListener[] myListeners;
	
	/** number of cloeckListeners for above. */
	private int numListeners;
	
	/** Max number of listeners allowed (voter producers + 
	 * check in counters + booth) */
	private int MAX = 100;

	/******************************************************************
	 * Constructor: Instantiates a new array of clock listeners.
	 *****************************************************************/
	public Clock() {
		numListeners = 0;
		myListeners = new ClockListener[MAX];
	}

	/******************************************************************
	 * run: Runs the events for each set of clockListeners.
	 * 
	 * @param totalTime, time of total simulation
	 * @param stepSize, increment of time
	 * @param startTime, start time, usually 0
	 * @return the current Time of the clock
	 *****************************************************************/
	public int run(int totalTime, int stepSize, int startTime) {
		int currentTime = startTime;
		int endingTime = currentTime + stepSize;
		
		if (endingTime > totalTime)
			endingTime = totalTime;
		
		while (currentTime < endingTime){
			for (int j = 0; j < numListeners; j++)
				//run each clockListener event
			myListeners[j].event(currentTime);
			currentTime++;
		}
		
		return currentTime;
	}

	
	/******************************************************************
	 * add: Adds ClockListener cl to array of listeners and increments 
	 * total number of listeners.
	 * 
	 * @param cl, booth, producer or checkIn
	 *****************************************************************/
	public void add(ClockListener cl) {
		myListeners[numListeners] = cl;
		numListeners++;
	}

	/******************************************************************
	 * getMyListeners: Return the clockListeners
	 * 
	 * @return myListeners, array of clockListeners
	 *****************************************************************/
	public ClockListener[] getMyListeners() {
		return myListeners;
	}

	/******************************************************************
	 * setMyListeners: Allow program to add new clockListeners.
	 * 
	 * @param myListeners, new array of clockListeners
	 *****************************************************************/
	public void setMyListeners(ClockListener[] myListeners) {
		this.myListeners = myListeners;
	}

	/******************************************************************
	 * getNumListeners: Return the number of clockListeners in the
	 * program.
	 * 
	 * @return numListeners
	 *****************************************************************/
	public int getNumListeners() {
		return numListeners;
	}

	/******************************************************************
	 * setNumListeners:Set the new number of clockListeners.
	 * 
	 * @param numListeners
	 *****************************************************************/
	public void setNumListeners(int numListeners) {
		this.numListeners = numListeners;
	}

	/*****************************************************************
	 * getMAX: Get max number of ClockListeners
	 * @return MAX
	 *****************************************************************/
	public int getMAX() {
		return MAX;
	}

}
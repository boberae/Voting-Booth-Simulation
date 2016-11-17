package votingBoothPack;
/**********************************************************************
 * Interface allows for events to be created for sub classes, and
 * these events override the main ClockListenerEvent.
 * 
 * @author Tony Bober
 * @version 7/13/2016
 *********************************************************************/
public interface ClockListener {
	
	/******************************************************************
	 * Abstarct event: Allows for specific events to be ran.
	 * 
	 * @param tick, simulation second
	 *****************************************************************/
	public void event(int tick);
}

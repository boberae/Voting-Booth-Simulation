package votingBoothPack;


// AllInfo packages all the relevant values into one object to return to the GUI
public class AllInfo {

	private VoterProducer producer;
	private CheckIn A_L;
	private CheckIn M_Z;
	private Booth booth;
	private int currentTime;
	// Max possible number of people that could theoretically get through the booths
	private int theoreticalThroughput;
	
	public AllInfo(VoterProducer producer, CheckIn A_L1, CheckIn M_Z1, Booth booth1, int theoreticalThroughput1, int currentTime1) {
		this.producer = producer;
		A_L = A_L1;
		M_Z = M_Z1;
		booth = booth1;
		currentTime = currentTime1;
		theoreticalThroughput = theoreticalThroughput1;
		}

	public CheckIn getA_L() {
		return A_L;
	}

	public int getCurrentTime() {
		return currentTime;
	}
	
	public VoterProducer getProducer(){
		return producer;
	}

	public CheckIn getM_Z() {
		return M_Z;
	}

	public Booth getBooth() {
		return booth;
	}

	public int getTheoreticalThroughput() {
		return theoreticalThroughput;
	}

}

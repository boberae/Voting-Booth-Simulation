package votingBoothPack;

import javax.swing.JFrame;


// GUI for the simulation
public class VoterSimulation {
	//-----------------------------------------------------------------
    //  Creates and displays the frame.
    //-----------------------------------------------------------------
	public static void main (String[] args)
	{	
		// New JFrame
		JFrame frame = new JFrame ("Voting Simulation");
		frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);

		
		// New VoterSimPanel
		MainPanel panel = new MainPanel();
		
		frame.add(panel);
		frame.getContentPane().add(panel);
		frame.setSize(1200, 800);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
}

package votingBoothPack;



import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;


public class MainPanel extends JPanel{

	/**
	 * @author Tony Bober
	 */
	private static final long serialVersionUID = 1L;


	private JLabel inputsLabel;
	private JLabel gridSeparator1;
	private JLabel gridSeparator2;
	private JLabel timeNextPersonLabel;
	private JLabel checkInTimeLabel;
	private JLabel totalTimeLabel;
	private JLabel votingBoothTimeLabel;
	private JLabel leaveTimeLabel;
	private JLabel numberOfBoothsLabel;
	private JLabel outputsLabel;
	private JLabel throughputLabel;
	private JLabel avgTimeLabel;
	private JLabel numberLeftLabel;
	private JLabel max_AL_QLengthLabel;
	private JLabel max_MZ_QLengthLabel;
	private JLabel max_Booth_QLengthLabel;
	private JLabel throughputValue;
	private JLabel avgTimeValue;
	private JLabel numberLeftValue;
	private JLabel max_AL_QLengthValue;
	private JLabel max_MZ_QLengthValue;
	private JLabel max_Booth_QLengthValue;
	private JLabel stepSizeLabel;
	private JLabel elapsedTimeLabel;
	private JLabel elapsedTimeValue;
	private JLabel gridSeparator3;
	private JLabel AL_QLengthLabel;
	private JLabel AL_QLengthValue;
	private JLabel MZ_QLengthLabel;
	private JLabel MZ_QLengthValue;
	private JLabel booth_QLengthLabel;
	private JLabel booth_QLengthValue;
	private JLabel numSSNLabel;
	private JLabel numSSNValue;
	private JLabel numSNLabel;
	private JLabel numSNValue;
	private JLabel numLTLabel;
	private JLabel numLTValue;
	private JLabel numRLabel;
	private JLabel numRValue;
	private JLabel numInProgressLabel;
	private JLabel numInProgressValue;
	private JLabel A_L_Q;
	private JLabel M_Z_Q;
	private JLabel booth_Q;
	private JLabel A_L_Counter;
	private JLabel M_Z_Counter;
	private JLabel empty1;
	private JLabel empty2;
	private JLabel empty3;
	private JLabel empty4;
	private JLabel[] booths;
	
	private JButton startSimButton;
	private JButton runSimButton;
	private JButton continueSimButton;
	private JButton continuePauseButton;
	private JButton stopSimButton;

	private JTextField timeNextPersonValue;
	private JTextField checkInTimeValue;
	private JTextField totalTimeValue;
	private JTextField votingBoothTimeValue;
	private JTextField leaveTimeValue;
	private JTextField numberOfBoothsValue;
	private JTextField stepSizeValue;
	private JTextField timeConstantValue;

	private JPanel voterSimPanel;
	private JPanel voterSimPanel2;
	private JPanel rightPanel;
	private JPanel leftPanel;
	private JPanel continueSimPanel;	

	private Font smallBoldFont;
	private Font bigBoldFont;
	private Font peopleFont;
	
	private JScrollPane scrollPanel;
	private JScrollPane scrollPanel2;
	
	private Timer simTimer;
	
	private boolean timerPaused;

	
	// Create Panels
	public MainPanel(){
		
		// Main Panel
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		
		// Top Panel with the input/output data
		voterSimPanel = new JPanel();
		voterSimPanel.setLayout (new GridLayout(26,2)); // Increase the row value to add more data
		voterSimPanel.setBackground (Color.getHSBColor(200,200,50));

		inputsLabel = new JLabel (" INPUT INFORMATION");
		gridSeparator1 = new JLabel ("");
		timeNextPersonLabel = new JLabel (" Average amount of time between voters' arrivals:");
		checkInTimeLabel = new JLabel (" Average amount of time taken during check-in:");
		totalTimeLabel = new JLabel (" Total time of simulation:");
		votingBoothTimeLabel = new JLabel (" Average amount of time taken in voting booth:");
		leaveTimeLabel = new JLabel (" Average amount of time a voter is willing to wait before leaving:");
		numberOfBoothsLabel = new JLabel (" Number of voting booths:");
		outputsLabel = new JLabel (" OUTPUT INFORMATION");
		gridSeparator2 = new JLabel ("");
		throughputLabel = new JLabel (" Throughput:");
		avgTimeLabel = new JLabel (" Average time between a voter's arrival and departure:");
		numberLeftLabel = new JLabel (" Number of people who got tired of waiting and left:");
		AL_QLengthLabel = new JLabel (" Current queue length of check-in counter A-L");
		MZ_QLengthLabel = new JLabel (" Current queue length of check-in counter M-Z");
		booth_QLengthLabel = new JLabel (" Current queue length of voting booth line");
		max_AL_QLengthLabel = new JLabel (" Max queue length of check-in counter A-L:");
		max_MZ_QLengthLabel = new JLabel (" Max queue length of check-in counter M-Z:");
		max_Booth_QLengthLabel = new JLabel (" Max queue length of voting booth line:");
		numSSNLabel = new JLabel (" Total Super Special Needs Voters :");
		numSNLabel = new JLabel (" Total Special Needs Voters:");
		numLTLabel = new JLabel (" Total Limited Time Voters:");
		numRLabel = new JLabel (" Total Regular Voters:");
		numInProgressLabel = new JLabel (" Current amount of people in voting process:");
		throughputValue = new JLabel ("");
		avgTimeValue = new JLabel ("");
		numberLeftValue = new JLabel ("");
		AL_QLengthValue = new JLabel ("");
		MZ_QLengthValue = new JLabel ("");
		booth_QLengthValue = new JLabel ("");
		max_AL_QLengthValue = new JLabel ("");
		max_MZ_QLengthValue = new JLabel ("");
		max_Booth_QLengthValue = new JLabel ("");
		numSSNValue = new JLabel ("");
		numSNValue = new JLabel ("");
		numLTValue = new JLabel ("");
		numRValue = new JLabel ("");
		numInProgressValue = new JLabel ("");
		stepSizeLabel = new JLabel(" Interval time of the simulation: ");
		elapsedTimeLabel = new JLabel(" Current time elapsed in the simulation: ");
		elapsedTimeValue = new JLabel("");
		gridSeparator3 = new JLabel("");

		// The buttons each need their own panel
		startSimButton =  new JButton ("Click Thru Simulation");
		JPanel startButtonPanel = new JPanel();
		startButtonPanel.setBackground (Color.getHSBColor(200,200,50));
		startButtonPanel.setLayout(new BorderLayout());
		startButtonPanel.add(startSimButton, BorderLayout.EAST);
		
		runSimButton = new JButton ("Sim Thru Simulation");
		JPanel runButtonPanel = new JPanel();
		runButtonPanel.setBackground (Color.getHSBColor(200,200,50));
		runButtonPanel.setLayout(new BorderLayout());
		runButtonPanel.add(runSimButton, BorderLayout.WEST);

		continueSimButton = new JButton ("Continue Simulation");
		continueSimPanel = new JPanel();
		continueSimPanel.setBackground (Color.getHSBColor(200,200,50));
		continueSimPanel.setLayout(new BorderLayout());
		
		//will replace continue sim btn on continue sim panel
		stopSimButton = new JButton ("Stop Simulation");
		continuePauseButton = new JButton ("Continue Simulation");

		ButtonListener listen = new ButtonListener();
		startSimButton.addActionListener(listen);
		runSimButton.addActionListener(listen);
		continueSimButton.addActionListener(listen);
		stopSimButton.addActionListener(listen);
		continuePauseButton.addActionListener(listen);

		timeNextPersonValue = new JTextField();
		checkInTimeValue = new JTextField();
		totalTimeValue = new JTextField();
		votingBoothTimeValue = new JTextField();
		leaveTimeValue = new JTextField();
		numberOfBoothsValue = new JTextField();
		stepSizeValue = new JTextField();
		timeConstantValue = new JTextField();
		
		timeConstantValue.setText("Enter Time contstant in milliSeconds");
		runButtonPanel.add(timeConstantValue, BorderLayout.CENTER);

		//Added elements auto-fill in the grid from left-to-right and top-to-bottom
		voterSimPanel.add (inputsLabel);
		voterSimPanel.add (gridSeparator1);
		voterSimPanel.add (totalTimeLabel);
		voterSimPanel.add (totalTimeValue);
		voterSimPanel.add (stepSizeLabel);
		voterSimPanel.add (stepSizeValue);
		voterSimPanel.add (numberOfBoothsLabel);
		voterSimPanel.add (numberOfBoothsValue);
		voterSimPanel.add (timeNextPersonLabel);
		voterSimPanel.add (timeNextPersonValue);
		voterSimPanel.add (checkInTimeLabel);
		voterSimPanel.add (checkInTimeValue);	
		voterSimPanel.add (votingBoothTimeLabel);
		voterSimPanel.add (votingBoothTimeValue);
		voterSimPanel.add (leaveTimeLabel);
		voterSimPanel.add (leaveTimeValue);
		voterSimPanel.add (startButtonPanel);
		voterSimPanel.add (runButtonPanel);
		voterSimPanel.add (continueSimPanel);
		voterSimPanel.add (gridSeparator3);
		voterSimPanel.add (outputsLabel);
		voterSimPanel.add (gridSeparator2);
		voterSimPanel.add (elapsedTimeLabel);
		voterSimPanel.add (elapsedTimeValue);
		voterSimPanel.add (throughputLabel);
		voterSimPanel.add (throughputValue);
		voterSimPanel.add (numInProgressLabel);
		voterSimPanel.add (numInProgressValue);
		voterSimPanel.add (numberLeftLabel);
		voterSimPanel.add (numberLeftValue);
		voterSimPanel.add (AL_QLengthLabel);
		voterSimPanel.add (AL_QLengthValue);
		voterSimPanel.add (MZ_QLengthLabel);
		voterSimPanel.add (MZ_QLengthValue);
		voterSimPanel.add (booth_QLengthLabel);
		voterSimPanel.add (booth_QLengthValue);
		voterSimPanel.add (max_AL_QLengthLabel);
		voterSimPanel.add (max_AL_QLengthValue);
		voterSimPanel.add (max_MZ_QLengthLabel);
		voterSimPanel.add (max_MZ_QLengthValue);
		voterSimPanel.add (max_Booth_QLengthLabel);
		voterSimPanel.add (max_Booth_QLengthValue);
		voterSimPanel.add (avgTimeLabel);
		voterSimPanel.add (avgTimeValue);
		voterSimPanel.add (numSSNLabel);
		voterSimPanel.add (numSSNValue);
		voterSimPanel.add (numSNLabel);
		voterSimPanel.add (numSNValue);
		voterSimPanel.add (numLTLabel);
		voterSimPanel.add (numLTValue);
		voterSimPanel.add (numRLabel);
		voterSimPanel.add (numRValue);
		

		//Bottom Panel with the visual simulation
		voterSimPanel2 = new JPanel();	
		voterSimPanel2.setLayout(new BoxLayout(voterSimPanel2, BoxLayout.X_AXIS));
		bigBoldFont = new Font("Courier", Font.BOLD, 18);
		smallBoldFont = new Font("Courier", Font.BOLD, 12);
		peopleFont = new Font("Courier", Font.BOLD, 11);

		
		// Bottom-Left Panel
		leftPanel = new JPanel();
		leftPanel.setLayout(new GridLayout(3,3));

		A_L_Q = new JLabel ("", SwingConstants.RIGHT);
		A_L_Q.setFont(peopleFont);
		M_Z_Q = new JLabel ("", SwingConstants.RIGHT);
		M_Z_Q.setFont(peopleFont);
		A_L_Counter = new JLabel (" |A-L REGISTRATION| ");
		A_L_Counter.setFont(bigBoldFont);
		M_Z_Counter = new JLabel (" |M-Z REGISTRATION| ");
		M_Z_Counter.setFont(bigBoldFont);
		booth_Q = new JLabel ("", SwingConstants.CENTER);
		booth_Q.setFont(peopleFont);
		empty1 = new JLabel ("");
		empty2 = new JLabel ("");
		empty3 = new JLabel ("");
		empty4 = new JLabel ("");
		
		// Bottom-Right Panel
		rightPanel = new JPanel();
		
		// Add bottom-left and bottom-right to Bottom panel
		voterSimPanel2.add(leftPanel);
		voterSimPanel2.add(rightPanel);
		
		scrollPanel = new JScrollPane(voterSimPanel2, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPanel.setPreferredSize(new Dimension(400, 400));
		
		scrollPanel2 = new JScrollPane(voterSimPanel, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPanel2.setPreferredSize(new Dimension(800, 800));
		
		// Add top and bottom panels to Main Panel
		add(scrollPanel2);
		add(scrollPanel);
		
		timerPaused = false;
	}

	private class ButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) 
		{
			// Quit button exits game
			if (e.getSource() == runSimButton)
			{
				timerPaused = false;
				
				AllInfo data = Sim.startSim(timeNextPersonValue.getText(), 
						checkInTimeValue.getText(), 
						totalTimeValue.getText(), 
						stepSizeValue.getText(),
						votingBoothTimeValue.getText(), 
						leaveTimeValue.getText(), 
						numberOfBoothsValue.getText());
				
				if(data == null)
					return;

				throughputValue.setText("" + data.getBooth().getThroughPut() + " people with max = " + data.getTheoreticalThroughput());
				numberLeftValue.setText("" + (data.getBooth().getNumLeft() + data.getA_L().getNumLeft() + data.getM_Z().getNumLeft()) + " people");
				AL_QLengthValue.setText("" + data.getA_L().getCheckInQ().size() + " people");
				MZ_QLengthValue.setText("" + data.getM_Z().getCheckInQ().size() + " people");
				booth_QLengthValue.setText("" + data.getBooth().getQ().size() + " people");
				max_AL_QLengthValue.setText("" + data.getA_L().getMaxCheckInQ() + " people");
				max_MZ_QLengthValue.setText("" + data.getM_Z().getMaxCheckInQ() + " people");
				max_Booth_QLengthValue.setText("" + data.getBooth().getMaxQlength() + " people");
				numSSNValue.setText("" + data.getProducer().getNumSSN() + " people");
				numSNValue.setText("" + data.getProducer().getNumSN() + " people");
				numLTValue.setText("" + data.getProducer().getNumLT() + " people");
				numRValue.setText("" + data.getProducer().getNumR() + " people");
				numInProgressValue.setText(data.getA_L().getInProgressCount() +
						data.getM_Z().getInProgressCount() +
						data.getBooth().getInProcessCount() + 
						" people");
				elapsedTimeValue.setText("" + data.getCurrentTime() + " seconds ");

				setRightPanel(Sim.getNumBooths(), data);
				setLeftPanel(data);
				
				int timeConst = 0;
				
				try{ 
					timeConst = Integer.parseInt
							(timeConstantValue.getText());}
				catch(Exception ex){ JOptionPane.showMessageDialog
					(null, "Positive Integers Only."); return; }
				
				continueSimPanel.add(stopSimButton, BorderLayout.EAST);
				continueSimPanel.remove(continueSimButton);
				
				if(simTimer != null)
					simTimer.stop();
				
				simTimer = new Timer(timeConst, new ActionListener() {
				    public void actionPerformed(ActionEvent evt) {
				    	if(!timerPaused)
				    		continueSimButton.doClick();
				    }
				});
				
				timeNextPersonValue.setEnabled(false);
				checkInTimeValue.setEnabled(false);
				totalTimeValue.setEnabled(false);
				stepSizeValue.setEnabled(false);
				votingBoothTimeValue.setEnabled(false);
				leaveTimeValue.setEnabled(false);
				numberOfBoothsValue.setEnabled(false);
				timeConstantValue.setEnabled(false);
				
				voterSimPanel.revalidate();
				voterSimPanel.repaint();
				
				simTimer.start();
			}

			
			// Start button starts a new simulation using the values in the text fields
			// Continue button does not show up until start button is pressed
			if (e.getSource() == startSimButton)
			{
				AllInfo data = Sim.startSim(timeNextPersonValue.getText(), 
						checkInTimeValue.getText(), 
						totalTimeValue.getText(), 
						stepSizeValue.getText(),
						votingBoothTimeValue.getText(), 
						leaveTimeValue.getText(), 
						numberOfBoothsValue.getText());
				
				if(simTimer != null)
					simTimer.stop();
				
				if(data == null)
					return;

				throughputValue.setText("" + data.getBooth().getThroughPut() + " people with max = " + data.getTheoreticalThroughput());
				numberLeftValue.setText("" + (data.getBooth().getNumLeft() + data.getA_L().getNumLeft() + data.getM_Z().getNumLeft()) + " people");
				AL_QLengthValue.setText("" + data.getA_L().getCheckInQ().size() + " people");
				MZ_QLengthValue.setText("" + data.getM_Z().getCheckInQ().size() + " people");
				booth_QLengthValue.setText("" + data.getBooth().getQ().size() + " people");
				max_AL_QLengthValue.setText("" + data.getA_L().getMaxCheckInQ() + " people");
				max_MZ_QLengthValue.setText("" + data.getM_Z().getMaxCheckInQ() + " people");
				max_Booth_QLengthValue.setText("" + data.getBooth().getMaxQlength() + " people");
				numSSNValue.setText("" + data.getProducer().getNumSSN() + " people");
				numSNValue.setText("" + data.getProducer().getNumSN() + " people");
				numLTValue.setText("" + data.getProducer().getNumLT() + " people");
				numRValue.setText("" + data.getProducer().getNumR() + " people");
				numInProgressValue.setText(data.getA_L().getInProgressCount() +
						data.getM_Z().getInProgressCount() +
						data.getBooth().getInProcessCount() + 
						" people");
				elapsedTimeValue.setText("" + data.getCurrentTime() + " seconds ");

				timeNextPersonValue.setEnabled(true);
				checkInTimeValue.setEnabled(true);
				totalTimeValue.setEnabled(true); 
				stepSizeValue.setEnabled(true);
				votingBoothTimeValue.setEnabled(true);
				leaveTimeValue.setEnabled(true);
				numberOfBoothsValue.setEnabled(true);
				timeConstantValue.setEnabled(true);
				
				continueSimPanel.add(continueSimButton, BorderLayout.EAST);
				continueSimPanel.remove(continuePauseButton);
				continueSimPanel.remove(stopSimButton);

				setRightPanel(Sim.getNumBooths(), data);
				setLeftPanel(data);
			}

			
			// Continue button continues a running simulation
			if (e.getSource() == continueSimButton)
			{
				AllInfo data = Sim.continueSim(timeNextPersonValue.getText(), 
						checkInTimeValue.getText(), 
						totalTimeValue.getText(), 
						stepSizeValue.getText(),
						votingBoothTimeValue.getText(), 
						leaveTimeValue.getText(), 
						numberOfBoothsValue.getText());
				
				if(data == null){
					if(simTimer != null){
						stopSimButton.doClick();
					}
					return;
				}


				throughputValue.setText("" + data.getBooth().getThroughPut() + " people with max = " + data.getTheoreticalThroughput());
				numberLeftValue.setText("" + (data.getBooth().getNumLeft() + data.getA_L().getNumLeft() + data.getM_Z().getNumLeft()) + " people");
				AL_QLengthValue.setText("" + data.getA_L().getCheckInQ().size() + " people");
				MZ_QLengthValue.setText("" + data.getM_Z().getCheckInQ().size() + " people");
				booth_QLengthValue.setText("" + data.getBooth().getQ().size() + " people");
				max_AL_QLengthValue.setText("" + data.getA_L().getMaxCheckInQ() + " people");
				max_MZ_QLengthValue.setText("" + data.getM_Z().getMaxCheckInQ() + " people");
				max_Booth_QLengthValue.setText("" + data.getBooth().getMaxQlength() + " people");
				numSSNValue.setText("" + data.getProducer().getNumSSN() + " people");
				numSNValue.setText("" + data.getProducer().getNumSN() + " people");
				numLTValue.setText("" + data.getProducer().getNumLT() + " people");
				numRValue.setText("" + data.getProducer().getNumR() + " people");
				numInProgressValue.setText(data.getA_L().getInProgressCount() +
						data.getM_Z().getInProgressCount() +
						data.getBooth().getInProcessCount() + 
						" people");
				avgTimeValue.setText("" + data.getBooth().getProcessTime() + " seconds");
				elapsedTimeValue.setText("" + data.getCurrentTime() + " seconds ");
				
				setRightPanel(Sim.getNumBooths(), data);
				setLeftPanel(data);
				
				if(data.getCurrentTime() == Integer.parseInt(totalTimeValue.getText()))
				{
					timeNextPersonValue.setEnabled(true);
					checkInTimeValue.setEnabled(true);
					totalTimeValue.setEnabled(true); 
					stepSizeValue.setEnabled(true);
					votingBoothTimeValue.setEnabled(true);
					leaveTimeValue.setEnabled(true);
					numberOfBoothsValue.setEnabled(true);
					timeConstantValue.setEnabled(true);
					
					voterSimPanel.revalidate();
					voterSimPanel.repaint();
					
					if(simTimer != null)
						simTimer.stop();
					
					JOptionPane.showMessageDialog(null, "Simulation Ended.");
				}
			}
			
			if(e.getSource() == stopSimButton)
			{
				timerPaused = true;
				
				timeNextPersonValue.setEnabled(true);
				checkInTimeValue.setEnabled(true);
				totalTimeValue.setEnabled(true); 
				stepSizeValue.setEnabled(true);
				votingBoothTimeValue.setEnabled(true);
				leaveTimeValue.setEnabled(true);
				numberOfBoothsValue.setEnabled(true);
				timeConstantValue.setEnabled(true);
				
				continueSimPanel.add(continuePauseButton, BorderLayout.EAST);
				continueSimPanel.remove(stopSimButton);
				
				voterSimPanel.revalidate();
				voterSimPanel.repaint();
			}
			
			if(e.getSource() == continuePauseButton)
			{
				try{
					simTimer.setDelay(Integer.parseInt
						(timeConstantValue.getText()));
				}
				catch(Exception ex){
					JOptionPane.showMessageDialog(null, "Invalid " +
						"Time Constant.");
				}
				
				timerPaused = false;
				
				timeNextPersonValue.setEnabled(false);
				checkInTimeValue.setEnabled(false);
				totalTimeValue.setEnabled(false);
				stepSizeValue.setEnabled(false);
				votingBoothTimeValue.setEnabled(false);
				leaveTimeValue.setEnabled(false);
				numberOfBoothsValue.setEnabled(false);
				timeConstantValue.setEnabled(false);
				
				continueSimPanel.add(stopSimButton, BorderLayout.EAST);
				continueSimPanel.remove(continuePauseButton);
				
				voterSimPanel.revalidate();
				voterSimPanel.repaint();
			}
		}
	}

	
	// Helper method to update the bottom-right panel at the start of a simulation
	private void setRightPanel(int numBooths, AllInfo data) {
		rightPanel.removeAll();
		rightPanel.setLayout(new GridLayout(numBooths,1));

		booths = new JLabel[100];

		for(int i = 0; i < numBooths; i++)
		{
			if(data.getBooth().getPeopleAtBooths().get(i) != null)
				if(data.getBooth().getPeopleAtBooths().get(i).getVoterType() == 0)
					booths[i] = new JLabel(" SSN" + "|BOOTH " + (i+1) + "|");
				else if(data.getBooth().getPeopleAtBooths().get(i).getVoterType() == 1)
					booths[i] = new JLabel("  SN" + "|BOOTH " + (i+1) + "|");
			
				else if(data.getBooth().getPeopleAtBooths().get(i).getVoterType() == 2)
					booths[i] = new JLabel("  LT" + "|BOOTH " + (i+1) + "|");
			
				else
					booths[i] = new JLabel("   R" + "|BOOTH " + (i+1) + "|");
			
			else
				booths[i] = new JLabel("    " + "|BOOTH " + (i+1) + "|");
			
			booths[i].setFont(smallBoldFont);
			rightPanel.add(booths[i]);
		}
	}

	// Helper method to update the bottom-left panel after each interval of the simulation
	private void setLeftPanel(AllInfo data) {
		leftPanel.removeAll();
		
		String a = "";
		String b = "";
		String c = "";
		
		A_L_Q.setText("");
		M_Z_Q.setText("");
		booth_Q.setText("");
		
		if(data.getA_L().getPersonAtCheckIn() != null)
			if(data.getA_L().getPersonAtCheckIn().getVoterType() == 0)
				A_L_Counter = new JLabel (" SSN" + "|A-L REGISTRATION| ");
			else if(data.getA_L().getPersonAtCheckIn().getVoterType() == 1)
				A_L_Counter = new JLabel ( " SN" + "|A-L REGISTRATION| ");
		
			else if(data.getA_L().getPersonAtCheckIn().getVoterType() == 2)
				A_L_Counter = new JLabel ("  LT" + "|A-L REGISTRATION| ");
		
			else
				A_L_Counter = new JLabel ("   R" + "|A-L REGISTRATION| ");
		
		else
			A_L_Counter = new JLabel ("    |A-L REGISTRATION| ");
		
		if(data.getM_Z().getPersonAtCheckIn() != null)
			if(data.getM_Z().getPersonAtCheckIn().getVoterType() == 0)
				M_Z_Counter = new JLabel (" SSN" + "|M_Z REGISTRATION| ");
			else if(data.getM_Z().getPersonAtCheckIn().getVoterType() == 1)
				M_Z_Counter = new JLabel ("  SN" + "|M_Z REGISTRATION| ");
		
			else if(data.getM_Z().getPersonAtCheckIn().getVoterType() == 2)
				M_Z_Counter = new JLabel ("  LT" + "|M_Z REGISTRATION| ");
		
			else
				M_Z_Counter = new JLabel ("   R" + "|M_Z REGISTRATION| ");
		
		else
			M_Z_Counter = new JLabel ("    |M_Z REGISTRATION| ");
		
		A_L_Counter.setFont(bigBoldFont);
		M_Z_Counter.setFont(bigBoldFont);
		
		
		
		for(int i = 0; i < data.getA_L().getCheckInQ().size(); i++)
		{
			if(data.getA_L().getCheckInQ().get(i).getVoterType() == 0)
				a = (" SSN" + a);
			else if(data.getA_L().getCheckInQ().get(i).getVoterType() == 1)
				a = (" SN" + a);
			
			else if(data.getA_L().getCheckInQ().get(i).getVoterType() == 2)
				a = (" LT" + a);
			
			else
				a = (" R" + a);
		}
		A_L_Q.setText(a);
		
		for(int i = 0; i < data.getM_Z().getCheckInQ().size(); i++)
		{
			if(data.getM_Z().getCheckInQ().get(i).getVoterType() == 0)
				b = (" SSN" + b);
			else if(data.getM_Z().getCheckInQ().get(i).getVoterType() == 1)
				b = (" SN" + b);
			
			else if(data.getM_Z().getCheckInQ().get(i).getVoterType() == 2)
				b = (" LT" + b);
			
			else
				b = (" R" + b);
		}
		M_Z_Q.setText(b);
		
		for(int i = 0; i < data.getBooth().getQ().size(); i++)
		{
			if(data.getBooth().getQ().get(i).getVoterType() == 0)
				c = (" SSN" + c);
			else if(data.getBooth().getQ().get(i).getVoterType() == 1)
				c = (" SN" + c);
			
			else if(data.getBooth().getQ().get(i).getVoterType() == 2)
				c = (" LT" + c);
			
			else
				c = (" R" + c);
		}
		booth_Q.setText(c);
		
		leftPanel.add (A_L_Q);
		leftPanel.add (A_L_Counter);
		leftPanel.add (empty1);
		leftPanel.add (empty2);
		leftPanel.add (empty3);
		leftPanel.add (booth_Q);
		leftPanel.add (M_Z_Q);
		leftPanel.add (M_Z_Counter);
		leftPanel.add (empty4);
		
		leftPanel.revalidate();
		leftPanel.repaint();
		
	}

}



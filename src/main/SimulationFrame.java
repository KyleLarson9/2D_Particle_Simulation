package main;

import javax.swing.JFrame;

public class SimulationFrame extends JFrame {

	public SimulationFrame(SimulationPanel panel) {
		this.add(panel);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
	
}

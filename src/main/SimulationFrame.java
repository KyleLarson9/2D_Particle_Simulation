package main;

import javax.swing.JFrame;

public class SimulationFrame extends JFrame {

	private static final long serialVersionUID = -2006883174417107008L;

	public SimulationFrame(SimulationPanel panel) {
		this.add(panel);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
	
}

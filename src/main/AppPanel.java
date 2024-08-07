package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import inputs.KeyboardInputs;
import inputs.MouseInputs;

public class AppPanel extends JPanel {

	private App app;
	private Dimension size;
	public MouseInputs mouseInputs;
	
	public AppPanel(App app) {
		this.app = app;
		mouseInputs = new MouseInputs(this);

		setPanelSize();
		addKeyListener(new KeyboardInputs(this));
		addMouseListener(mouseInputs);
		addMouseMotionListener(mouseInputs);
		this.setBackground(Color.DARK_GRAY);
	}
	
	// public methods
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		Graphics2D g2d = (Graphics2D) g;
		
		app.render(g2d);
	}
	
	public App getApp() {
		return app;
	}
	
	// private methods
	
	private void setPanelSize() {
		size = new Dimension(App.APP_WIDTH, App.APP_HEIGHT);
		setMinimumSize(size);
		setMaximumSize(size);
		setPreferredSize(size);
	}
	
}

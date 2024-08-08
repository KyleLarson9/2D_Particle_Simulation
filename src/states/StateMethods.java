package states;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public interface StateMethods {

	public void update();
	
	public void render(Graphics2D g2d);
	
	public void mouseClicked(MouseEvent e);
	
	public void mousePressed(MouseEvent e);
	
	public void mouseReleased(MouseEvent e);
	
	public void mouseMoved(MouseEvent e);
	
	public void keyReleased(KeyEvent e);
	
	public void keyPressed(KeyEvent e);}

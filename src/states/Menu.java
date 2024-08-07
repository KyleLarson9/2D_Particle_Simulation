package states;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import main.App;

public class Menu extends State implements StateMethods {

	public Menu(App app) {
		super(app);
	}

	@Override
	public void update() {
		
	}

	@Override
	public void render(Graphics2D g2d) {
		g2d.setColor(Color.black);
		g2d.drawString("MENU", App.APP_WIDTH/2, 200);
	}

	@Override
	public void mouseClicked(MouseEvent e) {

	}

	@Override
	public void mousePressed(MouseEvent e) {
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_ENTER) 
			AppState.state = AppState.SIMULATING;
	}

	

}

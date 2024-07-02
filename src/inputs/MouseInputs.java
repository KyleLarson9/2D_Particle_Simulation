package inputs;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import main.App;

public class MouseInputs extends MouseAdapter implements MouseListener, MouseMotionListener {

	public int x, y;
	
	private App app;
	
	public MouseInputs(App app) {
		this.app = app;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	@Override
	public void mouseMoved(MouseEvent e) {
		x = e.getX();
		y = e.getY();
	}
	
}

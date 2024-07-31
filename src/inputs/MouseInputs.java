package inputs;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import main.App;

public class MouseInputs extends MouseAdapter implements MouseListener, MouseMotionListener {

	public static int x, y;
	public static boolean clicked = false;
		
	public MouseInputs() {
	}
	
	public static int getX() {
		return x;
	}
	
	public static int getY() {
		return y;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		clicked = true;
		x = e.getX();
		y = e.getY();
	}
	
	public void resetClick() {
		clicked = false;
	}
	
}

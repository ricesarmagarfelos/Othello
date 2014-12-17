
import acm.graphics.*;

public class CRect extends GRect {
	
	public CRect(int x, int y, int dx, int dy) {
		super(x, y, dx, dy);
	}
	
	public boolean isClicked() {
		return clicked;
	}
	
	public void setClicked(boolean c) {
		clicked = c;
	}
	
	public void setXCord(int xCord) {
		xPosition = xCord;
	}
	
	public void setYCord(int yCord) {
		yPosition = yCord;
	}
	
	public int getXCord() {
		return xPosition;
	}
	
	public int getYCord() {
		return yPosition;
	}
	
	private boolean clicked = false;
	private int xPosition, yPosition;

}

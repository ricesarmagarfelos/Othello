
public class Coordinates {
	
	public Coordinates() {
		x = 0;
		y = 0;
	}
	
	public Coordinates(int xInit, int yInit) {
		x = xInit;
		y = yInit;
		
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public void setX(int newX) {
		x = newX;
	}
	
	public void setY(int newY) {
		y = newY;
	}
	
	private int x, y;

}

import java.awt.Color;

import acm.graphics.*;
import acm.program.*;

public class Othello extends GraphicsProgram {

	private static final int APP_SIZE = 640;

	private static final int CELLS_PER_ROW = 8;

	public void init() {
		createBoard(APP_SIZE, CELLS_PER_ROW);
		setBackground(Color.green);
	}

	public void run() {

	}
	
	private void createBoard(int appLength, int numCells) {
		setSize(appLength, appLength);
		int cellLength = appLength / numCells;

		for (int i = 0; i < numCells; i++) {
			for (int j = 0; j < numCells; j++) {
				GRect cell = new GRect(cellLength * j, cellLength * i,
						cellLength, cellLength);
				add(cell);
			}
		}
	}

}

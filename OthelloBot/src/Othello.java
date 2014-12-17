import java.awt.Color;

import acm.graphics.*;
import acm.program.*;

import java.awt.event.MouseEvent;

public class Othello extends GraphicsProgram {

	private static final int APP_SIZE = 640;

	private static final int CELLS_PER_ROW = 8;

	private static final int PIECE_RADIUS = 30;

	private static final int PIECE_OFFSET = 10;
	
	private static final int CENTER = 320;

	public void init() {
		createBoard(APP_SIZE, CELLS_PER_ROW);
		setBackground(Color.green);
	}

	public void run() {
		addMouseListeners();
	}

	public void mouseClicked(MouseEvent e) {
		CRect c = (CRect) getElementAt(e.getX(), e.getY());

		if (!c.isClicked()) {
			addPiece(c.getX() + PIECE_OFFSET, c.getY() + PIECE_OFFSET, playerTurn);
			playerTurn = !playerTurn;
		}

		c.setClicked(true);

	}

	private void createBoard(int appLength, int numCells) {
		setSize(appLength, appLength);
		int cellLength = appLength / numCells;

		for (int i = 0; i < numCells; i++) {
			for (int j = 0; j < numCells; j++) {
				CRect cell = new CRect(cellLength * j, cellLength * i,
						cellLength, cellLength);
				add(cell);
				cell.setXCord(j + 1);
				cell.setYCord(i + 1);
			}
		}
		
		addStartingFour();
	}
	
	private void addStartingFour() {
		CRect topLeft = (CRect) getElementAt(CENTER - 1, CENTER - 1);
		CRect topRight = (CRect) getElementAt(CENTER + 1, CENTER - 1);
		CRect bottomLeft = (CRect) getElementAt(CENTER - 1, CENTER + 1);
		CRect bottomRight = (CRect) getElementAt(CENTER + 1, CENTER + 1);
		
		topLeft.setClicked(true);
		topRight.setClicked(true);
		bottomLeft.setClicked(true);
		bottomRight.setClicked(true);
		
		addPiece(topLeft.getX() + PIECE_OFFSET, topLeft.getY() + PIECE_OFFSET, true);
		addPiece(topRight.getX() + PIECE_OFFSET, topRight.getY() + PIECE_OFFSET, false);
		addPiece(bottomLeft.getX() + PIECE_OFFSET, bottomLeft.getY() + PIECE_OFFSET, false);
		addPiece(bottomRight.getX() + PIECE_OFFSET, bottomRight.getY() + PIECE_OFFSET, true);
		
		pieceTracker[topLeft.getYCord()][topLeft.getXCord()] = 1;
		pieceTracker[bottomRight.getYCord()][bottomRight.getXCord()] = 1;
		pieceTracker[bottomLeft.getYCord()][bottomLeft.getXCord()] = 2;
		pieceTracker[topRight.getYCord()][topRight.getXCord()] = 2;
	}

	private void addPiece(double x, double y, boolean playerColor) {
		GOval o = new GOval(x, y, PIECE_RADIUS * 2, PIECE_RADIUS * 2);
		o.setFilled(true);
		if (playerColor) {
			o.setFillColor(Color.white);
		} else {
			o.setFillColor(Color.black);
		}
		o.sendToBack();
		add(o);
	}

	private boolean playerTurn = true;
	private int[][] pieceTracker = new int[CELLS_PER_ROW][CELLS_PER_ROW];
	// 1 is white piece, 2 is black piece

}

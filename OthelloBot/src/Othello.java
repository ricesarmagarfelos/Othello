import java.awt.Color;

import acm.graphics.*;
import acm.program.*;

import java.awt.event.MouseEvent;

public class Othello extends GraphicsProgram {

	private static final int APP_SIZE = 640;

	private static final int CELLS_PER_ROW = 8;

	private static final int CELL_LENGTH = 80;

	private static final int PIECE_RADIUS = 30;

	private static final int OFFSET = 10;

	private static final int CENTER = 320;

	private static final int CELL_CENTER = 40;

	private static final int WHITE = 1;
	private static final int BLACK = 2;

	public void init() {
		createBoard(APP_SIZE, CELLS_PER_ROW);
		setBackground(Color.green);
	}

	public void run() {
		addMouseListeners();
	}

	public void mouseClicked(MouseEvent e) {
		try {
			CRect c = (CRect) getElementAt(e.getX(), e.getY());

			if (!c.isClicked()) {
				addPiece(c.getX(), c.getY(), playerTurn);
				if (playerTurn) {
					pieceTracker[c.getYCord()][c.getXCord()] = WHITE;
				} else {
					pieceTracker[c.getYCord()][c.getXCord()] = BLACK;
				}
				flip(c.getXCord(), c.getYCord(), playerTurn);

				playerTurn = !playerTurn;
			}

			c.setClicked(true);

		} catch (ClassCastException c) {
			// player clicked on a piece
		}

	}

	private void flip(int xCord, int yCord, boolean pTurn) {
		if (pieceRight(xCord, yCord, pTurn)) {
			flipRight(xCord, yCord, pTurn);
		}

		if (pieceLeft(xCord, yCord, pTurn)) {
			flipLeft(xCord, yCord, pTurn);
		}

		if (pieceUp(xCord, yCord, pTurn)) {
			flipUp(xCord, yCord, pTurn);
		}
		
		if (pieceDown(xCord, yCord, pTurn)) {
			flipDown(xCord, yCord, pTurn);
		}
		
		if (pieceUpLeft(xCord, yCord, pTurn)) {
			flipUpLeft(xCord, yCord, pTurn);
		}
		
		if (pieceUpRight(xCord, yCord, pTurn)) {
			flipUpRight(xCord, yCord, pTurn);
		}
		
		if (pieceLowLeft(xCord, yCord, pTurn)) {
			flipLowLeft(xCord, yCord, pTurn);
		}
		
		if (pieceLowRight(xCord, yCord, pTurn)) {
			flipLowRight(xCord, yCord, pTurn);
		}
	}

	private boolean pieceRight(int rX, int rY, boolean pTurn) {
		boolean piecePresent = false;
		if (rX == CELLS_PER_ROW - 1)
			return piecePresent;
		// at the right of the screen

		rX++;
		if (pTurn) { // white is placing
			while (pieceTracker[rY][rX] == BLACK) {
				if (rX == CELLS_PER_ROW - 1) break;
				rX++;
			}
			if (pieceTracker[rY][rX] == WHITE)
				piecePresent = true;
		} else { // black is placing
			while (pieceTracker[rY][rX] == WHITE) {
				if (rX == CELLS_PER_ROW - 1) break;
				rX++;
			}
			if (pieceTracker[rY][rX] == BLACK)
				piecePresent = true;
		}

		return piecePresent;
	}

	private void flipRight(int rX, int rY, boolean pTurn) {
		rX++;
		if (pTurn) {
			while (pieceTracker[rY][rX] == BLACK) {
				GOval piece = (GOval) getElementAt(CELL_LENGTH * rX
						+ CELL_CENTER, CELL_LENGTH * rY + CELL_CENTER);
				piece.setFillColor(Color.white);
				pieceTracker[rY][rX] = WHITE;
				rX++;
			}
		} else {
			while (pieceTracker[rY][rX] == WHITE) {
				GOval piece = (GOval) getElementAt(CELL_LENGTH * rX
						+ CELL_CENTER, CELL_LENGTH * rY + CELL_CENTER);
				piece.setFillColor(Color.black);
				pieceTracker[rY][rX] = BLACK;
				rX++;
			}
		}
	}

	private boolean pieceLeft(int rX, int rY, boolean pTurn) {
		boolean piecePresent = false;
		if (rX == 0)
			return piecePresent;
		// at the left of the screen

		rX--;
		if (pTurn) { // white is placing
			while (pieceTracker[rY][rX] == BLACK) {
				if (rX == 0) break;
				rX--;
			}
			if (pieceTracker[rY][rX] == WHITE)
				piecePresent = true;
		} else { // black is placing
			while (pieceTracker[rY][rX] == WHITE) {
				if (rX == 0) break;
				rX--;
			}
			if (pieceTracker[rY][rX] == BLACK)
				piecePresent = true;
		}

		return piecePresent;
	}

	private void flipLeft(int rX, int rY, boolean pTurn) {
		rX--;
		if (pTurn) {
			while (pieceTracker[rY][rX] == BLACK) {
				GOval piece = (GOval) getElementAt(CELL_LENGTH * rX
						+ CELL_CENTER, CELL_LENGTH * rY + CELL_CENTER);
				piece.setFillColor(Color.white);
				pieceTracker[rY][rX] = WHITE;
				rX--;
			}
		} else {
			while (pieceTracker[rY][rX] == WHITE) {
				GOval piece = (GOval) getElementAt(CELL_LENGTH * rX
						+ CELL_CENTER, CELL_LENGTH * rY + CELL_CENTER);
				piece.setFillColor(Color.black);
				pieceTracker[rY][rX] = BLACK;
				rX--;
			}
		}
	}

	private boolean pieceUp(int rX, int rY, boolean pTurn) {
		boolean piecePresent = false;
		if (rY == 0)
			return piecePresent;
		// at the top of the screen

		rY--;
		if (pTurn) { // white is placing
			while (pieceTracker[rY][rX] == BLACK) {
				if (rY == 0) break;
				rY--;
			}
			if (pieceTracker[rY][rX] == WHITE)
				piecePresent = true;
		} else { // black is placing
			while (pieceTracker[rY][rX] == WHITE) {
				if (rY == 0) break;
				rY--;
			}
			if (pieceTracker[rY][rX] == BLACK)
				piecePresent = true;
		}

		return piecePresent;
	}

	private void flipUp(int rX, int rY, boolean pTurn) {
		rY--;
		if (pTurn) {
			while (pieceTracker[rY][rX] == BLACK) {
				GOval piece = (GOval) getElementAt(CELL_LENGTH * rX
						+ CELL_CENTER, CELL_LENGTH * rY + CELL_CENTER);
				piece.setFillColor(Color.white);
				pieceTracker[rY][rX] = WHITE;
				rY--;
			}
		} else {
			while (pieceTracker[rY][rX] == WHITE) {
				GOval piece = (GOval) getElementAt(CELL_LENGTH * rX
						+ CELL_CENTER, CELL_LENGTH * rY + CELL_CENTER);
				piece.setFillColor(Color.black);
				pieceTracker[rY][rX] = BLACK;
				rY--;
			}
		}
	}

	private boolean pieceDown(int rX, int rY, boolean pTurn) {
		boolean piecePresent = false;
		if (rY == CELLS_PER_ROW - 1)
			return piecePresent;
		// at the bottom of the screen

		rY++;
		if (pTurn) { // white is placing
			while (pieceTracker[rY][rX] == BLACK) {
				if (rY == CELLS_PER_ROW - 1) break;
				rY++;
			}
			if (pieceTracker[rY][rX] == WHITE)
				piecePresent = true;
		} else { // black is placing
			while (pieceTracker[rY][rX] == WHITE) {
				if (rY == CELLS_PER_ROW - 1) break;
				rY++;
			}
			if (pieceTracker[rY][rX] == BLACK)
				piecePresent = true;
		}

		return piecePresent;
	}

	private void flipDown(int rX, int rY, boolean pTurn) {
		rY++;
		if (pTurn) {
			while (pieceTracker[rY][rX] == BLACK) {
				GOval piece = (GOval) getElementAt(CELL_LENGTH * rX
						+ CELL_CENTER, CELL_LENGTH * rY + CELL_CENTER);
				piece.setFillColor(Color.white);
				pieceTracker[rY][rX] = WHITE;
				rY++;
			}
		} else {
			while (pieceTracker[rY][rX] == WHITE) {
				GOval piece = (GOval) getElementAt(CELL_LENGTH * rX
						+ CELL_CENTER, CELL_LENGTH * rY + CELL_CENTER);
				piece.setFillColor(Color.black);
				pieceTracker[rY][rX] = BLACK;
				rY++;
			}
		}
	}

	private boolean pieceUpLeft(int rX, int rY, boolean pTurn) {
		boolean piecePresent = false;
		if (rX == 0 || rY == 0)
			return piecePresent;
		// at the upper-left edges of the screen
		rX--;
		rY--;
		
		if (pTurn) { // white is placing
			while (pieceTracker[rY][rX] == BLACK) {
				if (rX == 0 || rY == 0) break;
				rX--;
				rY--;
			}
			if (pieceTracker[rY][rX] == WHITE)
				piecePresent = true;
		} else { // black is placing
			while (pieceTracker[rY][rX] == WHITE) {
				if (rX == 0 || rY == 0) break;
				rX--;
				rY--;
			}
			if (pieceTracker[rY][rX] == BLACK)
				piecePresent = true;
		}

		return piecePresent;
	}
	
	private void flipUpLeft(int rX, int rY, boolean pTurn) {
		rX--;
		rY--;
		if (pTurn) {
			while (pieceTracker[rY][rX] == BLACK) {
				GOval piece = (GOval) getElementAt(CELL_LENGTH * rX
						+ CELL_CENTER, CELL_LENGTH * rY + CELL_CENTER);
				piece.setFillColor(Color.white);
				pieceTracker[rY][rX] = WHITE;
				rX--;
				rY--;
			}
		} else {
			while (pieceTracker[rY][rX] == WHITE) {
				GOval piece = (GOval) getElementAt(CELL_LENGTH * rX
						+ CELL_CENTER, CELL_LENGTH * rY + CELL_CENTER);
				piece.setFillColor(Color.black);
				pieceTracker[rY][rX] = BLACK;
				rX--;
				rY--;
			}
		}
	}
	
	private boolean pieceUpRight(int rX, int rY, boolean pTurn) {
		boolean piecePresent = false;
		if (rX == CELLS_PER_ROW - 1 || rY == 0)
			return piecePresent;
		// at the upper-right edges of the screen
		rX++;
		rY--;
		
		if (pTurn) { // white is placing
			while (pieceTracker[rY][rX] == BLACK) {
				if (rX == CELLS_PER_ROW - 1 || rY == 0) break;
				rX++;
				rY--;
			}
			if (pieceTracker[rY][rX] == WHITE)
				piecePresent = true;
		} else { // black is placing
			while (pieceTracker[rY][rX] == WHITE) {
				if (rX == CELLS_PER_ROW - 1 || rY == 0) break;
				rX++;
				rY--;
			}
			if (pieceTracker[rY][rX] == BLACK)
				piecePresent = true;
		}

		return piecePresent;
	}
	
	private void flipUpRight(int rX, int rY, boolean pTurn) {
		rX++;
		rY--;
		if (pTurn) {
			while (pieceTracker[rY][rX] == BLACK) {
				GOval piece = (GOval) getElementAt(CELL_LENGTH * rX
						+ CELL_CENTER, CELL_LENGTH * rY + CELL_CENTER);
				piece.setFillColor(Color.white);
				pieceTracker[rY][rX] = WHITE;
				rX++;
				rY--;
			}
		} else {
			while (pieceTracker[rY][rX] == WHITE) {
				GOval piece = (GOval) getElementAt(CELL_LENGTH * rX
						+ CELL_CENTER, CELL_LENGTH * rY + CELL_CENTER);
				piece.setFillColor(Color.black);
				pieceTracker[rY][rX] = BLACK;
				rX++;
				rY--;
			}
		}
	}
	
	private boolean pieceLowLeft(int rX, int rY, boolean pTurn) {
		boolean piecePresent = false;
		if (rX == 0 || rY == CELLS_PER_ROW - 1)
			return piecePresent;
		// at the upper-left edges of the screen
		rX--;
		rY++;
		
		if (pTurn) { // white is placing
			while (pieceTracker[rY][rX] == BLACK) {
				if (rX == 0 || rY == CELLS_PER_ROW - 1) break;
				rX--;
				rY++;
			}
			if (pieceTracker[rY][rX] == WHITE)
				piecePresent = true;
		} else { // black is placing
			while (pieceTracker[rY][rX] == WHITE) {
				if (rX == 0 || rY == CELLS_PER_ROW - 1) break;
				rX--;
				rY++;
			}
			if (pieceTracker[rY][rX] == BLACK)
				piecePresent = true;
		}

		return piecePresent;
	}
	
	private void flipLowLeft(int rX, int rY, boolean pTurn) {
		rX--;
		rY++;
		if (pTurn) {
			while (pieceTracker[rY][rX] == BLACK) {
				GOval piece = (GOval) getElementAt(CELL_LENGTH * rX
						+ CELL_CENTER, CELL_LENGTH * rY + CELL_CENTER);
				piece.setFillColor(Color.white);
				pieceTracker[rY][rX] = WHITE;
				rX--;
				rY++;
			}
		} else {
			while (pieceTracker[rY][rX] == WHITE) {
				GOval piece = (GOval) getElementAt(CELL_LENGTH * rX
						+ CELL_CENTER, CELL_LENGTH * rY + CELL_CENTER);
				piece.setFillColor(Color.black);
				pieceTracker[rY][rX] = BLACK;
				rX--;
				rY++;
			}
		}
	}
	
	private boolean pieceLowRight(int rX, int rY, boolean pTurn) {
		boolean piecePresent = false;
		if (rX == CELLS_PER_ROW - 1 || rY == CELLS_PER_ROW - 1)
			return piecePresent;
		// at the upper-left edges of the screen
		rX++;
		rY++;
		
		if (pTurn) { // white is placing
			while (pieceTracker[rY][rX] == BLACK) {
				if (rX == CELLS_PER_ROW - 1 || rY == CELLS_PER_ROW - 1) break;
				rX++;
				rY++;
			}
			if (pieceTracker[rY][rX] == WHITE)
				piecePresent = true;
		} else { // black is placing
			while (pieceTracker[rY][rX] == WHITE) {
				if (rX == CELLS_PER_ROW - 1 || rY == CELLS_PER_ROW - 1) break;
				rX++;
				rY++;
			}
			if (pieceTracker[rY][rX] == BLACK)
				piecePresent = true;
		}

		return piecePresent;
	}
	
	private void flipLowRight(int rX, int rY, boolean pTurn) {
		rX++;
		rY++;
		if (pTurn) {
			while (pieceTracker[rY][rX] == BLACK) {
				GOval piece = (GOval) getElementAt(CELL_LENGTH * rX
						+ CELL_CENTER, CELL_LENGTH * rY + CELL_CENTER);
				piece.setFillColor(Color.white);
				pieceTracker[rY][rX] = WHITE;
				rX++;
				rY++;
			}
		} else {
			while (pieceTracker[rY][rX] == WHITE) {
				GOval piece = (GOval) getElementAt(CELL_LENGTH * rX
						+ CELL_CENTER, CELL_LENGTH * rY + CELL_CENTER);
				piece.setFillColor(Color.black);
				pieceTracker[rY][rX] = BLACK;
				rX++;
				rY++;
			}
		}
	}
	
	private void createBoard(int appLength, int numCells) {
		setSize(appLength, appLength);

		for (int i = 0; i < numCells; i++) {
			for (int j = 0; j < numCells; j++) {
				CRect cell = new CRect(CELL_LENGTH * j, CELL_LENGTH * i,
						CELL_LENGTH, CELL_LENGTH);
				add(cell);
				cell.sendToBack();

				cell.setXCord(j);
				cell.setYCord(i);
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

		addPiece(topLeft.getX(), topLeft.getY(), true);
		addPiece(topRight.getX(), topRight.getY(), false);
		addPiece(bottomLeft.getX(), bottomLeft.getY(), false);
		addPiece(bottomRight.getX(), bottomRight.getY(), true);

		pieceTracker[topLeft.getYCord()][topLeft.getXCord()] = 1;
		pieceTracker[bottomRight.getYCord()][bottomRight.getXCord()] = 1;
		pieceTracker[bottomLeft.getYCord()][bottomLeft.getXCord()] = 2;
		pieceTracker[topRight.getYCord()][topRight.getXCord()] = 2;
	}

	private void addPiece(double x, double y, boolean playerColor) {
		GOval o = new GOval(x + OFFSET, y + OFFSET, PIECE_RADIUS * 2,
				PIECE_RADIUS * 2);
		o.setFilled(true);
		if (playerColor) {
			o.setFillColor(Color.white);
		} else {
			o.setFillColor(Color.black);
		}
		o.sendToFront();
		add(o);
	}

	private boolean playerTurn = true;
	private int[][] pieceTracker = new int[CELLS_PER_ROW][CELLS_PER_ROW];
	// 1 is white piece, 2 is black piece

}

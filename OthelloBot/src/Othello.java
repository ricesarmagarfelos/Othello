import java.awt.Color;

import acm.graphics.*;
import acm.program.*;
import utils.Consts;

import java.awt.event.MouseEvent;

public class Othello extends GraphicsProgram {

	public void init() {
		createBoard(Consts.APP_SIZE, Consts.CELLS_PER_ROW);
		setBackground(Color.green);
	}

	public void run() {
		addMouseListeners();
	}

	public void mouseClicked(MouseEvent e) {
		try {
			CRect c = (CRect) getElementAt(e.getX(), e.getY());

			if (!c.isClicked() && legalMove(c.getXCord(), c.getYCord(), playerTurn)) {
				addPiece(c.getX(), c.getY(), playerTurn);
				if (playerTurn) {
					pieceTracker[c.getYCord()][c.getXCord()] = Consts.WHITE;
				} else {
					pieceTracker[c.getYCord()][c.getXCord()] = Consts.BLACK;
				}
				flip(c.getXCord(), c.getYCord(), playerTurn);

				playerTurn = !playerTurn;
				c.setClicked(true);
			}

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
		if (rX == Consts.CELLS_PER_ROW - 1)
			return piecePresent;
		// at the right of the screen

		rX++;
		if (pTurn && pieceTracker[rY][rX] == Consts.WHITE) {
			return piecePresent;
		}
		if (!pTurn && pieceTracker[rY][rX] == Consts.BLACK){
			return piecePresent;
		}
		if (pTurn) { // Consts.WHITE is placing
			while (pieceTracker[rY][rX] == Consts.BLACK) {
				if (rX == Consts.CELLS_PER_ROW - 1) break;
				rX++;
			}
			if (pieceTracker[rY][rX] == Consts.WHITE)
				piecePresent = true;
		} else { // Consts.BLACK is placing
			while (pieceTracker[rY][rX] == Consts.WHITE) {
				if (rX == Consts.CELLS_PER_ROW - 1) break;
				rX++;
			}
			if (pieceTracker[rY][rX] == Consts.BLACK)
				piecePresent = true;
		}

		return piecePresent;
	}

	private void flipRight(int rX, int rY, boolean pTurn) {
		rX++;
		if (pTurn) {
			while (pieceTracker[rY][rX] == Consts.BLACK) {
				GOval piece = (GOval) getElementAt(Consts.CELL_LENGTH * rX
						+ Consts.CELL_CENTER, Consts.CELL_LENGTH * rY + Consts.CELL_CENTER);
				piece.setFillColor(Color.WHITE);
				pieceTracker[rY][rX] = Consts.WHITE;
				rX++;
			}
		} else {
			while (pieceTracker[rY][rX] == Consts.WHITE) {
				GOval piece = (GOval) getElementAt(Consts.CELL_LENGTH * rX
						+ Consts.CELL_CENTER, Consts.CELL_LENGTH * rY + Consts.CELL_CENTER);
				piece.setFillColor(Color.BLACK);
				pieceTracker[rY][rX] = Consts.BLACK;
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
		if (pTurn && pieceTracker[rY][rX] == Consts.WHITE) {
			return piecePresent;
		}
		if (!pTurn && pieceTracker[rY][rX] == Consts.BLACK){
			return piecePresent;
		}
		if (pTurn) { // Consts.WHITE is placing
			while (pieceTracker[rY][rX] == Consts.BLACK) {
				if (rX == 0) break;
				rX--;
			}
			if (pieceTracker[rY][rX] == Consts.WHITE)
				piecePresent = true;
		} else { // Consts.BLACK is placing
			while (pieceTracker[rY][rX] == Consts.WHITE) {
				if (rX == 0) break;
				rX--;
			}
			if (pieceTracker[rY][rX] == Consts.BLACK)
				piecePresent = true;
		}

		return piecePresent;
	}

	private void flipLeft(int rX, int rY, boolean pTurn) {
		rX--;
		if (pTurn) {
			while (pieceTracker[rY][rX] == Consts.BLACK) {
				GOval piece = (GOval) getElementAt(Consts.CELL_LENGTH * rX
						+ Consts.CELL_CENTER, Consts.CELL_LENGTH * rY + Consts.CELL_CENTER);
				piece.setFillColor(Color.WHITE);
				pieceTracker[rY][rX] = Consts.WHITE;
				rX--;
			}
		} else {
			while (pieceTracker[rY][rX] == Consts.WHITE) {
				GOval piece = (GOval) getElementAt(Consts.CELL_LENGTH * rX
						+ Consts.CELL_CENTER, Consts.CELL_LENGTH * rY + Consts.CELL_CENTER);
				piece.setFillColor(Color.BLACK);
				pieceTracker[rY][rX] = Consts.BLACK;
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
		if (pTurn && pieceTracker[rY][rX] == Consts.WHITE) {
			return piecePresent;
		}
		if (!pTurn && pieceTracker[rY][rX] == Consts.BLACK){
			return piecePresent;
		}
		if (pTurn) { // Consts.WHITE is placing
			while (pieceTracker[rY][rX] == Consts.BLACK) {
				if (rY == 0) break;
				rY--;
			}
			if (pieceTracker[rY][rX] == Consts.WHITE)
				piecePresent = true;
		} else { // Consts.BLACK is placing
			while (pieceTracker[rY][rX] == Consts.WHITE) {
				if (rY == 0) break;
				rY--;
			}
			if (pieceTracker[rY][rX] == Consts.BLACK)
				piecePresent = true;
		}

		return piecePresent;
	}

	private void flipUp(int rX, int rY, boolean pTurn) {
		rY--;
		if (pTurn) {
			while (pieceTracker[rY][rX] == Consts.BLACK) {
				GOval piece = (GOval) getElementAt(Consts.CELL_LENGTH * rX
						+ Consts.CELL_CENTER, Consts.CELL_LENGTH * rY + Consts.CELL_CENTER);
				piece.setFillColor(Color.WHITE);
				pieceTracker[rY][rX] = Consts.WHITE;
				rY--;
			}
		} else {
			while (pieceTracker[rY][rX] == Consts.WHITE) {
				GOval piece = (GOval) getElementAt(Consts.CELL_LENGTH * rX
						+ Consts.CELL_CENTER, Consts.CELL_LENGTH * rY + Consts.CELL_CENTER);
				piece.setFillColor(Color.BLACK);
				pieceTracker[rY][rX] = Consts.BLACK;
				rY--;
			}
		}
	}

	private boolean pieceDown(int rX, int rY, boolean pTurn) {
		boolean piecePresent = false;
		if (rY == Consts.CELLS_PER_ROW - 1)
			return piecePresent;
		// at the bottom of the screen

		rY++;
		if (pTurn && pieceTracker[rY][rX] == Consts.WHITE) {
			return piecePresent;
		}
		if (!pTurn && pieceTracker[rY][rX] == Consts.BLACK){
			return piecePresent;
		}
		if (pTurn) { // Consts.WHITE is placing
			while (pieceTracker[rY][rX] == Consts.BLACK) {
				if (rY == Consts.CELLS_PER_ROW - 1) break;
				rY++;
			}
			if (pieceTracker[rY][rX] == Consts.WHITE)
				piecePresent = true;
		} else { // Consts.BLACK is placing
			while (pieceTracker[rY][rX] == Consts.WHITE) {
				if (rY == Consts.CELLS_PER_ROW - 1) break;
				rY++;
			}
			if (pieceTracker[rY][rX] == Consts.BLACK)
				piecePresent = true;
		}

		return piecePresent;
	}

	private void flipDown(int rX, int rY, boolean pTurn) {
		rY++;
		if (pTurn) {
			while (pieceTracker[rY][rX] == Consts.BLACK) {
				GOval piece = (GOval) getElementAt(Consts.CELL_LENGTH * rX
						+ Consts.CELL_CENTER, Consts.CELL_LENGTH * rY + Consts.CELL_CENTER);
				piece.setFillColor(Color.WHITE);
				pieceTracker[rY][rX] = Consts.WHITE;
				rY++;
			}
		} else {
			while (pieceTracker[rY][rX] == Consts.WHITE) {
				GOval piece = (GOval) getElementAt(Consts.CELL_LENGTH * rX
						+ Consts.CELL_CENTER, Consts.CELL_LENGTH * rY + Consts.CELL_CENTER);
				piece.setFillColor(Color.BLACK);
				pieceTracker[rY][rX] = Consts.BLACK;
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
		if (pTurn && pieceTracker[rY][rX] == Consts.WHITE) {
			return piecePresent;
		}
		if (!pTurn && pieceTracker[rY][rX] == Consts.BLACK){
			return piecePresent;
		}
		if (pTurn) { // Consts.WHITE is placing
			while (pieceTracker[rY][rX] == Consts.BLACK) {
				if (rX == 0 || rY == 0) break;
				rX--;
				rY--;
			}
			if (pieceTracker[rY][rX] == Consts.WHITE)
				piecePresent = true;
		} else { // Consts.BLACK is placing
			while (pieceTracker[rY][rX] == Consts.WHITE) {
				if (rX == 0 || rY == 0) break;
				rX--;
				rY--;
			}
			if (pieceTracker[rY][rX] == Consts.BLACK)
				piecePresent = true;
		}

		return piecePresent;
	}
	
	private void flipUpLeft(int rX, int rY, boolean pTurn) {
		rX--;
		rY--;
		if (pTurn) {
			while (pieceTracker[rY][rX] == Consts.BLACK) {
				GOval piece = (GOval) getElementAt(Consts.CELL_LENGTH * rX
						+ Consts.CELL_CENTER, Consts.CELL_LENGTH * rY + Consts.CELL_CENTER);
				piece.setFillColor(Color.WHITE);
				pieceTracker[rY][rX] = Consts.WHITE;
				rX--;
				rY--;
			}
		} else {
			while (pieceTracker[rY][rX] == Consts.WHITE) {
				GOval piece = (GOval) getElementAt(Consts.CELL_LENGTH * rX
						+ Consts.CELL_CENTER, Consts.CELL_LENGTH * rY + Consts.CELL_CENTER);
				piece.setFillColor(Color.BLACK);
				pieceTracker[rY][rX] = Consts.BLACK;
				rX--;
				rY--;
			}
		}
	}
	
	private boolean pieceUpRight(int rX, int rY, boolean pTurn) {
		boolean piecePresent = false;
		if (rX == Consts.CELLS_PER_ROW - 1 || rY == 0)
			return piecePresent;
		// at the upper-right edges of the screen
		rX++;
		rY--;
		if (pTurn && pieceTracker[rY][rX] == Consts.WHITE) {
			return piecePresent;
		}
		if (!pTurn && pieceTracker[rY][rX] == Consts.BLACK){
			return piecePresent;
		}
		if (pTurn) { // Consts.WHITE is placing
			while (pieceTracker[rY][rX] == Consts.BLACK) {
				if (rX == Consts.CELLS_PER_ROW - 1 || rY == 0) break;
				rX++;
				rY--;
			}
			if (pieceTracker[rY][rX] == Consts.WHITE)
				piecePresent = true;
		} else { // Consts.BLACK is placing
			while (pieceTracker[rY][rX] == Consts.WHITE) {
				if (rX == Consts.CELLS_PER_ROW - 1 || rY == 0) break;
				rX++;
				rY--;
			}
			if (pieceTracker[rY][rX] == Consts.BLACK)
				piecePresent = true;
		}

		return piecePresent;
	}
	
	private void flipUpRight(int rX, int rY, boolean pTurn) {
		rX++;
		rY--;
		if (pTurn) {
			while (pieceTracker[rY][rX] == Consts.BLACK) {
				GOval piece = (GOval) getElementAt(Consts.CELL_LENGTH * rX
						+ Consts.CELL_CENTER, Consts.CELL_LENGTH * rY + Consts.CELL_CENTER);
				piece.setFillColor(Color.WHITE);
				pieceTracker[rY][rX] = Consts.WHITE;
				rX++;
				rY--;
			}
		} else {
			while (pieceTracker[rY][rX] == Consts.WHITE) {
				GOval piece = (GOval) getElementAt(Consts.CELL_LENGTH * rX
						+ Consts.CELL_CENTER, Consts.CELL_LENGTH * rY + Consts.CELL_CENTER);
				piece.setFillColor(Color.BLACK);
				pieceTracker[rY][rX] = Consts.BLACK;
				rX++;
				rY--;
			}
		}
	}
	
	private boolean pieceLowLeft(int rX, int rY, boolean pTurn) {
		boolean piecePresent = false;
		if (rX == 0 || rY == Consts.CELLS_PER_ROW - 1)
			return piecePresent;
		// at the lower-left edges of the screen
		rX--;
		rY++;
		if (pTurn && pieceTracker[rY][rX] == Consts.WHITE) {
			return piecePresent;
		}
		if (!pTurn && pieceTracker[rY][rX] == Consts.BLACK){
			return piecePresent;
		}
		if (pTurn) { // Consts.WHITE is placing
			while (pieceTracker[rY][rX] == Consts.BLACK) {
				if (rX == 0 || rY == Consts.CELLS_PER_ROW - 1) break;
				rX--;
				rY++;
			}
			if (pieceTracker[rY][rX] == Consts.WHITE)
				piecePresent = true;
		} else { // Consts.BLACK is placing
			while (pieceTracker[rY][rX] == Consts.WHITE) {
				if (rX == 0 || rY == Consts.CELLS_PER_ROW - 1) break;
				rX--;
				rY++;
			}
			if (pieceTracker[rY][rX] == Consts.BLACK)
				piecePresent = true;
		}

		return piecePresent;
	}
	
	private void flipLowLeft(int rX, int rY, boolean pTurn) {
		rX--;
		rY++;
		if (pTurn) {
			while (pieceTracker[rY][rX] == Consts.BLACK) {
				GOval piece = (GOval) getElementAt(Consts.CELL_LENGTH * rX
						+ Consts.CELL_CENTER, Consts.CELL_LENGTH * rY + Consts.CELL_CENTER);
				piece.setFillColor(Color.WHITE);
				pieceTracker[rY][rX] = Consts.WHITE;
				rX--;
				rY++;
			}
		} else {
			while (pieceTracker[rY][rX] == Consts.WHITE) {
				GOval piece = (GOval) getElementAt(Consts.CELL_LENGTH * rX
						+ Consts.CELL_CENTER, Consts.CELL_LENGTH * rY + Consts.CELL_CENTER);
				piece.setFillColor(Color.BLACK);
				pieceTracker[rY][rX] = Consts.BLACK;
				rX--;
				rY++;
			}
		}
	}
	
	private boolean pieceLowRight(int rX, int rY, boolean pTurn) {
		boolean piecePresent = false;
		if (rX == Consts.CELLS_PER_ROW - 1 || rY == Consts.CELLS_PER_ROW - 1)
			return piecePresent;
		// at the lower-right edges of the screen
		rX++;
		rY++;
		if (pTurn && pieceTracker[rY][rX] == Consts.WHITE) {
			return piecePresent;
		}
		if (!pTurn && pieceTracker[rY][rX] == Consts.BLACK){
			return piecePresent;
		}
		
		if (pTurn) { // Consts.WHITE is placing
			while (pieceTracker[rY][rX] == Consts.BLACK) {
				if (rX == Consts.CELLS_PER_ROW - 1 || rY == Consts.CELLS_PER_ROW - 1) break;
				rX++;
				rY++;
			}
			if (pieceTracker[rY][rX] == Consts.WHITE)
				piecePresent = true;
		} else { // Consts.BLACK is placing
			while (pieceTracker[rY][rX] == Consts.WHITE) {
				if (rX == Consts.CELLS_PER_ROW - 1 || rY == Consts.CELLS_PER_ROW - 1) break;
				rX++;
				rY++;
			}
			if (pieceTracker[rY][rX] == Consts.BLACK)
				piecePresent = true;
		}

		return piecePresent;
	}
	
	private void flipLowRight(int rX, int rY, boolean pTurn) {
		rX++;
		rY++;
		if (pTurn) {
			while (pieceTracker[rY][rX] == Consts.BLACK) {
				GOval piece = (GOval) getElementAt(Consts.CELL_LENGTH * rX
						+ Consts.CELL_CENTER, Consts.CELL_LENGTH * rY + Consts.CELL_CENTER);
				piece.setFillColor(Color.WHITE);
				pieceTracker[rY][rX] = Consts.WHITE;
				rX++;
				rY++;
			}
		} else {
			while (pieceTracker[rY][rX] == Consts.WHITE) {
				GOval piece = (GOval) getElementAt(Consts.CELL_LENGTH * rX
						+ Consts.CELL_CENTER, Consts.CELL_LENGTH * rY + Consts.CELL_CENTER);
				piece.setFillColor(Color.BLACK);
				pieceTracker[rY][rX] = Consts.BLACK;
				rX++;
				rY++;
			}
		}
	}
	
	private void createBoard(int appLength, int numCells) {
		setSize(appLength, appLength);

		for (int i = 0; i < numCells; i++) {
			for (int j = 0; j < numCells; j++) {
				CRect cell = new CRect(Consts.CELL_LENGTH * j, Consts.CELL_LENGTH * i,
						Consts.CELL_LENGTH, Consts.CELL_LENGTH);
				add(cell);
				cell.sendToBack();

				cell.setXCord(j);
				cell.setYCord(i);
			}
		}

		addStartingFour();
	}

	private void addStartingFour() {
		CRect topLeft = (CRect) getElementAt(Consts.CENTER - 1, Consts.CENTER - 1);
		CRect topRight = (CRect) getElementAt(Consts.CENTER + 1, Consts.CENTER - 1);
		CRect bottomLeft = (CRect) getElementAt(Consts.CENTER - 1, Consts.CENTER + 1);
		CRect bottomRight = (CRect) getElementAt(Consts.CENTER + 1, Consts.CENTER + 1);

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
		GOval o = new GOval(x + Consts.OFFSET, y + Consts.OFFSET, Consts.PIECE_RADIUS * 2,
				Consts.PIECE_RADIUS * 2);
		o.setFilled(true);
		if (playerColor) {
			o.setFillColor(Color.WHITE);
		} else {
			o.setFillColor(Color.BLACK);
		}
		o.sendToFront();
		add(o);
	}
	
	private boolean legalMove(double clickedC, double clickedY, boolean pT) {
		int x = (int) clickedC;
		int y = (int) clickedY;
		for (int i = 0; i < 8; i++) {
			switch (i) {
			case 0:
				if (pieceUp(x, y, pT)) return true;
				break;
			case 1:
				if (pieceRight(x, y, pT)) return true;
				break;
			case 2:
				if (pieceDown(x, y, pT)) return true;
				break;
			case 3:
				if (pieceLeft(x, y, pT)) return true;
				break;
			case 4:
				if (pieceUpLeft(x, y, pT)) return true;
				break;
			case 5:
				if (pieceUpRight(x, y, pT)) return true;
				break;
			case 6:
				if (pieceLowRight(x, y, pT)) return true;
				break;
			case 7:
				if (pieceLowLeft(x, y, pT)) return true;
				break;
			}
		}
		
		return false;
		
	}

	private boolean playerTurn = true;
	private int[][] pieceTracker = new int[Consts.CELLS_PER_ROW][Consts.CELLS_PER_ROW];

}

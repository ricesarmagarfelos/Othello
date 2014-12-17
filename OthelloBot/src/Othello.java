import java.awt.Color;

import acm.graphics.*;
import acm.program.*;

import java.awt.event.MouseEvent;

public class Othello extends GraphicsProgram {

	private static final int APP_SIZE = 640;

	private static final int CELLS_PER_ROW = 8;

	private static final int CELL_LENGTH = 80;

	private static final int PIECE_RADIUS = 30;

	private static final int PIECE_OFFSET = 10;

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
				flipRight(c.getXCord() + 1, c.getYCord(), playerTurn);
				playerTurn = !playerTurn;
			}

			c.setClicked(true);

		} catch (ClassCastException c) {
			// player clicked on a piece
		}

	}

	private void flipRight(int rX, int rY, boolean pTurn) {
		if (pTurn) {
			while (pieceTracker[rY][rX] == BLACK) {
				GOval piece = (GOval) getElementAt(CELL_LENGTH * rX
						+ CELL_CENTER, CELL_LENGTH * rY + CELL_CENTER);
				//piece.setFillColor(Color.white);
				remove(piece);

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
		GOval o = new GOval(x + PIECE_OFFSET, y + PIECE_OFFSET,
				PIECE_RADIUS * 2, PIECE_RADIUS * 2);
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

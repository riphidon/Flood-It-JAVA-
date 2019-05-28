package floodGame;

import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class FloodFrame {
	private FloodBox floodGrid [][];
	private int size;
	private int nbColor;
	private int currentColor;
	private int newColor;
	private boolean hint;
	ArrayList<Integer> possibleColors = new ArrayList<Integer>(8);
	private JFrame frame;
	private int moveCounter = 0;
	
	
	public int getMoveCounter() {
		return moveCounter;
	}

	public void setMoveCounter(int moveCounter) {
		this.moveCounter = moveCounter;
	}

	public FloodFrame(int size, int color) {
		this.size = size;
		floodGrid = new FloodBox[size][size];
		for (int i = 0; i < floodGrid.length; i++) {
			for (int j = 0; j < floodGrid.length; j++) {
				floodGrid[i][j] = new FloodBox(i, j);
			}
		}
		colorMyBox(color);
	}
	
	public void colorMyBox(int color) {
		int min = 1;
		int range = color - min+1;
		for (int i = 0; i < floodGrid.length; i++) {
			for (int j = 0; j < floodGrid.length; j++) {
					floodGrid[i][j].setColor((int)(Math.random()*range) + min);
			}
		}

	}
	
	
	
	public FloodBox [][] getMyBoxes() {
		return floodGrid;
	}
	
	public FloodBox getMyBox(int row, int col) {
		return floodGrid[row][col];
	}
	
	
	public void CheckAndSetColor(int row, int col) {
		try {
			if (floodGrid[row][col ].getColor() == currentColor) {
				floodGrid[row ][col].setColor(newColor) ;
				expand(row, col);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
		
	
	
	public void expand(int row, int col) {
			
						CheckAndSetColor(row-1, col);
						CheckAndSetColor(row, col-1);
						CheckAndSetColor(row, col+1);
						CheckAndSetColor(row+1, col);	

	}
	
	public void winMessage() {
		JOptionPane.showMessageDialog(frame, "YOU LOOSE");
	};
	
	public void playBox(int row, int col) {
		if (floodGrid[row][col].getColor() != floodGrid[0][0].getColor()) {
			currentColor = floodGrid[0][0].getColor();
			newColor = floodGrid[row][col].getColor();
			floodGrid[0][0].setColor(newColor);
			expand(0, 0);
			resetCheck();
			possibleColors.clear();
			moveCounter++;
			setMoveCounter(moveCounter);
			
		}
		
	}
	
	public void getHint(int hrow, int hcol, int color) {
		
		try {
			if (floodGrid[hrow][hcol].isCheck()) {
				return;
			}
			if (floodGrid[hrow][hcol ].getColor() != color && (floodGrid[hrow][hcol].getColor() != floodGrid[0][0].getColor())) {
					floodGrid[hrow][hcol].setCheck(true);
					if (!possibleColors.contains(floodGrid[hrow][hcol ].getColor())) {
						possibleColors.add(floodGrid[hrow][hcol].getColor());
					}
			}else {
				floodGrid[hrow][hcol].setCheck(true);
				checkMove(hrow, hcol, color);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public void checkMove(int row, int col, int color) {
		getHint(row - 1, col, color);
		getHint(row, col -1, color);
		getHint(row, col + 1, color);
		getHint(row + 1, col, color);
	}

	
	public void resetCheck() {
		for (int i = 0; i < floodGrid.length; i++) {
			for ( int j = 0; j < floodGrid.length; j++) {
				floodGrid[i][j].setCheck(false);
			}
		}
	}

	public ArrayList<Integer> getPossibleColors() {
		return possibleColors;
	}

	public void setPossibleColors(ArrayList<Integer> possibleColors) {
		this.possibleColors = possibleColors;
	}
	
	public void win (FloodBox [][] floodGrid) {
		for (int i = 0; i < floodGrid.length; i++) {
			for (int j = 0; j < floodGrid.length; j++) {
				if (floodGrid[i][j].getColor() != floodGrid[0][0].getColor()) {
					return;
				}
				
			}
		}
		JOptionPane.showMessageDialog(frame, "YOU WIN");
		setMoveCounter(0);
	}

}

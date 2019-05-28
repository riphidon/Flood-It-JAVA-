package floodGame;

import java.awt.Color;

import javax.swing.JButton;


public class FloodBox extends JButton {
	private int row;
	private int col;
	private int color;
	private boolean blank;
	private boolean check;
	public final static Color [] COLORS = { Color.white, Color.red, Color.blue, Color.pink, Color.yellow, Color.magenta, Color.orange, Color.green, Color.black};

	public FloodBox(int row, int col) {
		this.row = row;
		this.col = col;
		setColor(0);
	}

	public int getColor() {
		return color;
	}

	public void setColor(int color) {
		this.color = color;
		setBackground(COLORS[color]);
	}
	
	public boolean isBlank() {
		return blank;
	}

	public void setBlank(boolean blank) {
		this.blank = blank;
	}
	
	public boolean isCheck() {
		return check;
	}

	public void setCheck(boolean check) {
		this.check = check;
	}
	
}

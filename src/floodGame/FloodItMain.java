package floodGame;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;


import javax.swing.ComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class FloodItMain {

	private JFrame frame;
	ArrayList<Color> array = new ArrayList<Color>();
	ArrayList<JButton> colorHints = new ArrayList<JButton>();
	private FloodBox [][] myFlood;
	private FloodFrame floodIt;
	String [] nbColors = {"3", "4", "5", "6", "7", "8"};
	private int nbColorPicked;
	String [] gameSize = {"2x2", "6x6", "14x14", "18x18", "22x22", "26x26" };
	private int size;
	private int altSize = 0;
	private String sized;
	private String currentSizeSelection;
	private String currentColorSelection;
	private int altColor = 0;
	private int color;
	private String colored;
	private int moveCounter;
	
	int coloredBox0;
	int coloredBox;
	int [] two = {0, 0, 1, 2, 2, 3, 4, 4};
	int [] six = {0 ,0, 7, 7, 8, 10, 12, 14};
	int [] ten = {0, 0, 8, 11, 14, 17, 20, 23};
	int [] fourteen = {0, 0, 12, 16, 20, 25, 29, 33};
	int [] eighteen = {0, 0, 16, 21, 26, 32, 37, 42};
	int [] twentyTwo = {0, 0, 19, 26, 32, 39, 45, 52};
	int [] twentSix = {0, 0, 23, 30, 38, 46, 54, 61};
	private int moves;
	private JLabel moveField;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FloodItMain window = new FloodItMain();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public FloodItMain() {
		frame = new JFrame();
		frame.setBounds(100, 100, 800, 800);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		moves = 7;
		size = 6;
		currentSizeSelection = gameSize[1];
		color = 3;
		currentColorSelection = nbColors[0];
		floodIt = new FloodFrame(size, color);
		myFlood = floodIt.getMyBoxes();
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		// new game button
				JButton btnNewButton = new JButton("New Game");
				btnNewButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						frame.getContentPane().removeAll();	
						if (altSize > 0){
							size = altSize;
						}
						if (altColor > 0) {
							color = altColor;
						}
						
						if (altSize==2) {
							moves = two[altColor-1];
						}
						if (altSize==6) {
							moves = six[altColor-1];
						}
						if (altSize==10) {
							moves = ten[altColor-1];
						}
						if (altSize==14) {
							moves = fourteen[altColor-1];
						}
						if (altSize==18) {
							moves = eighteen[altColor-1];
						}
						if (altSize==22) {
							moves = twentyTwo[altColor-1];
						}
						if (altSize==26) {
							moves = twentSix[altColor-1];
						}
						
						floodIt = new FloodFrame(size, color);
						myFlood = floodIt.getMyBoxes();
						moveCounter = 0;
						initialize();
						frame.revalidate();
						frame.repaint();
						
					}
					
				});
				btnNewButton.setBounds(0, 0, 135, 25);
				frame.getContentPane().add(btnNewButton);
		
		// size selection display
		JLabel sizes = new JLabel("Size");
		sizes.setBounds(12, 70, 38, 15);
		frame.getContentPane().add(sizes);
		
		JComboBox sizeSelect = new JComboBox(gameSize);
		sizeSelect.setSelectedItem(currentSizeSelection);
		sizeSelect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sized = (String)sizeSelect.getSelectedItem();
				currentSizeSelection = sized;
				String sizeString = "";
				if (sized.length() == 3) {
					sizeString = sized.substring(0, 1);
				}else {
					sizeString = sized.substring(0, 2);
				}
				altSize = Integer.parseInt(sizeString);
				
				
			}
		});
		frame.getContentPane().add(sizeSelect);
		sizeSelect.setBounds(52, 65, 83, 25);
		
		
		// color selection display
		JLabel colors = new JLabel("Colors");
		colors.setBounds(12, 97, 52, 37);
		frame.getContentPane().add(colors);
		
		JComboBox colorSelect = new JComboBox(nbColors);
		colorSelect.setSelectedItem(colored);
		colorSelect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				colored = (String)colorSelect.getSelectedItem();
				
				altColor = Integer.parseInt(colored);
				color = altColor;
			}
		});
		colorSelect.setBounds(74, 102, 61, 24);
		frame.getContentPane().add(colorSelect);
		
		
		
		
		
		
		JLabel moveField = new JLabel(moveCounter +" / "+moves);
		moveField.setBounds(85, 37, 50, 19);
		frame.getContentPane().add(moveField);
		
		JButton hint = new JButton("Cheat");
		hint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				floodIt.getHint(0, 0, floodIt.getMyBoxes()[0][0].getColor());
				ArrayList<Integer> possibleColor = floodIt.getPossibleColors();
				
				System.out.println(colorHints.size());
				if (colorHints.size() != 0) {
					for (int i = 0; i < colorHints.size(); i++) {
						frame.getContentPane().remove(colorHints.get(i));
					}
					frame.revalidate();
					frame.repaint();
				}
				
				for (int i = 0; i < possibleColor.size(); i++) {
					colorHints.add(new JButton());
					colorHints.get(i).setBounds(18, i*30+170, 30, 30);
					colorHints.get(i).setBackground(FloodBox.COLORS[possibleColor.get(i)]); 
					frame.getContentPane().add(colorHints.get(i));
					
				}
				frame.revalidate();
				frame.repaint();
				System.out.println(colorHints.size());
				possibleColor.clear();
			}
			
		});
		
		hint.setBounds(18, 146, 117, 25);
		frame.getContentPane().add(hint);
		
		//Move counter
		JLabel moveLabel = new JLabel("Moves");
		moveLabel.setBounds(12, 37, 61, 15);
		frame.getContentPane().add(moveLabel);
		
		
		
		
		
		
		
		for (int i = 0; i < size; i++) {
			final int row = i;
			for (int j = 0; j < size; j++) {
				final int col = j;
				myFlood[i][j].setBounds(i*30+200, j*30+25, 30, 30);
				myFlood[i][j].addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						floodIt.playBox(row,col);
						floodIt.win(myFlood);
						moveCounter = floodIt.getMoveCounter();
						System.out.println(moveCounter);
						moveField.setText(moveCounter +" / "+moves);
						if (moveCounter>moves) {
							JOptionPane.showMessageDialog(frame, "YOU LOOSE");
						}
						
					}
					
				});
				frame.getContentPane().add(myFlood[i][j]);
				
			}
			
		}
		
	}
	
	
}

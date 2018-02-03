package battleships_view;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;
/**
 * This is the PlayerMap class that will create grids for player 1 and player 2
 * @author Abdassami Alvi
 *
 */
public class PlayerMap extends JPanel  {

	public List<JButton> playerCoordinate;

	/**
	 * this creates an array list of JButtons that contains the value of the grid
	 * @param value is the value of the coordinates passed from the model indirectly
	 */
	public PlayerMap(String[][] value) {
		super.setLayout(new GridLayout(0, 10));
		playerCoordinate =  new ArrayList<JButton>();
		JButton coordinate = new JButton();
		int row;
		int column = 0;
		for(row = 0; row < 10; row++){
			for(column = 0; column < 10; column++){

				coordinate = new JButton(value[row][column]);
				coordinate.setActionCommand("(" + row + "," + column + ")");
				
				coordinate.setText("");
				
				if(value[row][column] == "~"){
					coordinate.setBackground(Color.cyan);
				}
				if(value[row][column] == "O"){
					coordinate.setBackground(Color.green);
				}
				if(value[row][column] == "*"){
					coordinate.setBackground(Color.white);
				}
				if(value[row][column] == "X"){
					coordinate.setBackground(Color.red);
				}
				this.playerCoordinate.add(coordinate);
				super.add(coordinate);
				
			}
		}
	}
	
	/**
	 * 
	 * this is the player button listener of type void
	 * it listens for the button to be clicked
	 * @param l is an action listener for the button
	 */
	public void addPlayerActionListener(ActionListener l) {
		for(JButton coordinate : this.playerCoordinate) {
			
			coordinate.addActionListener(l);
		}
	}
	/**
	 * 
	 * this is the player mouse listener of type void
	 * it listens for the mouse over the grid
	 * @param l is an mouse listener for the button
	 */
	public void addPlayerMouseListener(MouseListener l) {
		
		for(JButton coordinate : this.playerCoordinate) {
			coordinate.addMouseListener(l);
		}
	}

}

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
 * This is the CPUMap class that will create grids for the cpu
 * @author Abdassami Alvi
 *
 */
public class CPUMap extends JPanel  {

	public List<JButton> cpuCoordinate;
	JButton coordinate = new JButton();
	/**
	 * this creates an array list of JButtons that contains the value of the grid
	 * @param value is the value of the coordinates passed from the model indirectly
	 */
	public CPUMap(String[][] value) {
		super.setLayout(new GridLayout(0, 10));
		cpuCoordinate =  new ArrayList<JButton>();

		
		int row;
		int column = 0;
		for(row = 0; row < 10; row++){
			for(column = 0; column < 10; column++){

				
				coordinate = new JButton(value[row][column]);
				coordinate.setActionCommand("(" + row + "," + column + ")");
				coordinate.setText("");
				if(value[row][column] == "O" || value[row][column] == "~"){
					coordinate.setBackground(Color.cyan);
				}
				
				this.cpuCoordinate.add(coordinate);
				super.add(coordinate);
				
			}
		}
	}
	
	/**
	 * 
	 * this is the cpu mouse listener of type void
	 * it listens for the mouse over the grid
	 * @param l is an mouse listener for the button
	 */
	public void addCPUMouseListener(MouseListener l) {
		for(JButton coordinate : this.cpuCoordinate) {
			coordinate.addMouseListener(l);
		}
	}

	/**
	 * 
	 * this is the cpu button listener of type void
	 * it listens for the button to be clicked
	 * @param listenForShootButton is an action listener for the button
	 */
	public void addShootListener(ActionListener listenForShootButton) {
		for(JButton coordinate : this.cpuCoordinate) {
			coordinate.addActionListener(listenForShootButton);
		}
		
	}

}

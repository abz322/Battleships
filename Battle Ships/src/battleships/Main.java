package battleships;

import javax.swing.JFrame;

import battleships_controller.Battleships_Controller;
import battleships_model.Battleships_Model;
import battleships_view.Battleships_View;
import battleships_view.CPUMap;
import battleships_view.PlayerMap;

public class Main {


	/**
	 * @param args
	 */
	public static void main(String[] args) {

		Battleships_Model theModel = new Battleships_Model();
		
		PlayerMap pm = new PlayerMap(theModel.getCoordinate(theModel.getPlayerboard()));
		
		PlayerMap pm2 = new PlayerMap(theModel.getCoordinate(theModel.getPlayer2board()));
		
		CPUMap cm = new CPUMap(theModel.getCoordinate(theModel.getCPUboard()));
		
		Battleships_View theView = new Battleships_View(pm, cm, pm2);

		new Battleships_Controller(theModel,theView);
		
		theView.setVisible(true);

		theView.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}

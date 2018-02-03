package battleships_controller;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JButton;
import battleships_model.Battleships_Model;
import battleships_view.Battleships_View;

/**
 * This is the Controller class that will handle events between the View and the Model
 * @author Abdassami Alvi
 *
 */
public class Battleships_Controller {

	private final Battleships_Model theModel;
	private final Battleships_View theView;
	
	
	/**
	 * This is the constructor for the Battleships_Controller class
	 * it executes all the action and mouse listeners to implement the game functions
	 * 
	 * @param theModel is the model that will implement all the functions of the game
	 * @param theView is the view that will display the results of after functions are implemented
	 */
	public Battleships_Controller(final Battleships_Model theModel,  final Battleships_View theView){

		this.theModel = theModel;
		this.theView = theView;
		
		theView.setA(theModel.getAircraftCarrier().getUnits());
		theView.setB(theModel.getBattleShip().getUnits());
		theView.setD(theModel.getDestroyer1().getUnits() + theModel.getDestroyer2().getUnits());
		theView.setP(theModel.getPatrolBoat().getUnits());

		
		this.theView.setVisible(true);
		
		this.theView.addSinglePlayerListener(new ActionListener(){
			 
			@Override
			public void actionPerformed(ActionEvent arg0) {
				theModel.setGameType(1);
				theModel.setP1Attempts(0);
				theModel.setPlayerShots(0);
				theModel.setCpuShots(0);
				String cell;
				
				theModel.initialiseGame(theModel.getPlayerboard(), 1);
				theModel.initialiseGame(theModel.getCPUboard(), 3);
				theModel.randomizeGame(theModel.getCPUboard(), 3);
				
				for(JButton coordinate : theView.getPm().playerCoordinate ) {
					cell = theModel.updateCoordinate(coordinate.getActionCommand(), theModel.getPlayerboard() );
					if(cell == "~" || cell == "O"){
						coordinate.setBackground(Color.cyan);
					}
					coordinate.setEnabled(true);
				}
				for(JButton coordinate : theView.getCm().cpuCoordinate ) {
					cell = theModel.updateCoordinate(coordinate.getActionCommand(), theModel.getCPUboard() );
					if(cell == "~" || cell == "O"){
						coordinate.setBackground(Color.cyan);
					}
					coordinate.setEnabled(false);
				}
				
				theView.getAircraftCarrier().setEnabled(true);
				theView.getBattleShip().setEnabled(true);
				theView.getDestroyer().setEnabled(true);
				theView.getPatrolBoat().setEnabled(true);
				theView.getSwitchOrientation().setEnabled(true);
				theView.setA(1);
				theView.setB(1);
				theView.setD(2);
				theView.setP(1);
				
				theView.setPlayerMouseOff(false);
				
				theView.displayScreen(theView.getPm(),theView.getCm(), theView.getPm2(), false);
				theView.getPlayer().setText("Player: Set Your Ships");
			}
		});
		
		this.theView.addTwoPlayerListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				theModel.setGameType(2);
				String cell;
				theModel.setP1Attempts(0);
				theModel.setPlayerShots(0);
				theModel.setP2Attempts(0);
				theModel.setPlayer2Shots(0);
				
				theModel.setGameStart(false);
				theModel.initialiseGame(theModel.getPlayerboard(), 1);
				theModel.initialiseGame(theModel.getPlayer2board(), 2);
				
				for(JButton coordinate : theView.getPm().playerCoordinate ) {
					cell = theModel.updateCoordinate(coordinate.getActionCommand(), theModel.getPlayerboard() );
					if(cell == "~" || cell == "O"){
						coordinate.setBackground(Color.cyan);
					}
					coordinate.setEnabled(true);
				}
				
				for(JButton coordinate : theView.getPm2().playerCoordinate ) {
					cell = theModel.updateCoordinate(coordinate.getActionCommand(), theModel.getPlayer2board() );
					if(cell == "~" || cell == "O"){
						coordinate.setBackground(Color.cyan);
					}
					coordinate.setEnabled(true);
				}
				
				theView.getPm2().setVisible(false);
				theView.getPm().setVisible(true);
				theView.getAircraftCarrier().setEnabled(true);
				theView.getBattleShip().setEnabled(true);
				theView.getDestroyer().setEnabled(true);
				theView.getPatrolBoat().setEnabled(true);
				theView.getSwitchOrientation().setEnabled(true);
				theView.setA(1);
				theView.setB(1);
				theView.setD(2);
				theView.setP(1);
				
				theView.setPlayerMouseOff(false);
				
				theView.displayScreen(theView.getPm(),theView.getCm(),theView.getPm2(),true);

				theView.getPlayer().setText("Player: Set Your Ships");
				theView.getSecondPlayer().setText("Player 2: ");
				
			}
		});
		
		this.theView.addBasicGameListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				theModel.setGameType(0);
				theModel.setP1Attempts(0);
				theModel.setPlayerShots(0);
				theModel.setCpuShots(0);
				
				theModel.initialiseGame(theModel.getPlayerboard(), 1);
				theModel.randomizeGame(theModel.getPlayerboard(), 1);
				theModel.initialiseGame(theModel.getCPUboard(), 3);
				theModel.randomizeGame(theModel.getCPUboard(), 3);
				
				String cell;
				for(JButton coordinate : theView.getCm().cpuCoordinate ) {
					cell = theModel.updateCoordinate(coordinate.getActionCommand(), theModel.getCPUboard() );
					if(cell == "~" || cell == "O"){
						coordinate.setBackground(Color.cyan);
					}
					coordinate.setEnabled(true);
				}
				for(JButton coordinate : theView.getPm().playerCoordinate ) {
					cell = theModel.updateCoordinate(coordinate.getActionCommand(), theModel.getPlayerboard() );
					if(cell == "~"){
						coordinate.setBackground(Color.cyan);
					}
					if(cell == "O"){
						coordinate.setBackground(Color.green);
					}
					coordinate.setEnabled(false);
				}
				
				theView.getAircraftCarrier().setEnabled(false);
				theView.getBattleShip().setEnabled(false);
				theView.getDestroyer().setEnabled(false);
				theView.getPatrolBoat().setEnabled(false);
				theView.getSwitchOrientation().setEnabled(false);
				theView.setA(0);
				theView.setB(0);
				theView.setD(0);
				theView.setP(0);
				
				theView.setPlayerMouseOff(true);
				
				theView.displayScreen(theView.getPm(),theView.getCm(), theView.getPm2(), false);
			}
		
		});
		
		this.theView.addShootListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				
				Timer timer = new Timer();
				Object o = e.getSource();
				if(o instanceof JButton) {
					String cell;
					boolean shipHasSunk;
					JButton b = (JButton) o;
					theModel.setPlayerShots(theModel.shoot(b.getActionCommand(), theModel.getPlayerShots(), theModel.getCPUboard() ));
				
					shipHasSunk = theModel.shipSunk();
					
					cell = theModel.updateCoordinate(b.getActionCommand(), theModel.getCPUboard() );
					
					if(cell == "X"){
						b.setBackground(Color.red);
						theView.getSecondPlayer().setText("CPU: 'OI! Stop that!'");
						b.setEnabled(false);
						if(shipHasSunk){
							timer.schedule( new TimerTask(){
					             public void run() { 
					            	 theView.getSecondPlayer().setText("CPU: 'YOU SUNK MY BATTLE SHIP! >:-('");
					             }
					           }, 1000);
						}
						timer.schedule( new TimerTask(){
				             public void run() { 
				            	 theView.getSecondPlayer().setText("CPU: ");
				             }
				           }, 3000);
					}
					
					if(cell == "*"){
						b.setBackground(Color.white);
						b.setEnabled(false);
						theView.getSecondPlayer().setText("CPU: 'Haha! You missed :-P...'");
						theView.getSaveExit().setEnabled(false);
						
						timer.schedule( new TimerTask(){
				             public void run() { 
				            	 theView.getSecondPlayer().setText("CPU: 'My GO! :-D'");
				             }
				           }, 1000);
						for(JButton coordinate : theView.getCm().cpuCoordinate ) {
							coordinate.setEnabled(false);
						}
						timer.schedule( new TimerTask(){
				             public void run() { 
				            	 callAI();
				            	 theView.getSecondPlayer().setText("CPU: 'Your Turn -_-'");
				            	 theView.getSaveExit().setEnabled(true);
				            	 for(JButton coordinate : theView.getCm().cpuCoordinate ) {
										coordinate.setEnabled(true);
									}
				             }
				           }, 3000);
					}
					theModel.setP1Attempts(theModel.getP1Attempts() + 1);
					theView.getPlayer().setText("Player: Attempts: " + theModel.getP1Attempts() + ", Shots: " + theModel.getPlayerShots());
					if(theModel.getPlayerShots() == 17){
						timer.schedule( new TimerTask(){
				             public void run() { 
								theView.getPlayer().setText("Player: Attempts: " + theModel.getP1Attempts() + ", Shots: " + theModel.getPlayerShots());
								theView.getSecondPlayer().setText("CPU: 'You Won.... :'-('");
								theView.endGame();

				             }
				           }, 5000);
					}
					else if(theModel.getCpuShots() == 17){
						timer.schedule( new TimerTask(){
				             public void run() { 
								theView.getPlayer().setText("Player: Attempts: " + theModel.getP1Attempts() + ", Shots: " + theModel.getPlayerShots());
								theView.getSecondPlayer().setText("CPU: 'I Won!!!! HAHA! :-D'");
								theView.endGame();
					 }
		           }, 5000);
					}
				}
				
			}
			private void callAI() {
				theModel.setCpuShots(theModel.aI(theModel.getPlayerboard(), theModel.getCpuShots()));
				theView.removeScreen();
				String cell;
				for(JButton coordinate : theView.getPm().playerCoordinate ) {
					cell = theModel.updateCoordinate(coordinate.getActionCommand(), theModel.getPlayerboard() );
					
					if(cell == "*"){
						coordinate.setBackground(Color.white);
						
					}
					if(cell == "X"){
						coordinate.setBackground(Color.red);
					}
				}
				theView.displayScreen(theView.getPm(),theView.getCm(), theView.getPm2(), false);
			}
		});
		
		this.theView.addMainMenuButtonListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				theView.removeScreen();
				theView.getMainMenuButton().setVisible(false);
				theView.loadMainMenu();
			}
		});
		
		this.theView.addSelectAirCraftCarrierListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				theModel.setSize(theModel.getAircraftCarrier().getSize());
				theModel.setUnits(theView.getA());
				theView.getPlayer().setText("Player: Aircraft Carrier: Size " + theModel.getSize());
				
			}
			
		});
		
		this.theView.addSelectBattleShipListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				theModel.setSize(theModel.getBattleShip().getSize());
				theModel.setUnits(theView.getB());
				theView.getPlayer().setText("Player: Battle Ship: Size " + theModel.getSize());
				
			}
			
		});
		
		this.theView.addSelectDestroyerListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				theModel.setUnits(theView.getD());
				if(theModel.getUnits() == 2){
					theModel.setSize(theModel.getDestroyer1().getSize());	
				}
				else{
					theModel.setSize(theModel.getDestroyer2().getSize());
				}
				theView.getPlayer().setText("Player: Destroyer: Size " + theModel.getSize());
			}
			
		});
		
		this.theView.addSelectPatrolBoatListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				theModel.setSize(theModel.getPatrolBoat().getSize());
				theModel.setUnits(theView.getP());
				theView.getPlayer().setText("Player: Patrol Boat: Size " + theModel.getSize());
			}
			
		});
		
		this.theView.addSwitchOrientationListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				theModel.switchShip();
			}
			
		});
		
		this.theView.addSaveExitButtonListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				

			    try {
			    	ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("ResumeGame.bin"));

			    	outputStream.writeObject(theModel.getCPUboard());
					outputStream.writeObject(theModel.getPlayerboard());
					outputStream.writeObject(theModel.getPlayer2board());
					
					outputStream.writeInt(theModel.getPlayerShots());
					outputStream.writeInt(theModel.getPlayer2Shots());
					outputStream.writeInt(theModel.getCpuShots());
					outputStream.writeInt(theModel.getP1Attempts());
					outputStream.writeInt(theModel.getP2Attempts());
					
					outputStream.writeBoolean(theView.isTwoPlayer());
					outputStream.writeInt(theModel.getGameType());
					
					outputStream.writeInt(theView.getA());
					outputStream.writeInt(theView.getB());
					outputStream.writeInt(theView.getD());
					outputStream.writeInt(theView.getP());
					
					theView.getResumeGame().setEnabled(true);
					
				    theView.removeScreen();
				    theView.loadMainMenu();
				    outputStream.close();
					
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
		});
		
		this.theView.addResumeButtonListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					ObjectInputStream inputStream = new ObjectInputStream(new BufferedInputStream(new FileInputStream("ResumeGame.bin")));
					
					theModel.setCPUboard((int [][]) inputStream.readObject());
					theModel.setPlayerboard((int[][]) inputStream.readObject());
					theModel.setPlayer2board((int[][]) inputStream.readObject());
					
					theModel.setPlayerShots((int) inputStream.readInt());
					theModel.setPlayer2Shots((int) inputStream.readInt());
					theModel.setCpuShots((int) inputStream.readInt());
					theModel.setP1Attempts((int) inputStream.readInt());
					theModel.setP2Attempts((int) inputStream.readInt());
					
					theView.setTwoPlayer((boolean) inputStream.readBoolean());
					
					theModel.setGameType((int) inputStream.readInt());
					
					theView.setA((int) inputStream.readInt());
					theView.setB((int) inputStream.readInt());
					theView.setD((int) inputStream.readInt());
					theView.setP((int) inputStream.readInt());
					
					String cell;
					if(theModel.getGameType() == 0){
						for(JButton coordinate : theView.getPm().playerCoordinate ) {
							cell = theModel.updateCoordinate(coordinate.getActionCommand(), theModel.getPlayerboard() );
							if(cell == "*"){
								coordinate.setBackground(Color.white);
							}
							if(cell == "X"){
								coordinate.setBackground(Color.red);
							}
							if(cell == "O"){
								coordinate.setBackground(Color.green);
							}
							coordinate.setEnabled(false);
						}
						for(JButton coordinate : theView.getCm().cpuCoordinate ) {
							cell = theModel.updateCoordinate(coordinate.getActionCommand(), theModel.getCPUboard() );
							if(cell == "*"){
								coordinate.setBackground(Color.white);
							}
							if(cell == "X"){
								coordinate.setBackground(Color.red);
							}
							coordinate.setEnabled(true);
						}
						if(theView.getA() == 0){
							theView.getAircraftCarrier().setEnabled(false);
						}
						if(theView.getB() == 0){
							theView.getBattleShip().setEnabled(false);
						}
						if(theView.getD() == 0){
							theView.getDestroyer().setEnabled(false);
						}
						if(theView.getP() == 0){
							theView.getPatrolBoat().setEnabled(false);
						}
						if(theView.getA() == 0 && theView.getB() == 0 && theView.getD() == 0 && theView.getP() == 0){
							theView.getSwitchOrientation().setEnabled(false);
						}
					}
					if(theModel.getGameType() == 1){
						if(theView.getA() == 0){
							theView.getAircraftCarrier().setEnabled(false);
						}
						if(theView.getB() == 0){
							theView.getBattleShip().setEnabled(false);
						}
						if(theView.getD() == 0){
							theView.getDestroyer().setEnabled(false);
						}
						if(theView.getP() == 0){
							theView.getPatrolBoat().setEnabled(false);
						}
						
						for(JButton coordinate : theView.getPm().playerCoordinate ) {
							cell = theModel.updateCoordinate(coordinate.getActionCommand(), theModel.getPlayerboard() );
							if(cell == "*"){
								coordinate.setBackground(Color.white);
							}
							if(cell == "X"){
								coordinate.setBackground(Color.red);
							}
							if(cell == "O"){
								coordinate.setBackground(Color.green);
							}
							if(theView.getA() == 0 && theView.getB() == 0 && theView.getD() == 0 && theView.getP() == 0){
								theView.getSwitchOrientation().setEnabled(false);
								coordinate.setEnabled(false);
							}
						}
						for(JButton coordinate : theView.getCm().cpuCoordinate ) {
							cell = theModel.updateCoordinate(coordinate.getActionCommand(), theModel.getCPUboard() );
							if(cell == "*"){
								coordinate.setBackground(Color.white);
							}
							if(cell == "X"){
								coordinate.setBackground(Color.red);
							}
							if(theView.getA() == 0 && theView.getB() == 0 && theView.getD() == 0 && theView.getP() == 0){
								coordinate.setEnabled(true);
							}
							else{
								coordinate.setEnabled(false);
							}
						}
					}
					if(theModel.getGameType() == 2){
						for(JButton coordinate : theView.getPm().playerCoordinate ) {
							cell = theModel.updateCoordinate(coordinate.getActionCommand(), theModel.getPlayerboard() );
							if(cell == "*"){
								coordinate.setBackground(Color.white);
							}
							if(cell == "X"){
								coordinate.setBackground(Color.red);
							}
							coordinate.setEnabled(true);
						}
						for(JButton coordinate : theView.getPm2().playerCoordinate ) {
							cell = theModel.updateCoordinate(coordinate.getActionCommand(), theModel.getPlayer2board() );
							if(cell == "*"){
								coordinate.setBackground(Color.white);
							}
							if(cell == "X"){
								coordinate.setBackground(Color.red);
							}
							coordinate.setEnabled(true);
						}
						if(theView.getA() == 0){
							theView.getAircraftCarrier().setEnabled(false);
						}
						if(theView.getB() == 0){
							theView.getBattleShip().setEnabled(false);
						}
						if(theView.getD() == 0){
							theView.getDestroyer().setEnabled(false);
						}
						if(theView.getP() == 0){
							theView.getPatrolBoat().setEnabled(false);
						}
						if(theView.getA() == 0 && theView.getB() == 0 && theView.getD() == 0 && theView.getP() == 0){
							theView.getSwitchOrientation().setEnabled(false);
						}
					}
					theView.displayScreen(theView.getPm(),theView.getCm(), theView.getPm2(), theView.isTwoPlayer());
					inputStream.close();
					
				} catch (FileNotFoundException e1) {
					theView.getResumeGame().setEnabled(false);
				} catch (IOException e1) {
					e1.printStackTrace();
				} catch (ClassNotFoundException e1) {
					e1.printStackTrace();
				}
				
				
				
			}
			
		});
		
		this.theView.addPlayerMouseListener(new MouseListener(){
			
			
			@Override
			public void mouseClicked(MouseEvent e) {

				if(theModel.getUnits() !=0){

						if(theView.isPlayerMouseOff() == false){
							if(theModel.isInvalidPlacement() == false){
								theModel.placeShip(theModel.getPlayerboard(), 1);
								
								String cells = null;
								for(JButton coordinates : theView.getPm().playerCoordinate ) {
									cells = theModel.updateCoordinate(coordinates.getActionCommand(), theModel.getPlayerboard() );
									if(cells == "O"){
										coordinates.setBackground(Color.green);
										
									}
								}
								
								if(theModel.getSize() == 5){
									theView.setA(theView.getA() - 1);
									theView.getaUnits().setText("= "+ theView.getA());
									if(theView.getA() == 0){
										theView.getAircraftCarrier().setEnabled(false);
									}
								}
								if(theModel.getSize() == 4){
									theView.setB(theView.getB() - 1);
									theView.getbUnits().setText("= "+ theView.getB());
									if(theView.getB() == 0){
										theView.getBattleShip().setEnabled(false);
									}
								}
								if(theModel.getSize() == 3){
									theView.setD(theView.getD() - 1);
									theView.getdUnits().setText("= "+ theView.getD());
									if(theView.getD() == 0){
										theView.getDestroyer().setEnabled(false);
									}
								}
								if(theModel.getSize() == 2){
									theView.setP(theView.getP() - 1);
									theView.getpUnits().setText("= "+ theView.getP());
									if(theView.getP() == 0){
										theView.getPatrolBoat().setEnabled(false);
									}
								}
								if(theView.getA() == 0 && theView.getB() == 0 && theView.getD() == 0 && theView.getP() == 0){
									theView.getSwitchOrientation().setEnabled(false);
									
									theView.removeScreen();
									
									
									theView.displayScreen(theView.getPm(),theView.getCm(), theView.getPm2(), theView.isTwoPlayer());
									if(theView.isTwoPlayer() == false){
										for(JButton coordinates : theView.getCm().cpuCoordinate ) {
											coordinates.setEnabled(true);
										}
										theView.getPlayer().setText("Player: ");
										for(JButton coordinates : theView.getPm().playerCoordinate ) {
											coordinates.setEnabled(false);
										}
									}
									else{
										theView.getPm2().setVisible(true);
										theView.getPm().setVisible(false);
										theView.getPlayer().setText("Player: ");
										theView.getSecondPlayer().setText("Player 2: Set Your Ships ");
										theView.getAircraftCarrier().setEnabled(true);
										theView.getBattleShip().setEnabled(true);
										theView.getDestroyer().setEnabled(true);
										theView.getPatrolBoat().setEnabled(true);
										theView.getSwitchOrientation().setEnabled(true);
										theView.setA(1);
										theView.setB(1);
										theView.setD(2);
										theView.setP(1);
										theView.getaUnits().setText("= "+ theView.getA());
										theView.getbUnits().setText("= "+ theView.getB());
										theView.getdUnits().setText("= "+ theView.getD());
										theView.getpUnits().setText("= "+ theView.getP());
									}
								}
								theModel.setUnits(0);
								theModel.setSize(0);
							}
							else{
								theView.getPlayer().setText("Player: Invalid Placement");
							}
						}
					
				}
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				String cell;

				if(theView.isPlayerMouseOff() == false){
					Object o = e.getSource();
					if(o instanceof JButton) {
						JButton b = (JButton) o;
						
						if(theModel.getSize() != 0){
							theModel.setInvalidPlacement(theModel.highlight(b.getActionCommand(),theModel.getSize(), theModel.getPlayerboard()));
							
							for(JButton coordinate : theView.getPm().playerCoordinate ) {
								cell = theModel.updateCoordinate(coordinate.getActionCommand(), theModel.getPlayerboard());
								if(cell == "H"){
									coordinate.setBackground(Color.green);
								}
							}
						}
					}
				}
			}

			@Override
			public void mouseExited(MouseEvent e) {
					if(theView.isPlayerMouseOff() == false){

						theModel.removeHighlight(theModel.getPlayerboard());
						
						String cell;
						for(JButton coordinates : theView.getPm().playerCoordinate ) {
							cell = theModel.updateCoordinate(coordinates.getActionCommand(), theModel.getPlayerboard() );
							if(cell == "~"){
								coordinates.setBackground(Color.cyan);
								
							}
						}
					}
			}

			@Override
			public void mousePressed(MouseEvent e) {
				//Do Nothing
				
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				//Do Nothing
				
			}
		});
		
		this.theView.addPlayerActionListener(new ActionListener(){
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(theModel.isGameStart() == true){
					Timer timer = new Timer();
					Object o = e.getSource();
					if(o instanceof JButton) {
						JButton b = (JButton) o;
						String cell;
						boolean shipHasSunk;
						theModel.setPlayer2Shots(theModel.shoot(b.getActionCommand(), theModel.getPlayer2Shots(), theModel.getPlayerboard() ));
					
						shipHasSunk = theModel.shipSunk();
						
						cell = theModel.updateCoordinate(b.getActionCommand(), theModel.getPlayerboard() );
						
						if(cell == "*"){
							b.setBackground(Color.white);
							b.setEnabled(false);
							
							timer.schedule( new TimerTask(){
					             public void run() { 
					            	 theView.getPlayer().setText("Player: Your Turn");
					            	 theView.getSecondPlayer().setText("Player 2: You Missed");
					             }
					           }, 1000);
							timer.schedule( new TimerTask(){
							public void run() { 
				            	 theView.getSecondPlayer().setText("Player 2: ");
				             }
				           }, 2000);
							for(JButton coordinates : theView.getPm2().playerCoordinate ) {
								coordinates.setEnabled(true);
							}
							for(JButton coordinates : theView.getPm().playerCoordinate ) {
								coordinates.setEnabled(false);
							}
							
						}
						if(cell == "X"){
							b.setBackground(Color.red);
							b.setEnabled(false);
							if(shipHasSunk){
								timer.schedule( new TimerTask(){
						             public void run() { 
						            	 theView.getPlayer().setText("Player: 'YOU SUNK MY BATTLE SHIP! >:-('");
						             }
						           }, 1000);
							}
							timer.schedule( new TimerTask(){
					             public void run() { 
					            	 theView.getPlayer().setText("Player: ");
					             }
					           }, 3000);
						}
						
						theModel.setP2Attempts(theModel.getP2Attempts() + 1);
						theView.getSecondPlayer().setText("Player 2: Attempts: " + theModel.getP2Attempts() + ", Shots: " + theModel.getPlayer2Shots());
						if(theModel.getPlayer2Shots() == 17){
								theView.endGame();
								theView.getSecondPlayer().setText("Player 2: Attempts: " + theModel.getP2Attempts() + ", Shots: " + theModel.getPlayer2Shots() + " YOU WON!");
						}
					}
				}
			}
			
		});
		
		this.theView.addPlayer2ActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				if(theModel.isGameStart() == true){
					Timer timer = new Timer();
					Object o = e.getSource();
					if(o instanceof JButton) {
						JButton b = (JButton) o;
						String cell;
						boolean shipHasSunk;
						theModel.setPlayerShots(theModel.shoot(b.getActionCommand(), theModel.getPlayerShots(),theModel.getPlayer2board() ));
					
						shipHasSunk = theModel.shipSunk();
						
						cell = theModel.updateCoordinate(b.getActionCommand(), theModel.getPlayer2board() );
						
						if(cell == "*"){
							b.setBackground(Color.white);
							b.setEnabled(false);
							
							timer.schedule( new TimerTask(){
					             public void run() { 
					            	 theView.getPlayer().setText("Player: You Missed");
					            	 theView.getSecondPlayer().setText("Player 2: Your Turn");
					             }
					           }, 1000);
							timer.schedule( new TimerTask(){
					             public void run() { 
					            	 theView.getPlayer().setText("Player: ");
					             }
					           }, 2000);
							for(JButton coordinates : theView.getPm().playerCoordinate ) {
								coordinates.setEnabled(true);
							}
							for(JButton coordinates : theView.getPm2().playerCoordinate ) {
								coordinates.setEnabled(false);
							}
						}
						if(cell == "X"){
							b.setBackground(Color.red);
							b.setEnabled(false);
							if(shipHasSunk){
								timer.schedule( new TimerTask(){
						             public void run() { 
						            	 theView.getSecondPlayer().setText("Player 2: 'YOU SUNK MY BATTLE SHIP! >:-('");
						             }
						           }, 2000);
							}
							timer.schedule( new TimerTask(){
					             public void run() { 
					            	 theView.getSecondPlayer().setText("Player 2: ");
					             }
					           }, 3000);
						}
						theModel.setP1Attempts(theModel.getP1Attempts() + 1);
						theView.getPlayer().setText("Player: Attempts: " + theModel.getP1Attempts() + ", Shots: " + theModel.getPlayerShots());
						if(theModel.getPlayerShots() == 17){
								theView.endGame();
								theView.getPlayer().setText("Player: Attempts: " + theModel.getP1Attempts() + ", Shots: " + theModel.getPlayerShots() + " YOU WON!");
	
						}
					}
				}
			}
			
		});
		
		this.theView.addPlayer2MouseListener(new MouseListener(){

			@Override
			public void mouseClicked(MouseEvent e) {

				if(theModel.getUnits() !=0){
					
						if(theView.isPlayerMouseOff() == false){
							if(theModel.isInvalidPlacement() == false){
								theModel.placeShip(theModel.getPlayer2board(), 2);
								
								String cells = null;
								for(JButton coordinates : theView.getPm2().playerCoordinate ) {
									cells = theModel.updateCoordinate(coordinates.getActionCommand(), theModel.getPlayer2board() );
									if(cells == "O"){
										coordinates.setBackground(Color.green);
										
									}
								}
								
								if(theModel.getSize() == 5){
									theView.setA(theView.getA() - 1);
									theView.getaUnits().setText("= "+ theView.getA());
									if(theView.getA() == 0){
										theView.getAircraftCarrier().setEnabled(false);
									}
								}
								if(theModel.getSize() == 4){
									theView.setB(theView.getB() - 1);
									theView.getbUnits().setText("= "+ theView.getB());
									if(theView.getB() == 0){
										theView.getBattleShip().setEnabled(false);
									}
								}
								if(theModel.getSize() == 3){
									theView.setD(theView.getD() - 1);
									theView.getdUnits().setText("= "+ theView.getD());
									if(theView.getD() == 0){
										theView.getDestroyer().setEnabled(false);
									}
								}
								if(theModel.getSize() == 2){
									theView.setP(theView.getP() - 1);
									theView.getpUnits().setText("= "+ theView.getP());
									if(theView.getP() == 0){
										theView.getPatrolBoat().setEnabled(false);
									}
								}
								if(theView.getA() == 0 && theView.getB() == 0 && theView.getD() == 0 && theView.getP() == 0){
									theView.getSwitchOrientation().setEnabled(false);
									
									theView.removeScreen();
									
									String cell;
									for(JButton coordinates : theView.getPm().playerCoordinate ) {
										cell = theModel.updateCoordinate(coordinates.getActionCommand(), theModel.getPlayerboard() );
										if(cell == "O"){
											coordinates.setBackground(Color.cyan);	
										}
										coordinates.setEnabled(false);
									}
									for(JButton coordinates : theView.getPm2().playerCoordinate ) {
										cell = theModel.updateCoordinate(coordinates.getActionCommand(), theModel.getPlayer2board() );
										if(cell == "O"){
											coordinates.setBackground(Color.cyan);	
										}
										coordinates.setEnabled(true);
									}
									
									
									theView.displayScreen(theView.getPm(),theView.getCm(), theView.getPm2(), theView.isTwoPlayer());
									
									
									theView.getPm().setVisible(true);
									theModel.setGameStart(true);
										theView.setPlayerMouseOff(false);
										theView.getPlayer().setText("Player: Start Game");
										
										
								}
								theModel.setUnits(0);
								theModel.setSize(0);
							}
							else{
								theView.getSecondPlayer().setText("Player 2: Invalid Placement");
							}
						}
					
				}
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				String cell;

				if(theView.isPlayerMouseOff() == false){
					Object o = e.getSource();
					if(o instanceof JButton) {
						JButton b = (JButton) o;
						
						if(theModel.getSize() != 0){
							theModel.setInvalidPlacement(theModel.highlight(b.getActionCommand(),theModel.getSize(), theModel.getPlayer2board()));
							
							for(JButton coordinate : theView.getPm2().playerCoordinate ) {
								cell = theModel.updateCoordinate(coordinate.getActionCommand(), theModel.getPlayer2board());
								if(cell == "H"){
									coordinate.setBackground(Color.green);
								}
							}
						}
					}
				}
			}

			@Override
			public void mouseExited(MouseEvent e) {
					
				if(theView.isPlayerMouseOff() == false){

						theModel.removeHighlight(theModel.getPlayer2board());
						
						String cell;
						for(JButton coordinates : theView.getPm2().playerCoordinate ) {
							cell = theModel.updateCoordinate(coordinates.getActionCommand(), theModel.getPlayer2board() );
							if(cell == "~"){
								coordinates.setBackground(Color.cyan);
								
							}
						}
					}
			}

			@Override
			public void mousePressed(MouseEvent e) {
				//Do Nothing
				
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				//Do Nothing
				
			}
			
		});

	}
	

	
}

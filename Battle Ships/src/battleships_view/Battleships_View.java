package battleships_view;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
/**
 * 
 * This is the View Class that will display the GUI for the Game
 * It extends the JFrame
 * 
 * @author Abdassami Alvi
 *
 */
public class Battleships_View extends JFrame {
	
	URL url = getClass().getResource("Battle Ships.gif");
	private ImageIcon iconBattleship = new ImageIcon(url);
	private JLabel battleships = new JLabel(iconBattleship);
	private JPanel mainMenu = new JPanel();
	private JButton singlePlayer = new JButton();
    private JButton twoPlayer = new JButton();
    private JButton resumeGame = new JButton();
    private JButton basicGame = new JButton(); 
    
    private boolean playerMouseOff = false;
	

	private PlayerMap pm;
	private CPUMap cm;
	private PlayerMap pm2;
	
	private int a;
	private int b;
	private int d;
	private int p;
	
	private JLabel ships;
    private JLabel player;
    private JLabel secondPlayer;
    private JButton aircraftCarrier = new JButton("A");
    private JButton battleShip = new JButton("B");
    private JButton destroyer = new JButton("D");
    private JButton patrolBoat = new JButton("P");
    private JButton switchOrientation = new JButton("Switch");
    private JButton saveExit = new JButton("Save & Exit");
    private JButton mainMenuButton = new JButton("Main Menu");
    private JLabel aUnits;
    private JLabel bUnits;
    private JLabel dUnits;
    private JLabel pUnits;
    private JPanel panel = new JPanel();
	private boolean isTwoPlayer;
    
    /**
     * This is the View constructor of class Battleships_View
     * 
     * It takes in the parameters of the PlayerMap and CPUMap classes
     * 
     * @param pm is the player 1 grid of a PlayerMap
     * @param cm is the AI grid of a CPUMap
     * @param pm2 is the player 2 grid of a PlayerMap
     * */
	public Battleships_View(PlayerMap pm, CPUMap cm, PlayerMap pm2) {
		
		super("Battleships");
		super.setSize(450, 400);
		
		mainMenu.setBackground(Color.darkGray);
		super.add(mainMenu);
		
		battleships.setLocation(90,21);
		battleships.setSize(290,75);
		super.add(battleships);
		
		basicGame.setBackground(Color.gray);
		basicGame.setLocation(146,112);
		basicGame.setSize(150,50);
		basicGame.setText("Basic Game");
		super.add(basicGame);
		
		singlePlayer.setBackground(Color.gray);
		singlePlayer.setLocation(146,173);
		singlePlayer.setSize(150,50);
		singlePlayer.setText("Single Player");
		super.add(singlePlayer);
		
		twoPlayer.setBackground(Color.gray);
		twoPlayer.setLocation(146,233);
		twoPlayer.setSize(150,50);
		twoPlayer.setText("Two Player");
		super.add(twoPlayer);
		
		getResumeGame().setBackground(Color.gray);
		getResumeGame().setLocation(146,293);
		getResumeGame().setSize(150,50);
		getResumeGame().setText("Resume");
		super.add(getResumeGame());
		
		this.setPm(pm);
		this.setCm(cm);
		this.setPm2(pm2);
		
		super.add(mainMenu);
		
		setTitle("Battleships");
		setLocation(500,150);
		setResizable(false);
		
	}
	/**
	 * This is the displaySceen method of type void
	 * 
	 * It will display the main battleship game components
	 * 
	 * @param pm is the player 1 grid of a PlayerMap
     * @param cm is the AI grid of a CPUMap
     * @param pm2 is the player 2 grid of a PlayerMap
	 * @param isTwoPlayer is a boolean value to determine if the two player game mode has been selected
	 */
	public void displayScreen(PlayerMap pm, CPUMap cm, PlayerMap pm2, boolean isTwoPlayer){
		
		this.setPm(pm);
		this.setCm(cm);
		this.setPm2(pm2);
		this.setTwoPlayer(isTwoPlayer);
		
		super.remove(mainMenu);
		super.remove(battleships);
		super.remove(basicGame);
		super.remove(singlePlayer);
		super.remove(twoPlayer);
		super.remove(getResumeGame());
		
		setaUnits(new JLabel("= " + getA()));
	    setbUnits(new JLabel("= " + getB()));
	    setdUnits(new JLabel("= " + getD()));
	    setpUnits(new JLabel("= " + getP()));

		ships = new JLabel();
		ships.setLocation(21,16);
		ships.setSize(73,33);
		ships.setForeground(Color.red);
		ships.setText("Ships");
		super.add(ships);

		setPlayer(new JLabel());
		getPlayer().setLocation(148,12);
		getPlayer().setSize(173,33);
		getPlayer().setForeground(Color.red);
		getPlayer().setText("Player: ");
		super.add(getPlayer());

		setSecondPlayer(new JLabel());
		getSecondPlayer().setLocation(670,10);
		getSecondPlayer().setSize(250,33);
		getSecondPlayer().setForeground(Color.red);
		getSecondPlayer().setText("CPU: ");
		super.add(getSecondPlayer());

		getAircraftCarrier().setLocation(21,78);
		getAircraftCarrier().setSize(50,50);
		getAircraftCarrier().setBackground(Color.gray);
		super.add(getAircraftCarrier());

		getBattleShip().setLocation(21,147);
		getBattleShip().setSize(50,50);
		getBattleShip().setBackground(Color.gray);
		super.add(getBattleShip());

		getDestroyer().setLocation(21,218);
		getDestroyer().setSize(50,50);
		getDestroyer().setBackground(Color.gray);
		super.add(getDestroyer());

		getPatrolBoat().setLocation(21,291);
		getPatrolBoat().setSize(50,50);
		getPatrolBoat().setBackground(Color.gray);
		super.add(getPatrolBoat());
		
		getSwitchOrientation().setLocation(150,420);
		getSwitchOrientation().setSize(100,50);
		getSwitchOrientation().setBackground(Color.gray);
		super.add(getSwitchOrientation());

		getSaveExit().setLocation(610,420);
		getSaveExit().setSize(100,50);
		getSaveExit().setBackground(Color.gray);
		super.add(getSaveExit());

		getaUnits().setLocation(74,91);
		getaUnits().setSize(50,25);
		getaUnits().setForeground(Color.red);
		super.add(getaUnits());

		getbUnits().setLocation(73,159);
		getbUnits().setSize(50,25);
		getbUnits().setForeground(Color.red);
		super.add(getbUnits());

		getdUnits().setLocation(73,231);
		getdUnits().setSize(50,25);
		getdUnits().setForeground(Color.red);
		super.add(getdUnits());
		
		getpUnits().setLocation(74,305);
		getpUnits().setSize(50,25);
		getpUnits().setForeground(Color.red);
		super.add(getpUnits());
		
		
		this.getPm().setBackground(Color.darkGray);
		this.getPm().setLocation(150, 60);
		this.getPm().setSize(500, 340);
		super.add(this.getPm());

		if(isTwoPlayer == false){
			this.getCm().setBackground(Color.darkGray);
			this.getCm().setLocation(670, 60);
			this.getCm().setSize(500, 340);
			super.add(this.getCm());
		}
		else{
			getSecondPlayer().setText("Player 2: ");
			this.getPm2().setBackground(Color.darkGray);
			this.getPm2().setLocation(670, 60);
			this.getPm2().setSize(500, 340);
			super.add(this.getPm2());
		}
		panel.setBackground(Color.darkGray);

		super.add(panel);
		
		
		setTitle("Battleships");
		setLocation(75,150);
		setSize(1200,520);
		setVisible(true);
		setResizable(false);
		
		
	}

	/**
	 * this is the remove screen method of type void
	 * 
	 * it will clear the super of any components that have been previously added to it
	 */
	public void removeScreen(){
		super.remove(ships);
		super.remove(getPlayer());
		super.remove(getSecondPlayer());
		super.remove(getAircraftCarrier());
		super.remove(getBattleShip());
		super.remove(getDestroyer());
		super.remove(getPatrolBoat());
		super.remove(getaUnits());
		super.remove(getbUnits());
		super.remove(getdUnits());
		super.remove(getpUnits());
		super.remove(this.getPm());
		super.remove(this.getCm());
		super.remove(panel);
	}
	/**
	 * this the end game method of type game
	 * 
	 * it will disable the grids from being clicked on and add a main menu button
	 * that can return to the main menu screen
	 */
	public void endGame() {
		for(JButton coordinate : this.getCm().cpuCoordinate ) {
			coordinate.setEnabled(false);
		}
		for(JButton coordinate : this.getPm().playerCoordinate ) {
			coordinate.setEnabled(false);
		}
		for(JButton coordinate : this.getPm2().playerCoordinate ) {
			coordinate.setEnabled(false);
		}
	
		super.remove(panel);
		getMainMenuButton().setLocation(1072,420);
		getMainMenuButton().setSize(100,50);
		getMainMenuButton().setBackground(Color.gray);
		super.add(getMainMenuButton());
		super.add(panel);		
		getMainMenuButton().setVisible(false);
		getMainMenuButton().setVisible(true);
	}
	/**
	 * this is the load main menu method of type void
	 * 
	 * it will display all the components of the main menu
	 */
	public void loadMainMenu() {
		
		super.setSize(450, 400);
		
		super.add(battleships);
		super.add(basicGame);
		super.add(singlePlayer);
		super.add(twoPlayer);
		super.add(getResumeGame());
		super.add(mainMenu);
		
		setLocation(500,150);
		setResizable(false);
		
	}
	/**
	 * this is the main menu button listener of type void
	 * it listens for the button to be clicked
	 * @param listenForMainMenuButton is an action listener for the button
	 */
	public void addMainMenuButtonListener(ActionListener listenForMainMenuButton){
		
        getMainMenuButton().addActionListener(listenForMainMenuButton);
        
    }
	/**
	 * this is the save button listener of type void
	 * it listens for the button to be clicked
	 * @param listenForSaveExitButton is an action listener for the button
	 */
	public void addSaveExitButtonListener(ActionListener listenForSaveExitButton){
		
       getSaveExit().addActionListener(listenForSaveExitButton);
        
    }
	/**
	 * this is the resume button listener of type void
	 * it listens for the button to be clicked
	 * @param listenForResumeButton is an action listener for the button
	 */
	public void addResumeButtonListener(ActionListener listenForResumeButton){
		
        getResumeGame().addActionListener(listenForResumeButton);
        
    }
	/**
	 * this is the basic game button listener of type void
	 * it listens for the button to be clicked
	 * @param listenForBasicGameButton is an action listener for the button
	 */
	public void addBasicGameListener(ActionListener listenForBasicGameButton){
		
        basicGame.addActionListener(listenForBasicGameButton);
        
    }
	/**
	 * this is the single player button listener of type void
	 * it listens for the button to be clicked
	 * @param listenForSinglePlayerButton is an action listener for the button
	 */
	public void addSinglePlayerListener(ActionListener listenForSinglePlayerButton){
		
        singlePlayer.addActionListener(listenForSinglePlayerButton);
        
    }
	/**
	 * this is the two player button listener of type void
	 * it listens for the button to be clicked
	 * @param listenForTwoPlayerButton is an action listener for the button
	 */
	public void addTwoPlayerListener(ActionListener listenForTwoPlayerButton){
		
        twoPlayer.addActionListener(listenForTwoPlayerButton);
        
    }
	/**
	 * this is the player 1 grid buttons listener of type void
	 * it listens for the button to be clicked
	 * @param listener is an action listener for the grid buttons
	 */
	public void addPlayerActionListener(ActionListener listener) {
		
		this.getPm().addPlayerActionListener(listener);
	}
	/**
	 * this is the player 2 grid buttons listener of type void
	 * it listens for the button to be clicked
	 * @param listener is an action listener for the grid buttons
	 */
	public void addPlayer2ActionListener(ActionListener listener) {
		
		this.getPm2().addPlayerActionListener(listener);
	}
	/**
	 * this is the player 1 grid mouse listener of type void
	 * it listens for the mouse on the grid
	 * @param listenForMouseOver is an mouse listener for the grid buttons
	 */
	public void addPlayerMouseListener(MouseListener listenForMouseOver){
		
		this.getPm().addPlayerMouseListener(listenForMouseOver);
	}
	/**
	 * this is the player 2 grid mouse listener of type void
	 * it listens for the mouse on the grid
	 * @param listenForMouseOver is an mouse listener for the grid buttons
	 */
	public void addPlayer2MouseListener(MouseListener listenForMouseOver){
		
		this.getPm2().addPlayerMouseListener(listenForMouseOver);
	}
	/**
	 * this is the cpu grid buttons listener of type void
	 * it listens for the button to be clicked
	 * @param listenForShootButton is an action listener for the button
	 */
	public void addShootListener(ActionListener listenForShootButton){
		
		this.getCm().addShootListener(listenForShootButton);
		
    }
	/**
	 * this is the aircraft carrier button listener of type void
	 * it listens for the button to be clicked
	 * @param listenForAirCraftButton is an action listener for the button
	 */
	public void addSelectAirCraftCarrierListener(ActionListener listenForAirCraftButton){
		
		getAircraftCarrier().addActionListener(listenForAirCraftButton);
	}
	
	/**
	 * * this is the battleship button listener of type void
	 * it listens for the button to be clicked
	 * @param listenForBattleShipButton is an action listener for the button
	 */
	public void addSelectBattleShipListener(ActionListener listenForBattleShipButton){
		
		getBattleShip().addActionListener(listenForBattleShipButton);
	}
	
	/**
	 * this is the destroyer button listener of type void
	 * it listens for the button to be clicked
	 * @param listenForDestroyerButton is an action listener for the button
	 */
	public void addSelectDestroyerListener(ActionListener listenForDestroyerButton){
	
		getDestroyer().addActionListener(listenForDestroyerButton);
	}

	/**
	 * this is the patrol boat button listener of type void
	 * it listens for the button to be clicked
	 * @param listenForPatrolBoatButton is an action listener for the button
	 */
	public void addSelectPatrolBoatListener(ActionListener listenForPatrolBoatButton){
	
		getPatrolBoat().addActionListener(listenForPatrolBoatButton);
	}
	
	/**
	 * this is the switch orientation button listener of type void
	 * it listens for the button to be clicked
	 * @param listenForSwitchOrientationButton is an action listener for the button
	 */
	public void addSwitchOrientationListener(ActionListener listenForSwitchOrientationButton){
		
		getSwitchOrientation().addActionListener(listenForSwitchOrientationButton);
	}

	/**
	 * method of type PlayerMap for getting the player map
	 * @return the player map
	 */
	public PlayerMap getPm() {
		return pm;
	}

	/**
	 * method of type void for setting the player map
	 * @param pm of type PlayerMap is passed into the method
	 */
	public void setPm(PlayerMap pm) {
		this.pm = pm;
	}

	/**
	 * method of type CPUMap for getting the cpu map
	 * @return the cpu map
	 */
	public CPUMap getCm() {
		return cm;
	}

	/**
	 * method of type void for setting the cpu map
	 * @param cm of type CPUMap is passed into the method
	 */
	public void setCm(CPUMap cm) {
		this.cm = cm;
	}

	/**
	 * method of type PlayerMap for getting the player 2 map
	 * @return the player 2 map
	 */
	public PlayerMap getPm2() {
		return pm2;
	}

	/**
	 * method of type void for setting the player 2 map
	 * @param pm2 of type PlayerMap is passed into the method
	 */
	public void setPm2(PlayerMap pm2) {
		this.pm2 = pm2;
	}

	/**
	 * method of type JButton for getting the resume game button
	 * @return the resumeGame button
	 */
	public JButton getResumeGame() {
		return resumeGame;
	}

	/**
	 * method of type void for setting the resume game button
	 * @param resumeGame of type JButton is passed into the method
	 */
	public void setResumeGame(JButton resumeGame) {
		this.resumeGame = resumeGame;
	}

	/**
	 * method of type boolean for getting the playerMouseOff variable
	 * @return playerMouseOff
	 */
	public boolean isPlayerMouseOff() {
		return playerMouseOff;
	}

	/**
	 * method of type void for setting the playerMouseOff variable
	 * @param playerMouseOff of type boolean is passed into the method
	 */
	public void setPlayerMouseOff(boolean playerMouseOff) {
		this.playerMouseOff = playerMouseOff;
	}

	/**
	 * method of type int for getting the value of the aircraft carrier unit
	 * @return a
	 */
	public int getA() {
		return a;
	}

	/**
	 * setter for the int a
	 * @param a
	 */
	public void setA(int a) {
		this.a = a;
	}

	/**
	 * method of type int for getting the value of the battleship unit
	 * @return b
	 */
	public int getB() {
		return b;
	}

	/**
	 * setter for the int b
	 * @param b
	 */
	public void setB(int b) {
		this.b = b;
	}

	/**
	 * method of type int for getting the value of the destroyer unit
	 * @return d
	 */
	public int getD() {
		return d;
	}

	/**
	 * setter for the int d
	 * @param d
	 */
	public void setD(int d) {
		this.d = d;
	}

	/**
	 * method of type int for getting the value of the patrol boat unit
	 * @return p
	 */
	public int getP() {
		return p;
	}

	/**
	 * setter for the int p
	 * @param p
	 */
	public void setP(int p) {
		this.p = p;
	}
	/**
	 * method of type boolean to get the value of isTwoPlayer
	 * @return isTwoPlayer
	 */
	public boolean isTwoPlayer() {
		return isTwoPlayer;
	}

	/**
	 * method of type void to set the value of isTwoPlayer
	 * @param isTwoPlayer
	 */
	public void setTwoPlayer(boolean isTwoPlayer) {
		this.isTwoPlayer = isTwoPlayer;
	}
	
	/**
	 * These are getters and setter for various JButtons and JLabels to retrieve and set value 
	 * for their respective components
	 * these methods are implemented for class privacy
	 * 
	 */
	
	
	public JButton getAircraftCarrier() {
		return aircraftCarrier;
	}

	public void setAircraftCarrier(JButton aircraftCarrier) {
		this.aircraftCarrier = aircraftCarrier;
	}

	public JButton getBattleShip() {
		return battleShip;
	}

	public void setBattleShip(JButton battleShip) {
		this.battleShip = battleShip;
	}

	public JButton getDestroyer() {
		return destroyer;
	}

	public void setDestroyer(JButton destroyer) {
		this.destroyer = destroyer;
	}

	public JButton getPatrolBoat() {
		return patrolBoat;
	}

	public void setPatrolBoat(JButton patrolBoat) {
		this.patrolBoat = patrolBoat;
	}

	public JButton getSwitchOrientation() {
		return switchOrientation;
	}

	public void setSwitchOrientation(JButton switchOrientation) {
		this.switchOrientation = switchOrientation;
	}

	public JLabel getPlayer() {
		return player;
	}

	public void setPlayer(JLabel player) {
		this.player = player;
	}

	public JLabel getSecondPlayer() {
		return secondPlayer;
	}

	public void setSecondPlayer(JLabel secondPlayer) {
		this.secondPlayer = secondPlayer;
	}

	public JButton getSaveExit() {
		return saveExit;
	}

	public void setSaveExit(JButton saveExit) {
		this.saveExit = saveExit;
	}

	public JButton getMainMenuButton() {
		return mainMenuButton;
	}

	public void setMainMenuButton(JButton mainMenuButton) {
		this.mainMenuButton = mainMenuButton;
	}

	public JLabel getaUnits() {
		return aUnits;
	}

	public void setaUnits(JLabel aUnits) {
		this.aUnits = aUnits;
	}

	public JLabel getbUnits() {
		return bUnits;
	}

	public void setbUnits(JLabel bUnits) {
		this.bUnits = bUnits;
	}

	public JLabel getdUnits() {
		return dUnits;
	}

	public void setdUnits(JLabel dUnits) {
		this.dUnits = dUnits;
	}

	public JLabel getpUnits() {
		return pUnits;
	}

	public void setpUnits(JLabel pUnits) {
		this.pUnits = pUnits;
	}

	
	
}



package battleships_model;

import java.util.Random;
/**
 * This is the Model class in which all the methods of handling the data of the game is carried out
 * @author Abdassami Alvi
 *
 */
public class Battleships_Model {

	private int p1Attempts = 0;
	private int playerShots = 0;
	private int p2Attempts = 0;
	private int player2Shots = 0;
	private int cpuShots = 0;
	private int size = 0;
	private int units = 0;
	private boolean invalidPlacement = false;
	private boolean gameStart = false;
	private int gameType;
	
	private Ship AircraftCarrier = new Ship(5, 1, true);
	private Ship BattleShip = new Ship(4, 1, true);
	private Ship Destroyer1 = new Ship(3, 1, true);
	private Ship Destroyer2 = new Ship(3, 1, true);
	private Ship PatrolBoat = new Ship(2, 1, true);
	
	private int[][] Playerboard = new int[10][10];
	private int[][] Player2board = new int[10][10];
	private int[][] CPUboard = new int[10][10];
	
	private String orientation = "vertical";
	
	/**
	 * method of type void to clear an entire board to a 0 value
	 * which means it is water
	 * 
	 * @param board is the specific board in need of updating
	 * @param player is the specific player for which the board is needed for an update
	 */
	public void initialiseGame(int[][] board, int player){
		int row;
		int column;
		if(player == 1){
			this.setPlayerboard(board);
		}
		else if(player == 2){
			this.setPlayer2board(board);
		}
		else if(player == 3){
			this.setCPUboard(board);
		}
		for(row = 0; row < 10; row++){
			for(column = 0; column < 10; column++){
				if(player == 1){
					getPlayerboard()[row][column] = 0;
				}
				else if(player == 2){
					getPlayer2board()[row][column] = 0;
				}
				else if(player == 3){
					getCPUboard()[row][column] = 0;
				}
			}
		}
	}
	
	/**
	 * method of type void for radomizing the ship placement
	 * it will randomally choose a spot on the board and checks whether the length of the ship will go out of bounds
	 * if so then randomally choose another spot till the latter is false
	 * it then updates the board so that a valid area is occupied by a ship
	 * this is done till all the ships are placed
	 * 
	 * @param board is the specific player board to be passed in
	 * @param player is the specific player of the board that needs updating
	 */
	public void randomizeGame(int[][] board, int player){
		int row;
		int column;
		if(player == 1){
			this.setPlayerboard(board);
		}
		else if(player == 2){
			this.setPlayer2board(board);
		}
		else if(player == 3){
			this.setCPUboard(board);
		}
		Random random = new Random();
		int mark; 
		int orientation;
		int shipSize;
		int shipUnits;
		int temp;
		int a;
		
		switch (player){
			//Player 1
			case 1:
				for(int shipTypes = 1; shipTypes < 6; shipTypes++){
					Ship ship = getShip(shipTypes);
					shipSize = ship.getSize();
					shipUnits = ship.getUnits();
					do{
						orientation = random.nextInt(2);
						//vertical
						if(orientation == 0){
							do{
								mark = 2;
								row = random.nextInt(5);
								column = random.nextInt(10);
								for(temp = shipSize; temp >= 0; temp--){
									if(gridIsOccupied(getPlayerboard()[row+temp][column])){
										mark = 0;
									}
									
								}
								if (temp == -1 && mark == 2){
									mark = 1;
								}
							}while(mark != 1);
							for(a = 0; a < ship.getSize(); a++){
								if(row+a <10){
									if(getPlayerboard()[row+a][column] == 0){
										getPlayerboard()[row+a][column] = 2;
										String boardValue = "(" + (row+a) + "," + column + ")";
										ship.coordinates[a] = boardValue;
									}
								}
							}
							shipUnits = shipUnits - 1;
						}
						//horizontal
						else{
							
							do{
								mark = 2;
								row = random.nextInt(10);
								column = random.nextInt(5);
								for(temp = shipSize; temp >= 0; temp--){
									if(gridIsOccupied(getPlayerboard()[row][column+temp])){
										mark = 0;
									}
								}
								if (temp == -1 && mark == 2){
									mark = 1;
								}
							}while(mark != 1);
							for(a = 0; a < ship.getSize(); a++){
								if(column+a < 10){
									if(getPlayerboard()[row][column+a] == 0){
										getPlayerboard()[row][column+a] = 2;
										String boardValue = "(" + row + "," + (column+a) + ")";
										ship.coordinates[a] = boardValue;
									}
								}
							}
							shipUnits = shipUnits - 1;
						}
					}while(shipUnits != 0);
				}
				break;
			//Player 2
			case 2:
				for(int shipTypes = 1; shipTypes < 6; shipTypes++){
					Ship ship = getShip(shipTypes);
					shipSize = ship.getSize();
					shipUnits = ship.getUnits();
					do{
						orientation = random.nextInt(2);
						//vertical
						if(orientation == 0){
							do{
								mark = 2;
								row = random.nextInt(5);
								column = random.nextInt(10);
								for(temp = shipSize; temp >= 0; temp--){
									if(gridIsOccupied(getPlayer2board()[row+temp][column])){
										mark = 0;
									}
									
								}
								if (temp == -1 && mark == 2){
									mark = 1;
								}
							}while(mark != 1);
							for(a = 0; a < ship.getSize(); a++){
								if(row+a <10){
									if(getPlayer2board()[row+a][column] == 0){
										getPlayer2board()[row+a][column] = 2;
										String boardValue = "(" + (row+a) + "," + column + ")";
										ship.coordinates[a] = boardValue;
									}
								}
							}
							shipUnits = shipUnits - 1;
						}
						//horizontal
						else{
							
							do{
								mark = 2;
								row = random.nextInt(10);
								column = random.nextInt(5);
								for(temp = shipSize; temp >= 0; temp--){
									if(gridIsOccupied(getPlayer2board()[row][column+temp])){
										mark = 0;
									}
								}
								if (temp == -1 && mark == 2){
									mark = 1;
								}
							}while(mark != 1);
							for(a = 0; a < ship.getSize(); a++){
								if(column+a < 10){
									if(getPlayer2board()[row][column+a] == 0){
										getPlayer2board()[row][column+a] = 2;
										String boardValue = "(" + row + "," + (column+a) + ")";
										ship.coordinates[a] = boardValue;
									}
								}
							}
							shipUnits = shipUnits - 1;
						}
					}while(shipUnits != 0);
				}
				break;
			//CPU
			case 3:
				for(int shipTypes = 1; shipTypes < 6; shipTypes++){
					Ship ship = getShip(shipTypes);
					shipSize = ship.getSize();
					shipUnits = ship.getUnits();
					do{
						orientation = random.nextInt(2);
						//vertical
						if(orientation == 0){
							do{
								mark = 2;
								row = random.nextInt(5);
								column = random.nextInt(10);
								for(temp = shipSize; temp >= 0; temp--){
									if(gridIsOccupied(getCPUboard()[row+temp][column])){
										mark = 0;
									}
									
								}
								if (temp == -1 && mark == 2){
									mark = 1;
								}
							}while(mark != 1);
							for(a = 0; a < ship.getSize(); a++){
								if(row+a <10){
									if(getCPUboard()[row+a][column] == 0){
										getCPUboard()[row+a][column] = 2;
										String boardValue = "(" + (row+a) + "," + column + ")";
										ship.coordinates[a] = boardValue;
										
									}
								}
							}
							shipUnits = shipUnits - 1;
						}
						//horizontal
						else{
							do{
								mark = 2;
								row = random.nextInt(10);
								column = random.nextInt(5);
								for(temp = shipSize; temp >= 0; temp--){
									if(gridIsOccupied(getCPUboard()[row][column+temp])){
										mark = 0;
									}
								}
								if (temp == -1 && mark == 2){
									mark = 1;
								}
							}while(mark != 1);
							for(a = 0; a < ship.getSize(); a++){
								if(column+a < 10){
									if(getCPUboard()[row][column+a] == 0){
										getCPUboard()[row][column+a] = 2;
										String boardValue = "(" + row + "," + (column+a) + ")";
										ship.coordinates[a] = boardValue;
									}
								}
							}
							shipUnits = shipUnits - 1;
						}
					}while(shipUnits != 0);
				}
				break;
		}
	}
	/**
	 * method of type void to place a ship
	 * it will change the values that have been highlighted to an occupied value
	 * 
	 * @param board is the specific player grid
	 * @param player is the specific player for which the ship is being placed for
	 */
	public void placeShip(int[][] board, int player) {
		int row;
		int column;
		if(player == 1){
			this.setPlayerboard(board);
		}
		else if(player == 2){
			this.setPlayer2board(board);
		}
		for(row = 0; row < 10; row++){
			for(column = 0; column < 10; column++){
				if(player == 1){
					if(getPlayerboard()[row][column] == 3){
						getPlayerboard()[row][column] = 2;
					}
				}
				else if(player == 2){
					if(getPlayer2board()[row][column] == 3){
						getPlayer2board()[row][column] = 2;
					}
				}
			}
		}
	}
	/**
	 * method of type int to change a selected coordinate to a miss or a hit 
	 * it checks whether that coordinate is occupied and updates the board value according to the result
	 * 
	 * @param coordinate is the specific coordinate passed in for updating
	 * @param shots is the current amount of shots for a specific player
	 * @param board is the specific board which is in need of updating
	 * @return the up to date value of shots
	 */
	public int shoot(String coordinate, int shots, int[][] board){
		int row;
		int column;
		String boardValue;
		for(row = 0; row < 10; row++){
			for(column = 0; column < 10; column++){
				boardValue = "(" + row + "," + column + ")";
				if(boardValue.equals(coordinate)){
					if(gridIsOccupied(board[row][column])){
						 board[row][column] = 1;
						 shots++;
						 for(int shipTypes = 1; shipTypes < 6; shipTypes++){
								Ship ship = getShip(shipTypes);
								for(int i = 0; i < ship.getSize(); i++ ){
									if(boardValue.equals(ship.coordinates[i])){
										ship.coordinates[i] = "X";
									}
								}
							}
					}
					else if(!gridIsOccupied(board[row][column])){
						 board[row][column] = -1;
					}
					
				}
			}
		}
		return shots;
	}
	/**
	 * method of type boolean to check whether a ship has been sunk or not
	 * @return the result
	 */
	public boolean shipSunk(){
		String coordinate = "X";
		boolean hasSunk = false;
		for(int shipTypes = 1; shipTypes < 6; shipTypes++){
			int a = 0;
			Ship ship = getShip(shipTypes);
			for(int i = 0; i < ship.getSize(); i++ ){
				
				if(coordinate.equals(ship.coordinates[i])){
					a++;
					if(a == ship.getSize() && ship.isActive == true){
						hasSunk = true;
						ship.isActive = false;
					}
				}
			}
		}
		return hasSunk;
	}
		
	/**
	 * method of type String[][] for updating the entire grid
	 * @param board specific board is passed in for updating
	 * @return the latest update of the grid
	 */
	public String[][] getCoordinate(int[][] board){
		int row;
		int column;
		String string[][] = new String[10][10];
		for(row = 0; row < 10; row++){
			for(column = 0; column < 10; column++){
				if(board[row][column] == -1){
					string[row][column] = "*";
				}
				else if(board[row][column] == 0){
					string[row][column] = "~";
				}
				else if(board[row][column] == 1){
					string[row][column] = "X";
				}
				else if(board[row][column] == 2){
					string[row][column] = "O";
				}
				else if(board[row][column] == 3){
					string[row][column] = "H";
				}
			}
		}
		return string;
	}
	/**
	 * method of type string for updating a specific coordinate
	 * @param coordinate a specific coordinate is passed in for updating
	 * @param board a specific board is passed in for which the coordinate is updated on
	 * @return the latest update
	 */
	public String updateCoordinate(String coordinate, int[][] board){
		String result = null;
		String boardValue;
		int row;
		int column;
		for(row = 0; row < 10; row++){
			for(column = 0; column < 10; column++){
				boardValue = "(" + row + "," + column + ")";
				if(boardValue.equals(coordinate)){
					if(board[row][column] == -1){
						result = "*";
					}
					else if(board[row][column] == 0){
						result = "~";
					}
					else if(board[row][column] == 1){
						result = "X";
					}
					else if(board[row][column] == 2){
						result = "O";
					}
					else if(board[row][column] == 3){
						result = "H";
					}
				}
			}
		}
		
		return result;
	}
	/**
	 * method of type ship for retrieving a ship type
	 * @param i value passed in for retrieving referencing
	 * @return a ship
	 */
	public Ship getShip(int i){
		Ship ship;
		if(i == 1){
			ship = this.getAircraftCarrier();
			return ship;
		}
		else if(i == 2){
			return this.getBattleShip();
		}
		else if(i == 3){
			return this.getDestroyer1();
		}
		else if(i == 4){
			return this.getDestroyer2();
		}
		else if(i == 5){
			return this.getPatrolBoat();
		}
		return null;
	}
	
	/**
	 * method of type boolean to check if a coordinate is occupied by a ship
	 * @param board a coordinate passed in for checking
	 * @return returns a boolean statement 
	 */
	public boolean gridIsOccupied(int board){
		if(board == 2){
			return true;
		}
		return false;
	}

	/**
	 * method of type void to switch the current orientation
	 */
	public void switchShip() {
		if(orientation == "vertical"){
			orientation = "horizontal";
		}
		else if(orientation == "horizontal" ){
			orientation = "vertical";
		}
	}

	/**
	 * method of type string to retrieve the next coordinate from a reference point
	 * @param coordinate is the reference point from which to retrieve the next coordinate
	 * @return the next coordinate
	 */
	public String getNextCoordinate(String coordinate) {

		int row;
		int column;
		String boardValue = null;
		String nextCoordinate = null;
		for(row = 0; row < 10; row++){
			for(column = 0; column < 10; column++){
				boardValue = "(" + row + "," + column + ")";
				if(boardValue.equals(coordinate)){
					if(orientation == "vertical"){
						nextCoordinate = "(" + (row+1) + "," + column + ")";
					}
					if(orientation == "horizontal"){
						nextCoordinate = "(" + row + "," + (column+1) + ")";
					}
				}
			}
		}
		return nextCoordinate;
	}
	
	/**
	 * method of type string to retrieve the previous coordinate from a reference point
	 * @param coordinate is the reference point from which to retrieve the previous coordinate
	 * @return the previous coordinate
	 */
	public String getPrevCoordinate(String coordinate) {

		int row;
		int column;
		String boardValue = null;
		String prevCoordinate = null;
		for(row = 0; row < 10; row++){
			for(column = 0; column < 10; column++){
				boardValue = "(" + row + "," + column + ")";
				if(boardValue.equals(coordinate)){
					if(orientation == "vertical"){
						prevCoordinate = "(" + (row-1) + "," + column + ")";
					}
					if(orientation == "horizontal"){
						prevCoordinate = "(" + row + "," + (column-1) + ")";
					}
				}
			}
		}
		return prevCoordinate;
	}
	
	/**
	 * method of type boolean to highlight the buttons on the grid
	 * it will highlight the amount for the length of a chosen ship
	 * it will reverse the highlight if the mouse moves out of bound of a specified area
	 * 
	 * @param coordinate is the first coordinate and reference for highlighting the grid
	 * @param size is the length of a chosen ship
	 * @param board is the specific board on which to highlight
	 * @return whether a highlighted area would be a valid placement or not
	 */
	public boolean highlight(String coordinate, int size, int[][] board) {
		int row;
		int column;
		String boardValue = null;
		String temp = null;
		boolean usePrev = false;
		boolean invalid = false;
		for(row = 0; row < 10; row++){
			for(column = 0; column < 10; column++){
				boardValue = "(" + row + "," + column + ")";
				if(boardValue.equals(coordinate)){
					if(board[row][column] != 2){
						board[row][column] = 3;
						//invalid = false;
						if(orientation == "vertical"){ 
							if(row + size >10){
								usePrev = true;
							}
						}
						else if(orientation == "horizontal"){
							if(column + size >10){
								usePrev = true;
							}
						}
		
						for(int i = 1; i<size; i++){
							if(usePrev == false){
								temp = getNextCoordinate(boardValue);
							}
							else if(usePrev == true){
								temp = getPrevCoordinate(boardValue);
							}
							for(row = 0; row < 10; row++){
								for(column = 0; column < 10; column++){
									boardValue = "(" + row + "," + column + ")";
									if(boardValue.equals(temp)){
										if(board[row][column] != 2){
											board[row][column] = 3;
											//invalid = false;
										}
										else{
											invalid = true;
										}
									}
								}
							}
							boardValue = temp;
						}
						
						
					}
					else{
						invalid = true;
					}
				}
			}
		}
		return invalid;
	}
	/**
	 * method of type void to remove any current highlights from the grid
	 * @param board is passed as a players grid
	 */
	public void removeHighlight(int[][] board){
		int row;
		int column;
		for(row = 0; row < 10; row++){
			for(column = 0; column < 10; column++){
				if(board[row][column] == 3){
					board[row][column] = 0;
				}
			}
		}
	}
	/**
	 * method of type int to implement to computer playing function
	 * the AI randomly selects a coordinate on the player map and strategically
	 * shoots the board if it has found a ship
	 * @param board is passed in as the players grid
	 * @param cpuShots is passed in to keep count of how many hits the cpu has made
	 * @return an up to date value of cpuShots
	 */
	public int aI(int[][] board, int cpuShots) {
		int row;
		int column;
		Random random = new Random();
		boolean nextGuess = false;
		int orientation = random.nextInt(2);
		int i = 1;
		
		//first guess
		do{
			row = random.nextInt(10);
			column = random.nextInt(10);
		}while(board[row][column] == -1 || board[row][column] == 1);
		
		if(gridIsOccupied(board[row][column])){
			 this.getPlayerboard()[row][column] = 1;
			 nextGuess = true;
			 cpuShots++;
		}
		else if(!gridIsOccupied(board[row][column])){
			 this.getPlayerboard()[row][column] = -1;
			 nextGuess = false;
		}
		if(nextGuess == true){
			do{
				//vertical
				
				
				if(orientation == 0){
					if(row+i >9){
						if(board[row-i][column] == -1 || board[row-i][column] == 1){
							nextGuess = false;
						}
						else if(gridIsOccupied(board[row-i][column])){
							 this.getPlayerboard()[row-i][column] = 1;
							 nextGuess = true;
							 cpuShots++;
						}
						else if(!gridIsOccupied(board[row-i][column])){
							 this.getPlayerboard()[row-i][column] = -1;
							 nextGuess = false;
						}
					}
					else if(row-i < 0){
						if(board[row+i][column] == -1 || board[row+i][column] == 1){
							nextGuess = false; 
						}
						else if(gridIsOccupied(board[row+i][column])){
							 this.getPlayerboard()[row+i][column] = 1;
							 nextGuess = true;
							 cpuShots++;
						}
						else if(!gridIsOccupied(board[row+1][column])){
							 this.getPlayerboard()[row+i][column] = -1;
							 nextGuess = false;
						}
					}
					else{
						if(board[row+i][column] == -1 || board[row+i][column] == 1){
							nextGuess = false;
						}
						else if(gridIsOccupied(board[row+i][column])){
							 this.getPlayerboard()[row+i][column] = 1;
							 nextGuess = true;
							 cpuShots++;
						}
						else if(!gridIsOccupied(board[row+1][column])){
							 this.getPlayerboard()[row+i][column] = -1;
							 nextGuess = false;
						}
					}
					
					i++;
				}
				//horizontal
				else{
					if(column+i > 9){
						if(board[row][column-i] == -1 || board[row][column-i] == 1){
							nextGuess = false;
						}
						else if(gridIsOccupied(board[row][column-i])){
							 this.getPlayerboard()[row][column-i] = 1;
							 nextGuess = true;
							 cpuShots++;
						}
						else if(!gridIsOccupied(board[row][column-i])){
							 this.getPlayerboard()[row][column-i] = -1;
							 nextGuess = false;
						}
					}
					else if(column-i > 0){
						if(board[row][column+i] == -1 || board[row][column+i] == 1){
							nextGuess = false;
						}
						else if(gridIsOccupied(board[row][column+i])){
							 this.getPlayerboard()[row][column+i] = 1;
							 nextGuess = true;
							 cpuShots++;
						}
						else if(!gridIsOccupied(board[row][column+1])){
							 this.getPlayerboard()[row][column+i] = -1;
							 nextGuess = false;
						}
					}
					else{
						if(board[row][column+i] == -1 || board[row][column+i] == 1){
							nextGuess = false;
						}
						else if(gridIsOccupied(board[row][column+i])){
							 this.getPlayerboard()[row][column+i] = 1;
							 nextGuess = true;
							 cpuShots++;
						}
						else if(!gridIsOccupied(board[row][column+1])){
							 this.getPlayerboard()[row][column+i] = -1;
							 nextGuess = false;
						}
					}
					i++;
				}
				
			}while(nextGuess == true);
		}
		return cpuShots;
	}

	/**
	 * These are getters and setter for various data types to retrieve and set value 
	 * for their respective components
	 * these methods are implemented for class privacy
	 * 
	 */
	public int[][] getPlayerboard() {
		return Playerboard;
	}

	public void setPlayerboard(int[][] playerboard) {
		Playerboard = playerboard;
	}

	public int[][] getCPUboard() {
		return CPUboard;
	}

	public void setCPUboard(int[][] cPUboard) {
		CPUboard = cPUboard;
	}

	public int[][] getPlayer2board() {
		return Player2board;
	}

	public void setPlayer2board(int[][] player2board) {
		Player2board = player2board;
	}

	public Ship getAircraftCarrier() {
		return AircraftCarrier;
	}

	public void setAircraftCarrier(Ship aircraftCarrier) {
		AircraftCarrier = aircraftCarrier;
	}

	public Ship getBattleShip() {
		return BattleShip;
	}

	public void setBattleShip(Ship battleShip) {
		BattleShip = battleShip;
	}

	public Ship getDestroyer1() {
		return Destroyer1;
	}

	public void setDestroyer1(Ship destroyer1) {
		Destroyer1 = destroyer1;
	}

	public Ship getDestroyer2() {
		return Destroyer2;
	}

	public void setDestroyer2(Ship destroyer2) {
		Destroyer2 = destroyer2;
	}

	public Ship getPatrolBoat() {
		return PatrolBoat;
	}

	public void setPatrolBoat(Ship patrolBoat) {
		PatrolBoat = patrolBoat;
	}

	public int getP1Attempts() {
		return p1Attempts;
	}

	public void setP1Attempts(int p1Attempts) {
		this.p1Attempts = p1Attempts;
	}

	public int getPlayerShots() {
		return playerShots;
	}

	public void setPlayerShots(int playerShots) {
		this.playerShots = playerShots;
	}

	public int getCpuShots() {
		return cpuShots;
	}

	public void setCpuShots(int cpuShots) {
		this.cpuShots = cpuShots;
	}

	public int getP2Attempts() {
		return p2Attempts;
	}

	public void setP2Attempts(int p2Attempts) {
		this.p2Attempts = p2Attempts;
	}

	public int getPlayer2Shots() {
		return player2Shots;
	}

	public void setPlayer2Shots(int player2Shots) {
		this.player2Shots = player2Shots;
	}

	public boolean isGameStart() {
		return gameStart;
	}

	public void setGameStart(boolean gameStart) {
		this.gameStart = gameStart;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public int getUnits() {
		return units;
	}

	public void setUnits(int units) {
		this.units = units;
	}

	public boolean isInvalidPlacement() {
		return invalidPlacement;
	}

	public void setInvalidPlacement(boolean invalidPlacement) {
		this.invalidPlacement = invalidPlacement;
	}

	public int getGameType() {
		return gameType;
	}

	public void setGameType(int gameType) {
		this.gameType = gameType;
	}


	
}

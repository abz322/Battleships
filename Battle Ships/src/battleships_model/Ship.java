package battleships_model;
/**
 * this is the Ship class that will hold the properties of a ship
 * @author Abdassami Alvi
 *
 */
public class Ship {

	private int size;
	private int units;
	String[] coordinates;
	boolean isActive;

	/**
	 * this is the constructor of the class Ship
	 * 
	 * @param size is the length of a ship
	 * @param units is how many number of a particular ship there is
	 * @param isActive is a boolean that determis whether a ship has been sunk
	 */
	public Ship(int size, int units, boolean isActive) {
		// TODO Auto-generated constructor stub
		this.setSize(size);
		this.setUnits(units);
		this.coordinates = new String[this.getSize()];
		this.isActive = isActive;
	}


	/**
	 * method function for getting the size of a ship of type void
	 * @return the size of a ship
	 */
	public int getSize() {
		return size;
	}

	/**
	 * method of type void to set the ships size
	 * @param size is the length of a ship
	 */
	public void setSize(int size) {
		this.size = size;
	}

	/**
	 * method of type void for getting the units of a ship
	 * @return the amount of a ship type
	 */
	public int getUnits() {
		return units;
	}

	/**
	 * method of type void to set the ship units
	 * @param units the amount of a ship type
	 */
	public void setUnits(int units) {
		this.units = units;
	}

	
}

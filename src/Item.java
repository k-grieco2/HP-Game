/**
 * Class to implement items into the game.
 * 
 * @author kierstengrieco
 *
 */
public class Item {
	/** The name of the item. */
	private String name;
	/** The description of the item. */
	private String description;
	/** The number of points associated with the item. */
	private int points;
	/** The weight of the item. */
	private int weight;
	
	/**
	 * Constructor with each of the item values.
	 * 
	 * @param name The name of the item.
	 * @param description A short description of the item.
	 * @param points Point value associated with the item.
	 * @param weight The carrying weight of the item.
	 */
	public Item(String name, String description, int points, int weight) {
		this.name = name;
		this.description = description;
		this.points = points;
		this.weight = weight;
	}
	
	/**
	 * Return the name of the item.
	 * 
	 * @return The name of the item.
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Return the description of the item.
	 * 
	 * @return The description of the item.
	 */
	public String getDescription() {
		return description;
	}
	
	/**
	 * Mutator for the description of the item.
	 * 
	 * @param newString The new description.
	 */
	public void setDescription(String newString) {
		description = newString;
	}
	
	public int getPoints() {
		return points;
	}
	
	/**
	 * Method to change the point value of the item.
	 * 
	 * @param value New point value.
	 */
	public void setPoints(int value) {
		points = value;
	}
	
	/**
	 * Accessor for the weight of an item.
	 * 
	 * @return The weight of the item.
	 */
	public int getWeight() {
		return weight;
	}
	
	/**
	 * Returns the complete description of the item as a string.
	 * 
	 * @return String version of the name and description.
	 */
	public String toString() {
		return(name + "\n" + description);
	}
	
}

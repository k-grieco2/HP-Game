/**
 * Class Door - a door or portal between two Rooms in an adventure game.
 *
 * This class is part of the "Campus of Kings" application. "Campus of Kings" is a
 * very simple, text based adventure game.
 *
 * A "Door" represents a door or portal between two locations of the game.
 * It stores a reference to the neighboring room and whether that door
 * or portal is locked.  Doors are not locked by default.
 *
 * @author Maria Jump
 * @version 2015.02.01
 *
 * Used with permission from Dr. Maria Jump at Northeastern University
 */
public class Door {

	/** The room that this door leads to. */
	private Room destination;
	/** Whether this door is locked. */
	private boolean locked;
	/** Key storage. */
	private Item key;
	/** Whether or not a door is visible. */
	private boolean visible;
	
	/**
	 * Constructor for the Door class.
	 * @param destination The room this door leads to
	 */
	public Door(Room destination) {
		this.destination = destination;
		this.locked = false;
	}

	/**
	 * A getter for the room this door leads to.
	 * @return The room this door leads to
	 */
	public Room getDestination() {
		return destination;
	}

	/**
	 * A getter for whether this door is locked.
	 * @return Whether this door is locked
	 */
	public boolean isLocked() {
		return locked;
	}

	/**
	 * A setter for whether this door is locked.
	 * @param locked Whether this door is locked.
	 */
	public void setLocked(boolean locked) {
		this.locked = locked;
	}

	/**
	 * Gets the key required to unlock the door.
	 * 
	 * @return The key to the door.
	 */
	public Item getKey() {
		return key;
	}

	/**
	 * Sets the key to its door.
	 * 
	 * @param newkey The key that unlocks the door.
	 */
	public void setKey(Item newkey) {
		key = newkey;
	}
	
	/**
	 * Returns whether the door is visible.
	 * 
	 * @return Whether the door is visible.
	 */
	public boolean isVisible() {
		return visible;
	}
	
	/**
	 * Sets whether or not a door is visible.
	 * 
	 * @param isvisible
	 */
	public void setVisible(boolean isvisible) {
		visible = isvisible;
	}
	

}

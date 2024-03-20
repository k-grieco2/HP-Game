
import java.util.HashMap;

/**
 * Class Room - a room in an adventure game.
 *
 * This class is part of the "Campus of Kings" application. "Campus of Kings" is a
 * very simple, text based adventure game.
 *
 * A "Room" represents one location in the scenery of the game. It is connected
 * to other rooms via doors. The doors are labeled north, east, south, west.
 * For each direction, the room stores a reference to an instance of door.
 *
 * @author Maria Jump
 * @version 2015.02.01
 *
 * Used with permission from Dr. Maria Jump at Northeastern University
 */
public class Room {
	/** Counter for the total number of rooms created in the world. */
	private static int counter;
	/** The name of this room.  Room names should be unique. */
	private String name;
	/** The description of this room. */
	private String description;
	/** The amount of points earned for entering a room. */
	private int points;
	/** The exits associated with each door. */
	private HashMap<String, Door> exits = new HashMap<>();
	/** List to store items in a Room. */
	private HashMap<String, Item> stuff = new HashMap<>();
	/** Stores hidden exits associated with each door. */
	private HashMap<String, Door> hiddenexits = new HashMap<>();
	
	/**
	 * Static initializer.
	 */
	static {
		counter = 0;
	}
	/**
	 * Create a room described "description". Initially, it has no exits.
	 * "description" is something like "a kitchen" or "an open court yard".
	 *
	 * @param name  The room's name.
	 * @param description
	 *            The room's description.
	 */
	public Room(String name, String description) {
		this.name = name;
		this.description = description;
		counter++;
	}

	/**
	 * Returns the name of this room.
	 *
	 * @return The name of this room.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Returns the description of this room.
	 *
	 * @return The description of this room.
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Returns the number of rooms that have been created in the world.
	 * @return The number of rooms that have been created in the world.
	 */
	public static int getCounter() {
		return counter;
	}
	
	/**
	 * Returns a string description including all the details of a Room.
	 * For example,
	 * 		 Outside:
	 * 		 You are outside in the center of the King's College Campus.
	 * 		 Exits: north east south west
	 * 
	 * @return A string representing all the details of a room.
	 */
	public String toString() {
		String exit = "Exits: ";
		String items = "";
		if (exits.get("north") != null) {
			exit = exit + "north ";
		}
		if (exits.get("east") != null) {
			exit = exit + "east ";
		}
		if (exits.get("south") != null) {
			exit = exit + "south ";
		}
		if (exits.get("west") != null) {
			exit = exit + "west ";
		}
		if (stuff == null) {
			items = " none";
		}
		else {
			for (Item object : stuff.values()) {
				if (object.getWeight() > 40) {
					if (items.equals("")) {
						items = items + " " + object.getName();
					}
					else {
						items = items + ", " + object.getName();
					}
				}
			}
			if (items.equals("")) {
				items = " none";
			}
		}
		String st = name + ": " + "\n" + description;
		if (stuff.get("fly") !=  null) {
			st = st + "\n There is a fly buzzing around the room!";
		}
		st = st + "\n" + exit + "\n" + "Items:" + items;
		return st;
		
	}
	
	///////////////////////////////////////////////////////////////////////////
	// Mutator method for changing the value of points.
	/**
	 * This method will allow you to set the value of points to something new.
	 * 
	 * 
	 * @param newpoints
	 *            The new value passed in to update the points field.
	 * @return points
	 * 			  The new value of points is returned.
	 */
	public int setPoints(int newpoints) {
		points = newpoints;
		return points;
	}
	
	/** Accessor to return the value of points and reset it to zero. 
	 * 
	 * 
	 * @return newpoints
	 * 			  The value of points that is accessed.
	 * */
	public int getPoints() {
		int newpoints = points;
		if (newpoints != 0) {
			points = 0;
		}
		return newpoints;
	}

	/**
	 * Defines an exit from this room.
	 * 
	 * @param direction The direction of the exit.
	 * @param neighbor The door in the given direction.
	 */
	public void setExit (String direction, Door neighbor) {
		if (neighbor.isVisible()) {
			exits.put(direction, neighbor);
			if (hiddenexits.containsValue(neighbor)) {
				hiddenexits.remove(direction);
			}
		}
		else {
			hiddenexits.put(direction, neighbor);
			if (exits.containsValue(neighbor)) {
				exits.remove(direction);
			}
		}
	}
	
	/**
	 * Method that allows doors to be changed to visible or invisible.
	 * 
	 * @param direction The direction of the door.
	 * @param visible Whether the door should be visible or invisible.
	 */
	public void setVisible(String direction, boolean visible) {
		if(hiddenexits.containsKey(direction)) {
			hiddenexits.get(direction).setVisible(visible);
			this.setExit(direction, hiddenexits.get(direction));
		}
		else if (exits.containsKey(direction)) {
			exits.get(direction).setVisible(visible);
			this.setExit(direction, exits.get(direction));
		}
	}
	
	
	/**
	 * Gets a door in a specified direction if it exists.
	 * 
	 * @param direction The direction of the exit.
	 * @return The door in the specified direction or null if it does not exist.
	 */
	public Door getExit(String direction) {
		 return exits.get(direction);
	}
	
	/**
	 * Adds item to the room.
	 * 
	 * @param thing The item to be added.
	 */
	public void addItem(Item thing) {
		stuff.put(thing.getName().toLowerCase(), thing);
	}
	
	/**
	 * Gets item of an input name.
	 * 
	 * @param itemname The name of the item.
	 * @return The item associated with the name.
	 */
	public Item getItem(String itemname) {
		Item roomitem = null;
		if (stuff.containsKey(itemname)) {
			roomitem = stuff.get(itemname);
		}
		return roomitem;
	}
	
	/**
	 * Removes an item from the room.
	 * 
	 * @param itemname The name of the item.
	 * @return The removed item.
	 */
	public Item removeItem(String itemname) {
		Item removed = null;
		if (stuff.containsKey(itemname)) {
			removed = stuff.get(itemname);
			stuff.remove(itemname);
		}
		return removed;
	}
}

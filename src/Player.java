import java.util.HashMap;

/** Heading.
 * @author kierstengrieco
 * 
 * This class will track movements of the Player, and store and communicate them to
 * the game.
 * 
*/

public class Player {
	
	/** This initializes a new room. */
	private Room currentRoom;
	/** This initializes the player's inventory. */
	private HashMap<String, Item> inventory = new HashMap<>();
	/** Maximum carry weight for the player. */
	private int capacity = 40;
	
	/** This sets the initial "current room" value to be the beginning room.  
	 * 
	 * @param beginRoom Sets the current room to the beginning room.
	 * 
	 * */
	public Player(Room beginRoom) {
		setcurrentRoom(beginRoom);
	}

	/**
	 * Accessor method for the name of the current room.
	 * 
	 * @return The current room.
	 */
	public Room getcurrentRoom() {
		return currentRoom;
	}

	/**
	 * Mutator method for the current room.
	 * 
	 * @param currentRoomie The room the player is moving to.
	 */
	public void setcurrentRoom(Room currentRoomie) {
		currentRoom = currentRoomie;
	}
	
	/**
	 * Method to add an item to the player's inventory.
	 * 
	 * @param thisitem The item to be added.
	 */
	public void addItem(Item thisitem) {
		//if (thisitem.getWeight() <= capacity) {
			//int carrying = 0;
			//for (Item object : inventory.values()) {
				//carrying = carrying + object.getWeight();
			//}
			//if ((carrying + thisitem.getWeight()) > capacity) {
				//Writer.println("You're carrying too much! Drop something to pick up this item.");
			//}
			//else {
				inventory.put(thisitem.getName().toLowerCase(), thisitem);
				Writer.println(thisitem.getName() + " was added to your bag.");
			//}
		//}
		//else {
			//Writer.println("This item is too heavy to pick up!");
		//}
	}
	
	/**
	 * Retrieves an item from the player's inventory by its name.
	 * 
	 * @param name The name of the item.
	 * @return The item with the entered name.
	 */
	public Item getItem(String name) {
		Item thing = null;
		if (inventory.containsKey(name)) {
			thing = inventory.get(name);
		}
		return thing;
	}
	
	/**
	 * Removes item from the player's inventory.
	 * 
	 * @param itemname The name of the item to be removed.
	 * @return The removed item.
	 */
	public Item removeItem(String itemname) {
		Item removable = null;
		if (inventory.containsKey(itemname)) {
			removable = inventory.get(itemname);
			inventory.remove(itemname);
		}
		return (removable);
	}
	
	/**
	 * Method to print inventory as a string.
	 */
	public void printInventory() {
		if (inventory == null) {
			Writer.println("There is nothing in your inventory!");
		}
		else {
			Writer.println("Inventory: ");
			for (Item item : inventory.values()) {
				Writer.println("	" + item.getName());
			}
		}
	}
	
	/**
	 * Method to determine if there is enough room in a player's inventory to pick up an item.
	 * 
	 * @param thisitem The item to be picked up.
	 * @return Whether the item may be picked up.
	 */
	public boolean canPickUp(Item thisitem){
		boolean bool;
		if (thisitem.getWeight() <= capacity) {
			int carrying = 0;
			for (Item object : inventory.values()) {
				carrying = carrying + object.getWeight();
			}
			if ((carrying + thisitem.getWeight()) > capacity) {
				Writer.println("You're carrying too much! Drop something to pick up this item.");
				bool = false;
			}
			else {
				bool = true;
			}
		}
		else {
			Writer.println("This item is too heavy to pick up!");
			bool = false;
		}
		return bool;
	}
	
	/**
	 * Method to return how much weight the player can carry.
	 * 
	 * @return The weight the player can carry.
	 */
	public int getCapacity() {
		return capacity;
	}
	
}

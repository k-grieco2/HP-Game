import java.util.HashMap;

/**
 * Class to create containers.
 * 
 * @author kierstengrieco
 *
 */
public class Container extends Item {
	/** Items in the container. */
	private HashMap<String, Item> items = new HashMap<>();
	
	/**
	 * Default container constructor.
	 * 
	 * @param name
	 * @param description
	 * @param points
	 * @param weight
	 */
	public Container(String name, String description, int points, int weight) {
		super(name, description, points, weight);
	}
	
	/**
	 * Add item to the container.
	 * 
	 * @param item The item to be added.
	 */
	public void addItem(Item item) {
		items.put(item.getName().toLowerCase(), item);
	}

	/**
	 * Remove an item from the container.
	 * 
	 * @param name The name of the item to be removed.
	 * @return The removed item.
	 */
	public Item removeItem(String name) {
		Item thing = items.get(name);
		if (thing != null) {
			items.remove(name);
		}
		return thing;
	}
	
	/**
	 * Returns the container as a string.
	 * 
	 * @return String representation of the container.
	 */
	public String toString() {
		String bigstring = "";
		for (Item item : items.values()) {
			if (bigstring.equals("")) {
				bigstring += " " + item.getName();
			}
			else {
				bigstring += ", " + item.getName();
			}
			
		}
		if (bigstring.equals("")) {
			bigstring = " none";
		}
		return(getName() + "\n" + getDescription() + "\n" + "Contains:" + bigstring);
	}
	
	/**
	 * Retrieve an item from the container.
	 * 
	 * @param itemname The name of the item insider the container.
	 * @return The item inside the container.
	 */
	public Item getItemInContainer(String itemname) {
		return items.get(itemname);
	}
}

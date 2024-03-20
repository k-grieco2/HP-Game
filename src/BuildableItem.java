import java.util.HashMap;

/**
 * This class creates a new subclass of the Item class for making
 * different items out of pre-existing game items.
 * 
 * @author kierstengrieco
 *
 */
public class BuildableItem extends Item{
	/** HashMap to hold the collection of items needed to build this item. */
	private HashMap<String, Item> ingredients = new HashMap<>();
	/** Boolean to indicate whether the item is complete. */
	private boolean complete;
	/** Boolean to indicate whether item can be dismantled. */
	private boolean dismantle;
	
	/**
	 * Constructor for an object of the buildable item class.
	 * 
	 * @param name The name of the item.
	 * @param description The description of the item.
	 * @param points The point value of the item.
	 * @param weight The weight of the item.
	 */
	public BuildableItem(String name, String description, int points, int weight) {
		super(name, description, points, weight);
		dismantle = true;
	}
	
	/**
	 * Returns whether the item is complete.
	 * 
	 * @return Whether or not the item is complete.
	 */
	public boolean isComplete() {
		return complete;
	}
	
	/**
	 * Changes whether the item is complete.
	 * 
	 * @param iscomplete Whether or not the item is complete.
	 */
	public void setComplete(boolean iscomplete) {
		complete = iscomplete;
	}
	
	/**
	 * Adds ingredient to the list of items necessary to create
	 * the buildable item.
	 * 
	 * @param ingredient The ingredient.
	 */
	public void addIngredient(Item ingredient) {
		ingredients.put(ingredient.getName(), ingredient);
	}
	
	/**
	 * To retrieve the list of ingredients needed to make the buildable item.
	 * 
	 * @return The ingredients in the form of a HashMap.
	 */
	public HashMap<String, Item> getIngredients() {
		return ingredients;
	}
	
	/**
	 * To check whether the buildable item may be dismantled.
	 * 
	 * @return Whether the item may be dismantled.
	 */
	public boolean canBeDismantled() {
		return dismantle;
	}
	
	/**
	 * Allows the player to set whether a buildable item may be dismantled.
	 * 
	 * @param candismantle
	 */
	public void setDismantle(boolean candismantle) {
		dismantle = candismantle;
	}
	
	

}

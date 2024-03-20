import java.util.HashMap;

/**
 * This class is part of the "Campus of Kings" application. "Campus of Kings" is a
 * very simple, text based adventure game.
 *
 * This class holds an enumeration of all command words known to the game. It is
 * used to recognize commands as they are typed in.
 *
 * @author Maria Jump
 * @version 2015.02.01
 *
 * Used with permission from Dr. Maria Jump at Northeastern University
 */

public class CommandWords {
	/** A constant array that holds all valid command words. */
	private static HashMap<String, CommandEnum> validCommands = new HashMap<>();

	/**
	 * Static block to initialize the fields of CommandWords.
	 */
	static {
		validCommands.put(CommandEnum.GO.getName(), CommandEnum.GO);
		validCommands.put(CommandEnum.QUIT.getName(), CommandEnum.QUIT);
		validCommands.put(CommandEnum.HELP.getName(), CommandEnum.HELP);
		validCommands.put(CommandEnum.LOOK.getName(), CommandEnum.LOOK);
		validCommands.put(CommandEnum.STATUS.getName(), CommandEnum.STATUS);
		validCommands.put(CommandEnum.BACK.getName(), CommandEnum.BACK);
		validCommands.put(CommandEnum.EXAMINE.getName(), CommandEnum.EXAMINE);
		validCommands.put(CommandEnum.TAKE.getName(), CommandEnum.TAKE);
		validCommands.put(CommandEnum.DROP.getName(), CommandEnum.DROP);
		validCommands.put(CommandEnum.INVENTORY.getName(), CommandEnum.INVENTORY);
		validCommands.put(CommandEnum.UNLOCK.getName(), CommandEnum.UNLOCK);
		validCommands.put(CommandEnum.LOCK.getName(), CommandEnum.LOCK);
		validCommands.put(CommandEnum.UNPACK.getName(), CommandEnum.UNPACK);
		validCommands.put(CommandEnum.PACK.getName(), CommandEnum.PACK);
		validCommands.put(CommandEnum.BUILD.getName(), CommandEnum.BUILD);
		validCommands.put(CommandEnum.DISMANTLE.getName(), CommandEnum.DISMANTLE);
		validCommands.put(CommandEnum.DRINK.getName(), CommandEnum.DRINK);
		validCommands.put(CommandEnum.EAT.getName(), CommandEnum.EAT);
		validCommands.put(CommandEnum.UP.getName(), CommandEnum.UP);
		validCommands.put(CommandEnum.CAST.getName(), CommandEnum.CAST);
	}
	
	
	/**
	 * Check whether a given String is a valid command word.
	 *
	 * @param aString The string to determine whether it is a valid command.
	 * @return true if a given string is a valid command, false if it isn't.
	 */
	public static boolean isCommand(String aString) {
		boolean valid = false;
		int index = 0;
		while (!valid && index < validCommands.size()) {
			if (!(validCommands.get(aString) == null)) {
				valid = true;
			}
			index++;
		}
		// if we get here, the string was not found in the commands
		return valid;
	}
	
	/**
	 * Converts a String into a CommandEnum object.
	 * 
	 * @param theString The String containing the command word.
	 * @return The CommandEnum object representing the command, or null if the command does
	 * 		not exist.
	 */
	public static CommandEnum getCommand(String theString) {
		String capString = theString.toString().toUpperCase();
		CommandEnum newenum  = CommandEnum.valueOf(capString);
		return newenum;
	}
	
	
}

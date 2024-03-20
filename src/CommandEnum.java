public enum CommandEnum {
	/** Go command. */
	GO("go"),
	/** Quit command. */
	QUIT("quit"),
	/** Help command. */
	HELP("help"),
	/** Look command. */
	LOOK("look"),
	/** Status command. */
	STATUS("status"),
	/** Back command. */
	BACK("back"),
	/** Examine command. */
	EXAMINE("examine"),
	/** Take command. */
	TAKE("take"),
	/** Drop command. */
	DROP("drop"),
	/** Inventory command. */
	INVENTORY("inventory"),
	/** Unlock command. */
	UNLOCK("unlock"),
	/** Lock command. */
	LOCK("lock"),
	/** Unpack command. */
	UNPACK("unpack"),
	/** Pack command. */
	PACK("pack"),
	/** Build Command. */
	BUILD("build"),
	/** Dismantle Command. */
	DISMANTLE("dismantle"),
	/** Drink command. */
	DRINK("drink"),
	/** Eat command. */
	EAT("eat"),
	/** Up command. */
	UP("up"),
	/** Cast command. */
	CAST("cast");
	
	/** The name of the command that will be entered. */
	private String name;
	
	/**
	 * Constructs a new command.
	 * 
	 * @param name Name to be referenced when calling the command.
	 */
	private CommandEnum(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}

}

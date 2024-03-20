import java.util.HashMap;

/**
 * This class represents the entire world that makes up the "Campus of Kings"
 * application. "Campus of Kings" is a very simple, text based adventure game.
 * Users can walk around some scenery. That's all. It should really be extended
 * to make it more interesting!
 *
 * This world class creates the world where the game takes place.
 *
 * @author Maria Jump
 * @version 2015.02.01
 *
 * Used with permission from Dr. Maria Jump at Northeastern University
 */
public class World {
	/** The rooms in the world. */
	private HashMap<String, Room> rooms = new HashMap<>();
	/** The buildable items in the world. */
	private HashMap<String, BuildableItem> buildables = new HashMap<>();

	/**
	 * Constructor for the world.
	 */
	public World() {
		rooms = new HashMap<String, Room>();
		createRooms();
		createItems();
	}

	/**
	 * This method takes care of creating all of the aspects of the world for
	 * the "Campus of Kings" application.
	 *
	 * @param name
	 *            The provided name of the room.
	 * @return The room associated with the provided name
	 */
	public Room getRoom(String name) {
		return rooms.get(name.toLowerCase());
	}

	/////////////////////////////////////////////////////////////////////////////////////
	// Start of private helper methods

	/**
	 * Helper method for recreating a Room. Ensure that the room is created and
	 * installed in to the collection of Rooms
	 *
	 * @param theRoom
	 *            The room to add to the world.
	 */
	private void addRoom(Room theRoom) {
		rooms.put(theRoom.getName().toLowerCase(), theRoom);
	}
	
	/**
	 * Adds buildable item to the collection of buildable items.
	 * 
	 * @param thisitem
	 */
	private void addBuildable(BuildableItem thisitem) {
		buildables.put(thisitem.getName().toLowerCase(), thisitem);
	}
	
	/**
	 * Retrieves a buildable item.
	 * 
	 * @param name The name of the item.
	 * @return The item.
	 */
	public BuildableItem getBuildable(String name) {
		BuildableItem item;
		if (buildables.containsKey(name)) {
			item =  buildables.get(name);
		}
		else {
			item = null;
		}
		return item;
	}

	/**
	 * Helper method for creating doors between rooms.
	 *
	 * @param from
	 *            The room where the door originates.
	 * @param direction
	 * 			  The direction of the door in the from room.
	 * @param to
	 *            The room where the door goes.
	 * @param visible
	 * 			  Whether or not the room is visible.
	 */
	private void createDoor(Room from, String direction, Room to, boolean visible) {
		Door newdoor = new Door(to);
		newdoor.setVisible(visible);
		from.setExit(direction, newdoor);
	}

	/**
	 * This method creates all of the individual places in this world and all
	 * the doors connecting them.
	 */
	private void createRooms() {
		// Creating all the rooms.
		Room gboysdorms = new Room("Gryffindor Boys' Dorms", "Living quarters for Gryffindor wizards.");
		Room gdormhall = new Room("Gryffindor Dorm Hallway", "Long corridor leading from all Gryffindor bedrooms to the common room. It has a homey ambiance, with decorative sconces and plush carpeting.");
		Room gboysbathroom = new Room("Gryffindor Boys' Bathroom", "The men's bathroom for Gryffindor students.");
		Room gcommonroom = new Room("Gryffindor Common Room", "A common area for Gryffindor students.");
		Room gtowerhall = new Room("Gryffindor Tower Hallway", "The corridor outside Gryffindor Tower.");
		Room atowerstairs = new Room("Astronomy Tower Staircase", "A narrow staircase leading to the Astronomy Tower.");
		Room atower = new Room("Astronomy Tower", "A large room filled with artifacts for observing the sky.");
		Room divinationclass = new Room("Divination Classroom", "Professor Trelawney's divination classroom. There is almost nothing in here.");
		Room forbiddencorridor = new Room("Forbidden Corridor", "A corridor off-limits to students.");
		Room forbiddenstatue = new Room("One-Eyed Witch Statue", "A statue at the far corner of the forbidden corridor. It sparkles, as if trying to tell you there is something more to it...");
		Room northstairs = new Room("North Moving Staircase", "The north section of the moving stairs.");
		Room mainstairs = new Room("Main Moving Staircase", "The middle section of the moving stairs.");
		Room southstairs = new Room("South Moving Staircase", "The south section of the moving stairs.");
		Room transfiguration = new Room("Transfiguration Classroom", "Professor McGonagall's classroom for Transfiguration lessons.");
		Room charms = new Room("Charms Classroom", "Professor Flitwick's classroom for Charms lessons.");
		Room hhall = new Room("Hufflepuff Hall", "The hallway outside the Hufflepuff Common Room, which will only reveal itself to perceived Hufflepuff students.");
		Room hcommonroom = new Room("Hufflepuff Common Room", "A comfortable living space for students in Hufflepuff House.");
		Room greathall =  new Room("Great Hall", "An enormous, ornately-decorated gathering room.");
		Room library = new Room("Library", "The main library for all Hogwarts students; a quiet zone.");
		Room rhall =  new Room("Ravenclaw Hall", "The hallway outside the Ravenclaw Common Room.");
		Room dungeons = new Room("Dungeons", "A collection of dark, desolate empty cells in the basement.");
		Room shall =  new Room("Slytherin Hall", "The hallway outside the Slytherin Common Room.");
		Room potions = new Room("Potions Classroom", "Professor Snape's classroom for teaching Potions lessons.");
		Room hogwartsentrance = new Room("Hogwarts Main Entrance", "The main doorway to Hogwarts Castle.");
		Room hogwartsgrounds = new Room("Hogwarts Grounds", "The Hogwarts outdoor fields.");
		Room forbiddenforest =  new Room("Forbidden Forest", "An off-limits wooded area, home to a variety of magical creatures.");
		Room dforbiddenforest = new Room("Deep Forbidden Forest", "A very dangerous, very off-limits wooded area.");
		Room whompingwillow = new Room("Whomping Willow", "An unfriendly tree. Don't get too close.");
		Room quidditchstadium = new Room("Quidditch Stadium", "A sports complex where Quidditch is played.");
		Room hogwartsgate = new Room("Hogwarts Main Gate", "The main entrance/exit from the Hogwarts grounds.");
		Room hogsmeadeentrance = new Room("Entrance to Hogsmeade Village", "An archway welcoming visitors to Hogsmeade Village.");
		Room hogsmeadesquare = new Room("Hogsmeade Square", "The heart of Hogsmeade Village.");
		Room westlane = new Room("West Shop Lane", "A connecting road to the shops on the west side of the village.");
		Room eastlane = new Room("East Shop Lane", "A connecting road to the shops on the east side of the village.");
		Room honeydukes = new Room("Honeydukes", "A candy shop. A door reading \"Employees Only\" is propped open.");
		Room honeydukesbasement = new Room("Honeydukes' Basement", "A storage area for the candy shop.");
		Room zonkos = new Room("Zonko's Joke Shop", "A prank item shop.");
		Room threebroomsticks = new Room("The Three Broomsticks", "A lively, extremely popular bar. It is completely empty, which is highly unusual.");
		Room hogshead = new Room("Hog's Head", "A run-down, dingy bar.");
		Room madampuddifoots = new Room("Madam Puddifoot's Tea Shop", "A quaint tea shop.");
		Room shriekingshack = new Room("Shrieking Shack", "An old structure, rumored to be haunted by the locals. You're not sure if you hear screaming or if your mind is just playing tricks on you.");
		Room fountain = new Room("Fountain", "You are standing in front of a beautiful, enchanted water fountain. Someone has tacked a note to it that reads \"Fly south!\"");
		Room weasleysgarden = new Room("The Weasley's Garden", "After flying for a while, you spot the garden outside of the Weasleys' house. It appears to be overrun with gnomes. You land.");
		Room burrow = new Room("The Burrow", "The Weasley's living room.");
		
		/** This sets the point values received for entering each room. */
		gboysdorms.setPoints(0);
		gdormhall.setPoints(5);  
		gboysbathroom.setPoints(5);
		gcommonroom.setPoints(5);
		gtowerhall.setPoints(5);
		atowerstairs.setPoints(5);
		atower.setPoints(5);
		divinationclass.setPoints(5);
		forbiddencorridor.setPoints(5);
		forbiddenstatue.setPoints(5);
		northstairs.setPoints(5);
		mainstairs.setPoints(5);
		southstairs.setPoints(5);
		transfiguration.setPoints(5);
		charms.setPoints(5);
		hhall.setPoints(5);
		hcommonroom.setPoints(5);
		greathall.setPoints(5);
		library.setPoints(5);
		rhall.setPoints(5);
		dungeons.setPoints(5);
		shall.setPoints(5);
		potions.setPoints(5);
		hogwartsentrance.setPoints(5);
		hogwartsgrounds.setPoints(1000);
		forbiddenforest.setPoints(5);
		dforbiddenforest.setPoints(5);
		whompingwillow.setPoints(5);
		quidditchstadium.setPoints(5);
		hogwartsgate.setPoints(5);
		hogsmeadeentrance.setPoints(5);
		hogsmeadesquare.setPoints(5);
		westlane.setPoints(5);
		eastlane.setPoints(5);
		honeydukes.setPoints(5);
		honeydukesbasement.setPoints(5);
		zonkos.setPoints(5);
		threebroomsticks.setPoints(5);
		hogshead.setPoints(5);
		madampuddifoots.setPoints(5);
		shriekingshack.setPoints(5);
		fountain.setPoints(5);
		weasleysgarden.setPoints(5);
		burrow.setPoints(1000); 
		
		// Adding all the rooms to the world.
		this.addRoom(gboysdorms);
		this.addRoom(gdormhall);
		this.addRoom(gboysbathroom);
		this.addRoom(gcommonroom);
		this.addRoom(gtowerhall);
		this.addRoom(atowerstairs);
		this.addRoom(atower);
		this.addRoom(divinationclass);
		this.addRoom(forbiddencorridor);
		this.addRoom(forbiddenstatue);
		this.addRoom(northstairs);
		this.addRoom(mainstairs);
		this.addRoom(southstairs);
		this.addRoom(transfiguration);
		this.addRoom(charms);
		this.addRoom(hhall);
		this.addRoom(hhall);
		this.addRoom(hcommonroom);
		this.addRoom(greathall);
		this.addRoom(library);
		this.addRoom(rhall);
		this.addRoom(dungeons);
		this.addRoom(shall);
		this.addRoom(potions);
		this.addRoom(hogwartsentrance);
		this.addRoom(hogwartsgrounds);
		this.addRoom(forbiddenforest);
		this.addRoom(dforbiddenforest);
		this.addRoom(whompingwillow);
		this.addRoom(quidditchstadium);
		this.addRoom(hogwartsgate);
		this.addRoom(hogsmeadeentrance);
		this.addRoom(hogsmeadesquare);
		this.addRoom(westlane);
		this.addRoom(eastlane);
		this.addRoom(honeydukes);
		this.addRoom(honeydukesbasement);
		this.addRoom(zonkos);
		this.addRoom(threebroomsticks);
		this.addRoom(hogshead);
		this.addRoom(madampuddifoots);
		this.addRoom(shriekingshack);
		this.addRoom(fountain);
		this.addRoom(weasleysgarden);
		this.addRoom(burrow);
		
		// Creating all the doors between the rooms.
		this.createDoor(gboysdorms, "east", gdormhall, true);
		this.createDoor(gboysdorms, "south", gboysbathroom, true);
		
		this.createDoor(gdormhall, "south", gcommonroom, true);
		this.createDoor(gdormhall, "west", gboysdorms, true);

		this.createDoor(gboysbathroom, "north", gboysdorms, true);
		this.createDoor(gboysbathroom, "east", gcommonroom, true);

		this.createDoor(gcommonroom, "north", gdormhall, true);
		this.createDoor(gcommonroom, "south", gtowerhall, true);
		this.createDoor(gcommonroom, "west", gboysbathroom, true);

		this.createDoor(gtowerhall, "north", gcommonroom, true);
		this.createDoor(gtowerhall, "east", mainstairs, true);
		this.createDoor(gtowerhall, "south", atowerstairs, true);

		this.createDoor(atowerstairs, "north", gtowerhall, true);
		this.createDoor(atowerstairs, "south", atower, true);
		this.createDoor(atowerstairs, "west", forbiddencorridor, true);

		this.createDoor(atower, "north", atowerstairs, true);
		this.createDoor(atower, "west", divinationclass, true);

		this.createDoor(divinationclass, "east", atower, true);
		
		this.createDoor(forbiddencorridor, "north", forbiddenstatue, true);
		this.createDoor(forbiddencorridor, "east", atowerstairs, true);
		
		this.createDoor(forbiddenstatue, "north", honeydukesbasement, false);
		this.createDoor(forbiddenstatue, "south", forbiddencorridor, true);
		
		this.createDoor(northstairs, "north", charms, true);
		this.createDoor(northstairs, "east", hhall, false);
		this.createDoor(northstairs, "south", mainstairs, true);
		this.createDoor(northstairs, "west", transfiguration, true);
		
		this.createDoor(mainstairs, "north", northstairs, true);
		this.createDoor(mainstairs, "east", greathall, true);
		this.createDoor(mainstairs, "south", southstairs, false);
		this.createDoor(mainstairs, "west", gtowerhall, true);
		
		this.createDoor(southstairs, "north", mainstairs, true);
		this.createDoor(southstairs, "east", dungeons, true);
		this.createDoor(southstairs, "south", hogwartsentrance, true);
		this.createDoor(southstairs, "west", library, true);
		
		this.createDoor(transfiguration, "east", northstairs, true);
		
		this.createDoor(charms, "south", northstairs, true);
		
		this.createDoor(hhall, "north", hcommonroom, false);
		this.createDoor(hhall, "west", northstairs, true);
		
		this.createDoor(hcommonroom, "south", hhall, true);
		
		this.createDoor(greathall, "west", mainstairs, true);
		
		this.createDoor(library, "east", southstairs, true);
		this.createDoor(library, "south", rhall, true);
		
		this.createDoor(rhall, "north", library, true);
		
		this.createDoor(dungeons, "south", shall, true);
		this.createDoor(dungeons, "west", southstairs, true);
		
		this.createDoor(shall, "north", dungeons, true);
		this.createDoor(shall, "south", potions, true);
		
		this.createDoor(potions, "north", shall, true);
		
		this.createDoor(hogwartsentrance, "north", southstairs, true);
		this.createDoor(hogwartsentrance, "south", hogwartsgrounds, true);
		
		this.createDoor(hogwartsgrounds, "north", rhall, true);
		this.createDoor(hogwartsgrounds, "east", forbiddenforest, true);
		this.createDoor(hogwartsgrounds, "south", hogwartsgate, true);
		
		this.createDoor(forbiddenforest, "east", dforbiddenforest, true);
		this.createDoor(forbiddenforest, "south", whompingwillow, true);
		this.createDoor(forbiddenforest, "west", hogwartsgrounds, true);
		
		this.createDoor(dforbiddenforest, "west", forbiddenforest, true);
		
		this.createDoor(whompingwillow, "north", forbiddenforest, true);
		this.createDoor(whompingwillow, "west", hogwartsgate, true);
		
		this.createDoor(quidditchstadium, "east", hogwartsgate, true);
		
		this.createDoor(hogwartsgate, "north", hogwartsgrounds, true);
		this.createDoor(hogwartsgate, "east", whompingwillow, true);
		this.createDoor(hogwartsgate, "south", hogsmeadeentrance, true);
		this.createDoor(hogwartsgate, "west", quidditchstadium, true);
		
		this.createDoor(hogsmeadeentrance, "north", hogwartsgate, true);
		this.createDoor(hogsmeadeentrance, "south", hogsmeadesquare, true);
		
		this.createDoor(hogsmeadesquare, "north", hogsmeadeentrance, true);
		this.createDoor(hogsmeadesquare, "east", eastlane, true);
		this.createDoor(hogsmeadesquare, "south", fountain, true);
		this.createDoor(hogsmeadesquare, "west", westlane, true);
		
		this.createDoor(westlane, "north", honeydukes, true);
		this.createDoor(westlane, "east", hogsmeadesquare, true);
		this.createDoor(westlane, "south", threebroomsticks, true);
		this.createDoor(westlane, "west", zonkos, true);
		
		this.createDoor(eastlane, "north", hogshead, true);
		this.createDoor(eastlane, "east", madampuddifoots, true);
		this.createDoor(eastlane, "south", shriekingshack, true);
		this.createDoor(eastlane, "west", hogsmeadesquare, true);
		
		this.createDoor(honeydukes, "south", westlane, true);
		this.createDoor(honeydukes, "west", honeydukesbasement, true);
		
		this.createDoor(honeydukesbasement, "south", forbiddenstatue, false);
		this.createDoor(honeydukesbasement, "east", honeydukes, true);
		
		this.createDoor(zonkos, "east", westlane, true);
		
		this.createDoor(threebroomsticks, "north", westlane, true);
		
		this.createDoor(hogshead, "south", eastlane, true);
		
		this.createDoor(madampuddifoots, "west", eastlane, true);
		
		this.createDoor(shriekingshack, "north", eastlane, true);
		
		this.createDoor(fountain, "north", hogsmeadesquare, true);
		this.createDoor(fountain, "south", weasleysgarden, false);
		
		this.createDoor(weasleysgarden, "north", fountain, false);
		this.createDoor(weasleysgarden, "east", burrow, true);
		
		this.createDoor(burrow, "west", weasleysgarden, true);
	}
	

	/**
	 * Adds items to a room.
	 */
	public void createItems() {
		// Items in Gryffindor Boy's Dorms.
		Item sweater = new Item("Sweater", "One of Mrs. Weasley's hand-knit sweaters. It has an enormous letter \"H\" stitched on it.", 0, 4);
		Item stardustbottle = new Item("Stardust Bottle", "A bottle filled with a shiny, swirling liquid. It probably isn't a good idea to drink this. Maybe you should break it?", 0, 1);
		Item bed = new Item("Bed", "A relatively comfortable place to sleep.", 0, 80);
		Item mirror = new Item("Mirror", "You look at yourself in the mirror and wonder if you should comb your hair. Deep down, you know if you have to ask, the answer is probably yes.", 0, 80);
		
		Container closet = new Container("Closet", "It looks like it can contain a pretty standard amount of clothing. One of the doors is slightly ajar. \"Drop the bottle\" is scratched into it.", 0, 70);
		Container drawer = new Container("Dresser", "Contains a number of drawers for storing things.", 0, 100);
		
		closet.addItem(stardustbottle);
		drawer.addItem(sweater);
		
		this.getRoom("Gryffindor Boys' Dorms").addItem(closet);
		this.getRoom("Gryffindor Boys' Dorms").addItem(drawer);
		this.getRoom("Gryffindor Boys' Dorms").addItem(bed);
		this.getRoom("Gryffindor Boys' Dorms").addItem(mirror);
		
		// Items in Gryffindor Dorm Hall.
		Item painting1 = new Item("Painting of Old Wizard", "An old wizard sits at a table sipping his tea, deliberately ignoring you. He doesn't like students.", 0, 90);
		Item painting2 = new Item("Albus Dumbledore Painting", "The scenery is quit nice in this painting. It's inhabitant seems to have wandered off.", 0, 100);
		Item pennant = new Item("Gryffindor House Pennant", "An enormous red and gold pennant with a lion on it. Go Gryffindor!", 5, 10);
		
		Container frame = new Container("Frame", "Displays things on the wall.", 0, 80);
		
		this.getRoom("Gryffindor Dorm Hallway").addItem(painting1);
		this.getRoom("Gryffindor Dorm Hallway").addItem(painting2);
		this.getRoom("Gryffindor Dorm Hallway").addItem(frame);
		
		frame.addItem(pennant);
		
		// Items in Gryffindor Boy's Bathroom.
		Item hufflepuffuniform = new Item("Hufflepuff Uniform", "A bit wrinkled, but clean enough to wear. Allows you to appear as though you're in Hufflepuff House, but a stronger disguise is needed to fool magical objects.", 10, 5);
		Item toilet = new Item("Toilet", "It's pretty important for bathrooms to have toilets.", 0, 70);
		Item shower = new Item ("Shower", "A place to get clean. It doesn't look like it's been used in a while.", 0, 500);
		
		Container showerhook = new Container("Shower Hook", "A place to hang things while you shower.", 0,1000);
		
		this.getRoom("Gryffindor Boys' Bathroom").addItem(toilet);
		this.getRoom("Gryffindor Boys' Bathroom").addItem(shower);
		this.getRoom("Gryffindor Boys' Bathroom").addItem(showerhook);
		
		showerhook.addItem(hufflepuffuniform);
		
		// Items in Gryffindor Common Room.
		Item pwordflyer = new Item("Password Flyer", " \"Notice: Passwords have been temporarily waived. The entrances to each common room will be able to recognize what house each student belongs to. Neville, this is your fault.\"'", 0, 0);
		Item net = new Item("Net", "Looks good for catching insects.", 0, 1);
		Item lamp = new Item("Lamp", "Provides lighting for the room.", 0, 8);
		Item couch =  new Item("Couch", "A comfortable place to sit.", 0, 90);
		Item keg = new Item("Empty Keg", "An empty keg. Did you drink it all? You can't remember...", 0, 80);
		
		Container gfireplace = new Container("Fireplace", "Adorned in red and yellow, and with a fire that never burns out, this fireplace is an excellent way to travel by Floo powder.", 0, 1000);
		Container sidetable = new Container("Side Table", "An ornate table next to the couch, for convenience.", 0, 60);
		Container bulletinboard = new Container("Bulletin Board", "Important announcements are hung here.", 0, 50);
		
		this.getRoom("Gryffindor Common Room").addItem(couch);
		this.getRoom("Gryffindor Common Room").addItem(gfireplace);
		this.getRoom("Gryffindor Common Room").addItem(sidetable);
		this.getRoom("Gryffindor Common Room").addItem(bulletinboard);
		this.getRoom("Gryffindor Common Room").addItem(keg);
		
		gfireplace.addItem(stardustbottle);
		sidetable.addItem(net);
		sidetable.addItem(lamp);
		bulletinboard.addItem(pwordflyer);
		
		// Items in Gryffindor Tower Hall.
		
		// Items in Astronomy Tower Stairs.
		
		// Items in Astronomy Tower.
		
		Item telescope = new Item("Telescope", "Good for observing stars.", 0, 6);
		Item bicornhorn = new Item("Bicorn Horn", "The horn of a bicorn; a very rare and very powerful potion ingredient.", 0, 6);
		
		Container artifactcloset = new Container("Artifacts Closet", "A storage area containing important and powerful astronomy tools.", 0, 1000);
		
		this.getRoom("Astronomy Tower").addItem(artifactcloset);
		artifactcloset.addItem(stardustbottle);
		artifactcloset.addItem(bicornhorn);
		artifactcloset.addItem(telescope);
		
		// Items in Divination Classroom.
		Item crystalball = new Item("Crystal Ball", "It can read your future. You probably don't want to know what it sees.", 0, 90);
		Item fly = new Item("Fly", "It buzzes overhead, as if challenging you to try and catch it.", 0, 1);
		
		this.getRoom("Divination Classroom").addItem(fly);
		this.getRoom("Divination Classroom").addItem(crystalball);
		
		// Items in Forbidden Corridor.
		
		// Items in One-Eyed Witch Statue.
		Container bag = new Container("Bag", "The statue's stone bag.", 0, 50);
		
		this.getRoom("One-Eyed Witch Statue").addItem(bag);
		bag.addItem(stardustbottle);
		
		// Items in North Moving Staircase.
		
		// Items in Main Moving Staircase.
		
		// Items in South Moving Staircase.
		
		// Items in Transfiguration Classroom.
		Item feather = new Item("Feather", "Probably left over from a transfiguration class. You wonder what it used to be before it got turned into a feather.", 0, 1);
		
		Container transfigurationdesk = new Container("McGonagall's Desk", "It probably isn't a good idea to mess with her stuff.", 0, 60);
		
		this.getRoom("Transfiguration Classroom").addItem(fly);
		this.getRoom("Transfiguration Classroom").addItem(transfigurationdesk);
		
		transfigurationdesk.addItem(feather);
		transfigurationdesk.addItem(stardustbottle);
		
		// Items in Charms Classroom.
		Item flitwickdesk = new Item("Flitwick's Desk", "There are a number of papers, strewn messily about. You don't have time to make sense of this nonsense.", 0, 60);
		
		this.getRoom("Charms Classroom").addItem(flitwickdesk);
		
		// Items in Hufflepuff Hall.
		
		
		// Items in Hufflepuff Common Room.
		Item greathallkey = new Item("Key", "A large, ornate key. The words \"Great Hall\" are inscribed on it in an elegant cursive script.", 100, 2);
		Item woodenplank = new Item("Wooden Plank", "It's a wooden plank, not very interesting.", 0, 5);
		
		Container hfireplace = new Container("Fireplace", "Adorned in yellow and black, there is no fire here.", 0, 1000);
		
		this.getRoom("Hufflepuff Common Room").addItem(hfireplace);
		this.getRoom("Hufflepuff Common Room").addItem(couch);
		this.getRoom("Hufflepuff Common Room").addItem(sidetable);
		sidetable.addItem(lamp);
		sidetable.addItem(stardustbottle);
		hfireplace.addItem(woodenplank);
		hfireplace.addItem(greathallkey);
		
		// Items in Great Hall.
		Item hightable = new Item("The High Table", "Where the professors and other important guests sit.", 0, 500);
		Item wand = new Item("Wand", "An instrument for casting magic spells.", 0, 1);
		Item spellbook = new Item("Spellbook", "Spells: \n \t Alohomora - unlocks things \n \t Dissendium - reveals a certain hidden passage \n Not technically a spell but remember: Use the 'UP' command to mount a flying broomstick.", 0, 2);
		Item note = new Item("Note", "In an elegant, cursive script, it reads: \"Enjoy your breakfast, Harry. See you outside.\" There is no breakfast to be found.", 0, 1);
		
		Container lowtable = new Container("Regular Table", "For students. It isn't as nice as the High Table.", 0, 600);
		
		this.getRoom("Great Hall").addItem(hightable);
		this.getRoom("Great Hall").addItem(lowtable);
		
		lowtable.addItem(wand);
		lowtable.addItem(spellbook);
		lowtable.addItem(note);
		
		// Items in Library.
		Item boomslang = new Item("Boomslang Skin", "The skin of a boomslang. It is sometimes used in potions.", 0, 3);
		
		Container bookshelf = new Container("Bookshelf", "It's full of books. Obviously.", 0, 1000);
		this.getRoom("Library").addItem(bookshelf);
		this.getRoom("Library").addItem(fly);
		
		bookshelf.addItem(boomslang);
		bookshelf.addItem(stardustbottle);
		
		// Items in Ravenclaw Hall.
		Container vase = new Container("A Vase", "You wonder why they aren't any flowers in it...", 0, 45);
		
		this.getRoom("Ravenclaw Hall").addItem(vase);
		
		vase.addItem(stardustbottle);
		
		// Items in Dungeons.
		Container cells = new Container("Cells", "Legend has it, instead of dentention they used to jail students here. They smell bad.", 0, 100);
		Item shackles = new Item("Shackles", "Chains to hold prisoners in place.", 0, 25);
		
		this.getRoom("Dungeons").addItem(cells);
		cells.addItem(shackles);
		
		// Items in Slytherin Hall.
		this.getRoom("Slytherin Hall").addItem(fly);
		
		// Items in Potions Classroom.
		Item hair = new Item("Hair", "A clump of somebody's hair.", 0, 0);
		Item cauldron = new Item("Cauldron", "A pot for bewing potions. It looks like it already has some hair at the bottom?", 0, 100);
		Item chalkboard = new Item("Chalkboard", "It reads: \n \t Today's Class: How to build Polyjuice Potion \n \t \t 1 Boomslang Skin \n \t \t 1 Bicorn Horn \n \t \t 1 Lacewing Fly \n \t Hair or other DNA object from the person you want to look like", 0, 200);
		
		this.getRoom("Potions Classroom").addItem(cauldron);
		this.getRoom("Potions Classroom").addItem(hair);
		this.getRoom("Potions Classroom").addItem(chalkboard);
		
		// Items in Hogwarts Main Entrance.
		Container knight =  new Container("Knight", "A set of armor. Is it holding something?", 0, 100);
		
		this.getRoom("Hogwarts Main Entrance").addItem(knight);
		
		knight.addItem(stardustbottle);
		// Items in Hogwarts Grounds.
		
		// Items in Forbidden Forest.
		
		// Items in Deep Forbidden Forest.
		
		// Items in Whomping Willow.
		
		// Items in Quidditch Stadium.
		Item quaffle = new Item("Quaffle", "Used in Quidditch, everyone's favorite wizarding sport.", 0, 4);
		Item broomhandle = new Item("Broomstick Handle", "An enchanted piece of wood that floats, but cannot be steered without attaching straw to the end.", 25, 4);
		
		Container broomcloset = new Container("Broom Closet", "Only muggles would think this closet is boring. This is where the best, fastest brooms on campus are stored. Unfortunately, it's nearly empty.", 0, 90);
		Container chest = new Container("Quidditch Equipment Chest", "Normally, this is full of everything you need to play quidditch.", 0, 50);
		
		this.getRoom("Quidditch Stadium").addItem(broomcloset);
		this.getRoom("Quidditch Stadium").addItem(chest);
		
		chest.addItem(quaffle);
		broomcloset.addItem(broomhandle);
		
		// Items in Hogwarts Main Gate.
		Item archway = new Item("Archway", "A wrought iron archway welcoming you to campus grounds. Don't let looks be deceiving; if you aren't supposed to be here it has the power to fry your brains.", 0, 300);
		
		this.getRoom("Hogwarts Main Gate").addItem(archway);
		
		// Items in Entrance to Hogsmeade Village.
		Item straw = new Item("Straw", "This is good quality straw; it would be excellent when used on a broom.", 50, 3);
		Item sign = new Item("Sign", "It welcomes you to Hogsmeade.", 0, 80);
		
		Container wheelbarrow =  new Container("Wheelbarrow", "Good for carrying things.", 0, 60);
		
		this.getRoom("Entrance to Hogsmeade Village").addItem(wheelbarrow);
		this.getRoom("Entrance to Hogsmeade Village").addItem(sign);
		
		wheelbarrow.addItem(straw);
		wheelbarrow.addItem(stardustbottle);
		
		// Items in Hogsmeade Square.
		
		// Items in West Shop Lane.
		
		// Items in East Shop Lane.
		
		// Items in Honeydukes.
		Item chocolatefrog = new Item("Chocolate Frog", "A lifelike popular candy that comes with a collectible celebrity trading card. Eat it fast or it'll hop away!", 0, 1);
		Item jellybeans = new Item("Bertie Bott's Every Flavor Beans", "Assorted jellybeans. Note that their flavor is not always pleasant.", 0, 1);
		
		Container candycounter = new Container("Candy Counter", "Essential to any candy shop; this is where they keep all the good stuff.", 0, 300);
		
		this.getRoom("Honeydukes").addItem(candycounter);
		
		candycounter.addItem(chocolatefrog);
		candycounter.addItem(jellybeans);
		
		// Items in Honeydukes' Basement.
		
		// Items in Zonko's Joke Shop.
		Item decoydetonator = new Item("Decoy Detonator", "A prank item that when wound up, runs away from the user and emits a loud sound", 0, 1);
		Item fudge = new Item("Ton-Tongue Fudge", "A prank item guaranteed to make a consumer's tongue swell to 3 feet longer than usual!", 0, 1);
		
		Container shelves = new Container("Shelves", "Displays the joke items currently for sale.", 0, 100);
		
		this.getRoom("Zonko's Joke Shop").addItem(shelves);
		
		shelves.addItem(decoydetonator);
		shelves.addItem(fudge);
		
		// Items in The Three Broomsticks.
		Item butterbeer = new Item("Butterbeer", "A delicious butterscotch-flavored drink.", 0, 1);
		
		Container bar1 = new Container("Bar", "A clean sitting area for patrons to order drinks and watch the bartender work.", 0, 500);
		
		this.getRoom("The Three Broomsticks").addItem(bar1);
		
		bar1.addItem(butterbeer);
		
		// Items in Hog's Head.
		Item string = new Item("String", "This looks good for tying something together.", 10, 1);
		
		Container bar2 = new Container("Abe's Bar", "It's kind of grimy.", 0, 200);
		
		this.getRoom("Hog's Head").addItem(bar2);
		
		bar2.addItem(string);
		bar2.addItem(stardustbottle);
		
		// Items in Madam Puddifoot's Tea Shop.
		Item tea = new Item("Cup of Tea", "Delicious, warm, and looks like it's only been lightly sipped by someone else. Can be drank or shared with a friend.", 0, 1);
		Item plushcouch = new Item("Plush Couch", "It's pink, frilly, and incredibly soft.", 0, 60);
		
		Container coffeetable = new Container("Coffee Table", "A pink table in front of the couch.", 0, 80);
		
		this.getRoom("Madam Puddifoot's Tea Shop").addItem(plushcouch);
		this.getRoom("Madam Puddifoot's Tea Shop").addItem(coffeetable);
		
		coffeetable.addItem(tea);
		
		// Items in Shrieking Shack.
		
		// Items in Fountain.
		Container fountain = new Container("Fountain", "It has intricate carvings depicting the First Wizarding War, which are very worn. It must be very old.", 0, 800);
		
		this.getRoom("Fountain").addItem(fountain);
		
		fountain.addItem(stardustbottle);
		
		// Items in The Weasley's Garden.
		Item gnome = new Item("Gnome", "They say the only good gnome is a dead gnome. If you're going to pick it up, be careful. They bite!", 0, 5);
		
		Container garden = new Container("Garden", "You don't see any plants, just gnomes.", 0, 100);
		
		this.getRoom("The Weasley's Garden").addItem(garden);
		
		garden.addItem(gnome);
		
		// Items in The Burrow.
		Item cake = new Item("Cake", "", 0, 50);
		Item banner = new Item("\"Happy Birthday Harry\" Banner", "", 0, 50);
		Item balloons = new Item("Birthday balloons", "", 0, 50);
		
		this.getRoom("The Burrow").addItem(cake);
		this.getRoom("The Burrow").addItem(banner);
		this.getRoom("The Burrow").addItem(balloons);
		
		// Creating buildable items.
		BuildableItem polyjuice = new BuildableItem("Polyjuice Potion", "A potion to make the drinker look like someone else.", 100, 5);
		BuildableItem broomstick = new BuildableItem("Broomstick", "Allows the user to fly.", 100, 10);
		
		// Add buildable items to the world.
		this.addBuildable(polyjuice);
		polyjuice.addIngredient(boomslang);
		polyjuice.addIngredient(bicornhorn);
		polyjuice.addIngredient(fly);
		polyjuice.addIngredient(hair);
		polyjuice.setDismantle(false);
		
		this.addBuildable(broomstick);
		broomstick.addIngredient(broomhandle);
		broomstick.addIngredient(string);
		broomstick.addIngredient(straw);
		
		//Locking the appropriate doors.
		this.getRoom("Main Moving Staircase").getExit("east").setLocked(true);
		this.getRoom("Hogwarts Main Entrance").getExit("south").setLocked(true);
		
		//Setting keys to locked doors.
		this.getRoom("Main Moving Staircase").getExit("east").setKey(greathallkey);
		
	}
}

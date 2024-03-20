import java.util.HashMap;
import java.util.Random;

/**
 * This class is the main class of the "Campus of Kings" application.
 * "Campus of Kings" is a very simple, text based adventure game. Users can walk
 * around some scenery. That's all. It should really be extended to make it more
 * interesting!
 *
 * This game class creates and initializes all the others: it creates all rooms,
 * creates the parser and starts the game. It also evaluates and executes the
 * commands that the parser returns.
 *
 * @author Maria Jump and Kiersten Grieco
 * @version 2015.02.01
 *
 * Used with permission from Dr. Maria Jump at Northeastern University
 */

public class Game {
	/** The world where the game takes place. */
	private World world;
	/** The count of turns and total score is initialized below. */
	private int turns = 0;
	/** The initializion of the score-tracking variable. */
	private int score = 0;
	/** The player character is initialized below.*/
	private Player player;
	/** The room the player was previously in. */
	private Room previousRoom = null;
	/**
	 * Create the game and initialize its internal map.
	 */
	public Game() {
		world = new World();
		// set the starting room
		player = new Player(world.getRoom("Gryffindor Boys' Dorms"));
	}

	/**
	 * Main play routine. Loops until end of play.
	 */
	public void play() {
		printWelcome();

		// Enter the main game loop. Here we repeatedly read commands and
		// execute them until the game is over.
		boolean wantToQuit = false;
		while (!wantToQuit) {
			Command command = Reader.getCommand();
			wantToQuit = processCommand(command);
			turns = turns + 1;
			if (player.getcurrentRoom().getName().equals("The Burrow")) {
				wantToQuit = true;
				Writer.println("As you enter The Burrow, the lights suddenly flicker on.");
				Writer.println("EVERYONE: SURPRISE!!!!!!!!!!");
				Writer.println("You blink and look around to see everyone from Ron and Hermione to Snape and Draco in the room.");
				Writer.println("HERMIONE: Oh, Harry, did we surprise you? It was all Ron's idea...");
				Writer.println("RON: We know your birthday isn't for a few months, but we REALLY wanted it to be a surprise!!");
				Writer.println("This was not at all how you thought this day was going to end.");
				Writer.println("You are glad everyone is safe and that you get to eat cake.");
				Writer.println("Congratulations! You win!");
			}
			if (turns == 350) {
				wantToQuit = true;
				Writer.println("Exhausted, you collapse.");
				Writer.println("You accept that you will never find out what happend to everyone...");
				Writer.println("You are out of turns. You lose.");
			}
			// other stuff that needs to happen every turn can be added here.
		}
		printGoodbye();
	}

	///////////////////////////////////////////////////////////////////////////
	// Helper methods for processing the commands

	/**
	 * Given a command, process (that is: execute) the command.
	 *
	 * @param command
	 *            The command to be processed.
	 * @return true If the command ends the game, false otherwise.
	 */
	private boolean processCommand(Command command) {
		boolean wantToQuit = false;

		if (command.isUnknown()) {
			Writer.println("I don't know what you mean...");
		} else {
			CommandEnum fullcommand = command.getCommandWord();
			String newcommand = fullcommand.toString();
			String[] commandWords = newcommand.split(" ");
			CommandEnum firstword = CommandWords.getCommand(commandWords[0]);
			switch (firstword) {
			case HELP:
				printHelp();
				break;
			case GO:
				goRoom(command);
				break;
			case QUIT:
				wantToQuit = quit(command);
			case LOOK:
				look();
				break;
			case STATUS:
				look();
				Writer.println("So far, you have earned " + score + " points in " + turns + " turns.");
				break;
			case BACK:
				if (previousRoom == null) {
					Writer.println("You haven't gone anywhere yet!");
				} 
				else {
					Room thisRoom = player.getcurrentRoom();
					player.setcurrentRoom(previousRoom);
					previousRoom = thisRoom;
					Writer.println(player.getcurrentRoom().toString());
					}
				break;
			case EXAMINE:
				examine(command);
				break;
			case TAKE:
				if (!command.hasSecondWord()) {
					Writer.println("Take what?");
				}
				else {
					Item thisitem = player.getcurrentRoom().getItem(command.getRestOfLine());
					if (thisitem == null) {
						Writer.println("That item isn't here.");
					}
					else {
						if(player.canPickUp(thisitem)) {
							if (player.getcurrentRoom().getItem("fly") == thisitem) {
								if (player.getItem("net") != null) {
									player.addItem(player.getcurrentRoom().getItem(command.getRestOfLine()));
									player.getcurrentRoom().removeItem(command.getRestOfLine());
									score = score + thisitem.getPoints();
									thisitem.setPoints(0);
								}
								else {
									Writer.println("You need to be holding something to catch the fly with...");
								}
								
							}
							else {
								player.addItem(player.getcurrentRoom().getItem(command.getRestOfLine()));
								player.getcurrentRoom().removeItem(command.getRestOfLine());
								score = score + thisitem.getPoints();
								thisitem.setPoints(0);
							}
						}
					}
				}
				break;
			case DROP:
				if (!command.hasSecondWord()) {
					Writer.println("Drop what?");
				}
				else if (player.getItem(command.getRestOfLine()) == null) {
					Writer.println("You don't have this item in your inventory.");
				}
				else {
					player.getcurrentRoom().addItem(player.getItem(command.getRestOfLine()));
					Item removed = player.removeItem(command.getRestOfLine());
					Writer.println("You dropped the " + command.getRestOfLine() + ".");
					if (removed.getName().toLowerCase().equals("stardust bottle")) {
						int addPoints = new Random().nextInt(1, 100);
						score = score + addPoints;
						player.getcurrentRoom().removeItem(removed.getName().toLowerCase());
						Writer.println("It broke.");
						Writer.println("You got " + addPoints + " points from the Stardust Bottle!");
					}
				}
				break;
			case INVENTORY:
				player.printInventory();
				break;
			case UNLOCK:
				unlock(command);
				break;
			case LOCK:
				lock(command);
				break;
			case UNPACK:
				unpack(command);
				break;
			case PACK:
				pack(command);
				break;
			case BUILD:
				build(command);
				break;
			case DISMANTLE:
				dismantle(command);
				break;
			case DRINK:
				drink(command);
				break;
			case EAT:
				eat(command);
				break;
			case UP:
				if (player.getcurrentRoom().getItem("broomstick") != null || player.getItem("broomstick") != null) {
					if (player.getcurrentRoom().getItem("broomstick") != null) {
						Writer.println("You are on the Broomstick!");
						Writer.println("I wonder where you should fly to...");
						world.getRoom("Fountain").setVisible("south", true);
					}
					else {
						Writer.println("You are on the Broomstick!");
						Writer.println("I wonder where you should fly to...");
						world.getRoom("Fountain").setVisible("south", true);
					}
				}
				else {
					Writer.println("There is no Broomstick here!");
				}
				break;
			case CAST:
				if (player.getItem("wand") != null) {
					if (!command.hasSecondWord()) {
						Writer.println("Cast what?");
					}
					else if (!command.getRestOfLine().equals("alohomora") && !command.getRestOfLine().equals("dissendium")) {
						Writer.println("You don't know this spell.");
					}
					else {
						if (command.getRestOfLine().equals("alohomora")) {
							if(player.getcurrentRoom() == world.getRoom("hogwarts main entrance")) {
								player.getcurrentRoom().getExit("south").setLocked(false);
								Writer.println("You unlocked the castle's main doors! You can now go south.");
							}
							else {
								Writer.println("That spell doesn't do anything here.");
							}
							
						}
						else if (command.getRestOfLine().equals("dissendium")) {
							if (player.getcurrentRoom() == world.getRoom("one-eyed witch statue")) {
								world.getRoom("one-eyed witch statue").setVisible("north", true);
								world.getRoom("honeydukes' basement").setVisible("south", true);
								Writer.println("You unlocked a secret passage! Look around to see it.");
							}
							else {
								Writer.println("That spell doesn't do anything here.");
							}
						}
					}
				}
				else {
					Writer.println("You can't cast spells unless you're holding a Wand!");
				}
				break;
			default:
				Writer.println(commandWords[0] + " is not implemented yet!");
			}
		}
		return wantToQuit;
	}

	///////////////////////////////////////////////////////////////////////////
	// Helper methods for implementing all of the commands.
	// It helps if you organize these in alphabetical order.

	/**
	 * Try to go to one direction. If there is an exit, enter the new room,
	 * otherwise print an error message.
	 *
	 * @param command
	 *            The command to be processed.
	 */
	private void goRoom(Command command) {
		if (!command.hasSecondWord()) {
			// if there is no second word, we don't know where to go...
			Writer.println("Go where?");
		} else {
			String direction = command.getRestOfLine();
			// Try to leave current.
			Door doorway = null;
			doorway = player.getcurrentRoom().getExit(direction);
			
			if (doorway == null) {
				Writer.println("There is no door!");
			} else if (doorway.isLocked()){
				Writer.println("This door is locked!");
			} else {
				previousRoom = player.getcurrentRoom();
				Room newRoom = doorway.getDestination();
				player.setcurrentRoom(newRoom);
				score = score + newRoom.getPoints();
				Writer.println(player.getcurrentRoom().toString());
				// For the moving stairs.
				if (player.getcurrentRoom() == world.getRoom("Main Moving Staircase") || player.getcurrentRoom() == world.getRoom("North Moving Staircase") || player.getcurrentRoom() == world.getRoom("South Moving Staircase")) {
					int switchstairs = new Random().nextInt(12);
					if (switchstairs % 3 == 0) {
						Writer.println("The stairs have moved!");
						int rnNorth = new Random().nextInt(1, 10);
						int rnEast = new Random().nextInt(1, 10);
						int rnSouth = new Random().nextInt(1, 10);
						int rnWest = new Random().nextInt(1, 10);
						String exits = "";
						if (rnNorth % 2 == 0) {
							player.getcurrentRoom().setVisible("north", true);
							exits = exits + " north";
						}
						else {
							player.getcurrentRoom().setVisible("north", false);
						}
						if (rnEast % 2 == 0) {
							player.getcurrentRoom().setVisible("east", true);
							exits = exits + " east";
						}
						else {
							player.getcurrentRoom().setVisible("east", false);
						}
						if (rnSouth % 2 == 0) {
							player.getcurrentRoom().setVisible("south", true);
							exits = exits + " south";
						}
						else {
							player.getcurrentRoom().setVisible("south", false);
						}
						if (rnWest % 2 == 0) {
							player.getcurrentRoom().setVisible("west", true);
							exits = exits + " west";
						}
						else {
							player.getcurrentRoom().setVisible("west", false);
						}
						if ((rnNorth % 2 != 0 && rnEast % 2 != 0 && rnSouth % 2 != 0 && rnWest != 0) || (rnNorth % 2 != 0 && rnEast % 2 == 0 && rnSouth % 2 != 0 && rnWest != 0)) {
							player.getcurrentRoom().setVisible("west", true);
							exits = exits + " west";
						}
						Writer.println("New Exits:" + exits);
					}
				}
				if (player.getcurrentRoom() == world.getRoom("Hufflepuff Common Room")) {
					Writer.println("The Polyjuice Potion has worn off!");
					Writer.println("You will no longer be recognized as a Hufflepuff student and you will not be able to return to this room.");
					Writer.println("Make sure you get what you need before leaving");
					world.getRoom("Hufflepuff Hall").setVisible("north", false);
					world.getRoom("Gryffindor Tower Hallway").setVisible("north", true);
				}
			}
		}
	}

	/**
	 * Print out the closing message for the player.
	 */
	private void printGoodbye() {
		Writer.println("I hope you enjoyed your time at Hogwarts!");
		Writer.println("You have earned " + score + " points in " + (turns - 1) + " turns.");
		Writer.println("Thank you for playing.  Goodbye.");
	}

	/**
	 * Print out some help information. Here we print some stupid, cryptic
	 * message and a list of the command words.
	 */
	private void printHelp() {
		Writer.println("You are scared. You are alone. You wander");
		Writer.println("around Hogwarts and its surroundings.");
		Writer.println();
		Writer.println("Your command words are:");
		Writer.println("   go, quit, help, look, status,");
		Writer.println("   back, examine, take, drop,");
		Writer.println("   inventory, unlock, lock, unpack,");
		Writer.println("   pack, build, dismantle, eat");
		Writer.println("   drink, up, cast");
	}

	/**
	 * Print out the opening message for the player.
	 */
	private void printWelcome() {
		Writer.println();
		Writer.println("Welcome to Harry Potter and the Barren Halls of Ivy!");
		Writer.println("You are Harry Potter, a third-year Gryffindor Student at Hogwarts School of Witchcraft and Wizardry.");
		Writer.println("You wake up after a long night of celebrating last night's Qudditch win, still feeling a bit groggy. It soon becomes apparent that the castle is deserted, and you are the only one left.");
		Writer.println("It's up to you to figure out what happend to your friends and foes alike. You wonder what sinister adversary you'll face this time...");
		Writer.println("Type 'help' if you need help.");
		Writer.println();
		Writer.println(player.getcurrentRoom().toString());
		
	}	
	
	/**
	 * "Quit" was entered. Check the rest of the command to see whether we
	 * really quit the game.
	 *
	 * @param command
	 *            The command to be processed.
	 * @return true, if this command quits the game, false otherwise.
	 */
	private boolean quit(Command command) {
		boolean wantToQuit = true;
		if (command.hasSecondWord()) {
			Writer.println("Quit what?");
			wantToQuit = false;
		}
		return wantToQuit;
	}
	
	/**
	 * Prints out the location information.
	 */
	private void look() {
		Writer.println(player.getcurrentRoom().toString());
	}
	
	/**
	 * Method to implement the examine command.
	 * 
	 * @param command The entered examine command.
	 */
	private void examine(Command command) {
		String output = null;
		if (!command.hasSecondWord()) {
			output = "Examine what?";
		}
		else {
			if (player.getItem(command.getRestOfLine()) == null && player.getcurrentRoom().getItem(command.getRestOfLine()) == null) {
				output = "There is no such item!";
			}
			else {
				if (!(player.getItem(command.getRestOfLine()) == null)) {
					output = player.getItem(command.getRestOfLine()).toString();
				}
				if(!(player.getcurrentRoom().getItem(command.getRestOfLine()) == null)) {
					output = player.getcurrentRoom().getItem(command.getRestOfLine()).toString();
				}
			}
		}
		Writer.println(output);
	}
	
	/**
	 * Implements the unlock command.
	 * 
	 * @param command The command entered by the player.
	 */
	private void unlock(Command command) {
		if (!command.hasSecondWord()) {
			Writer.println("Unlock what?");
		}
		else {
			String direction = command.getRestOfLine();
			Door doorway = player.getcurrentRoom().getExit(direction);
			
			if (doorway == null) {
				Writer.println("There is no door in that direction!");
			}
			else if (!doorway.isLocked()) {
				Writer.println("This door is not locked.");
			}
			else {
				Writer.println("With what?");
				String keyname = Reader.getResponse();
				if (player.getItem(keyname) == null) {
					Writer.println("You don't have this item!");
				}
				else if(doorway.getKey().getName().toLowerCase().equals(keyname)) {
					doorway.setLocked(false);
					Writer.println("The door to the " + direction + " has been unlocked.");
				}
				else {
					Writer.println("That item does not unlock this door.");
				}
			}
		}
	}
	
	/**
	 * Implements the lock command.
	 * 
	 * @param command The command entered by the player.
	 */
	private void lock(Command command) {
		if (!command.hasSecondWord()) {
			Writer.println("Lock what?");
		}
		else {
			String direction = command.getRestOfLine();
			Door doorway = null;
			doorway = player.getcurrentRoom().getExit(direction);
			
			if (doorway == null) {
				Writer.println("There is no door in that direction!");
			}
			else if (doorway.isLocked()) {
				Writer.println("This door is already locked.");
			}
			else if (doorway.getKey() == null) {
				Writer.println("This door cannot be locked.");
			}
			else {
				Writer.print("With what?");
				String keyname = Reader.getResponse();
				if (player.getItem(keyname) == null) {
					Writer.println("You don't have this item!");
				}
				else if(doorway.getKey().getName() != keyname) {
					Writer.println("That item does not lock this door.");
				}
				else {
					doorway.setLocked(true);
					Writer.print("The door to the " + direction + " has been locked.");
				}
			}
		}
	}
	
	/**
	 * Method to implement the unpack command.
	 * 
	 * @param command The command entered by the player.
	 */
	private void unpack(Command command) {
		if (!command.hasSecondWord()) {
			Writer.println("Unpack what?");
		}
		else {
			String container = command.getRestOfLine();
			if (player.getcurrentRoom().getItem(container) == null && player.getItem(container) == null) {
				Writer.println("You don't see this item anywhere...");
			}
			else if(player.getcurrentRoom().getItem(container) == null) {
				if(player.getItem(container) instanceof Container) {
					Container thiscontainer = (Container) player.getItem(container);
					Writer.println("What item would you like to unpack?");
					String takeitem = Reader.getResponse();
					if (thiscontainer.getItemInContainer(takeitem) == null) {
						Writer.println("You can't find this item here...");
					}
					else {
						if(player.canPickUp(thiscontainer.getItemInContainer(takeitem))) {
							player.addItem(thiscontainer.getItemInContainer(takeitem));
							if (player.getItem(takeitem) != null) {
								thiscontainer.removeItem(takeitem);
							}
						}
						
					}
					
				}
				else {
					Writer.println("This item is not a container.");
				}
			}
			else {
				if(player.getcurrentRoom().getItem(container) instanceof Container) {
					Container thiscontainer = (Container) player.getcurrentRoom().getItem(container);
					Writer.println("What item would you like to unpack?");
					String takeitem = Reader.getResponse();
					if (thiscontainer.getItemInContainer(takeitem) == null) {
						Writer.println("You can't find this item here...");
					}
					else {
						if (player.canPickUp(thiscontainer.getItemInContainer(takeitem))) {
							player.addItem(thiscontainer.getItemInContainer(takeitem));
							if (player.getItem(takeitem) != null) {
								thiscontainer.removeItem(takeitem);
							}
						}
					}
				}
				else {
					Writer.println("This item is not a container.");
				}
			}
		}
	}
	
	/**
	 * Method to implement pack command. **NOT FULLY FUNCTIONAL YET**
	 * 
	 * @param command The command entered by the player.
	 */
	private void pack(Command command) {
		if (!command.hasSecondWord()) {
			Writer.println("Pack what?");
		}
		else if (player.getcurrentRoom().getItem(command.getRestOfLine()) == null && player.getItem(command.getRestOfLine()) == null) { 
			Writer.println("You don't see this item anywhere...");
		}
		// Item is in player's inventory.
		else if (player.getcurrentRoom().getItem(command.getRestOfLine()) == null) {
			Writer.println("In what container?");
			String containername = Reader.getResponse();
			if (player.getcurrentRoom().getItem(containername) == null && player.getItem(containername) == null) {
				Writer.println("You can't find this container...");
			}
			// Item is in player's inventory and container is in player's inventory.
			else if (player.getcurrentRoom().getItem(containername) == null) {
				if (player.getItem(containername) instanceof Container) {
					Container thiscontainer = (Container) player.getItem(containername);
					Item removed = player.removeItem(command.getRestOfLine());
					thiscontainer.addItem(removed);
					Writer.println(removed.getName() + " was added to " + thiscontainer.getName() + ".");
				}
				else {
					Writer.println("This item is not a container.");
				}
			}
			// Item is in player's inventory and container is in the current room.
			else {
				if (player.getcurrentRoom().getItem(containername) instanceof Container) {
					Container thiscontainer = (Container) player.getcurrentRoom().getItem(containername);
					Item removed = player.removeItem(command.getRestOfLine());
					thiscontainer.addItem(removed);
					Writer.println(removed.getName() + " was added to " + thiscontainer.getName() + ".");
				}
				else {
					Writer.println("This item is not a container.");
				}
			}
		}
		// Item is in current room.
		else {
			Item putitem = player.getcurrentRoom().getItem(command.getRestOfLine());
			if (putitem.getWeight() > player.getCapacity()) {
				Writer.println("This item is too heavy to move!");
			}
			else {
				Writer.println("In what container?");
				String containername = Reader.getResponse();
				if (player.getcurrentRoom().getItem(containername) == null && player.getItem(containername) == null) {
					Writer.println("You can't find this container...");
				}
				// Item is in current room and container is in player's inventory.
				else if (player.getcurrentRoom().getItem(containername) == null) {
					if (player.getItem(containername) instanceof Container) {
						if (player.canPickUp(putitem)) {
							Container thiscontainer = (Container) player.getItem(containername);
							Item removed = player.getcurrentRoom().removeItem(command.getRestOfLine());
							thiscontainer.addItem(removed);
							Writer.println(removed.getName() + " was added to " + thiscontainer.getName() + ".");
						}
						else {
							Writer.println("You're carrying too much to add that item to a container in your inventory!");
						}
					}
					else {
						Writer.println("This item is not a container.");
					}
				}
				// Item is in current room and container is in current room.
				else {
					if (player.getcurrentRoom().getItem(containername) instanceof Container) {
						Container thiscontainer = (Container) player.getcurrentRoom().getItem(containername);
						Item removed = player.getcurrentRoom().removeItem(command.getRestOfLine());
						thiscontainer.addItem(removed);
						Writer.println(removed.getName() + " was added to " + thiscontainer.getName() + ".");
					}
					else {
						Writer.println("This item is not a container.");
					}
				}
				
			}
		}
		
	}
	
	/**
	 * Method to build an item.
	 * 
	 * @param command The command entered by the player.
	 */
	public void build(Command command) {
		if (!command.hasSecondWord()) {
			Writer.println("Build what?");
		}
		else {
			if(world.getBuildable(command.getRestOfLine()) == null) {
				Writer.println("You aren't able to make that item!");
			}
			else {
				HashMap<Item, Boolean> hasitem = new HashMap<>();
				world.getBuildable(command.getRestOfLine()).setComplete(true);
				for (Item ingredient : world.getBuildable(command.getRestOfLine()).getIngredients().values()) {
					if (player.getcurrentRoom().getItem(ingredient.getName().toLowerCase()) == null && player.getItem(ingredient.getName().toLowerCase()) == null) {
						hasitem.put(ingredient, false);
						world.getBuildable(command.getRestOfLine()).setComplete(false);
					}
					else if (player.getItem(ingredient.getName().toLowerCase()) != null) {
						hasitem.put(ingredient, true);
					}
					else {
						hasitem.put(ingredient, true);
					}
				}
				if(!world.getBuildable(command.getRestOfLine()).isComplete()) {
					for (Item item : hasitem.keySet()) {
						if (!hasitem.get(item)) {
							Writer.println("You are missing the ingredient " + item.getName() + ".");
						}
					}
				} 
				else {
					Writer.println(world.getBuildable(command.getRestOfLine()).getName() + " was built.");
					for (Item thing : hasitem.keySet()) {
						if (player.getcurrentRoom().getItem(thing.getName().toLowerCase()) != null) {
							player.getcurrentRoom().removeItem(thing.getName().toLowerCase());
						}
						else if (player.getItem(thing.getName().toLowerCase()) != null) {
							player.removeItem(thing.getName().toLowerCase());
						}
					}
					BuildableItem newitem = world.getBuildable(command.getRestOfLine());
					if (player.canPickUp(newitem)) {
						player.addItem(newitem);
						score = score + newitem.getPoints();
						newitem.setPoints(0);
					}
					else {
						player.getcurrentRoom().addItem(newitem);
						Writer.println(newitem.getName() + " was added to the room.");
					}
				}
			}
		}
	}
	
	/**
	 * Execute the dismantle command.
	 * 
	 * @param command The command entered by the player.
	 */
	public void dismantle(Command command) {
		if (!command.hasSecondWord()) {
			Writer.println("Dismantle what?");
		}
		else {
			BuildableItem tobedismantled;
			if(player.getcurrentRoom().getItem(command.getRestOfLine()) == null && player.getItem(command.getRestOfLine()) == null) {
				Writer.println("You don't see this item anywhere...");
			}
			else if(player.getcurrentRoom().getItem(command.getRestOfLine()) != null) {
				if (player.getcurrentRoom().getItem(command.getRestOfLine()) instanceof BuildableItem) {
					tobedismantled = (BuildableItem) player.getcurrentRoom().getItem(command.getRestOfLine());
					if(tobedismantled.canBeDismantled()) {
						player.getcurrentRoom().removeItem(command.getRestOfLine());
						Writer.println(tobedismantled.getName() + " has been dismantled.");
						for (Item ingredient : tobedismantled.getIngredients().values()) {
							player.getcurrentRoom().addItem(ingredient);
							Writer.println(ingredient + " has been placed in the room.");
						}
					}
					else {
						Writer.println("You can't dismantle a potion!");
					}
				}
				else {
					Writer.println("You can't dismantle this!");
				}
			}
			else {
				if(player.getItem(command.getRestOfLine()) instanceof BuildableItem) {
					tobedismantled = (BuildableItem) player.getItem(command.getRestOfLine());
					if(tobedismantled.canBeDismantled()) {
						player.removeItem(command.getRestOfLine());
						Writer.println(tobedismantled.getName() + " has been dismantled.");
						for (Item ingredient : tobedismantled.getIngredients().values()) {
							if(player.canPickUp(ingredient)) {
								player.addItem(ingredient);
								Writer.println(ingredient + " has been added to your bag.");
							}
							else {
								player.getcurrentRoom().addItem(ingredient);
								Writer.println(ingredient + " has been placed in the room.");
							}
						}
					}
					else {
						Writer.println("You can't dismantle a potion!");
					}
				}
				else {
					Writer.println("You can't dismantle this!");
				}
			}
		}
	}
	
	/**
	 * Allows player to drink things.
	 * 
	 * @param command The command entered by the player.
	 */
	public void drink(Command command) {
		if (!command.hasSecondWord()) {
			Writer.println("Drink what?");
		}
		else {
			if (player.getcurrentRoom().getItem(command.getRestOfLine()) != null || player.getItem(command.getRestOfLine()) != null) {
				Item thisitem;
				if (player.getcurrentRoom().getItem(command.getRestOfLine()) != null) {
					thisitem = player.getcurrentRoom().getItem(command.getRestOfLine());
					if (thisitem.getName().toLowerCase().equals("polyjuice potion")) {
						Writer.println("You drank the Polyjuice Potion!");
						Writer.println("Your skin begins to bubble...and change...");
						Writer.println("...");
						Writer.println("...");
						Writer.println("You look just like Hufflepuff Student Cedric Diggory!");
						Writer.println("Go quickly, the potion will wear off soon.");
						world.getRoom("Hufflepuff Hall").setVisible("north", true);
						world.getRoom("Gryffindor Tower Hallway").setVisible("north", false);
						player.getcurrentRoom().removeItem(thisitem.getName().toLowerCase());
					}
					else if (thisitem.getName().toLowerCase().equals("stardust bottle")) {
						Writer.println("You drank the Stardust Bottle liquid!");
						Writer.println("You don't feel very good after drinking that...");
						player.getcurrentRoom().removeItem(thisitem.getName().toLowerCase());
						
					}
					else if (thisitem.getName().toLowerCase().equals("butterbeer")) {
						Writer.println("You drank the Butterbeer!");
						Writer.println("That was delicious!");
						player.getcurrentRoom().removeItem(thisitem.getName().toLowerCase());
						score = score + 50;
					}
					else if (thisitem.getName().toLowerCase().equals("cup of tea")) {
						Writer.println("You drank the Cup of Tea!");
						Writer.println("It's not the best tea you've ever had, but it's defintely the fanciest.");
						player.getcurrentRoom().removeItem(thisitem.getName().toLowerCase());
						score = score + 20;
					}
					else {
						Writer.println("You can't drink that!");
					}
				}
				else {
					thisitem = player.getItem(command.getRestOfLine());
					if (thisitem.getName().toLowerCase().equals("polyjuice potion")) {
						Writer.println("You drank the Polyjuice Potion!");
						Writer.println("Your skin begins to bubble...and change...");
						Writer.println("...");
						Writer.println("...");
						Writer.println("You look just like Hufflepuff Student Cedric Diggory!");
						Writer.println("Go quickly, the potion will wear off soon.");
						world.getRoom("Hufflepuff Hall").setVisible("north", true);
						world.getRoom("Gryffindor Tower Hallway").setVisible("north", false);
						player.removeItem(thisitem.getName().toLowerCase());
					}
					else if (thisitem.getName().toLowerCase().equals("stardust bottle")) {
						Writer.println("You drank the Stardust Bottle liquid!");
						Writer.println("You don't feel very good after drinking that...");
						player.removeItem(thisitem.getName().toLowerCase());
						
					}
					else if (thisitem.getName().toLowerCase().equals("butterbeer")) {
						Writer.println("You drank the Butterbeer!");
						Writer.println("That was delicious!");
						player.removeItem(thisitem.getName().toLowerCase());
						score = score + 50;
					}
					else if (thisitem.getName().toLowerCase().equals("cup of tea")) {
						Writer.println("You drank the Cup of Tea!");
						Writer.println("It's not the best tea you've ever had, but it's defintely the fanciest.");
						player.removeItem(thisitem.getName().toLowerCase());
						score = score + 20;
					}
					else {
						Writer.println("You can't drink that!");
					}
				}
			}
			else {
				Writer.println("You don't see that item anywhere...");
			}
		}
	}
	
	/**
	 * Allows player to eat things.
	 * 
	 * @param command The command entered by the player.
	 */
	public void eat(Command command) {
		if (!command.hasSecondWord()) {
			Writer.println("Eat what?");
		}
		else {
			if (player.getcurrentRoom().getItem(command.getRestOfLine()) != null || player.getItem(command.getRestOfLine()) != null) {
				Item thisitem;
				if (player.getcurrentRoom().getItem(command.getRestOfLine()) != null) {
					thisitem = player.getcurrentRoom().getItem(command.getRestOfLine());
					if (thisitem.getName().toLowerCase().equals("chocolate frog")) {
						Writer.println("You ate the Chocolate Frog!");
						Writer.println("It was good, but now you can feel it hopping around your stomach.");
						score = score + 25;
						player.getcurrentRoom().removeItem(thisitem.getName().toLowerCase());
					}
					else if (thisitem.getName().toLowerCase().equals("bertie bott's every flavor beans")) {
						Writer.println("You ate the Bertie Bott's Every Flavor Beans!");
						Writer.println("Some of those tasted really bad...");
						score = score + 10;
						player.getcurrentRoom().removeItem(thisitem.getName().toLowerCase());
						
					}
					else if (thisitem.getName().toLowerCase().equals("ton-tongue fudge")) {
						Writer.println("You ate the Ton-Tongue Fudge!");
						Writer.println("That was delicious!");
						Writer.println("Your tongue begins to grow...");
						Writer.println("...and grow...");
						Writer.println("...and grow...");
						Writer.println("It stops growing.");
						Writer.println("The packaging says it will only last a few minutes. Hopefully, you will be back to normal soon.");
						player.getcurrentRoom().removeItem(thisitem.getName().toLowerCase());
					}
					else {
						Writer.println("You can't eat that!");
					}
				}
				else {
					thisitem = player.getItem(command.getRestOfLine());
					if (thisitem.getName().toLowerCase().equals("chocolate frog")) {
						Writer.println("You ate the Chocolate Frog!");
						Writer.println("It was good, but now you can feel it hopping around your stomach.");
						score = score + 25;
						player.removeItem(thisitem.getName().toLowerCase());
					}
					else if (thisitem.getName().toLowerCase().equals("bertie bott's every flavor beans")) {
						Writer.println("You ate the Bertie Bott's Every Flavor Beans!");
						Writer.println("Some of those tasted really bad...");
						score = score + 10;
						player.removeItem(thisitem.getName().toLowerCase());
						
					}
					else if (thisitem.getName().toLowerCase().equals("ton-tongue fudge")) {
						Writer.println("You ate the Ton-Tongue Fudge!");
						Writer.println("That was delicious!");
						Writer.println("Your tongue begins to grow...");
						Writer.println("...and grow...");
						Writer.println("...and grow...");
						Writer.println("It stops growing.");
						Writer.println("The packaging says it will only last a few minutes. Hopefully, you will be back to normal soon.");
						player.removeItem(thisitem.getName().toLowerCase());
					}
					else {
						Writer.println("You can't eat that!");
					}
				}
			}
			else {
				Writer.println("You don't see that item anywhere...");
			}
		}
	}
	
}

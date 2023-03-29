/**
 *  This class is the main class of the "World of Zuul" application. 
 *  "World of Zuul" is a very simple, text based adventure game.  Users 
 *  can walk around some scenery. That's all. It should really be extended 
 *  to make it more interesting!
 * 
 *  To play this game, create an instance of this class and call the "play"
 *  method.
 * 
 *  This main class creates and initialises all the others: it creates all
 *  rooms, creates the parser and starts the game.  It also evaluates and
 *  executes the commands that the parser returns.
 * 
 * @author  Michael Kölling and David J. Barnes
 * @version 2016.02.29
 */

public class Game 
{
    private Parser parser;
    private Room currentRoom;
    private String answer;
    private boolean question;
    /**
     * Create the game and initialise its internal map.
     */
    public Game() 
    {
        createRooms();
        parser = new Parser();
        String[] backpack = new String[3];
    }

    /**
     * Create all the rooms and link their exits together.
     */
    public void createRooms()
    {
        Room mainEntrance, advising, firstFloorSomerset, secondFloorSomerset, library, theatre, westBuilding, scienceCenter, physicalEducation, bateman, 
                tutoring, soccerField, artsBuilding, cafeteria, collegeCenter, secondFloorHunterdon, firstFloorHunterdon, lobby;
      
        // create the rooms
        mainEntrance = new Room("the main entrance of RVCC");
        advising = new Room("in the Advising Center");
        firstFloorSomerset = new Room("in the first floor of the Somerset hall");
        secondFloorSomerset = new Room("in the second floor of the Somerset hall");
        library = new Room("in the Library");
        theatre = new Room("in the Theatre");
        westBuilding = new Room("in the West Building");
        scienceCenter = new Room("in the Science Center");
        physicalEducation = new Room("in the P.E. Building");
        bateman = new Room("in the Bateman Center");
        tutoring = new Room("in the Tutoring Center");
        soccerField = new Room("by the Soccer Field");
        artsBuilding = new Room("in the Arts Building");
        cafeteria = new Room("in the Cafeteria");
        collegeCenter = new Room("in the College Center");
        secondFloorHunterdon = new Room("in the second floor of the Hunterdon hall");
        firstFloorHunterdon = new Room("in the first floor of the Hunterdon hall");
        lobby = new Room("in the Lobby");
        
        // initialise room exits
        mainEntrance.setExit("west", advising);
        mainEntrance.setExit("north", lobby);
        mainEntrance.setExit("east", firstFloorSomerset);

        advising.setExit("up", library);
        advising.setExit("east", mainEntrance);
        advising.setExit("west", theatre);

        library.setExit("west", westBuilding);
        library.setExit("down", advising);

        theatre.setExit("east", advising);

        westBuilding.setExit("north", scienceCenter);
        westBuilding.setExit("northEast", physicalEducation);
        westBuilding.setExit("east", library);
        
        scienceCenter.setExit("south", westBuilding);
        scienceCenter.setExit("east", physicalEducation);
        
        physicalEducation.setExit("west", scienceCenter);
        physicalEducation.setExit("east", cafeteria);
        physicalEducation.setExit("north", bateman);
        
        bateman.setExit("up", tutoring);
        bateman.setExit("north", soccerField);
        bateman.setExit("south", physicalEducation);
        
        tutoring.setExit("down", bateman);
        
        soccerField.setExit("south", bateman);
        soccerField.setExit("west", artsBuilding);
        
        cafeteria.setExit("down", collegeCenter);
        cafeteria.setExit("east", secondFloorHunterdon);
        cafeteria.setExit("west", physicalEducation);
        
        collegeCenter.setExit("up", cafeteria);
        collegeCenter.setExit("east", firstFloorHunterdon);
        collegeCenter.setExit("down", lobby);
        collegeCenter.setExit("south", secondFloorSomerset);
        
        lobby.setExit("up", collegeCenter);
        lobby.setExit("south", mainEntrance);
        
        secondFloorHunterdon.setExit("down", firstFloorHunterdon);
        secondFloorHunterdon.setExit("west", cafeteria);
        
        firstFloorHunterdon.setExit("up", secondFloorHunterdon);
        firstFloorHunterdon.setExit("south", secondFloorSomerset);
        firstFloorHunterdon.setExit("west", collegeCenter);
        
        secondFloorSomerset.setExit("down", firstFloorSomerset);
        secondFloorSomerset.setExit("north", collegeCenter);
        secondFloorSomerset.setExit("east", firstFloorSomerset);
        
        firstFloorSomerset.setExit("up", secondFloorSomerset);
        secondFloorSomerset.setExit("west", mainEntrance);

        currentRoom = mainEntrance;  // start game outside
    }

    /**
     *  Main play routine.  Loops until end of play.
     */
    public void play() 
    {            
        printWelcome();

        // Enter the main command loop.  Here we repeatedly read commands and
        // execute them until the game is over.
                
        boolean finished = false;
        while (! finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println("Thank you for playing.  Good bye.");
    }

    /**
     * Print out the opening message for the player.
     */
    private void printWelcome()
    {
        System.out.println();
        System.out.println("Welcome to the Raritan Valley Community College");
        System.out.println("Let`s go find Professor Crosbie, you only have a couple more");
        System.out.println("minutes to turn in your homework. Hurry up!");
        System.out.println("Type '" + CommandWord.HELP + "' if you need help.");
        System.out.println();
        System.out.println(currentRoom.getLongDescription());
    }

    /**
     * Given a command, process (that is: execute) the command.
     * @param command The command to be processed.
     * @return true If the command ends the game, false otherwise.
     */
    private boolean processCommand(Command command) 
    {
        boolean wantToQuit = false;

        CommandWord commandWord = command.getCommandWord();

        switch (commandWord) {
            case UNKNOWN:
                System.out.println("I don't know what you mean...");
                break;

            case HELP:
                printHelp();
                break;

            case GO:
                goRoom(command);
                break;

            case QUIT:
                wantToQuit = quit(command);
                break;
                
            case EAT:
                printEat();
                break;
                
            case LOOK:
                lookAround();
                break;
                
        }
        return wantToQuit;
    }

    // implementations of user commands:

    /**
     * Print out some help information.
     * Here we print some stupid, cryptic message and a list of the 
     * command words.
     */
    private void printHelp() 
    {
        System.out.println("You are lost. You are alone. You wander");
        System.out.println("around at the university.");
        System.out.println();
        System.out.println("Your command words are:");
        parser.showCommands();
    }

    private void printEat()
    {
        System.out.println("You just ate. Now you are not hungry anymore.");
        
    }
    
    private void lookAround()
    {
        if (currentRoom == library){
            
            System.out.println("Hey look! There is an Anatomy book on the floor, do you want to take it?");
            
            if ( answer == "yes"){
                
                System.out.println("You picked up the item!");
            }
            
            else if (answer == "no"){
                
                System.out.println("You left the item behind.");

            }
            
            else {
                
                System.out.println("It's an yes/no question man,c'mon.");
            }
        }
        
        else {
            
            System.out.println("The room is empty. :/");
        }
        
    }
    
    /** 
     * Try to go in one direction. If there is an exit, enter the new
     * room, otherwise print an error message.
     */
    private void goRoom(Command command) 
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Go where?");
            return;
        }

        String direction = command.getSecondWord();

        // Try to leave current room.
        Room nextRoom = currentRoom.getExit(direction);

        if (nextRoom == null) {
            System.out.println("There is no door!");
        }
        else {
            currentRoom = nextRoom;
            System.out.println(currentRoom.getLongDescription());
        }
    }
    
    /** 
     * "Quit" was entered. Check the rest of the command to see
     * whether we really quit the game.
     * @return true, if this command quits the game, false otherwise.
     */
    private boolean quit(Command command) 
    {
        if(command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        }
        else {
            return true;  // signal that we want to quit
        }
    }
}

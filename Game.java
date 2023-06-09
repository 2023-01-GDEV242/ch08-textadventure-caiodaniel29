import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashMap;
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
 * 
 * edited by Caio Sanchez
 */

public class Game 
{
    private Parser parser;
    private Room currentRoom;
    private Room pastRoom;
    private boolean answer;
    private boolean question;
    private String response;
    private Room library2;
    private Room westBuilding2;
    private Room cafeteria2;
    private Room advising2;
    private Room firstFloorSomerset2;
    private Room mainEntrance2;
    private Room soccerField2;
    private Room secondFloorSomerset2;
    private Room gym;
    private Room arts2;
    private Items bookAnatomy;
    private Scanner reader;   
    private int maxItems = 3;
    private int items = 0;
    private String energyStatus;
    private int energy = 10;
    private boolean finished = false;
    HashMap<String, String> bag = new HashMap<String, String>();
    
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
     * Also creating all the items and linking them to their specific 
     * initial rooms.
     */
    public void createRooms()
    {
        Room mainEntrance, advising, firstFloorSomerset, secondFloorSomerset, library, theatre, westBuilding, scienceCenter, physicalEducation, bateman, 
                tutoring, soccerField, artsBuilding, cafeteria, collegeCenter, secondFloorHunterdon, firstFloorHunterdon, lobby;
      
        Items book, keys, soccerBall;
        
        //Energy energyLevel;
                
        // create the rooms
        mainEntrance = new Room("the main entrance of RVCC");
        advising = new Room("in the Advising Center, and Coach Mark is here");
        firstFloorSomerset = new Room("in the first floor of the Somerset hall");
        secondFloorSomerset = new Room("in the second floor of the Somerset hall, and the security is here..");
        library = new Room("in the Library");
        theatre = new Room("in the Theatre");
        westBuilding = new Room("in the West Building, Mike is here");
        scienceCenter = new Room("in the Science Center");
        physicalEducation = new Room("in the P.E. Building. Erik(the trainer) is at the gym");
        bateman = new Room("in the Bateman Center");
        tutoring = new Room("in the Tutoring Center");
        soccerField = new Room("by the Soccer Field. You see the ball bag, take a ball if you want");
        artsBuilding = new Room("in the Arts Building. Professor Crosbie is hereee");
        cafeteria = new Room("in the Cafeteria. There are free bagels here today");
        collegeCenter = new Room("in the College Center");
        secondFloorHunterdon = new Room("in the second floor of the Hunterdon hall");
        firstFloorHunterdon = new Room("in the first floor of the Hunterdon hall");
        lobby = new Room("in the Lobby");
        
        //create items
        book = new Items( 2, "Anatomy book");
        keys = new Items( 2, "Keychain");
        soccerBall = new Items( 3, "Soccer ball");
        
        // //creating the starting energy
        // energyLevel = new Energy(10);
        
        // initialise room exits
        mainEntrance.setExit("west", advising);
        mainEntrance.setExit("north", lobby);
        mainEntrance.setExit("east", firstFloorSomerset);

        advising.setExit("up", library);
        advising.setExit("east", mainEntrance);
        advising.setExit("west", theatre);

        library.setExit("west", westBuilding);
        library.setExit("down", advising);
        library.setItem(book);

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
        secondFloorSomerset.setItem(keys);
        
        firstFloorSomerset.setExit("up", secondFloorSomerset);
        // firstFloorSomerset.setExit("west", mainEntrance);        this door is a trap door (no way back)

        library2 = library;
        westBuilding2 = westBuilding;
        cafeteria2 = cafeteria;
        advising2 = advising;
        firstFloorSomerset2 = firstFloorSomerset;
        mainEntrance2 = mainEntrance;
        secondFloorSomerset2 = secondFloorSomerset;
        gym = physicalEducation;
        soccerField2 = soccerField;
        arts2 = artsBuilding;
        
        bookAnatomy = book;
        
        currentRoom = mainEntrance;  // start game in the main entrance
        pastRoom = null;             // start with no previous room
    }

    /**
     *  Main play routine.  Loops until end of play.
     */
    public void play() 
    {            
        printWelcome();

        // Enter the main command loop.  Here we repeatedly read commands and
        // execute them until the game is over.
                
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
            /*    
            case LOOK:
                lookAround();
                break;
            
            case YES:
                question();
                break;
                
            case NO:
                question2();
                break;
            */
            case TAKE:
                takeItem();
                break;
                
            case BACK:
                backOneRoom();
                break;
                
            case TALK:
                talkToNpc();
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
        System.out.println("You need find Professor Crosbie ASAP, but you have no idea where he is. ");
        System.out.println("He is at RVCC, but where? Time is ticking, find him before it is too late!");
        System.out.println("Also, you hurt your ankle at practice, you can only carry 5 kilograms in your bag at a time.");
        System.out.println();
        System.out.println("Your command words are:");
        parser.showCommands();
    }

    /**
     * Here is the EAT command. It will increase the energy value, and set it back to 10
     * which is its default value.
     */
    private void printEat()
    {
        if(currentRoom == cafeteria2 && energy < 10){
            energy = 10;
            System.out.println("You just ate. Now you are not hungry anymore.");
        }
        
        else if(currentRoom == cafeteria2 && energy == 10){
            
            System.out.println("You can't eat, you are full already.");
        }
        
        else {
            System.out.println("There is no food here.");
        }
        
    }
    /**
     * this method is responsible to crate the communication between the the player
     * and the NPC's of the game.
     */
    private void talkToNpc(){
        
        if(currentRoom == advising2){
            
            System.out.println("Coach Mark: ");
            System.out.println("Look for your Professsor where your class normally is.");
        }
        
        else if(currentRoom == westBuilding2){
            System.out.println("Mike: ");
            System.out.println("Hey man, you just missed him! He said he was going to his office.");
        }
        
        else if(currentRoom == secondFloorSomerset2){
            System.out.println("Security: ");
            System.out.println("I don't know about your professor, but there's free bagels in the Cafeteria if you are hungry.");
        }
        
        else if(currentRoom == gym){
            System.out.println("Erik: ");
            System.out.println("Hey, I think Crosbie's office is in the Arts building man, go check it out. And how is the knee?");
        }
        
        
    }
    
    /*.
     * 
    // case of the user input "yes":
    private void question(){
      
        if (currentRoom == library2){
           
         if (items < 3){
            System.out.println("You picked up the item!");       
            bag.put("Book", "Book");
            items++;
         }
         else {
            System.out.println("Your bag is full! You can't pick up the item."); 
         }
      }
        else if(currentRoom == westBuilding2){
            System.out.println("Mike:");
            System.out.println("Hey man, he just left! He said he'll be in his office.");
        }
        
      else{
          System.out.println("Yes?");
      }
    }
    
    // case of the "no" from user:
    private void question2(){
        
          if (currentRoom == library2){
    
           System.out.println("You left the item.");       
          }
          else{
           System.out.println("No?");
       }
      
    }
    */
    
    /**
     * The take method will make possible to pick up the item that is located 
     * in the current room that the player is. Not letting him/her pick it up more than once.
     */
    private void takeItem(){
        
        if (currentRoom.hasItem() == true && items > 5){
            
            System.out.println("You can`t pick up the item, your bag is full.");
        }
        
        else if (currentRoom.hasItem() == true && items < 5){
            
            Items itemToTake = currentRoom.getItem();
            
            System.out.println("You picked up the " + itemToTake.getDescription() + "!");       
            
            if(itemToTake.getDescription() == "Anatomy book"){
                bag.put("Book", "Book");
                items++;
                items++;
            }
            
            else if(itemToTake.getDescription() == "Keychain"){
                bag.put("Keys", "Keys");
                items++;
                items++;
            }
            
            else if(itemToTake.getDescription() == "Soccer ball"){
                bag.put("Ball", "Ball");
                items++;
                items++;
                items++;
            }
        }
        
        else {
            
            System.out.println("There is no item in the room.");
        }
        
    }
        
    /**
     * The look method, should check if the room has any items or persons on it. 
     * Changed plans for now because of lack of time.
     */
    private void lookAround()
    {
        if (currentRoom == library2){
            
            System.out.println("Hey look! There is an Anatomy book on the floor, do you want to take it?");
        }
        
      else if (currentRoom == westBuilding2){
          
          System.out.println("Professor Crosbie is not here, but Mike is in the computer lab!");
          System.out.println("Do you want to ask him about Crosbie?");
      }
        
        else {
            System.out.println("The room is empty. :/");
        }
        
    }
    
    /**
     *  The back command (back one room).
     */
    private void backOneRoom(){
        
        if( currentRoom == firstFloorSomerset2 && pastRoom == mainEntrance2){
            
            System.out.println("You went through a trap door, you can't go back.");
        }
        
        
        else if (pastRoom != null){
            
            currentRoom = pastRoom;
            System.out.println(currentRoom.getLongDescription());
        }
        
        else {
            System.out.println("There is no previous room to go back to.");
        }
    }
    
    /** 
     * Try to go in one direction. If there is an exit, enter the new
     * room, otherwise print an error message.
     * Also, set the previous room to the current room before changing the current
     * room to the next room.
     * Also, adding the energy countdown, after every move you lose 1 energy point,
     * if it gets to zero, it is game over.
     * Also, adding the case of the win of the player.
     */
    private void goRoom(Command command) 
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Go where?");
            return;
        }

        if(energy == 3){
            energyStatus = "You are starving!";
        }
        else if(energy > 3 && energy == 6){
            energyStatus = "You are getting hungry...";
        }
        else {
            energyStatus = "";
        }
        
        String direction = command.getSecondWord();

        // Try to leave current room.
        Room nextRoom = currentRoom.getExit(direction);

        if (nextRoom == null) {
            System.out.println("There is no door!");
        }
        else {
            pastRoom = currentRoom;
            currentRoom = nextRoom;
            System.out.println(currentRoom.getLongDescription() + "\n" + energyStatus);
            energy--;
            
        }
        
        if(energy == 0){
            System.out.println("You ran out of energy.");
            System.out.println("GAME OVER");
            finished = true;
        }
        
        if(currentRoom == arts2){
            System.out.println("YOU FOUND PROFESSOR CROSBIE!!!");
            System.out.println("You found Professor Crosbie on time to turn in your homework, Congratulations!");
            System.out.println("YOU WIN!");
            System.out.println("Thank you for playing, bye bye.");
            finished = true;
            
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

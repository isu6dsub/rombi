package Control;

import java.util.Scanner;

import Sub.Rombi;

/**
 * This class houses the interactive console for the control software.
 * It allows users to control the submarine from a SSH terminal or directly
 * on the Pi itself. It acts like a DOS or CP/M terminal and all of its
 * commands simply call methods that the Rombi class exposes.
 * @author Vaughn Dorsey
 *
 */
public class ConsoleUI {
	
	/**
	 * This method is the entirety of the command console.
	 * The main logic of it is a switch statement that contains all of
	 * the commands it supports. The switches will call the appropriate
	 * method in this class to call the correct commands.
	 */
	public static void startPrompt(){
		Scanner input = new Scanner(System.in);
		System.out.println("Starting Prompt");
		
		while(true){
			System.out.print("Rombi> ");
			String command = input.nextLine().toLowerCase();
			String[] splitCommand = command.split("\\s");
			try{
				switch(splitCommand.length) {
					case 1: // Commands that have no arguments.
						switch(splitCommand[0]){
							case "exit":
								input.close();
								exit();
								return;
							case "help":
								showHelp("interpreter");
								break;
							default:
								System.out.println("Invalid Command for this system version. See help for valid commands.");
								System.out.println("Command given: "+command);
						}
					case 2: // Commands that only have one argument
						switch(splitCommand[0]){
							case "surface":
								Rombi.getInstance().move("surface", Integer.parseInt(splitCommand[1]));
								break;
							case "dive":
								Rombi.getInstance().move("dive", Integer.parseInt(splitCommand[1]));
								break;
							case "help":
								showHelp(splitCommand[1]);
								break;
							default:
								System.out.println("Invalid Command for this system version. See help for valid commands.");
								System.out.println("Command given: "+command);
								break;
						}
					case 3: // Commands that have two arguments
						switch(splitCommand[0]) {
							case "go":
								go(splitCommand[1], Integer.parseInt(splitCommand[2]));
								break;
							case "turn":
								turn(splitCommand[1], Integer.parseInt(splitCommand[2]));
								break;
							case "pitch":
								pitch(splitCommand[1], Integer.parseInt(splitCommand[2]));
								break;
							case "roll":
								roll(splitCommand[1], Integer.parseInt(splitCommand[2]));
								break;
							case "yaw":
								yaw(splitCommand[1], Integer.parseInt(splitCommand[2]));
								break;
							default:
								System.out.println("Invalid Command for this system version. See help for valid commands.");
								System.out.println("Command given: "+command);
								break;
						}
					default: // For when a command is given more than 2 arguments, as they don't exist.
						System.out.println("Invalid Command for this system version. See help for valid commands.");
						System.out.println("Command given: "+command);
						break;
				}
			} catch(NumberFormatException e){
				System.out.println("The your speed value is not an integer. Please try again.");
				System.out.println("Command given: "+command);
			}
		}
	}
	
	/**
	 * Contains code to command the submarine to go forward, backward, left, or right.
	 * 
	 * @param direction The direction to move the submarine in.
	 * @param speed How fast the sub should move in this direction, as a percentage of throttle.
	 */
	public static void go(String direction, int speed){
		Rombi rombi = Rombi.getInstance();
		switch(direction){
			case "forward":
				rombi.move("forward", speed);
				break;
			case "backward":
				rombi.move("backward", speed);
				break;
			case "left":
				rombi.move("move left", speed);
				break;
			case "right":
				rombi.move("move right", speed);
				break;
			default:
				System.out.println("Invalid Direction");
				break;
		}
	}
	
	/**
	 * Contains code to command the submarine to turn left or right.
	 * 
	 * @param direction The direction to move the submarine in.
	 * @param speed How fast the sub should move in this direction, as a percentage of throttle.
	 */
	public static void turn(String direction, int speed){
		Rombi rombi = Rombi.getInstance();
		switch(direction){
			case "left":
				rombi.move("turn left", speed);
				break;
			case "right":
				rombi.move("turn right", speed);
				break;
			default:
				System.out.println("Invalid Direction");
				break;
		}
	}
	
	/**
	 * Contains code to command the submarine to pitch up or down.
	 * 
	 * @param direction The direction to move the submarine in.
	 * @param speed How fast the sub should move in this direction, as a percentage of throttle.
	 */
	public static void pitch(String direction, int speed){
		Rombi rombi = Rombi.getInstance();
		switch(direction){
			case "up":
				rombi.move("pitch up", speed);
				break;
			case "down":
				rombi.move("pitch down", speed);
				break;
			default:
				System.out.println("Invalid Direction");
				break;
		}
	}
	
	/**
	 * Contains code to command the submarine to roll left or right.
	 * 
	 * @param direction The direction to move the submarine in.
	 * @param speed How fast the sub should move in this direction, as a percentage of throttle.
	 */
	public static void roll(String direction, int speed){
		Rombi rombi = Rombi.getInstance();
		switch(direction){
			case "left":
				rombi.move("roll left", speed);
				break;
			case "right":
				rombi.move("roll right", speed);
				break;
			default:
				System.out.println("Invalid Direction");
				break;
		}
	}
	
	/**
	 * Contains code to command the submarine to yaw left or right.
	 * 
	 * @param direction The direction to move the submarine in.
	 * @param speed How fast the sub should move in this direction, as a percentage of throttle.
	 */
	public static void yaw(String direction, int speed){
		Rombi rombi = Rombi.getInstance();
		switch(direction){
			case "left":
				rombi.move("yaw left", speed);
				break;
			case "right":
				rombi.move("yaw right", speed);
				break;
			default:
				System.out.println("Invalid Direction");
				break;
		}
	}
	
	/**
	 * Runs code to shutdown the Control System.
	 */
	public static void exit(){
		Rombi.getInstance().stop();
		System.out.println("System shutdown");
	}
	
	/**
	 * This method prints out a list of statements when the
	 * user requests help on the command line. It will also contain
	 * specific help statements for each command at some point.
	 * 
	 * @param command The command that the user is requesting help for.
	 */
	public static void showHelp(String command){
		switch(command.toLowerCase()){
			case "interpreter": //Help for the console in general.
				System.out.println("Help for the Rombi Interpreter");
				System.out.println("Available commands:");
				System.out.println("help <command> - Shows help info for the given command.");
				System.out.println("go <direction> <speed> - Translate submarine.");
				System.out.println("turn <direction> <speed> - Turn submarine.");
				System.out.println("pitch <direction> <speed> - Pitch submarine.");
				System.out.println("roll <direction> <speed> - Roll submarine.");
				System.out.println("yaw <direction> <speed> - Yaw submarine");
				System.out.println("surface <speed> - Order submarine to surface");
				System.out.println("dive <speed> - Order submarine to dive");
				System.out.println("exit - Shut sub down.");
				break;
			case "go":
				System.out.println("Go - Translate the submarine in a given direction");
				System.out.println("Command: go <direction> <speed>");
				System.out.println("<direction> - Valid directions: forward|backward|left|right");
				System.out.println("<speed> - Speed in terms of throttle. Valid values: 0-100." );
				break;
			case "turn":
				System.out.println("Turn - Turn the submarine in a given direction");
				System.out.println("Command: turn <direction> <speed>");
				System.out.println("<direction> - Valid directions: left|right");
				System.out.println("<speed> - Speed in terms of throttle. Valid values: 0-100." );
				break;
			case "pitch":
				System.out.println("Pitch - Pitch the submarine in a given direction");
				System.out.println("Command: pitch <direction> <speed>");
				System.out.println("<direction> - Valid directions: up|down");
				System.out.println("<speed> - Speed in terms of throttle. Valid values: 0-100." );
				break;
			case "roll":
				System.out.println("Roll - Roll the submarine in a given direction");
				System.out.println("Command: roll <direction> <speed>");
				System.out.println("<direction> - Valid directions: left|right");
				System.out.println("<speed> - Speed in terms of throttle. Valid values: 0-100." );
				break;
			case "yaw":
				System.out.println("Yaw - Yaw the submarine in a given direction");
				System.out.println("Command: turn <direction> <speed>");
				System.out.println("<direction> - Valid directions: left|right");
				System.out.println("<speed> - Speed in terms of throttle. Valid values: 0-100." );
				break;
			case "surface":
				System.out.println("Surface - Make the submarine come to the surface.");
				System.out.println("Command: surface <speed>");
				System.out.println("<speed> - Speed in terms of throttle. Valid values: 0-100." );
				break;
			case "dive":
				System.out.println("Dive - Make the submarine dive.");
				System.out.println("Command: dive <speed>");
				System.out.println("<speed> - Speed in terms of throttle. Valid values: 0-100." );
				break;
			default:
				System.out.println("Either help is unavailable for given command or command doesn't exist.");
		}
	}
}

package com.java.toyrobot;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.io.File;

/**
 * The main class that runs the Toy Robot program
 * @author Mark
 *
 */
public class Main {
	private static BufferedReader br;
	
	public static void main(String[] args) {
		br = new BufferedReader(new InputStreamReader(System.in));
		boolean commandOptionChoosen = false;
		try {
			System.out.println("Welcome to the Toy Robot program");
			System.out.println("Please enter how you want to execute commands for the toy Robot: FILE|TYPE");
			while(!commandOptionChoosen) {
				String actionExecution = br.readLine();
				
				if("FILE".equals(actionExecution)) {
					commandOptionChoosen = true;
					executeFile();
				}else if("TYPE".contentEquals(actionExecution)){
					executeTypedCommands();
				}else {
					System.out.println("Please enter either \"FILE\" or \"TYPE\"");
				}
			}
			
		} catch (IOException e) {
			System.out.println(e);
		}
	}
	
	/**
	 * Execute commands from a file
	 * @throws IOException
	 */
	private static void executeFile() throws IOException {
		System.out.println("Please enter the location of the file");
		
		boolean openedFile = false;
		
		while(!openedFile) {
			String fileLocation = br.readLine();
			try {
				File file = new File(fileLocation);
				Scanner reader = new Scanner(file);
				ToyRobot robot = new ToyRobot(5, 5);
				while(reader.hasNextLine()) {
					String line = reader.nextLine();
					System.out.println("    " + line);
					executeCommands(line, robot);
				}
				reader.close();
				openedFile = true;
	
			} catch (FileNotFoundException e){
				System.out.println(e);
			}
		}
	}
	
	/**
	 * Execute commands from standard input
	 * @throws IOException
	 */
	private static void executeTypedCommands() throws IOException {
		System.out.println("Input any of the following commands");
		System.out.println("    PLACE X,Y,F : Place down the Robot");
		System.out.println("    MOVE        : Move the robot one unit foward");
		System.out.println("    LEFT        : Rotate robot 90 degrees left");
		System.out.println("    RIGHT       : Rotate robot 90 degrees right");
		System.out.println("    REPORT      : Reports the position and facing of the robot");
		
		ToyRobot robot = new ToyRobot(5, 5);
		
		while(true) {
			String inputtedCommand = br.readLine();
			boolean succesfulCommand = executeCommands(inputtedCommand, robot);
			if(!succesfulCommand) System.out.println("invalid command");
		}
	}
	
	/**
	 * Splits up strings that contain more than one command and executes them
	 * @param commandString
	 */
	public static boolean executeCommands(String commandString, ToyRobot robot) {
		String[] commands = commandString.split(" ");
		for(int i = 0; i <commands.length; i++) {
			switch(commands[i]) {
				case "PLACE":
					if(i + 1 >= commands.length) {
						return false; //needs to include X,Y,F
					}
					++i;
					if( !executePlace(commands[i], robot)) return false;
					break;
				case "MOVE":
					robot.move();
					break;
				case "LEFT":
					robot.left();
					break;
				case "RIGHT":
					robot.right();
					break;
				case "REPORT":
					robot.report();
					break;
				default:
					return false;
			}
		}
		return true;
	}
	
	/**
	 * Executes the place command
	 * @param commandParametersString
	 * @param robot
	 * @return success of Place command
	 */
	private static boolean executePlace(String commandParametersString, ToyRobot robot) {
		String[] commandParameters = commandParametersString.split(",");
		if(commandParameters.length != 3) return false;
		try {
			int xPos = Integer.parseInt(commandParameters[0]);
			int yPos = Integer.parseInt(commandParameters[1]);
			Direction facing = Direction.valueOf(commandParameters[2]);
			robot.place(xPos, yPos, facing);
		} catch(Exception e) {
			return false;
		}
		return true;
		
	}
}

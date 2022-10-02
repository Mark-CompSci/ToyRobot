package com.java.toyrobot.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

import com.java.toyrobot.Main;
import com.java.toyrobot.ToyRobot;

public class ToyRobotTest {
	
	public static void main(String[] args) {
		System.out.println("Welcome to the toy Robot tester please enter testData file location");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		boolean openedFile = false;
		
		while(!openedFile) {
			String fileLocation;
			
			try {
				fileLocation = br.readLine();
				File file = new File(fileLocation);
				Scanner reader = new Scanner(file);

				ToyRobot robot = new ToyRobot(5, 5);
				int numOfTestCases = 0;
				int numOfPassedTestCases = 0;
				while(reader.hasNextLine()) {
					numOfTestCases++;
					String line = reader.nextLine();
					String[] testCase = line.split(" Output: ");
					Main.executeCommands(testCase[0], robot);
					boolean passed = testCase[1].equals(robot.getXPos() + "," + robot.getYPos() + "," + robot.getFacing().toString());
					System.out.println("Test case " +  numOfTestCases + ": " + passed);
					if(passed) numOfPassedTestCases++;
				}
				System.out.println("Passed test cases: " + numOfPassedTestCases + "/" + numOfTestCases);
				reader.close();
				openedFile = true;
	
			} catch (FileNotFoundException e){
				System.out.println(e);
			} catch (IOException e) {
				System.out.println(e);
			}
		}
	}
}

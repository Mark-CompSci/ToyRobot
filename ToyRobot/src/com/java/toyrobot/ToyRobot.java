package com.java.toyrobot;
/**
 * Class for ToyRobot that contains methods for robot operations
 * @author Mark
 *
 */
public class ToyRobot {
	private int xMax = -1;
	private int yMax = -1;
	private int xPos = -1;
	private int yPos = -1;
	private boolean onTable = false;
	private Direction facing;
	
	/**
	 * Constructor to initialize the size of the board the Toy Robot moves on
	 * @param xMax
	 * @param yMax
	 */
	public ToyRobot(int xMax, int yMax) {
		this.xMax = xMax;
		this.yMax = yMax;
	}
	
	public void place(int xPos, int yPos, Direction facing) {
		if(!positionIsOnTable(xPos, yPos)) return;
		this.xPos = xPos;
		this.yPos = yPos;
		this.facing = facing;
		this.onTable = true;
	}
	
	public void move() {
		if(!this.onTable) return;
		int newX = this.xPos;
		int newY = this.yPos;
		
		switch(this.facing) {
			case NORTH:
				newY++;
				break;
			case EAST:
				newX++;
				break;
			case SOUTH:
				newY--;
				break;
			case WEST:
				newX--;
				break;
		}
		
		if(!positionIsOnTable(newX, newY)) return;
		this.xPos = newX;
		this.yPos = newY;
	}
	
	public void left() {
		if(!this.onTable) return;
		this.facing = this.facing.left();
	}
	
	public void right() {
		if(!this.onTable) return;
		this.facing = this.facing.right();
	}
	
	public void report() {
		if(!this.onTable) return;
		String toyRobotReport = this.xPos + "," + this.yPos + "," + this.facing.toString();
		System.out.println("Output: " + toyRobotReport);
		
	}
	
	public int getXPos() {
		return this.xPos;
	}
	
	public int getYPos() {
		return this.yPos;
	}
	
	public Direction getFacing() {
		return this.facing;
	}
	
	private boolean positionIsOnTable(int xPos, int yPos) {
		return 0 <= xPos && xPos < this.xMax && 
			   0 <= yPos && yPos < this.yMax;
	}
}

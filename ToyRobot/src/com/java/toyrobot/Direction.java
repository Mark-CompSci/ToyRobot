package com.java.toyrobot;

/**
 * Enum for directions and changing them
 * @author Mark
 *
 */
public enum Direction {
	NORTH,
	EAST,
	SOUTH,
	WEST;
	
	public Direction left() {
		Direction facing = NORTH;
		
		switch(ordinal()) {
			case 0:
				facing = WEST;
				break;
			case 1:
				facing = NORTH;
				break;
			case 2:
				facing = EAST;
				break;
			case 3:
				facing = SOUTH;
				break;
		}
		
		return facing;
	}
	
	public Direction right() {
		Direction facing = NORTH;
		
		switch(ordinal()) {
			case 0:
				facing = EAST;
				break;
			case 1:
				facing = SOUTH;
				break;
			case 2:
				facing = WEST;
				break;
			case 3:
				facing = NORTH;
				break;
		}
		
		return facing;
	}
}

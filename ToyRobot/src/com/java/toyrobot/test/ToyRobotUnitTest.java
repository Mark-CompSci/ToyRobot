package com.java.toyrobot.test;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.java.toyrobot.Direction;
import com.java.toyrobot.ToyRobot;

class ToyRobotUnitTest {
	
	ToyRobot robot = new ToyRobot(5,5);
	
	@Test 
	void testPlace(){
		//Test invalid placement
		robot.place(-10,-10,Direction.NORTH);
		assertEquals(-1, robot.getXPos());
		assertEquals(-1, robot.getYPos());
		assertNull(robot.getFacing());
		
		robot.place(5,5,Direction.NORTH);
		assertEquals(-1, robot.getXPos());
		assertEquals(-1, robot.getYPos());
		assertNull(robot.getFacing());
		
		//Test valid placement
		robot.place(2,2,Direction.NORTH);
		assertEquals(2, robot.getXPos());
		assertEquals(2, robot.getYPos());
		assertEquals(Direction.NORTH, robot.getFacing());
	}

	@Test
	void testDirections() {
		//test not on table LEFT and RIGHT
		robot.left();
		robot.right();
		assertNull(robot.getFacing());
		
		robot.place(2,2,Direction.NORTH);
		
		//Test LEFT
		assertEquals(Direction.NORTH, robot.getFacing());
		robot.left();
		assertEquals(Direction.WEST, robot.getFacing());
		robot.left();
		assertEquals(Direction.SOUTH, robot.getFacing());
		robot.left();
		assertEquals(Direction.EAST, robot.getFacing());
		robot.left();
		assertEquals(Direction.NORTH, robot.getFacing());
		
		//testRight
		robot.right();
		assertEquals(Direction.EAST, robot.getFacing());
		robot.right();
		assertEquals(Direction.SOUTH, robot.getFacing());
		robot.right();
		assertEquals(Direction.WEST, robot.getFacing());
		robot.right();
		assertEquals(Direction.NORTH, robot.getFacing());
			
	}
	
	@Test
	void testMove() {
		//test move before being placed on table
		robot.move();
		assertEquals(-1, robot.getXPos());
		assertEquals(-1, robot.getYPos());
		
		robot.place(2,2,Direction.NORTH);
		robot.move();
		assertEquals(2, robot.getXPos());
		assertEquals(3, robot.getYPos());
		robot.left();
		robot.move();
		assertEquals(1, robot.getXPos());
		assertEquals(3, robot.getYPos());
		robot.left();
		robot.move();
		assertEquals(1, robot.getXPos());
		assertEquals(2, robot.getYPos());
		robot.left();
		robot.move();
		assertEquals(2, robot.getXPos());
		assertEquals(2, robot.getYPos());
	}
}

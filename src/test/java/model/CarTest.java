package model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by timothysum on 12/07/2016.
 */
public class CarTest {
	private Car car;

	@Before
	public void setUp() throws Exception {
		car = new Car(5, 5);
	}

	@Test
	public void testTurnLeft() throws Exception {
		car.turnLeft();

		assertEquals(car.getStartingXCoor(), 5);
		assertEquals(car.getStartingYCoor(), 5);
		assertEquals(Car.Orientation.EAST, car.getOrientation());
		assertEquals(car.getxCoor(), 5);
		assertEquals(car.getyCoor(), 5);
	}

	@Test
	public void testTurnRight() throws Exception {
		car.turnRight();

		assertEquals(car.getStartingXCoor(), 5);
		assertEquals(car.getStartingYCoor(), 5);
		assertEquals(Car.Orientation.WEST, car.getOrientation());
		assertEquals(car.getxCoor(), 5);
		assertEquals(car.getyCoor(), 5);
	}

	@Test
	public void testMoveForward() throws Exception {
		car.moveForward();

		assertEquals(car.getStartingXCoor(), 5);
		assertEquals(car.getStartingYCoor(), 5);
		assertEquals(Car.Orientation.NORTH, car.getOrientation());
		assertEquals(car.getxCoor(), 5);
		assertEquals(car.getyCoor(), 6);
	}

	@Test(expected = IllegalStateException.class)
	public void testInitCarWithInvalidPositions() throws Exception {
		Car car = new Car(16, 16);
	}
}
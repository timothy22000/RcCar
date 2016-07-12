package model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.google.common.base.Preconditions.checkNotNull;

public class Car {
	private static final Logger logger = LoggerFactory.getLogger(Car.class);

	private final int startingXCoor;
	private final int startingYCoor;

	private int xCoor;
	private int yCoor;
	private Orientation orientation;

	enum Orientation {
		NORTH,
		SOUTH,
		EAST,
		WEST
	}

	public Car(int xCoor, int yCoor) {
		this.startingXCoor = checkNotNull(xCoor);
		this.startingYCoor = checkNotNull(yCoor);
		this.xCoor = checkNotNull(xCoor);
		this.yCoor = checkNotNull(yCoor);
		this.orientation = Orientation.NORTH;
		checkPositionValidity();
	}

	//Updates the orientation of the car
	public void turnLeft() {
		if(getOrientation() == Orientation.NORTH) {
			setOrientation(Orientation.EAST);
		} else if (getOrientation() == Orientation.EAST) {
			setOrientation(Orientation.SOUTH);
		} else if (getOrientation() == Orientation.SOUTH) {
			setOrientation(Orientation.WEST);
		} else if (getOrientation() == Orientation.WEST) {
			setOrientation(Orientation.NORTH);
		} else {
			logger.error("Unable to turn the orientation of the car to the left");
		}

	}

	//Updates the orientation of the car
	public void turnRight() {
		if(getOrientation() == Orientation.NORTH) {
			setOrientation(Orientation.WEST);
		} else if (getOrientation() == Orientation.EAST) {
			setOrientation(Orientation.NORTH);
		} else if (getOrientation() == Orientation.SOUTH) {
			setOrientation(Orientation.EAST);
		} else if (getOrientation() == Orientation.WEST) {
			setOrientation(Orientation.SOUTH);
		} else {
			logger.error("Unable to turn the orientation of the car to the right");
		}
	}

	public void moveForward() {
		if(getOrientation() == Orientation.NORTH) {
			yCoor++;
		} else if (getOrientation() == Orientation.EAST) {
			xCoor--;
		} else if (getOrientation() == Orientation.SOUTH) {
			yCoor--;
		} else if (getOrientation() == Orientation.WEST) {
			xCoor++;
		} else {
			logger.error("Unable to move the car forward");
		}
		checkPositionValidity();
	}

	//Always perform after an action and the x-y are constrained to be inside a 15x15 grid (smallest valid x and y starts from 1)
	private void checkPositionValidity() throws IllegalStateException {
		if(getxCoor() < 1 || getxCoor() > 15) {
			logger.error("You car has moved beyond the boundary of the 15x15 grid. " + "X-Coordinates: " + getxCoor());
			throw new IllegalStateException();
		} else if( getyCoor() < 1 || getyCoor() > 15) {
			logger.error("You car has moved beyond the boundary of the 15x15 grid. " + "Y-Coordinates: " + getyCoor());
			throw new IllegalStateException();
		}
	}

	public int getStartingYCoor() {
		return startingYCoor;
	}

	public int getStartingXCoor() {
		return startingXCoor;
	}

	public Orientation getOrientation() {
		return orientation;
	}

	public void setOrientation(Orientation orientation) {
		this.orientation = orientation;
	}

	public int getxCoor() {
		return xCoor;
	}

	public int getyCoor() {
		return yCoor;
	}
}

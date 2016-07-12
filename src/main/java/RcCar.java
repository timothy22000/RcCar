import model.Car;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedList;
import java.util.Queue;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RcCar {
	private static final Logger logger = LoggerFactory.getLogger(RcCar.class);

	/** Cars are placed on a 15 by 15 grid at particular co-ordinates heading north, and the simple
	    commands Left, right and forward are transmitted to them. The commands must be executed and
	    the final position calculated.

		The following examples should be used to demonstrate your code:

		For the input "5,5:RFLFRFLF"

		We should get the position "7,7"

		For the input "6,6:FFLFFLFFLFF"

		We should get the position "6,6"

		For the input "5,5:FLFLFFRFFF"

		We should get the position "4,1" (Should be 1,4 based on the derived movement algorithm, add justification to README")

	 **/

	public static void main(String[] args) throws InterruptedException {

		if (args.length < 1) {
			logger.error("Usage: RcCar <input>");
			System.exit(1);
		};

		if(args[0] != null && !args[0].isEmpty()) {
			startProcessing(args[0]);
		} else {
			throw new IllegalArgumentException();
		}
	}

	public static Car startProcessing(String movementInput) throws InterruptedException {

		Pattern inputPattern = Pattern.compile("^([0-9]|1[0-15]),([0-9]|1[0-15]):(\\w+)$");

		Matcher inputMatcher = inputPattern.matcher(movementInput);

		if(inputMatcher.matches()) {
			Car car = new Car(Integer.parseInt(inputMatcher.group(1)), Integer.parseInt(inputMatcher.group(2)));
			logger.info("Car Starting Position: " + car.getxCoor() + "," + car.getyCoor());

			String movementInputs = inputMatcher.group(3);
			movementInputs.toUpperCase();

			logger.info(movementInputs);

			Queue<Character> commandQueue = putInputsIntoAQueue(movementInputs);
			processMovements(car, commandQueue);

			logger.info("Car Finish Position: " + car.getxCoor() + "," + car.getyCoor());

			//To allow grid to be properly generated
			Thread.sleep(500);

			String[][] grid = generateGrid(car);

			//Using transpose and reverseColumn to rotate anticlockwise by 90 degrees
			String[][] gridInProperFormForVisualization = rotate90DegreeAntiClockwise(grid);

			printGrid(gridInProperFormForVisualization);

			return car;

		} else {
			logger.error("Input format is wrong. Please check your starting position to ensure it is within the " +
					"range of a 15x15 grid. Movement inputs can only be R, L or F. Sample valid input: '5,5:FLFLFFRFFF'");
			throw new IllegalArgumentException();
		}
	}

	//15x15 grid showing start and end position of car. "S" is starting position, "F" is finish position and "X" is empty.
	private static String[][] generateGrid(Car car) {
		String[][] grid = new String[14][14];

		for(int i = 0; i < grid.length; i++) {

			for(int j = 0; j < grid[i].length; j++) {
				grid[i][j] = "x";
			}
		}

		//Minus one because the coordinate system in Car is [1,15].
		grid[car.getStartingXCoor() - 1][car.getStartingYCoor() - 1] = "S";
		grid[car.getxCoor() - 1][car.getyCoor() - 1] = "F";

		return grid;
	}

	private static String[][] rotate90DegreeAntiClockwise(String[][] grid) {
		return transpose(reverseColumn(grid));
	}

	private static String[][] transpose(String[][] grid) {
		String[][] newGrid = new String[grid[0].length][grid.length];

		for(int i = 0; i < grid.length; i++) {
			for(int j = 0; j < grid[i].length; j++) {
				newGrid[j][i] = grid[i][j];
			}
		}

		return newGrid;
	}

	private static String[][] reverseColumn(String[][] grid) {
		String[][] newGrid = new String[grid.length][grid[0].length];

		for(int i = 0; i < grid.length; i++) {
			for(int j = 0; j < grid[i].length; j++) {
				newGrid[i][grid[i].length - 1 - j] = grid[i][j];
			}
		}

		return newGrid;
	}

	private static String[][] rotate90DegreeClockwise(String[][] grid) {
		String[][] newGrid = new String[grid[0].length][grid.length];

		for(int i = 0; i < grid.length; i++) {
			for(int j = 0; j < grid[i].length; j++) {
				newGrid[j][grid[i].length - 1 - i] = grid[i][j];
			}
		}

		return newGrid;
	}

	private static void printGrid(String[][] grid) {

		for(int i = 0; i < grid.length; i++) {
			System.out.println();

			for(int j = 0; j < grid[i].length; j++) {
				System.out.print(grid[i][j] + " ");
			}
		}

	}

	//Process left, right and forward movements. Unknown inputs will be ignored.
	private static void processMovements(Car car, Queue<Character> commandQueue) {
		while(!commandQueue.isEmpty()) {
			char command = commandQueue.remove();
			logger.info("Current command: " + String.valueOf(command));

			if(command == 'L') {
				car.turnLeft();
			} else if(command == 'R') {
				car.turnRight();
			} else if (command == 'F'){
				car.moveForward();
			}

			logger.info("Car New Position: " + car.getxCoor() + "," + car.getyCoor());

		}
	}

	private static Queue<Character> putInputsIntoAQueue(String movementInputs) {
		LinkedList<Character> commandQueue = new LinkedList<>();

		if(!movementInputs.isEmpty() && movementInputs != null) {
			for(int i = 0; i < movementInputs.length(); i++) {
				char command = movementInputs.charAt(i);
				commandQueue.add(command);
			}
		}

		return commandQueue;
	}
}

import model.Car;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class RcCarTest {
	private RcCar rcCar;

	@Before
	public void setUp() throws Exception {
		rcCar = new RcCar();
	}

	@Test
	public void testExampleInput1() throws Exception {
		/**
		 *  For the input "5,5:RFLFRFLF"
		 * We should get the position "7,7"
		 */
		String testInput = "5,5:RFLFRFLF";
		Car car = rcCar.startProcessing(testInput);

		assertNotNull(car);
		assertEquals(car.getStartingXCoor(), 5);
		assertEquals(car.getStartingYCoor(), 5);
		assertEquals(car.getxCoor(), 7);
		assertEquals(car.getyCoor(), 7);
	}

	@Test
	public void testExampleInput2() throws Exception {

		/**
		 * For the input "6,6:FFLFFLFFLFF"
		 * We should get the position "6,6"
		 */

		String testInput = "6,6:FFLFFLFFLFF";
		Car car = rcCar.startProcessing(testInput);

		assertNotNull(car);
		assertEquals(car.getStartingXCoor(), 6);
		assertEquals(car.getStartingYCoor(), 6);
		assertEquals(car.getxCoor(), 6);
		assertEquals(car.getyCoor(), 6);
	}

	@Test
	public void testExampleInput3() throws Exception {

		/**
		 *  For the input "5,5:FLFLFFRFFF"
		 *  We should get the position "1,4"
		 */


		String testInput = "5,5:FLFLFFRFFF";
		Car car = rcCar.startProcessing(testInput);

		assertNotNull(car);
		assertEquals(car.getStartingXCoor(), 5);
		assertEquals(car.getStartingYCoor(), 5);
		assertEquals(car.getxCoor(), 1);
		assertEquals(car.getyCoor(), 4);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testInvalidStartPosition() throws Exception {
		String testInput = "17,17:FLFLFFRFFF";

		Car car = rcCar.startProcessing(testInput);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testInvalidMovementInput() throws Exception {
		String testInput = "6,6:~%134";

		Car car = rcCar.startProcessing(testInput);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testEmptyString() throws Exception {
		String testInput = "";

		Car car = rcCar.startProcessing(testInput);
	}

	@Test
	public void testUnknownMovementInputIgnored() throws Exception {
		String testInput = "6,6:FFLFFLyFFLFFABC";

		Car car = rcCar.startProcessing(testInput);

		assertNotNull(car);
		assertEquals(car.getStartingXCoor(), 6);
		assertEquals(car.getStartingYCoor(), 6);
		assertEquals(car.getxCoor(), 6);
		assertEquals(car.getyCoor(), 6);
	}
}
/**
 * 
 */
package de.lexasoft.common.math;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

/**
 * @author nierax
 *
 */
class BezierTest {

	private List<Point> controlPoints;

	private static List<Point> createLoP(Point... points) {
		List<Point> lop = new ArrayList<>();
		for (Point point : points) {
			lop.add(point);
		}
		return lop;
	}

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		controlPoints = createLoP(Point.of(0, 0), Point.of(5, 0), Point.of(5, 10), Point.of(10, 10));
	}

	private static Stream<Arguments> testBezier() {
		return Stream.of(Arguments.of(createLoP(Point.of(0, 0), Point.of(5, 0), Point.of(5, 10), Point.of(10, 10)),
		    new double[] { 0.0, 0.1, 0.2, 0.3, 0.4 }, createLoP(Point.of(0, 0), Point.of(1.36, 0.28), Point.of(2.48, 1.04),
		        Point.of(3.42, 2.16), Point.of(4.24, 3.52))));
	}

	/**
	 * Test method for {@link de.lexasoft.common.math.Bezier#bezier(double)}.
	 */
	@ParameterizedTest
	@MethodSource
	void testBezier(List<Point> control, double[] ts, List<Point> expected) {
		Bezier cut = Bezier.of(control);
		int i = 0;
		for (double t : ts) {
			Point result = cut.bezier(t);
			assertEquals(expected.get(i).x(), result.x(), 0.001);
			assertEquals(expected.get(i++).y(), result.y(), 0.001);
		}
	}

	@ParameterizedTest
	@ValueSource(doubles = { 1.1, -0.1, 2, -100 })
	void testBezierNotInRange(double t) {
		Bezier cut = Bezier.of(createLoP(Point.of(0, 0), Point.of(5, 0), Point.of(5, 10), Point.of(10, 10)));
		assertThrows(IllegalArgumentException.class, () -> {
			cut.bezier(t);
		});
	}

	/**
	 * 
	 */
	@ParameterizedTest
	@ValueSource(doubles = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 })
	void testTFromX(double x) {
		Bezier cut = Bezier.of(controlPoints);
		double t = cut.tFromX(x);
		assertNotNull(t);
//		assertTrue((t >= 0));
		assertEquals(x, cut.bezier(t).x(), 0.0001);
	}

	@ParameterizedTest
	@ValueSource(doubles = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 0.2, 0.05, 5.3, 2.1 })
	void testTFromX2(double x) {
		Bezier cut = Bezier.of(controlPoints);
		double t = cut.tFromX2(x);
		assertNotNull(t);
		assertEquals(x, cut.bezier(t).x(), 0.0001);
	}
}

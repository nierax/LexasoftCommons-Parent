/**
 * 
 */
package de.lexasoft.common.math;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

/**
 * @author admin
 *
 */
class BezierTest {

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
	}

	private static Stream<Arguments> testBezier() {
		return Stream.of(Arguments.of(createLoP(Point.of(0, 0), Point.of(5, 0), Point.of(5, 10), Point.of(10, 10)),
		    new double[] { 0.1, 0.2, 0.3, 0.4 },
		    createLoP(Point.of(1.36, 0.28), Point.of(2.48, 1.04), Point.of(3.42, 2.16), Point.of(4.24, 3.52))));
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

}

/**
 * 
 */
package de.lexasoft.common.math;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

/**
 * Simple test to check the {@link BezierCurvePlotter} class.
 * <p>
 * Images with curves are stored in junit-tmp directory.
 * 
 * @author nierax
 *
 */
class BezierCurvePlotterTest {

	private static List<Point> create4PointList() {
		List<Point> list = new ArrayList<>();
		list.add(Point.of(0, 0));
		list.add(Point.of(50, 0));
		list.add(Point.of(50, 100));
		list.add(Point.of(100, 100));
		return list;
	}

	private static List<Point> create3PointList() {
		List<Point> list = new ArrayList<>();
		list.add(Point.of(0, 0));
		list.add(Point.of(50, 75));
		list.add(Point.of(100, 100));
		return list;
	}

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
	}

	private static Stream<Arguments> testPlotCurve() {
		return Stream.of(Arguments.of(create4PointList(), "junit-tmp/4-point-list.png"),
		    Arguments.of(create3PointList(), "junit-tmp/3-point-list.png"));
	}

	@ParameterizedTest
	@MethodSource
	void testPlotCurve(List<Point> list, String filename) throws IOException {
		// First check, whether file exists and delete it.
		File file2Write = new File(filename);
		if (file2Write.exists()) {
			file2Write.delete();
		}
		BezierCurvePlotter cut = BezierCurvePlotter.of(Bezier.of(list), 500, 500);
		assertNotNull(cut);
		cut.plotCurve();
		cut.saveImageToFileSystem(filename);
		assertTrue(file2Write.exists());
	}

}

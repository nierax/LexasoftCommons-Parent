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

import org.junit.jupiter.api.BeforeAll;
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

	private final static String DIRECTORY = "junit-tmp";

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

	private static List<Point> createList(Point... points) {
		List<Point> list = new ArrayList<>();
		for (Point point : points) {
			list.add(point);
		}
		return list;
	}

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeAll
	static void initialize() throws Exception {
		File dir = new File(DIRECTORY);
		if (!dir.exists()) {
			dir.mkdir();
		}
	}

	private static Stream<Arguments> testPlotCurve() {
		return Stream.of(Arguments.of(create4PointList(), DIRECTORY + "/4-point-list.png"),
		    Arguments.of(create3PointList(), DIRECTORY + "/3-point-list.png"),
		    Arguments.of(createList(Point.of(0, 0), Point.of(75, 75), Point.of(100, 100)),
		        DIRECTORY + "/3-point-75-75.png"),
		    Arguments.of(createList(Point.of(0, 0), Point.of(75, 100), Point.of(100, 100)),
		        DIRECTORY + "/3-point-75-100.png"),
		    Arguments.of(createList(Point.of(0, 0), Point.of(66, 100), Point.of(100, 100)),
		        DIRECTORY + "/3-point-66-100.png"),
		    Arguments.of(createList(Point.of(0, 0), Point.of(66, 120), Point.of(100, 100)),
		        DIRECTORY + "/3-point-66-120.png"),
		    Arguments.of(createList(Point.of(0, 0), Point.of(50, 100), Point.of(100, 100)),
		        DIRECTORY + "/3-point-50-100.png"),
		    Arguments.of(createList(Point.of(0, 0), Point.of(50, 0), Point.of(100, 100)), DIRECTORY + "/3-point-50-0.png"));
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

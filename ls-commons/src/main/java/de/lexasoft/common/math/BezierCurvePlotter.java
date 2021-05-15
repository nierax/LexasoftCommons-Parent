/**
 * 
 */
package de.lexasoft.common.math;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

/**
 * Draws a Bezier curve in any image.
 * <p>
 * Can use either a given image or creates a new one with the given width and
 * height.
 * 
 * @author nierax
 *
 */
public class BezierCurvePlotter {

	private Bezier bezier;
	private BufferedImage image;
	private Graphics graphics;
	private Point lastPoint;

	/**
	 * 
	 */
	protected BezierCurvePlotter(Bezier bezier, BufferedImage image, Graphics graphics) {
		this.bezier = bezier;
		this.image = image;
		this.graphics = graphics;
	}

	private int normToDim(double value, double factor) {
		return (int) (value * factor);
	}

	private Point map2AWTPoint(de.lexasoft.common.math.Point p, de.lexasoft.common.math.Point factor) {
		Point awtPoint = new Point();
		// Centering in image
		awtPoint.x = (int) (normToDim(p.x(), factor.x()) + (image.getWidth() * 0.1));
		awtPoint.y = (int) (normToDim(p.y(), factor.y()) + (image.getHeight() * 0.1));
		// Horizontally mirrored
		awtPoint.y = image.getHeight() - awtPoint.y;
		return awtPoint;
	}

	private List<de.lexasoft.common.math.Point> calculateCurve() {
		List<de.lexasoft.common.math.Point> curve = new ArrayList<>();
		for (double t = 0; t <= 1; t += 0.001) {
			curve.add(bezier.bezier(t));
		}
		return curve;
	}

	private de.lexasoft.common.math.Point findMin(List<de.lexasoft.common.math.Point> list) {
		double minX = Double.MAX_VALUE;
		double minY = Double.MAX_VALUE;
		for (de.lexasoft.common.math.Point point : list) {
			minX = Math.min(point.x(), minX);
			minY = Math.min(point.y(), minY);
		}
		return de.lexasoft.common.math.Point.of(minX, minY);
	}

	private de.lexasoft.common.math.Point findMax(List<de.lexasoft.common.math.Point> list) {
		double maxX = Double.MIN_VALUE;
		double maxY = Double.MIN_VALUE;
		for (de.lexasoft.common.math.Point point : list) {
			maxX = Math.max(point.x(), maxX);
			maxY = Math.max(point.x(), maxY);
		}
		return de.lexasoft.common.math.Point.of(maxX, maxY);
	}

	private de.lexasoft.common.math.Point calcFactors(List<de.lexasoft.common.math.Point> curve) {
		de.lexasoft.common.math.Point max = findMax(curve);
		de.lexasoft.common.math.Point min = findMin(curve);
		double dx = image.getWidth() / Math.abs(max.x() - min.x()) * 0.8;
		double dy = image.getHeight() / Math.abs(max.y() - min.y()) * 0.8;
		return de.lexasoft.common.math.Point.of(dx, dy);
	}

	private void drawLine(Point from, Point to) {
		graphics.drawLine(from.x, from.y, to.x, to.y);
	}

	/**
	 * Plots the curve into the image, given or created under the creation of the
	 * object.
	 * 
	 * @return Image with the curve plotted.
	 */
	public BufferedImage plotCurve() {
		List<de.lexasoft.common.math.Point> curve = calculateCurve();
		// First calculate the curve to be able to center it in the image.
		de.lexasoft.common.math.Point factors = calcFactors(curve);
		for (de.lexasoft.common.math.Point point : curve) {
			Point p2d = map2AWTPoint(point, factors);
			drawLine((lastPoint == null ? p2d : lastPoint), p2d);
			lastPoint = p2d;
		}
		return image;
	}

	/**
	 * Saves the image, formerly plotted to the file system with the given file
	 * name.
	 * 
	 * @param fileName File name to write the image to.
	 * @throws IOException
	 */
	public void saveImageToFileSystem(String fileName) throws IOException {
		File file = new File(fileName);
		String filetype = fileName.substring(fileName.lastIndexOf(".") + 1);
		ImageIO.write(image, filetype, file);
	}

	/**
	 * Creates a {@link BezierCurvePlotter} with the given Bezier and the given
	 * image.
	 * 
	 * @param bezier The {@link Bezier} to calculate the curve from.
	 * @param image  The image to write the curve to.
	 * @return A {@link BezierCurvePlotter} object ready to use.
	 */
	public static BezierCurvePlotter of(Bezier bezier, BufferedImage image) {
		return new BezierCurvePlotter(bezier, image, image.getGraphics());
	}

	/**
	 * Creates a {@link BezierCurvePlotter} with the given Bezier. It uses a new
	 * image with the given dimensions width and height
	 * 
	 * @param bezier The {@link Bezier} to calculate the curve from.
	 * @param width  The width of the image to plot the curve in.
	 * @param height The height of the image to plot the curve in.
	 * @return A {@link BezierCurvePlotter} object ready to use.
	 */
	public static BezierCurvePlotter of(Bezier bezier, int width, int height) {
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		Graphics2D g2d = image.createGraphics();
		g2d.setBackground(Color.WHITE);
		g2d.clearRect(0, 0, width, height);
		g2d.setColor(Color.BLACK);
		return new BezierCurvePlotter(bezier, image, g2d);
	}

}

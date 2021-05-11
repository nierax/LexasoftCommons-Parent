/**
 * 
 */
package de.lexasoft.common.math;

/**
 * This class represents a point with two coordinates x and y.
 * 
 * @author nierax
 *
 */
public class Point {
	/**
	 * x position
	 */
	private double x;
	/**
	 * y position
	 */
	private double y;

	protected Point(double x, double y) {
		this.x = x;
		this.y = y;
	}

	public double x() {
		return x;
	}

	public void x(double x) {
		this.x = x;
	}

	public double y() {
		return y;
	}

	public void y(double y) {
		this.y = y;
	}

	public static Point of(double x, double y) {
		return new Point(x, y);
	}

	@Override
	public String toString() {
		return String.format("[px=%s,py=%s]", x, y);
	}
}

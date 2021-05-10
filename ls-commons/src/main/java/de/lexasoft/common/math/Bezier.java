/**
 * 
 */
package de.lexasoft.common.math;

import java.util.List;

/**
 * Supports calculation of a Bezier curve
 * <p>
 * https://javascript.info/bezier-curve<br/>
 * https://de.wikipedia.org/wiki/B%C3%A9zierkurve<br/>
 * https://www.tutorials.de/threads/java-swing-bezier-curves.363073/
 * 
 * @author nierax
 *
 */
public class Bezier {

	private List<Point> controlPoints;

	protected Bezier(List<Point> controlPoints) {
		this.controlPoints = controlPoints;
	}

	private double bernstein(int n, int k, double t) {
		return choose(n, k) * Math.pow(1 - t, n - k) * Math.pow(t, k);
	}

	private long choose(int n, int k) {
		return fact(n) / (fact(k) * fact(n - k));
	}

	private long fact(int n) {
		if (n > 1) {
			return fact(n - 1) * n;
		}

		return 1;
	}

	public Point bezier(double t) {
		double x = 0;
		double y = 0;
		for (int i = 0; i < controlPoints.size(); i++) {
			Point p = controlPoints.get(i);
			x += p.x() * bernstein(controlPoints.size() - 1, i, t);
			y += p.y() * bernstein(controlPoints.size() - 1, i, t);
		}
		return Point.of(x, y);
	}

	public static Bezier of(List<Point> controlPoints) {
		return new Bezier(controlPoints);
	}
}

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
		if ((t > 1) || t < 0) {
			throw new IllegalArgumentException(String.format("Parameter t was %s, but must be between 0 and 1.", t));
		}
		double x = 0;
		double y = 0;
		for (int i = 0; i < controlPoints.size(); i++) {
			Point p = controlPoints.get(i);
			x += p.x() * bernstein(controlPoints.size() - 1, i, t);
			y += p.y() * bernstein(controlPoints.size() - 1, i, t);
		}
		return Point.of(x, y);
	}

	public double tFromX(double x) {
		double p = 5;
		double p_max = 100;
		double t = 1;
		double d = 1;
		for (int n = 0; n < Math.min(p, p_max); n++) {
			d = d * bezier(t).x();
			if (p < p_max) {
				p += Math.min(0.02 / Math.pow(d, 2), 1);
			}
			t -= Math.abs(bezier(t).x() - x) / d;
		}
		return t;
	}

	private double findMin(double x, double from, double to, double dt) {
		if (Math.abs(dt) < 1e-6) {
			return from;
		}
		double d1 = 0;
		double d2 = Double.NaN;
		double t = 0;
		double lastT = from;
		for (double ti = from; ti <= to; ti += dt) {
			d1 = Math.abs(bezier(ti).x() - x);
			if (d1 < 1e-4) {
				return ti;
			}
			if (!Double.isNaN(d2) && (d1 > d2)) {
				t = findMin(x, lastT, to, dt *= -0.1);
			}
			d2 = d1;
			lastT = ti;
		}
		return t;
	}

	/**
	 * 
	 * @param x
	 * @return
	 */
	public double tFromX2(double x) {
		return findMin(x, 0, 1.0, 0.1);
	}

	public static Bezier of(List<Point> controlPoints) {
		return new Bezier(controlPoints);
	}
}

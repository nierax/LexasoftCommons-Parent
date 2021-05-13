/**
 * 
 */
package de.lexasoft.common.math;

import java.util.List;

/**
 * Supports calculation of a Bezier curve
 * <p>
 * Read more about Bezier curves:
 * <ul>
 * <li>https://en.wikipedia.org/wiki/B%C3%A9zier_curve</li>
 * <li>https://javascript.info/bezier-curve</li
 * <li>https://de.wikipedia.org/wiki/B%C3%A9zierkurve</li>
 * <li>https://www.tutorials.de/threads/java-swing-bezier-curves.363073/</li>
 * </ul>
 * 
 * @author nierax
 *
 */
public class Bezier {

	/**
	 * The minimum iteration step to find t from x
	 */
	public static double THRESHOLD = 1e-6;

	/**
	 * The default accuracy (maximal difference) between the found x from the x,
	 * expected.
	 */
	public static double DEF_ACCURACY = 1e-6;

	/**
	 * Default iteration step to start from searching t for x.
	 */
	public static double DEF_DT = 0.1;

	@SuppressWarnings("serial")
	class NotFoundInBezierCurveException extends MathException {

		public NotFoundInBezierCurveException(String message) {
			super(message);
		}

	}

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

	/**
	 * Calculates a bezier point from the given t, using the control points, given
	 * under creation of this object.
	 * 
	 * @param t The t parameter to calculate from
	 * @return The point with x and y on the Bezier curve.
	 */
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

	/**
	 * Experimental
	 * <p>
	 * Tries to get t from x. Not always accurate, but an approximation and
	 * relatively efficiently.
	 * <p>
	 * Always gets the first value for t. If there is more than one t for x, this
	 * method might fail.
	 * 
	 * @param x
	 * @return
	 */
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

	private double findMin(double x, double from, double dt, double accuracy) throws MathException {
		if (Math.abs(dt) < THRESHOLD) {
			return from;
		}
		double d1 = 0;
		double d2 = Double.NaN;
		double lastT = from;
		double ti = from;
		while ((ti >= 0) && (ti <= 1)) {
			d1 = Math.abs(bezier(ti).x() - x);
			if (d1 < accuracy) {
				return ti;
			}
			if (!Double.isNaN(d2) && (d1 > d2)) {
				return findMin(x, lastT, dt *= -0.1, accuracy);
			}
			d2 = d1;
			lastT = ti;
			ti = ti + dt;
		}
		throw new NotFoundInBezierCurveException(String.format("Value %s not found for x in %s.", x, controlPoints));
	}

	/**
	 * Experimental
	 * <p>
	 * Gets the first t for a given x.
	 * <p>
	 * Only works correctly, if there is exactly one t for x. If no t is found, the
	 * method throws an {@link NotFoundInBezierCurveException}.
	 * <p>
	 * If there are more than one t for x, the first x is delivered.
	 * <p>
	 * The method searches iteratively after the best approximation and, thus it is
	 * not very efficient.
	 * 
	 * @param x        x-value for which the t-value is wanted.
	 * @param dt       Iteration to start from. Default: 0.1. If You experience
	 *                 problems try to decrease in potency of 10 (f.ex. 0.01,
	 *                 0.001). Calculation will become more accurate, but less
	 *                 efficient.
	 * @param accuracy Tolerance for the difference of the result to the expected
	 *                 value. Default: 1e-6. Increasing the tolerance reduces the
	 *                 accuracy, but increases the efficiency.
	 * @return First found t for x.
	 * @throws MathException If no t was found for x.
	 */
	public double tFromX2(double x, double dt, double accuracy) throws MathException {
		// First check, whether the border values match.
		for (double t = 0; t <= 1; t++) {
			if (Math.abs(bezier(t).x() - x) <= THRESHOLD) {
				return t;
			}
		}
		// Then search on the entire bezier curve.
		return findMin(x, 0, dt, accuracy);
	}

	/**
	 * Experimental
	 * <p>
	 * Gets the first t for a given x.
	 * <p>
	 * Only works correctly, if there is exactly one t for x. If no t is found, the
	 * method throws an {@link NotFoundInBezierCurveException}.
	 * <p>
	 * If there are more than one t for x, the first x is delivered.
	 * <p>
	 * The method searches iteratively after the best approximation and, thus it is
	 * not very efficient.
	 * 
	 * @param x  x-value for which the t-value is wanted.
	 * @param dt Iteration to start from. Default: 0.1. If You experience problems
	 *           try to decrease in potency of 10 (f.ex. 0.01, 0.001). Calculation
	 *           will become more accurate, but less efficient.
	 * @return First found t for x.
	 * @throws MathException If no t was found for x.
	 */
	public double tFromX2(double x, double dt) throws MathException {
		return tFromX2(x, dt, DEF_ACCURACY);
	}

	/**
	 * Experimental
	 * <p>
	 * Gets the first t for a given x.
	 * <p>
	 * Only works correctly, if there is exactly one t for x. If no t is found, the
	 * method throws an {@link NotFoundInBezierCurveException}.
	 * <p>
	 * If there are more than one t for x, the first x is delivered.
	 * <p>
	 * The method searches iteratively after the best approximation and, thus it is
	 * not very efficient.
	 * 
	 * @param x x-value for which the t-value is wanted.
	 * @return First found t for x.
	 * @throws MathException If no t was found for x.
	 */
	public double tFromX2(double x) throws MathException {
		return tFromX2(x, DEF_DT, DEF_ACCURACY);
	}

	public static Bezier of(List<Point> controlPoints) {
		return new Bezier(controlPoints);
	}
}

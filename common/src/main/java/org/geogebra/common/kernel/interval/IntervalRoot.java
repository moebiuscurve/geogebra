package org.geogebra.common.kernel.interval;

import static org.geogebra.common.kernel.interval.IntervalConstants.undefined;
import static org.geogebra.common.kernel.interval.IntervalOperands.computeInverted;
import static org.geogebra.common.kernel.interval.IntervalOperands.pow;

import org.geogebra.common.util.DoubleUtil;

public class IntervalRoot {

	/**
	 * Computes the nth root of the interval
	 * if other (=n) is a singleton
	 * @param other interval
	 * @return nth root of the interval.
	 */
	Interval compute(Interval interval, Interval other) {
		if (!other.isSingleton()) {
			interval.setUndefined();
			return interval;
		}

		double power = other.getLow();
		return compute(interval, power);
	}

	/**
	 * Computes x^(1/n)
	 * @param n the root
	 * @return nth root of the interval.
	 */
	Interval compute(Interval interval, double n) {
		if (interval.isUndefined()) {
			return undefined();
		}

		if (interval.isInverted()) {
			Interval result1 = compute(interval.extractLow(), n);
			Interval result2 = compute(interval.extractHigh(), n);
			return computeInverted(result1, result2);
		}

		double power = 1 / n;
		if (isPositiveOdd(n)) {
			return new Interval(oddFractionPower(interval.getLow(), power),
					oddFractionPower(interval.getHigh(), power));
		}
		return pow(interval, power).round();
	}

	private double oddFractionPower(double x, double power) {
		return Math.max(IntervalConstants.PRECISION,
				Math.signum(x) * Math.pow(Math.abs(x), power));
	}

	private boolean isPositiveOdd(double n) {
		return n > 0 && DoubleUtil.isInteger(n) && (int) n % 2 != 0;
	}
}

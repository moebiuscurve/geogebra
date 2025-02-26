package org.geogebra.common.kernel.interval;

import static org.geogebra.common.kernel.interval.IntervalConstants.undefined;
import static org.geogebra.common.kernel.interval.IntervalConstants.zero;
import static org.geogebra.common.kernel.interval.IntervalOperands.nthRoot;
import static org.geogebra.common.kernel.interval.IntervalOperands.pow;
import static org.geogebra.common.kernel.interval.IntervalOperands.sin;
import static org.geogebra.common.kernel.interval.IntervalOperands.sqrt;
import static org.geogebra.common.kernel.interval.IntervalTest.interval;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Ignore;
import org.junit.Test;

public class IntervalRootTest {

	@Test
	public void testSqrtPositive() {
		assertEquals(interval(2, 3), 
				sqrt(interval(4, 9)));
	}

	@Test
	public void testSqrtMixed() {
		assertEquals(interval(0, 3), 
				sqrt(interval(-4, 9)));
	}

	@Test
	public void testSqrtNegative() {
		assertEquals(undefined(), sqrt(interval(-9, -4)));
		assertEquals(zero(), sqrt(interval(0, 0)));
		assertEquals(interval(0, 1), sqrt(interval(0, 1)));
	}

	@Ignore
	@Test
	public void testNthRootInNegativeInterval() {
		assertEquals(interval(-2, 2), nthRoot(interval(-8, 8), 3));
		assertEquals(interval(0.5, Double.POSITIVE_INFINITY),
				nthRoot(interval(-8, 8), -3));
	}

	@Ignore
	@Test
	public void testNthRoot() {
		assertEquals(undefined(), nthRoot(interval(-27, -8), -3));
		assertEquals(undefined(), nthRoot(interval(-27, -8), 2));
		assertEquals(interval(0, 3), nthRoot(interval(-4, 9), 2));
		assertEquals(interval(-3, 2), nthRoot(interval(-27, 8), 3));
		assertEquals(interval(2, 3), nthRoot(interval(4, 9), 2));
		assertEquals(interval(2, 3), nthRoot(interval(8, 27), 3));
		assertEquals(interval(2, 2), nthRoot(interval(8, 8), 3));
	}

	@Ignore
	@Test
	public void testNthRootWithInterval() {
		assertEquals(interval(-3, -2),
				nthRoot(interval(-27, -8),	interval(3, 3)));
		assertEquals(undefined(), nthRoot(interval(-27, -8), interval(4, 3)));

	}

	@Test
	public void testSqrtSinUndef() {
		assertTrue(sqrt(sin(interval(4, 5))).isUndefined());
	}

	@Test
	public void testPowerOnPositiveFraction() {
		assertEquals(sqrt(interval(1, 2)), pow(interval(1, 2), 0.5));
	}
}
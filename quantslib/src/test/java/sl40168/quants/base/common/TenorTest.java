package sl40168.quants.base.common;

import static org.junit.Assert.*;

import org.junit.Test;

public class TenorTest {

	@Test
	public void test() {
		Tenor ofDays = Tenor.ofDays(0);
		assertEquals(0, ofDays.getDays());
		assertEquals(0, ofDays.getMonths());
		assertEquals(0, ofDays.getYears());
		assertFalse(ofDays.isYear());
		assertFalse(ofDays.isMonth());
		assertFalse(ofDays.isDay());
		assertEquals("0D", ofDays.getStringValue());
		
		ofDays.withDays(10);
		assertEquals(10, ofDays.getDays());
		assertEquals(0, ofDays.getMonths());
		assertEquals(0, ofDays.getYears());
		assertFalse(ofDays.isYear());
		assertFalse(ofDays.isMonth());
		assertTrue(ofDays.isDay());
		assertEquals("10D", ofDays.getStringValue());
		
		ofDays.withMonths(3);
		assertEquals(10, ofDays.getDays());
		assertEquals(3, ofDays.getMonths());
		assertEquals(0, ofDays.getYears());
		assertFalse(ofDays.isYear());
		assertTrue(ofDays.isMonth());
		assertTrue(ofDays.isDay());
		assertEquals("3M10D", ofDays.getStringValue());
		
		ofDays.withYears(2);
		assertEquals(10, ofDays.getDays());
		assertEquals(3, ofDays.getMonths());
		assertEquals(2, ofDays.getYears());
		assertTrue(ofDays.isYear());
		assertTrue(ofDays.isMonth());
		assertTrue(ofDays.isDay());
		assertEquals("2Y3M10D", ofDays.getStringValue());
		
		ofDays = ofDays.yearsToMonths();
		assertEquals(10, ofDays.getDays());
		assertEquals(27, ofDays.getMonths());
		assertEquals(0, ofDays.getYears());
		assertFalse(ofDays.isYear());
		assertTrue(ofDays.isMonth());
		assertTrue(ofDays.isDay());
		assertEquals("27M10D", ofDays.getStringValue());
		
		ofDays.withDays(0);
		assertEquals(0, ofDays.getDays());
		assertEquals(27, ofDays.getMonths());
		assertEquals(0, ofDays.getYears());
		assertFalse(ofDays.isYear());
		assertTrue(ofDays.isMonth());
		assertFalse(ofDays.isDay());
		assertEquals("27M", ofDays.getStringValue());
	}

}

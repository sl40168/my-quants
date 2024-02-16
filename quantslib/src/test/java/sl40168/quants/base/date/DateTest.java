package sl40168.quants.base.date;

import static org.junit.Assert.*;

import org.junit.Test;

public class DateTest {

	@Test
	public void testToDate() {
		assertEquals(1, Date.MIN.getDate());
		assertEquals(60, new Date(1900, 2, 29).getDate());
		assertEquals(44926, new Date(2022,12,31).getDate());
		assertEquals(44927, new Date(2023,1,1).getDate());
		assertEquals(45291, new Date(2023,12,31).getDate());
		assertEquals(45292, new Date(2024,1,1).getDate());
		assertEquals(45351, new Date(2024,2,29).getDate());
		assertEquals(45352, new Date(2024,3,1).getDate());
		assertEquals(45657, new Date(2024,12,31).getDate());
	}

	@Test
	public void testFromDate() {
		assertEquals(Date.MIN, Date.fromDate(1));
		assertEquals(new Date(1900, 2, 29), Date.fromDate(60));
		assertEquals(new Date(2022, 12, 31), Date.fromDate(44926));
		assertEquals(new Date(2023,1,1), Date.fromDate(44927));
		assertEquals(new Date(2023,12,31), Date.fromDate(45291));
		assertEquals(new Date(2024,1,1), Date.fromDate(45292));
		assertEquals(new Date(2024,2,29), Date.fromDate(45351));
		assertEquals(new Date(2024, 3, 1), Date.fromDate(45352));
		assertEquals(new Date(2024, 12, 31), Date.fromDate(45657));
	}
	
	@Test
	public void testMoveDays() {
		assertEquals(new Date(2024, 1, 1), new Date(2023, 12, 31).moveDays(1));
		assertEquals(new Date(2024, 1, 1), new Date(2024, 1, 1).moveDays(0));
		assertEquals(new Date(2023, 12, 31), new Date(2024, 12, 31).moveDays(-366));
	}
}

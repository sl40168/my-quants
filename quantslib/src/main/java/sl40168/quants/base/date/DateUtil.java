package sl40168.quants.base.date;

public class DateUtil {

	/**
	 * Just follow the rol in Excel here.
	 * @param year
	 * @return
	 */
	public static boolean isLeapYear(int year) {
		int remainder = year % 4;
		return remainder == 0;
	}
	
	public static boolean isWeekend(WeekDay weekDay) {
		return WeekDay.Saturday.equals(weekDay) || WeekDay.Sunday.equals(weekDay);
	}
}
package sl40168.quants.base.date;

import java.util.Objects;

public class Date {

	private static final int MIN_YEAR = 1900;
	private static final Month MIN_MONTH = Month.January;
	private static final int MIN_DAY = 1;
	private static final int MAX_YEAR = 2199;
	private static final Month MAX_MONTH = Month.December;

	public static final Date MIN = new Date(MIN_YEAR, MIN_MONTH, MIN_DAY);
	public static final Date MAX = new Date(MAX_YEAR, MAX_MONTH, MAX_MONTH.getMaxDays(MAX_YEAR));

	private int year;
	private Month month;
	private int day;
	private int date;
	private WeekDay weekDay;

	public Date(int year, Month month, int day) {
		isValid(year, month, day);
		this.year = year;
		this.month = month;
		this.day = day;
		this.date = toDate(this.year, this.month, this.day);
		this.weekDay = calcWeekDay(this.year, this.month, this.day);
	}

	public Date(int year, int month, int day) {
		isValid(year, month, day);
		this.year = year;
		this.month = Month.parse(month);
		this.day = day;
		this.date = toDate(this.year, this.month, this.day);
		this.weekDay = calcWeekDay(this.year, this.month, this.day);
	}

	public WeekDay getWeekDay() {
		return weekDay;
	}

	public Date moveDays(int days) {
		int newDate = this.date + days;
		return fromDate(newDate);
	}

	@Override
	public int hashCode() {
		return Objects.hash(this.year, this.month, this.day);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (null == obj) {
			return false;
		}
		if (!Date.class.equals(obj.getClass())) {
			return false;
		}
		Date other = (Date) obj;
		return other.year == this.year && other.month == this.month && other.day == this.day;
	}

	@Override
	public String toString() {
		return String.valueOf(this.year) + "/" + String.valueOf(this.month.getMonth()) + "/" + String.valueOf(this.day);
	}

	public int getDate() {
		return date;
	}

	public Month getMonth() {
		return month;
	}

	public int getYear() {
		return year;
	}

	public int getDay() {
		return day;
	}

	static int toDate(int year, Month month, int day) {
		int days = 0;
		int years = year - MIN_YEAR;
		days += years * 365;
		int numberOfLeapYear = years / 4;
		int yearsFromLeapYear = years % 4;
		if (yearsFromLeapYear != 0) {
			numberOfLeapYear += 1;
		}
		days += numberOfLeapYear;
		for (Month m : Month.values()) {
			if (m.getMonth() < month.getMonth()) {
				days += m.getMaxDays(year);
			}
		}
		days += day;
		return days;
	}
	
	public int between(Date other) {
		return this.getDate() - other.getDate();
	}
	
	public int lengthOfYear( ) {
		return DateUtil.isLeapYear(this.year) ? 366 : 365;
	}

	public static Date fromDate(int date) {
		int years = date / 365;
		int year = MIN_YEAR + years;
		Date temp = new Date(year, 1, 1);
		int leftDays = date - temp.getDate() + 1;
		if (leftDays < 1) {
			year -= 1;
			temp = new Date(year, 1, 1);
			leftDays = date - temp.getDate() + 1;
		} else if (leftDays > 366 || (leftDays > 365 && (!DateUtil.isLeapYear(year)))) {
			year += 1;
			temp = new Date(year, 1, 1);
			leftDays = date - temp.getDate() + 1;
		}
		Month month = null;
		for (Month m : Month.values()) {
			month = m;
			if (leftDays <= month.getMaxDays(year)) {
				break;
			}
			leftDays -= month.getMaxDays(year);
		}
		return new Date(year, month, leftDays);
	}

	/**
	 * Base on Kim larsen calculation formula and the first day of week is Monday
	 * 
	 * @param year
	 * @param month
	 * @param day
	 * @return
	 */
	private static WeekDay calcWeekDay(int year, Month month, int day) {
		int y = year, m = month.getMonth(), d = day;
		if (m == 1 || m == 2) {
			m += 12;
			y -= 1;
		}
		int dayOfWeek = (d + 2 * m + 3 * (m + 1) / 5 + y + y / 4 - y / 100 + y / 400) % 7;

		return WeekDay.values()[dayOfWeek];
	}

	private static void isValid(int year, Month month, int day) {
		if (null == month) {
			throw new InvalidDateException(year, 0, day);
		}
		isValid(year, month.getMonth(), day);
	}

	private static void isValid(int year, int month, int day) {
		if (year < MIN_YEAR || year > MAX_YEAR) {
			throw new InvalidDateException(year, month, day);
		}
		Month m = Month.parse(month);
		if (null == m) {
			throw new InvalidDateException(year, month, day);
		}

		if (day < 1) {
			throw new InvalidDateException(year, month, day);
		}
		int maxDaysInMonth = m.getMaxDays(year);
		if (day > maxDaysInMonth) {
			throw new InvalidDateException(year, month, day);
		}
	}

	public static class InvalidDateException extends IllegalArgumentException {
		public InvalidDateException(int year, int month, int day) {
			super(String.valueOf(year) + "/" + String.valueOf(month) + "/" + String.valueOf(day)
					+ " is an invalid date.");
		}
	}
}

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

	public Date(int year, Month month, int day) {
		isValid(year, month, day);
		this.year = year;
		this.month = month;
		this.day = day;
		this.date = toDate(this.year, this.month, this.day);
	}

	public Date(int year, int month, int day) {
		isValid(year, month, day);
		this.year = year;
		this.month = Month.parse(month);
		this.day = day;
		this.date = toDate(this.year, this.month, this.day);
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
		return String.valueOf(this.year) + "/" + String.valueOf(this.month.month) + "/" + String.valueOf(this.day);
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
			if (m.month < month.month) {
				days += m.getMaxDays(year);
			}
		}
		days += day;
		return days;
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

	private static void isValid(int year, Month month, int day) {
		if (null == month) {
			throw new InvalidDateException(year, 0, day);
		}
		isValid(year, month.month, day);
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

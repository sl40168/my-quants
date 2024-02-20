package sl40168.quants.base.common;

import java.util.Objects;

public class Tenor {
	
	public static final Tenor ZERO = Tenor.ofDays(0);

	private int years;
	private int months;
	private int days;
	private int unit;

	private Tenor(int years, int months, int days) {
		this.years = years;
		this.months = months;
		this.days = days;
		this.unit = (this.years != 0 ? Unit.Year.getLabel() : 0) + (this.months != 0 ? Unit.Month.getLabel() : 0)
				+ (this.days != 0 ? Unit.Day.getLabel() : 0);
	}

	public static Tenor ofDays(int days) {
		return new Tenor(0, 0, days);
	}

	public static Tenor ofYears(int years) {
		return new Tenor(1, 0, 0);
	}

	public static Tenor ofMonths(int months) {
		return new Tenor(0, 1, 0);
	}
	
	public boolean isYear() {
		return (this.unit & Unit.Year.getLabel()) == Unit.Year.getLabel();
	}
	
	public boolean isMonth() {
		return (this.unit & Unit.Month.getLabel()) == Unit.Month.getLabel();
	}
	
	public boolean isDay() {
		return (this.unit & Unit.Day.getLabel()) == Unit.Day.getLabel();
	}

	public Tenor withYears(int years) {
		this.years = years;
		this.unit = this.years != 0 ? (this.unit | Unit.Year.getLabel())
				: (this.unit & (Unit.Month.getLabel() + Unit.Day.getLabel()));
		return this;
	}

	public Tenor withMonths(int months) {
		this.months = months;
		this.unit = this.months != 0 ? (this.unit | Unit.Month.getLabel())
				: (this.unit & (Unit.Year.getLabel() + Unit.Day.getLabel()));
		return this;
	}

	public Tenor withDays(int days) {
		this.days = days;
		this.unit = this.days != 0 ? (this.unit | Unit.Day.getLabel())
				: (this.unit & (Unit.Year.getLabel() + Unit.Month.getLabel()));
		return this;
	}

	public Tenor yearsToMonths() {
		return new Tenor(0, this.years * 12 + this.months, this.days);
	}

	public String getStringValue() {
		StringBuilder sb = new StringBuilder();
		if (this.years != 0) {
			sb.append(this.years).append(Unit.Year.getName());
		}
		if (this.months != 0) {
			sb.append(this.months).append(Unit.Month.getName());
		}
		if (this.days != 0 || sb.length() == 0) {
			sb.append(this.days).append(Unit.Day.getName());
		}

		return sb.toString();
	}

	@Override
	public int hashCode() {
		return Objects.hash(this.years, this.months, this.days);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (null == obj) {
			return false;
		}
		if (!this.getClass().equals(obj.getClass())) {
			return false;
		}
		Tenor other = (Tenor) obj;
		return this.years == other.years && this.months == other.months && this.days == other.days;
	}

	public int getDays() {
		return days;
	}

	public int getMonths() {
		return months;
	}

	public int getYears() {
		return years;
	}

	public static enum Unit {
		Year("Y", 4), Month("M", 2), Day("D", 1);

		private final String name;
		private final int label;

		private Unit(String name, int label) {
			this.name = name;
			this.label = label;
		}

		public int getLabel() {
			return label;
		}

		public String getName() {
			return name;
		}
	}
}

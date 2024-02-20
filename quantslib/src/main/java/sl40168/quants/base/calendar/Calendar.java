package sl40168.quants.base.calendar;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import sl40168.quants.base.date.Date;
import sl40168.quants.base.date.DateUtil;

public class Calendar {

	private CalendarRef ref;
	private Set<Date> holidays;
	private Set<Date> workingWeekends;

	public Calendar(CalendarRef ref, Collection<Date> holidays, Collection<Date> workingWeekends) {
		this.ref = ref;
		this.holidays = null != holidays ? new HashSet<>(holidays) : Collections.EMPTY_SET;
		this.workingWeekends = null != workingWeekends ? new HashSet<>(workingWeekends) : Collections.EMPTY_SET;
	}
	
	public Collection<Date> getHolidays() {
		return new HashSet<>(holidays);
	}
	
	public CalendarRef getRef() {
		return ref;
	}
	
	public Collection<Date> getWorkingWeekends() {
		return new HashSet<>(workingWeekends);
	}

	public static class CalendarRef {
		private String name;

		protected CalendarRef(String name) {
			this.name = name;
		}

		public String getName() {
			return name;
		}
	}
	
	public boolean isBusinessDay(Date day) {
		return !this.isHoliday(day);
	}
	
	public boolean isHoliday(Date day) {
		return this.holidays.contains(day) || (DateUtil.isWeekend(day.getWeekDay()) && (!this.workingWeekends.contains(day)));
	}
	
	public static final CalendarRef CHINA_IB = new CalendarRef("China.IB");
	public static final CalendarRef CHINA_EXCH = new CalendarRef("China.Exch");
	public static final CalendarRef CFETS_CNY = new CalendarRef("Cfets.CNY");
	public static final CalendarRef CFETS_USD = new CalendarRef("Cfets.USD");
	public static final CalendarRef CFETS_EUR = new CalendarRef("Cfets.EUR");
	public static final CalendarRef CFETS_JPY = new CalendarRef("Cfets.JPY");
	public static final CalendarRef CFETS_GBP = new CalendarRef("Cfets.GBP");
	public static final CalendarRef CFETS_CNH = new CalendarRef("Cfets.CNH");
}

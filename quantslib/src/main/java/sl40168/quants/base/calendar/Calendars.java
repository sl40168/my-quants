package sl40168.quants.base.calendar;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import sl40168.quants.base.date.Date;

public class Calendars {

	private String label;
	private Collection<Calendar> calendars;
	private JoinType joinType;

	public Calendars(String label, Collection<Calendar> calendars) {
		this(label, calendars, null);
	}
	
	public Calendars(String label, Collection<Calendar> calendars, JoinType joinType) {
		this.label = label;
		this.calendars = null != calendars ? new ArrayList<>(calendars) : Collections.EMPTY_LIST;
		this.joinType = null != joinType ? joinType : JoinType.Holiday;
	}
	
	public boolean isBusinessDay(Date day) {
		if (JoinType.BusinessDay.equals(this.joinType) ) {
			boolean isBusinessDay = true;
			for (Calendar calendar : this.calendars) {
				isBusinessDay = isBusinessDay && calendar.isBusinessDay(day);
			}
			return isBusinessDay;
		}
		return !this.isHolidayDay(day);
	}
	
	public boolean isHolidayDay(Date day) {
		if (JoinType.Holiday.equals(this.joinType) ) {
			boolean isHoliday = true;
			for (Calendar calendar : this.calendars) {
				isHoliday = isHoliday && calendar.isHoliday(day);
			}
			return isHoliday;
		}
		return !this.isBusinessDay(day);
	}
	
	public static enum JoinType {
		Holiday, BusinessDay;
	}
}

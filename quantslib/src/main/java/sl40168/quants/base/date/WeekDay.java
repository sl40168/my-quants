package sl40168.quants.base.date;

public enum WeekDay {
	Monday(1), Tuesday(2), Wednesday(3), Thursday(4), Friday(5), Saturday(6), Sunday(7);
	
	int dayOfWeek;
	
	private WeekDay(int dayOfWeek) {
		this.dayOfWeek = dayOfWeek;
	}
	
	public int getDayOfWeek() {
		return dayOfWeek;
	}
	
	public static WeekDay parse(int weekDay) {
		if (weekDay > WeekDay.values().length) {
			return null;
		}
		return WeekDay.values()[weekDay - 1];
	}
}

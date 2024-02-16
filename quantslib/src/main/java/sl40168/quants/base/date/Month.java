package sl40168.quants.base.date;

public enum Month {

	January(1, 31), February(2, 28), March(3, 31), April(4, 30), May(5, 31), June(6, 30), July(7, 31), August(8, 31),
	September(9, 30), October(10, 31), November(11, 30), December(12, 31);

	int month;
	int normalDays;

	Month(int month, int normalDays) {
		this.month = month;
		this.normalDays = normalDays;
	}

	public int getMonth() {
		return month;
	}

	public int getMaxDays(int year) {
		if (February.equals(this) && DateUtil.isLeapYear(year)) {
			return normalDays + 1;	
		}
		return normalDays;
	}

	static Month parse(int month) {
		if (month < 1 || month > Month.values().length) {
			return null;
		}
		return Month.values()[month - 1];
	}
}

package sl40168.quants.base.common;

import sl40168.quants.base.date.Date;

public interface DayCounter {

	public double yearFraction(Date firstDay, Date secondDay);
	
	public double yearFraction(Date firstDay, Date secondDay, Date startDay, Date endDay);
	
}

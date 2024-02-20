package sl40168.quants.base.common;

import sl40168.quants.base.date.Date;

public enum StandardDayCounter implements DayCounter {
	ONE_ONE("1/1") {

		@Override
		public double yearFraction(Date firstDay, Date secondDay) {
			return 1;
		}

		@Override
		public double yearFraction(Date firstDay, Date secondDay, Date startDay, Date endDay) {
			return 1;
		}

	},
	
	ACT_ACT_ISDA("Act/Act ISDA") {

		@Override
		public double yearFraction(Date firstDay, Date secondDay) {
			int between = secondDay.between(firstDay);
			
			return 0;
		}

		@Override
		public double yearFraction(Date firstDay, Date secondDay, Date startDay, Date endDay) {
			return 0;
		}
		
	};

	private final String name;

	private StandardDayCounter(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
}

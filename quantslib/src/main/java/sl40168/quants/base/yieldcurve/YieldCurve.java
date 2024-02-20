package sl40168.quants.base.yieldcurve;

import sl40168.quants.base.date.Date;

public abstract class YieldCurve {

	private Date referenceDate;

	public YieldCurve(Date referenceDate) {
		this.referenceDate = referenceDate;
	}
	
	public abstract double discountFactor(Date date);
	
	public abstract double zeroRate(Date date);
}

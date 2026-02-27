package interval_oop;

public class Interval {
	private int lowerBound;
	private int upperBound;
	
	public static int getLowerBound(Interval interval) {
		return interval.lowerBound;
	}
	
	public static int getUpperBound(Interval interval) {
		return interval.upperBound;
	}
	
	public static void setLowerBound(Interval interval, int newLowerBound) {
		interval.lowerBound = newLowerBound;
	}
	
	public static void setUpperBound(Interval interval, int newUpperBound) {
		interval.upperBound = newUpperBound;
	}
}
package intervals;

public class Interval{
	int start;
	int end;
	
	static int getStart(Interval interval) {
		return interval.start;
	}
	static int getEnd(Interval interval) {
		return interval.end;
	}
	static void setStart(Interval interval, int start) {
		interval.start = start;
	}
	static void setEnd(Interval interval, int end) {
		interval.end = end;
	}
}
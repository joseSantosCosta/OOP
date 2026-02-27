package intervals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class intervalsTest {

	@Test
	void test() {
		Interval myInterval = new Interval();
		Interval.setStart(myInterval,3);
		Interval.setEnd(myInterval, 7);
		int length = Interval.getEnd(myInterval) - Interval.getStart(myInterval);
		assertEquals(4,length);
	}

}

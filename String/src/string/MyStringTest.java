package string;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class MyStringTest {

	@Test
	void testToCharArray() {
		MyString t = MyString.valueOf('a');
		t.toCharArray()[0] = 'b';
		assertEquals('a',t.charAt(0));
	}

}

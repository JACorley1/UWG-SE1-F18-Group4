package edu.westga.cs3211.time_management.test.event;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import edu.westga.cs3211.time_management.model.Event;
import edu.westga.cs3211.time_management.model.Visibility;

class TestToString {

	@Test
	void test() {
		LocalDateTime start = LocalDateTime.of(2030, 1, 1, 0, 0);
		LocalDateTime end = start.plusDays(1);
		
		Event event = new Event("Bob", start, end, "location", "description", Visibility.PUBLIC);
		
		String result = event.toString();
		
		String expectedString = "Bob" + System.lineSeparator()
							  + "Tue Jan 01, 2030 - Wed Jan 02, 2030" + System.lineSeparator()
							  + "location";
		assertEquals(expectedString, result);
		
	}

}

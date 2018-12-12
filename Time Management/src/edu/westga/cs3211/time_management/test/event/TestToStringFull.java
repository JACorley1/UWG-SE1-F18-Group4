package edu.westga.cs3211.time_management.test.event;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

import edu.westga.cs3211.time_management.model.Event;
import edu.westga.cs3211.time_management.model.Visibility;

class TestToStringFull {

	@Test
	void test() {
		
		LocalDateTime start = LocalDateTime.of(2030, 1, 1, 0, 0);
		LocalDateTime end = start.plusDays(1);
		
		Event event = new Event("Bob", start, end, "location", "description", Visibility.PUBLIC);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm a");
		
		String result = event.toStringFull();

		String expectedString = "";
		expectedString += "Name: Bob" + System.lineSeparator();
		expectedString += "Start time: " + "Tue Jan 01, 2030 at " + formatter.format(start) + System.lineSeparator();
		expectedString += "End time: " + "Wed Jan 02, 2030 at " + formatter.format(end) + System.lineSeparator();
		expectedString += "Location: location" + System.lineSeparator();
		expectedString += "Description: description" + System.lineSeparator();
		expectedString += "Visibility: Public" + System.lineSeparator();

		assertEquals(expectedString, result);
	}

}

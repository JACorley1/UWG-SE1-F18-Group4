package edu.westga.cs3211.time_management.test.event;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import edu.westga.cs3211.time_management.model.Event;
import edu.westga.cs3211.time_management.model.Visibility;

class TestConstructor {

	@Test
	void testInvalidName() {	
		LocalDateTime start = LocalDateTime.now().plusDays(1);
		LocalDateTime end = start.plusDays(1);
		
		assertThrows(
						IllegalArgumentException.class, 
						()->{
							new Event(null, start, end, "", "", Visibility.PUBLIC);
						}
					);
	}

	@Test
	void testInvalidStartTime() {		
		LocalDateTime start = LocalDateTime.now().plusDays(1);
		LocalDateTime end = start.plusDays(1);

		assertThrows(
						IllegalArgumentException.class, 
						()->{
							new Event("Bob", null, end, "", "", Visibility.PUBLIC);
						}
					);
	}

	@Test
	void testInvalidEndTime() {		
		LocalDateTime start = LocalDateTime.now().plusDays(1);
			
		assertThrows(
						IllegalArgumentException.class, 
						()->{
							new Event("Bob", start, null, "", "", Visibility.PUBLIC);
						}
					);
	}

	@Test
	void testNullLocation() {		
		LocalDateTime start = LocalDateTime.now().plusDays(1);
		LocalDateTime end = start.plusDays(1);
			
		assertThrows(
						IllegalArgumentException.class, 
						()->{
							new Event("Bob", start, end, null, "", Visibility.PUBLIC);
						}
					);
	}

	@Test
	void testNullDescription() {		
		LocalDateTime start = LocalDateTime.now().plusDays(1);
		LocalDateTime end = start.plusDays(1);
			
		assertThrows(
						IllegalArgumentException.class, 
						()->{
							new Event("Bob", start, end, "", null, Visibility.PUBLIC);
						}
					);
	}

	@Test
	void testNullVisibility() {		
		LocalDateTime start = LocalDateTime.now().plusDays(1);
		LocalDateTime end = start.plusDays(1);
			
		assertThrows(
						IllegalArgumentException.class, 
						()->{
							new Event("Bob", start, end, "", "", null);
						}
					);
	}

	@Test
	void testValidEvent() {			
		LocalDateTime start = LocalDateTime.now().plusDays(1);
		LocalDateTime end = start.plusDays(1);
		
		
		Event result = new Event("Bob", start, end, "location", "description", Visibility.PUBLIC);
		
		assertEquals("Bob", result.getName(), "checking name");
		assertEquals(start, result.getStartTime(), "checking start time");
		assertEquals(end, result.getEndTime(), "checking end time");
		assertEquals("location", result.getLocation(), "checking location");
		assertEquals("description", result.getDescription(), "checking description");
		
		assertEquals(Visibility.PUBLIC, result.getVisibility(), "checking visibility");
	}

}

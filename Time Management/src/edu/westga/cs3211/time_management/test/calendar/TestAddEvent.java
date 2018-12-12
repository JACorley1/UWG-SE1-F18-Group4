package edu.westga.cs3211.time_management.test.calendar;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.ArrayList;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import edu.westga.cs3211.time_management.model.Calendar;
import edu.westga.cs3211.time_management.model.Event;
import edu.westga.cs3211.time_management.model.Visibility;

public class TestAddEvent {

	@Test
	public void testAddNullEvent() {
		Calendar myCalendar = new  Calendar();
		
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			myCalendar.addEvent(null);
		});
		
	}
	
	@Test
	public void testAddOneEvent() {
		Calendar myCalendar = new  Calendar();
		LocalDateTime start = LocalDateTime.now().plusDays(1);
		LocalDateTime end = start.plusDays(1);
		ArrayList<String> attendees = new ArrayList<String>();
		attendees.add("jack");
		Event myEvent = new Event("Name", start, end, "school", "homework", Visibility.PUBLIC);
		
		myCalendar.addEvent(myEvent);
		
		assertEquals(1, myCalendar.getEvents().size());
		
	}
	
	@Test
	public void testAddOneEventInCalenderOfEvents() {
		Calendar myCalendar = new  Calendar();
		LocalDateTime start = LocalDateTime.now().plusDays(1);
		LocalDateTime end = start.plusDays(1);
		ArrayList<String> attendees = new ArrayList<String>();
		attendees.add("jack");
		Event myEvent = new Event("Name", start, end, "school", "homework", Visibility.PUBLIC);
		
		LocalDateTime start2 = LocalDateTime.now().plusDays(1);
		LocalDateTime end2 = start2.plusDays(1);	
		Event myEvent2 = new Event("Name", start2, end2, "school", "homework", Visibility.PUBLIC);
		

		LocalDateTime start3 = LocalDateTime.now().plusDays(1);
		LocalDateTime end3 = start3.plusDays(1);	
		Event myEvent3 = new Event("Name", start3, end3, "school", "homework", Visibility.PUBLIC);
		
		myCalendar.addEvent(myEvent);
		myCalendar.addEvent(myEvent3);
		myCalendar.addEvent(myEvent2);
		
		assertEquals(3, myCalendar.getEvents().size());
		
	}

}

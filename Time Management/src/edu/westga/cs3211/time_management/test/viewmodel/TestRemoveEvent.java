package edu.westga.cs3211.time_management.test.viewmodel;

import static org.junit.jupiter.api.Assertions.*;


import java.time.LocalDateTime;


import org.junit.jupiter.api.Test;

import edu.westga.cs3211.time_management.model.Event;
import edu.westga.cs3211.time_management.model.Visibility;
import edu.westga.cs3211.time_management.viewmodel.TimeManagementViewModel;

class TestRemoveEvent {

	@Test
	void testRemoveOneEvent() {
		TimeManagementViewModel vModel = new TimeManagementViewModel();
		LocalDateTime start = LocalDateTime.of(2030, 1, 1, 0, 0);
		LocalDateTime end = start.plusDays(1);
		String location = "test";
		String description = "test";
		String name ="test";
		Visibility visibility = Visibility.PUBLIC;
		
		Event event = new Event(name, start, end, location, description, visibility);
		vModel.addEventToCalendar(event);
		vModel.removeEvent(event);
		assertEquals(0,vModel.getCalendar().getEvents().size(), " size is zero");
	}
	
	@Test
	void testRemoveOneEventWithMultipleEvents() {
		TimeManagementViewModel vModel = new TimeManagementViewModel();
		LocalDateTime start = LocalDateTime.of(2030, 1, 1, 0, 0);
		LocalDateTime end = start.plusDays(1);
		String location = "test";
		String description = "test";
		String name ="test";
		Visibility visibility = Visibility.PUBLIC;
		
		Event event = new Event(name, start, end, location, description, visibility);
		
	
		LocalDateTime start2 = LocalDateTime.of(2030, 1, 1, 0, 0);
		LocalDateTime end2 = start2.plusDays(1);
		String location2 = "test";
		String description2 = "test";
		String name2 ="test";
		Visibility visibility2 = Visibility.PUBLIC;
		
		Event event2 = new Event(name2, start2, end2, location2, description2, visibility2);
		vModel.addEventToCalendar(event);
		vModel.addEventToCalendar(event2);
		vModel.removeEvent(event2);
		assertEquals(1,vModel.getCalendar().getEvents().size(), " size is one");
	}

}

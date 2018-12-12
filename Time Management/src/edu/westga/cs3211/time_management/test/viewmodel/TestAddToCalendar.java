package edu.westga.cs3211.time_management.test.viewmodel;

import static org.junit.jupiter.api.Assertions.*;


import java.time.LocalDateTime;


import org.junit.jupiter.api.Test;

import edu.westga.cs3211.time_management.model.Event;
import edu.westga.cs3211.time_management.model.Visibility;
import edu.westga.cs3211.time_management.viewmodel.TimeManagementViewModel;

class TestAddToCalendar {

	@Test
	void addToCalendar() {
		TimeManagementViewModel vModel = new TimeManagementViewModel();
		
		LocalDateTime start = LocalDateTime.of(2030, 1, 1, 0, 0);
		LocalDateTime end = start.plusDays(1);
		String location = "test";
		String description = "test";  
		String name ="test";
		Visibility visibility = Visibility.PUBLIC;
		
		Event event = new Event(name, start, end, location, description, visibility);
		vModel.addEventToCalendar(event); 
		assertEquals(1, vModel.getCalendar().getEvents().size());
	}

}

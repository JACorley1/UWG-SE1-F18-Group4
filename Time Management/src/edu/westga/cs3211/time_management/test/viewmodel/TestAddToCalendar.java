package edu.westga.cs3211.time_management.test.viewmodel;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import org.junit.jupiter.api.Test;

import edu.westga.cs3211.time_management.model.Event;
import edu.westga.cs3211.time_management.model.Visibility;
import edu.westga.cs3211.time_management.viewmodel.TimeManagementViewModel;

class TestAddToCalendar {

	@Test
	void addToCalendar() {
		TimeManagementViewModel vModel = new TimeManagementViewModel();
		LocalTime startTime = LocalTime.now();
		LocalTime endTime = LocalTime.now();
		LocalDate date = LocalDate.of(1, 2, 3);
		LocalDateTime start = LocalDateTime.of(date, startTime);
		LocalDateTime end = LocalDateTime.of(date, endTime);
		String location = "test";
		String description = "test";
		String name ="test";
		Visibility visibility = Visibility.PUBLIC;
		
		Event event = new Event(name, start, end, location, description, visibility);
		vModel.addEventToCalendar(event);
		var calendar = vModel.getCalendar();
		var collection = calendar.getEvents();
		
		assertTrue(collection.size() == 1);
	}

}

package edu.westga.cs3211.time_management.test.viewmodel;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.westga.cs3211.time_management.viewmodel.TimeManagementViewModel;

class TestGetProperties {

	@Test
	void testGetNameProperty() {
		TimeManagementViewModel vModel = new TimeManagementViewModel();
		
		assertEquals(null, vModel.getNameProperty().getValue());
	}
	
	@Test
	void testGetLocationProperty() {
		TimeManagementViewModel vModel = new TimeManagementViewModel();
		
		assertEquals(null, vModel.getLocationProperty().getValue());
	}
	
	@Test
	void testGetStartTimeProperty() {
		TimeManagementViewModel vModel = new TimeManagementViewModel();
		
		assertEquals(null, vModel.getStartTimeProperty().getValue());
	}
	
	@Test
	void testGetEndTimeProperty() {
		TimeManagementViewModel vModel = new TimeManagementViewModel();
		
		assertEquals(null, vModel.getEndTimeProperty().getValue());
	}
	
	@Test
	void testGetErrorMessage() {
		TimeManagementViewModel vModel = new TimeManagementViewModel();
		
		assertEquals("", vModel.getErrorMessage());
	}
	
	@Test
	void testSummaryAndConflictText() {
		TimeManagementViewModel vModel = new TimeManagementViewModel();
		
		assertEquals(null, vModel.getSummaryAndConflictText());
	}

}

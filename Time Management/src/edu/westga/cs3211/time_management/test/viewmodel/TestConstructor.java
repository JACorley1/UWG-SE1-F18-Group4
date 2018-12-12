package edu.westga.cs3211.time_management.test.viewmodel;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.westga.cs3211.time_management.model.EventDataValidator;
import edu.westga.cs3211.time_management.viewmodel.TimeManagementViewModel;

class TestConstructor {

	@Test
	void testCreateDefaultConstructor() {
		new TimeManagementViewModel();

	}

}

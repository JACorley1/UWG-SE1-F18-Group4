package edu.westga.cs3211.time_management.viewmodel;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

import edu.westga.cs3211.time_management.model.Calendar;
import edu.westga.cs3211.time_management.model.Event;
import edu.westga.cs3211.time_management.model.EventDataValidator;
import edu.westga.cs3211.time_management.model.Visibility;
import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;

/**
 * View Model for time management system
 * 
 * @author Dexter Tarver, Nolan Williams, Tyler Scott
 *
 */
public class TimeManagementViewModel {
	private Calendar calendar;
	private final StringProperty nameText;
	private final ObjectProperty<LocalDate> startTime;
	private final ObjectProperty<LocalDate> endTime;
	private final StringProperty locationProperty;
	private String errorMessage;
	private final StringProperty description;
	private final ListProperty<Visibility> visibilityList;
	private String eventSummaryAndConflictText;

	/**
	 * Constructs a new time management view model
	 * 
	 */
	public TimeManagementViewModel() {

		this.nameText = new SimpleStringProperty();
		this.startTime = new SimpleObjectProperty<LocalDate>();
		this.endTime = new SimpleObjectProperty<LocalDate>();
		this.locationProperty = new SimpleStringProperty();
		this.description = new SimpleStringProperty();
		this.visibilityList = new SimpleListProperty<Visibility>(FXCollections.observableArrayList());
		this.calendar = new Calendar();
		this.visibilityList.add(Visibility.PUBLIC);
		this.visibilityList.add(Visibility.PRIVATE);
		this.visibilityList.add(Visibility.FRIENDS_ONLY);
//		this.visibilityList.setValue(Visibility.PUBLIC);
	}

	/**
	 * Adds a new Event
	 */
	public void addEvent() {
		Event newEvent = this.setupNewEvent();
		List<Event> conflictingEvents = this.calendar.declareConflicts(newEvent);
		String eventText = newEvent.toStringFull();
		String conflictText = "";
		for (Event currEvent : conflictingEvents) {
			conflictText += currEvent.toString() + System.lineSeparator() + System.lineSeparator();
		}
		this.eventSummaryAndConflictText = "NEW EVENT DETAILS" + System.lineSeparator() + eventText
				+ System.lineSeparator() + "CONFLICTING EVENTS\n" + conflictText;
	}

	private Event setupNewEvent() {
		LocalDateTime startTime = null;
		LocalDateTime endTime = null;
		String name = this.nameText.getValue();

		if (!EventDataValidator.checkName(name)) {
			this.errorMessage += "Name is invalid" + System.lineSeparator();
		}
		if ((this.startTime.getValue() != null) && (this.endTime != null)) {
			startTime = LocalDateTime.of(this.startTime.getValue(), LocalTime.of(5, 0));
			endTime = LocalDateTime.of(this.endTime.getValue(), LocalTime.of(9, 0));
			if (!EventDataValidator.checkStartTime(startTime)) {
				this.errorMessage += "Invalid start time" + System.lineSeparator();
			} else if (!EventDataValidator.checkEndTime(startTime, endTime)) {
				this.errorMessage += "Invalid end time" + System.lineSeparator();
			}
		} else {
			this.errorMessage += "You must enter a end and start date";
		}

		String location = this.locationProperty.getValue();
		if (location == null) {
			location = "";
		}
		String description = this.description.getValue();
		if (description == null) {
			description = "";
		}
		Visibility visibility = this.visibilityList.get(0);
		Event newEvent = new Event(name, startTime, endTime, location, description, visibility);
		return newEvent;
	}

	/**
	 * Gets the event summary and conflict text
	 * 
	 * @return the event Summary and Conflict text
	 */
	public String getSummaryAndConflictText() {
		return this.eventSummaryAndConflictText;
	}

	/**
	 * Returns the error message
	 * 
	 * @return the error message
	 */
	public String getErrorMessage() {
		return this.errorMessage;
	}

	/**
	 * Returns the name String Property
	 * 
	 * @return the name string property
	 */
	public StringProperty getNameProperty() {
		return this.nameText;
	}

	/**
	 * Gets the start time property
	 * 
	 * @return the start time property
	 */
	public ObjectProperty<LocalDate> getStartTimeProperty() {
		return this.startTime;
	}

	/**
	 * Gets the end time property
	 * 
	 * @return the end time property
	 */
	public ObjectProperty<LocalDate> getEndTimeProperty() {
		return this.endTime;
	}

	/**
	 * Gets the location property
	 * 
	 * @return the location property
	 */
	public StringProperty getLocationProperty() {
		return this.locationProperty;
	}

	/**
	 * Gets the visibility list
	 * 
	 * @return the visibility list
	 */
	public ListProperty<Visibility> getVisibilityListProperty() {
		return this.visibilityList;
	}
}

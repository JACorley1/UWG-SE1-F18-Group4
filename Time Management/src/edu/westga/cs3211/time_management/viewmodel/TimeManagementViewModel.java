package edu.westga.cs3211.time_management.viewmodel;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;

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
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

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
		this.errorMessage = "";
		this.visibilityList.add(Visibility.PUBLIC);
		this.visibilityList.add(Visibility.PRIVATE);
		this.visibilityList.add(Visibility.FRIENDS_ONLY);

	}

	/**
	 * Adds the event to the calendar
	 * 
	 * @param event the event to the calendar
	 */
	public void addEventToCalendar(Event event) {
		this.calendar.addEvent(event);
	}

	/**
	 * Sets up a new Event
	 * 
	 * @param visible the visibility for the event
	 * 
	 * @return the new event
	 */
	public Event setupNewEvent(Visibility visible) {
		LocalDateTime startTime = null;
		LocalDateTime endTime = null;
		String name = this.nameText.get();
		if (!EventDataValidator.checkName(name)) {
			this.errorMessage += "Name is invalid" + System.lineSeparator();
		}
		if ((this.startTime.get() != null) && (this.endTime.get() != null)) {
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
		if (!this.errorMessage.isEmpty()) {
			this.displayErrorMessage(this.errorMessage);
		}

		String location = this.locationProperty.get();
		if (location == null) {
			location = "";
		}
		String description = this.description.get();
		if (description == null) {
			description = "";
		}
		Visibility visibility = visible;
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

	/**
	 * Returns the calendar
	 * 
	 * @return the calendar
	 */
	public Calendar getCalendar() {
		return this.calendar;
	}

	/**
	 * Gets the events on the calendar
	 * 
	 * @return the calendar events
	 */
	public ArrayList<Event> getCalendarEvents() {
		return this.calendar.getEvents();
	}

	/**
	 * Removes the event from the calendar
	 * 
	 * @param event the event to remove
	 */
	public void removeEvent(Event event) {
		this.calendar.removeEvent(event);
	}

	private void displayErrorMessage(String errorMessage) {
		Alert alert = new Alert(AlertType.ERROR, errorMessage);
		alert.showAndWait();
	}
}

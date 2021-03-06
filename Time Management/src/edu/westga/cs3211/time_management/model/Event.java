package edu.westga.cs3211.time_management.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Store basic information for an event.
 * 
 * @author Nolan Williams, Tyler Scott, Dexter Tarver
 */
public class Event {

	private String name;
	private LocalDateTime startTime;
	private LocalDateTime endTime;
	private String location;
	private String description;

	private Visibility visibility;

	/**
	 * Creates a new Event
	 * 
	 * @precondition EventDataValidator.checkName(name) &&
	 *               EventDataValidator.checkStartTime(start) &&
	 *               EventDataValidator.checkEndTime(start, end) &&
	 *               EventDataValidator.checkAttendees(attendees) && location !=
	 *               null && description != null && visibility != null
	 * @postcondition getName() == name && getStartTime() == startTime &&
	 *                getEndTime() == endTime && getLocation() == location &&
	 *                getDescription() == description && getAttendees() == attendees
	 *                && getVisibility() == visibility
	 * 
	 * @param name        name of the event
	 * @param start       start time for the event
	 * @param end         end time for the event
	 * @param location    location for the event
	 * @param description description of the event
	 * @param visibility  visibility of the event
	 */
	public Event(String name, LocalDateTime start, LocalDateTime end, String location, String description,
			Visibility visibility) {
		if (!EventDataValidator.checkName(name)) {
			throw new IllegalArgumentException("Invalid name");
		}
		if (!EventDataValidator.checkStartTime(start)) {
			throw new IllegalArgumentException("Invalid start time");
		}
		if (!EventDataValidator.checkEndTime(start, end)) {
			throw new IllegalArgumentException("Invalid end time");
		}
		if (location == null) {
			throw new IllegalArgumentException("Invalid location");
		}
		if (description == null) {
			throw new IllegalArgumentException("Invalid description");
		}
		if (visibility == null) {
			throw new IllegalArgumentException("Invalid visibility");
		}
		this.name = name;
		this.startTime = start;
		this.endTime = end;
		this.location = location;
		this.description = description;

		this.visibility = visibility;
	}

	/**
	 * return the name of the event
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return the name of the event
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * return the start time of the event
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return the start time of the event
	 */
	public LocalDateTime getStartTime() {
		return this.startTime;
	}

	/**
	 * return the end time of the event
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return the end time of the event
	 */
	public LocalDateTime getEndTime() {
		return this.endTime;
	}

	/**
	 * return the location of the event
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return the location of the event
	 */
	public String getLocation() {
		return this.location;
	}

	/**
	 * return the description of the event
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return the description of the event
	 */
	public String getDescription() {
		return this.description;
	}

	/**
	 * return the visibility of the event
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return the visibility of the event
	 */
	public Visibility getVisibility() {
		return this.visibility;
	}

	/**
	 * Convert the Event to a String representation.
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return String representation of the Event
	 */
	@Override
	public String toString() {
		return this.name + System.lineSeparator() + this.formatDate(this.startTime) + " - "
				+ this.formatDate(this.endTime) + System.lineSeparator() + this.location;
	}

	private String formatDate(LocalDateTime date) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEE MMM dd, yyyy");
		return formatter.format(date);
	}

	private String formatTime(LocalDateTime date) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm a");
		return formatter.format(date);
	}

	/**
	 * Generate a multi-line full string representation of the event.
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return Multi-line full string representation
	 */
	public String toStringFull() {
		String fullEventDetails = "";
		String at = " at ";
		fullEventDetails += "Name: " + this.name + System.lineSeparator();
		fullEventDetails += "Start time: " + this.formatDate(this.startTime) + at + this.formatTime(this.startTime)
				+ System.lineSeparator();
		fullEventDetails += "End time: " + this.formatDate(this.endTime) + at + this.formatTime(this.endTime)
				+ System.lineSeparator();
		fullEventDetails += "Location: " + this.location + System.lineSeparator();
		fullEventDetails += "Description: " + this.description + System.lineSeparator();
		fullEventDetails += "Visibility: " + this.visibility + System.lineSeparator();

		return fullEventDetails;
	}

}

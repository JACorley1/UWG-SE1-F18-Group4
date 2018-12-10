package edu.westga.cs3211.time_management.view;

import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import edu.westga.cs3211.time_management.model.Calendar;
import edu.westga.cs3211.time_management.model.Event;
import edu.westga.cs3211.time_management.model.Visibility;
import edu.westga.cs3211.time_management.viewmodel.TimeManagementViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * Codebehind for the AddEvent Scene.
 * 
 * @author Jonathan Corley
 */
public class AddEvent {

	@FXML
	private ResourceBundle resources;
	@FXML
	private URL location;

	@FXML
	private Label nameLabel;
	@FXML
	private Label startTimeLabel;
	@FXML
	private Label endTimeLabel;
	@FXML
	private Label locationLabel;
	@FXML
	private Label descriptionLabel;
	@FXML
	private Label visibilityLabel;
	@FXML
	private TextField nameText;
	@FXML
	private DatePicker startTimeDate;
	@FXML
	private DatePicker endTimeDate;
	@FXML
	private TextField locationText;
	@FXML
	private TextField descriptionText;
	@FXML
	private ComboBox<Visibility> visibilityList;
	@FXML
	private Calendar calendar;
	private TimeManagementViewModel timeViewModel;

	@FXML
	void cancel(ActionEvent event) {
		((Node) (event.getSource())).getScene().getWindow().hide();
	}

	@FXML
	void addEvent(ActionEvent event) {
		Visibility visibility = this.visibilityList.getValue();
		Event newEvent = this.timeViewModel.setupNewEvent(visibility);

		List<Event> conflictingEvents = this.calendar.declareConflicts(newEvent);

		String eventText = newEvent.toStringFull();
		String conflictText = "";
		for (Event currEvent : conflictingEvents) {
			conflictText += currEvent.toString() + System.lineSeparator() + System.lineSeparator();
		}
		String eventSummaryAndConflictText = "NEW EVENT DETAILS" + System.lineSeparator() + eventText
				+ System.lineSeparator() + "CONFLICTING EVENTS\n" + conflictText;
		Alert alert = new Alert(AlertType.CONFIRMATION, eventSummaryAndConflictText);
		alert.setTitle("Create New Event?");

		Optional<ButtonType> result = alert.showAndWait();

		if (result.isPresent() && result.get() == ButtonType.OK) {
			this.calendar.addEvent(newEvent);
			((Node) (event.getSource())).getScene().getWindow().hide();
		}
	}

	@FXML
	void initialize() {
		assert this.visibilityLabel != null : "fx:id=\"visibilityLabel\" was not injected: check your FXML file 'AddEvent.fxml'.";
		assert this.locationText != null : "fx:id=\"locationText\" was not injected: check your FXML file 'AddEvent.fxml'.";
		assert this.startTimeDate != null : "fx:id=\"startTimeDate\" was not injected: check your FXML file 'AddEvent.fxml'.";
		assert this.locationLabel != null : "fx:id=\"locationLabel\" was not injected: check your FXML file 'AddEvent.fxml'.";
		assert this.descriptionText != null : "fx:id=\"descriptionText\" was not injected: check your FXML file 'AddEvent.fxml'.";
		assert this.nameText != null : "fx:id=\"nameText\" was not injected: check your FXML file 'AddEvent.fxml'.";
		assert this.endTimeDate != null : "fx:id=\"endTimeDate\" was not injected: check your FXML file 'AddEvent.fxml'.";
		assert this.endTimeLabel != null : "fx:id=\"endTimeLabel\" was not injected: check your FXML file 'AddEvent.fxml'.";
		assert this.startTimeLabel != null : "fx:id=\"startTimeLabel\" was not injected: check your FXML file 'AddEvent.fxml'.";
		assert this.descriptionLabel != null : "fx:id=\"descriptionLabel\" was not injected: check your FXML file 'AddEvent.fxml'.";
		assert this.visibilityList != null : "fx:id=\"visibilityList\" was not injected: check your FXML file 'AddEvent.fxml'.";
		assert this.nameLabel != null : "fx:id=\"nameLabel\" was not injected: check your FXML file 'AddEvent.fxml'.";

		this.timeViewModel = new TimeManagementViewModel();
		this.bindToViewModel();

	}

	private void bindToViewModel() {
		this.nameText.textProperty().bindBidirectional(this.timeViewModel.getNameProperty());
		this.startTimeDate.valueProperty().bindBidirectional(this.timeViewModel.getStartTimeProperty());
		this.endTimeDate.valueProperty().bindBidirectional(this.timeViewModel.getEndTimeProperty());
		this.locationText.textProperty().bindBidirectional(this.timeViewModel.getLocationProperty());
		this.visibilityList.itemsProperty().bind(this.timeViewModel.getVisibilityListProperty());
		this.visibilityList.setValue(Visibility.PUBLIC);
	}

	/**
	 * Sets up the Calendar
	 * 
	 * @param calendar calendar input
	 */
	public void setCalendar(Calendar calendar) {
		if (calendar == null) {
			throw new IllegalArgumentException("Calendar provided was null");
		}
		this.calendar = calendar;
	}
}

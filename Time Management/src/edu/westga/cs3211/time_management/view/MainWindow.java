package edu.westga.cs3211.time_management.view;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import edu.westga.cs3211.time_management.Main;
import edu.westga.cs3211.time_management.model.Event;
import edu.westga.cs3211.time_management.viewmodel.TimeManagementViewModel;
import javafx.beans.binding.BooleanBinding;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * Codebehind for the MainWindow Scene.
 * 
 * @author Jonathan Corley, Dexter Tarver
 */
public class MainWindow {

	@FXML
	private ResourceBundle resources;
	@FXML
	private URL location;
	@FXML
	private ListView<Event> eventList;
	@FXML
	private TextArea eventDetailsText;
	@FXML
    private Button removeButton;

	private TimeManagementViewModel timeViewModel;

	@FXML
	void addEvent(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("view/AddEvent.fxml"));
		loader.load();
		Parent parent = loader.getRoot();
		Scene scene = new Scene(parent);
		Stage addEventStage = new Stage();
		addEventStage.setTitle("Add New Event");
		addEventStage.setScene(scene);
		addEventStage.initModality(Modality.APPLICATION_MODAL);
		AddEvent addEventDialog = loader.getController();
		addEventDialog.setCalendar(this.timeViewModel.getCalendar());
		addEventStage.showAndWait();
		this.eventList.setItems(FXCollections.observableArrayList(this.timeViewModel.getCalendarEvents()));
	}

	@FXML
	void removeEvent(ActionEvent event) throws IOException {
		Event eventSelected = this.eventList.getSelectionModel().getSelectedItem();
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Delete Event");
		alert.setHeaderText("Delete Event");
		alert.setContentText("Click OK, to remove." + System.lineSeparator() + System.lineSeparator()
				+ this.eventDetailsText.getText());
		Optional<ButtonType> result = alert.showAndWait();

		if (result.isPresent() && result.get() == ButtonType.OK) {
			this.timeViewModel.removeEvent(eventSelected);
			this.eventList.setItems(FXCollections.observableArrayList(this.timeViewModel.getCalendarEvents()));
			this.eventDetailsText.setText("");
		}
	}

	@FXML
	void selectEvent(MouseEvent event) {
		Event eventSelected = this.eventList.getSelectionModel().getSelectedItem();
		if (eventSelected != null) {
			this.eventDetailsText.setText(eventSelected.toStringFull());
		}
	}

	@FXML
	void initialize() {
		assert this.eventList != null : "fx:id=\"eventList\" was not injected: check your FXML file 'MainWindow.fxml'.";
		assert this.eventDetailsText != null : "fx:id=\"eventDetailsText\" was not injected: check your FXML file 'MainWindow.fxml'.";
		
		this.timeViewModel = new TimeManagementViewModel();
		this.eventList.setItems(FXCollections.observableArrayList(this.timeViewModel.getCalendarEvents()));
		this.bindGuiInteraction();
	}
	
	private void bindGuiInteraction() {
		
		BooleanBinding enableRemoveButton = javafx.beans.binding.Bindings.isNull(this.eventList.getSelectionModel().selectedItemProperty());
		this.removeButton.disableProperty().bind(enableRemoveButton);
	}
}
	
	
	
	
	
	
	


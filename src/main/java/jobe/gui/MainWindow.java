package jobe.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import jobe.Jobe;

/**
 * Controller for the main GUI.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;
    
    private Jobe jobe;
    
    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/penguin.jpg"));
    private Image jobeImage = new Image(this.getClass().getResourceAsStream("/images/jobe.jpg"));
    
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().add(
                DialogBox.getJobeDialog("Hello! My name is Jobe! How can I help you!", jobeImage)
        );
    }
    
    /** Injects the Duke instance */
    public void setJobe(Jobe jobe) {
        this.jobe = jobe;
    }
    
    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = jobe.getResponse(input);
        dialogContainer.getChildren().addAll(
          DialogBox.getUserDialog(input, userImage),
          DialogBox.getJobeDialog(response, jobeImage)
        );
        userInput.clear();
    }
}

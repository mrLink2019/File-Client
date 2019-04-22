package application;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.FileChooser;

public class Controller {

    @FXML
    private ResourceBundle resources;
    private URL location;
    File choosedFile;

    @FXML
    private Button openFileButton;
    @FXML
    private Button sendFileButton;
    @FXML
    private Label filePathLabel;
    @FXML
    private Label fileCheckerLabel;
    @FXML
    private Label sendingCheckerLabel;

    @FXML
    public void ChooseFile(ActionEvent event) throws IOException {
    	FileChooser fs = new FileChooser();      
        fs.getExtensionFilters().addAll(
        		new FileChooser.ExtensionFilter("Всі файли", "*.*"));
        choosedFile = fs.showOpenDialog(null);
     if(choosedFile.isFile()) {
    	 filePathLabel.setText(choosedFile.getAbsolutePath());
    	 fileCheckerLabel.setText("File successfully chosed!");
     } else {
    	 fileCheckerLabel.setText("Something went wrong!");
     }
    }
    
    @FXML
    public void SendFile() {
    	//TODO: sending file on server
    }
    @FXML
    void initialize() {
        assert filePathLabel != null : "fx:id=\"filePathLabel\" was not injected: check your FXML file 'view.fxml'.";
        assert openFileButton != null : "fx:id=\"openFileButton\" was not injected: check your FXML file 'view.fxml'.";
        assert fileCheckerLabel != null : "fx:id=\"fileCheckerLabel\" was not injected: check your FXML file 'view.fxml'.";
        assert sendFileButton != null : "fx:id=\"sendFileButton\" was not injected: check your FXML file 'view.fxml'.";
        assert sendingCheckerLabel != null : "fx:id=\"sendingCheckerLabel\" was not injected: check your FXML file 'view.fxml'.";

    }
}


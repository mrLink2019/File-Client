package application;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

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
    private ImageView refreshImage;

    @FXML
    void ChooseFile(ActionEvent event) throws IOException {
    	FileChooser fs = new FileChooser();      
        fs.getExtensionFilters().addAll(
        		new FileChooser.ExtensionFilter("Всі файли", "*.*"));
        choosedFile = fs.showOpenDialog(null);
     if(choosedFile.isFile()) {
    	 filePathLabel.setText(choosedFile.getAbsolutePath());
    	 fileCheckerLabel.setText("File successfully chosed!");
     } else {
    	 fileCheckerLabel.setText("Please choose file!");
     }
    }
    
    @FXML
    void SendFile() throws Exception {
    	//Server URL!!!
    	String urlToConnect = "http://...";

    	CloseableHttpClient httpclient = HttpClients.createDefault();
        try {
           HttpPost httppost = new HttpPost(urlToConnect);

           FileBody file = new FileBody(choosedFile);
           StringBody comment = new StringBody("A binary file of some kind", ContentType.TEXT_PLAIN);

           HttpEntity reqEntity = MultipartEntityBuilder.create()
              .addPart("file",file)
              .addPart("comment", comment)
              .build();


           httppost.setEntity(reqEntity);

           System.out.println("executing request " + httppost.getRequestLine());
           CloseableHttpResponse response = httpclient.execute(httppost);
         try {
              System.out.println("----------------------------------------");
              System.out.println(response.getStatusLine());
              HttpEntity resEntity = response.getEntity();
              if (resEntity != null) {
                   System.out.println("Response content length: " +    resEntity.getContentLength());
              }
            EntityUtils.consume(resEntity);
           } finally {
               response.close();
          }
     } finally {
        httpclient.close();
    }
    	/**URLConnection connection = new URL(urlToConnect).openConnection();
    	connection.setDoOutput(true); // This sets request method to POST.
    	connection.setRequestProperty("Content-Type", "multipart/form-data;");
    	PrintWriter writer = null;
    	try {
    	    writer = new PrintWriter(new OutputStreamWriter(connection.getOutputStream()));
    	    writer.println("Content-Disposition: form-data; name=\"file\"; filename=\""+ choosedFile.getName() +"\"");
    	    writer.println("Content-Type: file/" + choosedFile.getName().substring(choosedFile.getName().lastIndexOf(".")));
    	    writer.println();
    	    BufferedReader reader = null;
    	    try {
    	        reader = new BufferedReader( new InputStreamReader( new FileInputStream(choosedFile)));
    	        for (String line; (line = reader.readLine()) != null;) {
    	            writer.println(line);
    	        }
    	    } finally {
    	        if (reader != null) try { reader.close(); } catch (IOException logOrIgnore) {}
    	    }
    	} finally {
    	    if (writer != null) writer.close();
    	}

    	// Connection is lazily executed whenever you request any status.
    	int responseCode = ((HttpURLConnection) connection).getResponseCode();
    	
    	if(responseCode == 200) {
    		sendingCheckerLabel.setText("File successfully sent!");
    	}else {
    		sendingCheckerLabel.setText("Problems with connection!");
    		System.out.println(responseCode);
    	}**/
    }
    
    @FXML
    void RefreshFields() {
    	choosedFile = null;
    	filePathLabel.setText(" ");
    	fileCheckerLabel.setText(" ");
    	sendingCheckerLabel.setText(" ");
    }
    
    @FXML
    void initialize() {
    	assert refreshImage != null : "fx:id=\"filePathLabel\" was not injected: check your FXML file 'view.fxml'.";
        assert filePathLabel != null : "fx:id=\"filePathLabel\" was not injected: check your FXML file 'view.fxml'.";
        assert openFileButton != null : "fx:id=\"openFileButton\" was not injected: check your FXML file 'view.fxml'.";
        assert fileCheckerLabel != null : "fx:id=\"fileCheckerLabel\" was not injected: check your FXML file 'view.fxml'.";
        assert sendFileButton != null : "fx:id=\"sendFileButton\" was not injected: check your FXML file 'view.fxml'.";
        assert sendingCheckerLabel != null : "fx:id=\"sendingCheckerLabel\" was not injected: check your FXML file 'view.fxml'.";

    }
}


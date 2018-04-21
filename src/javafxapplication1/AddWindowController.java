package javafxapplication1;

import java.net.URL;
import java.util.Collections;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.fxml.FXML;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


public class AddWindowController{
    
    @FXML
    private TextField setTimeText;

    @FXML
    private AnchorPane mainPane;
    
    @FXML
    private Button addAlarmButton;
    
    public static ObservableList data;
   
    private ListView<String> list;
    public void setData(ListView<String> dataa) {
        this.list = dataa;

    }

    public boolean validateTime(String time)
    {
        Pattern pattern;
	Matcher matcher;
        String timePattern = "([01][0-9]|2[0-3]):[0-5][0-9]";
        pattern = Pattern.compile(timePattern);
        matcher = pattern.matcher(time);
	return matcher.matches();
	    	    
    }

    public void addAlarm()
    {
        if(validateTime(setTimeText.getText()) == true)
        {
            data = list.getItems();
            data.add(setTimeText.getText()); 
            Stage stage = (Stage) addAlarmButton.getScene().getWindow();
            stage.close();
            Collections.sort(data);
            list.setItems(data);

        }
        
        
    }
    
   
}

package javafxapplication1;

import javafx.animation.PauseTransition;
import java.io.IOException;
import java.util.Calendar;
import java.util.TimeZone;
import javafx.scene.control.ListView;
import javafx.application.Platform;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.util.Duration;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.io.File;
import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.scene.canvas.GraphicsContext;

public class Controller {

    @FXML
    private MenuItem closeButton;

    @FXML
    private MenuItem addButton;

    @FXML
    private MenuItem cleanButton;

    @FXML
    private Menu authorButton;

    @FXML
    private StackPane mainPane;

    @FXML
    private MenuItem deleteFromList;

    @FXML
    private Label authorLabel;

    @FXML
    private ListView<String> alarmsList;
    private boolean transition = false;
    private boolean flag = false;
    ClockBackground canvas = new ClockBackground();
    ClockHands canvas2 = new ClockHands();
    public void initialize() {

        alarmsList.getItems().addListener(new ListChangeListener() {
            @Override
            public void onChanged(ListChangeListener.Change change) {
                if (transition == false) {
                    setAlarm();
                }

            }
        });
        mainPane.getChildren().add(canvas);
        mainPane.getChildren().add(canvas2);

    }

    public void closeApp() {
        Platform.exit();
      //  System.exit(0);

    }

    public void newWindow() throws IOException {

        Stage neww = new Stage();

        Parent root;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AddWindow.fxml"));
            root = loader.load();
            AddWindowController controller = loader.getController();
            controller.setData(alarmsList);

        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        Scene sceneNewDate = new Scene(root);
        neww.setResizable(false);
        neww.setTitle("Dodaj alarm");
        neww.setScene(sceneNewDate);
        neww.show();
    }
    public void authorWindow() throws IOException {
        Stage author = new Stage();

        Parent root;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AuthorWindow.fxml"));
            root = loader.load();
            AuthorWindowController controller = loader.getController();

        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        Scene sceneNewDate = new Scene(root);
        author.setResizable(false);
        author.setTitle("Informacje o autorze");
        author.setScene(sceneNewDate);
        author.show();
    }
    public void beep() {
        String musicFile = "C:\\Users\\Ada\\Documents\\NetBeansProjects\\JavaFXApplication1\\discreet.mp3";
        Media sound = new Media(new File(musicFile).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(sound);
        mediaPlayer.play();
    }
    public void removeAlarm() {
        alarmsList.setEditable(true);
        int selectedIndex = alarmsList.getSelectionModel().getSelectedIndex();
        if (selectedIndex > 0) {
            alarmsList.getItems().remove(selectedIndex);
        }
        if(selectedIndex == 0)
        {
            transition = false;
            flag = true;
            alarmsList.getItems().remove(selectedIndex);
        }
    }
    public void removeAlarm(int a) {

        if (a >= 0 && alarmsList.getItems().size() > 0 && flag == false) {
            beep();
            alarmsList.setEditable(true);
            alarmsList.getItems().remove(a);
            alarmsList.setEditable(false);
        }
        flag = false;
    }
    public void cleanAlarms() {
        alarmsList.getItems().clear();
        transition = false;
    }
    public void setAlarm() {

        ObservableList alarms;
        alarms = alarmsList.getItems();
        if (alarms.size() > 0) {
            Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
            double currentTime = (double) (calendar.get(Calendar.HOUR_OF_DAY)) * 3600
                    + (double) (calendar.get(Calendar.MINUTE)) * 60 + (double) (calendar.get(Calendar.SECOND));
            String[] time = alarms.get(0).toString().split(":");
            double sleepTime = Integer.parseInt(time[0]) * 3600 + Integer.parseInt(time[1]) * 60 - currentTime;
            int i = (int)sleepTime;
            if(i < 0)
            {
                removeAlarm(0);
                return;
            }
            PauseTransition pause = new PauseTransition(Duration.seconds(sleepTime));
            transition = true;
            pause.setOnFinished(event -> {
                transition = false;
                removeAlarm(0);
            });pause.play();
        }
    }
}

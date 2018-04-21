/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication1;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.transform.Scale;

import javafx.stage.*;

public class JavaFXApplication1 extends Application {
    public void start(Stage stage) throws Exception {
        
        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        Platform.setImplicitExit(false);
        Scene scene = new Scene(root);
        stage.setTitle("Zegar, alarm...");
        stage.setScene(scene);
        stage.setMinWidth(600);
        stage.setMinHeight(450);
        stage.show();
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
        @Override
        public void handle(final WindowEvent event) {
            Platform.exit();
          //  System.exit(0);
        }});
    }
    
    
    public static void main(String[] args) {
      
        launch(args);
    }
    
    
}

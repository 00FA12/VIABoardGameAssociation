package GUI;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.Window;
import main.LoadInitialData;

import java.time.LocalDateTime;

/**
 * A class that starts the application.
 * @author Sevastian Bahynskyi
 * @version 1.0
 */
public class StartGUI extends Application
{
  public void start(Stage window) throws Exception
  {
    LoadInitialData.load();
    window.setTitle("VIA Board Games Association");
    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(getClass().getResource("/GUI/main.fxml"));
    Scene scene = new Scene(loader.load());
    window.setScene(scene);
    window.setResizable(false);
    window.getIcons().add(new Image("GUI/via-logo.jpg"));
    window.show();
  }
}

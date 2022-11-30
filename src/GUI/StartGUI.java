package GUI;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import main.LoadInitialData;

import java.io.IOException;

public class StartGUI extends Application
{
  public void start(Stage window) throws Exception
  {
    LoadInitialData.load(); //todo event.tab.fxml 
    window.setTitle("Association");
    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(getClass().getResource("/GUI/main.fxml"));
    Scene scene = new Scene(loader.load());
    window.setScene(scene);
    window.show();
  }
}

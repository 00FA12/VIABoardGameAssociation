package GUI;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.Window;
import main.LoadInitialData;

public class StartGUI extends Application
{
  public void start(Stage window) throws Exception
  {
    LoadInitialData.load(); //todo event.tab.fxml
    window.setTitle("VIA Board Games Association");
    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(getClass().getResource("/GUI/main.fxml"));
    Scene scene = new Scene(loader.load());
    window.setScene(scene);
    window.setResizable(false);
    window.show();
  }
}

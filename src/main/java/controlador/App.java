package controlador;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;


/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
    	Parent root = loadFXML("primary");
        scene = new Scene(root, root.prefWidth(0), root.prefHeight(0));
        stage.getIcons().add(new Image("https://cdn-icons-png.flaticon.com/512/49/49203.png"));
        stage.setTitle("Cronometro");
        stage.setScene(scene);
        stage.show();
        stage.setOnCloseRequest(event->{
          	try {
              	PrimaryController.cerrarHilo();
  			} catch (Exception e) {
  				System.out.println("");			
  			}
      	});
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }

}
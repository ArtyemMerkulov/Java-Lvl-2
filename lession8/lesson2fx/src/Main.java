import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader chatLoader = new FXMLLoader();
        chatLoader.setLocation(getClass().getResource("views/chat.fxml"));

        Parent root = chatLoader.load();

        primaryStage.setTitle("Very Ugly Chat");
        primaryStage.setResizable(false);
        primaryStage.setScene(new Scene(root, 720, 590));
        primaryStage.show();
        primaryStage.setOnCloseRequest(event -> {
            Controller c = chatLoader.getController();
            if (c.isAuthorized()) {
                c.sendMsg("/exitonclose");
                c.closeConnection();
            }
            primaryStage.close();
        });
    }

    public static void main(String[] args) {
        launch(args);
    }
}

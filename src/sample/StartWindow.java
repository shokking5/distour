package sample;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.scenicview.ScenicView;
import sample.Chat.Client;
import sample.Controllers.ControllersConst;

import java.io.IOException;

public class StartWindow extends Application {

    public static Stage primaryStage;
    private static double xOffset;
    private static double yOffset;
//    public static Scene currentScene;

    public static Stage getPrimaryStage() {
        return primaryStage;
    }

    @Override
    public void start(Stage primaryStage) {
//        Client.connect();
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.setResizable(false);
        StartWindow.primaryStage = primaryStage;
        create("fxmlFiles/Arkanoid.fxml");
//        ScenicView.show(getPrimaryStage().getScene());
    }

    @Override
    public void stop() {
//        Client.send("", 3);
        System.exit(0);
    }

    public static void create(String fxmlFile) {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(StartWindow.class.getResource(fxmlFile));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        switch (fxmlFile) {
            case "fxmlFiles/StartWindow.fxml":
                ControllersConst.startWindowController = loader.getController();
                break;
            case "fxmlFiles/Categories.fxml":
                ControllersConst.categoriesController = loader.getController();
                break;
            case "fxmlFiles/SignUpWindow.fxml":
                ControllersConst.signUpController = loader.getController();
                break;
        }
        Scene scene = new Scene(root, 1280, 720);
        setStageStyle(scene);
    }

    private static void setStageStyle(Scene scene) {
        scene.setOnMousePressed(event -> {
            xOffset = primaryStage.getX() - event.getScreenX();
            yOffset = primaryStage.getY() - event.getScreenY();
        });
        scene.setOnMouseDragged(event -> {
            primaryStage.setX(event.getScreenX() + xOffset);
            primaryStage.setY(event.getScreenY() + yOffset);
        });

        Platform.runLater(() -> {
            primaryStage.setScene(scene);
            primaryStage.setTitle("Distour");
            primaryStage.show();
        });

    }

    public static void main(String[] args) {
        launch(args);
    }
}
package ownBruce;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {

    /**
     * Obrazek Johna. W projekcie zaliczeniowym lepiej oczywiście trzymać obrazki
     * w jednym z folderów projektu.
     */
    Image bruce;

    /**
     * Współrzędna Johna McClane'a.
     */
    private int x = 0;

    /**
     * ...
     */
    private Canvas map;

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("MainStage.fxml"));
        Parent root = loader.load();
        MainStageController mainStageController = loader.getController();
        this.map = mainStageController.getMap();
        final GraphicsContext context = this.map.getGraphicsContext2D();
        this.bruce = new Image("http://storage.canoe.ca/v1/dynamic_resize/sws_path/suns-prod-images/1322778266171_ORIGINAL.jpg?quality=80&size=420x");

        primaryStage.setTitle("Wieżowiec");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();

        Thread renderer = new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(40);
                } catch (InterruptedException ex) {
                }

                Platform.runLater(() -> {
                    context.clearRect(0, 0, context.getCanvas().getWidth(), context.getCanvas().getHeight());
                    context.drawImage(bruce, (x += 2) % 800, 0);
                });
            }
        });

        renderer.setDaemon(true);
        renderer.start();
    }


    public static void main(String[] args) {
        launch(args);
    }
}

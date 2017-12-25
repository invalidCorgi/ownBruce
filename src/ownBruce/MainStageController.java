package ownBruce;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;

public class MainStageController {
    @FXML
    private Canvas map;

    public Canvas getMap(){
        return this.map;
    }
}

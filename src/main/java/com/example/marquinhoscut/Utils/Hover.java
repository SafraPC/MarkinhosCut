package com.example.marquinhoscut.Utils;

import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

public class Hover extends StackPane {
    public Hover(int priorValue, int value) {
        setPrefSize(15, 15);

        final Label label = createDataThresholdLabel(priorValue, value);

        setOnMouseEntered((EventHandler<javafx.scene.input.MouseEvent>) mouseEvent -> {
            getChildren().setAll(label);
            setCursor(Cursor.NONE);
            toFront();
        });
        setOnMouseExited((EventHandler<MouseEvent>) mouseEvent -> {
            getChildren().clear();
            setCursor(Cursor.CROSSHAIR);
        });
    }

    private Label createDataThresholdLabel(int priorValue, int value) {
        final Label label = new Label(value + "");
        label.getStyleClass().addAll("default-color0", "chart-line-symbol", "chart-series-line");
        label.setStyle("-fx-font-size: 20; -fx-font-weight: bold;");

        if (priorValue == 0) {
            label.setTextFill(Color.DARKGRAY);
        } else if (value > priorValue) {
            label.setTextFill(Color.FORESTGREEN);
        } else {
            label.setTextFill(Color.FIREBRICK);
        }

        label.setMinSize(Label.USE_PREF_SIZE, Label.USE_PREF_SIZE);
        return label;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TesylShape;

import Tools.Vector;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import tesylprototyping.TesylPoint;

/**
 *
 * @author Kolbe
 */
public class TesylShape {

    private Point point;
    private Bound bound;
    private Pane rootPane;

    private Timeline timeline;
    private KeyFrame keyframe;

    public TesylShape() {
        rootPane = new Pane();
        rootPane.setMinSize(500, 500);
        this.point = new Point();
        this.bound = new StaticBound(new Vector(100, 200), new Vector(rootPane.getMinWidth(), rootPane.getMinHeight()));
        rootPane.getChildren().add(point.getBody());
        rootPane.getChildren().add(bound.getBody());
        setupTimeline();
    }

    public Pane getPane() {
        return this.rootPane;
    }

    private void setupTimeline() {
        keyframe = new KeyFrame(Duration.millis(10), (ActionEvent event) -> {
            update();
        });
        timeline = new Timeline(keyframe);
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    private void update() {
        point.update();
        if(bound.checkBound(point))
            bound.applyBound(point);
    }
}

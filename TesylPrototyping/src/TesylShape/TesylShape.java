/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TesylShape;

import Tools.Vector;
import java.util.ArrayList;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

/**
 *
 * @author Kolbe
 */
public class TesylShape {

    private StackPane rootPane;
    private Timeline timeline;
    private KeyFrame keyframe;

    private ArrayList<Point> points;
    private ArrayList<Bound> bounds;
    
    private Face face;

    private final double POINT_COUNT = 4;
    private final double MIN_SPEED = 0; //Multiplier to speed - 1.0 equivilent 100% ;
    private final double MAX_SPEED = 1.0;

    public TesylShape() {
        rootPane = new StackPane();
        rootPane.setMinSize(500, 500);
        setup();
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
        face.update();
    }

    private void setup() {
        setupPoints();
//        setupBounds();
        face = new Face(points.get(0),points.get(1),points.get(2),points.get(3));
        rootPane.getChildren().add(face.face);
        setupTimeline();
    }

    private void setupPoints() {
        points = new ArrayList<>();
        for (int i = 0; i < POINT_COUNT; i++) {
            Point hold = new Point(i);
//            hold.setPosition(this.getRandomPositon());
//            rootPane.getChildren().add(hold.getBody());
            points.add(hold);
        }
    }

    protected Vector getRandomPositon() {
        return new Vector(getRandomX(), getRandomY());
    }

    private double getRandomX() {
        double width = rootPane.getWidth();
        double randomNum = (Math.random() * width);
        if (randomNum < 0 || randomNum > width) {
            randomNum = getRandomX();
        }
        return randomNum;
    }

    private double getRandomY() {
        double height = rootPane.getHeight();
        double randomNum = (Math.random() * height);
        if (randomNum < 0 || randomNum > height) {
            randomNum = getRandomY();
        }
        return randomNum;
    }

}

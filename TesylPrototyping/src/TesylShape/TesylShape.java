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
import javafx.util.Duration;
import tesylprototyping.TesylPoint;

/**
 *
 * @author Kolbe
 */
public class TesylShape {

    private Pane rootPane;
    private Timeline timeline;
    private KeyFrame keyframe;

    private ArrayList<Point> points;
    private ArrayList<Bound> bounds;

    private final double POINT_COUNT = 5;
    private final double MIN_SPEED = 0; //Multiplier to speed - 1.0 equivilent 100% ;
    private final double MAX_SPEED = 1.0;

    public TesylShape() {
        rootPane = new Pane();
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
        for (Point point : points) {
            point.update();
            for (Bound curr : bounds) {
                if (curr.checkBound(point)) {
                    curr.applyBound(point);
                }
            }
        }
    }

    private void setup() {
        setupPoints();
        setupBounds();
        setupTimeline();
    }

    private void setupPoints() {
        points = new ArrayList<>();
        for (int i = 0; i < POINT_COUNT; i++) {
            Point hold = new Point();
//            hold.setPosition(this.getRandomPositon());
            rootPane.getChildren().add(hold.getBody());
            points.add(hold);
        }
    }

    private void setupBounds() {
        bounds = new ArrayList<>();
        StaticBound top = new StaticBound(new Vector(0, 0), new Vector(500, 0));
        StaticBound right = new StaticBound(new Vector(500, 0), new Vector(500, 500));
        StaticBound bottom = new StaticBound(new Vector(500, 500), new Vector(0, 500));
        StaticBound left = new StaticBound(new Vector(0, 0), new Vector(0, 500));

        bounds.add(top);
        bounds.add(left);
        bounds.add(right);
        bounds.add(bottom);

        rootPane.getChildren().add(top.getBody());
        rootPane.getChildren().add(left.getBody());
        rootPane.getChildren().add(right.getBody());
        rootPane.getChildren().add(bottom.getBody());
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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tesylprototyping;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

/**
 *
 * @author Kolbe
 */
public class TesylShape {

    private Timeline timeline;
    private KeyFrame keyframe;
    private TesylPoint[] points;
    private TesylBound[] bounds;
    private AnchorPane vizPane;
    private final int pointCount = 10;
    private final double maxVelocity = 2.0, minVelocity = 0.5;

    public TesylShape() {
    }

    public TesylShape(AnchorPane pane) {
        vizPane = pane;
        initialize();
    }

    private void initialize() {
        points = new TesylPoint[pointCount];
        bounds = new TesylBound[10];
        for (int i = 0; i < pointCount; i++) {
            TesylPoint hold = new TesylPoint(this, i);
            hold.initialize_Random();
            points[i] = hold;
            vizPane.getChildren().addAll(hold.getBody());
        }
        StaticBound test = new StaticBound();
        test.setEndPoints(new Vector(0,150), new Vector(150,150));
        bounds[0] = test;
        vizPane.getChildren().add(test.bound);
        setupTimeline();
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
//        for (TesylBound curr : bounds) {
//            curr.updateState();
//        }
        for (TesylPoint point : points) {
//          for(TesylBound bound : bounds){
//              bound.checkIntersection(point);
//          }
          point.update();
        }
    }

    protected double getRandomX(TesylPoint a) {
        Double radius = a.getBody().getRadius();
        double width = vizPane.getPrefWidth();
        double randomNum = (Math.random() * width);
        if (randomNum < 0 + radius || randomNum > width - radius) {
            randomNum = getRandomX(a);
        }
        return randomNum;
    }

    protected double getRandomY(TesylPoint a) {
        Double radius = a.getBody().getRadius();
        double height = vizPane.getPrefHeight();
        double randomNum = (Math.random() * height);
        if (randomNum < 0 + radius || randomNum > height - radius) {
            randomNum = getRandomY(a);
        }
        return randomNum;
    }

    private Vector getRandomVelocity() {
        return new Vector(randomVelocity(), randomVelocity());
    }

    private double randomVelocity() {
        double done;
        double flip = Math.random() * 10 - 5;
        done = Math.random() * maxVelocity + minVelocity;
        if (flip <= 0) {
            done = -done;
        }
        return done;
    }

}

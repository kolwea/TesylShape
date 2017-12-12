/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TS2;

import Tools.Vector;
import java.util.ArrayList;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

/**
 *
 * @author Kolbe
 */
public class Shape {

    private Pane pane;
    private ArrayList<Face> faces;
    private ArrayList<StaticBound> bounds;
    private KeyFrame keyframe;
    private Timeline timeline;

    public Shape() {
        this.pane = new Pane();
        pane.setMinSize(500, 500);
        setupFaces();
        setupBounds();
        setupTimeline();

    }

    public Pane getPane() {
        return this.pane;
    }

    private void setupTimeline() {
        keyframe = new KeyFrame(Duration.millis(10), (ActionEvent event) -> {
            update();
        });
        timeline = new Timeline(keyframe);
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    private void setupFaces() {
        faces = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            Face hold = new Face(pane);
            faces.add(hold);
        }


        faces.get(0).connect(faces.get(1));
        faces.get(0).connect(faces.get(1));
        faces.get(0).connect(faces.get(1));
        faces.get(0).connect(faces.get(1));

//        faces.get(2).connect(faces.get(1));
//        faces.get(2).connect(faces.get(1));
//        faces.get(2).connect(faces.get(1));
//        faces.get(2).connect(faces.get(1));


    }

    private void setupBounds() {
        this.bounds = new ArrayList<>();
        StaticBound top = new StaticBound(new Vector(0, 0), new Vector(pane.getMinWidth(), 0));
        StaticBound right = new StaticBound(new Vector(pane.getMinWidth(), 0), new Vector(pane.getMinWidth(), pane.getMinHeight()));
        StaticBound bottom = new StaticBound(new Vector(pane.getMinWidth(), pane.getMinHeight()), new Vector(0, pane.getMinHeight()));
        StaticBound left = new StaticBound(new Vector(0, 0), new Vector(0, pane.getMinHeight()));

        bounds.add(top);
        bounds.add(left);
        bounds.add(right);
        bounds.add(bottom);
        pane.getChildren().add(top.getBody());
        pane.getChildren().add(bottom.getBody());
        pane.getChildren().add(left.getBody());
        pane.getChildren().add(right.getBody());
    }

    private void update() {

        for (Face curr : faces) {
            curr.update();
            for (StaticBound a : bounds) {
                if (a.checkBound(curr)) {
                    a.applyBound(curr);
                }
            }
        }
    }
}

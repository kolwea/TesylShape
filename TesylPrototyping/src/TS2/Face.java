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
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.util.Duration;

/**
 *
 * @author Kolbe
 */
public class Face {

    protected Pane pane;
    private Circle body;
    private Polygon face;
    private Vector position, velocity;
    private double radius;
    private ArrayList<Point> points;
    private double rotation;
    private KeyFrame keyframe;
    private Timeline timeline;
    int ike;
    double key;

    private final boolean SHOW_MODEL = false;
    private Line horz, vert;

    protected Face(Pane par) {
        this.pane = par;
        setup();
        ike = 0;
        key = 0;
    }

    protected Pane getPane() {
        return this.pane;
    }

    protected void update() {
        this.position = position.add(velocity);
        updatePoints();
        updateModel();
        updateShape();
        if (rotation > 359) {
            rotation = 0;
        }
        if (ike == 200) {
            key = Math.random() * 5 - 2.5;
            ike = 0;
        }
        ike++;
        rotation += key;
        if (rotation > 360) {
            rotation -= 360;
        } else if (rotation <= 0) {
            rotation += 360;
        }
        System.out.println(rotation);

    }

    private void setup() {
        this.position = new Vector(0, 0);
        this.radius = 50.0;
        this.rotation = 0;
        this.velocity = new Vector(0.5, 0.5);
        setupBody();
        setupPoints();
        setupTimeline();
        setupShape();
//        updatePoints();

    }

    private void setupPoints() {
        this.points = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            Point hold = new Point(i);
            points.add(hold);
            if (SHOW_MODEL) {
                pane.getChildren().add(hold.getLabel());
                pane.getChildren().add(hold.getBody());
            }
        }
    }

    private void setupBody() {
        this.body = new Circle();
        if (SHOW_MODEL) {
            body.setCenterX(position.x);
            body.setCenterY(position.y);
            body.setRadius(radius);
            body.setFill(Color.CADETBLUE);
            pane.getChildren().add(body);
        }

    }

    private void setupTimeline() {
        keyframe = new KeyFrame(Duration.millis(10), (ActionEvent event) -> {
            update();
        });
        timeline = new Timeline(keyframe);
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    private void setupShape() {
        face = new Polygon();
        face.getPoints().addAll(new Double[]{
            points.get(0).getBody().getCenterX(), points.get(0).getBody().getCenterY(),
            points.get(1).getBody().getCenterX(), points.get(1).getBody().getCenterY(),
            points.get(2).getBody().getCenterX(), points.get(2).getBody().getCenterY(),
            points.get(3).getBody().getCenterX(), points.get(3).getBody().getCenterY()
        });
        face.setOpacity(0.5);
        face.setFill(Color.rgb((150), (int) (Math.random() * 255), 150));
        pane.getChildren().add(face);
    }

    private void updateShape() {
        face.getPoints().setAll(new Double[]{
            points.get(0).getBody().getCenterX(), points.get(0).getBody().getCenterY(),
            points.get(1).getBody().getCenterX(), points.get(1).getBody().getCenterY(),
            points.get(2).getBody().getCenterX(), points.get(2).getBody().getCenterY(),
            points.get(3).getBody().getCenterX(), points.get(3).getBody().getCenterY()
        });
    }

    private void updatePoints() {
        double angle = rotation;
        for (int i = 0; i < 4; i++) {
            if (angle > 360) {
                angle -= 360;
            } else if (angle <= 0) {
                angle += 360;
            }
            Point curr = points.get(i);
            Vector pos = this.position.clone();
            Vector hold = Vector.angleToVector(angle);
            for (int k = 0; k < radius; k++) {
                pos = pos.add(hold);
            }
            curr.setPosition(pos);
            angle += 90;
        }
    }

    private void updateModel() {
        if (SHOW_MODEL) {
            this.body.setCenterX(position.x);
            this.body.setCenterY(position.y);
            for (int i = 0; i < 4; i++) {
                Point curr = points.get(i);
                Vector pos = curr.getPosition();
                curr.getLabel().setX(pos.x + 10);
                curr.getLabel().setY(pos.y - 10);
            }
        }

    }

}

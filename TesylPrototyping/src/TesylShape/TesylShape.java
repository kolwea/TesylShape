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

    private Face face1, face2, face3, face4, face5, face6;

    private final double POINT_COUNT = 8;
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
        face1.update();
        face2.update();
    }

    private void setup() {
        setupPoints();
        setupFaces();
        setupTimeline();
    }

    private void setupPoints() {
        points = new ArrayList<>();
        for (int i = 0; i < POINT_COUNT; i++) {
            Point hold = new Point(i);
//            hold.setPosition(this.getRandomPositon());
            rootPane.getChildren().add(hold.getBody());
            points.add(hold);
        }
        double minWidth = rootPane.getMinWidth(),minHeight = rootPane.getMinHeight();
        Vector zero = new Vector(minWidth/2 , minHeight/2 - minHeight/4);
        zero.printVector();
        Vector one = new Vector(zero.x - 100, zero.y + 100);
        Vector two = new Vector(zero.x, one.y + 100);
        Vector three = new Vector (zero.x + 100, one.y);
        Vector four = new Vector(zero.x - 50 ,rootPane.getMinHeight()/2 + 50);
        Vector five = new Vector(one.x + 50 , four.y + 50);
        Vector six = new Vector(five.x+50,five.y+50);
        Vector seven = new Vector(three.x + 50, four.y+50);

        points.get(0).setPosition(zero);
        points.get(1).setPosition(one);
        points.get(2).setPosition(two);
        points.get(3).setPosition(three);
        points.get(4).setPosition(four);
        points.get(5).setPosition(five);
        points.get(6).setPosition(six);
        points.get(7).setPosition(seven);

    }

    private void setupFaces() {
        face1 = new Face(points.get(0), points.get(1), points.get(2), points.get(3));
        face2 = new Face(points.get(0), points.get(1), points.get(4), points.get(5));
//        face3 = new Face(points.get(0), points.get(3), points.get(7), points.get(4));
//        face2 = new Face(points.get(0), points.get(1), points.get(4), points.get(5));
//        face1 = new Face(points.get(0), points.get(1), points.get(2), points.get(3));
//        face2 = new Face(points.get(0), points.get(1), points.get(4), points.get(5));

//        rootPane.getChildren().addAll(face1.face, face2.face);
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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TS2;

import java.util.ArrayList;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Polygon;

/**
 *
 * @author Kolbe
 */
public class Side {

    private ArrayList<Point> points;
    private Polygon side;
    private Pane pane;

    protected Side(Pane pane) {
        this.pane = pane;
        side = new Polygon();
        points = new ArrayList<>();
    }

    protected void update() {
        if (side != null) {
            side.getPoints().setAll(new Double[]{
                points.get(0).getBody().getCenterX(), points.get(0).getBody().getCenterY(),
                points.get(1).getBody().getCenterX(), points.get(1).getBody().getCenterY(),
                points.get(2).getBody().getCenterX(), points.get(2).getBody().getCenterY(),
                points.get(3).getBody().getCenterX(), points.get(3).getBody().getCenterY()
            });
        }
    }

    protected void addPoint(Point add) {
        points.add(add);
        if (points.size() == 4) {
            updateShape();
        }
    }

    protected void updateShape() {
        side = new Polygon();
        side.getPoints().addAll(new Double[]{
            points.get(0).getBody().getCenterX(), points.get(0).getBody().getCenterY(),
            points.get(1).getBody().getCenterX(), points.get(1).getBody().getCenterY(),
            points.get(2).getBody().getCenterX(), points.get(2).getBody().getCenterY(),
            points.get(3).getBody().getCenterX(), points.get(3).getBody().getCenterY()
        });
        side.setOpacity(0.5);
        side.setFill(Color.web("#F71735"));
        side.setStroke(Color.web("#000004"));
        side.setStrokeWidth(1.0);
        pane.getChildren().add(side);
//        side.toFront();
        System.out.println("Did it");
    }

}

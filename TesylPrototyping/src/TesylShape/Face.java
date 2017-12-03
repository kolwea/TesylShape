/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TesylShape;

import Tools.Vector;
import java.util.ArrayList;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Shape;

/**
 *
 * @author Kolbe
 */
public class Face {

    protected Pane face;
    protected Polygon body;
    private ArrayList<Bound> bounds;
    private Point[] points;

    protected Face(Point once, Point dos, Point tres, Point quat) {
        face = new Pane();
        face.setMinSize(500, 500);
        this.face = face;
        setup(once, dos, tres, quat);
    }

    protected void update() {
        for (int i = 0; i < 4; i++) {
            for (Bound curr : bounds) {
                curr.update();
                if (curr.checkBound(points[i])) {
                    curr.applyBound(points[i]);
                }
            }
//            points[i].update();
            updateShape();
        }
    }

    protected void setup(Point o, Point d, Point t, Point q) {
        points = new Point[]{o, d, t, q};
//        face.getChildren().addAll(points[0].getBody(), points[1].getBody(), points[2].getBody(), points[3].getBody());
        setupBounds();
        setupShape();
    }

//    private void setupPoints() {
//        points[0].setPosition(new Vector(face.getMinWidth() / 2 - 100, face.getMinHeight() / 2 - 100));
//        Vector pos0 = points[0].getPosition();
//        points[1].setPosition(new Vector(pos0.x - 50, pos0.y + 50));
//
//        points[2].setPosition(new Vector(pos0.x + face.getMinHeight()/2, pos0.y + face.getMinWidth()/2));
//        Vector pos2 = points[2].getPosition();
//        points[3].setPosition(new Vector(pos2.x + 50, pos2.y - 50));
//
//        points[0].getBody().setFill(Paint.valueOf("RED"));
//        points[2].getBody().setFill(Paint.valueOf("RED"));
//
//    }

    private void setupBounds() {
        bounds = new ArrayList<>();

        StaticBound top = new StaticBound(new Vector(0, 0), new Vector(face.getMinWidth(), 0));
        StaticBound right = new StaticBound(new Vector(face.getMinWidth(), 0), new Vector(face.getMinWidth(), face.getMinHeight()));
        StaticBound bottom = new StaticBound(new Vector(face.getMinWidth(), face.getMinHeight()), new Vector(0, face.getMinHeight()));
        StaticBound left = new StaticBound(new Vector(0, 0), new Vector(0, face.getMinHeight()));

        bounds.add(top);
        bounds.add(left);
        bounds.add(right);
        bounds.add(bottom);

        StaticBound vert = new StaticBound(new Vector(face.getMinWidth() / 2, 0), new Vector(face.getMinWidth() / 2, face.getMinHeight()));
        StaticBound horz = new StaticBound(new Vector(0, face.getMinHeight() / 2), new Vector(face.getMinWidth(), face.getMinHeight() / 2));

        horz.getBody().setStroke(Paint.valueOf("RED"));

        bounds.add(vert);
        bounds.add(horz);

        vert.ignore(points[0]);
        vert.ignore(points[2]);
        horz.ignore(points[1]);
        horz.ignore(points[3]);

        DynamicBound one = new DynamicBound(points[0], points[2], Orientation.VERTICAL, 1);
        DynamicBound two = new DynamicBound(points[0], points[2], Orientation.VERTICAL, 0);

        DynamicBound three = new DynamicBound(points[1], points[3], Orientation.HORIZONTAL, 1);
        DynamicBound four = new DynamicBound(points[1], points[3], Orientation.HORIZONTAL, 0);

        bounds.add(one);
        bounds.add(two);
        bounds.add(three);
        bounds.add(four);

//        face.getChildren().add(top.getBody());
//        face.getChildren().add(bottom.getBody());
//        face.getChildren().add(left.getBody());
//        face.getChildren().add(right.getBody());
//        face.getChildren().add(horz.getBody());
//        face.getChildren().add(vert.getBody());
    }

    private void setupShape() {
        body = new Polygon();
        body.getPoints().addAll(new Double[]{
            points[0].getBody().getCenterX(), points[0].getBody().getCenterY(),
            points[1].getBody().getCenterX(), points[1].getBody().getCenterY(),
            points[2].getBody().getCenterX(), points[2].getBody().getCenterY(),
            points[3].getBody().getCenterX(), points[3].getBody().getCenterY()
        });
        body.setOpacity(0.5);
        body.setFill(Color.rgb((int) (Math.random() * 255), (int) (Math.random() * 255), (int) (Math.random() * 255)));
        face.getChildren().add(body);
    }

    private void updateShape() {
        body.getPoints().setAll(new Double[]{
            points[0].getBody().getCenterX(), points[0].getBody().getCenterY(),
            points[1].getBody().getCenterX(), points[1].getBody().getCenterY(),
            points[2].getBody().getCenterX(), points[2].getBody().getCenterY(),
            points[3].getBody().getCenterX(), points[3].getBody().getCenterY()
        });
    }

}

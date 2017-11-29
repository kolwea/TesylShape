/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TesylShape;

import Tools.Vector;
import java.awt.Color;
import java.util.ArrayList;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;

/**
 *
 * @author Kolbe
 */
public class Face {

    protected Pane face;
    private ArrayList<Bound> bounds;
    private ArrayList<Point> points;

    protected Face() {
        face = new Pane();
        face.setMinSize(500, 500);
        setupPoints();
        setupBounds();
    }

    protected void update() {
        for (Point point : points) {
            for (Bound curr : bounds) {
                if (curr.checkBound(point)) {
                    curr.applyBound(point);
                }
            }
            point.update();
        }
    }

    private void setupPoints() {
        points = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            Point hold = new Point();
            face.getChildren().add(hold.getBody());
            points.add(hold);
        }
        points.get(0).setPosition(new Vector(face.getMinWidth()/2 - 100, face.getMinHeight()/2 - 100));
        points.get(1).setPosition(new Vector(face.getMinWidth() / 3 + 5, face.getMinHeight() / 3));
        points.get(2).setPosition(new Vector(face.getMinWidth()/2 + 100, face.getMinHeight()/2 + 100));
        points.get(3).setPosition(new Vector(face.getMinHeight() / 3 * 2,0));

        points.get(0).getBody().setFill(Paint.valueOf("RED"));
        points.get(2).getBody().setFill(Paint.valueOf("RED"));

    }

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

//        vert.ignore(points.get(0));
//        vert.ignore(points.get(2));
//        horz.ignore(points.get(1));
//        horz.ignore(points.get(3));
        horz.getBody().setStroke(Paint.valueOf("RED"));

        bounds.add(vert);
        bounds.add(horz);

        DynamicBound one = new DynamicBound(points.get(0), points.get(2), Orientation.VERTICAL);
        DynamicBound two = new DynamicBound(points.get(0), points.get(2), Orientation.HORIZONTAL);

        DynamicBound three = new DynamicBound(points.get(1), points.get(3), Orientation.VERTICAL);
        DynamicBound four = new DynamicBound(points.get(1), points.get(3), Orientation.HORIZONTAL);

        DynamicBound onet = new DynamicBound(points.get(2), points.get(0), Orientation.HORIZONTAL);
        DynamicBound twot = new DynamicBound(points.get(2), points.get(0), Orientation.VERTICAL);

        DynamicBound threet = new DynamicBound(points.get(3), points.get(1), Orientation.HORIZONTAL);
        DynamicBound fourt = new DynamicBound(points.get(3), points.get(1), Orientation.VERTICAL);
//        DynamicBound two = new DynamicBound(points.get(1),Orientation.VERTICAL);
//        DynamicBound three = new DynamicBound(points.get(2),Orientation.HORIZONTAL);
//        DynamicBound four = new DynamicBound(points.get(3),Orientation.VERTICAL);
        bounds.add(one);
        bounds.add(two);
        bounds.add(three);
        bounds.add(four);
        bounds.add(onet);
        bounds.add(twot);
        bounds.add(threet);
        bounds.add(fourt);

//        one.ignore(points.get(2));
//        three.ignore(points.get(0));
//        two.ignore(points.get(3));
//        four.ignore(points.get(1));
        face.getChildren().add(top.getBody());
        face.getChildren().add(bottom.getBody());
        face.getChildren().add(left.getBody());
        face.getChildren().add(right.getBody());
        face.getChildren().add(horz.getBody());
        face.getChildren().add(vert.getBody());
    }

}

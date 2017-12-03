/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TS2;

import Tools.Vector;
import java.util.ArrayList;
import javafx.beans.binding.Bindings;
import javafx.geometry.Bounds;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;

/**
 *
 * @author Kolbe
 */
public class Face {

    protected Pane pane;
    protected Circle body;
    private Polygon face;
    private Vector position;
    private double radius, add;
    protected ArrayList<Point> points;
    private double rotation, velocity;
    protected int connectIndex;

    int ike;
    double key;

    private final boolean SHOW_MODEL = true;
    private Line horz, vert;

    protected Face(Pane par) {
        this.pane = par;
        setup();
        ike = 0;
        key = 0;
        connectIndex = 0;
    }

    protected Pane getPane() {
        return this.pane;
    }

    protected double getVelcity() {
        return this.velocity;
    }

    protected void setVelocity(double angle) {
        this.velocity = angle;
    }

    protected void update() {
        this.position = position.add(Vector.angleToVector(velocity));
        updateRadius();
        updatePoints();
        updateModel();
        updateShape();
        if (ike % 100 == 0) {
            key = Math.random() * 3 - 1.5;
        }
        rotation = noramlize(rotation);
        rotation += key;
        ike++;
//        System.out.println(rotation);
    }

    private void setup() {
        this.position = new Vector(100, 100);
        this.radius = 50.0;
        this.rotation = 0;
        this.velocity = 90 + 13;
        setupBody();
        setupPoints();
        setupShape();
//        updatePoints();

    }

    private void setupPoints() {
        this.points = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            Point hold = new Point(i);
            points.add(hold);
//            pane.getChildren().add(hold.getLabel());
            pane.getChildren().add(hold.getBody());
//            hold.getLabel().setText("");
            hold.getBody().toFront();

        }
    }

    private void setupBody() {
        this.body = new Circle();
//        if (SHOW_MODEL) {
            body.setCenterX(position.x);
            body.setCenterY(position.y);
            body.setRadius(radius);
            body.setFill(Color.CADETBLUE);
            body.setOpacity(0.4);
            pane.getChildren().add(body);
//        }

    }

    private void setupShape() {
        face = new Polygon();
        face.getPoints().addAll(new Double[]{
            points.get(0).getBody().getCenterX(), points.get(0).getBody().getCenterY(),
            points.get(1).getBody().getCenterX(), points.get(1).getBody().getCenterY(),
            points.get(2).getBody().getCenterX(), points.get(2).getBody().getCenterY(),
            points.get(3).getBody().getCenterX(), points.get(3).getBody().getCenterY()
        });
        face.setOpacity(0.0);
        face.setFill(Color.rgb((150), (int) (Math.random() * 255), 150));
        pane.getChildren().add(face);
        face.toFront();
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
                curr.getLabel().setText(Integer.toString(curr.index));
                curr.getLabel().setX(pos.x + 10);
                curr.getLabel().setY(pos.y - 10);
            }
        } else {
            for (int i = 0; i < 4; i++) {
                Point curr = points.get(i);
                curr.getLabel().setText("");
            }
        }
    }

    protected void connect(Face face) {
        int other = connectIndex + 1, faceOther = face.connectIndex+1;
        if (other > 3) {
            other = 0;
        }
        if (faceOther > 3) {
            faceOther = 0;
        }
        updateBind(points.get(connectIndex), points.get(other));
        updateBind(face.points.get(connectIndex), face.points.get(faceOther));
        updateBind(points.get(connectIndex), face.points.get(face.connectIndex));
        updateBind(points.get(other), face.points.get(faceOther));
        connectIndex++;
        face.connectIndex++;
        if (connectIndex > 3) {
            connectIndex = 0;
        }
        if (face.connectIndex > 3) {
            face.connectIndex = 0;
        }
    }

    private void updateBind(Point a, Point c) {
        Pane parent = pane;
        Line body = new Line();
        body.setStrokeWidth(1.0);
        body.startXProperty().bind(Bindings.createDoubleBinding(() -> {
            Bounds b = a.getBody().getBoundsInParent();
            return b.getMinX() + b.getWidth() / 2;
        }, a.getBody().boundsInParentProperty()));
        body.endXProperty().bind(Bindings.createDoubleBinding(() -> {
            Bounds b = c.getBody().getBoundsInParent();
            return b.getMinX() + b.getWidth() / 2;
        }, c.getBody().boundsInParentProperty()));
        body.startYProperty().bind(Bindings.createDoubleBinding(() -> {
            Bounds b = a.getBody().getBoundsInParent();
            return b.getMinY() + b.getWidth() / 2;
        }, a.getBody().boundsInParentProperty()));
        body.endYProperty().bind(Bindings.createDoubleBinding(() -> {
            Bounds b = c.getBody().getBoundsInParent();
            return b.getMinY() + b.getWidth() / 2;
        }, c.getBody().boundsInParentProperty()));
        parent.getChildren().add(body);
        body.toFront();
    }

    private void updateRadius() {
        if (ike % 50 == 0) {
            if (ike == 300) {
                ike = 0;
            }
            add = Math.random() - 0.5;
        }
        radius += add;
        if (radius <= 10) {
            add *= -1;
        } else if (radius >= 100) {
            add *= -1;
        }
        body.setRadius(radius);
    }

    private double noramlize(double angle) {
        double done = angle;
        if (angle > 360) {
            done = angle - 360;
        }
        if (angle <= 0) {
            done = angle + 360;
        }
        return done;
    }

    private void addShape(Point a, Point b, Point c, Point d) {
        Polygon hold = new Polygon();
        hold.getPoints().addAll(new Double[]{
            a.getBody().getCenterX(), a.getBody().getCenterY(),
            b.getBody().getCenterX(), b.getBody().getCenterY(),
            b.getBody().getCenterX(), b.getBody().getCenterY(),
            b.getBody().getCenterX(), b.getBody().getCenterY()
        });
        face.setOpacity(0.0);
        face.setFill(Color.rgb((150), (int) (Math.random() * 255), 150));
        pane.getChildren().add(face);
        face.toFront();
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tesylprototyping;

import javafx.beans.binding.Bindings;
import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

/**
 *
 * @author Kolbe
 */
public class TesylPoint {

    //Initialized Variables////////////////////////////////
    private int index;
    private TesylShape parent;

    //Dynamic Variables////////////////////////////////////
    private Vector position, velocity;
    private double velocityAngle, acceleration, lastAngle;

    //Static Variables////////////////////////////////////
    private static double maxVelocity, minVelocity;

    //Visual Body Variables///////////////////////////////
    private Circle body;
    private double bodyWidth, strokeWidth;
    private Color bodyColor;

    //Default Values/////////////////////////////////////
    private double dBodyWidth = 10.0;
    private double dStrokeWidth = 3.0;
    private double dAcceleration = 1.0;
    private Color dColor = Color.CORAL;

    public TesylPoint(TesylShape pap, int index) {
        this.parent = pap;
        initialize(index);
    }
/////////////////////////////////////////////INITIALIZATION FUNCTIONS//////////////////////////////////////////////

    private void initialize(int index) {
        this.index = index;
        setupDefaultValues();
        setupBody();
        setupVelocity();
    }

    private void setupDefaultValues() {
        bodyWidth = dBodyWidth;
        strokeWidth = dStrokeWidth;
        bodyColor = dColor;
        acceleration = dAcceleration;
    }

    private void setupBody() {
        body = new Circle();
        body.setFill(bodyColor);
        body.setRadius(bodyWidth);
        position = new Vector(150.0, 150.0);
        body.setCenterX(position.x);
        body.setCenterY(position.y);
    }

    private void updateBody() {
        body.setFill(bodyColor);
        body.setRadius(bodyWidth);
        body.setCenterX(position.x);
        body.setCenterY(position.y);
    }

    private void setupVelocity() {
        velocity = new Vector(1.0, 1.0);
    }

////////////////////////////////////////////////CLASS FUNCTIONS/////////////////////////////////////////////////////
    protected Circle getBody() {
        return this.body;
    }

    protected Vector getPosition() {
        return this.position;
    }

    protected int getIndex() {
        return this.index;
    }

    protected double getAngle() {
        return this.velocityAngle;
    }

    protected void setAngle(double that) {
        this.velocityAngle = that;
    }

    protected void initialize_Random() {
        setRandomPosition();
        setRandomVelocity();
    }

    protected void setRandomPosition() {
        position = new Vector(parent.getRandomX(this), parent.getRandomY(this));
    }

    protected void setRandomVelocity() {
        velocityAngle = Math.random() * 360;
        System.out.println("Angle: " + velocityAngle);

    }

    protected void update() {
        if (velocityAngle != lastAngle) {
            velocity = new Vector(getAngleVelocityX(velocityAngle), getAngleVelocityY(velocityAngle));
            System.out.println("Index " + index + " changed from " + lastAngle + " to " + velocityAngle);
            lastAngle = velocityAngle;
        }
        position = position.add(velocity);
        this.updateBody();
    }

    protected void connect(TesylPoint connie) {
        Node n2 = connie.getBody();
        if (this.body.getParent() == null) {
            System.out.println("Tis null");
        } else {
            Pane parent = (Pane) this.body.getParent();
            Line line = new Line();
            line.setStrokeWidth(strokeWidth);
            line.startXProperty().bind(Bindings.createDoubleBinding(() -> {
                Bounds b = this.body.getBoundsInParent();
                return b.getMinX() + b.getWidth() / 2;
            }, this.body.boundsInParentProperty()));
            line.startYProperty().bind(Bindings.createDoubleBinding(() -> {
                Bounds b = this.body.getBoundsInParent();
                return b.getMinY() + b.getHeight() / 2;
            }, this.body.boundsInParentProperty()));
            line.endXProperty().bind(Bindings.createDoubleBinding(() -> {
                Bounds b = n2.getBoundsInParent();
                return b.getMinX() + b.getWidth() / 2;
            }, n2.boundsInParentProperty()));
            line.endYProperty().bind(Bindings.createDoubleBinding(() -> {
                Bounds b = n2.getBoundsInParent();
                return b.getMinY() + b.getHeight() / 2;
            }, n2.boundsInParentProperty()));
            parent.getChildren().add(line);
            this.getBody().toFront();
            n2.toFront();
            this.body.toFront();
        }
    }

////////////////////////////////////////////////HELPER FUNCTIONS/////////////////////////////////////////////////////  
//    private Vector getRandomVelocity() {
//        Vector velo = new Vector(getAngleVelocityX(angle),getAngleVelocityY(angle));
//        return velo;
//    }
    private double getAngleVelocityX(double x) {
        double angle;
        if (x > 180) {
            angle = mapXOver(x);
        } else {
            angle = x;
        }
        double inMin = 0, inMax = 180, outMin = 1.000, outMax = -1.000;
        double done = (double) (outMin + ((outMax - outMin) / (inMax - inMin)) * (angle - inMin));
        return done;
    }

    private double mapXOver(double x) {
        double inMin = 180, inMax = 360, outMin = 180, outMax = 0;
        double done = (double) (outMin + ((outMax - outMin) / (inMax - inMin)) * (x - inMin));
        return done;
    }

    private double getAngleVelocityY(double x) {
        double angle;
        if (x > 270) {
            angle = mapYHigh(x);
        } else if (x < 90) {
            angle = mapYLow(x);
        } else {
            angle = x;
        }
        double inMin = 90, inMax = 270, outMin = -1.000, outMax = 1.000;
        double done = (double) (outMin + ((outMax - outMin) / (inMax - inMin)) * (angle - inMin));
        return done;
    }

    private double mapYLow(double x) {
        double inMin = 0, inMax = 90, outMin = 180, outMax = 90;
        double done = (double) (outMin + ((outMax - outMin) / (inMax - inMin)) * (x - inMin));
        return done;
    }

    private double mapYHigh(double x) {
        double inMin = 270, inMax = 360, outMin = 270, outMax = 180;
        double done = (double) (outMin + ((outMax - outMin) / (inMax - inMin)) * (x - inMin));
        return done;
    }

}

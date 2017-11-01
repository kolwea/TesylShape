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
    private Vector pos;
    private Vector velocity;
<<<<<<< HEAD
    private final Circle body;
    private final TesylPoint[] neighbors;
    private final int index;
    private final double bodyWidth = 10.0;
//    private static final int maxDist = 10;
    private TesylShape shape;

    
    public TesylPoint(int index){
        this.index = index;
        body = new Circle();
        body.setFill(Color.CORAL);
        body.setRadius(bodyWidth);
        neighbors = new TesylPoint[2];
        velocity = new Vector(1.0, 1.0);
=======
    private Circle body;
    private int index;
    private double bodyWidth;
    private double strokeWidth;
    private Color bodyColor;    
    private double maxVelocity, minVelocity;

    

    public TesylPoint(int index){
        initialize(index);
    }
/////////////////////////////////////////////INITIALIZATION FUNCTIONS//////////////////////////////////////////////////
    private void initialize(int index){
        this.index = index;
        setupInitialValues();
        setupBody();
        setupVelocity();
    }
    
    private void setupInitialValues(){
        bodyWidth = 10.0;
        strokeWidth = 3.0;
        bodyColor = Color.CORAL;
        maxVelocity = 2.0;
        minVelocity = 0.5;
    }
    
    private void setupBody(){        
        body = new Circle();
        body.setFill(bodyColor);
        body.setRadius(bodyWidth);
>>>>>>> origin/master
        pos = new Vector(150.0,150.0);
        body.setCenterX(pos.x);
        body.setCenterY(pos.y);
    }
    
<<<<<<< HEAD
=======
    private void setupVelocity(){
        velocity = new Vector(1.0, 1.0);
    }
    
////////////////////////////////////////////////CLASS FUNCTIONS/////////////////////////////////////////////////////
    
>>>>>>> origin/master
    protected Vector getPosition(){
        return this.pos;
    }
        
<<<<<<< HEAD
    protected int getIndex(){
        return this.index;
    }
    
=======
    protected void setPosition(Vector pos){
        this.pos = pos;
    }
            
>>>>>>> origin/master
    protected Circle getBody(){
        return this.body;
    }
    
<<<<<<< HEAD
    protected void setPosition(Vector pos){
        this.pos = pos;
=======
    protected void setBody(Circle bod){
        this.body = bod;
>>>>>>> origin/master
    }
    
    protected void setVelocity(Vector velo){
        this.velocity = velo;
    }
    
<<<<<<< HEAD
    protected void setNeighbors(TesylPoint a, TesylPoint b){
        neighbors[0] = a;
        neighbors[1] = b;
    }
    
    protected void update(){
//        Vector neighborA = neighbors[0].getPosition();
//        Vector neighborB = neighbors[1].getPosition();
        this.getInBound();
        pos = pos.add(velocity);
        body.setCenterX(pos.x);
        body.setCenterY(pos.y);        
//        System.out.println(pos.x + " " + pos.y);
    }
    
    protected void setShape(TesylShape shape){
        this.shape = shape;
    }
    
//    private boolean inBounds(){
//        Vector a = neighbors[0].getPosition();
//        Vector b = neighbors[1].getPosition();
//        Double distA = Math.sqrt(Math.pow(pos.x - a.x, 2.0) + Math.pow(pos.y - a.y, 2));        
//        Double distB = Math.sqrt(Math.pow(pos.x - b.x, 2.0) + Math.pow(pos.y - b.y, 2));
//
//    }
    
    private void getInBound(){
        Vector bounds = this.shape.getBounds();
        if(this.pos.x+body.getRadius() > bounds.x)
            velocity.x = -velocity.x;
        if(this.pos.y+body.getRadius() > bounds.y)
            velocity.y = velocity.y*-1;
        if(this.pos.x-body.getRadius() < 0)
            velocity.x = -velocity.x;
        if(this.pos.y-body.getRadius() < 0)
            velocity.y = velocity.y*-1;
    }
    
    //This is a lot of online code but theres just no way I could've gotten here without basically all of it...         
//Thanks James_D of StackOverflow (T_T)
    protected void connect(Node n2){
=======
    protected Vector getVelocity(){
        return this.velocity;
    }
        
    protected void setRandomPosition(Pane pane){
        pos =  new Vector(this.getRandomX(pane),this.getRandomY(pane));
    }
        
    protected void setRandomVelocity(){
        velocity = new Vector(randomVelocity(),randomVelocity());
    }
    
    protected void update(Vector bounds){
        getInBound(bounds);
        pos = pos.add(velocity);
        body.setCenterX(pos.x);
        body.setCenterY(pos.y);        
    }
        
    protected void connect(TesylPoint connie){
        Node n2 = connie.getBody();
>>>>>>> origin/master
        if(this.body.getParent() == null){
            System.out.println("Tis null");
        }
        else{
            Pane parent = (Pane) this.body.getParent();
            Line line = new Line();
<<<<<<< HEAD
            line.setStrokeWidth(3.0);
=======
            line.setStrokeWidth(strokeWidth);
>>>>>>> origin/master
            line.startXProperty().bind(Bindings.createDoubleBinding(() -> {
                Bounds b = this.body.getBoundsInParent();
                return b.getMinX() + b.getWidth() / 2 ;
            }, this.body.boundsInParentProperty()));
            line.startYProperty().bind(Bindings.createDoubleBinding(() -> {
                Bounds b = this.body.getBoundsInParent();
                return b.getMinY() + b.getHeight() / 2 ;
            }, this.body.boundsInParentProperty()));
            line.endXProperty().bind(Bindings.createDoubleBinding(() -> {
                Bounds b = n2.getBoundsInParent();
                return b.getMinX() + b.getWidth() / 2 ;
            }, n2.boundsInParentProperty()));
            line.endYProperty().bind(Bindings.createDoubleBinding(() -> {
                Bounds b = n2.getBoundsInParent();
                return b.getMinY() + b.getHeight() / 2 ;
            }, n2.boundsInParentProperty()));
            parent.getChildren().add(line);
            this.getBody().toFront();
            n2.toFront();
            this.body.toFront();

        }
    }
    
<<<<<<< HEAD
=======
////////////////////////////////////////////////HELPER FUNCTIONS//////////////////////////////////////////////////////////   
    private void getInBound(Vector bounds){
        if(this.pos.x+body.getRadius() > bounds.x)
            velocity.x = -velocity.x;
        if(this.pos.y+body.getRadius() > bounds.y)
            velocity.y = velocity.y*-1;
        if(this.pos.x-body.getRadius() < 0)
            velocity.x = -velocity.x;
        if(this.pos.y-body.getRadius() < 0)
            velocity.y = velocity.y*-1;
    }
    
    private double getRandomX(Pane vizPane){
        Double radius = body.getRadius();
        double width = vizPane.getPrefWidth();
        double randomNum = (Math.random() * width);
        if(randomNum < 0 + radius || randomNum > width - radius){
            randomNum = getRandomX(vizPane);
        }
        return randomNum;
    }
       
    private double getRandomY(Pane vizPane){
        Double radius = body.getRadius();
        double height = vizPane.getPrefHeight();
        double randomNum = (Math.random() * height);
        if(randomNum < 0 + radius || randomNum > height - radius){
            randomNum = getRandomY(vizPane);
        }
        return randomNum;
    }
    
    private double randomVelocity(){
        double done;
        double flip = Math.random()*10 - 5;
        done = Math.random() * maxVelocity + minVelocity;
        if(flip <= 0)
            done = -done;
        return done;
    }
    
    
    
    
>>>>>>> origin/master

}

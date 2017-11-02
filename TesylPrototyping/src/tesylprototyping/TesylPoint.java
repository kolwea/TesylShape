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
        maxVelocity = 1.0;
        minVelocity = 1.0;
    }
    
    private void setupBody(){        
        body = new Circle();
        body.setFill(bodyColor);
        body.setRadius(bodyWidth);
        pos = new Vector(150.0,150.0);
        body.setCenterX(pos.x);
        body.setCenterY(pos.y);
    }
    
    private void setupVelocity(){
        velocity = new Vector(1.0, 1.0);
    }
    
////////////////////////////////////////////////CLASS FUNCTIONS/////////////////////////////////////////////////////
    
    protected Vector getPosition(){
        return this.pos;
    }
        
    protected void setPosition(Vector pos){
        this.pos = pos;
    }
            
    protected Circle getBody(){
        return this.body;
    }
    
    protected void setBody(Circle bod){
        this.body = bod;
    }
    
    protected void setVelocity(Vector velo){
        this.velocity = velo;
    }
    
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
        if(this.body.getParent() == null){
            System.out.println("Tis null");
        }
        else{
            Pane parent = (Pane) this.body.getParent();
            Line line = new Line();
            line.setStrokeWidth(strokeWidth);
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
    
    protected void reverseVelocity_X(){
        velocity.x = -velocity.x;
    }
        
    protected void reverseVelocity_Y(){
        velocity.y = -velocity.y;
    }
    
////////////////////////////////////////////////HELPER FUNCTIONS//////////////////////////////////////////////////////////   
    private void getInBound(Vector bounds){
        if(this.pos.x+body.getRadius() > bounds.x)
            this.reverseVelocity_X();
        if(this.pos.y+body.getRadius() > bounds.y)
            this.reverseVelocity_Y();
        if(this.pos.x-body.getRadius() < 0)
            this.reverseVelocity_X();
        if(this.pos.y-body.getRadius() < 0)
            this.reverseVelocity_Y();
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
    
    
    
    
    
    

}

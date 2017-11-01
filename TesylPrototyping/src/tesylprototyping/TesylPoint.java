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
        pos = new Vector(150.0,150.0);
        body.setCenterX(pos.x);
        body.setCenterY(pos.y);
    }
    
    protected Vector getPosition(){
        return this.pos;
    }
        
    protected int getIndex(){
        return this.index;
    }
    
    protected Circle getBody(){
        return this.body;
    }
    
    protected void setPosition(Vector pos){
        this.pos = pos;
    }
    
    protected void setVelocity(Vector velo){
        this.velocity = velo;
    }
    
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
        if(this.body.getParent() == null){
            System.out.println("Tis null");
        }
        else{
            Pane parent = (Pane) this.body.getParent();
            Line line = new Line();
            line.setStrokeWidth(3.0);
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
    

}

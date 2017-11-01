/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tesylprototyping;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

/**
 *
 * @author Kolbe
 */
public class TesylShapeV_02 {
    private TesylPoint[] points;
    private TesylBound[] bounds;
    private TesylFace[] faces;
    private Pane vizPane;
    private Vector position;
    private final int tesylCount = 4;
    
    private KeyFrame keyframe;
    private Timeline timeline;
    
    public TesylShapeV_02(Pane pane){
      this.vizPane = pane;
    }
    
    /////////////////////////////////////////////INITIALIZATION FUNCTIONS////////////////////////////////////////////////    
    private void setupShape(){
        setupPoints();
        setupTimeline();
    }  
    
    private void setupPoints(){
        points = new TesylPoint[tesylCount];
        int i = 0;
        for(TesylPoint a : points){
            a = new TesylPoint(i);
            points[i] = a;
            a.setRandomPosition(vizPane);
            a.setRandomVelocity();
            vizPane.getChildren().add(a.getBody());
            i++;
        }
    }
    
    private void setupTimeline(){        
        keyframe = new KeyFrame(Duration.millis(10), (ActionEvent event) -> {
            update();
        });
        timeline = new Timeline(keyframe);
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }
    ///////////////////////////////////////////////CLASS FUNCTIONS////////////////////////////////////////////////////////
    public void setPane(Pane pane){
        vizPane = pane;
    }
    
    public void setPosition(Vector pos){
        position = pos;
    }
    
    public Pane getPane(){
        return this.vizPane;
    }
    
    public Vector getPosition(){
        return this.position;
    }
    
    public void createTesyl(){
        setupShape();
    }
    
    //////////////////////////////////////////////HELPER FUNCTIONS//////////////////////////////////////////////////////////
    private void update(){
        for(TesylPoint a : points){
            a.update(getBounds());
        }
    }
        
    private Vector getBounds(){
        Vector done = new Vector((int)vizPane.getWidth(),(int)vizPane.getHeight());
        return done;
        
    }
    
    ///////////////////////////////////////////////TEST FUNCTIONS///////////////////////////////////////////////////////////
    
    private void testSequence(){
        
    }
    
    private void connectNodes(TesylPoint uno, TesylPoint dos){
        uno.connect(dos);
    }
    
    

}

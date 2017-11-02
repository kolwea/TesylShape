/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tesylprototyping;

import javafx.beans.binding.Bindings;
import javafx.geometry.Bounds;

/**
 *
 * @author Kolbe
 */
public class Tesyl_InnerBound extends TesylBound{

    private double strokeWidth = 3.0;
    private double slope,intercept;
    private TesylPoint p1,p2;
    private int k,i;
    
    protected Tesyl_InnerBound(){
        super();
        k = 0;
        i = 0;
    }

    
//////////////////////////////////////////////////////////TEST FUNCTIONS////////////////////////////////////////////////////////////
    @Override
    protected void setBound(Object uno, Object dos) {
        if(uno.getClass() == TesylPoint.class && dos.getClass() == TesylPoint.class){
            p1 = (TesylPoint)uno;
            p2 = (TesylPoint)dos;  
            createBind();
        }
        else
            System.out.println("Object type incorrect: TesylPoint");
    }
        
    @Override
    protected double boundFunctionX(double x){
        return slope*x + intercept;
    }
        
    @Override
    protected double boundFunctionY(double y){
        return (y - intercept)/slope;
    }
    
    private void checkWithinBound(){
        if(k > 0)
            k--;
        if(i > 0)
            i --;
        for(TesylPoint curr : points){
           Vector pos = curr.getPosition();
           if((int)pos.y+curr.getBody().getRadius() == (int)boundFunctionX(pos.x) && k == 0){
               curr.reverseVelocity_X();
               k = 10;
           }
           if((int)pos.x+curr.getBody().getRadius() == (int)boundFunctionY(pos.y) && i == 0){
               curr.reverseVelocity_Y();
               i = 10;
           }
           
        }
    }
    
    private void createBind(){            
            boundBody.setStrokeWidth(strokeWidth);
            boundBody.startXProperty().bind(Bindings.createDoubleBinding(() -> {
                Bounds b = p1.getBody().getBoundsInParent();
                return b.getMinX() + b.getWidth() / 2 ;
            }, p1.getBody().boundsInParentProperty()));
            boundBody.startYProperty().bind(Bindings.createDoubleBinding(() -> {
                Bounds b = p1.getBody().getBoundsInParent();
                return b.getMinY() + b.getHeight() / 2 ;
            }, p1.getBody().boundsInParentProperty()));
            boundBody.endXProperty().bind(Bindings.createDoubleBinding(() -> {
                Bounds b = p2.getBody().getBoundsInParent();
                return b.getMinX() + b.getWidth() / 2 ;
            }, p2.getBody().boundsInParentProperty()));
            boundBody.endYProperty().bind(Bindings.createDoubleBinding(() -> {
                Bounds b = p2.getBody().getBoundsInParent();
                return b.getMinY() + b.getHeight() / 2 ;
            }, p2.getBody().boundsInParentProperty()));
    }
    
    protected void update(){
        updateFunction();
        checkWithinBound();
//        System.out.println("Position First point:");
//        p1.getPosition().printVector();
//        System.out.println("Function of point yields " + boundFunction(p1.getPosition().x));
    }
    
    private void updateFunction(){
        Vector pos1 = p1.getPosition();
        Vector pos2 = p2.getPosition();
        double x1 = pos1.x;
        double x2 = pos2.x;
        double y1 = pos1.y;
        double y2 = pos2.y;
        
        slope = (y1 - y2)/(x1 - x2);
        intercept = y1 - (slope * x1);
    }
    

    
}

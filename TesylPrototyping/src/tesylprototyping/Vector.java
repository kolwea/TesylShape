/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tesylprototyping;

/**
 *
 * @author Kolbe
 */
public class Vector {
    
    public double x,y;
    
    public Vector(){
        
    }
        
    public Vector(double x, double y){
        this.x = x; 
        this.y = y;
    }
    
    public void setVector(double x, double y){
        this.x = x;
        this.y = y;
    }
    
    public Vector add(Vector me){
        Vector hai = new Vector(x + me.x, y + me.y);
        return hai;
    }
    
    public Vector sub(Vector me){
        Vector hai = new Vector(x - me.x, y - me.y);
        return hai;
    }
    
    public void printVector(){
        System.out.println("X: " + x + " Y: " + y);
    }
    
    @Override
    public Vector clone(){
        return new Vector(x,y);
    }
    
//    public int getX(){
//        return this.x;
//    }
//    
//    public int getY(){
//        return this.y;
//    }
    
//    public Vector addVector(Vector me){
//        Vector hai = this.clone();
//        this.x += me.getX();
//        this
//    }
    
}

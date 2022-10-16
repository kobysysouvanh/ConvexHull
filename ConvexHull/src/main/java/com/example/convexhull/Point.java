package com.example.convexhull;

import java.util.Random;

// Class Created to Easily Manage Points in the Convex Hull Problem.
public class Point {

    // Instance Variables: X and Y Coordinates.
    private final int x;
    private final int y;
    private final int id;

    static private final Random rand = new Random();
    // Constructor
    public Point(int id){
        this.id = id;
        x = -150 + rand.nextInt(300);
        y = -150 + rand.nextInt(300);
    }

    // Getters
    public int getX(){
        return this.x;
    }
    public int getY(){
        return this.y;
    }

    // Formula Used to Find the Area Between Three Points (from Page 197 of the Book).
    public double area(Point p2, Point p3){
        return this.x * p2.getY() + p3.getX()*this.y + p2.getX()*p3.getY() - p3.getX()*p2.getY() - p2.getX()*this.y - this.x*p3.getY();
    }

    // toString Method To Make Printing More Convenient.
    public String toString(){
        return "Point " + id + ": (" + x + "," + y + ")";
    }
}

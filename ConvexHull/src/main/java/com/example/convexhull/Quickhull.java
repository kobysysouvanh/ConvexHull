package com.example.convexhull;

import java.util.ArrayList;

public class Quickhull {
    public static void main(String[] args){

// Create ArrayList of points in order to test Convex Hull.
        ArrayList<Point> list = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            list.add(new Point(i));
        }



// Get the Convex Hull Using the quickHull Algorithm implemented below.
        ArrayList<Point> convexHull = quickHull(list);

        System.out.println("QUICKHULL");
        //Prints out the inputs
        System.out.println("Inputs:");
        for (Point p : list) {
            System.out.println(p.toString());
        }

        System.out.println();

        System.out.println("Outputs: The following below are the outermost points that the Convex Hull consists of: ");
        for(Point p : convexHull){
            System.out.println(p.toString());
        }

    }

    // Implementation of the QuickHull algorithm using Divide and Conquer design paradigm.
    private static ArrayList<Point> quickHull(ArrayList<Point> list) {


// If Size is Less Than or Equal to 3, Then the List Will be the Convex Hull by Default.
        if(list.size() <= 3){
            return list;
        }

// Initially Declare Both the Minimum and Maximum Point as Element 0 of the List.
        Point min = list.get(0);
        Point max = list.get(0);

// Iterate Over the List in Order To Find The Minimum and Maximum Points.
        for(Point p : list){
            if(p.getX() < min.getX()){
                min = p;
            }
            if(p.getX() > max.getX()){
                max = p;
            }
        }

// Create Final List that Will Hold The Convex Hull.
        ArrayList<Point> convexHull = new ArrayList<>();

// Add Both the Minimum and Maximum Elements of the List Into The Convex Hull.
        convexHull.add(min);
        convexHull.add(max);

// Create New Lists that Split the Points in the Middle.
// Points over the Middle go to the Upper Hull, and Points below the Middle
// Go in the Lower Hull.
        ArrayList<Point> upperHull = new ArrayList<>();
        ArrayList<Point> lowerHull = new ArrayList<>();

// Use the Formula of Area of a Triangle to find if a Point Belongs in the Upper or Lower Hull.
        for(Point p : list){
            if(min.area(max, p) > 0){
                upperHull.add(p);
            }
            if(min.area(max, p) < 0){
                lowerHull.add(p);
            }
        }

// Find the both the Upper and Lower Hulls Recursively .
        findHull(min, max, upperHull, convexHull);
        findHull(max, min, lowerHull, convexHull);

        return convexHull;
    }

    // Private Method Used to Find The Hulls.
    private static void findHull(Point min, Point max, ArrayList<Point> hull, ArrayList<Point> convexHull) {

// If there are no more elements to Verify, Simply Return (Base Case).
        if(hull.size() == 0){
            return;
        }

// Initially Declare pMax (the farthest point from the line) as the 0th Element of the List.
        Point pMax = hull.get(0);

// Find the Farthest Point From the Line Using the Area Formula.
        for(Point p : hull){
            if(min.area(max, p) > min.area(max, pMax)){
                pMax = p;
            }
        }

// Add the Farthest Point to the Convex Hull.
        convexHull.add(pMax);

// Create new Lists that will Split the Remaining Points In Half.
        ArrayList<Point> upperHull = new ArrayList<>();
        ArrayList<Point> lowerHull = new ArrayList<>();

// Using the Area Formula, Find which Points go in the Upper and the Lower Hulls.
        for(Point p : hull){
            if(min.area(pMax, p) > 0){
                upperHull.add(p);
            }
            if(pMax.area(max, p) > 0){
                lowerHull.add(p);
            }
        }

// Recursively Find the Rest of the Convex Hull.
        findHull(min, pMax, upperHull, convexHull);
        findHull(pMax, max, lowerHull, convexHull);

    }

}




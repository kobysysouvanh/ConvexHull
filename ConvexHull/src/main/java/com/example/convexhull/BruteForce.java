package com.example.convexhull;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.util.ArrayList;

public class BruteForce extends Application {
    public static Group root = new Group();
    // Create ArrayList of points and solution points.
    public static ArrayList<Point> list = new ArrayList<>();
    public static ArrayList<Point> solutionList = new ArrayList<>();
    @Override
    public void start(Stage stage) {

        Scene scene = new Scene(root, 700, 700);
        stage.setTitle("Convex Hull");
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();

        //Draws the points on the application
        drawPoints();
        textAbovePoints();
    }

    public static void main(String[] args) {
        // Creates 10 points and adds them to the list.
        for (int i = 0; i < 10; i++) {
            list.add(new Point(i));
        }

        System.out.println("BRUTE FORCE");
        //Prints out the inputs
        System.out.println("Inputs:");
        for (Point p : list) {
            System.out.println(p.toString());
        }
        System.out.println();

        //Takes two points and draws a line between them to divide the plane into two. Then it checks if the rest of the points lie on the same side of the line.
        for (int i = 0; i < list.size(); i++) {
            for (int j = 0; j < list.size(); j++) {
                if (i == j){
                    continue;
                }
                boolean isOnOneSide = true;

                for (int k = 0; k < list.size(); k++){
                    if(k == i || k == j){continue;}
                    int l = pointsOnOneSide(list.get(i), list.get(j), list.get(k));
                    if (l < 0) {
                        isOnOneSide = false;
                        break;
                    }
                }
                if (isOnOneSide) {
                    //draws the lines to connect the points
                    drawLines(list.get(i), list.get(j));
                    if (!solutionList.contains(list.get(i))) {
                        solutionList.add(list.get(i));
                    }
                    if (!solutionList.contains(list.get(j))){
                        solutionList.add(list.get(j));
                    }
                }
            }
        }

        //Prints out the outputs
        System.out.println("Outputs: That create the Convex Hull");
        for (Point p: solutionList) {
            System.out.println(p);
        }

        //launches the application
        launch();
    }
    public static int pointsOnOneSide(Point p1, Point p2, Point p3) {
        return (p3.getX() - p1.getX()) * (p2.getY() - p1.getY()) - (p3.getY() - p1.getY()) * (p2.getX() - p1.getX());
    }

    //Function to draw the points on the application
    public static void drawPoints(){

        for (Point p: list){
            Circle c = new Circle(p.getX()+300, p.getY()+300, 4, Color.BLACK);
            root.getChildren().add(c);
        }
    }
    //Function to draw the lines between the points
    public static void drawLines(Point p1, Point p2){
        Line l = new Line(p1.getX()+300, p1.getY()+300, p2.getX()+300, p2.getY()+300);
        root.getChildren().add(l);


    }

    public static void textAbovePoints() {
        for (Point p: list){
            Text t = new Text(p.getX()+300, p.getY()+290, p.toString());
            root.getChildren().add(t);
        }
    }
}
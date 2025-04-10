/*
Jeremy Underwood
Programming Project 2
4/9/25
The PointComposer class renders points and connects the maximal points with lines.
Further, it allows the user to add and remove points while adjusting the maximal
points accordingly.
 */

import java.util.ArrayList;
import java.util.Collections;

import javafx.scene.input.MouseButton;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

public class PointComposer extends Pane {

    private ArrayList<Point> points;

    public PointComposer(ArrayList<Point> points) {
        this.points = points;

        setPrefSize(500, 500);

        handleMouseClicks();
        connectMaximalSet();
    }

    private void handleMouseClicks() {
        setOnMouseClicked(event -> {
            double xValue = Math.round(event.getX());
            double yValue = Math.round(490 - event.getY());

            if (event.getButton() == MouseButton.PRIMARY) {
                for (Point p : points) {
                    if (p.getX() == xValue && p.getY() == yValue) {
                        return;
                    }
                }
                points.add(new Point(xValue, yValue));
            } else if (event.getButton() == MouseButton.SECONDARY) {
                double buffer = 5.0;
                points.removeIf(p -> Math.abs(p.getX() - xValue) < buffer && Math.abs((490 - p.getY()) - event.getY()) < buffer);
            }
            connectMaximalSet();
        });
    }

    private void connectMaximalSet() {
        getChildren().clear();
        double flipY = getPrefHeight() - 10;

        // Draw points
        for (Point p : points) {
            Circle circle = new Circle(p.getX(), flipY - p.getY(), 4);
            circle.setFill(Color.BLACK);
            getChildren().add(circle);
        }

        ArrayList<Point> maximalPoints = new ArrayList<>();
        for (Point current : points) {
            boolean maximal = true;

            for (Point other : points) {
                if (current.isBelowAndLeftOf(other)) {
                    maximal = false;
                    break;
                }
            }
            if (maximal) {
                maximalPoints.add(current);
            }
        }

        Collections.sort(maximalPoints);

        for (int i = 0; i < maximalPoints.size() - 1; i++) {
            Point p1 = maximalPoints.get(i);
            Point p2 = maximalPoints.get(i + 1);

            double x1 = p1.getX();
            double y1 = flipY - p1.getY();
            double x2 = p2.getX();
            double y2 = flipY - p2.getY();

            Line line = new Line(x1, y1, x2, y2);
            line.setStroke(Color.BLACK);
            getChildren().add(line);
        }
    }
}//end class

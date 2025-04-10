import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import javafx.scene.input.MouseEvent;
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
            double xValue = event.getX();
            double yValue = event.getY();

            if (event.getButton() == MouseButton.PRIMARY) {
                for (Point p : points) {
                    if (p.getX() == xValue && p.getY() == yValue) {
                        System.out.println("Clicked Point: (" + p.getX() + ", " + p.getY() + ")");
                        return;
                    }
                }
                points.add(new Point(xValue, yValue));
            } else if (event.getButton() == MouseButton.SECONDARY) {
                points.removeIf(p -> p.getX() == xValue && p.getY() == yValue);
            }
            connectMaximalSet();
        });

    }

    private void connectMaximalSet() {
        getChildren().clear();

        // Draw points
        for (Point p : points) {
            Circle circle = new Circle(p.getX(), p.getY(), 4);
            circle.setFill(Color.BLACK);
            getChildren().add(circle);
        }

        ArrayList<Point> maximalPoints = new ArrayList<>();
        for (Point candidate : points) {
            boolean isMaximal = true;

            for (Point other : points) {
                if (other.getX() > candidate.getX() && other.getY() > candidate.getY()) {
                    isMaximal = false;
                    break;
                }
            }
            if (isMaximal) {
                maximalPoints.add(candidate);
            }
        }

        // Debug: Print the maximal points before sorting
        System.out.println("Maximal points (before sorting):");
        for (Point mp : maximalPoints) {
            System.out.println("Point: (" + mp.getX() + ", " + mp.getY() + ")");
        }

        Collections.sort(maximalPoints);

        for (int i = 0; i < maximalPoints.size() - 1; i++) {
            Point p1 = maximalPoints.get(i);
            Point p2 = maximalPoints.get(i + 1);

            // Draw horizontal line from p1 to p2.x
            Line horizontal = new Line(p1.getX(), p1.getY(), p2.getX(), p1.getY());
            horizontal.setStroke(Color.BLACK);
            getChildren().add(horizontal);

            // Draw vertical line from p2.x at p1.y to p2.y
            Line vertical = new Line(p2.getX(), p1.getY(), p2.getX(), p2.getY());
            vertical.setStroke(Color.BLACK);
            getChildren().add(vertical);
        }

    }

}

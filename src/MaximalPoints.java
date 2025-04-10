/*
Jeremy Underwood
Programming Project 2
4/9/25
The MaximaPoints class reads coordinates from a file and constructs a list of Point
objects. It launches and initializes the GUI through the PointComposer pane.
 */
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.nio.file.Files;
import java.util.List;
import java.nio.file.Paths;
import java.io.IOException;

public class MaximalPoints {
    public static void main(String[] args) {

        ArrayList<Point> pointList = new ArrayList<>();

        try {
            List<String> lines = Files.readAllLines(Paths.get("points.txt"));
            for (String line : lines) {
                String[] parts = line.trim().split(" ");
                if (parts.length == 2) {
                    double x = Double.parseDouble(parts[0]);
                    double y = Double.parseDouble(parts[1]);
                    pointList.add(new Point(x, y));
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }

        PointDisplay.setPoints(pointList);
        Application.launch(PointDisplay.class);
    }

    public static class PointDisplay extends Application {
        private static ArrayList<Point> initialPoints;

        public static void setPoints(ArrayList<Point> points) {
            initialPoints = points;
        }

        @Override
        public void start(Stage primaryStage) {
            PointComposer pane = new PointComposer(initialPoints);
            Scene scene = new Scene(pane, 500, 500);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Maximal Points");
            primaryStage.setResizable(false);
            primaryStage.show();
        }
    }//end PointDisplay
}//end MaximalPoints

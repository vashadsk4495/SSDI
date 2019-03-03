import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class Main extends Application {

    //Window
    Pane pane = new Pane();
    double width = 300;
    double height = 300;

    @Override
    public void start(Stage primaryStage) {

        //Circle
        Circle circle = new Circle(width / 2, height / 2, 100);
        pane.getChildren().add(circle);
        circle.setFill(Color.TRANSPARENT);
        circle.setStroke(Color.BLACK);

        Circle[] points = new Circle[3];
        Line[] lines = new Line[3];
        Text[] texts = new Text[3];

        for (int i = 0; i < points.length; i++) {

            texts[i] = new Text();
            points[i] = new Circle(0, 0, 5);
            points[i].setFill(Color.RED);
            points[i].setStroke(Color.RED);
            setRandomLocation(points[i], circle);

            int index = i; // for use in lambda expression

            points[i].setOnMouseDragged(e -> {

                double radian = Math.atan2(e.getY() - circle.getCenterY(), e.getX() - circle.getCenterX());
                double x = circle.getCenterX() + circle.getRadius() * Math.cos(radian);
                double y = circle.getCenterY() + circle.getRadius() * Math.sin(radian);
                points[index].setCenterX(x);
                points[index].setCenterY(y);
                updateLines(lines, points, texts);
            });

        }

        for (int i = 0; i < lines.length; i++) {
            int cIndex2 = (i + 1 >= points.length) ? 0 : i + 1;
            lines[i] = new Line(
                    points[i].getCenterX(), points[i].getCenterY(),
                    points[cIndex2].getCenterX(), points[cIndex2].getCenterY());

        }
        updateLines(lines, points, texts);
        pane.getChildren().addAll(lines);
        pane.getChildren().addAll(texts);
        pane.getChildren().addAll(points);
        primaryStage.setScene(new Scene(pane, width, height));
        primaryStage.setTitle("Exercise 3");
        primaryStage.show();
    }

    private void updateLines(Line[] lines, Circle[] p, Text[] angles) {

        for (int i = 0; i < lines.length; i++) {

            int cIndex2 = (i + 1 >= p.length) ? 0 : i + 1;
            lines[i].setStartX(p[i].getCenterX());
            lines[i].setStartY(p[i].getCenterY());
            lines[i].setEndX(p[cIndex2].getCenterX());
            lines[i].setEndY(p[cIndex2].getCenterY());
            angles[i].setX(p[i].getCenterX() + 5);
            angles[i].setY(p[i].getCenterY() - 5);

        }

        double a = drawLines(lines[0]);
        double b = drawLines(lines[1]);
        double c = drawLines(lines[2]);

        //Computing and setting angles
        double A = Math.toDegrees(Math.acos((a * a - b * b - c * c) / (-2 * b * c)));
        angles[2].setText(String.format("%.2f", A));

        double B = Math.toDegrees(Math.acos((b * b - a * a - c * c) / (-2 * a * c)));
        angles[0].setText(String.format("%.2f", B));

        double C = Math.toDegrees(Math.acos((c * c - b * b - a * a) / (-2 * a * b)));
        angles[1].setText(String.format("%.2f", C));

    }

    private void setRandomLocation(Circle tPoint, Circle c) {

        double angle = Math.random() * 360;
        double x = c.getCenterX() + c.getRadius() * Math.cos(Math.toRadians(angle));
        double y = c.getCenterY() + c.getRadius() * Math.sin(Math.toRadians(angle));
        tPoint.setCenterX(x);
        tPoint.setCenterY(y);

    }

    public static double distance(double x1, double y1, double x2, double y2) {

        return Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2));
    }

    public static double drawLines(Line line) {
        return distance(line.getStartX(), line.getStartY(), line.getEndX(), line.getEndY());
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}
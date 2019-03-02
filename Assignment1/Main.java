
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.geometry.Insets;
import javafx.stage.Stage;
import javafx.scene.image.ImageView;
import java.util.ArrayList;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {

        Pane pane = new HBox(10);
        pane.setPadding(new Insets(5, 5, 5, 5));

        //creating an array for cards
        ArrayList<String> cards = new ArrayList<>();

        for (int i = 0; i < 52; i++) {
            cards.add(String.valueOf(i + 1));
        }

        java.util.Collections.shuffle(cards);

        ImageView image1 = new ImageView("file:///C:/Users/100723510/Desktop/Cards/" + cards.get(0) + ".png");
        ImageView image2 = new ImageView("file:///C:/Users/100723510/Desktop/Cards/" + cards.get(1) + ".png");
        ImageView image3 = new ImageView("file:///C:/Users/100723510/Desktop/Cards/" + cards.get(2) + ".png");

        pane.getChildren().add(image1);
        pane.getChildren().add(image2);
        pane.getChildren().add(image3);

        Scene scene = new Scene(pane);
        primaryStage.setTitle("Exercise1");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
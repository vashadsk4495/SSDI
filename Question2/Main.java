import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.scene.control.Label;

public class Main extends Application {

    //Variables
    private TextField investmentAmount = new TextField();
    private TextField years = new TextField();
    private TextField annualInterestRate = new TextField();
    private TextField futureValue = new TextField();

    private Button calculateButton = new Button("Calculate");

    @Override
    public void start(Stage primaryStage) {

        //Interface
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(10,10,10,10));
        gridPane.setHgap(5);
        gridPane.setVgap(5);
        gridPane.add(new Label("Investment Amount: "),0,0);
        gridPane.add(investmentAmount, 1, 0);
        gridPane.add(new Label("Years:"), 0, 1);
        gridPane.add(years, 1, 1);
        gridPane.add(new Label("Annual Interest Rate"), 0, 2);
        gridPane.add(annualInterestRate, 1, 2);
        gridPane.add(new Label("Future Value:"), 0, 3);
        gridPane.add(futureValue, 1, 3);

        //Button on right
        HBox button = new HBox();
        button.getChildren().add(calculateButton);
        button.setAlignment(Pos.BOTTOM_RIGHT);
        gridPane.add(button,1,4);

        //disable last textfield
        futureValue.setDisable(true);

        //clicking calculates future value
        calculateButton.setOnAction(e -> getFutureValue());

        // Create a scene and place it in the stage
        Scene scene = new Scene(gridPane, 290, 250);
        primaryStage.setTitle("Exercise 2"); // Set title
        primaryStage.setScene(scene); // Place the scene in the stage
        primaryStage.show(); // Display the stage

    }

    //calculates future value
    private void getFutureValue(){

        double invAmount = Double.parseDouble(investmentAmount.getText());
        int invYears = Integer.parseInt(years.getText());
        double monthlyInterest = Double.parseDouble(annualInterestRate.getText())/12/100;

        double finalValue = invAmount* Math.pow( 1 + monthlyInterest, invYears * 12);

        futureValue.setText(String.format("$%.2f", finalValue));
    }

    public static void main(String[] args) {
        launch(args);
    }
}


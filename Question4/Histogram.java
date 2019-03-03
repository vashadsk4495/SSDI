import javafx.geometry.VPos;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Histogram extends Pane {

    //Variables
    private char[] chars = new char[26];
    private int counts[] = new int[26];
    private Rectangle[] bars = new Rectangle[26];
    private File file;
    GridPane pane;

    //Window dimensions
    double w = 350;
    double h = 350;

    public Histogram(String filename) {

        this.file = new File(filename.trim());

        //call all methods
        setWidth(w);
        setHeight(h);
        readFile();
        draw();
    }

    private void readFile() {

        Scanner scanner; //text scanner
        String s = "";

        //reads through line by line in file
        try {
            scanner = new Scanner(file);
            while (scanner.hasNextLine()) {

                s += scanner.nextLine();
            }
        } catch (IOException ex) {
        }

        s = s.toUpperCase();
        for (int i = 0; i < s.length(); i++) {

            char character = s.charAt(i); //getting character at that particular index

            if (Character.isLetter(character)) { //only alphabet taken
                counts[character - 'A']++;
            }
        }

    }

    //get total alphabets
    private double getTotal() {
        double total = 0;
        for (int count : counts) {
            total += count;
        }
        return total;
    }

    private void draw() {

        pane = new GridPane();

        //setting up bar width
        double barW = w / chars.length;
        double total = getTotal();

        for (int i = 0; i < counts.length; i++) {

            chars[i] = (char) ('A' + i); //alphabet

            //setting up bar heights
            double percentage = counts[i] / total;
            double barH = h * percentage;

            //drawing the bars and placing them following desired UI
            bars[i] = new Rectangle(barW, barH);
            bars[i].setStroke(Color.BLACK);
            bars[i].setFill(Color.WHITE);
            Label label = new Label(chars[i] + "", bars[i]);
            label.setContentDisplay(ContentDisplay.TOP);
            pane.add(label, i, 0);
            GridPane.setValignment(label, VPos.BASELINE);

        }

        //adding everything to the pane so its visible
        getChildren().addAll(pane);

    }

}